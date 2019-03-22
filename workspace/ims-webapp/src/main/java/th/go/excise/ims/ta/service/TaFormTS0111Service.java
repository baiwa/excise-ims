package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;

@Service
public class TaFormTS0111Service {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	public byte[] exportTaFormTS0111(ReportJsonBean reportJsonBean) throws IOException, JRException {
		logger.info("export TA_FORM_TS01_11");

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TaFormTS0111Vo formTs = gson.fromJson(reportJsonBean.getJson(), TaFormTS0111Vo.class);

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE+"."+FILE_EXTENSION.JPG));
		params.put("docPlace", formTs.getDocPlace());
		params.put("docDate", formTs.getDocDate());
		params.put("docTime", formTs.getDocTime());
		params.put("officerFullName", formTs.getOfficerFullName());
		params.put("officerPosition", formTs.getOfficerPosition());
		params.put("factoryName", formTs.getFactoryName());
		params.put("newRegId", formTs.getNewRegId());
		params.put("factoryType", formTs.getFactoryType());
		params.put("facAddrNo", formTs.getFacAddrNo());
		params.put("facMooNo", formTs.getFacMooNo());
		params.put("facSoiName", formTs.getFacSoiName());
		params.put("facThnName", formTs.getFacThnName());
		params.put("facTambolName", formTs.getFacTambolName());
		params.put("facAmphurName", formTs.getFacAmphurName());
		params.put("facProvinceName", formTs.getFacProvinceName());
		params.put("facZipCode", formTs.getFacZipCode());
		params.put("deliverFullName", formTs.getDeliverFullName());
		params.put("deliverPosition", formTs.getDeliverPosition());
		params.put("deliverOther", formTs.getDeliverOther());
		params.put("refBookNumber1", formTs.getRefBookNumber1());
		params.put("refBookNumber2", formTs.getRefBookNumber2());
		params.put("refDocDate", formTs.getRefDocDate());
		params.put("taFormTS0111DtlVoList", formTs.getTaFormTS0111DtlVoList());
		params.put("signAuthFullName1", formTs.getSignAuthFullName1());
		params.put("signWitnessFullName1", formTs.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTs.getSignWitnessFullName2());
		// format string to LocalDate
		Date localDate = ConvertDateUtils.parseStringToDate(formTs.getDocDate(), ConvertDateUtils.YYYYMMDD,
				ConvertDateUtils.LOCAL_EN);
		params.put("day",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		params.put("month",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		params.put("year",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY));

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_011 + "." + FILE_EXTENSION.JASPER,
				params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
	
	public byte[] exportTaFormTS01112(ReportJsonBean reportJsonBean) throws IOException, JRException {
		logger.info("export TA_FORM_TS01_11-2");

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TaFormTS0111Vo formTs = gson.fromJson(reportJsonBean.getJson(), TaFormTS0111Vo.class);

		Map<String, Object> params = new HashMap<>();
		params.put("authFullName1", formTs.getAuthFullName1());
		params.put("signAuthFullName2", formTs.getSignAuthFullName2());
		params.put("signWitnessFullName3", formTs.getSignWitnessFullName3());
		params.put("signWitnessFullName4", formTs.getSignWitnessFullName4());
		params.put("authFullName2", formTs.getAuthFullName2());
		params.put("authPosition", formTs.getAuthPosition());
		params.put("authPositionOther", formTs.getAuthPositionOther());
		params.put("authFrom", formTs.getAuthFrom());
		params.put("signAuthFullName3", formTs.getSignAuthFullName3());
		params.put("signAuthFullName4", formTs.getSignAuthFullName4());
		params.put("signWitnessFullName5", formTs.getSignWitnessFullName5());
		params.put("signWitnessFullName6", formTs.getSignWitnessFullName6());
		// format string to LocalDate
		Date localDate = ConvertDateUtils.parseStringToDate(formTs.getAuthDate(), ConvertDateUtils.YYYYMMDD,
				ConvertDateUtils.LOCAL_EN);
		params.put("day",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		params.put("month",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		params.put("year",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY));

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_011_2 + "." + FILE_EXTENSION.JASPER,
				params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
