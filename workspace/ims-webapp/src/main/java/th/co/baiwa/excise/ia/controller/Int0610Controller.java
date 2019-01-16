package th.co.baiwa.excise.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.CheckPaymentExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610Vo;
import th.co.baiwa.excise.ia.service.CheckPaymentExcelService;
import th.co.baiwa.excise.ia.service.Int0610Service;

@Controller
@RequestMapping("api/ia/int0610")
public class Int0610Controller {

	@Autowired
	private CheckPaymentExcelService checkPaymentExcelService;

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

	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();

		CheckPaymentExcelVo result = gson.fromJson(dataJson, CheckPaymentExcelVo.class);
		List<Int0610Vo> dataList = result.getInt0610ExcelList();

		// set fileName
		String fileName = URLEncoder.encode("บันทึกข้อมูลเบิกจ่าย", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = checkPaymentExcelService.exportInt0610(dataList);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

}
