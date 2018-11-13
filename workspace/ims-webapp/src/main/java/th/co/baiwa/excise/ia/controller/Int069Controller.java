package th.co.baiwa.excise.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.persistence.vo.CheckPaymentExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int069FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int069Vo;
import th.co.baiwa.excise.ia.service.CheckPaymentExcelService;
import th.co.baiwa.excise.ia.service.Int069Service;

@Controller
@RequestMapping("api/ia/int069")
public class Int069Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CheckPaymentExcelService checkPaymentExcelService;

	@Autowired
	private Int069Service int069Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<TransferList> filter(@ModelAttribute Int069FormVo en) {
		logger.info("Filter BudgetList Int069!!");
		return int069Service.filterByform(en);
	}

	@PostMapping("/delete")
	@ResponseBody
	public Message delete(@RequestBody TransferList vo) {
		Message msg = ApplicationCache.getMessage("MSG_00003");

		try {
			int069Service.delete(vo);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			msg = ApplicationCache.getMessage("MSG_00003");
		}

		return msg;
	}

	@PostMapping("/export")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {

		Gson gson = new Gson();
		CheckPaymentExcelVo result = gson.fromJson(dataJson, CheckPaymentExcelVo.class);
		List<Int069Vo> dataList = result.getInt069ExcelList();

		/* set fileName */
		String fileName = URLEncoder.encode("บันทึกข้อมูลรับโอนเงิน", "UTF-8");

		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = checkPaymentExcelService.exportInt069(dataList);
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
