package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0112;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0112Repository;
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;

@Service
public class TaFormTS0112Service extends AbstractTaFormTSService<TaFormTS0112Vo, TaFormTs0112> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0112Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0112Repository taFormTs0112Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_12;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0112Vo TaFormTS0112Vo) throws Exception {
		logger.info("processFormTS");

		//saveFormTS(TaFormTS0112Vo);
		byte[] reportFile = generateReport(TaFormTS0112Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS0112Vo formTS0112Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0112Vo.getFormTsNumber());

		/*TaFormTs0113 formTS0113 = null;
		if (StringUtils.isNotEmpty(formTS0113Vo.getFormTsNumber())) {
			formTS0113 = taFormTs0113Repository.findByFormTsNumber(formTS0113Vo.getFormTsNumber());
			toEntity(formTS0113, formTS0113Vo);
		} else {
			formTS0113 = new TaFormTs0113();
			toEntity(formTS0113, formTS0113Vo);
			formTS0113.setOfficeCode(officeCode);
			formTS0113.setBudgetYear(budgetYear);
			formTS0113.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0113Repository.save(formTS0113);*/
	}

	public byte[] generateReport(TaFormTS0112Vo formTS0112Vo) throws Exception, IOException {
		logger.info("generateReport");
		
		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("docPlace",formTS0112Vo.getDocPlace());
		params.put("docDate",formTS0112Vo.getDocDate());
		params.put("headOfficerFullName",formTS0112Vo.getHeadOfficerFullName());
		params.put("headOfficerPosition",formTS0112Vo.getHeadOfficerPosition());
		params.put("headOfficerOfficeName",formTS0112Vo.getHeadOfficerOfficeName());
		params.put("officerFullName1",formTS0112Vo.getOfficerFullName1());
		params.put("officerPosition1",formTS0112Vo.getOfficerPosition1());
		params.put("officerFullName2",formTS0112Vo.getOfficerFullName2());
		params.put("officerPosition2",formTS0112Vo.getOfficerPosition2());
		params.put("officerFullName3",formTS0112Vo.getOfficerFullName3());
		params.put("officerPosition3",formTS0112Vo.getOfficerPosition3());
		params.put("officerFullName4",formTS0112Vo.getOfficerFullName4());
		params.put("officerPosition4",formTS0112Vo.getOfficerPosition4());
		params.put("officerFullName5",formTS0112Vo.getOfficerFullName5());
		params.put("officerPosition5",formTS0112Vo.getOfficerPosition5());
		params.put("factoryName",formTS0112Vo.getFactoryName());
		params.put("newRegId",formTS0112Vo.getNewRegId());
		params.put("facAddrNo",formTS0112Vo.getFacAddrNo());
		params.put("facSoiName",formTS0112Vo.getFacSoiName());
		params.put("facThnName",formTS0112Vo.getFacThnName());
		params.put("facTambolName",formTS0112Vo.getFacTambolName());
		params.put("facAmphurName",formTS0112Vo.getFacAmphurName());
		params.put("facProvinceName",formTS0112Vo.getFacProvinceName());
		params.put("facZipCode",formTS0112Vo.getFacZipCode());
		params.put("ownerFullName1",formTS0112Vo.getOwnerFullName1());
		params.put("ownerPosition",formTS0112Vo.getOwnerPosition());
		params.put("ownerOther",formTS0112Vo.getOwnerOther());
		params.put("lawGroup",formTS0112Vo.getLawGroup());
		params.put("seizeDesc",formTS0112Vo.getSeizeDesc());
		params.put("contactDesc",formTS0112Vo.getContactDesc());
		params.put("ownerFullName2",formTS0112Vo.getOwnerFullName2());
		params.put("ownerPosition2",formTS0112Vo.getOwnerPosition2());
		params.put("ownerOther2",formTS0112Vo.getOwnerOther2());
		params.put("signAuthFullName",formTS0112Vo.getSignAuthFullName());
		params.put("signInspectorFullName",formTS0112Vo.getSignInspectorFullName());
		params.put("signWitnessFullName1",formTS0112Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2",formTS0112Vo.getSignWitnessFullName2());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_12 + "." + FILE_EXTENSION.JASPER, params);
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
	public TaFormTS0112Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
