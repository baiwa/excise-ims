package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
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
		// format string to LocalDate
//		Date localDate = ConvertDateUtils.parseStringToDate(request.getDocDate(), ConvertDateUtils.YYYYMMDD,
//				ConvertDateUtils.LOCAL_EN);
//		params.put("docDate", localDate);
//		request.setDay(ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
//		request.setMonth(
//				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
//		request.setYear(
//				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	public byte[] objectToPDF(TaFormTS0110Vo reportJsonBean) throws IOException, JRException {
		String reportName = "TA_FORM_TS01_10.jasper";
		
//		GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        TaFormTS0110Vo obj = gson.fromJson(reportJsonBean.getJson(), TaFormTS0110Vo.class);
        
        Gson gson = new Gson();
		Map<String, Object> params = new HashMap<String, Object>();
		params = (Map<String, Object>) gson.fromJson(reportJsonBean.getJson(), params.getClass());
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo-garuda.jpg"));
		
		// format string to LocalDate
		Date localDate = ConvertDateUtils.parseStringToDate(reportJsonBean.getDocDate(), ConvertDateUtils.YYYYMMDD,
				ConvertDateUtils.LOCAL_EN);
		params.put("day", ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		params.put("month", 
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		params.put("year", 
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH));

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params, new JREmptyDataSource());

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint));

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();

		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
