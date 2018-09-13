package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;
import th.co.baiwa.excise.ia.persistence.entity.IaStamType;
import th.co.baiwa.excise.ia.persistence.vo.Int051111Vo;
import th.co.baiwa.excise.ia.service.Int051111Service;

@Controller
@RequestMapping("api/ia/int051111")
public class Int051111Controller {
	
	@Autowired
    private Int051111Service int051111Service;

	@PostMapping("/save")
	@ResponseBody
	public List<Int051111Vo> save(@RequestBody List<Int051111Vo> formVos) {
		int051111Service.save(formVos);
		return formVos;
	}

	@GetMapping("/stamTypes")
    @ResponseBody
    public List<IaStamType> stamTypes(){
	    return int051111Service.stamTypes();
    }

    @GetMapping("/stamGenre/{stamTypeId}")
    @ResponseBody
    public List<IaStamGenre> stamGenres(@PathVariable("stamTypeId")String stamTypeId){
	    return int051111Service.stamGenres(stamTypeId);
    }
}
