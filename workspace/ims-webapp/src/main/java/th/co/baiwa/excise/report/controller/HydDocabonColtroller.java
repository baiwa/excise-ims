package th.co.baiwa.excise.report.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.excise.report.bean.ReportJsonBean;
import th.co.baiwa.excise.report.service.HydDocabonService;


@Controller
@RequestMapping("api/hyddocabon")
public class HydDocabonColtroller {

	@Autowired
	private HydDocabonService hydDocabonService;

	@PostMapping("/pdf/oa/hydDocabonService")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response)
			throws IOException, JRException {
		byte[] reportFile = hydDocabonService.objectToPDF(reportJsonBean);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=hydDocabonService.pdf");

		IOUtils.write(reportFile, response.getOutputStream());
	}
}
