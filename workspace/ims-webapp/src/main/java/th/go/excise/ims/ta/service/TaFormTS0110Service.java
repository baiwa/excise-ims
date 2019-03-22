package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Service
public class TaFormTS0110Service {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	public byte[] exportTaFormTS0110(TaFormTS0110Vo formTs) throws IOException, JRException {
		logger.info("export exportTaFormTS0110");
		
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("testimonyOf", formTs.getTestimonyOf());
		params.put("testimonyTopic", formTs.getTestimonyTopic());
		params.put("docDate", formTs.getDocDate());
		params.put("officerFullName", formTs.getOfficerFullName());
		params.put("officerPosition", formTs.getOfficerPosition());
		params.put("testimonyFullName", formTs.getTestimonyFullName());
		params.put("testimonyAge", formTs.getTestimonyAge());
		params.put("testimonyNationality", formTs.getTestimonyNationality());
		params.put("testimonyRace", formTs.getTestimonyRace());
		params.put("testimonyAddrNo", formTs.getTestimonyAddrNo());
		params.put("testimonyBuildNameVillage", formTs.getTestimonyBuildNameVillage());
		params.put("testimonyFloorNo", formTs.getTestimonyFloorNo());
		params.put("testimonyRoomNo", formTs.getTestimonyRoomNo());
		params.put("testimonySoiName", formTs.getTestimonySoiName());
		params.put("testimonyThnName", formTs.getTestimonyThnName());
		params.put("testimonyTambolName", formTs.getTestimonyTambolName());
		params.put("testimonyAmphurName", formTs.getTestimonyAmphurName());
		params.put("testimonyProvinceName", formTs.getTestimonyProvinceName());
		params.put("testimonyZipCode", formTs.getTestimonyZipCode());
		params.put("testimonyTelNo", formTs.getTestimonyTelNo());
		params.put("testimonyCardType", formTs.getTestimonyCardType());
		params.put("testimonyCardOtherDesc", formTs.getTestimonyCardOtherDesc());
		params.put("testimonyCardNo", formTs.getTestimonyCardNo());
		params.put("testimonyCardSource", formTs.getTestimonyCardSource());
		params.put("testimonyCardCountry", formTs.getTestimonyCardCountry());
		params.put("testimonyPosition", formTs.getTestimonyPosition());
		params.put("testimonyFactoryFullName", formTs.getTestimonyFactoryFullName());
		params.put("newRegId", formTs.getNewRegId());
		params.put("testimonyText", formTs.getTestimonyText());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_010 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

}