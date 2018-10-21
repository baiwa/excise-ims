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
import th.co.baiwa.excise.ia.persistence.vo.Int05111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int05111Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610Vo;
import th.co.baiwa.excise.ia.service.Int0610Service;

@Controller
@RequestMapping("api/ia/int0610")
public class Int0610Controller {

	@Autowired
	private Int0610Service int0610Service;
	
	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int0610Vo> findAll(@RequestBody Int0610FormVo formVo) {
		return int0610Service.findAll(formVo);
	}
	
	@GetMapping("/activity")
	@ResponseBody
	public List<Lov> activity() {
		return int0610Service.activity();
	}
	
	@GetMapping("/budge")
	@ResponseBody
	public List<Lov> budge() {
		return int0610Service.budge();
	}

//	@GetMapping("/list")
//	@ResponseBody
//	public DataTableAjax<Int0610ExcelVo> fineAll(@ModelAttribute Int0610FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
//		return int0610Service.readFileExcel(formVo);
//	}
//	
//	@GetMapping("/getDatail")
//	@ResponseBody
//	public DataTableAjax<Int0610ExcelVo> getDatail(@ModelAttribute Int0610FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
//		return int0610Service.readFileExcel(formVo);
//	}
	
	
}
