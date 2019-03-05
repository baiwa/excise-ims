package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.CreatePaperProductRawMaterialReceiptVo;

@Service
public class CreatePaperProductService {

	public DataTableAjax<CreatePaperProductRawMaterialReceiptVo> listRowMaterialReceipt(CreatePaperFormVo request) {

		DataTableAjax<CreatePaperProductRawMaterialReceiptVo> dataTableAjax = new DataTableAjax<CreatePaperProductRawMaterialReceiptVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialReceipt());
		dataTableAjax.setRecordsTotal(20);
		dataTableAjax.setRecordsFiltered(20);
		return dataTableAjax;
	}

	public List<CreatePaperProductRawMaterialReceiptVo> getDataRawMaterialReceipt() {

		List<CreatePaperProductRawMaterialReceiptVo> datalist = new ArrayList<CreatePaperProductRawMaterialReceiptVo>();

		CreatePaperProductRawMaterialReceiptVo data = null;
		for (int i = 0; i < 10; i++) {
			data = new CreatePaperProductRawMaterialReceiptVo();
			data.setId(Long.valueOf(1));
			data.setList("ตรวจสอบการรับวัตถุดิบ"+i);
			data.setInvoices("13-05555-037");
			data.setDailyAccount("1305555037");
			data.setMonthStatement("1,500");
			data.setExternalData("500");
			data.setMaximumDifference("1,000");
			datalist.add(data);
		}
		return datalist;
	}

}
