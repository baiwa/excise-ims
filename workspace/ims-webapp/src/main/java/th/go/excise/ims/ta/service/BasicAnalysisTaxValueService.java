package th.go.excise.ims.ta.service;

import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaPaperBaD3;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD3Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxValueService extends AbstractBasicAnalysisService<BasicAnalysisTaxValueVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxValueService.class);

	@Autowired
	private TaPaperBaD3Repository taPaperBaD3Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxValueVo> voList = new ArrayList<BasicAnalysisTaxValueVo>();
		BasicAnalysisTaxValueVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxValueVo();
			vo.setGoodsDescText(anafri0001Vo.getProductName());
			vo.setTaxQty(anafri0001Vo.getProductQty());
			vo.setInformPrice(anafri0001Vo.getProductPrice());
			vo.setGoodsValueAmt(vo.getTaxQty().multiply(vo.getInformPrice(), MathContext.DECIMAL32));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD3> entityList = taPaperBaD3Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxValueVo> voList = new ArrayList<>();
		BasicAnalysisTaxValueVo vo = null;
		for (TaPaperBaD3 entity : entityList) {
			vo = new BasicAnalysisTaxValueVo();
			vo.setGoodsDescText(entity.getGoodsDescText());
			vo.setTaxQty(entity.getTaxQty());
			vo.setInformPrice(entity.getInformPrice());
			vo.setGoodsValueAmt(entity.getGoodsValueAmt());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxValueVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD3> entityList = new ArrayList<>();
		TaPaperBaD3 entity = null;
		int i = 1;
		for (BasicAnalysisTaxValueVo vo : voList) {
			entity = new TaPaperBaD3();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxQty(vo.getTaxQty());
			entity.setInformPrice(vo.getInformPrice());
			entity.setGoodsValueAmt(vo.getGoodsValueAmt());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD3Repository.saveAll(entityList);
	}

}
