package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int0603Service;
import th.go.excise.ims.ia.vo.AuditLicdupDVo;
import th.go.excise.ims.ia.vo.AuditLicdupHVo;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ia.vo.Int0602ResultTab1Vo;
import th.go.excise.ims.ia.vo.Int0603SaveVo;

@Controller
@RequestMapping("/api/ia/int06/03")
public class Int0603Controller {

	@Autowired
	private Int0603Service int0603Service;

	@PostMapping("/find-by-criteria")
	@ResponseBody
	public ResponseData<List<Int0602ResultTab1Vo>> findByCriteria(@RequestBody Int0602FormVo request) {
		ResponseData<List<Int0602ResultTab1Vo>> response = new ResponseData<List<Int0602ResultTab1Vo>>();
		try {
			response.setData(int0603Service.findByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-all-audit-lic-dup-no")
	@ResponseBody
	public ResponseData<List<AuditLicdupHVo>> findAllDataHeader() {
		ResponseData<List<AuditLicdupHVo>> response = new ResponseData<List<AuditLicdupHVo>>();
		try {
			response.setData(int0603Service.findAuditLicdupHList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<AuditLicdupHVo> saveLicdupH(@RequestBody Int0603SaveVo request) {
		ResponseData<AuditLicdupHVo> response = new ResponseData<>();
		try {
			response.setData(int0603Service.saveLicdupH(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-data-by-audit-lic-dup-no")
	@ResponseBody
	public ResponseData<List<AuditLicdupDVo>> findAuditLicdupDByAuditLicdupNo(@RequestBody String auditLicdupNo) {
		ResponseData<List<AuditLicdupDVo>> response = new ResponseData<List<AuditLicdupDVo>>();
		try {
			response.setData(int0603Service.findAuditLicdupDByAuditLicdupNo(auditLicdupNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/find-header-by-audit-lic-dup-no")
	@ResponseBody
	public ResponseData<AuditLicdupHVo> findAuditLicdupHByAuditLicdupNo(@RequestBody String auditLicdupNo) {
		ResponseData<AuditLicdupHVo> response = new ResponseData<AuditLicdupHVo>();
		try {
			response.setData(int0603Service.findByAuditLicdupNo(auditLicdupNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
