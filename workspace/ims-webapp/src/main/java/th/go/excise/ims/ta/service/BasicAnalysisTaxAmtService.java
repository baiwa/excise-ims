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

import th.go.excise.ims.ta.persistence.repository.TaPaperBaD5Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxAmtVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;

@Service
public class BasicAnalysisTaxAmtService extends AbstractBasicAnalysisService<BasicAnalysisTaxAmtVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxAmtService.class);

	@Autowired
	private TaPaperBaD5Repository taPaperBaD5Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	
	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		List<BasicAnalysisTaxAmtVo> voList = new ArrayList<BasicAnalysisTaxAmtVo>();
		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
		
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001D> wsAna0001DList = wsAnafri0001DRepository.findProductListByBasicAnalysisFormVo(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		BasicAnalysisTaxAmtVo dataSet = null;
		for (WsAnafri0001D wsAna0001D : wsAna0001DList) {
			dataSet = new BasicAnalysisTaxAmtVo();
			dataSet.setGoodsDesc(wsAna0001D.getProductName());
			dataSet.setAnaTaxByValAmt(wsAna0001D.getTaxValueAmt());
			dataSet.setAnaTaxByQtyAmt(wsAna0001D.getTaxQuantityAmt());
			dataSet.setSumAnaTaxAmt(wsAna0001D.getTaxAmt());
			voList.add(dataSet);
		}
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub

	}

}
