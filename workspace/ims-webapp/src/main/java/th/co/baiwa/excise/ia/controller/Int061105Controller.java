package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ia.service.Int061105Service;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Controller
@RequestMapping("api/ia/int061105")
public class Int061105Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int061105Service int061105Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<DisbursementRequest> search(@ModelAttribute Int061105FormSearchVo ids) {
		return int061105Service.search(ids);
	}
	
	@PostMapping("/comment")
	@ResponseBody
	public Message comment(@RequestBody Int061105FormSearchVo ids) {
		return int061105Service.comment(ids);
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Message save(@RequestBody Int061105FormSearchVo ids) {
		Message msg = ApplicationCache.getMessage("MSG_00003");
		try {
			if(BeanUtils.isNotEmpty(ids)) {
				int061105Service.save(ids);
				msg = ApplicationCache.getMessage("MSG_00002");
			}else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@PostMapping("/getNextval")
	@ResponseBody
	public Long getNextval() {
		return int061105Service.getNextval();
	}

}
