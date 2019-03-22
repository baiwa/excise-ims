package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.service.TaFormTS0107Service;
import th.go.excise.ims.ta.service.TaFormTS0110Service;

@Controller
@RequestMapping("/api/ta/report")
public class TaFormTSController {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTSController.class);

	private TaFormTS0110Service formTS0110Service;
	private TaFormTS0107Service formTS0107Service;

	@Autowired
	public TaFormTSController(TaFormTS0110Service formTS0110Service, TaFormTS0107Service formTS0107Service) {

		this.formTS0110Service = formTS0110Service;
		this.formTS0107Service = formTS0107Service;

	}

	// TODO TaFormTS0110
	@PostMapping("/pdf/ta-form-ts0110")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		logger.info("export TaFormTS01_10.pdf");

		byte[] bytes = formTS0110Service.exportTaFormTS0110(reportJsonBean);

//		response.setContentType("application/pdf");
//		response.addHeader("Content-Disposition", "inline;filename=TaFormTS01_10.pdf");

//		IOUtils.write(reportFile, new FileOutputStream(new File("D:/tmp/" + "TaFormTS01_10.pdf")));
//		IOUtils.write(reportFile, response.getOutputStream());

		String filename = String.format("TaFormTS01_10.pdf", DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}

	// TODO TaFormTS0107
	@PostMapping("/pdf/ta-form-ts0107")
	public void pdfTs0107(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("export" + REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.PDF);

		byte[] bytes = formTS0107Service.exportTaFormTS0107(reportJsonBean);
 
		String filename = String.format(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
}
