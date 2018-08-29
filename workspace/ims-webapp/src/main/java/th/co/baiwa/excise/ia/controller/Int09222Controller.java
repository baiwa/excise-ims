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
import th.co.baiwa.excise.ia.persistence.vo.Int09222FormVo;
import th.co.baiwa.excise.ia.service.Int09222Service;

@Controller
@RequestMapping("api/ia/int09222")
public class Int09222Controller {

	private static Logger log = LoggerFactory.getLogger(Int09222Controller.class);
	
	@Autowired
	private Int09222Service int09222Service;
	

	@PostMapping("/save")
    @ResponseBody
    public Int09222FormVo save(@RequestBody Int09222FormVo vo){

        try {
        	 Long id = int09222Service.save(vo);
        	vo.setIdProcess(id);
        } catch (Exception e) {
        	vo.setErrorMessage(ApplicationCache.getMessage("MSG_00003"));
            return vo;
        }
    	vo.setErrorMessage(ApplicationCache.getMessage("MSG_00002"));
        return vo;
    }

	
}
