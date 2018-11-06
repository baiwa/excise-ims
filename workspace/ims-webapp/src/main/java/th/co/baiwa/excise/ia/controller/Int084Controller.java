
package th.co.baiwa.excise.ia.controller;

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

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int084FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int084Vo;
import th.co.baiwa.excise.ia.service.Int084Service;

@Controller
@RequestMapping("api/ia/int084")
public class Int084Controller {

	private Logger log = LoggerFactory.getLogger(Int084Controller.class);

	
	@Autowired
	private Int084Service int084Service;
	

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int084Vo> list(@RequestBody Int084FormVo formVo){
		DataTableAjax<Int084Vo> list = null;
		try {
			 list = int084Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}
	
	@GetMapping("/exportFile")
	@ResponseBody
	public  void exportFile(@ModelAttribute Int084FormVo formVo, HttpServletResponse response) throws Exception {
		try {
			int084Service.exportFile(formVo,response);
		} catch (Exception e) {
			log.error("Error ! ==> exportFile method exportFile",e);
		}
		
	}
}
