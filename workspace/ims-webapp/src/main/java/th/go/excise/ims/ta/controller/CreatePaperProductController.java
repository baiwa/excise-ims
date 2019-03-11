package th.go.excise.ims.ta.controller;

import java.io.IOException;
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

	// TODO MaterialReceive
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
	public void exportRawMaterialReceive(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการรับวัตถุดิบ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportRawMaterialReceive();
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

	@PostMapping("/list-raw-material-receive-upload")
	@ResponseBody
	public List<CppRawMaterialReceiveVo> readFileExcel(@ModelAttribute CppRawMaterialReceiveVo request){
		logger.info("listRawMaterialReceive Upload!!");
		List<CppRawMaterialReceiveVo> resultList = createPaperProductService.readFileRawMaterialReceive(request);

		return resultList;
	}

//TODO MaterialPayment
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
	public void exportRawMaterialPayment(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบจ่ายวัตถุดิบ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportRawMaterialPayment();
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

	// TODO MaterialBalance
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
	public void exportRawMaterialBalance(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจนับวัตถุดิบคงเหลือ", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportRawMaterialBalance();
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

//TODO MaterialFinishedGoodsRelationship
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
	public void exportRawMaterialFinishedGoodsRelationship(HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจหาความสัมพันธ์การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportRawMaterialFinishedGoodsRelationship();
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

//TODO FinishedGoodsReceive
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
	public void exportRawFinishedGoodsReceive(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการรับสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportFinishedGoodsReceive();
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

//TODO FinishedGoodsPayment
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
	public void exportRawFinishedGoodsPayment(HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบการจ่ายสินค้าสำเร็จรูป", "UTF-8");
		/* write it as an excel attachment */
		byte[] outArray = createPaperProductService.exportFinishedGoodsPayment();
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

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษีที่ยื่นต่อกรมสรรพสามิต (ภส. ๐๕-๐๒)",
				"UTF-8");
		/* write it as an excel attachment */
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

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี", "UTF-8");
		/* write it as an excel attachment */
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
	public void exportCheckPrice(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("ตรวจสอบด้านราคา", "UTF-8");
		/* write it as an excel attachment */
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

		/* set fileName */
		String fileName = URLEncoder.encode("จ่ายสินค้าสำเร็จรูปต่างประเทศ", "UTF-8");
		/* write it as an excel attachment */
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
	public void exportTax(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

		logger.info("listRawMaterialReceive export!!");

		/* set fileName */
		String fileName = URLEncoder.encode("คำนวณภาษีที่ต้องชำระเพิ่ม", "UTF-8");
		/* write it as an excel attachment */
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

	}
}