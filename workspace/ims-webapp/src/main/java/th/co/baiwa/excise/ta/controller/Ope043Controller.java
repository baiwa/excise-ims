package th.co.baiwa.excise.ta.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope043DataTable;
import th.co.baiwa.excise.ta.service.MaterialsWsService;
import th.co.baiwa.excise.ta.service.Ope04ExcelService;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ope043")
public class Ope043Controller {

	@Autowired
	private MaterialsWsService materialsWsService;

	@Autowired
	private UploadFileExciseService uploadFileExciseService;

	@Autowired
	private Ope04ExcelService ope04Service;

	private final String SESSION_DATA = "SESSION_DATA_OPE043";

	@PostMapping("excel")
	@ResponseBody
	public DataTableAjax<Ope043DataTable> excel(@ModelAttribute Ope041Vo vo, HttpServletRequest httpServletRequest)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		httpServletRequest.getSession().removeAttribute(SESSION_DATA);
		
		List<Ope043DataTable> result = new ArrayList<Ope043DataTable>();
		if ((BeanUtils.isNotEmpty(vo.getStartDate())) && (BeanUtils.isNotEmpty(vo.getEndDate()))) {
			result = materialsWsService.queryExciseIdFromAccDTL(vo.getExciseId(), vo.getType(), vo.getStartDate(),
					vo.getEndDate());

			if (!result.isEmpty()) {
				httpServletRequest.getSession().setAttribute(SESSION_DATA, result);
			}
		}

		if (vo.getFileExel() != null) {
			List<Ope041Vo> fuList = new ArrayList<>();
			Ope041Vo fu = new Ope041Vo();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(vo.getFileExel());
			for (String[] stringArr : ListfileEx) {
				fu = new Ope041Vo();
				for (int i = 0; i < stringArr.length; i++) {
					if (i == 0) {
						fu.setColumn1(stringArr[i]);
					} else if (i == 1) {
						fu.setColumn2(stringArr[i]);
					} else if (i == 2) {
						fu.setColumn3(stringArr[i]);
					} else if (i == 3) {
						fu.setColumn4(stringArr[i]);
					} else if (i == 4) {
						fu.setColumn5(stringArr[i]);
					}
				}
				fuList.add(fu);
			}
			result = materialsWsService.sumData(fuList, vo);
		}

		DataTableAjax<Ope043DataTable> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(result.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(result.size()));
		dataTableAjax.setData(result);
		return dataTableAjax;
	}

	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope043DataTable> allData) {
		try {
			materialsWsService.insertMaterialsWsService(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Ope043DataTable> resultList = (List<Ope043DataTable>) httpServletRequest.getSession()
				.getAttribute(SESSION_DATA);

		/* set fileName */
		String fileName = URLEncoder.encode("กระดาษทำการตรวจนับวัตถุดิบคงเหลือ", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = ope04Service.exportOpe043(resultList);
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
