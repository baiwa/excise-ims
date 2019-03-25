package th.go.excise.ims.oa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.service.Oa0206Service;
import th.go.excise.ims.oa.vo.Oa0206FormVo;
import th.go.excise.ims.oa.vo.Oa0206Vo;

@Controller
@RequestMapping("/api/oa/02/06")
public class Oa0206Controller {
	

	private static final Logger logger = LoggerFactory.getLogger(Oa0206Controller.class);
	
	@Autowired
	private Oa0206Service oa0206Service;
	
	@PostMapping("/getData")
	@ResponseBody
	public DataTableAjax<Oa0206Vo> filterByCriteria(@RequestBody Oa0206FormVo request) {
		DataTableAjax<Oa0206Vo> response = new DataTableAjax<>();
		try {
			response = oa0206Service.filterByCriteria(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
	@PostMapping("/pdf/{id}")
	public void pdfTs(@PathVariable("id") String idStr, HttpServletResponse response)throws IOException, JRException{
		byte[] reportFile = oa0206Service.objectToPDF();

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=hydDocabonService.pdf");

		IOUtils.write(reportFile, response.getOutputStream());
	}
	

}
