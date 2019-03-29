package th.go.excise.ims.ta.service;

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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0103;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0103Repository;
import th.go.excise.ims.ta.vo.TaFormTS0103Vo;

@Service
public class TaFormTS0103Service extends AbstractTaFormTSService<TaFormTS0103Vo, TaFormTs0103> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0103Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0103Repository taFormTs0103Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_03;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	@Override
	public byte[] processFormTS(TaFormTS0103Vo formTS0103Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0103Vo);
		byte[] reportFile = generateReport(formTS0103Vo);

		return reportFile;
	}

	@Override
	protected void saveFormTS(TaFormTS0103Vo formTS0103Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0103Vo.getFormTsNumber());

		// Set Data
		TaFormTs0103 formTs0103 = null;
		if (StringUtils.isNotEmpty(formTS0103Vo.getFormTsNumber())) {
			formTs0103 = taFormTs0103Repository.findByFormTsNumber(formTS0103Vo.getFormTsNumber());
			toEntity(formTs0103, formTS0103Vo);
		} else {
			formTs0103 = new TaFormTs0103();
			toEntity(formTs0103, formTS0103Vo);
			formTs0103.setOfficeCode(officeCode);
			formTs0103.setBudgetYear(budgetYear);
			formTs0103.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0103Repository.save(formTs0103);

	}

	@Override
	public byte[] generateReport(TaFormTS0103Vo formTS0103Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("bookNumber1", formTS0103Vo.getBookNumber1());
		params.put("bookNumber2", formTS0103Vo.getBookNumber2());
		

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_03 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0103Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0103Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}");

		TaFormTs0103 formTs0103 = taFormTs0103Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0103Vo formTs0103Vo = new TaFormTS0103Vo();
		toVo(formTs0103Vo, formTs0103);

		return formTs0103Vo;
	}

}
