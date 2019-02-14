package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.service.IaQuestionnaireHdrService;
import th.go.excise.ims.ia.vo.IaQuestionnaireHdrVo;

@Controller
@RequestMapping("/api/ia/questionnaire-hdr")
public class IaQuestionnaireHdrController {
	
	@Autowired
	private IaQuestionnaireHdrService iaQuestionnaireHdrService;
	
	@PostMapping("/filter-qtn-hdr")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireHdr>> filterQtnHdr(@RequestBody IaQuestionnaireHdrVo request) {
		ResponseData<List<IaQuestionnaireHdr>> responseData = new ResponseData<List<IaQuestionnaireHdr>>();
		List<IaQuestionnaireHdr> data = new ArrayList<IaQuestionnaireHdr>();
		
		try {
			data = iaQuestionnaireHdrService.filterQtnHdr(request);
			responseData.setData(data);
			responseData.setMessage("SUCCES");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
