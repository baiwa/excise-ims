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
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD1Vo;


@Service
public class AnalysisTaxQtyService {
	// D1
	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxQtyService.class);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	public DataTableAjax<PaperBasicAnalysisD1Vo> getAnalysisTaxQty(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());
		
		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());
		System.out.println(ToStringBuilder.reflectionToString(factoryVo, ToStringStyle.MULTI_LINE_STYLE));
		
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD1Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD1Vo>();
		dataTableAjax.setData(listAnalysisTaxQty(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD1Vo> listAnalysisTaxQty(String dutyCode) {
		List<PaperBasicAnalysisD1Vo> dataList = null;
		
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
	
	private List<PaperBasicAnalysisD1Vo> getData0101() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxQty(new BigDecimal(3166175.0000));
		data.setMonthStatementTaxQty(new BigDecimal(3166175.0000));
		data.setDiffTaxQty(new BigDecimal("0"));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxQty(new BigDecimal(1878440.0000));
		data.setMonthStatementTaxQty(new BigDecimal(1878460.0000));
		data.setDiffTaxQty(new BigDecimal("20"));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxQty(new BigDecimal(1287414.0000));
		data.setMonthStatementTaxQty(new BigDecimal(1287414.0000));
		data.setDiffTaxQty(new BigDecimal("0"));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxQty(new BigDecimal(196510.0000));
		data.setMonthStatementTaxQty(new BigDecimal(196520.0000));
		data.setDiffTaxQty(new BigDecimal("10"));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0201() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0401() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0501() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0601() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0701() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData0901() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}
	
	private List<PaperBasicAnalysisD1Vo> getData1001() {
		List<PaperBasicAnalysisD1Vo> dataList = new ArrayList<PaperBasicAnalysisD1Vo>();
		PaperBasicAnalysisD1Vo data = null;
		
		// 1
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 2
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 3
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		// 4
		data = new PaperBasicAnalysisD1Vo();
		data.setGoodsDesc("");
		data.setTaxQty(new BigDecimal(""));
		data.setMonthStatementTaxQty(new BigDecimal(""));
		data.setDiffTaxQty(new BigDecimal(""));
		dataList.add(data);
		
		return dataList;
	}

}
