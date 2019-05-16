package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Ws_Incfri8020IncVo;

@Controller
@RequestMapping("/api/ia/int06/03")
public class Int0603Controller {

//	@PostMapping("/find-tab1")
//	@ResponseBody
//	public ResponseData<List<Ws_Incfri8020IncVo>> findTab1(@RequestBody Int0601RequestVo request) {
//		ResponseData<List<Ws_Incfri8020IncVo>> response = new ResponseData<List<Ws_Incfri8020IncVo>>();
//		try {
//			response.setData(int0601Service.findTab1ByCriteria(request));
//			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setMessage(RESPONSE_MESSAGE.ERROR500);
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
}
