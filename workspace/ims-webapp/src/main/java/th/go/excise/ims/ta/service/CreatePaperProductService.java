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
		dataTableAjax.setRecordsTotal(10);
		dataTableAjax.setRecordsFiltered(10);
		return dataTableAjax;
	}

	public List<CreatePaperProductRawMaterialReceiptVo> getDataRawMaterialReceipt() {

		List<CreatePaperProductRawMaterialReceiptVo> datalist = new ArrayList<CreatePaperProductRawMaterialReceiptVo>();
		CreatePaperProductRawMaterialReceiptVo data = new CreatePaperProductRawMaterialReceiptVo();

		data.setId(Long.valueOf(1));
		data.setList("test");
		data.setInvoices("test");
		data.setDailyAccount("test");
		data.setMonthStatement("test");
		data.setExternalData("sadasd");
		data.setMaximumDifference("sdfdsf");
		datalist.add(data);

		data.setId(Long.valueOf(2));
		data.setList("test");
		data.setInvoices("test");
		data.setDailyAccount("test");
		data.setMonthStatement("test");
		data.setExternalData("sadasd");
		data.setMaximumDifference("sdfdsf");
		datalist.add(data);

		return datalist;
	}

}
