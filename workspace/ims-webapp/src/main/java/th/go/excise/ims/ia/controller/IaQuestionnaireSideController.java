package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.IaQuestionnaireSideService;
import th.go.excise.ims.ia.vo.IaQuestionnaireSideVo;

@Controller
@RequestMapping("/api/ia/questionnaire_side")
public class IaQuestionnaireSideController {

	@Autowired
	private IaQuestionnaireSideService iaQuestionnaireSideService;
	
	@GetMapping("/")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSideVo>> findAllQtnSide() {
		ResponseData<List<IaQuestionnaireSideVo>> responseData = new ResponseData<List<IaQuestionnaireSideVo>>();
		List<IaQuestionnaireSideVo> data = new ArrayList<>();
		try {
			data = iaQuestionnaireSideService.findAll();
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
	
}
