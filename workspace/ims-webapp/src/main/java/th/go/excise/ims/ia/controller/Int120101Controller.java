package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int120101Service;
import th.go.excise.ims.ia.vo.Int120101FormVo;
import th.go.excise.ims.ia.vo.Int120101Vo;

@Controller
@RequestMapping("api/ia/int120101")
public class Int120101Controller {

	private static final Logger log = LoggerFactory.getLogger(Int120101Controller.class);
	@Autowired
	private Int120101Service int120101Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int120101Vo> findAll(@RequestBody Int120101FormVo formVo) {
		DataTableAjax<Int120101Vo> response = new DataTableAjax<Int120101Vo>();
		try {
			response = int120101Service.findAll(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public Int120101FormVo save(@RequestBody Int120101FormVo formVo) {
		int120101Service.save(formVo);
		return formVo;
	}

	@PostMapping("/delete")
	@ResponseBody
	public Int120101FormVo delete(@RequestBody Int120101FormVo formVo) {
		int120101Service.delete(formVo);
		return formVo;
	}

	@PostMapping("/listFile")
	@ResponseBody
	public List<String> listFile(@RequestBody String id) {
		return int120101Service.listFile(id);
	}

	@GetMapping("/exportFile")
	@ResponseBody
	public void exportFile(@ModelAttribute Int120101FormVo formVo, HttpServletResponse response) throws Exception {
		try {
			int120101Service.exportFile(formVo, response);
		} catch (Exception e) {
			log.error("Error ! ==> exportFile method exportFile", e);
		}

	}
}
