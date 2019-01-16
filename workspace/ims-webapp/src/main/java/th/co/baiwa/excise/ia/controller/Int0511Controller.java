package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.service.Int0511Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/ia/int0511")
public class Int0511Controller {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int0511Service int0511Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int0511Vo> findAll(@RequestBody Int0511FormVo formVo) {
		return int0511Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<LabelValueBean> sector() {
		return int0511Service.sector();
	}

	@PostMapping("/area")
	@ResponseBody
	public List<LabelValueBean> area(@RequestBody String id) {
		return int0511Service.area(id);
	}

	@PostMapping("/save")
	@ResponseBody
	public Int0511FormVo save(@RequestBody Int0511FormVo formVo) {
		int0511Service.save(formVo);
		return formVo;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Int0511FormVo delete(@RequestBody Int0511FormVo formVo) {
		int0511Service.delete(formVo);
		return formVo;
	}
	
	@PostMapping("/listFile")
	@ResponseBody
	public List<String> listFile(@RequestBody String id) {
		return int0511Service.listFile(id);		
	}
	
	   @GetMapping("/exportFile")
			@ResponseBody
			public  void exportFile(@ModelAttribute Int0511FormVo formVo, HttpServletResponse response) throws Exception {
				try {
					int0511Service.exportFile(formVo, response);
				} catch (Exception e) {
					log.error("Error ! ==> exportFile method exportFile",e);
				}
				
			}
}
