package th.go.excise.ims.mockup.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.DataTableRequest;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.entity.ExciseEntity;
import th.go.excise.ims.mockup.service.MockupService;
import th.go.excise.ims.mockup.service.ta.PlanWorksheetHeaderService;

@Controller
@RequestMapping("working/test")
public class MockupController {

	@Autowired
	private MockupService mockupService;
	
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;	
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<MockupVo> listdata(@ModelAttribute MockupVo vo, DataTableRequest input) {
		String[] fulldate = input.getStartBackDate().split("/");
		Calendar date = Calendar.getInstance(DateConstant.LOCAL_TH);
		date.set(Integer.parseInt(fulldate[1]), Integer.parseInt(fulldate[0]), 1);
//		
		ResponseDataTable<MockupVo> listdata= mockupService.findAll("" , vo, date.getTime(), input.getMonth(),input.getExciseProductType());
		return listdata;
	}
	
	@GetMapping("/list/{id}")
	@ResponseBody
	public List<ExciseEntity> list(@PathVariable("id") String id) {
		List<ExciseEntity> li = mockupService.findById(id, 1);
		return li;
	}

	@GetMapping("/list/{id}/{limit}")
	@ResponseBody
	public List<ExciseEntity> listLimit(@PathVariable("id") String id,
								   @PathVariable(value = "limit", required = false) int limit) {
		List<ExciseEntity> li = mockupService.findById(id, limit);
		return li;
	}
	
	@PostMapping("/createWorkSheet")
	@ResponseBody
	public String createWorkSheet(@ModelAttribute MockupVo vo, DataTableRequest input){
		String[] fulldate = input.getStartBackDate().split("/");
		Date date = new Date();
		date.setYear(Integer.parseInt(fulldate[1]));
		date.setMonth(Integer.parseInt(fulldate[0]));
		int month = input.getMonth() != null ? input.getMonth() : 0;
		return planWorksheetHeaderService.insertPlanWorksheetHeaderService(vo,date, month,input.getExciseProductType());
		
	}
	
}
