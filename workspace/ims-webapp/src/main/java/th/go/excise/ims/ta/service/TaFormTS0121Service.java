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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0121;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0121Repository;
import th.go.excise.ims.ta.vo.TaFormTS0121Vo;

@Service
public class TaFormTS0121Service extends AbstractTaFormTSService<TaFormTS0121Vo, TaFormTs0121> {
	
private static final Logger logger = LoggerFactory.getLogger(TaFormTS0121Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0121Repository taFormTs0121Repository;
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0121Vo formTS0121Vo) throws Exception {
		logger.info("processFormTS");

//		saveFormTS(formTS0121Vo);
		byte[] reportFile = generateReport(formTS0121Vo);

		return reportFile;
	}
	
	protected void saveFormTS(TaFormTS0121Vo formTS0121Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0121Vo.getFormTsNumber());

		TaFormTs0121 formTS0121 = null;
		if (StringUtils.isNotEmpty(formTS0121Vo.getFormTsNumber())) {
			formTS0121 = taFormTs0121Repository.findByFormTsNumber(formTS0121Vo.getFormTsNumber());
			toEntity(formTS0121, formTS0121Vo);
		} else {
			formTS0121 = new TaFormTs0121();
			toEntity(formTS0121, formTS0121Vo);
			formTS0121.setOfficeCode(officeCode);
			formTS0121.setBudgetYear(budgetYear);
			formTS0121.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0121Repository.save(formTS0121);
	}
	
	public byte[] generateReport(TaFormTS0121Vo request) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", request.getFormTsNumber());
		params.put("factoryName", request.getFactoryName());
		params.put("officerSendFullName1", request.getOfficerSendFullName1());
		params.put("officerSendPosition1", request.getOfficerSendPosition1());
		params.put("officerReceiveFullName1", request.getOfficerReceiveFullName1());
		params.put("officerReceivePosition1", request.getOfficerReceivePosition1());
		params.put("officeName", request.getOfficeName());
		params.put("docDate", request.getDocDate());
		params.put("comdDesc", request.getComdDesc());
		params.put("comdDate", request.getComdDate());
		params.put("officerSendFullName2", request.getOfficerSendFullName2());
		params.put("factoryName2", request.getFactoryName2());
		params.put("officerReceiveFullName2", request.getOfficerReceiveFullName2());
		params.put("officerSendFullName3", request.getOfficerSendFullName3());
		params.put("officerReceiveFullName3", request.getOfficerReceiveFullName3());
		params.put("factoryName3", request.getFactoryName3());
		params.put("doc1Num", request.getDoc1Num());
		params.put("docAcct1Num", request.getDocAcct1Num());
		params.put("docAcct1No", request.getDocAcct1No());
		params.put("docAcct2Num", request.getDocAcct2Num());
		params.put("docAcct2No", request.getDocAcct2No());
		params.put("docOther", request.getDocOther());
		params.put("signOfficerFullName1", request.getSignOfficerFullName1());
		params.put("signOfficerFullName2", request.getSignOfficerFullName2());
		params.put("signWitnessFullName1", request.getSignWitnessFullName1());
		params.put("signWitnessFullName2", request.getSignWitnessFullName2());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_21 + "." + FILE_EXTENSION.JASPER, params);
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
	public TaFormTS0121Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
