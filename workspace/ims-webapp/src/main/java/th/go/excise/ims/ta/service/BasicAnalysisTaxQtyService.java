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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD7;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD1Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastMonthVo;
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
		List<BasicAnalysisTaxQtyVo> voList = new ArrayList<BasicAnalysisTaxQtyVo>();
		LocalDate localDateStart = LocalDate
				.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]),
						Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]),
				Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));

		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth())
				.format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);

		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(),
				formVo.getDutyGroupId(), dateStart, dateEnd);
		BasicAnalysisTaxQtyVo dataSet = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			dataSet = new BasicAnalysisTaxQtyVo();
			dataSet.setGoodsDesc(anafri0001Vo.getProductName());
			dataSet.setTaxQty(anafri0001Vo.getProductQty());
			dataSet.setMonthStatementTaxQty(null);
			dataSet.setDiffTaxQty(new BigDecimal(0));
			voList.add(dataSet);
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
		List<BasicAnalysisTaxQtyVo> dataSaveList = inquiryByWs(formVo);
		int i = 1;
		TaPaperBaD1 entity = null;
		for (BasicAnalysisTaxQtyVo saveData : dataSaveList) {
			entity = new TaPaperBaD1();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(saveData.getGoodsDesc());
			entity.setTaxQty(saveData.getTaxQty());
//			entity.setMonthStatementTaxQty(saveData.getMonthStatementTaxQty());
			entity.setDiffTaxQty(saveData.getDiffTaxQty());
			taPaperBaD1Repository.save(entity);
			i++;
		}
	}

}
