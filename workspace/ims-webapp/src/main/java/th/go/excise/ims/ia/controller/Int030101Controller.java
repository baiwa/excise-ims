package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int030101Service;
import th.go.excise.ims.ia.vo.Int030101FormVo;


@Controller
@RequestMapping("/api/ia/int03/01/01")
public class Int030101Controller {
	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);
	@Autowired
	private Int030101Service int030101Service;
	
	@PostMapping("/saveFactors")
	public ResponseData<String> save(@ModelAttribute Int030101FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		String save = "บันทึกเรียบร้อบ";

		try {
			int030101Service.saveFactors(form);
			response.setData(save);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
}
