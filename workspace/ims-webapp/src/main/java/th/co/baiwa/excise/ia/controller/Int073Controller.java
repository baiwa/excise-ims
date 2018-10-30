package th.co.baiwa.excise.ia.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import th.co.baiwa.excise.ia.persistence.vo.Int073Vo;
import th.co.baiwa.excise.ia.service.Int073Service;

@Controller
@RequestMapping("api/ia/int073")
public class Int073Controller {

	private Logger logger = LoggerFactory.getLogger(Int073Controller.class);

	@Autowired
	private Int073Service int073Service;

	@PostMapping("/readFileExcelTrialBalanceSheet")
	@ResponseBody
	public List<Int073Vo> readFileExcelTrialBalanceSheet(@ModelAttribute Int073Vo formVo, HttpServletRequest httpServletRequest)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		logger.info("INT073 readFileExcelTest!!");
		return int073Service.readFileExcelTrialBalanceSheet(formVo);
	}

}
