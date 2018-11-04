package th.co.baiwa.excise.report.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.excise.report.bean.ExampleBean;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class ReportService {

	@Value("${app.datasource.path.report}")
	private String PATH_EXPORT;

	private Logger logger = LoggerFactory.getLogger(ReportService.class);

	public void initialService() {
		File f = new File(PATH_EXPORT); // initial file (folder)
		if (!f.exists()) { // check folder exists
			if (f.mkdirs()) {
				logger.info("Directory is created!");
			} else {
				logger.error("Failed to create directory!");
			}
		}
	}

	public byte[] exampleToPDF(Integer num) throws IOException, JRException {
		// Folder Exist ??
		initialService();

		String reportName = "Example";

		Map<String, Object> params = new HashMap<>();
		params.put("sendTo", "ผมชื่อ เฟรม แฟน น้องมุ๊ก ข้อความทดสอบ");
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "garuda-emblem.jpg"));
		List<ExampleBean> exampleList = new ArrayList<>();
		ExampleBean exBean = null;
		for (int i = 0; i < num; i++) {
			exBean = new ExampleBean();
			exBean.setData1(String.valueOf(i + 1));
			exBean.setData2(new BigDecimal(i + 1));
			exBean.setData3(new Date());
			exampleList.add(exBean);
		}

		JasperPrint jasperPrint = ReportUtils.exportReport(reportName, params,
				new JRBeanCollectionDataSource(exampleList)); // JRBeanCollectionDataSource(exampleList)
		// JasperPrint jasperPrint = ReportUtils.exportReport(jasperFile, params, new
		// JREmptyDataSource());

		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH_EXPORT + "Example.pdf"))); // /tmp/excise/ims/report/

		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	public byte[] objectToPDF(String name, String param) throws IOException, JRException {
		// Folder Exist ??
		initialService();

		Gson gson = new Gson();
		Map<String, Object> params = new HashMap<String, Object>();
		params = (Map<String, Object>) gson.fromJson(param, params.getClass());
		List<Object> bean = new ArrayList<Object>();
		if (BeanUtils.isNotEmpty(params.get("Bean"))) {
			bean = (List<Object>) params.get("Bean");
		}

		JRDataSource dataSource = null;

		if (BeanUtils.isNotEmpty(params.get("logo"))) {
			String logo = params.get("logo").toString();
			params.remove("logo");
			params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, logo));
		}
		params.put("logo1", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo1.jpg"));

		if (BeanUtils.isEmpty(bean)) {
			dataSource = new JREmptyDataSource();
		} else {
			dataSource = new JRBeanCollectionDataSource(bean);
		}

		JasperPrint jasperPrint = ReportUtils.exportReport(name, params, dataSource); // JRBeanCollectionDataSource(exampleList)
		// JasperPrint jasperPrint = ReportUtils.exportReport(jasperFile, params, new
		// JREmptyDataSource());

		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH_EXPORT + name + ".pdf"))); // /tmp/excise/ims/report/

		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	public byte[] objectToPDF(String name, Map<String, Object> params, List<Object> bean)
			throws IOException, JRException {
		// Folder Exist ??
		initialService();
		
		JRDataSource dataSource = null;

		if (BeanUtils.isNotEmpty(params.get("logo"))) {
			String logo = params.get("logo").toString();
			params.remove("logo");
			params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, logo));
		}
		params.put("logo1", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo1.jpg"));

		if (BeanUtils.isEmpty(bean)) {
			dataSource = new JREmptyDataSource();
		} else {
			dataSource = new JRBeanCollectionDataSource(bean);
		}

		JasperPrint jasperPrint = ReportUtils.exportReport(name, params, dataSource); // JRBeanCollectionDataSource(exampleList)
		// JasperPrint jasperPrint = ReportUtils.exportReport(jasperFile, params, new
		// JREmptyDataSource());

		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH_EXPORT + name + ".pdf"))); // /tmp/excise/ims/report/

		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	public void viewPdf(String name, HttpServletResponse response) throws Exception {
		File file = new File(PATH_EXPORT + name + ".pdf");
		byte[] reportFile = IOUtils.toByteArray(new FileInputStream(file)); // null

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=" + name + ".pdf");
		response.setContentLength(reportFile.length);

		OutputStream responseOutputStream = response.getOutputStream();
		for (byte bytes : reportFile) {
			responseOutputStream.write(bytes);
		}
	}
}
