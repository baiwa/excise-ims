package th.co.baiwa.excise.ta.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope044SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope044Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.ta.service.Ope045Service;
import th.co.baiwa.excise.ta.service.Ope04ExcelService;

@Controller
@RequestMapping("api/ta/opo045")
public class Opo045Controller {

	@Autowired
	private Ope04ExcelService ope04Service;

	@Autowired
	private Ope045Service ope045Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope045Vo> findAll(@RequestBody Ope045FormVo formVo) {
		return ope045Service.findAll(formVo);
	}

	@GetMapping("/exciseidList")
	@ResponseBody
	public List<LabelValueBean> exciseidList() {
		List<LabelValueBean> dataList = ope045Service.exciseidList();
		return dataList;
	}

	@PostMapping("/findByExciseId")
	@ResponseBody
	public Ope045FormVo findByExciseId(@RequestBody String exciseId) {
		return ope045Service.findByExciseId(exciseId);
	}

	@PostMapping("/upload")
	@ResponseBody
	public List<Ope046ExcelVo> upload(@ModelAttribute Ope046FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		return ope045Service.readFileExcel(formVo);
	}

	@PostMapping("/save")
	@ResponseBody
	public Ope045SumVo save(@RequestBody Ope045SumVo sumVo) {
		ope045Service.save(sumVo);
		return sumVo;
	}

	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		Ope044SumVo result = gson.fromJson(dataJson, Ope044SumVo.class);

		List<Ope044Vo> dataList = result.getVoList();

		/* set fileName */
		String fileName = URLEncoder.encode("กระดาษทำการรับสินค้าสำเร็จรูป", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = ope04Service.exportOpo044(dataList);
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
