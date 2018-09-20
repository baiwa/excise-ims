package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import th.co.baiwa.excise.constant.MessageConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int06121FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;
import th.co.baiwa.excise.ia.service.Int06121Service;

@Controller
@RequestMapping("api/ia/int06121")
public class Int06121Controller {

	@Autowired
	private Int06121Service int06121Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int06121Vo> findAll(@RequestBody Int06121FormVo formVo){
		return int06121Service.findAll(formVo);
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody String id){
		try {
			int06121Service.delete(id);
			return new ResponseEntity<>(MessageConstant.MSG.STATUS.DELETE.SUCCESS, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(MessageConstant.MSG.STATUS.DELETE.SUCCESS, HttpStatus.EXPECTATION_FAILED);
		}
	}
}