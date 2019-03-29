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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0102;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0106;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0106Repository;
import th.go.excise.ims.ta.vo.TaFormTS0102Vo;
import th.go.excise.ims.ta.vo.TaFormTS0106Vo;

@Service
public class TaFormTS0106Service extends AbstractTaFormTSService<TaFormTS0106Vo, TaFormTs0106> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0106Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0106Repository taFormTs0106Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_06;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	@Override
	public byte[] processFormTS(TaFormTS0106Vo formTS0106Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0106Vo);
		byte[] reportFile = generateReport(formTS0106Vo);

		return reportFile;
	}

	@Override
	public void saveFormTS(TaFormTS0106Vo formTS0106Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0106Vo.getFormTsNumber());

		// Set Data
		TaFormTs0106 formTs0106 = null;
		if (StringUtils.isNotEmpty(formTS0106Vo.getFormTsNumber())) {
			formTs0106 = taFormTs0106Repository.findByFormTsNumber(formTS0106Vo.getFormTsNumber());
			toEntity(formTs0106, formTS0106Vo);
		} else {
			formTs0106 = new TaFormTs0106();
			toEntity(formTs0106, formTS0106Vo);
			formTs0106.setOfficeCode(officeCode);
			formTs0106.setBudgetYear(budgetYear);
			formTs0106.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0106Repository.save(formTs0106);

	}

	@Override
	public byte[] generateReport(TaFormTS0106Vo formTS0106Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_06 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0106Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0106Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}");

		TaFormTs0106 formTs0106 = taFormTs0106Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0106Vo formTs0106Vo = new TaFormTS0106Vo();
		toVo(formTs0106Vo, formTs0106);

		return formTs0106Vo;
	}

}
