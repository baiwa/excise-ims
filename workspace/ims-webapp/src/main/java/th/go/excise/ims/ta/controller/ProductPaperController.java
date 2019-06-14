package th.go.excise.ims.ta.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.go.excise.ims.ta.service.AbstractProductPaperService;
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
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;

@Controller
@RequestMapping("/api/ta/product-paper")
public class ProductPaperController {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperController.class);

	private Map<String, AbstractProductPaperService> productPaperServiceMap = new HashMap<>();

	@Autowired
	public ProductPaperController(ProductPaperInputMaterialService productPaperInputMaterialService, ProductPaperOutputMaterialService productPaperOutputMaterialService, ProductPaperBalanceMaterialService productPaperBalanceMaterialService,
			ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService, ProductPaperInputGoodsService productPaperInputGoodsService, ProductPaperOutputGoodsService productPaperOutputGoodsService, ProductPaperReduceTaxService productPaperReduceTaxService,
			ProductPaperUnitPriceReduceTaxService productPaperUnitPriceReduceTaxService, ProductPaperInformPriceService productPaperInformPriceService, ProductPaperOutputForeignGoodsService productPaperOutputForeignGoodsService,
			ProductPaperTaxAmtAdditionalService productPaperTaxAmtAdditionalService) {
		productPaperServiceMap.put("input-material", productPaperInputMaterialService);
		productPaperServiceMap.put("output-material", productPaperOutputMaterialService);
		productPaperServiceMap.put("balance-material", productPaperBalanceMaterialService);
		productPaperServiceMap.put("relation-produced-goods", productPaperRelationProducedGoodsService);
		productPaperServiceMap.put("input-goods", productPaperInputGoodsService);
		productPaperServiceMap.put("output-goods", productPaperOutputGoodsService);
		productPaperServiceMap.put("reduce-tax", productPaperReduceTaxService);
		productPaperServiceMap.put("unit-price-reduce-tax", productPaperUnitPriceReduceTaxService);
		productPaperServiceMap.put("inform-price", productPaperInformPriceService);
		productPaperServiceMap.put("output-foreign-goods", productPaperOutputForeignGoodsService);
		productPaperServiceMap.put("tax-amt-additional", productPaperTaxAmtAdditionalService);
	}

	@PostMapping("/inquiry-{productPaperType}")
	@ResponseBody
	public DataTableAjax<?> inquiryData(@PathVariable("productPaperType") String productPaperType, @RequestBody ProductPaperFormVo formVo) {
		logger.info("inquiryData productPaperType={}", productPaperType);

		DataTableAjax<Object> dataTableAjax = new DataTableAjax<>();
		try {
			AbstractProductPaperService<Object> service = productPaperServiceMap.get(productPaperType);
			List<Object> voList = service.inquiry(formVo);
			dataTableAjax.setData(voList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return dataTableAjax;
	}

	@PostMapping("/export-{productPaperType}")
	@ResponseBody
	public void exportData(@PathVariable("productPaperType") String productPaperType, @ModelAttribute ProductPaperFormVo formVo, HttpServletResponse response) throws IOException {
		logger.info("exportData productPaperType={}, paperPrNumber={}", productPaperType, formVo.getPaperPrNumber());

		// String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		String fileName = "test";
		AbstractProductPaperService<Object> service = productPaperServiceMap.get(productPaperType);
		byte[] bytes = service.export(formVo);

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(bytes);
		outStream.flush();
		outStream.close();
	}

	@PostMapping("/upload-{productPaperType}")
	@ResponseBody
	public ResponseData<List<ProductPaperInputMaterialVo>> uploadData(@ModelAttribute ProductPaperInputMaterialVo request) {
		logger.info("Upload listProductPaperInputMaterial");
		ResponseData<List<ProductPaperInputMaterialVo>> responseData = new ResponseData<List<ProductPaperInputMaterialVo>>();
		// try {
		// responseData.setData(productPaperInputMaterialService.readFileProductPaperInputMaterial(request));
		// responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
		// responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
		// responseData.setStatus(RESPONSE_STATUS.FAILED);
		// }
		return responseData;
	}

}