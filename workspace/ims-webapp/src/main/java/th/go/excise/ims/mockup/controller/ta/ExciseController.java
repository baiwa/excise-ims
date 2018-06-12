package th.go.excise.ims.mockup.controller.ta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.mockup.domain.ta.File;
import th.go.excise.ims.mockup.domain.ta.Response;

@Controller
@RequestMapping("working/excise")
public class ExciseController {
	
	@PutMapping("/list/{id}")
	@ResponseBody
	public Response addData(@PathVariable("id") String id, @RequestBody File file) {
		System.out.println(file.getName());
		System.out.println(file.getType());
		System.out.println(file.getValue());
		if (file.getName() != "")
			return new Response("200", "OK");
		else
			return new Response("404", "ERROR");
	}
	
}
