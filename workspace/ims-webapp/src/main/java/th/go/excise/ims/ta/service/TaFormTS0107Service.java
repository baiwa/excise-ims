package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_FORM_TS_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0107;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0107Repository;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

@Service
public class TaFormTS0107Service extends AbstractTaFormTSService<TaFormTS0107Vo, TaFormTs0107> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	@Autowired
	private TaFormTs0107Repository taFormTs0107Repository;

	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_07;
	}

	@Override
	public byte[] processFormTS(TaFormTS0107Vo formTS0107Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0107Vo);
		byte[] reportFile = generateReport(formTS0107Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0107Vo formTS0107Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0107Vo.getFormTsNumber());
		
		TaFormTs0107 formTs0107 = null;
		String formTsNumber = null;
		if (StringUtils.isNotBlank(formTS0107Vo.getFormTsNumber()) && !NULL.equalsIgnoreCase(formTS0107Vo.getFormTsNumber())) {
			// Case Update FormTS
			formTsNumber = formTS0107Vo.getFormTsNumber();
						
			formTs0107 = taFormTs0107Repository.findByFormTsNumber(formTS0107Vo.getFormTsNumber());
			toEntity(formTs0107, formTS0107Vo);
		} else {
			// Case New FormTS
			formTsNumber = taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear);
						
			formTs0107 = new TaFormTs0107();
			toEntity(formTs0107, formTS0107Vo);
			formTs0107.setOfficeCode(officeCode);
			formTs0107.setBudgetYear(budgetYear);
			formTs0107.setFormTsNumber(formTsNumber);
		}
		
		saveAuditStep(formTS0107Vo, TaFormTS0107Vo.class, TA_FORM_TS_CODE.TS0107, formTsNumber);
		taFormTs0107Repository.save(formTs0107);
	}

	@Override
	public byte[] generateReport(TaFormTS0107Vo formTS0107Vo) throws Exception, IOException {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTS0107Vo.getFormTsNumber());
		params.put("bookNumber1", formTS0107Vo.getBookNumber1());
		params.put("bookNumber2", formTS0107Vo.getBookNumber2());
		params.put("officeName1", formTS0107Vo.getOfficeName1());
		params.put("docDate", formTS0107Vo.getDocDate());
		params.put("officeName2", formTS0107Vo.getOfficeName2());
		params.put("headOfficerFullName", formTS0107Vo.getHeadOfficerFullName());
		params.put("headOfficerPosition", formTS0107Vo.getHeadOfficerPosition());
		params.put("officerFullName1", formTS0107Vo.getOfficerFullName1());
		params.put("officerPosition1", formTS0107Vo.getOfficerPosition1());
		params.put("officerFullName2", formTS0107Vo.getOfficerFullName2());
		params.put("officerPosition2", formTS0107Vo.getOfficerPosition2());
		params.put("officerFullName3", formTS0107Vo.getOfficerFullName3());
		params.put("officerPosition3", formTS0107Vo.getOfficerPosition3());
		params.put("officerFullName4", formTS0107Vo.getOfficerFullName4());
		params.put("officerPosition4", formTS0107Vo.getOfficerPosition4());
		params.put("officerFullName5", formTS0107Vo.getOfficerFullName5());
		params.put("officerPosition5", formTS0107Vo.getOfficerPosition5());
		params.put("companyName", formTS0107Vo.getCompanyName());
		params.put("factoryType", formTS0107Vo.getFactoryType());
		params.put("factoryName", formTS0107Vo.getFactoryName());
		params.put("newRegId", formTS0107Vo.getNewRegId());
		params.put("facAddrNo", formTS0107Vo.getFacAddrNo());
		params.put("facMooNo", formTS0107Vo.getFacMooNo());
		params.put("facSoiName", formTS0107Vo.getFacSoiName());
		params.put("facThnName", formTS0107Vo.getFacThnName());
		params.put("facTambolName", formTS0107Vo.getFacTambolName());
		params.put("facAmphurName", formTS0107Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0107Vo.getFacProvinceName());
		params.put("facZipCode", formTS0107Vo.getFacZipCode());
		params.put("auditDate", formTS0107Vo.getAuditDate());
		params.put("lawSection", formTS0107Vo.getLawSection());
		params.put("headOfficerPhone", formTS0107Vo.getHeadOfficerPhone());
		params.put("signOfficerFullName", formTS0107Vo.getSignOfficerFullName());
		params.put("signOfficerPosition", formTS0107Vo.getSignOfficerPosition());
		params.put("otherText", formTS0107Vo.getOtherText());
		params.put("otherPhone", formTS0107Vo.getOtherPhone());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0107Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0107Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}", formTsNumber);

		TaFormTs0107 formTs0107 = taFormTs0107Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0107Vo formTs0107Vo = new TaFormTS0107Vo();
		toVo(formTs0107Vo, formTs0107);

		return formTs0107Vo;
	}

}
