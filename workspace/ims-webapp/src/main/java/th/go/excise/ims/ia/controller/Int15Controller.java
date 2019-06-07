package th.go.excise.ims.ia.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.IaGfdrawAccountService;
import th.go.excise.ims.ia.vo.Int15UploadVo;

@Controller
@RequestMapping("/api/ia/int15/01")
public class Int15Controller {
	private Logger logger = LoggerFactory.getLogger(Int15Controller.class);

	@Autowired
	private IaGfdrawAccountService iaGfdrawAccountService;

	

	@PostMapping("/upload-t1")
	@ResponseBody
	public ResponseData<String> uploadT1(@ModelAttribute Int15UploadVo form) throws EncryptedDocumentException, InvalidFormatException, IOException {

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

	@PostMapping("/upload-t2")
	@ResponseBody
	public ResponseData<String> uploadT2(@ModelAttribute Int15UploadVo form) throws EncryptedDocumentException, InvalidFormatException, IOException {

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

	@PostMapping("/upload-t3")
	@ResponseBody
	public ResponseData<String> uploadT3(@ModelAttribute Int15UploadVo form) throws EncryptedDocumentException, InvalidFormatException, IOException {

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
	@PostMapping("/upload-t4")
	@ResponseBody
	public ResponseData<String> uploadT4(@ModelAttribute Int15UploadVo form) throws EncryptedDocumentException, InvalidFormatException, IOException {

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
