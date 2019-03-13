package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxAmtVo;
@Service
public class AnalysisTaxAmtsService {
	public DataTableAjax<AnalysisTaxAmtVo> GetAnalysisTaxAmt( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxAmtVo> dataTableAjax = new DataTableAjax<AnalysisTaxAmtVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxAmt(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxAmtVo> listAnalysisTaxAmt(int start,int length,int total) {
		String excise = "C16M DOM-1.5T CVT ZA7";
		
		List<AnalysisTaxAmtVo> datalist = new ArrayList<AnalysisTaxAmtVo>();
		AnalysisTaxAmtVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisTaxAmtVo();
			data.setGoodsDesc(excise+i);
			data.setNetTaxByValue("1000.00");
			data.setNetTaxByQty("200.00");
			data.setNetTaxByValueAndQty("400.00");
			data.setAnaNetTaxByValue("500.00");
			data.setAnaNetTaxByQty("600.00");
			data.setAnaNetTaxByValueAndQty("700.00");
			data.setDiffNetTaxByValue("800.00");
			data.setDiffNetTaxByQty("900.00");
			data.setDiffNetTaxByValueAndQty("4560.00");
		
		}
		

		return datalist;
	}

}
