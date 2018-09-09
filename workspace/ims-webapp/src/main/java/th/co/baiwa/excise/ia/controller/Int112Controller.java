
package th.co.baiwa.excise.ia.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int112FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int112Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;
import th.co.baiwa.excise.ia.service.IaFollowUpDepartmentService;

@Controller
@RequestMapping("api/ia/int112")
public class Int112Controller {

	private Logger log = LoggerFactory.getLogger(Int112Controller.class);

	@Autowired
	private IaFollowUpDepartmentService iaFollowUpDepartmentService;
	
	@PostMapping("/search")
	@ResponseBody
	public ResponseDataTable<Int112Vo> search(@RequestBody Int112FormVo formVo) {
		return iaFollowUpDepartmentService.searchIaFollowUpDepartment(formVo);
	}
	
	@GetMapping("/department")
	@ResponseBody
	public ResponseEntity<?> departmentDropdown() {
		return new ResponseEntity<List<LabelValueBean>>(iaFollowUpDepartmentService.getDepartmentDropdown(), HttpStatus.OK);
	}
	
	@GetMapping("/region/{id}")
	@ResponseBody
	public ResponseEntity<?> regionDropdown(@PathVariable("id") String id) {
		return new ResponseEntity<List<LabelValueBean>>(iaFollowUpDepartmentService.getRegionDropdown(id), HttpStatus.OK);
	}
	
	@GetMapping("/district/{id}")
	@ResponseBody
	public ResponseEntity<?> districtDropdown(@PathVariable("id") String id) {
		return new ResponseEntity<List<LabelValueBean>>(iaFollowUpDepartmentService.getDistrictDropdown(id), HttpStatus.OK);
	}
	
	@GetMapping("/status")
	@ResponseBody
	public ResponseEntity<?> statusDropdown() {
		return new ResponseEntity<List<LabelValueBean>>(iaFollowUpDepartmentService.getStatusDropdown(), HttpStatus.OK);
	}

	@GetMapping("/get/followUpDepartment/{id}")
	@ResponseBody
	public ResponseEntity<?> getFollowUpproject(@PathVariable("id") Long id) {
		Int112Vo iaFollowUpDepartment = iaFollowUpDepartmentService.findById(id);
		return new ResponseEntity<Int112Vo>(iaFollowUpDepartment, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Int112FormVo vo) {
		iaFollowUpDepartmentService.saveOrUpdate(vo);
		return new ResponseEntity<Int112FormVo>(vo, HttpStatus.OK);
	}
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Int112FormVo vo) {
		iaFollowUpDepartmentService.saveOrUpdate(vo);
		return new ResponseEntity<Int112FormVo>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") List<Long> ids) {
		iaFollowUpDepartmentService.delete(ids);
		return new ResponseEntity<List<Long>>(ids, HttpStatus.OK);
	}
	
	@PostMapping("/closeJob")
	@ResponseBody
	public ResponseEntity<?> closeJob(@RequestBody Int112FormVo vo) {
		iaFollowUpDepartmentService.closeJob(vo);
		return new ResponseEntity<Int112FormVo>(vo, HttpStatus.OK);
	}
	
	@PostMapping("/shiftDate")
	@ResponseBody
	public ResponseEntity<?> shiftDate(@RequestBody Int11ShiftDateVo vo) {
		iaFollowUpDepartmentService.shiftDate(vo);
		return new ResponseEntity<Int11ShiftDateVo>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/export")
	public ResponseEntity<FileSystemResource> export(@ModelAttribute Int112FormVo formVo) throws Exception {
		String fileName = String.format("EXPORT_IA_FOLLOW_UP_DEPARTMENT_%s.xlsx", DateConstant.convertDateToStrDDMMYYYY(new Date()));

		FileSystemResource file = iaFollowUpDepartmentService.exportFile(formVo);
        		
		HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.TEXT_PLAIN);
        respHeaders.setContentLength(file.getFile().length());
        respHeaders.setContentDispositionFormData("attachment", fileName);
        
		return new ResponseEntity<FileSystemResource>(file, respHeaders, HttpStatus.OK);
	}
}
