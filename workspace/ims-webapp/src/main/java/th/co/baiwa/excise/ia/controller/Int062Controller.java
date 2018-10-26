package th.co.baiwa.excise.ia.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int062FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062Vo;
import th.co.baiwa.excise.ia.service.Int062Service;

@Controller
@RequestMapping("api/ia/int062")
public class Int062Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int062Service int062Service;
	
	@PostMapping("/uploadExcel")
	@ResponseBody
	public List<Int062Vo> upload(@ModelAttribute Int062FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		logger.info("INT062 UPLOAD FILE EXCEL!!");
		return int062Service.upload(formVo);
	}

}
