package th.co.baiwa.excise.upload.controller;

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

import th.co.baiwa.excise.domain.form.FormUpload;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;

@Controller
@RequestMapping("api/upload")
public class UploadFileExciseController {
	private Logger logger = LoggerFactory.getLogger(UploadFileExciseController.class);

	@Autowired
	UploadFileExciseService uploadFileExciseService;

	@PostMapping("excel")
	@ResponseBody
	public List<Object> excel(@ModelAttribute FormUpload mainForm) throws EncryptedDocumentException, InvalidFormatException, IOException{
		logger.debug("mainForm : " + mainForm);
		return uploadFileExciseService.readFileExcel(mainForm);
	}

}
