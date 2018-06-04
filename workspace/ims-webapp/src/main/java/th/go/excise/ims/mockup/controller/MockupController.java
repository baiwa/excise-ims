package th.go.excise.ims.mockup.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.go.excise.ims.mockup.domain.DataTableRequest;
import th.go.excise.ims.mockup.domain.MockupVo;
import th.go.excise.ims.mockup.persistence.entity.ExciseEntity;
import th.go.excise.ims.mockup.service.MockupService;

import javax.websocket.server.PathParam;

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
	
}
