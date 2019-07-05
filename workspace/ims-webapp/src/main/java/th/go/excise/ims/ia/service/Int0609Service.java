package th.go.excise.ims.ia.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendD;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncSendDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncSendHRepository;
import th.go.excise.ims.ia.vo.IaAuditIncSendDVo;
import th.go.excise.ims.ia.vo.Int0609SaveVo;
import th.go.excise.ims.ia.vo.Int0609TableVo;
import th.go.excise.ims.ia.vo.Int0609Vo;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.ia.vo.WsIncr0003Vo;
import th.go.excise.ims.ws.persistence.repository.WsGfr01051JdbcRepository;
import th.go.excise.ims.ws.persistence.repository.WsIncr0003JdbcRepository;

@Service
public class Int0609Service {

	@Autowired
	private WsGfr01051JdbcRepository wsGfr01051JdbcRepository;

	@Autowired
	private WsIncr0003JdbcRepository wsIncr0003JdbcRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaAuditIncSendHRepository iaAuditIncSendHRepository;

	@Autowired
	private IaAuditIncSendDRepository iaAuditIncSendDRepository;

	public Int0609Vo search(SearchVo request) {
		/* __________ set year TH to EN __________ */
		request.setPeriodMonth(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodMonth(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_EN));

		/* __________ filter from condition display __________ */
		List<Int0609TableVo> resWsGfr01051 = wsGfr01051JdbcRepository.findByFilter(request);
		List<WsIncr0003Vo> resWsIncr0003 = wsIncr0003JdbcRepository.findByFilter(request);

		/* __________ loop for map object __________ */
		for (Int0609TableVo wsGfr01051 : resWsGfr01051) {
			/* __________ calculate difference day__________ */
			long diffInMillies = Math.abs(wsGfr01051.getGfDate().getTime() - wsGfr01051.getTrnDate().getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			wsGfr01051.setDateDiff(diff);

			String dateStrHeader = ConvertDateUtils.formatDateToString(wsGfr01051.getTrnDate(), ConvertDateUtils.DD_MM_YY);
			wsGfr01051.setTrnDateStr(dateStrHeader);
			wsGfr01051.setGfDateStr(ConvertDateUtils.formatDateToString(wsGfr01051.getGfDate(), ConvertDateUtils.DD_MM_YY));
			for (WsIncr0003Vo wsIncr0003Vo : resWsIncr0003) {
				String dateStr = ConvertDateUtils.formatDateToString(wsIncr0003Vo.getTrnDate(), ConvertDateUtils.DD_MM_YY);

				if (dateStrHeader.equals(dateStr)) {
					wsGfr01051.setSum1Sum2(wsIncr0003Vo.getSum1Sum2());
					wsGfr01051.setSum4Sum5(wsIncr0003Vo.getSum4Sum5());
					wsGfr01051.setSum7(wsIncr0003Vo.getSum7());
				}
			}
		}

		/* __________ incomeCode = '115010' __________ */
		BigDecimal sum4_I = BigDecimal.ZERO;
		request.setIncomeCode("115010");
		List<WsIncr0003Vo> sum4_IList = wsIncr0003JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : resWsGfr01051) {
			if (sum4_IList.size() > 0) {
				String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
				for (WsIncr0003Vo I : sum4_IList) {
					String dateStr = ConvertDateUtils.formatDateToString(I.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4I(I.getSum4());
						sum4_I = sum4_I.add(NumberUtils.nullToZero(I.getSum4()));
					} else {
						r.setSum4I(BigDecimal.ZERO);
					}
				}
			} else {
				r.setSum4I(BigDecimal.ZERO);
			}
		}

		/* __________ incomeCode = '116010' __________ */
		BigDecimal sum4_II = BigDecimal.ZERO;
		request.setIncomeCode("116010");
		List<WsIncr0003Vo> sum4_IIList = wsIncr0003JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : resWsGfr01051) {
			if (sum4_IIList.size() > 0) {
				for (WsIncr0003Vo II : sum4_IIList) {
					String dateStr = ConvertDateUtils.formatDateToString(II.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4II(II.getSum4());
						sum4_II = sum4_II.add(NumberUtils.nullToZero(II.getSum4()));
					} else {
						r.setSum4II(BigDecimal.ZERO);
					}
				}
			} else {
				r.setSum4II(BigDecimal.ZERO);
			}
		}

		/* __________ set footer __________ */
		Int0609TableVo sumWsGfr01051 = wsGfr01051JdbcRepository.summaryByRequest(request);
		WsIncr0003Vo sumWsIncr0003 = wsIncr0003JdbcRepository.summaryByRequest(request);
		Int0609TableVo footer = new Int0609TableVo();
		if (sumWsGfr01051 != null) {
			footer.setCnt(sumWsGfr01051.getCnt());
			footer.setTotalAmt(sumWsGfr01051.getTotalAmt());
			footer.setTotalSendAmt(sumWsGfr01051.getTotalSendAmt());
		}
		if (sumWsIncr0003 != null) {
			footer.setSum1Sum2(sumWsIncr0003.getSum1Sum2());
			footer.setSum4Sum5(sumWsIncr0003.getSum4Sum5());
			footer.setSum7(sumWsIncr0003.getSum7());
		}
		footer.setSum4I(sum4_I);
		footer.setSum4II(sum4_II);

		/* __________ set response __________ */
		Int0609Vo response = new Int0609Vo();
		response.setFooter(footer);
		response.setTable(resWsGfr01051);
		return response;
	}

	public void save(Int0609SaveVo request) throws Exception {
		String incsendNo = iaCommonService.autoGetRunAuditNoBySeqName("AIS", request.getHeader().getIncsendOfficeCode(), "INCSEND_NO_SEQ", 8);

		/* __________ header __________ */
		request.getHeader().setIncsendNo(incsendNo);
		request.getHeader().setIncsendPeriodMonth(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getHeader().getIncsendPeriodMonth(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYYMM));
		iaAuditIncSendHRepository.save(request.getHeader());

		/* __________ details __________ */
		IaAuditIncSendD detail = null;
		for (IaAuditIncSendDVo d : request.getDetails()) {
			detail = new IaAuditIncSendD();
			BeanUtils.copyProperties(detail, d);
			detail.setIncsendNo(incsendNo);
			detail.setIncsendTrnDate(ConvertDateUtils.parseStringToDate(d.getIncsendTrnDateStr(), ConvertDateUtils.DD_MM_YY));
			detail.setIncsendGfDate(ConvertDateUtils.parseStringToDate(d.getIncsendGfDateStr(), ConvertDateUtils.DD_MM_YY));
			iaAuditIncSendDRepository.save(detail);
		}
	}

}
