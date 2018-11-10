package th.co.baiwa.excise.report.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.excise.report.bean.ReportJsonBean;

@Service
public class AskForMoneyReportService {

	public byte[] objectToPDF(ReportJsonBean reportJsonBean) throws IOException, JRException {
		
		Gson gson = new Gson();
		Map<String, Object> params = new HashMap<String, Object>();
		params = (Map<String, Object>) gson.fromJson(reportJsonBean.getJson(), params.getClass());
		
		params.put("logo1", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo1.jpg"));
		
		JasperPrint jasperPrint1 = ReportUtils.exportReport("AskForMoney", params, new JREmptyDataSource());
		JasperPrint jasperPrint2 = ReportUtils.exportReport("AskForMoney2", params, new JREmptyDataSource());
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));
		
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
