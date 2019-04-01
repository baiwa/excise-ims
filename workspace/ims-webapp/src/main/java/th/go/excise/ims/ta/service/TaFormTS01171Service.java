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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0117;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01171;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01171Repository;
import th.go.excise.ims.ta.vo.TaFormTS01171Vo;
import th.go.excise.ims.ta.vo.TaFormTS0117Vo;

@Service
public class TaFormTS01171Service extends AbstractTaFormTSService<TaFormTS01171Vo, TaFormTs01171> {

private static final Logger logger = LoggerFactory.getLogger(TaFormTS01171Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs01171Repository taFormTs01171Repository;
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_17_1;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS01171Vo formTS01171Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS01171Vo);
		byte[] reportFile = generateReport(formTS01171Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS01171Vo formTS01171Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01171Vo.getFormTsNumber());

		TaFormTs01171 formTS01171 = null;
		if (StringUtils.isNotEmpty(formTS01171Vo.getFormTsNumber())) {
			formTS01171 = taFormTs01171Repository.findByFormTsNumber(formTS01171Vo.getFormTsNumber());
			toEntity(formTS01171, formTS01171Vo);
		} else {
			formTS01171 = new TaFormTs01171();
			toEntity(formTS01171, formTS01171Vo);
			formTS01171.setOfficeCode(officeCode);
			formTS01171.setBudgetYear(budgetYear);
			formTS01171.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs01171Repository.save(formTS01171);
	}
	
	@Override
	public byte[] generateReport(TaFormTS01171Vo request) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("bookNumber1", request.getBookNumber1());
		params.put("bookNumber2", request.getBookNumber2());
		params.put("docTopic", request.getDocTopic());
		params.put("docDate", request.getDocDate());
		params.put("docDear", request.getDocDear());
		params.put("factoryName", request.getFactoryName());
		params.put("newRegId", request.getNewRegId());
		params.put("factoryType", request.getFactoryType());
		params.put("facAddrNo", request.getFacAddrNo());
		params.put("facMooNo", request.getFacMooNo());
		params.put("facSoiName", request.getFacSoiName());
		params.put("facThnName", request.getFacThnName());
		params.put("facTambolName", request.getFacTambolName());
		params.put("facAmphurName", request.getFacAmphurName());
		params.put("facProvinceName", request.getFacProvinceName());
		params.put("facZipCode", request.getFacZipCode());
		params.put("bookType", request.getBookType());
		params.put("refBookNumber1", request.getRefBookNumber1());
		params.put("refBookNumber2", request.getRefBookNumber2());
		params.put("refDocDate", request.getRefDocDate());
		params.put("auditDateStart", request.getAuditDateStart());
		params.put("auditDateEnd", request.getAuditDateEnd());
		params.put("factDesc", request.getFactDesc());
		params.put("lawDesc", request.getLawDesc());
		params.put("factoryName2", request.getFactoryName2());
		params.put("taxAmt", request.getTaxAmt());
		params.put("fineAmt", request.getFineAmt());
		params.put("extraAmt", request.getExtraAmt());
		params.put("exciseTaxAmt", request.getExciseTaxAmt());
		params.put("moiAmt", request.getMoiAmt());
		params.put("sumAllTaxAmt", request.getSumAllTaxAmt());
		params.put("extraDate", request.getExtraDate());
		params.put("officeDest", request.getOfficeDest());
		params.put("officeDate", request.getOfficeDate());
		params.put("officeTime", request.getOfficeTime());
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("signOfficerPosition", request.getSignOfficerPosition());
		params.put("officeName", request.getOfficeName());
		params.put("offficePhone", request.getOffficePhone());
		params.put("headOfficerFullName", request.getHeadOfficerFullName());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_17_1 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs01171Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS01171Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}");
		
		TaFormTs01171 formTs01171 = taFormTs01171Repository.findByFormTsNumber(formTsNumber);
		
		// Set Data
		TaFormTS01171Vo formTs01171Vo = new TaFormTS01171Vo();
		toVo(formTs01171Vo, formTs01171);
		
		return formTs01171Vo;
	}

}
