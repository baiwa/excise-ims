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
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int06113FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06113Vo;
import th.co.baiwa.excise.ia.service.Int06113Service;

@Controller
@RequestMapping("api/ia/int0613")
public class Int06113Controller {

	@Autowired
	private Int06113Service int06113Service;

    @PostMapping("/findAll")
    @ResponseBody
    public DataTableAjax<Int06113Vo> findAll(@RequestBody Int06113FormVo formVo){
        return int06113Service.findAll(formVo);
    }

	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector(){

        List<Lov> sector = int06113Service.sector();
        return sector;
	}

    @PostMapping("/area")
    @ResponseBody
    public List<Lov> sector(@RequestBody Long idMaster){

        List<Lov> arae = int06113Service.area(idMaster);
        return arae;
    }

    @GetMapping("/year")
    @ResponseBody
    public List<LabelValueBean> year(){
        List<LabelValueBean> year = int06113Service.year();
        return year;
    }
		
}
