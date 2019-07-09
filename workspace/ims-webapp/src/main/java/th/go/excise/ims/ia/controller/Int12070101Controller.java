package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse;
import th.go.excise.ims.ia.service.Int12070101Service;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;

@Controller
@RequestMapping("/api/ia/int12/07/01/01")
public class Int12070101Controller {
	private Logger logger = LoggerFactory.getLogger(Int12070101Controller.class);

	@Autowired
	private Int12070101Service int12070101Service;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaRentHouse> save(@RequestBody Int12070101SaveFormVo en) {

		ResponseData<IaRentHouse> response = new ResponseData<IaRentHouse>();
		IaRentHouse data = new IaRentHouse();
		try {
			data = int12070101Service.save(en);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("/find-by-renhouse/{id}")
	@ResponseBody
	public ResponseData<Int12070101SaveFormVo> findById(@PathVariable("id") long id) {
		ResponseData<Int12070101SaveFormVo> response = new ResponseData<Int12070101SaveFormVo>();
		try {
			response.setData(int12070101Service.findById(id));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
}
