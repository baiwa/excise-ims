package th.go.excise.ims.mockup.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.go.excise.ims.mockup.domain.DataTableRequest;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.service.MockupService;

@Controller
@RequestMapping("working/test")
public class MockupController {

	@Autowired
	private MockupService mockupService;	
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<MockupVo> listdata(@ModelAttribute MockupVo vo, DataTableRequest input){
		Date date = new Date();
		date.setTime(input.getStartBackDate());
		System.out.println(input.getMonth());
		ResponseDataTable<MockupVo> listdata= mockupService.findAll("" , vo, date, input.getMonth());
		return listdata;
	}
	
	
	
	
}
