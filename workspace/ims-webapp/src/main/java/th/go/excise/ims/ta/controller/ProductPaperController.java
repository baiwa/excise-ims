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
import th.go.excise.ims.ta.service.ProductPaperInformPriceService;
import th.go.excise.ims.ta.service.ProductPaperInputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperInputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperOutputForeignGoodsService;
import th.go.excise.ims.ta.service.ProductPaperOutputGoodsService;
import th.go.excise.ims.ta.service.ProductPaperOutputMaterialService;
import th.go.excise.ims.ta.service.ProductPaperReduceTaxService;
import th.go.excise.ims.ta.service.ProductPaperRelationProducedGoodsService;
import th.go.excise.ims.ta.service.ProductPaperTaxAmtAdditionalService;
import th.go.excise.ims.ta.service.ProductPaperUnitPriceReduceTaxService;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperInformPriceVo;
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputForeignGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperReduceTaxVo;
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;
import th.go.excise.ims.ta.vo.ProductPaperTaxAmtAdditionalVo;
import th.go.excise.ims.ta.vo.ProductPaperUnitPriceReduceTaxVo;

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
	private ProductPaperReduceTaxService productPaperReduceTaxService;
	private ProductPaperUnitPriceReduceTaxService productPaperUnitPriceReduceTaxService;
	private ProductPaperInformPriceService productPaperInformPriceService;
	private ProductPaperOutputForeignGoodsService productPaperOutputForeignGoodsService;
	private ProductPaperTaxAmtAdditionalService productPaperTaxAmtAdditionalService;

	@Autowired
	public ProductPaperController(ProductPaperInputMaterialService productPaperInputMaterialService,
			ProductPaperOutputMaterialService productPaperOutputMaterialService,
			ProductPaperBalanceMaterialService productPaperBalanceMaterialService,
			ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService,
			ProductPaperInputGoodsService productPaperInputGoodsService,
			ProductPaperOutputGoodsService productPaperOutputGoodsService,
			ProductPaperReduceTaxService productPaperReduceTaxService,
			ProductPaperUnitPriceReduceTaxService productPaperUnitPriceReduceTaxService,
			ProductPaperInformPriceService productPaperInformPriceService,
			ProductPaperOutputForeignGoodsService productPaperOutputForeignGoodsService,
			ProductPaperTaxAmtAdditionalService productPaperTaxAmtAdditionalService) {
		this.productPaperInputMaterialService = productPaperInputMaterialService;
		this.productPaperOutputMaterialService = productPaperOutputMaterialService;
		this.productPaperBalanceMaterialService = productPaperBalanceMaterialService;
		this.productPaperRelationProducedGoodsService = productPaperRelationProducedGoodsService;
		this.productPaperInputGoodsService = productPaperInputGoodsService;
		this.productPaperOutputGoodsService = productPaperOutputGoodsService;
		this.productPaperReduceTaxService = productPaperReduceTaxService;
		this.productPaperUnitPriceReduceTaxService = productPaperUnitPriceReduceTaxService;
		this.productPaperInformPriceService = productPaperInformPriceService;
		this.productPaperOutputForeignGoodsService = productPaperOutputForeignGoodsService;
		this.productPaperTaxAmtAdditionalService = productPaperTaxAmtAdditionalService;
	}

	// TODO ProductPaperInputMaterial
	@PostMapping("/list-product-paper-input-material")
	@ResponseBody
	public DataTableAjax<ProductPaperInputMaterialVo> listProductPaperInputMaterial(
			@RequestBody CreatePaperFormVo request) {
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
	public DataTableAjax<ProductPaperOutputMaterialVo> listProductPaperOutputMaterial(
			@RequestBody CreatePaperFormVo request) {
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

	@PostMapping("/upload-product-paper-output-material")
	@ResponseBody
	public ResponseData<List<ProductPaperOutputMaterialVo>> uploadProductPaperOutputMaterial(
			@ModelAttribute ProductPaperOutputMaterialVo request) {
		logger.info("Upload listProductPaperOutputMaterial");
		ResponseData<List<ProductPaperOutputMaterialVo>> responseData = new ResponseData<List<ProductPaperOutputMaterialVo>>();
		try {
			responseData.setData(productPaperOutputMaterialService.readFileProductPaperOutputMaterial(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	// TODO ProductPaperBalanceMaterial
	@PostMapping("/list-product-paper-balance-material")
	@ResponseBody
	public DataTableAjax<ProductPaperBalanceMaterialVo> listProductPaperBalanceMaterial(
			@RequestBody CreatePaperFormVo request) {
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

	@PostMapping("/upload-product-paper-balance-material")
	@ResponseBody
	public ResponseData<List<ProductPaperBalanceMaterialVo>> uploadProductPaperBalanceMaterial(
			@ModelAttribute ProductPaperBalanceMaterialVo request) {
		logger.info("Upload listProductPaperBalanceMaterial");
		ResponseData<List<ProductPaperBalanceMaterialVo>> responseData = new ResponseData<List<ProductPaperBalanceMaterialVo>>();
		try {
			responseData.setData(productPaperBalanceMaterialService.readFileProductPaperBalanceMaterial(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
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

	@PostMapping("/upload-product-paper-relation-produced-goods")
	@ResponseBody
	public ResponseData<List<ProductPaperRelationProducedGoodsVo>> uploadProductPaperRelationProducedGoods(
			@ModelAttribute ProductPaperRelationProducedGoodsVo request) {
		logger.info("Upload listProductPaperRelationProducedGoods");
		ResponseData<List<ProductPaperRelationProducedGoodsVo>> responseData = new ResponseData<List<ProductPaperRelationProducedGoodsVo>>();
		try {
			responseData.setData(
					productPaperRelationProducedGoodsService.readFileProductPaperRelationProducedGoods(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
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

	@PostMapping("/upload-product-paper-input-goods")
	@ResponseBody
	public ResponseData<List<ProductPaperInputGoodsVo>> uploadProductPaperInputGoods(
			@ModelAttribute ProductPaperInputGoodsVo request) {
		logger.info("Upload listProductPaperInputGoods");
		ResponseData<List<ProductPaperInputGoodsVo>> responseData = new ResponseData<List<ProductPaperInputGoodsVo>>();
		try {
			responseData.setData(productPaperInputGoodsService.readFileProductPaperInputGoods(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//TODO ProductPaperOutputGoods
	@PostMapping("/list-product-paper-output-goods")
	@ResponseBody
	public DataTableAjax<ProductPaperOutputGoodsVo> listProductPaperOutputGoods(
			@RequestBody CreatePaperFormVo request) {
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

		logger.info("Export listProductPaperOutputGoods !!");

		String fileName = URLEncoder.encode("ตรวจสอบการจ่ายสินค้าสำเร็จรูป", "UTF-8");
		byte[] outArray = productPaperOutputGoodsService.exportProductPaperOutputGoods();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

	@PostMapping("/upload-product-paper-output-goods")
	@ResponseBody
	public ResponseData<List<ProductPaperOutputGoodsVo>> uploadProductPaperOutputGoods(
			@ModelAttribute ProductPaperOutputGoodsVo request) {
		logger.info("Upload listRawMaterialReceive");
		ResponseData<List<ProductPaperOutputGoodsVo>> responseData = new ResponseData<List<ProductPaperOutputGoodsVo>>();
		try {
			responseData.setData(productPaperOutputGoodsService.readFileProductPaperOutputGoods(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	// TODO ProductPaperReduceTax
	@PostMapping("/list-product-paper-reduce-tax")
	@ResponseBody
	public DataTableAjax<ProductPaperReduceTaxVo> listProductPaperReduceTax(@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperReduceTax");

		DataTableAjax<ProductPaperReduceTaxVo> response = new DataTableAjax<>();
		try {
			response = productPaperReduceTaxService.listProductPaperReduceTax(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-reduce-tax")
	@ResponseBody
	public void exportRawMaterialTaxBreak(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listRawMaterialTaxBreak !!");

		String fileName = URLEncoder.encode("ตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษีที่ยื่นต่อกรมสรรพสามิต (ภส. ๐๕-๐๒)",
				"UTF-8");
		byte[] outArray = productPaperReduceTaxService.exportProductPaperReduceTax();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

	@PostMapping("/upload-product-paper-reduce-tax")
	@ResponseBody
	public ResponseData<List<ProductPaperReduceTaxVo>> uploadProductPaperReduceTax(
			@ModelAttribute ProductPaperReduceTaxVo request) {
		logger.info("Upload listRawMaterialTaxBreak");
		ResponseData<List<ProductPaperReduceTaxVo>> responseData = new ResponseData<List<ProductPaperReduceTaxVo>>();
		try {
			responseData.setData(productPaperReduceTaxService.readFileProductPaperReduceTax(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	// TODO ProductPaperUnitPriceReduceTax
	@PostMapping("/list-product-paper-unit-price-reduce-tax")
	@ResponseBody
	public DataTableAjax<ProductPaperUnitPriceReduceTaxVo> listProductPaperUnitPriceReduceTax(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperUnitPriceReduceTax");

		DataTableAjax<ProductPaperUnitPriceReduceTaxVo> response = new DataTableAjax<>();
		try {
			response = productPaperUnitPriceReduceTaxService.listProductPaperUnitPriceReduceTax(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-unit-price-reduce-tax")
	@ResponseBody
	public void exportProductPaperUnitPriceReduceTax(HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws Exception {
		logger.info("Export listProductPaperUnitPriceReduceTax !!");
		String fileName = URLEncoder.encode("ตรวจสอบราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี", "UTF-8");
		byte[] outArray = productPaperUnitPriceReduceTaxService.exportProductPaperUnitPriceReduceTax();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

	@PostMapping("/upload-product-paper-unit-price-reduce-tax")
	@ResponseBody
	public ResponseData<List<ProductPaperUnitPriceReduceTaxVo>> uploadProductPaperUnitPriceReduceTax(
			@ModelAttribute ProductPaperUnitPriceReduceTaxVo request) {
		logger.info("Upload listProductPaperUnitPriceReduceTax");
		ResponseData<List<ProductPaperUnitPriceReduceTaxVo>> responseData = new ResponseData<List<ProductPaperUnitPriceReduceTaxVo>>();
		try {
			responseData.setData(productPaperUnitPriceReduceTaxService.readFileProductPaperUnitPriceReduceTax(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//TODO ProductPaperInformPrice
	@PostMapping("/list-product-paper-inform-price")
	@ResponseBody
	public DataTableAjax<ProductPaperInformPriceVo> listProductPaperInformPrice(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperInformPrice");

		DataTableAjax<ProductPaperInformPriceVo> response = new DataTableAjax<>();
		try {
			response = productPaperInformPriceService.listProductPaperInformPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-inform-price")
	@ResponseBody
	public void exportProductPaperInformPrice(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listProductPaperInformPrice !!");

		String fileName = URLEncoder.encode("ตรวจสอบด้านราคา", "UTF-8");
		byte[] outArray = productPaperInformPriceService.exportProductPaperInformPrice();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	@PostMapping("/upload-product-paper-inform-price")
	@ResponseBody
	public ResponseData<List<ProductPaperInformPriceVo>> uploadProductPaperInformPrice(
			@ModelAttribute ProductPaperInformPriceVo request) {
		logger.info("Upload listProductPaperInformPrice");
		ResponseData<List<ProductPaperInformPriceVo>> responseData = new ResponseData<List<ProductPaperInformPriceVo>>();
		try {
			responseData.setData(productPaperInformPriceService.readFileProductPaperInformPrice(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//TODO ProductPaperOutputForeignGoods
	@PostMapping("/list-product-paper-output-foreign-goods")
	@ResponseBody
	public DataTableAjax<ProductPaperOutputForeignGoodsVo> listProductPaperOutputForeignGoods(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperOutputForeignGoods");

		DataTableAjax<ProductPaperOutputForeignGoodsVo> response = new DataTableAjax<>();
		try {
			response = productPaperOutputForeignGoodsService.listProductPaperOutputForeignGoods(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-output-foreign-goods")
	@ResponseBody
	public void exportPayForeignFinishedGoods(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listProductPaperOutputForeignGoods !!");
		String fileName = URLEncoder.encode("จ่ายสินค้าสำเร็จรูปต่างประเทศ", "UTF-8");

		byte[] outArray = productPaperOutputForeignGoodsService.exportPayForeignFinishedGoods();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	@PostMapping("/upload-product-paper-output-foreign-goods")
	@ResponseBody
	public ResponseData<List<ProductPaperOutputForeignGoodsVo>> uploadProductPaperOutputForeignGoods(
			@ModelAttribute ProductPaperOutputForeignGoodsVo request) {
		logger.info("Upload listProductPaperOutputForeignGoods");
		ResponseData<List<ProductPaperOutputForeignGoodsVo>> responseData = new ResponseData<List<ProductPaperOutputForeignGoodsVo>>();
		try {
			responseData.setData(productPaperOutputForeignGoodsService.readFileProductPaperOutputForeignGoods(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	// TODO productPaperTaxAmtAdditional
	@PostMapping("/list-product-paper-tax-amt-additional")
	@ResponseBody
	public DataTableAjax<ProductPaperTaxAmtAdditionalVo> listProductPaperTaxAmtAdditional(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listProductPaperTaxAmtAdditional");

		DataTableAjax<ProductPaperTaxAmtAdditionalVo> response = new DataTableAjax<>();
		try {
			response = productPaperTaxAmtAdditionalService.listProductPaperTaxAmtAdditional(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/export-product-paper-tax-amt-additional")
	@ResponseBody
	public void exportProductPaperTaxAmtAdditional(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("Export listProductPaperTaxAmtAdditional !!");

		String fileName = URLEncoder.encode("คำนวณภาษีที่ต้องชำระเพิ่ม", "UTF-8");
		byte[] outArray = productPaperTaxAmtAdditionalService.exportProductPaperTaxAmtAdditional();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	@PostMapping("/upload-product-paper-tax-amt-additional")
	@ResponseBody
	public ResponseData<List<ProductPaperTaxAmtAdditionalVo>> uploadProductPaperOutputForeignGoods(
			@ModelAttribute ProductPaperTaxAmtAdditionalVo request) {
		logger.info("Upload listProductPaperOutputForeignGoods");
		ResponseData<List<ProductPaperTaxAmtAdditionalVo>> responseData = new ResponseData<List<ProductPaperTaxAmtAdditionalVo>>();
		try {
			responseData.setData(productPaperTaxAmtAdditionalService.readFileProductPaperTaxAmtAdditional(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}