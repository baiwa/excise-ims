package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int020101Service;
import th.go.excise.ims.ia.vo.Int020101FormVo;
import th.go.excise.ims.ia.vo.Int020101Vo;

@Controller
@RequestMapping("/api/ia/int02/01/01")
public class Int020101Controller {

	@Autowired
	private Int020101Service int020101Service;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseData<List<Int020101Vo>> findAllQtnSide() {
		ResponseData<List<Int020101Vo>> responseData = new ResponseData<List<Int020101Vo>>();
		List<Int020101Vo> data = new ArrayList<>();
		try {
			data = int020101Service.findAll();
			responseData.setData(data);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			// e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/by/head/{head}")
	@ResponseBody
	public ResponseData<List<Int020101Vo>> findByIdHead(@PathVariable("head") String idHead) {
		ResponseData<List<Int020101Vo>> responseData = new ResponseData<List<Int020101Vo>>();
		List<Int020101Vo> data = new ArrayList<>();
		try {
			data = int020101Service.findByIdHead(idHead);
			responseData.setData(data);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			// e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<?> save(@RequestBody Int020101FormVo request) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			// e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
