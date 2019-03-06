package th.go.excise.ims.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.service.CreatePaperProductService;
import th.go.excise.ims.ta.vo.CppFinishedGoodsPaymentVo;
import th.go.excise.ims.ta.vo.CppFinishedGoodsReceiveVo;
import th.go.excise.ims.ta.vo.CppRawMaterialBalanceVo;
import th.go.excise.ims.ta.vo.CppRawMaterialFinishedGoodsRelationshipVo;
import th.go.excise.ims.ta.vo.CppRawMaterialPaymentVo;
import th.go.excise.ims.ta.vo.CppRawMaterialReceiveVo;
import th.go.excise.ims.ta.vo.CppRawMaterialTaxBreakVo;
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
}
