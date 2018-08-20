package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int09211FormVo;
import th.co.baiwa.excise.ia.service.IaTravelCostWorkSheetHeaderService;

import java.util.List;

@Controller
@RequestMapping("api/ia/int09211")
public class Int09211Controller {

	private static Logger logger = LoggerFactory.getLogger(Int09211Controller.class);
	
	@Autowired
	private IaTravelCostWorkSheetHeaderService int0911Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Budget> list(@RequestBody Int09211FormVo formVo){
		DataTableAjax<Budget> list = null;
		try {
			 list = int0911Service.findAll(formVo);
			 logger.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			logger.error("Error ! ==> Int0911Controller method findAll");
		}
		
		return list;
	}
	
	@GetMapping("/departmentDropdown")
	@ResponseBody
	public List<LabelValueBean> departmentDropdown(){
		return int0911Service.departmentDropdown();
	}

	@PostMapping("/delete")
    @ResponseBody
    public Message delete(@RequestBody List<Long> ids){

        try {
            int0911Service.delete(ids);
        } catch (Exception e) {
            return ApplicationCache.getMessage("MSG_00006");
        }
        return ApplicationCache.getMessage("MSG_00005");

    }
	
}
