package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxQtyVo;
@Service
public class AnalysisTaxQtyService {
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxQtyService.class);
	
	public DataTableAjax<AnalysisTaxQtyVo> GetAnalysisTaxQty( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxQtyVo> dataTableAjax = new DataTableAjax<AnalysisTaxQtyVo>();
		dataTableAjax.setData(listAnalysisTaxQty(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxQtyVo> listAnalysisTaxQty(Integer start,Integer length,int total) {
		String excise = "C16M DOM-1.5T CVT ZA7";
		
		List<AnalysisTaxQtyVo> datalist = new ArrayList<AnalysisTaxQtyVo>();
		AnalysisTaxQtyVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisTaxQtyVo();
			data.setGoodsDesc(excise+i);
			data.setTaxQty("100.00");
			data.setMonthStatementTaxQty("200.00");
			data.setDiffTaxQty("300.00");
			datalist.add(data);
		}
		


		return datalist;
	}

}
