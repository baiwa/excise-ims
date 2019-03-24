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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0110;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0110Repository;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Service
public class TaFormTS0110Service extends AbstractTaFormTSService<TaFormTS0110Vo, TaFormTs0110> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0110Repository taFormTs0110Repository;
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0110Vo);
		byte[] reportFile = generateReport(formTS0110Vo);

		return reportFile;
	}
	
	protected void saveFormTS(TaFormTS0110Vo formTS0110Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0110Vo.getFormTsNumber());

		TaFormTs0110 formTS0110 = null;
		if (StringUtils.isNotEmpty(formTS0110Vo.getFormTsNumber())) {
			formTS0110 = taFormTs0110Repository.findByFormTsNumber(formTS0110Vo.getFormTsNumber());
			toEntity(formTS0110, formTS0110Vo);
		} else {
			formTS0110 = new TaFormTs0110();
			toEntity(formTS0110, formTS0110Vo);
			formTS0110.setOfficeCode(officeCode);
			formTS0110.setBudgetYear(budgetYear);
			formTS0110.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0110Repository.save(formTS0110);
	}
	
	public byte[] generateReport(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("export exportTaFormTS0110");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("testimonyOf", formTS0110Vo.getTestimonyOf());
		params.put("testimonyTopic", formTS0110Vo.getTestimonyTopic());
		params.put("docDate", formTS0110Vo.getDocDate());
		params.put("officerFullName", formTS0110Vo.getOfficerFullName());
		params.put("officerPosition", formTS0110Vo.getOfficerPosition());
		params.put("testimonyFullName", formTS0110Vo.getTestimonyFullName());
		params.put("testimonyAge", formTS0110Vo.getTestimonyAge());
		params.put("testimonyNationality", formTS0110Vo.getTestimonyNationality());
		params.put("testimonyRace", formTS0110Vo.getTestimonyRace());
		params.put("testimonyAddrNo", formTS0110Vo.getTestimonyAddrNo());
		params.put("testimonyBuildNameVillage", formTS0110Vo.getTestimonyBuildNameVillage());
		params.put("testimonyFloorNo", formTS0110Vo.getTestimonyFloorNo());
		params.put("testimonyRoomNo", formTS0110Vo.getTestimonyRoomNo());
		params.put("testimonySoiName", formTS0110Vo.getTestimonySoiName());
		params.put("testimonyThnName", formTS0110Vo.getTestimonyThnName());
		params.put("testimonyTambolName", formTS0110Vo.getTestimonyTambolName());
		params.put("testimonyAmphurName", formTS0110Vo.getTestimonyAmphurName());
		params.put("testimonyProvinceName", formTS0110Vo.getTestimonyProvinceName());
		params.put("testimonyZipCode", formTS0110Vo.getTestimonyZipCode());
		params.put("testimonyTelNo", formTS0110Vo.getTestimonyTelNo());
		params.put("testimonyCardType", formTS0110Vo.getTestimonyCardType());
		params.put("testimonyCardOtherDesc", formTS0110Vo.getTestimonyCardOtherDesc());
		params.put("testimonyCardNo", formTS0110Vo.getTestimonyCardNo());
		params.put("testimonyCardSource", formTS0110Vo.getTestimonyCardSource());
		params.put("testimonyCardCountry", formTS0110Vo.getTestimonyCardCountry());
		params.put("testimonyPosition", formTS0110Vo.getTestimonyPosition());
		params.put("testimonyFactoryFullName", formTS0110Vo.getTestimonyFactoryFullName());
		params.put("newRegId", formTS0110Vo.getNewRegId());
		params.put("testimonyText", formTS0110Vo.getTestimonyText());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10 + "." + FILE_EXTENSION.JASPER, params);
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
	public TaFormTS0110Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}