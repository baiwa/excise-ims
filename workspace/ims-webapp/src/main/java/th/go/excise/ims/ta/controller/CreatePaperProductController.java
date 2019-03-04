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
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.CreatePaperProductRawMaterialReceiptVo;

@Controller
@RequestMapping("/api/ta/create-paper-product")
public class CreatePaperProductController {

	private static final Logger logger = LoggerFactory.getLogger(CreatePaperProductController.class);

	private CreatePaperProductService createPaperProductService;

	@Autowired
	public CreatePaperProductController(CreatePaperProductService createPaperProductService) {
		this.createPaperProductService = createPaperProductService;
	}

	@PostMapping("/list-raw-material-receipt")
	@ResponseBody
	public DataTableAjax<CreatePaperProductRawMaterialReceiptVo> listRawMaterialReceiptVo(
			@RequestBody CreatePaperFormVo request) {
		logger.info("listRawMaterialReceipt");

		DataTableAjax<CreatePaperProductRawMaterialReceiptVo> response = new DataTableAjax<>();
		try {
			response = createPaperProductService.listRowMaterialReceipt(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
