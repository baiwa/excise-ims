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
import th.go.excise.ims.ta.vo.CppRawMaterialBalanceVo;
import th.go.excise.ims.ta.vo.CppRawMaterialPaymentVo;
import th.go.excise.ims.ta.vo.CppRawMaterialReceiveVo;
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
			response = createPaperProductService.listRowMaterialReceive(request);
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
			response = createPaperProductService.listRowMaterialPayment(request);
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
			response = createPaperProductService.listRowMaterialBalance(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
