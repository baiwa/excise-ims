package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxValueVo;
@Service
public class AnalysisTaxValueService {
private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);
	
	public DataTableAjax<AnalysisTaxValueVo> GetAnalysisTaxValue( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxValueVo> dataTableAjax = new DataTableAjax<AnalysisTaxValueVo>();
		dataTableAjax.setData(listAnalysisTaxValue(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxValueVo> listAnalysisTaxValue(int start,int length,int total) {
		String excise = "C16M DOM-1.5T CVT ZA7";
		
		List<AnalysisTaxValueVo> datalist = new ArrayList<AnalysisTaxValueVo>();
		AnalysisTaxValueVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisTaxValueVo();
			data.setGoodsDescText(excise+i);
			data.setTaxQty("100.00");
			data.setInformPrice("200.00");
			data.setTaxValue("300.000");
			datalist.add(data);
		}
		
		return datalist;
	}
}
