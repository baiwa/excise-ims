package th.co.baiwa.excise.ta.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.Gson;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope048ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048Vo;
import th.co.baiwa.excise.ta.service.Ope048Service;
import th.co.baiwa.excise.ta.service.Ope04ExcelService;

@Controller
@RequestMapping("api/ta/opo048")
public class Ope048Controller {

	@Autowired
	private Ope04ExcelService ope04Service;

	@Autowired
	private Ope048Service ope048Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope048Vo> findAll(@RequestBody Ope048FormVo formVo) {
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

	@PostMapping("/upload")
	@ResponseBody
	public List<Ope048ExcelVo> upload(@ModelAttribute Ope048FormVo formVo) throws EncryptedDocumentException,
			InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		return ope048Service.readFileExcel(formVo);
	}

	/*
	 * @PostMapping("/save")
	 * 
	 * @ResponseBody public List<Ope046Vo> save(@RequestBody List<Ope046Vo> vo) {
	 * ope048Service.save(vo); return vo; }
	 */

	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		Ope048SumVo result = gson.fromJson(dataJson, Ope048SumVo.class);

		List<Ope048Vo> dataList = result.getVoList();

		/* set fileName */
		String fileName = URLEncoder.encode("กระดาษทำการตรวจสอบด้านราคา", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = ope04Service.exportOpo048(dataList);
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
