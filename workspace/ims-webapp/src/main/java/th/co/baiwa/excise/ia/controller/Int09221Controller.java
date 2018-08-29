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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09221FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09221Vo;
import th.co.baiwa.excise.ia.service.Int09221Service;

@Controller
@RequestMapping("api/ia/int09221")
public class Int09221Controller {

	private static Logger log = LoggerFactory.getLogger(Int09221Controller.class);
	
	@Autowired
	private Int09221Service int09221Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09221Vo> list(@RequestBody Int09221FormVo formVo){
		DataTableAjax<Int09221Vo> list = null;
//		if(StringUtils.isBlank(formVo.getSearchFlag())) {
//			formVo.setSearchFlag("FALSE");
//		}
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = int09221Service.findAll(formVo);
			 log.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! ==> Int09221Controller method findAll");
		}
		
		return list;
	}
	
	@GetMapping("/documentTypeDropdown")
	@ResponseBody
	public List<LabelValueBean> documentTypeDropdown(){
		return int09221Service.documentTypeDropdown();
	}

	@PostMapping("/deleteList")
    @ResponseBody
    public Message delete(@RequestBody List<Long> ids){

        try {
        	int09221Service.delete(ids);
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	
}
