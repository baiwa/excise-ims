package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.constant.IaConstant.REPORT;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;
import th.co.baiwa.excise.ia.service.IaTravelCostWorkSheetDetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/ia/int09213")
public class Int09213Controller {
	
	private static Logger logger = LoggerFactory.getLogger(Int09213Controller.class);
	
	@Autowired
	private IaTravelCostWorkSheetDetailService iaTravelCostWorkSheetDetailService;
	
	
	@GetMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09213Vo> list(){
		DataTableAjax<Int09213Vo> list = new DataTableAjax<>();
		/*try {
			@SuppressWarnings("unchecked")
			List<Int09213Vo> dataTableSession = (List<Int09213Vo>) httpServletRequest.getSession().getAttribute(REPORT.TABLE_INT09213);
			 list = iaTravelCostWorkSheetDetailService.findAll(dataTableSession);
			 logger.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			logger.error("Error ! ==> Int0911Controller method findAll");
		}*/
		
		return list;
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
