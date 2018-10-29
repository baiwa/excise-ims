package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope048FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048Vo;
import th.co.baiwa.excise.ta.service.Ope048Service;

import java.util.List;

@Controller
@RequestMapping("api/ta/opo048")
public class Opo048Controller {

	@Autowired
	private Ope048Service ope048Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope048Vo> findAll(@RequestBody Ope048FormVo formVo){
		return ope048Service.findAll(formVo);
	}

	@GetMapping("/exciseidList")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope048Service.findExciseId();
		return dataList;
	}

	@PostMapping("/findByExciseId")
	@ResponseBody
	public Ope048FormVo findByExciseId(@RequestBody String exciseId) {
		return ope048Service.findByExciseId(exciseId);
	}
	
	/*@PostMapping("/upload")
	@ResponseBody
	public List<Ope046ExcelVo> upload(@ModelAttribute Ope046FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return ope048Service.readFileExcel(formVo);
	}

    @PostMapping("/save")
    @ResponseBody
    public List<Ope046Vo> save(@RequestBody List<Ope046Vo> vo)
    {
    	ope048Service.save(vo);
         return vo;
    }*/
}
