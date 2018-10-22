package th.co.baiwa.excise.ta.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.MockupVo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.AnalysisFromCountVo;
import th.co.baiwa.excise.ta.persistence.vo.MockupForm;
import th.co.baiwa.excise.ta.service.MockupService;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("api/working/test")
public class MockupController {

	@Autowired
	private MockupService mockupService;
		
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;	
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<MockupVo> listdata(@ModelAttribute MockupVo vo, MockupForm input) {
		String[] fulldate = input.getStartBackDate().split("/");
		Calendar date = Calendar.getInstance(DateConstant.LOCAL_TH);
		date.set(Integer.parseInt(fulldate[1]), Integer.parseInt(fulldate[0]), 1);
		DataTableAjax<MockupVo> listdata= mockupService.findAll("" , vo, date.getTime(), input.getMonth(),input.getExciseProductType(),input.getFormSearch(),input);
		return listdata;
	}
	
	@PostMapping("/countList")
	@ResponseBody
	public Long countListdata(@RequestBody AnalysisFromCountVo countVo) {		
		Long listdata= mockupService.countListdata(countVo);
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

	@GetMapping("/webService")
    @ResponseBody
    public ResponseEntity<?> webService(){
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.OK);
    }
	
}
