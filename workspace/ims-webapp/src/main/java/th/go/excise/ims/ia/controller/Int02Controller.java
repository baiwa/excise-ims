package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.service.Int02Service;
import th.go.excise.ims.ia.vo.Int02Vo;

@Controller
@RequestMapping("/api/ia/int02")
public class Int02Controller {
	
	@Autowired
	private Int02Service int02Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireHdr>> filterQtnHdr(@RequestBody Int02Vo request) {
		ResponseData<List<IaQuestionnaireHdr>> responseData = new ResponseData<List<IaQuestionnaireHdr>>();
		List<IaQuestionnaireHdr> data = new ArrayList<IaQuestionnaireHdr>();
		
		try {
			data = int02Service.filterQtnHdr(request);
			responseData.setData(data);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> findOne(@PathVariable("id") String idStr) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.findOne(idStr));
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> save(@RequestBody IaQuestionnaireHdr request) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.save(request));
			responseData.setMessage("SAVE SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			// e.printStackTrace();
			responseData.setMessage("SAVE ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> update(@PathVariable("id") String idStr, @RequestBody IaQuestionnaireHdr request) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.update(idStr, request));
			responseData.setMessage("UPDATE SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("UPDATE ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
