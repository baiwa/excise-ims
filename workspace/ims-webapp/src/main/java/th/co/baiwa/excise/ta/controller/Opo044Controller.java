package th.co.baiwa.excise.ta.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope044FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope044SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope044Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.ta.service.Ope044Service;

@Controller
@RequestMapping("api/ta/opo044")
public class Opo044Controller {

	
	@Autowired
	private Ope044Service ope044Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope044Vo> findAll(@RequestBody Ope044FormVo formVo){
		return ope044Service.findAll(formVo);
	}

	@GetMapping("/exciseidList")
	@ResponseBody
	public List<LabelValueBean> exciseidList() {
		List<LabelValueBean> dataList = ope044Service.exciseidList();
		return dataList;
	}
	
	@PostMapping("/findByExciseId")
	@ResponseBody
	public Ope044FormVo findByExciseId(@RequestBody String exciseId) {
		return ope044Service.findByExciseId(exciseId);
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public List<Ope046ExcelVo> upload(@ModelAttribute Ope046FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return ope044Service.readFileExcel(formVo);
	}
	
	@PostMapping("/save")
    @ResponseBody
    public Ope044SumVo save(@RequestBody Ope044SumVo sumVo)
    {
		ope044Service.save(sumVo);
         return sumVo;
    }

}
