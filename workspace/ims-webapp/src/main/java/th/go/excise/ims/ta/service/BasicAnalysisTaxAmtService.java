package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaPaperBaD5;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD5Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxAmtVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

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
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxAmtVo> voList = new ArrayList<BasicAnalysisTaxAmtVo>();
		BasicAnalysisTaxAmtVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxAmtVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxSubmissionDate(""); // FIXME anafri0001Vo.getRegInDate()
			vo.setNetTaxByValue(BigDecimal.ZERO); // FIXME Calculate
			vo.setNetTaxByQty(BigDecimal.ZERO); // FIXME Calculate
			vo.setNetTaxAmt(BigDecimal.ZERO); // FIXME Calculate
			vo.setAnaNetTaxByValue(anafri0001Vo.getTaxValueAmt());
			vo.setAnaNetTaxByQty(anafri0001Vo.getTaxQuantityAmt());
			vo.setAnaNetTaxAmt(anafri0001Vo.getTaxAmt());
			vo.setDiffNetTaxByValue(vo.getAnaNetTaxByValue().subtract(vo.getNetTaxByValue()));
			vo.setDiffNetTaxByQty(vo.getAnaNetTaxByQty().subtract(vo.getNetTaxByQty()));
			vo.setDiffNetTaxAmt(vo.getAnaNetTaxAmt().subtract(vo.getNetTaxAmt()));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD5> entityList = taPaperBaD5Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxAmtVo> voList = new ArrayList<>();
		BasicAnalysisTaxAmtVo vo = null;
		for (TaPaperBaD5 entity : entityList) {
			vo = new BasicAnalysisTaxAmtVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxSubmissionDate(""); // FIXME Convert Date
			vo.setNetTaxByValue(entity.getNetTaxByValue());
			vo.setNetTaxByQty(entity.getNetTaxByQty());
			vo.setNetTaxAmt(entity.getNetTaxAmt());
			vo.setAnaNetTaxByValue(entity.getAnaNetTaxByValue());
			vo.setAnaNetTaxByQty(entity.getAnaNetTaxByQty());
			vo.setAnaNetTaxAmt(entity.getAnaNetTaxAmt());
			vo.setDiffNetTaxByValue(entity.getDiffNetTaxByValue());
			vo.setDiffNetTaxByQty(entity.getDiffNetTaxByQty());
			vo.setDiffNetTaxAmt(entity.getDiffNetTaxAmt());
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxAmtVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD5> entityList = new ArrayList<>();
		TaPaperBaD5 entity = null;
		int i = 1;
		for (BasicAnalysisTaxAmtVo vo : voList) {
			entity = new TaPaperBaD5();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxSubmissionDate(null); // FIXME Convert Date
			entity.setNetTaxByValue(vo.getNetTaxByValue());
			entity.setNetTaxByQty(vo.getNetTaxByQty());
			entity.setNetTaxAmt(vo.getNetTaxAmt());
			entity.setAnaNetTaxByValue(vo.getAnaNetTaxByValue());
			entity.setAnaNetTaxByQty(vo.getAnaNetTaxByQty());
			entity.setAnaNetTaxAmt(vo.getAnaNetTaxAmt());
			entity.setDiffNetTaxByValue(vo.getAnaNetTaxByValue());
			entity.setDiffNetTaxByQty(vo.getAnaNetTaxByQty());
			entity.setDiffNetTaxAmt(vo.getAnaNetTaxAmt());
			i++;
		}
		
		taPaperBaD5Repository.saveAll(entityList);
	}

}
