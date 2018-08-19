package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;
import th.co.baiwa.excise.ia.service.IaTravelCostWorkSheetDetailService;

@Controller
@RequestMapping("api/ia/int09213")
public class Int09213Controller {
	
	private static Logger logger = LoggerFactory.getLogger(Int09213Controller.class);
	
	@Autowired
	private IaTravelCostWorkSheetDetailService iaTravelCostWorkSheetDetailService;
	
	
	@GetMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09213Vo> list(){
		
		return new DataTableAjax<>();				 			
	}

	@PostMapping("/addData")
	@ResponseBody
	public Int09213Vo addData(@RequestBody Int09213FormVo formVo) {
        Int09213Vo data = new Int09213Vo();
		try {
             data = iaTravelCostWorkSheetDetailService.addData(formVo);
            logger.info("Add data success.");
        }catch (Exception e){
		    logger.error("Add data fail!.");
        }

		return data;
		
	}
	
	@PostMapping("/listDropdown")
	@ResponseBody
	public List<LabelValueBean> dropdownList(@RequestBody Int09213FormVo formVo){
        List<LabelValueBean> result = iaTravelCostWorkSheetDetailService.dropdownListType(formVo);
        return  result;
	}

}
