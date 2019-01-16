package th.co.baiwa.excise.cop.controller;

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
import th.co.baiwa.excise.cop.persistence.vo.Cop0711FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711Vo;
import th.co.baiwa.excise.cop.service.Cop0711Service;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;

@Controller
@RequestMapping("api/cop/cop0711")
public class Cop0711Controller {

	private static Logger log = LoggerFactory.getLogger(Cop0711Controller.class);
	
	@Autowired
	private Cop0711Service cop0711Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Cop0711Vo> list(@RequestBody Cop0711FormVo formVo){
		DataTableAjax<Cop0711Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = cop0711Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}
	
	@PostMapping("/list2")
	@ResponseBody
	public DataTableAjax<Cop0711Vo> list2(@RequestBody Cop0711FormVo formVo){
		DataTableAjax<Cop0711Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = cop0711Service.findAll2(formVo);
			 log.info("Data list2 {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll2",e);
		}
		
		return list;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public CommonMessage<Long> save(@RequestBody Cop0711FormVo formVo){
		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			id = cop0711Service.save(formVo);	
			message.setData(id);
		} catch (Exception e) {
			log.error("Error ! add ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public CommonMessage<Cop0711FormVo> edit(@RequestBody Cop0711FormVo formVo){

		CommonMessage<Cop0711FormVo> message = new CommonMessage<Cop0711FormVo>();
		try {
			cop0711Service.edit(formVo);	
			message.setData(formVo);
		} catch (Exception e) {
			log.error("Error ! edit ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}
	
	@PostMapping("/delete")
    @ResponseBody
    public Message delete(@RequestBody Cop0711FormVo formVo){
		log.info("id : {}",formVo.getId());
        try {
        	cop0711Service.delete(formVo.getId());
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }

}
