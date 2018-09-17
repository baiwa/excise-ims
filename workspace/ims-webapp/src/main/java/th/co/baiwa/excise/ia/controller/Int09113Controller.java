package th.co.baiwa.excise.ia.controller;

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
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.ia.service.Int09113Service;

@Controller
@RequestMapping("api/ia/int09113")
public class Int09113Controller {

	private static Logger log = LoggerFactory.getLogger(Int09113Controller.class);
	
	@Autowired
	private Int09113Service int09113Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09TableDtlVo> list(@RequestBody Int09111And3FormVo formVo){
		DataTableAjax<Int09TableDtlVo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int09113Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}
	@PostMapping("/delete")
    @ResponseBody
    public Message delete(@RequestBody Int09111And3FormVo formVo){
		log.info("id : {}",formVo.getId());
        try {
        	int09113Service.delete(formVo.getId());
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	@PostMapping("/save")
	@ResponseBody
	public CommonMessage<Long> save(@RequestBody Int09111And3FormVo formVo){
		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			id = int09113Service.save(formVo);	
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
	public CommonMessage<Int09111And3FormVo> edit(@RequestBody Int09111And3FormVo formVo){

		CommonMessage<Int09111And3FormVo> message = new CommonMessage<Int09111And3FormVo>();
		try {
			int09113Service.edit(formVo);	
			message.setData(formVo);
		} catch (Exception e) {
			log.error("Error ! edit ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}
	
	@PostMapping("/saveAll")
	@ResponseBody
	public Message saveAll(@RequestBody Int09111And3FormVo formVo){
		
		try {
			int09113Service.saveAll(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}

	

}
