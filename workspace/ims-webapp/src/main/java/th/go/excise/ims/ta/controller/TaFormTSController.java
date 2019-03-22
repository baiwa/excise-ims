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
import th.go.excise.ims.ta.service.TaFormTS0110Service;

@Controller
@RequestMapping("/api/ta/report")
public class TaFormTSController {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTSController.class);

	@Autowired
	private TaFormTS0110Service formTS0110Service;

	// TODO TaFormTS0110
	@PostMapping("/pdf/ta-form-ts0110")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response)
			throws IOException, JRException {
		logger.info("export TaFormTS01_10.pdf");
		
		byte[] bytes = formTS0110Service.exportTaFormTS0110(reportJsonBean);

//		response.setContentType("application/pdf");
//		response.addHeader("Content-Disposition", "inline;filename=TaFormTS01_10.pdf");

//		IOUtils.write(reportFile, new FileOutputStream(new File("D:/tmp/" + "TaFormTS01_10.pdf")));
//		IOUtils.write(reportFile, response.getOutputStream());

		String filename = String.format("TaFormTS01_10.pdf",
				DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
}
