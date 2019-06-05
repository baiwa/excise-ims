package th.go.excise.ims.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;
import th.go.excise.ims.ta.service.PaperBaHService;
import th.go.excise.ims.ta.vo.TaPaperBaHFormVo;

@Controller
@RequestMapping("/api/ta/paper")
public class PaperController {
	
	@Autowired
	private PaperBaHService paperBaHService;

	@PostMapping("/bah-insert")
	@ResponseBody
	public ResponseData<List<TaPaperBaH>> insertBaH(@RequestBody TaPaperBaHFormVo form) {
		ResponseData<List<TaPaperBaH>> responseData = new ResponseData<List<TaPaperBaH>>();
		try {
			paperBaHService.insertBaH(form);
			responseData.setData(null);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/bah-find")
	@ResponseBody
	public ResponseData<TaPaperBaH> findBaH(@RequestBody TaPaperBaHFormVo form) {
		ResponseData<TaPaperBaH> res = new ResponseData<TaPaperBaH>();
		try {
			res.setData(paperBaHService.findBaH(form));
			res.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(RESPONSE_STATUS.FAILED);
		}
		return res;
	}
}
