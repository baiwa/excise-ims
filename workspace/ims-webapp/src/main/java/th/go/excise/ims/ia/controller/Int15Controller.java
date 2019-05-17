package th.go.excise.ims.ia.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.DocumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.service.IaGfdrawAccountService;
import th.go.excise.ims.ia.vo.Int030101FormVo;

@Controller
@RequestMapping("/api/ia/int15/01")
public class Int15Controller {
	private Logger logger = LoggerFactory.getLogger(Int15Controller.class);
	
	@Autowired
	private IaGfdrawAccountService iaGfdrawAccountService;
	
	@PostMapping("/upload")
	@ResponseBody
	public ResponseData<String> upload(@ModelAttribute Int030101FormVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			MultipartFile file = form.getFile();
			iaGfdrawAccountService.addDataByExcel(file);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller upload : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
