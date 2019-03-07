package th.go.excise.ims.ta.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.service.CreatePaperServiceService;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.LeftInStockServiceVo;
import th.go.excise.ims.ta.vo.MemberStatusServiceVo;
import th.go.excise.ims.ta.vo.PriceServiceVo;
import th.go.excise.ims.ta.vo.QuantityServiceVo;

@Controller
@RequestMapping("/api/ta/create-paper-service")
public class CreatePaperServiceController {
	private static final Logger logger = LoggerFactory.getLogger(CreatePaperServiceController.class);

	private CreatePaperServiceService createPaperServiceService;

	
	@Autowired
	public CreatePaperServiceController(CreatePaperServiceService createPaperServiceService) {
		this.createPaperServiceService = createPaperServiceService;
	}
	
	//startmethodQuantityServiceVo
	@PostMapping("/list-quantityService")
	@ResponseBody
	public DataTableAjax<QuantityServiceVo> listQuantityServiceVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listquantityService");

		DataTableAjax<QuantityServiceVo> response = new DataTableAjax<>();
		try {
			response = createPaperServiceService.GetQuantityServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	@GetMapping("/exportFileQuantityServiceVo")
	@ResponseBody
	public  void exportFileQuantityServiceVo(@ModelAttribute QuantityServiceVo formVo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบด้านปริมาณ", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = createPaperServiceService.exportFileQuantityServiceVo(formVo, response, request);
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
	//Done
	
	//startmethodPriceServiceVo
	@PostMapping("/list-priceServiceVo")
	@ResponseBody
	public DataTableAjax<PriceServiceVo> listPriceServiceVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listpriceServiceVo");

		DataTableAjax<PriceServiceVo> response = new DataTableAjax<>();
		try {
			response = createPaperServiceService.GetPriceServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFilePriceServiceVo")
	@ResponseBody
	public  void exportFilePriceServiceVo(@ModelAttribute PriceServiceVo formVo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = createPaperServiceService.exportFilePriceServiceVo(formVo, response, request);
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
	//Done
	
	//startmethodMemberStatusServiceVo
	@PostMapping("/list-rawMaterialReceiptVo")
	@ResponseBody
	public DataTableAjax<MemberStatusServiceVo> listRawMaterialReceiptVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listMemberStatusServiceVo");

		DataTableAjax<MemberStatusServiceVo> response = new DataTableAjax<>();
		try {
			response = createPaperServiceService.GetMemberStatusServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFileMemberStatusServiceVo")
	@ResponseBody
	public  void exportFileMemberStatusServiceVo(@ModelAttribute MemberStatusServiceVo formVo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบสถานะสมาชิก", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = createPaperServiceService.exportFileMemberStatusServiceVo(formVo, response, request);
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
	
	//Done
	
	//StartMethodLeftInStockServiceVo
	@PostMapping("/list-leftInStockServiceVo")
	@ResponseBody
	public DataTableAjax<LeftInStockServiceVo> listLeftInStockServiceVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listleftInStockServiceVo");

		DataTableAjax<LeftInStockServiceVo> response = new DataTableAjax<>();
		try {
			response = createPaperServiceService.GetLeftInStockServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFileLeftInStockServiceVo")
	@ResponseBody
	public  void exportFileLeftInStockServiceVo(@ModelAttribute LeftInStockServiceVo formVo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจนับสินค้าคงเหลือ", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = createPaperServiceService.exportFileLeftInStockServiceVo(formVo, response, request);
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
