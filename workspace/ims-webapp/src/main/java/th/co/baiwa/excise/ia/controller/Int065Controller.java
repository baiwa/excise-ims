package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.ia.service.Int065Service;

@Controller
@RequestMapping("api/ia/int065")
public class Int065Controller {

	@Autowired
	private Int065Service int065Service;
	
	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int065Vo> findAll(@RequestBody Int065FormVo formVo) {
		return int065Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector() {
		return int065Service.sector();
	}

	@PostMapping("/area")
    @ResponseBody
    public List<Lov> sector(@RequestBody Long idMaster){
        List<Lov> arae = int065Service.area(idMaster);
        return arae;
    }
	
	@PostMapping("/branch")
    @ResponseBody
    public List<Lov> branch(@RequestBody Long idMaster){
        List<Lov> branch = int065Service.branch(idMaster);
        return branch;
    }
}