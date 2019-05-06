package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		} else {
			dataList = getDataMock();
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
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(10988991.0000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(10988991.0000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		return datalist;
	}

	private List<PaperBasicAnalysisD5Vo> getData0201() {
		List<PaperBasicAnalysisD5Vo> datalist = new ArrayList<PaperBasicAnalysisD5Vo>();
		PaperBasicAnalysisD5Vo data = null;

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำแร่และน้ำอัดลมที่เติมน้ำตาลหรือสารที่ทำให้หวานอื่นที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร โออิชิ ชาคูลล์ซ่า");
		data.setTaxByValAmt(new BigDecimal(2530526.1408));
		data.setTaxByQtyAmt(new BigDecimal(217119.3600));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(2530526.1408));
		data.setAnaTaxByQtyAmt(new BigDecimal(217119.3600));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("น้ำผลไม้ (รวมถึงเกรปมัสต์) และน้ำพืชผักที่ไม่ได้หมักและไม่เติมสุรา ไม่ว่าจะเติมน้ำตาล หรือสารทำให้หวานอื่น ๆหรือไม่ก็ตามที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร ฟาร์มเมอรี่");
		data.setTaxByValAmt(new BigDecimal(0.0000));
		data.setTaxByQtyAmt(new BigDecimal(100.7500));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0.0000));
		data.setAnaTaxByQtyAmt(new BigDecimal(100.7500));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		return datalist;
	}

	private List<PaperBasicAnalysisD5Vo> getDataMock() {
		List<PaperBasicAnalysisD5Vo> datalist = new ArrayList<PaperBasicAnalysisD5Vo>();
		PaperBasicAnalysisD5Vo data = null;

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(6694552.8000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		data = new PaperBasicAnalysisD5Vo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxByValAmt(new BigDecimal(0));
		data.setTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumTaxAmt(data.getTaxByValAmt().add(data.getTaxByQtyAmt()));
		data.setAnaTaxByValAmt(new BigDecimal(0));
		data.setAnaTaxByQtyAmt(new BigDecimal(1149583.5000));
		data.setSumAnaTaxAmt(data.getAnaTaxByValAmt().add(data.getAnaTaxByQtyAmt()));
		data.setDiffTaxByValAmt(data.getAnaTaxByValAmt().subtract(data.getTaxByValAmt()));
		data.setDiffTaxByQtyAmt(data.getAnaTaxByQtyAmt().subtract(data.getTaxByQtyAmt()));
		data.setDiffSumTaxAmt(data.getSumAnaTaxAmt().subtract(data.getSumTaxAmt()));
		datalist.add(data);

		return datalist;
	}

}
