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
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046Vo;
import th.co.baiwa.excise.ta.service.Ope046Service;

@Controller
@RequestMapping("api/ta/opo046")
public class Opo046Controller {

	@Autowired
	private Ope046Service ope046Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope046Vo> findAll(@RequestBody Ope046FormVo formVo){
		return ope046Service.findAll(formVo);
	}

	@GetMapping("/exciseidList")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope046Service.findExciseId();
		return dataList;
	}

	@PostMapping("/findByExciseId")
	@ResponseBody
	public Ope046FormVo findByExciseId(@RequestBody String exciseId) {
		return ope046Service.findByExciseId(exciseId);
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public List<Ope046ExcelVo> upload(@ModelAttribute Ope046FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return ope046Service.readFileExcel(formVo);
	}
}
