package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;
import th.go.excise.ims.ia.service.Int030405Service;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;

@Controller
@RequestMapping("/api/ia/int03/04/05")
public class Int030405Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);
	
	@Autowired
	private Int030405Service int030405Service;
	
	@PostMapping("/systemUnworkingList")
	@ResponseBody
	public DataTableAjax<Int030405Vo> systemUnworkingList(@RequestBody Int030405FormVo form) {
		DataTableAjax<Int030405Vo> response = new DataTableAjax<Int030405Vo>();
		List<Int030405Vo> systemUnworkingList = new ArrayList<Int030405Vo>();

		try {
			systemUnworkingList = int030405Service.systemUnworkingList(form);
			response.setData(systemUnworkingList);

		} catch (Exception e) {
			logger.error("Int030405Controller systemUnworkingList : ", e);
		}
		return response;
	}
	
	@PostMapping("/getForm0304")
	@ResponseBody
	public Int0301Vo getForm0304(@RequestBody Int0301FormVo form) {
		Int0301Vo response = new Int0301Vo();
		try {	
			response = int030405Service.getForm0304(form);
		} catch (Exception e) {
			logger.error("Int0304Controller : " , e);
		}
		return response;
	}
	
	@GetMapping("/year/export/{budgetYear}/{inspectionWork}/{idConfig}")
	public void exportByYear(@PathVariable("budgetYear") String budgetYear,@PathVariable("inspectionWork") BigDecimal inspectionWork,@PathVariable("idConfig") BigDecimal idConfig, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("สรุปผลปัจจัยเสี่ยงจำนวนครั้งที่ใช้งานไม่ได้ของระบบ", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030405Service.exportInt030405(budgetYear,inspectionWork,idConfig);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}
}
