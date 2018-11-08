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
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.ta.service.Ope041Service;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;
import th.co.baiwa.excise.ta.service.ReceiveRmatWsService;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ope041")
public class Ope041Controller {

	@Autowired
	private ReceiveRmatWsService receiveRmatWsService;

	@Autowired
	private Ope041Service ope041Service;

	@Autowired
	private UploadFileExciseService uploadFileExciseService;

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;

	private final String SESSION_DATA = "SESSION_DATA_OPE041";

	@PostMapping("excel")
	@ResponseBody
	public DataTableAjax<Ope041DataTable> excel(@ModelAttribute Ope041Vo vo, HttpServletRequest httpServletRequest)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<Ope041DataTable> result = new ArrayList<Ope041DataTable>();
		if ((BeanUtils.isNotEmpty(vo.getStartDate())) && (BeanUtils.isNotEmpty(vo.getEndDate()))) {
			result = planWorksheetHeaderService.queryExciseIdFromAccDTL(vo.getExciseId(), vo.getType(),
					vo.getStartDate(), vo.getEndDate());
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
					} else if (i == 5) {
						fu.setColumn6(stringArr[i]);
					}
				}
				fuList.add(fu);
			}
			result = planWorksheetHeaderService.sumData(fuList, vo);
		}

		DataTableAjax<Ope041DataTable> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(result.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(result.size()));
		dataTableAjax.setData(result);
		return dataTableAjax;
	}

	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope041DataTable> allData) {
		try {
			receiveRmatWsService.insertReceiveRmatWsService(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Ope041DataTable> resultList = (List<Ope041DataTable>) httpServletRequest.getSession().getAttribute(SESSION_DATA);

		/* set fileName */
		String fileName = URLEncoder.encode("กระดาษทำการรับวัตถุดิบ", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = ope041Service.export(resultList);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
		
		httpServletRequest.getSession().removeAttribute(SESSION_DATA);
	}

}
