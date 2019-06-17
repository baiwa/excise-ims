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
import th.go.excise.ims.ta.service.AbstractServicePaperService;
import th.go.excise.ims.ta.service.ServicePaperBalanceGoodsService;
import th.go.excise.ims.ta.service.ServicePaperMemberService;
import th.go.excise.ims.ta.service.ServicePaperPricePerUnitService;
import th.go.excise.ims.ta.service.ServicePaperQtyService;
import th.go.excise.ims.ta.service.ServicePaperTaxAmtAdditionalService;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;

@Controller
@RequestMapping("/api/ta/service-paper")
public class ServicePaperController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperController.class);
	
	private Map<String, AbstractServicePaperService> servicePaperServiceMap = new HashMap<>();
	
	@Autowired
	public ServicePaperController(
			ServicePaperQtyService servicePaperQtyService,
			ServicePaperPricePerUnitService servicePaperPricePerUnitService,
			ServicePaperMemberService servicePaperMemberService,
			ServicePaperBalanceGoodsService servicePaperBalanceGoodsService,
	        ServicePaperTaxAmtAdditionalService servicePaperTaxAmtAdditionalService) {
		servicePaperServiceMap.put("qty", servicePaperQtyService);
		servicePaperServiceMap.put("price-per-unit", servicePaperPricePerUnitService);
		servicePaperServiceMap.put("member", servicePaperMemberService);
		servicePaperServiceMap.put("balance-goods", servicePaperBalanceGoodsService);
		servicePaperServiceMap.put("tax-amt-additional", servicePaperTaxAmtAdditionalService);
	}
	
	@PostMapping("/inquiry-{servicePaperType}")
	@ResponseBody
	public DataTableAjax<?> inquiryData(@PathVariable("servicePaperType") String servicePaperType, @RequestBody ServicePaperFormVo formVo) {
		logger.info("inquiryData servicePaperType={}", servicePaperType);
		
		DataTableAjax<Object> dataTableAjax = new DataTableAjax<>();
		try {
			AbstractServicePaperService<Object> service = servicePaperServiceMap.get(servicePaperType);
			List<Object> voList = service.inquiry(formVo);
			dataTableAjax.setData(voList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return dataTableAjax;
	}
	
	@PostMapping("/export-{servicePaperType}")
	@ResponseBody
	public void exportData(@PathVariable("servicePaperType") String servicePaperType, @ModelAttribute ServicePaperFormVo formVo, HttpServletResponse response) throws IOException {
		logger.info("exportData servicePaperType={}, paperSvNumber={}", servicePaperType, formVo.getPaperSvNumber());

		//String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		String fileName = "test";
		AbstractServicePaperService<Object> service = servicePaperServiceMap.get(servicePaperType);
		byte[] bytes = service.export(formVo);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(bytes);
		outStream.flush();
		outStream.close();
	}
	
	@PostMapping("/upload-{servicePaperType}")
	@ResponseBody
	public ResponseData<DataTableAjax<?>> uploadData(@PathVariable("servicePaperType") String servicePaperType, @ModelAttribute ServicePaperFormVo formVo) {
		logger.info("Upload listProductPaperInputMaterial");
		ResponseData<DataTableAjax<?>> responseData = new ResponseData<DataTableAjax<?>>();
		//try {
		//	responseData.setData(productPaperInputMaterialService.readFileProductPaperInputMaterial(request));
		//	responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
		//	responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		//} catch (Exception e) {
		//	logger.error(e.getMessage(), e);
		//	responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
		//	responseData.setStatus(RESPONSE_STATUS.FAILED);
		//}
		return responseData;
	}
	
	@PostMapping("/save-{servicePaperType}")
	@ResponseBody
	public ResponseData<String> saveData(@PathVariable("servicePaperType") String servicePaperType, @RequestBody ServicePaperFormVo formVo) {
		logger.info("saveData");
		ResponseData<String> responseData = new ResponseData<String>();
		//try {
		//	responseData.setData(productPaperInputMaterialService.readFileProductPaperInputMaterial(request));
		//	responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
		//	responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		//} catch (Exception e) {
		//	logger.error(e.getMessage(), e);
		//	responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
		//	responseData.setStatus(RESPONSE_STATUS.FAILED);
		//}
		return responseData;
	}
	
}
