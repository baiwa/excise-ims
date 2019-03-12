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
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.service.ProductPaperBalanceMaterialService;
import th.go.excise.ims.ta.service.ProductPaperInputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperInputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperOutputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperOutputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperRelationProducedGoodsService;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;

@Controller
@RequestMapping("/api/ta/product-paper")
public class ProductPaperController {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperController.class);

	private ProductPaperInputMaterialService productPaperInputMaterialService;
	private ProductPaperOutputMaterialService productPaperOutputMaterialService;
	private ProductPaperBalanceMaterialService productPaperBalanceMaterialService;
	private ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService;
	private ProductPaperInputGoodsService productPaperInputGoodsService;
	private ProductPaperOutputGoodsService productPaperOutputGoodsService;

	@Autowired
	public ProductPaperController(
			ProductPaperInputMaterialService productPaperInputMaterialService,
			ProductPaperOutputMaterialService productPaperOutputMaterialService,
			ProductPaperBalanceMaterialService productPaperBalanceMaterialService,
			ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService,
			ProductPaperInputGoodsService productPaperInputGoodsService,
			ProductPaperOutputGoodsService productPaperOutputGoodsService) 
	{		
		this.productPaperInputMaterialService = productPaperInputMaterialService;
		this.productPaperOutputMaterialService = productPaperOutputMaterialService;
		this.productPaperBalanceMaterialService = productPaperBalanceMaterialService;
		this.productPaperRelationProducedGoodsService =productPaperRelationProducedGoodsService;
		this.productPaperInputGoodsService = productPaperInputGoodsService;
		this.productPaperOutputGoodsService =productPaperOutputGoodsService;
	}

	// TODO ProductPaperInputMaterial
	@PostMapping("/list-product-paper-input-material")
	@ResponseBody
	public DataTableAjax<ProductPaperInputMaterialVo> listProductPaperInputMaterial(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperInputMaterial");

		DataTableAjax<ProductPaperInputMaterialVo> response = new DataTableAjax<>();
		try {
			response = productPaperInputMaterialService.listProductPaperInputMaterial(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-input-material")
	@ResponseBody
	public void exportProductPaperInputMaterial(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {
		logger.info("Export listProductPaperInputMaterial");
		
		String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		byte[] outArray = productPaperInputMaterialService.exportProductPaperInputMaterial();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	@PostMapping("/upload-product-paper-input-material")
	@ResponseBody
	public ResponseData<List<ProductPaperInputMaterialVo>> uploadProductPaperInputMaterial(
			@ModelAttribute ProductPaperInputMaterialVo request) {
		logger.info("Upload listProductPaperInputMaterial");
		ResponseData<List<ProductPaperInputMaterialVo>> responseData = new ResponseData<List<ProductPaperInputMaterialVo>>();
		try {
			responseData.setData(productPaperInputMaterialService.readFileProductPaperInputMaterial(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//TODO ProductPaperOutputMaterial
	@PostMapping("/list-product-paper-output-material")
	@ResponseBody
	public DataTableAjax<ProductPaperOutputMaterialVo> listProductPaperOutputMaterial(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperOutputMaterial");

		DataTableAjax<ProductPaperOutputMaterialVo> response = new DataTableAjax<>();
		try {
			response = productPaperOutputMaterialService.listProductPaperOutputMaterial(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-output-material")
	@ResponseBody
	public void exportProductPaperOutputMaterial(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {
		logger.info("Export listProductPaperOutputMaterial");
		String fileName = URLEncoder.encode("ตรวจสอบจ่ายวัตถุดิบ", "UTF-8");
		byte[] outArray = productPaperOutputMaterialService.exportProductPaperOutputMaterial();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}
	
	// TODO ProductPaperBalanceMaterial
	@PostMapping("/list-product-paper-balance-material")
	@ResponseBody
	public DataTableAjax<ProductPaperBalanceMaterialVo> listProductPaperBalanceMaterial(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperBalanceMaterial");

		DataTableAjax<ProductPaperBalanceMaterialVo> response = new DataTableAjax<>();
		try {
			response = productPaperBalanceMaterialService.listProductPaperBalanceMaterial(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-balance-material")
	@ResponseBody
	public void exportProductPaperBalanceMaterial(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listProductPaperBalanceMaterial");

		String fileName = URLEncoder.encode("ตรวจนับวัตถุดิบคงเหลือ", "UTF-8");
		byte[] outArray = productPaperBalanceMaterialService.exportProductPaperBalanceMaterial();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}
	
//TODO ProductPaperRelationProducedGoods
	@PostMapping("/list-product-paper-relation-produced-goods")
	@ResponseBody
	public DataTableAjax<ProductPaperRelationProducedGoodsVo> listProductPaperRelationProducedGoods(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperRelationProducedGoods");

		DataTableAjax<ProductPaperRelationProducedGoodsVo> response = new DataTableAjax<>();
		try {
			response = productPaperRelationProducedGoodsService.listProductPaperRelationProducedGoods(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-relation-produced-goods")
	@ResponseBody
	public void exportProductPaperRelationProducedGoods(HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws Exception {

		logger.info("Export listProductPaperRelationProducedGoods");

		String fileName = URLEncoder.encode("ตรวจหาความสัมพันธ์การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป", "UTF-8");
		byte[] outArray = productPaperRelationProducedGoodsService.exportProductPaperRelationProducedGoods();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

//TODO ProductPaperInputGoods
	@PostMapping("/list-product-paper-input-goods")
	@ResponseBody
	public DataTableAjax<ProductPaperInputGoodsVo> listProductPaperInputGoods(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperInputGoods");

		DataTableAjax<ProductPaperInputGoodsVo> response = new DataTableAjax<>();
		try {
			response = productPaperInputGoodsService.listProductPaperInputGoods(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-input-goods")
	@ResponseBody
	public void exportProductPaperInputGoods(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listProductPaperInputGoods");
		
		String fileName = URLEncoder.encode("ตรวจสอบการรับสินค้าสำเร็จรูป", "UTF-8");
		byte[] outArray = productPaperInputGoodsService.exportProductPaperInputGoods();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

//TODO ProductPaperOutputGoods
	@PostMapping("/list-product-paper-output-goods")
	@ResponseBody
	public DataTableAjax<ProductPaperOutputGoodsVo> listProductPaperOutputGoods(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperOutputGoods");

		DataTableAjax<ProductPaperOutputGoodsVo> response = new DataTableAjax<>();
		try {
			response = productPaperOutputGoodsService.listProductPaperOutputGoods(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-output-goods")
	@ResponseBody
	public void exportProductPaperOutputGoods(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listRawMaterialReceive !!");

		String fileName = URLEncoder.encode("ตรวจสอบการจ่ายสินค้าสำเร็จรูป", "UTF-8");
		byte[] outArray = productPaperOutputGoodsService.exportProductPaperOutputGoods();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}
	
	/*
	// TODO RawMaterialTaxBreak
	@PostMapping("/list-raw-material-tax-break")
	@ResponseBody
	public DataTableAjax<CppRawMaterialTaxBreakVo> listRawMaterialTaxBreak(@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialTaxBreak");

		DataTableAjax<CppRawMaterialTaxBreakVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRawMaterialTaxBreak(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-raw-material-tax-break-export")
	@ResponseBody
	public void exportRawMaterialTaxBreak(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialTaxBreak export!!");

		 set fileName 
		String fileName = URLEncoder.encode("ตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษีที่ยื่นต่อกรมสรรพสามิต (ภส. ๐๕-๐๒)",
				"UTF-8");
		 write it as an excel attachment 
		byte[] outArray = createPaperProductService.exportRawMaterialTaxBreak();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

		try (OutputStream outStream = response.getOutputStream();) {
			outStream.write(outArray);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	// TODO UnitPrice
	@PostMapping("/list-unit-price")
	@ResponseBody
	public DataTableAjax<CppUnitPriceVo> listUnitPrice(@RequestBody CreatePaperFormVo request) {
		logger.info("listUnitPrice");

		DataTableAjax<CppUnitPriceVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listUnitPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-unit-price-export")
	@ResponseBody
	public void exportUnitPrice(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listUnitPrice export!!");

		 set fileName 
		String fileName = URLEncoder.encode("ตรวจสอบราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี", "UTF-8");
		 write it as an excel attachment 
		byte[] outArray = createPaperProductService.exportUnitPrice();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

		try (OutputStream outStream = response.getOutputStream();) {
			outStream.write(outArray);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

//TODO
	------CheckPrice-----
	@PostMapping("/list-check-price")
	@ResponseBody
	public DataTableAjax<CppCheckPriceVo> listCheckPrice(@RequestBody CreatePaperFormVo request) {
		logger.info("listCheckPrice");

		DataTableAjax<CppCheckPriceVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listCheckPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-check-price-export")
	@ResponseBody
	public void exportCheckPrice(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		 set fileName 
		String fileName = URLEncoder.encode("ตรวจสอบด้านราคา", "UTF-8");
		 write it as an excel attachment 
		byte[] outArray = createPaperProductService.exportCheckPrice();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

		try (OutputStream outStream = response.getOutputStream();) {
			outStream.write(outArray);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

//TODO PayForeignFinishedGoods
	@PostMapping("/list-pay-foreign-finished-goods")
	@ResponseBody
	public DataTableAjax<CppPayForeignFinishedGoodsVo> listPayForeignFinishedGoods(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listPayForeignFinishedGoods");

		DataTableAjax<CppPayForeignFinishedGoodsVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listPayForeignFinishedGoods(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-pay-foreign-finished-goods-export")
	@ResponseBody
	public void exportPayForeignFinishedGoods(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		 set fileName 
		String fileName = URLEncoder.encode("จ่ายสินค้าสำเร็จรูปต่างประเทศ", "UTF-8");
		 write it as an excel attachment 
		byte[] outArray = createPaperProductService.exportPayForeignFinishedGoods();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

		try (OutputStream outStream = response.getOutputStream();) {
			outStream.write(outArray);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	------Tax-----
	@PostMapping("/list-tax")
	@ResponseBody
	public DataTableAjax<CppTaxVo> listTax(@RequestBody CreatePaperFormVo request) {
		logger.info("listTax");

		DataTableAjax<CppTaxVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listTax(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-tax-export")
	@ResponseBody
	public void exportTax(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		 set fileName 
		String fileName = URLEncoder.encode("คำนวณภาษีที่ต้องชำระเพิ่ม", "UTF-8");
		 write it as an excel attachment 
		byte[] outArray = createPaperProductService.exportTax();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

		try (OutputStream outStream = response.getOutputStream();) {
			outStream.write(outArray);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}*/
}