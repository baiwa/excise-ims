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

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD4Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRateVo;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;

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

		List<WsAnafri0001D> wsAna0001DList = wsAnafri0001DRepository.findProductListByBasicAnalysisFormVo(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
	
		List<BasicAnalysisTaxRateVo> voList = new ArrayList<>();
		BasicAnalysisTaxRateVo vo = null;
		
		for (WsAnafri0001D wsAnafri0001D : wsAna0001DList) {
			vo = new BasicAnalysisTaxRateVo();
			vo.setGoodsDesc(wsAnafri0001D.getProductName());
			vo.setTaxRateByPrice(wsAnafri0001D.getValueRate());
			vo.setTaxRateByQty(wsAnafri0001D.getQtyRate());
			BigDecimal anaTaxRateByPrice = null;
			BigDecimal anaTaxRateByQty = null;
			BigDecimal diffTaxRateByPrice = null;
			BigDecimal diffTaxRateByQty = null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
