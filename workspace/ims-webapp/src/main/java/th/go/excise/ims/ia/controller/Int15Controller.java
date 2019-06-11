package th.go.excise.ims.ia.controller;

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
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.service.ExciseOrgGfmisService;
import th.go.excise.ims.ia.service.IaGfdrawAccountService;
import th.go.excise.ims.ia.service.IaGfledgerAccountService;
import th.go.excise.ims.ia.service.IaGfmovementAccountService;
import th.go.excise.ims.ia.service.IaGftrialBalanceService;
import th.go.excise.ims.ia.vo.ExciseOrgGfDisburseUnitVo;
import th.go.excise.ims.ia.vo.Int15UploadVo;

@Controller
@RequestMapping("/api/ia/int15/01")
public class Int15Controller {
	private Logger logger = LoggerFactory.getLogger(Int15Controller.class);

	@Autowired
	private IaGfdrawAccountService iaGfdrawAccountService;

	@Autowired
	private IaGftrialBalanceService iaGftrialBalanceService;

	@Autowired
	private IaGfledgerAccountService iaGfledgerAccountService;

	@Autowired
	private IaGfmovementAccountService iaGfmovementAccountService;
	
	@Autowired
	private ExciseOrgGfmisService exciseOrgGfmisService;

	@PostMapping("/upload/ia-type-data1")
	@ResponseBody
	public ResponseData<List<IaGfdrawAccount>> uploadT1(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		ResponseData<List<IaGfdrawAccount>> responseData = new ResponseData<List<IaGfdrawAccount>>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfdrawAccountService.addDataByExcel(file);

		} catch (Exception e) {
			logger.error("Int030102Controller upload1 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data2")
	@ResponseBody
	public ResponseData<List<IaGftrialBalance>> uploadT2(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
//		iaGftrialBalanceService
		ResponseData<List<IaGftrialBalance>> responseData = new ResponseData<List<IaGftrialBalance>>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGftrialBalanceService.addDataByExcel(file);
//			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller upload2 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data3")
	@ResponseBody
	public ResponseData<List<IaGfledgerAccount>> uploadT3(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
//		iaGfledgerAccountService
		ResponseData<List<IaGfledgerAccount>> responseData = new ResponseData<List<IaGfledgerAccount>>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfledgerAccountService.addDataByExcel(file);
//			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller upload3 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/upload/ia-type-data4")
	@ResponseBody
	public ResponseData<List<IaGfmovementAccount>> uploadT4(@ModelAttribute Int15UploadVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
//		iaGfmovementAccountService
		ResponseData<List<IaGfmovementAccount>> responseData = new ResponseData<List<IaGfmovementAccount>>();
		try {
			MultipartFile file = form.getFile();
			responseData = iaGfmovementAccountService.addDataByExcel(file);
//			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller upload4 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data1")
	@ResponseBody
	public ResponseData<String> save1(@RequestBody List<IaGfdrawAccount> form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfdrawAccountService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save1 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data2")
	@ResponseBody
	public ResponseData<String> save2(@RequestBody List<IaGftrialBalance> form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGftrialBalanceService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save2 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data3")
	@ResponseBody
	public ResponseData<String> save3(@RequestBody List<IaGfledgerAccount> form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfledgerAccountService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save3 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/ia-type-data4")
	@ResponseBody
	public ResponseData<String> save4(@RequestBody List<IaGfmovementAccount> form) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			iaGfmovementAccountService.saveData(form);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save4 : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
