package th.co.baiwa.excise.ia.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911T2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;
import th.co.baiwa.excise.ia.service.Int0911Service;

@Controller
@RequestMapping("api/ia/int0911")
public class Int0911Controller {

	private static Logger log = LoggerFactory.getLogger(Int0911Controller.class);
	
	@Autowired
	private Int0911Service int0911Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int0911Vo> list(@RequestBody Int0911FormVo formVo){
		DataTableAjax<Int0911Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int0911Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}
	
	@PostMapping("/list2")
	@ResponseBody
	public DataTableAjax<Int0911T2Vo> list2(@RequestBody Int0911FormVo formVo){
		DataTableAjax<Int0911T2Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int0911Service.findAll2(formVo);
			 log.info("Data list2 {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll2",e);
		}
		
		return list;
	}

	@PostMapping("/delete")
    @ResponseBody
    public Message delete(@RequestBody Int0911FormVo formVo){
		log.info("id : {}",formVo.getId());
        try {
        	int0911Service.delete(formVo.getId());
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	@PostMapping("/deleteT2")
    @ResponseBody
    public Message deleteT2(@RequestBody Int0911FormVo formVo){
		log.info("id : {}",formVo.getId());
        try {
        	int0911Service.deleteT2(formVo.getId());
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	
	@PostMapping("/gethead")
	@ResponseBody
	public Int0911FormVo gethead(@RequestBody Int0911FormVo formVo){
		Int0911FormVo data = new Int0911FormVo();
		try {
			data = int0911Service.gethead(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! getHead ",e);
			return data;
		}
		return data;
	}
	
	@PostMapping("/uploadFile")
	@ResponseBody
	public Message upload(@ModelAttribute Int0911FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException{
		try {
			int0911Service.upload(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! upload ",e);
			  return ApplicationCache.getMessage("MSG_00003");
        }
        return ApplicationCache.getMessage("MSG_00002");

	}
}
