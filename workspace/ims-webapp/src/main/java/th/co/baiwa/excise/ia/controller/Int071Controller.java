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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountDetil;
import th.co.baiwa.excise.ia.persistence.vo.Int071ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int071FormVo;
import th.co.baiwa.excise.ia.service.Int071Service;

@Controller
@RequestMapping("api/ia/int071")
public class Int071Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int071Service int071Service;

	@PostMapping("/upload")
	@ResponseBody
	public DataTableAjax<Int071ExcelVo> upload(@ModelAttribute Int071FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		logger.info("INT071 UPLOAD FILE EXCEL!!");
		return int071Service.readExcel(formVo);
	}

	@PostMapping("/save")
	@ResponseBody
	public Message save(@RequestBody List<VerifyAccountDetil> excelList) {
		logger.info("INT071 SAVE FILE EXCEL!!");
		Message msg = ApplicationCache.getMessage("MSG_00003");

		try {
			int071Service.save(excelList);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		
		return msg;
	}

}
