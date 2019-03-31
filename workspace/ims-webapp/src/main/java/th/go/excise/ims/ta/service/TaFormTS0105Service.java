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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0105;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0105Repository;
import th.go.excise.ims.ta.vo.TaFormTS0105Vo;

@Service
public class TaFormTS0105Service extends AbstractTaFormTSService<TaFormTS0105Vo, TaFormTs0105> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0105Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0105Repository taFormTs0105Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_05;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	@Override
	public byte[] processFormTS(TaFormTS0105Vo formTS0105Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0105Vo);
		byte[] reportFile = generateReport(formTS0105Vo);

		return reportFile;
	}

	@Override
	public void saveFormTS(TaFormTS0105Vo formTS0105Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0105Vo.getFormTsNumber());

		// Set Data
		TaFormTs0105 formTs0105 = null;
		if (StringUtils.isNotEmpty(formTS0105Vo.getFormTsNumber())) {
			formTs0105 = taFormTs0105Repository.findByFormTsNumber(formTS0105Vo.getFormTsNumber());
			toEntity(formTs0105, formTS0105Vo);
		} else {
			formTs0105 = new TaFormTs0105();
			toEntity(formTs0105, formTS0105Vo);
			formTs0105.setOfficeCode(officeCode);
			formTs0105.setBudgetYear(budgetYear);
			formTs0105.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0105Repository.save(formTs0105);

	}

	@Override
	public byte[] generateReport(TaFormTS0105Vo formTS0105Vo) throws Exception {
		logger.info("generateReport");

		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("BookNumber1",formTS0105Vo.getBookNumber1());
		params.put("BookNumber2",formTS0105Vo.getBookNumber2());
		params.put("OfficeName",formTS0105Vo.getOfficeName());
		params.put("DocDate",formTS0105Vo.getDocDate());
		params.put("DocDear",formTS0105Vo.getDocDear());
		params.put("RefBookNumber1",formTS0105Vo.getRefBookNumber1());
		params.put("RefBookNumber2",formTS0105Vo.getRefBookNumber2());
		params.put("RefDocDate",formTS0105Vo.getRefDocDate());
		params.put("RefDocSend",formTS0105Vo.getRefDocSend());
		params.put("CaseDate",formTS0105Vo.getCaseDate());
		params.put("CaseTime",formTS0105Vo.getCaseTime());
		params.put("DestText",formTS0105Vo.getDestText());
		params.put("DestDate",formTS0105Vo.getDestDate());
		params.put("DestTime",formTS0105Vo.getDestTime());
		params.put("SignOfficerFullName",formTS0105Vo.getSignOfficerFullName());
		params.put("SignOfficerPosition",formTS0105Vo.getSignOfficerPosition());
		params.put("OtherText",formTS0105Vo.getOtherText());
		params.put("OtherPhone",formTS0105Vo.getOtherPhone());





		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_05 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0105Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0105Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}");

		TaFormTs0105 formTs0105 = taFormTs0105Repository.findByFormTsNumber(formTsNumber);

		// Set Data
		TaFormTS0105Vo formTs0105Vo = new TaFormTS0105Vo();
		toVo(formTs0105Vo, formTs0105);

		return formTs0105Vo;
	}

}
