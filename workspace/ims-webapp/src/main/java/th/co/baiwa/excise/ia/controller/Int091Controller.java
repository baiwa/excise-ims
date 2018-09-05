package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ia.service.Int091Service;

@Controller
@RequestMapping("api/ia/int091")
public class Int091Controller {

	private static Logger log = LoggerFactory.getLogger(Int091Controller.class);
	
	@Autowired
	private Int091Service int091Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int091Vo> list(@RequestBody Int091FormVo formVo){
		DataTableAjax<Int091Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int091Service.findAll(formVo);
			 log.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! ==> Int091Controller method findAll");
		}
		
		return list;
	}
	@PostMapping("/add")
	@ResponseBody
	public Message add(@RequestBody Int091FormVo formVo){
		
		try {
			 int091Service.add(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}

	@PostMapping("/deleteList")
    @ResponseBody
    public Message delete(@RequestBody List<Long> ids){

        try {
        	int091Service.delete(ids);
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	
	

}
