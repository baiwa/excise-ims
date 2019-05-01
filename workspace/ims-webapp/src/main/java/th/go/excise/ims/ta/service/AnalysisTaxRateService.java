package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD4Vo;

@Service
public class AnalysisTaxRateService {

	public DataTableAjax<PaperBasicAnalysisD4Vo> GetAnalysisTaxRate(AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD4Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD4Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxRate());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD4Vo> listAnalysisTaxRate() {

		List<PaperBasicAnalysisD4Vo> datalist = new ArrayList<PaperBasicAnalysisD4Vo>();
		PaperBasicAnalysisD4Vo data = null;
	
			data = new PaperBasicAnalysisD4Vo();
			data.setGoodsDesc("สุราแช่ ตะวันไทย ตะวันไทย");
			data.setTaxRateByPrice(new BigDecimal(52));
			data.setTaxRateByQty(new BigDecimal(50));
			data.setAnaTaxRateByPrice(new BigDecimal(70));
			data.setAnaTaxRateByQty(new BigDecimal(75));
			data.setDiffTaxRateByPrice(new BigDecimal(60));
			data.setDiffTaxRateByQty(new BigDecimal(65));
			datalist.add(data);
	
			data = new PaperBasicAnalysisD4Vo();
			data.setGoodsDesc("รวงข้าว SILVER");
			data.setTaxRateByPrice(new BigDecimal(89));
			data.setTaxRateByQty(new BigDecimal(92));
			data.setAnaTaxRateByPrice(new BigDecimal(69));
			data.setAnaTaxRateByQty(new BigDecimal(65));
			data.setDiffTaxRateByPrice(new BigDecimal(71));
			data.setDiffTaxRateByQty(new BigDecimal(73));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD4Vo();
			data.setGoodsDesc("ตะวันแดง ข้าวหอม ");
			data.setTaxRateByPrice(new BigDecimal(63));
			data.setTaxRateByQty(new BigDecimal(65));
			data.setAnaTaxRateByPrice(new BigDecimal(63));
			data.setAnaTaxRateByQty(new BigDecimal(65));
			data.setDiffTaxRateByPrice(new BigDecimal(77));
			data.setDiffTaxRateByQty(new BigDecimal(82));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD4Vo();
			data.setGoodsDesc("ขาวไผ่ทอง ทดสอบ STRAWBERRY");
			data.setTaxRateByPrice(new BigDecimal(98));
			data.setTaxRateByQty(new BigDecimal(96));
			data.setAnaTaxRateByPrice(new BigDecimal(50));
			data.setAnaTaxRateByQty(new BigDecimal(52));
			data.setDiffTaxRateByPrice(new BigDecimal(36));
			data.setDiffTaxRateByQty(new BigDecimal(32));
			datalist.add(data);

		return datalist;
	}

}
