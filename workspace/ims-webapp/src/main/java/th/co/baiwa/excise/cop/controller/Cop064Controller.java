package th.co.baiwa.excise.cop.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.cop.persistence.vo.Cop061FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064ExcelVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064Vo;
import th.co.baiwa.excise.cop.service.Cop064Service;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Controller
@RequestMapping("api/cop/cop064")
public class Cop064Controller {

	@Autowired
	private Cop064Service cop064Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Cop064Vo> findAll(@RequestBody Cop064FormVo formVo){
		return cop064Service.findAll(formVo);
	}

	@PostMapping("/exciseidList")
	@ResponseBody
	public List<String> findExciseId(@RequestBody String fiscalYear) {
		List<String> dataList = cop064Service.findExciseId(fiscalYear);
		return dataList;
	}

	@PostMapping("/findByExciseId")
	@ResponseBody
	public Cop061FormVo findByExciseId(@RequestBody String exciseId) {
		return cop064Service.findByExciseId(exciseId);
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public List<Cop064ExcelVo> upload(@ModelAttribute Cop064FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return cop064Service.readFileExcel(formVo);
	}

    @PostMapping("/save")
    @ResponseBody
    public List<Cop064Vo> save(@RequestBody Cop064FormVo formVo)
    {
         cop064Service.save(formVo);
         
         return formVo.getDataListVo();
    }
}
