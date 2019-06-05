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

import th.go.excise.ims.ta.persistence.entity.TaPaperBaD2;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD2Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRetailPriceVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxRetailPriceService extends AbstractBasicAnalysisService<BasicAnalysisTaxRetailPriceVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRetailPriceService.class);

	@Autowired
	private TaPaperBaD2Repository taPaperBaD2Repository;

	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");

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

		List<BasicAnalysisTaxRetailPriceVo> voList = new ArrayList<>();
		BasicAnalysisTaxRetailPriceVo vo = null;
		BigDecimal informPrice = null;
		BigDecimal diffTaxInformPrice = null;

		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxRetailPriceVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxInformPrice(anafri0001Vo.getProductPrice());
			vo.setInformPrice(informPrice);
			vo.setDiffTaxInformPrice(diffTaxInformPrice);
			voList.add(vo);
		}
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		List<BasicAnalysisTaxRetailPriceVo> dataSaveList = inquiryByWs(formVo);
		int i = 1;
		TaPaperBaD2 entity = null;
		for (BasicAnalysisTaxRetailPriceVo saveData : dataSaveList) {
			entity = new TaPaperBaD2();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(saveData.getGoodsDesc());
			entity.setTaxInformPrice(saveData.getTaxInformPrice());
			entity.setInformPrice(saveData.getInformPrice());
			entity.setDiffTaxInformPrice(saveData.getDiffTaxInformPrice());
			taPaperBaD2Repository.save(entity);
			i++;
		}
	}

}
