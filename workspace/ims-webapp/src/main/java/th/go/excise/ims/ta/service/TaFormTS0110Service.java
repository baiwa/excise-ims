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
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
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

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0110Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0110Repository taFormTs0110Repository;

	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_10;
	}

	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0110Vo);
		byte[] reportFile = generateReport(formTS0110Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS0110Vo formTS0110Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0110Vo.getFormTsNumber());

		TaFormTs0110 taFormTs0110Dtl = null;
		TaFormTs0110 taFormTs0110Hdr = null;
		if (StringUtils.isNotEmpty(formTS0110Vo.getFormTsNumber())) {

			taFormTs0110Repository.setIsDeleteY(officeCode, budgetYear, formTS0110Vo.getFormTsNumber());

			for (TaFormTS0110Vo formDtl : formTS0110Vo.getTaFormTS0110VoList()) {
				TaFormTs0110 dtlVo = taFormTs0110Repository
						.findByFormTs0110IdAndIsDeleted(Long.valueOf(formDtl.getFormTs0110Id()), FLAG.Y_FLAG);
				if (dtlVo != null) {
					taFormTs0110Dtl = dtlVo;
					toEntity(taFormTs0110Dtl, formDtl);
					taFormTs0110Dtl.setIsDeleted(FLAG.N_FLAG);
				} else {
					taFormTs0110Dtl = new TaFormTs0110();
					toEntity(taFormTs0110Dtl, formDtl);
					taFormTs0110Dtl.setOfficeCode(officeCode);
					taFormTs0110Dtl.setBudgetYear(budgetYear);
				}

				taFormTs0110Repository.save(taFormTs0110Dtl);
			}
		} else {
			taFormTs0110Hdr = new TaFormTs0110();
			toEntity(taFormTs0110Hdr, formTS0110Vo);
			taFormTs0110Hdr.setOfficeCode(officeCode);
			taFormTs0110Hdr.setBudgetYear(budgetYear);
			taFormTs0110Hdr.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
			taFormTs0110Repository.save(taFormTs0110Hdr);
			
			for (TaFormTS0110Vo formDtl : formTS0110Vo.getTaFormTS0110VoList()) {
				taFormTs0110Dtl = new TaFormTs0110();
				toEntity(taFormTs0110Dtl, formDtl);
				taFormTs0110Dtl.setOfficeCode(officeCode);
				taFormTs0110Dtl.setBudgetYear(budgetYear);
				taFormTs0110Dtl.setFormTsNumber(taFormTs0110Hdr.getFormTsNumber());
				taFormTs0110Repository.save(taFormTs0110Dtl);
			}
		}

	}

	public byte[] generateReport(TaFormTS0110Vo formTS0110Vo) throws Exception {
		logger.info("export exportTaFormTS0110");

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		JasperPrint jasperPrint = null;

		Map<String, Object> params = new HashMap<>();
		params.put("logo",
				ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
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
		jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10 + "." + FILE_EXTENSION.JASPER, params);
		items.add(new SimpleExporterInputItem(jasperPrint));

		// add report TA_FORM_TS01_10_1
		if (formTS0110Vo.getTaFormTS0110VoList() != null) {
			Map<String, Object> subParams = null;
			for (TaFormTS0110Vo subFormTS0110Vo : formTS0110Vo.getTaFormTS0110VoList()) {
				subParams = new HashMap<>();
				subParams.put("testimonyPageNo", subFormTS0110Vo.getTestimonyPageNo());
				subParams.put("testimonyOf", formTS0110Vo.getTestimonyOf());
				subParams.put("testimonyText", subFormTS0110Vo.getTestimonyText());
				jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_10_1 + "." + FILE_EXTENSION.JASPER,
						subParams);
				items.add(new SimpleExporterInputItem(jasperPrint));
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
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		return taFormTs0110Repository.findFormTsNumberByOfficeCode(officeCode);
	}

	@Override
	public TaFormTS0110Vo getFormTS(String formTsNumber) {
		logger.info("getFormTS formTsNumber={}");
		TaFormTS0110Vo taFormTS0110Vo = new TaFormTS0110Vo();

		// Set Data
		List<TaFormTs0110> dtls = taFormTs0110Repository.findByFormTsNumber(formTsNumber);
        List<TaFormTS0110Vo> dtlVos = new ArrayList<>();
        for (TaFormTs0110 dtl : dtls) {
            TaFormTS0110Vo dtlVo = new TaFormTS0110Vo();
            toVo(dtlVo, dtl);
            dtlVos.add(dtlVo);
        }
        taFormTS0110Vo.setTaFormTS0110VoList(dtlVos);
		return taFormTS0110Vo;
	}

}