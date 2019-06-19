package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.persistence.entity.IaGfuploadH;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int15ResponseUploadVo;
import th.go.excise.ims.ia.vo.Int15SaveVo;

@Service
public class IaGftrialBalanceService {

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;

	private final String KEY_FILTER = "User name     :";

	public ResponseData<Int15ResponseUploadVo> addDataByExcel(MultipartFile file) {
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			String departmentCode = "";
			String periodFrom = "";
			String periodTo = "";
			String periodYear = "";
			List<IaGftrialBalance> iaRepDisbPerMonthList = new ArrayList<>();
			IaGftrialBalance iaRepDisbPerMonth = new IaGftrialBalance();
			List<List<String>> allLine = ExcelUtils.readExcel(file);
			for (List<String> line : allLine) {
				if (line != null && line.size() == 5 && line.get(0) != null && KEY_FILTER.equals(line.get(0).trim())) {
					departmentCode = line.get(2).trim().split(" ")[1];
				} else if (line != null && line.size() == 1 && line.get(0).trim().split(" ").length == 6) {
					String[] periodData = line.get(0).trim().split(" ");
					periodFrom = periodData[1];
					periodTo = periodData[3];
					periodYear = periodData[5];
				} else if (line != null && line.size() == 6) {
					iaRepDisbPerMonth = new IaGftrialBalance();
					iaRepDisbPerMonth.setDeptDisb(departmentCode);
					iaRepDisbPerMonth.setPeriodFrom(periodFrom);
					iaRepDisbPerMonth.setPeriodTo(periodTo);
					iaRepDisbPerMonth.setPeriodYear(periodYear);
					iaRepDisbPerMonth.setAccNo(line.get(0));
					iaRepDisbPerMonth.setAccName(line.get(1));
					iaRepDisbPerMonth.setBringForward(NumberUtils.toBigDecimal(line.get(2)));
					iaRepDisbPerMonth.setDebit(NumberUtils.toBigDecimal(line.get(3)));
					iaRepDisbPerMonth.setCredit(NumberUtils.toBigDecimal(line.get(4)));
					iaRepDisbPerMonth.setCarryForward(NumberUtils.toBigDecimal(line.get(5)));
					iaRepDisbPerMonthList.add(iaRepDisbPerMonth);
				}
			}
			Int15ResponseUploadVo response  = new Int15ResponseUploadVo();
			response.setFileName(file.getOriginalFilename());
			response.setFormData2(iaRepDisbPerMonthList);
			responseData.setData(response);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
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
		if (form.getFormData2() != null && form.getFormData2().size() > 0) {
			for (IaGftrialBalance iaGfmovementAccount : form.getFormData2()) {
				iaGfmovementAccount.setGfuploadHId(ia.getGfuploadHId());
			}
		}
		iaGftrialBalanceRepository.saveAll(form.getFormData2());
	}
}
