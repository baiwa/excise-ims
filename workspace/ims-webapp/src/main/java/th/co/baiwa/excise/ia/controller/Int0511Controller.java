package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.ia.service.Int0511Service;

@Controller
@RequestMapping("api/ia/int0511")
public class Int0511Controller {

	@Autowired	
	private Int0511Service int0511Service;

	@PostMapping("/findAll")
    @ResponseBody
    public DataTableAjax<Int0511Vo> findAll(@RequestBody Int0511FormVo formVo){
		return int0511Service.findAll(formVo);
    }
	
	@GetMapping("/sector")
	@ResponseBody
	public List<LabelValueBean> sector(){
		return int0511Service.sector();
	}
	
	@PostMapping("/area")
	@ResponseBody
	public List<LabelValueBean> area(@RequestBody String id){
		return int0511Service.area(id);
	}
}
