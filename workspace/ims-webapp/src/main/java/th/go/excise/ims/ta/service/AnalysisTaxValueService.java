package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD3Vo;
@Service
public class AnalysisTaxValueService {
private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);
	
	public DataTableAjax<PaperBasicAnalysisD3Vo> GetAnalysisTaxValue( AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD3Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD3Vo>();
		dataTableAjax.setData(listAnalysisTaxValue());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD3Vo> listAnalysisTaxValue() {
	
		
		List<PaperBasicAnalysisD3Vo> datalist = new ArrayList<PaperBasicAnalysisD3Vo>();
		PaperBasicAnalysisD3Vo data = null;
		
			data = new PaperBasicAnalysisD3Vo();
			data.setGoodsDescText("สุราแช่ผลไม้ที่มีส่วนผสมขององุ่นหรือไวน์องุ่น");
			data.setTaxQty(new BigDecimal(300));
			data.setInformPrice(new BigDecimal(120));
			data.setGoodsValueAmt(new BigDecimal(120*300));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD3Vo();
			data.setGoodsDescText("แสงโสม แสง 5 40 ดีกรี");
			data.setTaxQty(new BigDecimal(150));
			data.setInformPrice(new BigDecimal(250));
			data.setGoodsValueAmt(new BigDecimal(150*250));
			datalist.add(data);
	
			data = new PaperBasicAnalysisD3Vo();
			data.setGoodsDescText("รีเจนซี่ รีเจนซี่ 3 40 ดีกรี");
			data.setTaxQty(new BigDecimal(165));
			data.setInformPrice(new BigDecimal(450));
			data.setGoodsValueAmt(new BigDecimal(165*450));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD3Vo();
			data.setGoodsDescText("แบรนด์เบียร์ BEER EDB");
			data.setTaxQty(new BigDecimal(200));
			data.setInformPrice(new BigDecimal(55));
			data.setGoodsValueAmt(new BigDecimal(200*55));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD3Vo();
			data.setGoodsDescText("สุราแช่ ตะวันไทย ตะวันไทย 1");
			data.setTaxQty(new BigDecimal(546));
			data.setInformPrice(new BigDecimal(60));
			data.setGoodsValueAmt(new BigDecimal(546*60));
			datalist.add(data);
			
		return datalist;
	}
}
