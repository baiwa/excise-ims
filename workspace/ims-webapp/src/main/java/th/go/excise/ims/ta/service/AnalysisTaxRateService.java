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
import th.go.excise.ims.ta.vo.AnalysisTaxRateVo;
import th.go.excise.ims.ta.vo.FactoryVo;

@Service
public class AnalysisTaxRateService {
	// D4

	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRateService.class);

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	public DataTableAjax<AnalysisTaxRateVo> GetAnalysisTaxRate(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());

		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());

		int total = 0;
		DataTableAjax<AnalysisTaxRateVo> dataTableAjax = new DataTableAjax<AnalysisTaxRateVo>();
		dataTableAjax.setData(listAnalysisTaxRate(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxRateVo> listAnalysisTaxRate(String dutyCode) {
		List<AnalysisTaxRateVo> dataList = null;
		
		if ("0101".equals(dutyCode)) {
			dataList = getData0101();
		} else if("0201".equals(dutyCode)) {
			dataList = getData0201();
		} else {
			dataList = getDataMock();
		}

		return dataList;
	}

	private List<AnalysisTaxRateVo> getData0101() {
		List<AnalysisTaxRateVo> dataList = new ArrayList<AnalysisTaxRateVo>();
		AnalysisTaxRateVo data = null;

		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);
		
		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);
		
		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.2000));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.2000));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);
		
		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);

		return dataList;
	}

	private List<AnalysisTaxRateVo> getData0201() {
		List<AnalysisTaxRateVo> dataList = new ArrayList<AnalysisTaxRateVo>();
		AnalysisTaxRateVo data = null;

		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำแร่และน้ำอัดลมที่เติมน้ำตาลหรือสารที่ทำให้หวานอื่นที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร โออิชิ ชาคูลล์ซ่า");
		data.setTaxRateByPrice(new BigDecimal(14.0000));
		data.setTaxRateByQty(new BigDecimal(0.5000));
		data.setAnaTaxRateByPrice(new BigDecimal(14.0000));
		data.setAnaTaxRateByQty(new BigDecimal(0.5000));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);
		
		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("น้ำผลไม้ (รวมถึงเกรปมัสต์) และน้ำพืชผักที่ไม่ได้หมักและไม่เติมสุรา ไม่ว่าจะเติมน้ำตาล หรือสารทำให้หวานอื่น ๆหรือไม่ก็ตามที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร ฟาร์มเมอรี่");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(0.5000));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(0.5000));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);

		return dataList;
	}

	private List<AnalysisTaxRateVo> getDataMock() {
		List<AnalysisTaxRateVo> dataList = new ArrayList<AnalysisTaxRateVo>();
		AnalysisTaxRateVo data = null;

		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);
		
		data = new AnalysisTaxRateVo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxRateByPrice(new BigDecimal(0));
		data.setTaxRateByQty(new BigDecimal(5.8500));
		data.setAnaTaxRateByPrice(new BigDecimal(0));
		data.setAnaTaxRateByQty(new BigDecimal(5.8500));
		data.setDiffTaxRateByPrice(data.getAnaTaxRateByPrice().subtract(data.getTaxRateByPrice()));
		data.setDiffTaxRateByQty(data.getAnaTaxRateByQty().subtract(data.getTaxRateByQty()));
		dataList.add(data);

		return dataList;
	}

}
