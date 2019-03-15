package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxQtyVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRetailPriceVo;
@Service
public class AnalysisTaxRetailPriceService {
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);
	
	public DataTableAjax<AnalysisTaxRetailPriceVo> GetAnalysisTaxQuRetailPrice( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxRetailPriceVo> dataTableAjax = new DataTableAjax<AnalysisTaxRetailPriceVo>();
		dataTableAjax.setData(listAnalysisTaxQuRetailPrice(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxRetailPriceVo> listAnalysisTaxQuRetailPrice(int start,int length,int total) {
		String excise = "C16M DOM-1.5T CVT ZA7";
		
		List<AnalysisTaxRetailPriceVo> datalist = new ArrayList<AnalysisTaxRetailPriceVo>();
		AnalysisTaxRetailPriceVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisTaxRetailPriceVo();
			data.setGoodsDesc(excise+i);
			data.setTaxInformPrice("100.00");
			data.setInformPrice("200.00");
			data.setDiffTaxInformPrice("300.000");
			datalist.add(data);
		}
		
		return datalist;
	}
}
