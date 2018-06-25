package th.go.excise.ims.mockup.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.ia.constant.DateConstant;
import th.go.excise.ims.mockup.domain.DataTableRequest;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.entity.sys.Lov;
import th.go.excise.ims.mockup.service.ListOfValueService;
import th.go.excise.ims.mockup.service.MockupService;
import th.go.excise.ims.mockup.service.ta.PlanWorksheetHeaderService;

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
		return planWorksheetHeaderService.insertPlanWorksheetHeaderService(vo,date.getTime(), month,input.getExciseProductType());
		
	}
	
	@PostMapping("/getAnalysNumber")
	@ResponseBody
	public List<String> getAnalizeNumber() {
		List<String> li = planWorksheetHeaderService.queryAnalysNumberFromHeader();
		return li;
	}
	
	@PostMapping("/getCoordinates")
	@ResponseBody
	public List<Lov> getCoordinates() {
		Lov lov = new Lov("PRODUCT_TYPE");
		List<Lov> li = listOfValueService.queryLovByCriteria(lov, "VALUE1");
		return li;
	}
	
}
