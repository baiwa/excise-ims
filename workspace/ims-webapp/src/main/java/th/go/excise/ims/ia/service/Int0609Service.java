package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.vo.Int0609TableVo;
import th.go.excise.ims.ia.vo.Int0609Vo;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.ws.persistence.repository.WsGfr01051JdbcRepository;

@Service
public class Int0609Service {

	@Autowired
	private WsGfr01051JdbcRepository wsGfr01051JdbcRepository;

	public Int0609Vo search(SearchVo request) {
		/* __________ set year TH to EN __________ */
		request.setPeriodMonth(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodMonth(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_EN));

		/* __________ filter from condition display __________ */
		List<Int0609TableVo> dataRes = wsGfr01051JdbcRepository.findByFilter(request);

		/* __________ incomeCode = '115010' __________ */
		BigDecimal sum4_I = BigDecimal.ZERO;
		request.setIncomeCode("115010");
		List<Int0609TableVo> sum4_IList = wsGfr01051JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : dataRes) {
			if (sum4_IList.size() > 0) {
				for (Int0609TableVo int0609TableVo : sum4_IList) {
					String dateStr = ConvertDateUtils.formatDateToString(int0609TableVo.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4I(int0609TableVo.getSum4());
						sum4_I = sum4_I.add(NumberUtils.nullToZero(int0609TableVo.getSum4()));
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
		List<Int0609TableVo> sum4_IIList = wsGfr01051JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : dataRes) {
			if (sum4_IIList.size() > 0) {
				for (Int0609TableVo int0609TableVo : sum4_IIList) {
					String dateStr = ConvertDateUtils.formatDateToString(int0609TableVo.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4II(int0609TableVo.getSum4());
						sum4_II = sum4_II.add(NumberUtils.nullToZero(int0609TableVo.getSum4()));
					} else {
						r.setSum4II(BigDecimal.ZERO);
					}
				}
			} else {
				r.setSum4II(BigDecimal.ZERO);
			}
		}

		/* __________ set footer __________ */
		List<Int0609TableVo> summary = wsGfr01051JdbcRepository.summaryByResult(request);
		Int0609TableVo footer = new Int0609TableVo();
		if (summary.size() > 0) {
			footer.setCnt(summary.get(0).getCnt());
			footer.setSum1Sum2(summary.get(0).getSum1Sum2());
			footer.setSum4Sum5(summary.get(0).getSum4Sum5());
			footer.setSum7(summary.get(0).getSum7());
			footer.setTotalAmt(summary.get(0).getTotalAmt());
			footer.setTotalSendAmt(summary.get(0).getTotalSendAmt());
		}
		footer.setSum4I(sum4_I);
		footer.setSum4II(sum4_II);

		/* __________ set response __________ */
		Int0609Vo response = new Int0609Vo();
		response.setFooter(footer);
		response.setTable(dataRes);
		return response;
	}

}
