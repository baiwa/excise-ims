package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.rest.adapter.DateThaiJsonDeserializer;
import th.go.excise.ims.ta.service.TaFormTS0107Service;
import th.go.excise.ims.ta.service.TaFormTS0110Service;
import th.go.excise.ims.ta.service.TaFormTS0113Service;
import th.go.excise.ims.ta.service.TaFormTS0119Service;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;

@Controller
@RequestMapping("/api/ta/report")
public class TaFormTSController {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTSController.class);

	private Gson gson = new GsonBuilder()
		.registerTypeAdapter(Date.class, DateThaiJsonDeserializer.getInstance())
		.create();

	private TaFormTS0107Service taFormTS0107Service;
	private TaFormTS0110Service taFormTS0110Service;
	private TaFormTS0113Service taFormTS0113Service;
	private TaFormTS0119Service taFormTS0119Service;

	@Autowired
	public TaFormTSController(
			TaFormTS0107Service taFormTS0107Service,
			TaFormTS0110Service taFormTS0110Service,
			TaFormTS0113Service taFormTS0113Service,
			TaFormTS0119Service taFormTS0119Service) {
		this.taFormTS0107Service = taFormTS0107Service;
		this.taFormTS0110Service = taFormTS0110Service;
		this.taFormTS0113Service = taFormTS0113Service;
		this.taFormTS0119Service = taFormTS0119Service;
	}

	@PostMapping("/pdf/ta-form-ts0107")
	public void pdfTs0107(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("export" + REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.PDF);

		TaFormTS0107Vo ts0107Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0107Vo.class);

		byte[] bytes = taFormTS0107Service.exportTaFormTS0107(ts0107Vo);

		String filename = String.format(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
	
	@PostMapping("/pdf/ta-form-ts0110")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		logger.info("export TaFormTS01_10.pdf");
		
		TaFormTS0110Vo ts0110Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0110Vo.class);
		byte[] bytes = taFormTS0110Service.exportTaFormTS0110(ts0110Vo);

		String filename = String.format("TaFormTS01_10.pdf", DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
	
	@PostMapping("/pdf/ta-form-ts0113")
	public void pdfTs0113(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("export TaFormTS01_13.pdf");
		
		TaFormTS0113Vo ts0113Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0113Vo.class);
		byte[] bytes = taFormTS0113Service.exportTaFormTS0113(ts0113Vo);

		String filename = String.format("TaFormTS01_13.pdf", DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
	
	@PostMapping("/pdf/ta-form-ts0119")
	public void pdfTs0119(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("export TaFormTS01_19.pdf");
		TaFormTS0119Vo ts0119Vo = gson.fromJson(reportJsonBean.getJson(), TaFormTS0119Vo.class);
		byte[] bytes = taFormTS0119Service.exportTaFormTS019(ts0119Vo);

		String filename = String.format("TaFormTS01_19.pdf", DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(bytes, response.getOutputStream());
	}
	
}
