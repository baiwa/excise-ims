package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope0412Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.service.Ope0412Service;

@Controller
@RequestMapping("api/ta/ope0412")
public class Opo0412Controller {

	@Autowired
	private Ope0412Service ope0412Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope0412Vo> findAll(@RequestBody Ope0461FormVo formVo){
		return ope0412Service.findAll(formVo);
	}

	/*@GetMapping("/exciseidList")
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

    @PostMapping("/save")
    @ResponseBody
    public Ope046SumVo save(@RequestBody Ope046SumVo sumVo)
    {
         ope046Service.save(sumVo);
         return sumVo;
    }*/
}
