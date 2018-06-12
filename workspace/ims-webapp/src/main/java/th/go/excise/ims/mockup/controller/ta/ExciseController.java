package th.go.excise.ims.mockup.controller.ta;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.mockup.domain.ta.ExciseFile;
import th.go.excise.ims.mockup.domain.ta.Response;
import th.go.excise.ims.mockup.service.ta.ExciseService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping("working/excise")
public class ExciseController {
	
	@Autowired
	ExciseService exciseService;
	
	@PutMapping("/list/{id}")
	@ResponseBody
	public Response addData(@PathVariable("id") String id, @RequestBody ExciseFile[] file) {
		if (exciseService.saveExciseFileUpload(id, file))
			return new Response("200", "OK");
		else
			return new Response("404", "ERROR");
	}
	
}
