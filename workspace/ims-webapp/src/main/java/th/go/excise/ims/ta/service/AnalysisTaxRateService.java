package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRateVo;

@Service
public class AnalysisTaxRateService {

	public DataTableAjax<AnalysisTaxRateVo> GetAnalysisTaxRate(AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxRateVo> dataTableAjax = new DataTableAjax<AnalysisTaxRateVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxRate(request.getStart(), request.getLength(), total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxRateVo> listAnalysisTaxRate(int start, int length, int total) {
		String excise = "C16M DOM-1.5T CVT ZA7";
		List<AnalysisTaxRateVo> datalist = new ArrayList<AnalysisTaxRateVo>();
		AnalysisTaxRateVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new AnalysisTaxRateVo();
			data.setGoodsDesc(excise + i);
			data.setTaxRateByPriceAmt("100.00");
			data.setTaxRateByQtyAmt("100.00");
			data.setAnaTaxRateByPriceAmt("200.00");
			data.setAnaTaxRateByQtyAmt("3000.00");
			data.setDiffTaxRateByPriceAmt("400.00");
			data.setDiffTaxRateByQtyAmt("600.00");
			datalist.add(data);
		}

		return datalist;
	}

}
