package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;
import th.co.baiwa.excise.ia.persistence.entity.IaStamType;
import th.co.baiwa.excise.ia.persistence.vo.Int05113Vo;
import th.co.baiwa.excise.ia.service.Int05113Service;
import th.co.baiwa.excise.ta.persistence.entity.ExciseFile;

import java.util.List;

@Controller
@RequestMapping("api/ia/int05113")
public class Int05113Controller {

    @Autowired
    private Int05113Service int05113Service;

	@PostMapping("/save")
	@ResponseBody
	public List<Int05113Vo> save(@RequestBody List<Int05113Vo> formVos) {
        int05113Service.save(formVos);
		return formVos;
	}

	@GetMapping("/stamTypes")
    @ResponseBody
    public List<IaStamType> stamTypes(){
	    return int05113Service.stamTypes();
    }

    @GetMapping("/stamGenre/{stamTypeId}")
    @ResponseBody
    public List<IaStamGenre> stamGenres(@PathVariable("stamTypeId")String stamTypeId){
	    return int05113Service.stamGenres(stamTypeId);
    }
}
