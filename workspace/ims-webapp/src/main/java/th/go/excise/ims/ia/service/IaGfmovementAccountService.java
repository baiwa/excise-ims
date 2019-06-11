package th.go.excise.ims.ia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monitorjbl.xlsx.StreamingReader;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfuploadH;
import th.go.excise.ims.ia.persistence.repository.IaGfmovementAccountRepository;
import th.go.excise.ims.ia.persistence.repository.IaGfuploadHRepository;
import th.go.excise.ims.ia.vo.Int15ResponseUploadVo;
import th.go.excise.ims.ia.vo.Int15SaveVo;

@Service
public class IaGfmovementAccountService {

	@Autowired
	private IaGfmovementAccountRepository iaGfmovementAccountRepository;

	@Autowired
	private IaGfuploadHRepository iaGfuploadHRepository;

	public final String KEY_FILTER[] = { "บัญชีแยกประเภท", "บัญชีเงินฝาก", "รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง", "Program name  :", "User name     :", "ตั้งแต่" };

	public ResponseData<Int15ResponseUploadVo> addDataByExcel(MultipartFile file) throws IOException {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		List<IaGfmovementAccount> iaGfmovementAccountList = new ArrayList<>();
		IaGfmovementAccount iaGfmovementAccount = new IaGfmovementAccount();
		Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(file.getInputStream());
		String valueExc = "";
		for (Sheet sheet : workbook) {
			String accTypeNo = "";
			String accTypeName = "";
			String accNo = "";
			Date docDate = null;
			for (Row r : sheet) {
				iaGfmovementAccount = new IaGfmovementAccount();
				try {
					for (Cell c : r) {
						valueExc = "'" + ExcelUtils.getCellValueAsString(c) + "':" + c.getColumnIndex();
						String lineData = ExcelUtils.getCellValueAsString(c);
						if (c.getColumnIndex() == 0 && KEY_FILTER[0].equals(lineData.split(":")[0].trim())) {
							String data[] = lineData.split(":")[1].trim().split(" ");
							accTypeNo = data[0];
							accTypeName = data[1];
						} else if (c.getColumnIndex() == 3 && KEY_FILTER[1].equals(lineData.split(":")[0].trim())) {
							accNo = lineData.split(":")[1].trim().split(" ")[0];
						} else {
							if (r.getLastCellNum() > 15 && r.getCell(4) != null && r.getCell(6) != null && r.getCell(7) != null && !(KEY_FILTER[2].equals(ExcelUtils.getCellValueAsString(r.getCell(9))))
									&& !(KEY_FILTER[3].equals(ExcelUtils.getCellValueAsString(r.getCell(0)))) && !(KEY_FILTER[4].equals(ExcelUtils.getCellValueAsString(r.getCell(0))))
									&& !(r.getCell(0) != null ? ExcelUtils.getCellValueAsString(r.getCell(0)).indexOf(KEY_FILTER[5]) > -1 : false)) {
								System.out.print(valueExc + "||");
								switch (c.getColumnIndex()) {

								case 1:
									docDate = ConvertDateUtils.parseStringToDate(ExcelUtils.getCellValueAsString(c), ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.LOCAL_EN);
									break;
								case 4:
									iaGfmovementAccount.setGfDocNo(ExcelUtils.getCellValueAsString(c));
									break;
								case 6:
									iaGfmovementAccount.setGfDocTyep(ExcelUtils.getCellValueAsString(c));
									break;
								case 7:
									iaGfmovementAccount.setGfRefDoc(ExcelUtils.getCellValueAsString(c));
									break;
								case 8:
									iaGfmovementAccount.setCareInstead(ExcelUtils.getCellValueAsString(c));
									break;
								case 11:
									iaGfmovementAccount.setDeterminaton(ExcelUtils.getCellValueAsString(c));
									break;
								case 14:
									iaGfmovementAccount.setDepCode(ExcelUtils.getCellValueAsString(c));
									break;
								case 15:
									iaGfmovementAccount.setDebit(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
									break;
								case 16:
									iaGfmovementAccount.setCredit(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
									break;
								case 18:
									iaGfmovementAccount.setCarryForward(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
									break;

								default:
									break;
								}
							}
						}
					}
					iaGfmovementAccount.setAccTypeNo(accTypeNo);
					iaGfmovementAccount.setAccTypeName(accTypeName);
					iaGfmovementAccount.setAccNo(accNo);
					iaGfmovementAccount.setGfDocDate(docDate);
					if (StringUtils.isNotBlank(iaGfmovementAccount.getGfDocNo())) {
						iaGfmovementAccountList.add(iaGfmovementAccount);
					}
					System.out.println("");

				} catch (Exception e) {
					// System.out.print(valueExc + "|err|");
					// e.printStackTrace();
				}

			}
		}
		Int15ResponseUploadVo response = new Int15ResponseUploadVo();
		response.setFileName(file.getOriginalFilename());
		response.setFormData4(iaGfmovementAccountList);
		responseData.setData(response);
		responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
		responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		// iaGfmovementAccountRepository.batchInsert(iaGfmovementAccountList);
		return responseData;
	}

	public void saveData(Int15SaveVo form) {
		IaGfuploadH ia = new IaGfuploadH();
		ia.setPeriodMonth(form.getPeriod());
		ia.setPeriodYear(form.getYear());
		ia.setStartDate(ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setEndDate(ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setUploadType(form.getTypeData());
		ia.setDeptDisb(form.getDisburseMoney());
		ia.setFileName(form.getFileName());
		iaGfuploadHRepository.save(ia);
		if (form.getFormData3() != null && form.getFormData4().size() > 0) {
			for (IaGfmovementAccount iaGfmovementAccount : form.getFormData4()) {
				iaGfmovementAccount.setGfuploadHId(ia.getGfuploadHId());
			}
		}
		iaGfmovementAccountRepository.saveAll(form.getFormData4());
	}
}
