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

import th.go.excise.ims.ta.persistence.entity.TaPaperBaD1;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD1Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxQtyVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxQtyService extends AbstractBasicAnalysisService<BasicAnalysisTaxQtyVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxQtyService.class);

	@Autowired
	private TaPaperBaD1Repository taPaperBaD1Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxQtyVo> voList = new ArrayList<BasicAnalysisTaxQtyVo>();
		BasicAnalysisTaxQtyVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxQtyVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxQty(anafri0001Vo.getProductQty());
			vo.setMonthStatementTaxQty(BigDecimal.ZERO); // FIXME Find Month Statement Tax Qty from WS OASFRI0100
			vo.setDiffTaxQty(vo.getMonthStatementTaxQty().subtract(vo.getTaxQty()));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD1> entityList = taPaperBaD1Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxQtyVo> voList = new ArrayList<>();
		BasicAnalysisTaxQtyVo vo = null;
		for (TaPaperBaD1 entity : entityList) {
			vo = new BasicAnalysisTaxQtyVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxQty(entity.getTaxQty());
			vo.setMonthStatementTaxQty(entity.getMonthStmtTaxQty());
			vo.setDiffTaxQty(entity.getDiffTaxQty());
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxQtyVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD1> entityList = new ArrayList<>();
		TaPaperBaD1 entity = null;
		int i = 1;
		for (BasicAnalysisTaxQtyVo vo : voList) {
			entity = new TaPaperBaD1();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxQty(vo.getTaxQty());
			entity.setMonthStmtTaxQty(vo.getMonthStatementTaxQty());
			entity.setDiffTaxQty(vo.getDiffTaxQty());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD1Repository.saveAll(entityList);
	}

}
