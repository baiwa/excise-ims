package th.go.excise.ims.mockup.controller.ta;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.mockup.domain.ta.ExciseFile;
import th.go.excise.ims.mockup.domain.ta.Response;
import th.go.excise.ims.mockup.persistence.entity.ExciseDetail;
import th.go.excise.ims.mockup.service.ta.ExciseDetailService;

@Controller
@RequestMapping("excise/detail")
public class ExciseDetailController {
	
	@Autowired
	ExciseDetailService exciseService;
	
	@PutMapping("/list/{id}")
	@ResponseBody
	public Response addFile(@PathVariable("id") String id, @RequestBody ExciseFile[] file) {
		if (exciseService.saveExciseFileUpload(id, file))
			return new Response("200", "OK");
		else
			return new Response("404", "ERROR");
	}
	
	@GetMapping("/list/{id}")
	@ResponseBody
	public List<ExciseDetail> list(@PathVariable("id") String id) {
		List<ExciseDetail> li = exciseService.findById(id, 1);
		return li;
	}

	@GetMapping("/list/{id}/{limit}")
	@ResponseBody
	public List<ExciseDetail> listLimit(@PathVariable("id") String id,
								   @PathVariable(value = "limit", required = false) int limit) {
		List<ExciseDetail> li = exciseService.findById(id, limit);
		return li;
	}
	
}
