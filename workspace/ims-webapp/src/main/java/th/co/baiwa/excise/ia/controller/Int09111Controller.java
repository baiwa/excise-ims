package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.ia.service.Int09111Service;

@Controller
@RequestMapping("api/ia/int09111")
public class Int09111Controller {

	private static Logger log = LoggerFactory.getLogger(Int09111Controller.class);
	
	@Autowired
	private Int09111Service int09111Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09TableDtlVo> list(@RequestBody Int09111And3FormVo formVo){
		DataTableAjax<Int09TableDtlVo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int09111Service.findAll(formVo);
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
        	int09111Service.delete(formVo.getId());
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
			id = int09111Service.save(formVo);	
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
			int09111Service.edit(formVo);	
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
			int09111Service.saveAll(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}
	
	@PostMapping("/getAllowanceRAndRoostR")
	@ResponseBody
	public Lov getAllowanceRAndRoostR(@RequestBody Int09111And3FormVo formVo){
		Lov lov = new Lov();
		try {
			lov = int09111Service.getAllowanceRAndRoostR(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! getAllowanceRAndRoostR ",e);
		}
		return lov;
	}
	
	@PostMapping("/getNumberDateAllowance")
	@ResponseBody
	public Float getNumberDateAllowance(@RequestBody Int09111And3FormVo formVo){
		Float numberDate = 0f;
		try {
			numberDate = int09111Service.getNumberDateAllowance(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! getNumberDate ",e);
		}
		return numberDate;
	}


}
