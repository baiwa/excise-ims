package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD1Vo;
@Service
public class AnalysisTaxQtyService {
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxQtyService.class);
	
	public DataTableAjax<PaperBasicAnalysisD1Vo> GetAnalysisTaxQty( AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD1Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD1Vo>();
		dataTableAjax.setData(listAnalysisTaxQty());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD1Vo> listAnalysisTaxQty() {
		
		
		List<PaperBasicAnalysisD1Vo> datalist = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
//		for(int i = start;i<(start+length);i++){
//			if(i >= total){
//				break;
//			}
			data = new PaperBasicAnalysisD1Vo();
			data.setGoodsDesc("แสงโสม แสดงโสม 40 ดีกรี ");
			data.setTaxQty(new BigDecimal(256));
			data.setMonthStatementTaxQty(new BigDecimal(250));
			data.setDiffTaxQty(new BigDecimal(6));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD1Vo();
			data.setGoodsDesc("สุราแช่ ตะวันไทย ตะวันไทย");
			data.setTaxQty(new BigDecimal(536));
			data.setMonthStatementTaxQty(new BigDecimal(501));
			data.setDiffTaxQty(new BigDecimal(35));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD1Vo();
			data.setGoodsDesc("สิงห์โตเบียร์ ");
			data.setTaxQty(new BigDecimal(306));
			data.setMonthStatementTaxQty(new BigDecimal(295));
			data.setDiffTaxQty(new BigDecimal(11));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD1Vo();
			data.setGoodsDesc("ชีต้าร์ ออริจินัล  ");
			data.setTaxQty(new BigDecimal(650));
			data.setMonthStatementTaxQty(new BigDecimal(510));
			data.setDiffTaxQty(new BigDecimal(140));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD1Vo();
			data.setGoodsDesc("สุราแช่ผลไม้ที่มีส่วนผสมขององุ่นหรือไวน์องุ่น");
			data.setTaxQty(new BigDecimal(150));
			data.setMonthStatementTaxQty(new BigDecimal(143));
			data.setDiffTaxQty(new BigDecimal(7));
			datalist.add(data);
//		}
		


		return datalist;
	}

}
