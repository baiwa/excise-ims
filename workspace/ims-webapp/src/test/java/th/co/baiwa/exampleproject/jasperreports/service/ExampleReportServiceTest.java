package th.co.baiwa.exampleproject.jasperreports.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.exampleproject.jasperreports.bean.ExampleBean;

public class ExampleReportServiceTest {
	
	@Test
	public void test_exportPdf() throws Exception {
		
		String reportName = "Example";
		
		Map<String, Object> params = new HashMap<>();
		params.put("sendTo", "ผมชื่อ เฟรม แฟน น้องมุ๊ก ข้อความทดสอบ");
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "garuda-emblem.jpg"));
		
		List<ExampleBean> exampleList = new ArrayList<>();
		ExampleBean exBean = null;
		for (int i = 0; i < 10; i++) {
			exBean = new ExampleBean();
			exBean.setData1(String.valueOf(i + 1));
			exBean.setData2(new BigDecimal(i + 1));
			exBean.setData3(new Date());
			exampleList.add(exBean);
		}
		
		JasperPrint jasperPrint = ReportUtils.exportReport(reportName, params, new JRBeanCollectionDataSource(exampleList));
//		JasperPrint jasperPrint = ReportUtils.exportReport(jasperFile, params, new JREmptyDataSource());
		
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		IOUtils.write(reportFile, new FileOutputStream(new File("/tmp/excise/ims/report/" + "Example.pdf")));
		
		ReportUtils.closeResourceFileInputStream(params);
	}
	
}
