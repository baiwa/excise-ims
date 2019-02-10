package co.th.ims.taxaudit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.th.ims.taxaudit.service.TaxOperatorService;
import co.th.ims.taxaudit.vo.TaxOperatorVo;

@Controller
@RequestMapping("/api/taxOperatorWs")
public class TaxOperatorController {

    @Autowired
    private TaxOperatorService taxOperatorService;

    @GetMapping("/")
    @ResponseBody
    public List<TaxOperatorVo> getOperator(@RequestBody TaxOperatorVo.TaxOperatorFormVo formVo){
        List<TaxOperatorVo> datas = this.taxOperatorService.getOperator(formVo);
        return datas;
    }
    
}
