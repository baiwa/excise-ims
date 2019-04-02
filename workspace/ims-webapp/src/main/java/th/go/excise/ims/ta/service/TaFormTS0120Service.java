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
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0120;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0120Repository;
import th.go.excise.ims.ta.vo.TaFormTS0120Vo;

@Service
public class TaFormTS0120Service extends AbstractTaFormTSService<TaFormTS0120Vo, TaFormTs0120> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0120Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0120Repository taFormTs0120Repository;
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_20;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0120Vo formTS0120Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0120Vo);
		byte[] reportFile = generateReport(formTS0120Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0120Vo formTS0120Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0120Vo.getFormTsNumber());

		TaFormTs0120 formTS0120 = null;
		if (StringUtils.isNotEmpty(formTS0120Vo.getFormTsNumber())) {
			formTS0120 = taFormTs0120Repository.findByFormTsNumber(formTS0120Vo.getFormTsNumber());
			toEntity(formTS0120, formTS0120Vo);
		} else {
			formTS0120 = new TaFormTs0120();
			toEntity(formTS0120, formTS0120Vo);
			formTS0120.setOfficeCode(officeCode);
			formTS0120.setBudgetYear(budgetYear);
			formTS0120.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0120Repository.save(formTS0120);
	}
	
	@Override
	public byte[] generateReport(TaFormTS0120Vo request) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("factoryName", request.getFactoryName());
		params.put("docDear", request.getDocDear());
		params.put("bookNumber1", request.getBookNumber1());
		params.put("bookDate", request.getBookDate());
		params.put("factoryName2", request.getFactoryName2());
		params.put("newRegId", request.getNewRegId());
		params.put("auditDateStart", request.getAuditDateStart());
		params.put("auditDateEnd", request.getAuditDateEnd());
		params.put("facAddrNo", request.getFacAddrNo());
		params.put("facMooNo", request.getFacMooNo());
		params.put("facSoiName", request.getFacSoiName());
		params.put("facThnName", request.getFacThnName());
		params.put("facTambolName", request.getFacTambolName());
		params.put("facAmphurName", request.getFacAmphurName());
		params.put("facProvinceName", request.getFacProvinceName());
		params.put("facZipCode", request.getFacZipCode());
		params.put("expandReason", request.getExpandReason());
		params.put("expandFlag1", request.getExpandFlag1());
		params.put("expandFlag2", request.getExpandFlag2());
		params.put("expandNo", request.getExpandNo());
		params.put("expandDateOld", request.getExpandDateOld());
		params.put("expandDateNew", request.getExpandDateNew());
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("signOfficerDate", request.getSignOfficerDate());
		params.put("headOfficerComment", request.getHeadOfficerComment());
		params.put("signHeadOfficerFullName", request.getSignHeadOfficerFullName());
		params.put("signHeadOfficerDate", request.getSignHeadOfficerDate());
		params.put("approverComment", request.getApproverComment());
		params.put("approveFlag", request.getApproveFlag());
		params.put("signApproverFullName", request.getSignApproverFullName());
		params.put("signApproverDate", request.getSignApproverDate());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_20 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		return taFormTs0120Repository.findFormTsNumber();
	}

	@Override
	public TaFormTS0120Vo getFormTS(String formTsNumber) {
		TaFormTS0120Vo formTS0120Vo = null;
        if (StringUtils.isNotBlank(formTsNumber)) {
            TaFormTs0120 entiry = taFormTs0120Repository.findByFormTsNumber(formTsNumber);
            formTS0120Vo = new TaFormTS0120Vo();
            if (entiry != null) {

                toVo(formTS0120Vo, entiry);
            }
        }
        return formTS0120Vo;
	}

}
