
package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int085Vo;
import th.co.baiwa.excise.ia.service.Int085Service;

@Controller
@RequestMapping("api/ia/int085")
public class Int085Controller {

	private Logger log = LoggerFactory.getLogger(Int085Controller.class);

	
	@Autowired
	private Int085Service int085Service;
	

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int085Vo> list(@RequestBody Int085FormVo formVo){
		DataTableAjax<Int085Vo> list = null;
		try {
			 list = int085Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}

}
