package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.service.Int1502Service;
import th.go.excise.ims.preferences.vo.ExciseIncMast;

@Controller
@RequestMapping("/api/ia/int15/02")
public class Int1502Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int1502Controller.class);
	
	@Autowired
	private Int1502Service int1502Service;
	
	@GetMapping("/get-dropdown-coa-code")
	@ResponseBody
	public ResponseData<List<IaChartOfAcc>> getDropdownCoaCode() {
		ResponseData<List<IaChartOfAcc>> response = new ResponseData<List<IaChartOfAcc>>();
		try {
			response.setData(int1502Service.getDropdownCoaCode());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-dropdown-inc-code")
	@ResponseBody
	public ResponseData<List<ExciseIncMast>> getDropdownIncCode() {
		ResponseData<List<ExciseIncMast>> response = new ResponseData<List<ExciseIncMast>>();
		try {
			response.setData(ApplicationCache.getExciseIncMastList());
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
