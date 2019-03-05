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
		int total = 17;
		DataTableAjax<CreatePaperProductRawMaterialReceiptVo> dataTableAjax = new DataTableAjax<CreatePaperProductRawMaterialReceiptVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialReceipt(request.getStart(), request.getLength(), total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<CreatePaperProductRawMaterialReceiptVo> getDataRawMaterialReceipt(int start, int length, int total) {
		List<CreatePaperProductRawMaterialReceiptVo> datalist = new ArrayList<CreatePaperProductRawMaterialReceiptVo>();
		String desc = "ตรวจสอบวัตถุดิบ";
		CreatePaperProductRawMaterialReceiptVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CreatePaperProductRawMaterialReceiptVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
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
