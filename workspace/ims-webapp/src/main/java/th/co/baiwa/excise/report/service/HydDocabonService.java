package th.co.baiwa.excise.report.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.excise.report.bean.ReportJsonBean;

@Service
public class HydDocabonService {

	public byte[] objectToPDF(ReportJsonBean reportJsonBean) throws IOException, JRException {

		Map<String, Object> params = new HashMap<String, Object>();
		
		JasperPrint jasperPrint1 = ReportUtils.exportReport("Report_01", params, new JREmptyDataSource());
		JasperPrint jasperPrint2 = ReportUtils.exportReport("Report_05", params, new JREmptyDataSource());
		JasperPrint jasperPrint3 = ReportUtils.exportReport("Report_03", params, new JREmptyDataSource());
		JasperPrint jasperPrint8 = ReportUtils.exportReport("Report_07", params, new JREmptyDataSource());
		JasperPrint jasperPrint4 = ReportUtils.exportReport("Report_04", params, new JREmptyDataSource());
		JasperPrint jasperPrint5 = ReportUtils.exportReport("Report_02", params, new JREmptyDataSource());
		JasperPrint jasperPrint6 = ReportUtils.exportReport("Solvent-01", params, new JREmptyDataSource());
		JasperPrint jasperPrint7 = ReportUtils.exportReport("Solvent-02", params, new JREmptyDataSource());
		
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));
		items.add(new SimpleExporterInputItem(jasperPrint3));
		items.add(new SimpleExporterInputItem(jasperPrint4));
		items.add(new SimpleExporterInputItem(jasperPrint5));
		items.add(new SimpleExporterInputItem(jasperPrint6));
		items.add(new SimpleExporterInputItem(jasperPrint7));		
		items.add(new SimpleExporterInputItem(jasperPrint8));		
		
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();

		return content;
	}
}
