package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.FactoryVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD4Vo;

@Service
public class AnalysisTaxRateService {
	// D4

	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRateService.class);

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	public DataTableAjax<PaperBasicAnalysisD4Vo> GetAnalysisTaxRate(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());

		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());
		System.out.println(ToStringBuilder.reflectionToString(factoryVo, ToStringStyle.MULTI_LINE_STYLE));

		int total = 0;
		DataTableAjax<PaperBasicAnalysisD4Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD4Vo>();
		dataTableAjax.setData(listAnalysisTaxRate(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD4Vo> listAnalysisTaxRate(String dutyCode) {
		List<PaperBasicAnalysisD4Vo> dataList = null;
		
		if ("0101".equals(dutyCode)) {
			dataList = getData0101();
		} else if("0201".equals(dutyCode)) {
			dataList = getData0201();
		} else if("0401".equals(dutyCode)) {
			dataList = getData0401();
		} else if("0501".equals(dutyCode)) {
			dataList = getData0501();
		} else if("0601".equals(dutyCode)) {
			dataList = getData0601();
		} else if("0701".equals(dutyCode)) {
			dataList = getData0701();
		} else if("0901".equals(dutyCode)) {
			dataList = getData0901();
		} else if("1001".equals(dutyCode)) {
			dataList = getData1001();
		}

		return dataList;
	}

	private List<PaperBasicAnalysisD4Vo> getData0101() {
		List<PaperBasicAnalysisD4Vo> dataList = new ArrayList<PaperBasicAnalysisD4Vo>();
		PaperBasicAnalysisD4Vo data = null;

		data = new PaperBasicAnalysisD4Vo();
		data.setGoodsDesc("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(new BigDecimal(0));
		data.setDiffTaxRateByQty(new BigDecimal(0));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD4Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(new BigDecimal(0));
		data.setDiffTaxRateByQty(new BigDecimal(0));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD4Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.2000));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.2000));
		data.setDiffTaxRateByPrice(new BigDecimal(0));
		data.setDiffTaxRateByQty(new BigDecimal(0));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD4Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(new BigDecimal(0));
		data.setDiffTaxRateByQty(new BigDecimal(0));
		dataList.add(data);

		return dataList;
	}

	private List<PaperBasicAnalysisD4Vo> getData0201() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData0401() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData0501() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData0601() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData0701() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData0901() {
		return null;
	}

	private List<PaperBasicAnalysisD4Vo> getData1001() {
		return null;
	}

}
