package th.co.baiwa.excise.ia.controller;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int06112ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06112FormVo;
import th.co.baiwa.excise.ia.service.Int06112Service;

@Controller
@RequestMapping("api/ia/int0612")
public class Int06112Controller {

	@Autowired
	private Int06112Service int06112Service;
	
	@PostMapping("/upload")
	@ResponseBody
	public DataTableAjax<Int06112ExcelVo> upload(@ModelAttribute Int06112FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {		
		return int06112Service.readFileExcel(formVo);
	}
}
