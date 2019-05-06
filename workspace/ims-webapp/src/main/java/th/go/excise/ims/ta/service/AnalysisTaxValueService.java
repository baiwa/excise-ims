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
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD3Vo;


@Service
public class AnalysisTaxValueService {
	// D3
	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisTaxRetailPriceService.class);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	public DataTableAjax<PaperBasicAnalysisD3Vo> GetAnalysisTaxValue(AnalysisFormVo request) {
		logger.info("newRegId={}", request.getNewRegId());
		
		FactoryVo factoryVo = taWsReg4000Repository.findByNewRegId(request.getNewRegId());
		
		int total = 0;
		DataTableAjax<PaperBasicAnalysisD3Vo> dataTableAjax = new DataTableAjax<PaperBasicAnalysisD3Vo>();
		dataTableAjax.setData(listAnalysisTaxValue(factoryVo.getDutyCode()));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PaperBasicAnalysisD3Vo> listAnalysisTaxValue(String dutyCode) {
		List<PaperBasicAnalysisD3Vo> dataList = null;
		
		if ("0101".equals(dutyCode)) {
			dataList = getData0101();
		} else if("0201".equals(dutyCode)) {
			dataList = getData0201();
		} else {
			dataList = getDataMock();
		}

		return dataList;
	}
	
	private List<PaperBasicAnalysisD3Vo> getData0101() {
		List<PaperBasicAnalysisD3Vo> dataList = new ArrayList<PaperBasicAnalysisD3Vo>();
		PaperBasicAnalysisD3Vo data = null;

		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำมันดีเซลที่มีปริมาณกำมะถันไม่เกินร้อยละ 0.005 โดยน้ำหนัก");
		data.setTaxQty(new BigDecimal(3166175.0000));
		data.setInformPrice(new BigDecimal(0));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);

		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 91");
		data.setTaxQty(new BigDecimal(1878440.0000));
		data.setInformPrice(new BigDecimal(0));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำมันแก๊สโซฮอล์ E20");
		data.setTaxQty(new BigDecimal(1287414.0000));
		data.setInformPrice(new BigDecimal(0));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำมันแก๊สโซฮอล์ E10 แก๊สโซฮอล์ออกเทน 95");
		data.setTaxQty(new BigDecimal(196510.0000));
		data.setInformPrice(new BigDecimal(27.45));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
	private List<PaperBasicAnalysisD3Vo> getData0201() {
		List<PaperBasicAnalysisD3Vo> dataList = new ArrayList<PaperBasicAnalysisD3Vo>();
		PaperBasicAnalysisD3Vo data = null;
		
		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำแร่และน้ำอัดลมที่เติมน้ำตาลหรือสารที่ทำให้หวานอื่นที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร โออิชิ ชาคูลล์ซ่า");
		data.setTaxQty(new BigDecimal(1356996.0000));
		data.setInformPrice(new BigDecimal(13.32));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("น้ำผลไม้ (รวมถึงเกรปมัสต์) และน้ำพืชผักที่ไม่ได้หมักและไม่เติมสุรา ไม่ว่าจะเติมน้ำตาล หรือสารทำให้หวานอื่น ๆหรือไม่ก็ตามที่มีปริมาณน้ำตาลเกิน 10 กรัม แต่ไม่เกิน 14 กรัม ต่อ 100 มิลลิลิตร ฟาร์มเมอรี่");
		data.setTaxQty(new BigDecimal(403.0000));
		data.setInformPrice(new BigDecimal(327.10));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
	private List<PaperBasicAnalysisD3Vo> getDataMock() {
		List<PaperBasicAnalysisD3Vo> dataList = new ArrayList<PaperBasicAnalysisD3Vo>();
		PaperBasicAnalysisD3Vo data = null;

		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("สินค้าทดสอบ");
		data.setTaxQty(new BigDecimal(3166175.0000));
		data.setInformPrice(new BigDecimal(1));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);
		
		data = new PaperBasicAnalysisD3Vo();
		data.setGoodsDescText("สินค้าทดสอบ");
		data.setTaxQty(new BigDecimal(196510.0000));
		data.setInformPrice(new BigDecimal(27.45));
		data.setGoodsValueAmt(data.getTaxQty().multiply(data.getInformPrice()));
		dataList.add(data);

		return dataList;
	}
	
}
