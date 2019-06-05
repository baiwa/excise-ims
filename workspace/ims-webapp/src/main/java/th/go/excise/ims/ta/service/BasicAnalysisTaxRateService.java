package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaPaperBaD4;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD4Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRateVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxRateService extends AbstractBasicAnalysisService<BasicAnalysisTaxRateVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRateService.class);

	@Autowired
	private TaPaperBaD4Repository taPaperBaD4Repository;
	
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]),Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]),Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));

		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);

		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
	
		List<BasicAnalysisTaxRateVo> voList = new ArrayList<>();
		BasicAnalysisTaxRateVo vo = null;
		BigDecimal anaTaxRateByPrice = null;
		BigDecimal anaTaxRateByQty = null;
		BigDecimal diffTaxRateByPrice = null;
		BigDecimal diffTaxRateByQty = null;
		
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxRateVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxRateByPrice(anafri0001Vo.getValueRate());
			vo.setTaxRateByQty(anafri0001Vo.getQtyRate());
			vo.setAnaTaxRateByPrice(anaTaxRateByPrice);
			vo.setAnaTaxRateByQty(anaTaxRateByQty);
			vo.setDiffTaxRateByPrice(diffTaxRateByPrice);
			vo.setDiffTaxRateByQty(diffTaxRateByQty);	
			voList.add(vo);
		}
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD4> entityList = taPaperBaD4Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxRateVo> voList = new ArrayList<>();
		BasicAnalysisTaxRateVo vo = null;
		for (TaPaperBaD4 entity : entityList) {
			vo = new BasicAnalysisTaxRateVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxRateByPrice(entity.getTaxRateByPrice());
			vo.setTaxRateByQty(entity.getTaxRateByQty());
			vo.setAnaTaxRateByPrice(entity.getAnaTaxRateByPrice());
			vo.setAnaTaxRateByQty(entity.getAnaTaxRateByQty());
			vo.setDiffTaxRateByPrice(entity.getDiffTaxRateByPrice());
			vo.setDiffTaxRateByQty(entity.getDiffTaxRateByQty());	
			voList.add(vo);
		}
		return voList;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		List<BasicAnalysisTaxRateVo> dataSaveList = inquiryByWs(formVo);
		int i = 1;
		TaPaperBaD4 entity = null;
		for (BasicAnalysisTaxRateVo saveData : dataSaveList) {
			entity = new TaPaperBaD4();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(saveData.getGoodsDesc());
			entity.setTaxRateByPrice(saveData.getTaxRateByPrice());
			entity.setTaxRateByQty(saveData.getTaxRateByQty());
			entity.setAnaTaxRateByPrice(saveData.getAnaTaxRateByPrice());
			entity.setAnaTaxRateByQty(saveData.getAnaTaxRateByQty());
			entity.setDiffTaxRateByPrice(saveData.getDiffTaxRateByPrice());
			entity.setDiffTaxRateByQty(saveData.getDiffTaxRateByQty());
			taPaperBaD4Repository.save(entity);
			i++;
		}
	}

}
