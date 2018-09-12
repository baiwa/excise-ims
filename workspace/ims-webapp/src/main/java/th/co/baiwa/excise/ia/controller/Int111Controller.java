
package th.co.baiwa.excise.ia.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

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
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int111Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;
import th.co.baiwa.excise.ia.service.IaFollowUpProjectService;

@Controller
@RequestMapping("api/ia/int111")
public class Int111Controller {

	private Logger log = LoggerFactory.getLogger(Int111Controller.class);

	@Autowired
	private IaFollowUpProjectService iaFollowUpProjectService;
	
	@PostMapping("/search")
	@ResponseBody
	public ResponseDataTable<Int111Vo> search(@RequestBody Int111FormVo formVo) {
		return iaFollowUpProjectService.searchIaFollowUpProject(formVo);
	}
	
	@GetMapping("/status")
	@ResponseBody
	public ResponseEntity<?> statusDropdown() {
		return new ResponseEntity<List<LabelValueBean>>(iaFollowUpProjectService.getStatusDropdown(), HttpStatus.OK);
	}
	
	@GetMapping("/get/followUpProject/{id}")
	@ResponseBody
	public ResponseEntity<?> getFollowUpproject(@PathVariable("id") Long id) {
		Int111Vo iaFollowUpProject = iaFollowUpProjectService.findById(id);
		return new ResponseEntity<Int111Vo>(iaFollowUpProject, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Int111FormVo vo) {
		iaFollowUpProjectService.saveOrUpdate(vo);
		return new ResponseEntity<Int111FormVo>(vo, HttpStatus.OK);
	}
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Int111FormVo vo) {
		iaFollowUpProjectService.saveOrUpdate(vo);
		return new ResponseEntity<Int111FormVo>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") List<Long> ids) {
		iaFollowUpProjectService.delete(ids);
		return new ResponseEntity<List<Long>>(ids, HttpStatus.OK);
	}
	
	@PostMapping("/closeJob")
	@ResponseBody
	public ResponseEntity<?> closeJob(@RequestBody Int111FormVo vo) {
		iaFollowUpProjectService.closeJob(vo);
		return new ResponseEntity<Int111FormVo>(vo, HttpStatus.OK);
	}
	
	@PostMapping("/shiftDate")
	@ResponseBody
	public ResponseEntity<?> shiftDate(@RequestBody Int11ShiftDateVo vo) {
		iaFollowUpProjectService.shiftDate(vo);
		return new ResponseEntity<Int11ShiftDateVo>(vo, HttpStatus.OK);
	}
	
/*	@GetMapping("/export")
	public ResponseEntity<FileSystemResource> export(@ModelAttribute Int111FormVo formVo) throws Exception {
		String fileName = String.format("EXPORT_IA_FOLLOW_UP_PROJECT_%s.xlsx", DateConstant.convertDateToStrDDMMYYYY(new Date()));

		FileSystemResource file = iaFollowUpProjectService.exportFile(formVo);
        		
		HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.TEXT_PLAIN);
        respHeaders.setContentLength(file.getFile().length());
        respHeaders.setContentDispositionFormData("attachment", fileName);
        
		return new ResponseEntity<FileSystemResource>(file, respHeaders, HttpStatus.OK);
	}*/
	
	@GetMapping("/exportFollowUpProject")
	@ResponseBody
	public  void exportFollowUpProject(@ModelAttribute Int111FormVo formVo, HttpServletResponse response) throws Exception {
		log.info("projectName : "+formVo.getProjectName()+ ", status : " +formVo.getStatus());
		iaFollowUpProjectService.exportFollowUpProject(formVo, response);
	 
	}
}
