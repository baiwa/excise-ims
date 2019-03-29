package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0110;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0110Repository;
import th.go.excise.ims.ta.vo.TaFormTS01101ListVo;
import th.go.excise.ims.ta.vo.TaFormTS01101Vo;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Service
public class TaFormTS0110Service extends AbstractTaFormTSService<TaFormTS0110Vo, TaFormTs0110> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0110Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0110Repository taFormTs0110Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_10;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS01101ListVo formTS01101ListVo) throws Exception {
		logger.info("processFormTS");

//		saveFormTS(formTS01101ListVo);
		byte[] reportFile = generateReport(formTS01101ListVo);

		return reportFile;
	}

	protected void saveFormTS(TaFormTS01101ListVo formTS01101ListVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01101ListVo.getFormTS0110().getFormTsNumber());

		TaFormTs0110 formTS0110 = null;
		if (StringUtils.isNotEmpty(formTS01101ListVo.getFormTS0110().getFormTsNumber())) {
			formTS0110 = taFormTs0110Repository.findByFormTsNumber(formTS01101ListVo.getFormTS0110().getFormTsNumber());
			toEntity(formTS0110, formTS01101ListVo.getFormTS0110());
		} else {
			formTS0110 = new TaFormTs0110();
			toEntity(formTS0110, formTS01101ListVo.getFormTS0110());
			formTS0110.setOfficeCode(officeCode);
			formTS0110.setBudgetYear(budgetYear);
			formTS0110.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0110Repository.save(formTS0110);
	}
	
	public byte[] generateReport(TaFormTS01101ListVo formTS01101ListVo) throws Exception {
		logger.info("export exportTaFormTS0110");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("testimonyOf", formTS01101ListVo.getFormTS0110().getTestimonyOf());
		params.put("testimonyTopic", formTS01101ListVo.getFormTS0110().getTestimonyTopic());
		params.put("docDate", formTS01101ListVo.getFormTS0110().getDocDate());
		params.put("officerFullName", formTS01101ListVo.getFormTS0110().getOfficerFullName());
		params.put("officerPosition", formTS01101ListVo.getFormTS0110().getOfficerPosition());
		params.put("testimonyFullName", formTS01101ListVo.getFormTS0110().getTestimonyFullName());
		params.put("testimonyAge", formTS01101ListVo.getFormTS0110().getTestimonyAge());
		params.put("testimonyNationality", formTS01101ListVo.getFormTS0110().getTestimonyNationality());
		params.put("testimonyRace", formTS01101ListVo.getFormTS0110().getTestimonyRace());
		params.put("testimonyAddrNo", formTS01101ListVo.getFormTS0110().getTestimonyAddrNo());
		params.put("testimonyBuildNameVillage", formTS01101ListVo.getFormTS0110().getTestimonyBuildNameVillage());
		params.put("testimonyFloorNo", formTS01101ListVo.getFormTS0110().getTestimonyFloorNo());
		params.put("testimonyRoomNo", formTS01101ListVo.getFormTS0110().getTestimonyRoomNo());
		params.put("testimonySoiName", formTS01101ListVo.getFormTS0110().getTestimonySoiName());
		params.put("testimonyThnName", formTS01101ListVo.getFormTS0110().getTestimonyThnName());
		params.put("testimonyTambolName", formTS01101ListVo.getFormTS0110().getTestimonyTambolName());
		params.put("testimonyAmphurName", formTS01101ListVo.getFormTS0110().getTestimonyAmphurName());
		params.put("testimonyProvinceName", formTS01101ListVo.getFormTS0110().getTestimonyProvinceName());
		params.put("testimonyZipCode", formTS01101ListVo.getFormTS0110().getTestimonyZipCode());
		params.put("testimonyTelNo", formTS01101ListVo.getFormTS0110().getTestimonyTelNo());
		params.put("testimonyCardType", formTS01101ListVo.getFormTS0110().getTestimonyCardType());
		params.put("testimonyCardOtherDesc", formTS01101ListVo.getFormTS0110().getTestimonyCardOtherDesc());
		params.put("testimonyCardNo", formTS01101ListVo.getFormTS0110().getTestimonyCardNo());
		params.put("testimonyCardSource", formTS01101ListVo.getFormTS0110().getTestimonyCardSource());
		params.put("testimonyCardCountry", formTS01101ListVo.getFormTS0110().getTestimonyCardCountry());
		params.put("testimonyPosition", formTS01101ListVo.getFormTS0110().getTestimonyPosition());
		params.put("testimonyFactoryFullName", formTS01101ListVo.getFormTS0110().getTestimonyFactoryFullName());
		params.put("newRegId", formTS01101ListVo.getFormTS0110().getNewRegId());
		params.put("testimonyText", formTS01101ListVo.getFormTS0110().getTestimonyText());

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10 + "." + FILE_EXTENSION.JASPER, params);
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		
		//add report TA_FORM_TS01_10_1
		if (0 < formTS01101ListVo.getFormTS01101List().size()) {
			for (int i = 0; i < formTS01101ListVo.getFormTS01101List().size(); i++) {
				TaFormTS01101Vo form = formTS01101ListVo.getFormTS01101List().get(i);
				Map<String, Object> params2 = new HashMap<>();
				params2.put("testimonyPageNo", form.getTestimonyPageNo());
				params2.put("testimonyOf", formTS01101ListVo.getFormTS01101List().get(0).getTestimonyOf());
				params2.put("testimonyText", form.getTestimonyText());
				JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10_1 + "." + FILE_EXTENSION.JASPER, params2);
				items.add(new SimpleExporterInputItem(jasperPrint2));
			}
		}
		
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();
		
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

	@Override
	public byte[] processFormTS(TaFormTS0110Vo vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFormTS(TaFormTS0110Vo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] generateReport(TaFormTS0110Vo vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}