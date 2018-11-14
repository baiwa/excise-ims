package th.co.baiwa.excise.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.AllocatedBudget;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.entity.TimeSet;
import th.co.baiwa.excise.ia.persistence.vo.CheckPaymentExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int068Vo;
import th.co.baiwa.excise.ia.service.CheckPaymentExcelService;
import th.co.baiwa.excise.ia.service.Int068Service;

@Controller
@RequestMapping("api/ia/int068")
public class Int068Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int068Service int068Service;

	@Autowired
	private CheckPaymentExcelService checkPaymentExcelService;

	@PostMapping("/saveAB")
	@ResponseBody
	public CommonMessage<AllocatedBudget> saveAB(@RequestBody AllocatedBudget ab) {
		logger.info("AB_AllocatedBudget: {}", ab.getAllocatedBudget());
		return int068Service.saveAB(ab);
	}

	@PostMapping("/savePU")
	@ResponseBody
	public CommonMessage<PublicUtility> savePU(@RequestBody List<Int068FormVo> vo) {
//		logger.info("PU_AllocatedBudget: {}", vo.getAllocatedBudgetId());

		return int068Service.savePU(vo);
	}

	@Transactional
	@PostMapping("/checkRangeTime")
	@ResponseBody
	public List<TimeSet> checkTime() {
//		Message msg = null;
//		try {

//		} catch (Exception e) {
//			e.printStackTrace();
//			msg = ApplicationCache.getMessage("MSG_00003");
//		}
		return int068Service.checkTime();
	}

	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		CheckPaymentExcelVo result = gson.fromJson(dataJson, CheckPaymentExcelVo.class);
		List<Int068Vo> dataList = result.getInt068ExcelList();

		/* set fileName */
		String fileName = "import_account_list";

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = checkPaymentExcelService.exportInt068(dataList);
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
