package th.go.excise.ims.ta.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

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
import th.go.excise.ims.ta.service.ServicePaperBalanceGoodsService;
import th.go.excise.ims.ta.service.ServicePaperMemberService;
import th.go.excise.ims.ta.service.ServicePaperPricePerUnitService;
import th.go.excise.ims.ta.service.ServicePaperQtyService;
import th.go.excise.ims.ta.service.ServicePaperTaxAmtAdditionalService;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperBalanceGoodsVo;
import th.go.excise.ims.ta.vo.ServicePaperMemberVo;
import th.go.excise.ims.ta.vo.ServicePaperPricePerUnitVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;
import th.go.excise.ims.ta.vo.ServicePaperTaxAmtAdditionalVo;

@Controller
@RequestMapping("/api/ta/create-paper-service")
public class ServicePaperController {
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperController.class);

	private ServicePaperQtyService servicePaperQty;
	private ServicePaperPricePerUnitService servicePaperPricePerUnit;
	private ServicePaperMemberService servicePaperMember;
	private ServicePaperBalanceGoodsService servicePaperBalanceGoods;
	private ServicePaperTaxAmtAdditionalService servicePaperTaxAmtAdditional;

	@Autowired
	public ServicePaperController(ServicePaperQtyService servicePaperQty,
		   ServicePaperPricePerUnitService servicePaperPricePerUnit
		  ,ServicePaperMemberService  servicePaperMember,
		  ServicePaperBalanceGoodsService servicePaperBalanceGoods,
		  ServicePaperTaxAmtAdditionalService servicePaperTaxAmtAdditional) {
		 
		   this.servicePaperMember = servicePaperMember;
		   this.servicePaperQty = servicePaperQty;
		   this.servicePaperPricePerUnit = servicePaperPricePerUnit;
		   this.servicePaperBalanceGoods = servicePaperBalanceGoods;
		   this.servicePaperTaxAmtAdditional = servicePaperTaxAmtAdditional;
	}
	
	//startmethodQuantityServiceVo
	@PostMapping("/list-quantityService")
	@ResponseBody
	public DataTableAjax<ServicePaperQtyVo> listQuantityServiceVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listquantityService");

		DataTableAjax<ServicePaperQtyVo> response = new DataTableAjax<>();
		try {
			response = servicePaperQty.GetQuantityServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	@GetMapping("/exportFileQuantityServiceVo")
	@ResponseBody
	public  void exportFileQuantityServiceVo(  HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบด้านปริมาณ", "UTF-8");
		// write it as an excel attachment
		byte[] outByteStream = servicePaperQty.exportFileQuantityServiceVo();
		response.setContentType("application/octet-stream");
		response.setContentLength(outByteStream.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
	}
	 @PostMapping("/uploedFileQuantityServiceVo")
	 @ResponseBody
	 public List<ServicePaperQtyVo> readFileExcel(@ModelAttribute ServicePaperQtyVo request,
	   HttpServletRequest httpServletRequest) {
	  logger.info("listRawMaterialReceive Upload!!");
	  List<ServicePaperQtyVo> resultList = servicePaperQty.readFileQuantityServiceVo(request);

	  return resultList;
	 }

	//Done
	
	//startmethodPriceServiceVo
	@PostMapping("/list-priceServiceVo")
	@ResponseBody
	public DataTableAjax<ServicePaperPricePerUnitVo> listPriceServiceVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listpriceServiceVo");

		DataTableAjax<ServicePaperPricePerUnitVo> response = new DataTableAjax<>();
		try {
			response = servicePaperPricePerUnit.GetPriceServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFilePriceServiceVo")
	@ResponseBody
	public  void exportFilePriceServiceVo(HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย", "UTF-8");
	
		// write it as an excel attachment
		byte[] outByteStream = servicePaperPricePerUnit.exportFilePriceServiceVo();
		response.setContentType("application/octet-stream");
		response.setContentLength(outByteStream.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
		
	}
	 @PostMapping("/uploedFileServicePaperPricePerUnitVo")
	 @ResponseBody
	 public List<ServicePaperPricePerUnitVo> readFileExcel(@ModelAttribute ServicePaperPricePerUnitVo request,
	   HttpServletRequest httpServletRequest) {
	  logger.info("listRawMaterialReceive Upload!!");
	  List<ServicePaperPricePerUnitVo> resultList = servicePaperPricePerUnit.readFileServicePaperPricePerUnitVo(request);

	  return resultList;
	 }

	//Done
	
	//startmethodMemberStatusServiceVo
	@PostMapping("/list-rawMaterialReceiptVo")
	@ResponseBody
	public DataTableAjax<ServicePaperMemberVo> listRawMaterialReceiptVo(@RequestBody CreatePaperFormVo request) {
		logger.info("listMemberStatusServiceVo");

		DataTableAjax<ServicePaperMemberVo> response = new DataTableAjax<>();
		try {
			response = servicePaperMember.GetMemberStatusServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFileMemberStatusServiceVo")
	@ResponseBody
	public  void exportFileMemberStatusServiceVo(  HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจสอบสถานะสมาชิก", "UTF-8");
		// write it as an excel attachment
		byte[] outByteStream = servicePaperMember.exportFileMemberStatusServiceVo();
		response.setContentType("application/octet-stream");
		response.setContentLength(outByteStream.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
		
	}
	 @PostMapping("/readFileServicePaperMemberVo")
	 @ResponseBody
	 public List<ServicePaperMemberVo> readFileServicePaperMemberVo(@ModelAttribute ServicePaperMemberVo request,
	   HttpServletRequest httpServletRequest) {
	  logger.info("listRawMaterialReceive Upload!!");
	  List<ServicePaperMemberVo> resultList = servicePaperMember.readFileServicePaperMemberVo(request);

	  return resultList;
	 }

	//Done
	
	//StartMethodLeftInStockServiceVo
	@PostMapping("/list-leftInStockServiceVo")
	@ResponseBody
	public DataTableAjax<ServicePaperBalanceGoodsVo> listLeftInStockService(@RequestBody CreatePaperFormVo request) {
		logger.info("listleftInStockServiceVo");

		DataTableAjax<ServicePaperBalanceGoodsVo> response = new DataTableAjax<>();
		try {
			response = servicePaperBalanceGoods.GetLeftInStockServiceVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("/exportFileLeftInStockServiceVo")
	@ResponseBody
	public  void exportFileLeftInStockServiceVo(  HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("บันทึกผลการตรวจนับสินค้าคงเหลือ", "UTF-8");
		// write it as an excel attachment
		byte[] outByteStream = servicePaperBalanceGoods.exportFileLeftInStockServiceVo();		
		response.setContentType("application/octet-stream");
		response.setContentLength(outByteStream.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
		
	}
	 @PostMapping("/readFileServicePaperBalanceGoodsVo")
	 @ResponseBody
	 public List<ServicePaperBalanceGoodsVo> readFileServicePaperBalanceGoodsVo(@ModelAttribute ServicePaperBalanceGoodsVo request,
	   HttpServletRequest httpServletRequest) {
	  logger.info("readFileServicePaperBalanceGoodsVo Upload!!");
	  List<ServicePaperBalanceGoodsVo> resultList = servicePaperBalanceGoods.readFileServicePaperMemberVo(request);

	  return resultList;
	 }
	
	@PostMapping("/list-servicePaperTaxAmtAdditional")
	@ResponseBody
	public DataTableAjax<ServicePaperTaxAmtAdditionalVo> listServicePaperTaxAmtAdditional(@RequestBody CreatePaperFormVo request) {
		logger.info("ServicePaperTaxAmtAdditionalVo");

		DataTableAjax<ServicePaperTaxAmtAdditionalVo> response = new DataTableAjax<>();
		try {
			response = servicePaperTaxAmtAdditional.GetlistServicePaperTaxAmtAdditionalVo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/exportFileServicePaperTaxAmtAdditional")
	@ResponseBody
	public  void exportFileServicePaperTaxAmtAdditional(  HttpServletResponse response,HttpServletRequest request) throws Exception {
		String fileName = URLEncoder.encode("ตารางการคำนวณภาษีที่ต้องชำระเพิ่ม", "UTF-8");
		// write it as an excel attachment
		byte[] outByteStream = servicePaperTaxAmtAdditional.exportFinishedGoodsReceive();		
		response.setContentType("application/octet-stream");
		response.setContentLength(outByteStream.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
		
	}
	 @PostMapping("/readFileServicePaperTaxAmtAdditionalVo")
	 @ResponseBody
	 public List<ServicePaperTaxAmtAdditionalVo> readFileServicePaperTaxAmtAdditionalVo(@ModelAttribute ServicePaperTaxAmtAdditionalVo request,
	   HttpServletRequest httpServletRequest) {
	  logger.info("readFileServicePaperBalanceGoodsVo Upload!!");
	  List<ServicePaperTaxAmtAdditionalVo> resultList = servicePaperTaxAmtAdditional.readFileServicePaperTaxAmtAdditionalVo(request);

	  return resultList;
	 }


  
}
