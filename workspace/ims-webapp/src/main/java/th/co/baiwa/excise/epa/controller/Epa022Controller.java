package th.co.baiwa.excise.epa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;
import th.co.baiwa.excise.epa.service.Epa022Service;

@Controller
@RequestMapping("api/epa/epa022")
public class Epa022Controller {

	@Autowired
	private Epa022Service epa022Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa012Vo> search(@RequestBody Epa012FormVo epa012FormVo) {
		return epa022Service.search(epa012FormVo);
	}
	
	@PostMapping("/getInformation")
	@ResponseBody
	public List<Epa012Vo> getInformation(@RequestBody Epa012FormVo epa012FormVo) {
		return epa022Service.getInformation(epa012FormVo);
	}
	
	@PostMapping("/saveTaxDatas")
	@ResponseBody
	public ResponseEntity<?> saveTaxDatas(@RequestBody Epa012FormVo epa012FormVo) {
		epa022Service.saveTaxDatas(epa012FormVo);
		return new ResponseEntity<Epa012FormVo>(epa012FormVo, HttpStatus.OK);	
	}
	
	@PostMapping("/saveFactoryDatas")
	@ResponseBody
	public ResponseEntity<?> saveFactoryDatas(@RequestBody Epa012FormVo epa012FormVo) {
		epa022Service.saveFactoryDatas(epa012FormVo);
		return new ResponseEntity<Epa012FormVo>(epa012FormVo, HttpStatus.OK);	
	}
}
