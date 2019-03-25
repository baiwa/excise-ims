package th.go.excise.ims.ta.service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0114Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0113Repository;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaFormTS0114Service extends AbstractTaFormTSService<TaFormTS0114Vo, TaFormTs0114Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0114Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0113Repository taFormTs0113Repository;

	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0114Vo taFormTS0114Vo) throws Exception {
		logger.info("processFormTS");

		//saveFormTS(formTS0113Vo);
		byte[] reportFile = generateReport(taFormTS0114Vo);

		return reportFile;
	}

    protected void saveFormTS(TaFormTS0114Vo formTS0114Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
//		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0113Vo.getFormTsNumber());
//
//		TaFormTs0113 formTS0113 = null;
//		if (StringUtils.isNotEmpty(formTS0113Vo.getFormTsNumber())) {
//			formTS0113 = taFormTs0113Repository.findByFormTsNumber(formTS0113Vo.getFormTsNumber());
//			toEntity(formTS0113, formTS0113Vo);
//		} else {
//			formTS0113 = new TaFormTs0113();
//			toEntity(formTS0113, formTS0113Vo);
//			formTS0113.setOfficeCode(officeCode);
//			formTS0113.setBudgetYear(budgetYear);
//			formTS0113.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
//		}
//		taFormTs0113Repository.save(formTS0113);
	}

	public byte[] generateReport(TaFormTS0114Vo formTS0114Vo) throws Exception, IOException {
		logger.info("generateReport");
		
		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
        params.put("formTsNumber", formTS0114Vo.getFormTsNumber());
        params.put("factoryName", formTS0114Vo.getFactoryName());
        params.put("newRegId", formTS0114Vo.getNewRegId());
        params.put("facAddrNo", formTS0114Vo.getFacAddrNo());
        params.put("facSoiName", formTS0114Vo.getFacSoiName());
        params.put("facThnName", formTS0114Vo.getFacThnName());
        params.put("facTambolName", formTS0114Vo.getFacTambolName());
        params.put("facAmphurName", formTS0114Vo.getFacAmphurName());
        params.put("facProvinceName", formTS0114Vo.getFacProvinceName());
        params.put("facZipCode", formTS0114Vo.getFacZipCode());
        params.put("facTelNo", formTS0114Vo.getFacTelNo());
        params.put("facFaxNumber", formTS0114Vo.getFacFaxNumber());
        params.put("factoryTypeText", formTS0114Vo.getFactoryTypeText());
        params.put("officerFullName", formTS0114Vo.getOfficerFullName());
        params.put("officerDept", formTS0114Vo.getOfficerDept());
        params.put("auditDate", formTS0114Vo.getAuditDate());
        params.put("bookNumber1", formTS0114Vo.getBookNumber1());
        params.put("bookDate", formTS0114Vo.getBookDate());
        params.put("auditDateStart", formTS0114Vo.getAuditDateStart());
        params.put("auditDateEnd", formTS0114Vo.getAuditDateEnd());
        params.put("auditSumMonth", formTS0114Vo.getAuditSumMonth());
        params.put("auditSumDay", formTS0114Vo.getAuditSumDay());
        params.put("auditBookType", formTS0114Vo.getAuditBookType());
        params.put("auditBookTypeOther", formTS0114Vo.getAuditBookTypeOther());
        params.put("auditBookNumber", formTS0114Vo.getAuditBookNumber());
        params.put("auditBookDate", formTS0114Vo.getAuditBookDate());
        params.put("docNum", formTS0114Vo.getDocNum());
        params.put("doc1Num", formTS0114Vo.getDoc1Num());
        params.put("doc1Date", formTS0114Vo.getDoc1Date());
        params.put("doc2Num", formTS0114Vo.getDoc2Num());
        params.put("doc2Date", formTS0114Vo.getDoc2Date());
        params.put("doc3Num", formTS0114Vo.getDoc3Num());
        params.put("doc3Date", formTS0114Vo.getDoc3Date());
        params.put("doc4Num", formTS0114Vo.getDoc4Num());
        params.put("doc5Num", formTS0114Vo.getDoc5Num());
        params.put("doc6Num", formTS0114Vo.getDoc6Num());
        params.put("doc7Num", formTS0114Vo.getDoc7Num());
        params.put("doc8Num", formTS0114Vo.getDoc8Num());
        params.put("doc9Num", formTS0114Vo.getDoc9Num());
        params.put("assReason", formTS0114Vo.getAssReason());
        params.put("signOfficerFullName", formTS0114Vo.getSignOfficerFullName());
        params.put("signOfficerPosition", formTS0114Vo.getSignOfficerPosition());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS0114Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
