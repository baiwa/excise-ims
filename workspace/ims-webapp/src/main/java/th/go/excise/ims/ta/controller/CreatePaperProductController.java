package th.go.excise.ims.ta.controller;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.service.CreatePaperProductService;
import th.go.excise.ims.ta.vo.CppCheckPriceVo;
import th.go.excise.ims.ta.vo.CppFinishedGoodsPaymentVo;
import th.go.excise.ims.ta.vo.CppFinishedGoodsReceiveVo;
import th.go.excise.ims.ta.vo.CppPayForeignFinishedGoodsVo;
import th.go.excise.ims.ta.vo.CppRawMaterialBalanceVo;
import th.go.excise.ims.ta.vo.CppRawMaterialFinishedGoodsRelationshipVo;
import th.go.excise.ims.ta.vo.CppRawMaterialPaymentVo;
import th.go.excise.ims.ta.vo.CppRawMaterialReceiveVo;
import th.go.excise.ims.ta.vo.CppRawMaterialTaxBreakVo;
import th.go.excise.ims.ta.vo.CppTaxVo;
import th.go.excise.ims.ta.vo.CppUnitPriceVo;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;

@Controller
@RequestMapping("/api/ta/create-paper-product")
public class CreatePaperProductController {

	private static final Logger logger = LoggerFactory.getLogger(CreatePaperProductController.class);
	
	private CreatePaperProductService createPaperProductService;

	@Autowired
	public CreatePaperProductController(CreatePaperProductService createPaperProductService) {
		this.createPaperProductService = createPaperProductService;
	}

	/*------MaterialReceive-----*/
	@PostMapping("/list-raw-material-receive")
	@ResponseBody
	public DataTableAjax<CppRawMaterialReceiveVo> listRawMaterialReceive(@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialReceipt");

		DataTableAjax<CppRawMaterialReceiveVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRawMaterialReceive(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/list-raw-material-receive-export")
	@ResponseBody
	public void exportRawMaterialReceive(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportRawMaterialReceive();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	/*------MaterialPayment-----*/
	@PostMapping("/list-raw-material-payment")
	@ResponseBody
	public DataTableAjax<CppRawMaterialPaymentVo> listRawMaterialPayment(@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialPayment");

		DataTableAjax<CppRawMaterialPaymentVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRawMaterialPayment(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/list-raw-material-payment-export")
	@ResponseBody
	public void export(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบจ่ายวัตถุดิบ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppFinishedGoodsPayment();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	/*------MaterialBalance-----*/
	@PostMapping("/list-raw-material-balance")
	@ResponseBody
	public DataTableAjax<CppRawMaterialBalanceVo> listRawMaterialBalance(@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialBalance");

		DataTableAjax<CppRawMaterialBalanceVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRawMaterialBalance(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/list-raw-material-balance")
	@ResponseBody
	public void exportCppRawMaterialBalance(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจนับวัตถุดิบคงเหลือ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppRawMaterialBalance();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}
	/*------MaterialFinishedGoodsRelationship-----*/
	@PostMapping("/list-raw-material-finished-goods-relationship")
	@ResponseBody
	public DataTableAjax<CppRawMaterialFinishedGoodsRelationshipVo> listRawMaterialFinishedGoodsRelationship(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialFinishedGoodsRelationship");

		DataTableAjax<CppRawMaterialFinishedGoodsRelationshipVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRawMaterialFinishedGoodsRelationship(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/list-raw-material-finished-goods-relationship-export")
	@ResponseBody
	public void exportCppMaterialFinishedGoodsRelationship(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการรับสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppMaterialFinishedGoodsRelationship();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}


	/*------FinishedGoodsReceive-----*/
	@PostMapping("/list-finished-goods-receive")
	@ResponseBody
	public DataTableAjax<CppFinishedGoodsReceiveVo> listFinishedGoodsReceive(@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialFinishedGoodsRelationship");

		DataTableAjax<CppFinishedGoodsReceiveVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listFinishedGoodsReceive(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("/list-finished-goods-receive-export")
	@ResponseBody
	public void exportCppFinishedGoodsReceive(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการรับสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppFinishedGoodsReceive();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}


	/*------FinishedGoodsPayment-----*/
	@PostMapping("/list-finished-goods-payment")
	@ResponseBody
	public DataTableAjax<CppFinishedGoodsPaymentVo> listFinishedGoodsPayment(@RequestBody CreatePaperFormVo request) {
		logger.info("listFinishedGoodsPayment");

		DataTableAjax<CppFinishedGoodsPaymentVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listFinishedGoodsPayment(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@GetMapping("/list-finished-goods-payment-export")
	@ResponseBody
	public void exportCppFinishedGoodsPayment(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการจ่ายสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppFinishedGoodsPayment();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	/*------RawMaterialTaxBreak-----*/
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

	/*------UnitPrice-----*/
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

	/*------CheckPrice-----*/
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
	public void exportCppCheckPrice(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบด้านราคา", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppCheckPrice();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}


	/*------PayForeignFinishedGoods-----*/
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
	public void exportCppPayForeignFinishedGoods(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("จ่ายสินค้าสำเร็จรูปต่างประเทศ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppPayForeignFinishedGoods();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();

	}

	/*------Tax-----*/
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
	public void exportCppTax(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("คำนวณภาษีที่ต้องชำระเพิ่ม", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportCppTax();
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