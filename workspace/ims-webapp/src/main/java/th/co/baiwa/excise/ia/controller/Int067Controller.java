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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.excise.ia.service.Int067Service;

@Controller
@RequestMapping("api/ia/int067")
public class Int067Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int067Service int067Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Int068FormVo> qeuryByFilter(@ModelAttribute PublicUtility pu){
		logger.info("qeuryByFilter Int067");
		DataTableAjax<Int068FormVo> listData = null;
		try {
			listData = int067Service.qeuryByFilter(pu);
		} catch (Exception e) {
			logger.info("query Fail!!");
		}
		return listData;
	}

}
