package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD5Vo;

@Service
public class AnalysisTaxAmtsService {
	public DataTableAjax<PaperBasicAnalysisD5Vo> GetAnalysisTaxAmt(AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD5Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD5Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxAmt());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD5Vo> listAnalysisTaxAmt() {
		List<PaperBasicAnalysisD5Vo> datalist = new ArrayList<PaperBasicAnalysisD5Vo>();
		PaperBasicAnalysisD5Vo data = null;
	
			data = new PaperBasicAnalysisD5Vo();
			data.setGoodsDesc("สุราขาวสรรพสามิต สรรพสามิตสุราไทย ");
			data.setTaxByValAmt(new BigDecimal(55));
			data.setTaxByQtyAmt(new BigDecimal(50));
			data.setSumTaxAmt(new BigDecimal(55+50));
			data.setAnaTaxByValAmt(new BigDecimal(70));
			data.setAnaTaxByQtyAmt(new BigDecimal(60));
			data.setSumAnaTaxAmt(new BigDecimal(70+65));
			data.setDiffTaxByValAmt(new BigDecimal(63));
			data.setDiffTaxByQtyAmt(new BigDecimal(68));
			data.setDiffSumTaxAmt(new BigDecimal(63+68));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD5Vo();
			data.setGoodsDesc("ขาวไผ่ทอง MAEKONG");
			data.setTaxByValAmt(new BigDecimal(38));
			data.setTaxByQtyAmt(new BigDecimal(42));
			data.setSumTaxAmt(new BigDecimal(38+42));
			data.setAnaTaxByValAmt(new BigDecimal(63));
			data.setAnaTaxByQtyAmt(new BigDecimal(60));
			data.setSumAnaTaxAmt(new BigDecimal(63+60));
			data.setDiffTaxByValAmt(new BigDecimal(72));
			data.setDiffTaxByQtyAmt(new BigDecimal(74));
			data.setDiffSumTaxAmt(new BigDecimal(72+74));
			datalist.add(data);
			
			data = new PaperBasicAnalysisD5Vo();
			data.setGoodsDesc("ขาวไผ่ทอง เบลน 285 MAEKONG");
			data.setTaxByValAmt(new BigDecimal(96));
			data.setTaxByQtyAmt(new BigDecimal(89));
			data.setSumTaxAmt(new BigDecimal(96+89));
			data.setAnaTaxByValAmt(new BigDecimal(102));
			data.setAnaTaxByQtyAmt(new BigDecimal(111));
			data.setSumAnaTaxAmt(new BigDecimal(102+111));
			data.setDiffTaxByValAmt(new BigDecimal(123));
			data.setDiffTaxByQtyAmt(new BigDecimal(146));
			data.setDiffSumTaxAmt(new BigDecimal(123+146));
			datalist.add(data);

			
			data = new PaperBasicAnalysisD5Vo();
			data.setGoodsDesc("สุราไทย 50 ดีกรี");
			data.setTaxByValAmt(new BigDecimal(146));
			data.setTaxByQtyAmt(new BigDecimal(138));
			data.setSumTaxAmt(new BigDecimal(146+138));
			data.setAnaTaxByValAmt(new BigDecimal(142));
			data.setAnaTaxByQtyAmt(new BigDecimal(111));
			data.setSumAnaTaxAmt(new BigDecimal(142+111));
			data.setDiffTaxByValAmt(new BigDecimal(86));
			data.setDiffTaxByQtyAmt(new BigDecimal(82));
			data.setDiffSumTaxAmt(new BigDecimal(86+82));
			datalist.add(data);

		return datalist;
	}

}
