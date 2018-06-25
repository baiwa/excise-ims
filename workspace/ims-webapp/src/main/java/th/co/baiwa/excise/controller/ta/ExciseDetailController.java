package th.co.baiwa.excise.controller.ta;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.ta.ExciseDetail;
import th.co.baiwa.excise.domain.ta.ExciseFile;
import th.co.baiwa.excise.domain.ta.Response;
import th.co.baiwa.excise.service.ta.ExciseDetailService;

@Controller
@RequestMapping("excise/detail")
public class ExciseDetailController {
	
	private Logger logger = LoggerFactory.getLogger(ExciseDetailController.class);
	
	@Autowired
	ExciseDetailService exciseService;
	
	@PutMapping("/list/{num}/{id}")
	@ResponseBody
	public Response addFile(@PathVariable("num") String num, @PathVariable("id") String id, @RequestBody ExciseFile[] file) {
		logger.info("ExciseDetailContorller.addFile id: {}, file: {}", id, file);
		if (exciseService.saveExciseFileUpload(id, num, file))
			return new Response("200", "OK");
		else
			return new Response("404", "ERROR");
	}
	
	@GetMapping("/list/{num}/{id}")
	@ResponseBody
	public List<ExciseDetail> list(@PathVariable("num") String num, @PathVariable("id") String id) {
		logger.info("ExciseDetailContorller.list num: {}, id: {}", num, id);
		List<ExciseDetail> li = exciseService.findById(id, num, 1);
		return li;
	}

	@GetMapping("/list/{num}/{id}/{limit}")
	@ResponseBody
	public List<ExciseDetail> list(@PathVariable("num") String num, @PathVariable("id") String id,
								   @PathVariable(value = "limit", required = false) int limit) {
		logger.info("ExciseDetailContorller.list num: {}, id: {}, limit: {}", num, id, limit);
		List<ExciseDetail> li = exciseService.findById(id, num, limit);
		return li;
	}
	
}
