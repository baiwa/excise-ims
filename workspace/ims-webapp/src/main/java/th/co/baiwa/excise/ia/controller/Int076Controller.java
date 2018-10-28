package th.co.baiwa.excise.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int076FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.ia.service.Int076Service;

@Controller
@RequestMapping("api/ia/int076")
public class Int076Controller {

	private final String SESSION_DATA = "SESSION_DATA_INT076";

	private Logger logger = LoggerFactory.getLogger(Int076Controller.class);

	@Autowired
	private Int076Service int076Service;

	@PostMapping("/readFileExcel")
	@ResponseBody
	public List<Int076Vo> readFileExcel(@ModelAttribute Int076FormVo formVo, HttpServletRequest httpServletRequest)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		logger.info("INT076 readFileExcel!!");
		httpServletRequest.getSession().removeAttribute(SESSION_DATA);

		return int076Service.readFileExcel(formVo);
	}

	@PostMapping("/checkData")
	@ResponseBody
	public List<Int076Vo> checkData(@RequestBody List<Int076Vo> dataList, HttpServletRequest httpServletRequest) {
		logger.info("INT076 checkData!!");

		List<Int076Vo> resultList = int076Service.checkData(dataList);

		httpServletRequest.getSession().setAttribute(SESSION_DATA, resultList);

		return resultList;

	}

	@GetMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("INT076 export!!");

		@SuppressWarnings("unchecked")
		List<Int076Vo> resultList = (List<Int076Vo>) httpServletRequest.getSession().getAttribute(SESSION_DATA);

		/* set fileName */
		String fileName = "check_transfer_money_account_local_governmen";

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = int076Service.export(resultList);
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
