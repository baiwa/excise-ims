package th.go.excise.ims.mockup.controller;

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
		Date date = new Date();
		date.setYear(Integer.parseInt(fulldate[1]));
		date.setMonth(Integer.parseInt(fulldate[0]));
		ResponseDataTable<MockupVo> listdata= mockupService.findAll("" , vo, date, input.getMonth());
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
	public void createWorkSheet(@ModelAttribute MockupVo vo, DataTableRequest input){
		String[] fulldate = input.getStartBackDate().split("/");
		Date date = new Date();
		date.setYear(Integer.parseInt(fulldate[1]));
		date.setMonth(Integer.parseInt(fulldate[0]));
		int month = input.getMonth() != null ? input.getMonth() : 0;
		planWorksheetHeaderService.insertPlanWorksheetHeaderService(vo,date, month);
		
	}
	
}
