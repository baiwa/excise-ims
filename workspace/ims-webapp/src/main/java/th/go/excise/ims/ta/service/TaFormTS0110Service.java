package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@Service
public class TaFormTS0110Service {
	
	public byte[] exportTaFormTS0110(TaFormTS0110Vo request) throws Exception, IOException {
		String reportName = "TA_FORM_TS01_10.jasper";
		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo-garuda.jpg"));
		params.put("testimonyOf", request.getTestimonyOf());
		params.put("testimonyTopic", request.getTestimonyTopic());
		params.put("docDate", request.getDocDate());
		request.setDay(ConvertDateUtils.formatLocalDateToString(request.getDocDate(), ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		request.setMonth(ConvertDateUtils.formatLocalDateToString(request.getDocDate(), ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		request.setYear(ConvertDateUtils.formatLocalDateToString(request.getDocDate(), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("day", request.getDay());
		params.put("month", request.getMonth());
		params.put("year", request.getYear());
		params.put("officerFullName", request.getOfficerFullName());
		params.put("officerPosition", request.getOfficerPosition());
		params.put("testimonyFullName", request.getTestimonyFullName());
		params.put("testimonyAge", request.getTestimonyAge());
		params.put("testimonyNationality", request.getTestimonyNationality());
		params.put("testimonyRace", request.getTestimonyRace());
		params.put("testimonyAddrNo", request.getTestimonyAddrNo());
		params.put("testimonyBuildNameVillage", request.getTestimonyBuildNameVillage());
		params.put("testimonyFloorNo", request.getTestimonyFloorNo());
		params.put("testimonyRoomNo", request.getTestimonyRoomNo());
		params.put("testimonySoiName", request.getTestimonySoiName());
		params.put("testimonyThnName", request.getTestimonyThnName());
		params.put("testimonyTambolName", request.getTestimonyTambolName());
		params.put("testimonyAmphurName", request.getTestimonyAmphurName());
		params.put("testimonyProvinceName", request.getTestimonyProvinceName());
		params.put("testimonyZipCode", request.getTestimonyZipCode());
		params.put("testimonyTelNo", request.getTestimonyTelNo());
		params.put("testimonyCardType", request.getTestimonyCardType());
		params.put("testimonyCardOtherDesc", request.getTestimonyCardOtherDesc());
		params.put("testimonyCardNo", request.getTestimonyCardNo());
		params.put("testimonyCardSource", request.getTestimonyCardSource());
		params.put("testimonyCardCountry", request.getTestimonyCardCountry());
		params.put("testimonyPosition", request.getTestimonyPosition());
		params.put("testimonyFactoryFullName", request.getTestimonyFactoryFullName());
		params.put("newRegId", request.getNewRegId());
		params.put("testimonyText", request.getTestimonyText());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
