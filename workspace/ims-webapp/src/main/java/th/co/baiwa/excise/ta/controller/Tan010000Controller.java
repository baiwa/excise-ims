package th.co.baiwa.excise.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000Vo;
import th.co.baiwa.excise.ta.service.Tan010000Service;

@Controller
@RequestMapping("api/taxAuditNew/tan010000")
public class Tan010000Controller {
	
	private static Logger log = LoggerFactory.getLogger(Tan010000Controller.class);

	@Autowired
	private Tan010000Service tan010000Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Tan010000Vo> search(@RequestBody Tan010000FormVo formVo) {
		String username = UserLoginUtils.getCurrentUserBean().getUsername();
		DataTableAjax<Tan010000Vo> dataTable = new DataTableAjax<Tan010000Vo>();
		try {
			log.info("Tan010000Controller");
			formVo.setUser(username);
			dataTable = tan010000Service.search(formVo);
		} catch (Exception e) {
			log.error("Tan010000Controller ",e);
			
		}
		
		return dataTable;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public CommonMessage<Tan010000FormVo> edit(@RequestBody Tan010000FormVo formVo){

		CommonMessage<Tan010000FormVo> message = new CommonMessage<Tan010000FormVo>();
		try {
			tan010000Service.edit(formVo);	
			message.setData(formVo);
		} catch (Exception e) {
			log.error("Error ! edit ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}

}
