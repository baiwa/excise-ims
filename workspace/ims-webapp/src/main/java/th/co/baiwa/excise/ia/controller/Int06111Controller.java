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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0611ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0611FormVo;
import th.co.baiwa.excise.ia.service.Int06111Service;

@Controller
@RequestMapping("api/ia/int0611")
public class Int06111Controller {

	@Autowired
	private Int06111Service int0611Service;

	@PostMapping("/upload")
	@ResponseBody
	public DataTableAjax<Int0611ExcelVo> upload(@ModelAttribute Int0611FormVo formVo) throws EncryptedDocumentException, InvalidFormatException, IOException {
		DataTableAjax<Int0611ExcelVo> data = new DataTableAjax<>();
		try {
			data = int0611Service.readFileExcel(formVo);
		}catch (Exception e){
			data = new DataTableAjax<>();
		}
		return data;
	}
}
