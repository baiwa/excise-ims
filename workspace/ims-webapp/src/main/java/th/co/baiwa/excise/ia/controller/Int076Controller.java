package th.co.baiwa.excise.ia.controller;

import java.io.IOException;
import java.util.List;

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

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.vo.Int076FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.ia.service.Int076Service;

@Controller
@RequestMapping("api/ia/int076")
public class Int076Controller {

	private Logger logger = LoggerFactory.getLogger(Int076Controller.class);

	@Autowired
	private Int076Service int076Service;

	@PostMapping("/readFileExcel")
	@ResponseBody
	public List<Int076Vo> readFileExcel(@ModelAttribute Int076FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		logger.info("INT076 readFileExcel!!");
		return int076Service.readFileExcel(formVo);
	}
	
	@PostMapping("/checkData")
	@ResponseBody
	public List<Int076Vo> checkData(@RequestBody List<Int076Vo> dataList){
		logger.info("INT076 checkData!!");
		return int076Service.checkData(dataList);
	}

//	@PostMapping("/export")
//	@ResponseBody
//	public List<Int076Vo> export(@RequestBody List<Int076Vo> dataCheckList ,HttpServletResponse response)throws Exception{
//		logger.info("INT076 checkData!!");
//		return int076Service.checkData(dataList);
//	}
	

}
