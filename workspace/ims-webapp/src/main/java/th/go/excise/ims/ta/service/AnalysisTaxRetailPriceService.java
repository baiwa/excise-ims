package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD2Vo;

@Service
public class AnalysisTaxRetailPriceService {
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);

	public DataTableAjax<PaperBasicAnalysisD2Vo> GetAnalysisTaxQuRetailPrice(AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD2Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD2Vo>();
		dataTableAjax.setData(listAnalysisTaxQuRetailPrice());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD2Vo> listAnalysisTaxQuRetailPrice() {
	

		List<PaperBasicAnalysisD2Vo> datalist = new ArrayList<PaperBasicAnalysisD2Vo>();
		PaperBasicAnalysisD2Vo data = null;

			data = new PaperBasicAnalysisD2Vo();
			data.setGoodsDesc("แสงโสม แสดงโสม 40 ดีกรี ");
			data.setTaxInformPrice(new BigDecimal(256));
			data.setInformPrice(new BigDecimal(250));
			data.setDiffTaxInformPrice(new BigDecimal(6));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD2Vo();
			data.setGoodsDesc("สุราแช่ ตะวันไทย ตะวันไทย");
			data.setTaxInformPrice(new BigDecimal(536));
			data.setInformPrice(new BigDecimal(501));
			data.setDiffTaxInformPrice(new BigDecimal(35));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD2Vo();
			data.setGoodsDesc("สิงห์โตเบียร์ ");
			data.setTaxInformPrice(new BigDecimal(306));
			data.setInformPrice(new BigDecimal(295));
			data.setDiffTaxInformPrice(new BigDecimal(11));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD2Vo();
			data.setGoodsDesc("ชีต้าร์ ออริจินัล  ");
			data.setTaxInformPrice(new BigDecimal(650));
			data.setInformPrice(new BigDecimal(510));
			data.setDiffTaxInformPrice(new BigDecimal(140));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD2Vo();
			data.setGoodsDesc("สุราแช่ผลไม้ที่มีส่วนผสมขององุ่นหรือไวน์องุ่น");
			data.setTaxInformPrice(new BigDecimal(150));
			data.setInformPrice(new BigDecimal(143));
			data.setDiffTaxInformPrice(new BigDecimal(7));
			datalist.add(data);
		

		return datalist;
	}
}
