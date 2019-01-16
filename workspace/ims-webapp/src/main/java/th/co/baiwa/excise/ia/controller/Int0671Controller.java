package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.vo.Int0671FormVo;
import th.co.baiwa.excise.ia.service.Int0671Service;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Controller
@RequestMapping("api/ia/int0671")
public class Int0671Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int0671Service int0671Service;

	@PostMapping("/saveTime")
	@ResponseBody
	public CommonMessage<List<Int0671FormVo>> saveTime(@RequestBody Int0671FormVo dataReq) {
		logger.info("saveTime");
		return int0671Service.saveTime(dataReq);
	}

	@PostMapping("/updateTime")
	@ResponseBody
	public CommonMessage<List<Int0671FormVo>> update(@RequestBody Int0671FormVo vo) {
		logger.info("updateTime id: {}", vo.getTimeSetId());
		return int0671Service.updateTime(vo);
	}

	@PostMapping("/deleteTime")
	@ResponseBody
	public CommonMessage<List<Int0671FormVo>> delete(@RequestBody Int0671FormVo vo) {
		logger.info("deleteTime id: {}", vo.getTimeSetId());
		return int0671Service.deleteTime(vo);
	}
	
	@PostMapping("/queryInit")
	@ResponseBody
	public CommonMessage<List<Int0671FormVo>> queryInit() {
		return int0671Service.queryInit();
	}

}
