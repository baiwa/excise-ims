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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0110;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0110Repository;
import th.go.excise.ims.ta.vo.TaFormTS01101Vo;

@Service
public class TaFormTS01101Service extends AbstractTaFormTSService<TaFormTS01101Vo, TaFormTs0110> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS01101Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0110Repository taFormTs0110Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_10_1;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS01101Vo formTS01101Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS01101Vo);
		byte[] reportFile = generateReport(formTS01101Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS01101Vo formTS01101Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01101Vo.getFormTsNumber());

		TaFormTs0110 formTS0110 = null;
		if (StringUtils.isNotEmpty(formTS01101Vo.getFormTsNumber())) {
			formTS0110 = taFormTs0110Repository.findByFormTsNumber(formTS01101Vo.getFormTsNumber());
			toEntity(formTS0110, formTS01101Vo);
		} else {
			formTS0110 = new TaFormTs0110();
			toEntity(formTS0110, formTS01101Vo);
			formTS0110.setOfficeCode(officeCode);
			formTS0110.setBudgetYear(budgetYear);
			formTS0110.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0110Repository.save(formTS0110);
	}
	
	public byte[] generateReport(TaFormTS01101Vo formTS01101Vo) throws Exception, IOException {
		logger.info("export exportTaFormTS0110");
		
		// Set Data
		Map<String, Object> params = new HashMap<>();
		params.put("testimonyPageNo", formTS01101Vo.getTestimonyPageNo());
		params.put("testimonyOf", formTS01101Vo.getTestimonyOf());
		params.put("testimonyText", formTS01101Vo.getTestimonyText());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10_1 + "." + FILE_EXTENSION.JASPER, params);
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
	public TaFormTS01101Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
