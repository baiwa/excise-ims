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

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.persistence.repository.IaGfmovementAccountRepository;

@Service
public class IaGfmovementAccountService {

	@Autowired
	private IaGfmovementAccountRepository iaGfmovementAccountRepository;

	public final String KEY_FILTER[] = { "บัญชีแยกประเภท", "บัญชีเงินฝาก", "รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง"
			, "Program name  :"
			, "User name     :"  
			, "ตั้งแต่"};

	public void addDataByExcel(MultipartFile file) throws IOException {
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
									&& !( KEY_FILTER[3].equals(ExcelUtils.getCellValueAsString(r.getCell(0))) )
									&&! (KEY_FILTER[4].equals(ExcelUtils.getCellValueAsString(r.getCell(0))))
 									&&!( r.getCell(0) != null  ? ExcelUtils.getCellValueAsString(r.getCell(0)).indexOf(KEY_FILTER[5]) >-1 : false
									)) {
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
					if(StringUtils.isNotBlank(iaGfmovementAccount.getGfDocNo())) {
						;
					}
					iaGfmovementAccountList.add(iaGfmovementAccount);
					System.out.println("");

				} catch (Exception e) {
//					System.out.print(valueExc + "|err|");
//					e.printStackTrace();
				}
			}
		}
		iaGfmovementAccountRepository.batchInsert(iaGfmovementAccountList);
	}
}
