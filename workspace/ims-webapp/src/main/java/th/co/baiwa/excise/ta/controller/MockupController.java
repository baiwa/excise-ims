package th.co.baiwa.excise.ta.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.MockupVo;
import th.co.baiwa.excise.ta.service.ListOfValueService;
import th.co.baiwa.excise.ta.service.MockupService;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("working/test")
public class MockupController {

	@Autowired
	private MockupService mockupService;
	
	@Autowired
	private ListOfValueService listOfValueService;
	
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;	
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<MockupVo> listdata(@ModelAttribute MockupVo vo, DataTableRequest input) {
		String[] fulldate = input.getStartBackDate().split("/");
		Calendar date = Calendar.getInstance(DateConstant.LOCAL_TH);
		date.set(Integer.parseInt(fulldate[1]), Integer.parseInt(fulldate[0]), 1);
		ResponseDataTable<MockupVo> listdata= mockupService.findAll("" , vo, date.getTime(), input.getMonth(),input.getExciseProductType());
		return listdata;
	}
	
	@PostMapping("/createWorkSheet")
	@ResponseBody
	public String createWorkSheet(@ModelAttribute MockupVo vo, DataTableRequest input){
		String[] fulldate = input.getStartBackDate().split("/");
		Calendar date = Calendar.getInstance(DateConstant.LOCAL_TH);
		date.set(Integer.parseInt(fulldate[1]), Integer.parseInt(fulldate[0]), 1);
		int month = input.getMonth() != null ? input.getMonth() : 0;
		List<String> listCondition = new ArrayList<String>();
		String condition[] = vo.getConditionStr().split(",");
		for (String con : condition) {
			listCondition.add(con);
		}
		vo.setCondition(listCondition);
		return planWorksheetHeaderService.insertPlanWorksheetHeaderService(vo,date.getTime(), month,input.getExciseProductType());
	}
	@PostMapping("/checkDupProductType")
	@ResponseBody
	public List<String> checkDupProductType(@ModelAttribute MockupVo vo, DataTableRequest input){
		String[] fulldate = input.getStartBackDate().split("/");
		Calendar date = Calendar.getInstance(DateConstant.LOCAL_TH);
		date.set(Integer.parseInt(fulldate[1]), Integer.parseInt(fulldate[0]), 1);
		int month = input.getMonth() != null ? input.getMonth() : 0;
		
		
		return planWorksheetHeaderService.getProductionInProcessByMonthAndBackDate(vo, date.getTime(), month);
	}
	
}
