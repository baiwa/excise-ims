package th.co.baiwa.excise.ta.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.ta.persistence.vo.Ope041ExcelVo;
import th.co.baiwa.excise.ta.service.DisbRmatWsService;
import th.co.baiwa.excise.ta.service.Ope04ExcelService;

@Controller
@RequestMapping("api/ope042")
public class Ope042Controller {
	
	@Autowired
	private DisbRmatWsService disbRmatWsService;
	
	@Autowired
	private Ope04ExcelService ope04Service;
	
	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope041DataTable> allData) {
		try {
			disbRmatWsService.insertDisbRmatWsService(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		Ope041ExcelVo result = gson.fromJson(dataJson, Ope041ExcelVo.class);

		List<Ope041DataTable> dataList = result.getVoList();

		/* set fileName */
		String fileName = URLEncoder.encode("กระดาษทำการจ่ายวัตถุดิบ", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = ope04Service.exportOpe042(dataList);
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
