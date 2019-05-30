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
import th.go.excise.ims.ta.vo.AnalysisTaxRetailPriceVo;
import th.go.excise.ims.ta.vo.FactoryVo;

@Service
public class AnalysisTaxRetailPriceService {
	// D2
	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	public DataTableAjax<AnalysisTaxRetailPriceVo> GetAnalysisTaxQuRetailPrice(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());
		
		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());
		
		int total = 0;
		DataTableAjax<AnalysisTaxRetailPriceVo> dataTableAjax = new DataTableAjax<AnalysisTaxRetailPriceVo>();
		dataTableAjax.setData(listAnalysisTaxQuRetailPrice(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxRetailPriceVo> listAnalysisTaxQuRetailPrice(String dutyCode) {
		List<AnalysisTaxRetailPriceVo> dataList = null;
		
		if ("0101".equals(dutyCode)) {
			dataList = getData0101();
		} else if("0201".equals(dutyCode)) {
			dataList = getData0201();
		} else {
			getDataMock();
		}

		return dataList;
	}
	
	private List<AnalysisTaxRetailPriceVo> getData0101() {
		List<AnalysisTaxRetailPriceVo> dataList = new ArrayList<AnalysisTaxRetailPriceVo>();
		AnalysisTaxRetailPriceVo data = null;

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxInformPrice(new BigDecimal(0));
		data.setInformPrice(new BigDecimal(0));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxInformPrice(new BigDecimal(0));
		data.setInformPrice(new BigDecimal(0));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxInformPrice(new BigDecimal(0));
		data.setInformPrice(new BigDecimal(5));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxInformPrice(new BigDecimal(27.45));
		data.setInformPrice(new BigDecimal(25.45));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
	private List<AnalysisTaxRetailPriceVo> getData0201() {
		List<AnalysisTaxRetailPriceVo> dataList = new ArrayList<AnalysisTaxRetailPriceVo>();
		AnalysisTaxRetailPriceVo data = null;

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำแร่และน้ำอัดลมที่เติมน้ำตาลหรือสารที่ทำให้หวานอื่นที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร โออิชิ ชาคูลล์ซ่า");
		data.setTaxInformPrice(new BigDecimal(13.32));
		data.setInformPrice(new BigDecimal(10.32));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("น้ำผลไม้ (รวมถึงเกรปมัสต์) และน้ำพืชผักที่ไม่ได้หมักและไม่เติมสุรา ไม่ว่าจะเติมน้ำตาล หรือสารทำให้หวานอื่น ๆหรือไม่ก็ตามที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร ฟาร์มเมอรี่");
		data.setTaxInformPrice(new BigDecimal(327.10));
		data.setInformPrice(new BigDecimal(330.10));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
	private List<AnalysisTaxRetailPriceVo> getDataMock() {
		List<AnalysisTaxRetailPriceVo> dataList = new ArrayList<AnalysisTaxRetailPriceVo>();
		AnalysisTaxRetailPriceVo data = null;

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxInformPrice(new BigDecimal(327.10));
		data.setInformPrice(new BigDecimal(330.10));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		data = new AnalysisTaxRetailPriceVo();
		data.setGoodsDesc("สินค้าทดสอบ");
		data.setTaxInformPrice(new BigDecimal(13.32));
		data.setInformPrice(new BigDecimal(10.32));
		data.setDiffTaxInformPrice(data.getInformPrice().subtract(data.getTaxInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
}
