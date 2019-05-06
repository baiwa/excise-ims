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
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD5Vo;

@Service
public class AnalysisTaxAmtsService {
	// D5

	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxAmtsService.class);

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	public DataTableAjax<PaperBasicAnalysisD5Vo> GetAnalysisTaxAmt(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());
		
		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());
		System.out.println(ToStringBuilder.reflectionToString(factoryVo, ToStringStyle.MULTI_LINE_STYLE));

		int total = 0;
		DataTableAjax<PaperBasicAnalysisD5Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD5Vo>();
		dataTableAjax.setData(listAnalysisTaxAmt(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD5Vo> listAnalysisTaxAmt(String dutyCode) {
		List<PaperBasicAnalysisD5Vo> dataList = null;

		if ("0101".equals(dutyCode)) {
			dataList = getData0101();
		} else if ("0201".equals(dutyCode)) {
			dataList = getData0201();
		} else if ("0401".equals(dutyCode)) {
			dataList = getData0401();
		} else if ("0501".equals(dutyCode)) {
			dataList = getData0501();
		} else if ("0601".equals(dutyCode)) {
			dataList = getData0601();
		} else if ("0701".equals(dutyCode)) {
			dataList = getData0701();
		} else if ("0901".equals(dutyCode)) {
			dataList = getData0901();
		} else if ("1001".equals(dutyCode)) {
			dataList = getData1001();
		}

		return dataList;
	}

	private List<PaperBasicAnalysisD5Vo> getData0101() {
		List<PaperBasicAnalysisD5Vo> datalist = new ArrayList<PaperBasicAnalysisD5Vo>();
		PaperBasicAnalysisD5Vo data = null;

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(18522123.7500));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(18522123.7500));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getTaxByValAmt().subtract(data.getAnaTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getTaxByQtyAmt().subtract(data.getAnaTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumTaxAmt().subtract(data.getSumAnaTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(10988991.0000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(10988991.0000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getTaxByValAmt().subtract(data.getAnaTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getTaxByQtyAmt().subtract(data.getAnaTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumTaxAmt().subtract(data.getSumAnaTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getTaxByValAmt().subtract(data.getAnaTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getTaxByQtyAmt().subtract(data.getAnaTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumTaxAmt().subtract(data.getSumAnaTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getTaxByValAmt().subtract(data.getAnaTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getTaxByQtyAmt().subtract(data.getAnaTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumTaxAmt().subtract(data.getSumAnaTaxAmt()));
		datalist.add(data);

		return datalist;
	}

	private List<PaperBasicAnalysisD5Vo> getData0201() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData0401() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData0501() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData0601() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData0701() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData0901() {
		return null;

	}

	private List<PaperBasicAnalysisD5Vo> getData1001() {
		return null;

	}

}
