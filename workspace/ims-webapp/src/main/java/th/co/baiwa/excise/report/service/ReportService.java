package th.co.baiwa.excise.report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.co.baiwa.excise.report.bean.ExampleBean;
import th.co.baiwa.excise.report.controller.ReportController;
import th.co.baiwa.excise.report.bean.ContractBean;

@Service
public class ReportService {

	private Logger logger = LoggerFactory.getLogger(ReportService.class);

	public byte[] exampleToPDF() throws IOException, JRException {
		String reportName = "Example";

		Map<String, Object> params = new HashMap<>();
		params.put("sendTo", "ผมชื่อ เฟรม แฟน น้องมุ๊ก ข้อความทดสอบ");
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "garuda-emblem.jpg"));
		List<ExampleBean> exampleList = new ArrayList<>();
		ExampleBean exBean = null;
		for (int i = 0; i < 29; i++) {
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
		IOUtils.write(reportFile, new FileOutputStream(new File("c:/out/" + "Example.pdf"))); // /tmp/excise/ims/report/

		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	public byte[] contractToPDF(String name, ContractBean contract) throws IOException, JRException {
		String reportName = name != null ? name : "Example";

		DecimalFormat formatter = new DecimalFormat("#,###.00");
		
		contract.setSumCostTxt(ThaiNumberUtils.toThaiBaht(contract.getSumCost().toString()));
		Map<String, Object> params = new HashMap<>();
		params.put("day", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getDay().doubleValue())));
		params.put("allowanceCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getAllowanceCost().doubleValue())));
		params.put("rentCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getRentCost().doubleValue())));
		params.put("travelCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getTravelCost().doubleValue())));
		params.put("otherCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getOtherCost().doubleValue())));
		params.put("sumCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getSumCost().doubleValue())));
		
		params.put("sumCostTxt", contract.getSumCostTxt());
		params.put("numberId", contract.getNumberId());
		params.put("loanName", contract.getLoanName());
		params.put("loanFrom", contract.getLoanFrom());
		params.put("dateFixed", contract.getDateFixed());
		params.put("locateAt", contract.getLocateAt());
		params.put("positionName", contract.getPositionName());
		params.put("presentTo", contract.getPresentTo());
		params.put("reasonTxt", contract.getReasonTxt());
		params.put("sendTo", contract.getSendTo());

//		List<ContractBean> conList = new ArrayList<>();
//		contract.setDateFixed(new Date());
//		conList.add(contract);

		JasperPrint jasperPrint = ReportUtils.exportReport(reportName, params, new JREmptyDataSource()); // JRBeanCollectionDataSource(exampleList)
		// JasperPrint jasperPrint = ReportUtils.exportReport(jasperFile, params, new
		// JREmptyDataSource());

		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		IOUtils.write(reportFile, new FileOutputStream(new File("c:/out/" + contract.getNumberId() + ".pdf"))); // /tmp/excise/ims/report/

		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}
}
