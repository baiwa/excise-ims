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
import th.co.baiwa.excise.ia.service.Int066Service;

@Controller
@RequestMapping("api/ia/int066")
public class Int066Controller {
	@Autowired
	private Int066Service int066Service;
	
	/*@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int05111Vo> findAll(@RequestBody Int05111FormVo formVo) {
		return int05111Service.findAll(formVo);
	}*/

	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector() {
		return int066Service.sector();
	}

	@PostMapping("/area")
    @ResponseBody
    public List<Lov> sector(@RequestBody Long idMaster){
        List<Lov> arae = int066Service.area(idMaster);
        return arae;
    }
	
	@PostMapping("/branch")
    @ResponseBody
    public List<Lov> branch(@RequestBody Long idMaster){
        List<Lov> branch = int066Service	.branch(idMaster);
        return branch;
    }
}
