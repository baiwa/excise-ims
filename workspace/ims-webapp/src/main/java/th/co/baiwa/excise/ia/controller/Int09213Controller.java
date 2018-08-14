package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("api/ia/int09213")
public class Int09213Controller {
	
	private static Logger logger = LoggerFactory.getLogger(Int09213Controller.class);
	
	@Autowired
	private IaTravelCostWorkSheetDetailService iaTravelCostWorkSheetDetailService;
	
	
	@GetMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09213Vo> list(HttpServletRequest httpServletRequest){
		DataTableAjax<Int09213Vo> list = null;
		try {
			@SuppressWarnings("unchecked")
			List<Int09213Vo> dataTableSession = (List<Int09213Vo>) httpServletRequest.getSession().getAttribute(REPORT.TABLE_INT09213);
			 list = iaTravelCostWorkSheetDetailService.findAll(dataTableSession);
			 logger.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			logger.error("Error ! ==> Int0911Controller method findAll");
		}
		
		return list;
	}
	
	@GetMapping("/setSession")
	@ResponseBody
	public ResponseEntity<?> setSession(HttpServletRequest httpServletRequest) {
		
		List<Int09213Vo> voList = new ArrayList<>();
		
		httpServletRequest.getSession().setAttribute(REPORT.TABLE_INT09213, voList);
	
		
		Int09213Vo vo = new Int09213Vo();
		return new ResponseEntity<Int09213Vo>(vo, HttpStatus.OK);
	}
	
	@PostMapping("/addData")
	@ResponseBody
	public Int09213FormVo addData(@RequestBody Int09213FormVo formVo,HttpServletRequest httpServletRequest) {
		
		@SuppressWarnings("unchecked")
		List<Int09213Vo> dataTableSession = (List<Int09213Vo>) httpServletRequest.getSession().getAttribute(REPORT.TABLE_INT09213);
		iaTravelCostWorkSheetDetailService.addData(dataTableSession,formVo);
		return formVo;
		
	}
	
	@PostMapping("/listDropdown")
	@ResponseBody
	public List<LabelValueBean> dropdownList(@ModelAttribute Int09213FormVo formVo){
		return iaTravelCostWorkSheetDetailService.dropdownListType(formVo);
	}

}
