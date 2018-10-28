package th.co.baiwa.excise.cop.controller;

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

import th.co.baiwa.excise.cop.persistence.vo.Cop061ExcelVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop061FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop061Vo;
import th.co.baiwa.excise.cop.service.Cop061Service;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Controller
@RequestMapping("api/cop/cop061")
public class Cop061Controller {

	@Autowired
	private Cop061Service cop061Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Cop061Vo> findAll(@RequestBody Cop061FormVo formVo){
		return cop061Service.findAll(formVo);
	}

	@GetMapping("/exciseidList")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = cop061Service.findExciseId();
		return dataList;
	}

	@PostMapping("/findByExciseId")
	@ResponseBody
	public Cop061FormVo findByExciseId(@RequestBody String exciseId) {
		return cop061Service.findByExciseId(exciseId);
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public List<Cop061ExcelVo> upload(@ModelAttribute Cop061FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return cop061Service.readFileExcel(formVo);
	}

    @PostMapping("/save")
    @ResponseBody
    public List<Cop061Vo> save(@RequestBody List<Cop061Vo> vo)
    {
         cop061Service.save(vo);
         return vo;
    }
}
