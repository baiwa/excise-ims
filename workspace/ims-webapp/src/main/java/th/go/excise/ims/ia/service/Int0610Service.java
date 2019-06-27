package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncGfRepository;
import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610SumVo;
import th.go.excise.ims.ia.vo.Int0610TabVo;
import th.go.excise.ims.ia.vo.Int0610Vo;
import th.go.excise.ims.ws.client.pcc.incfri8020.service.IncFri8020Service;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8020IncRepository;

@Service
public class Int0610Service {

	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;

	@Autowired
	private IncFri8020Service incFri8020Service;

	@Autowired
	private IaAuditIncGfRepository iaAuditIncGfRepository;

	public List<Int0610Vo> getTabs(Int0610SearchVo request) throws Exception {
		List<Int0610Vo> response = new ArrayList<Int0610Vo>();

		String periodMonthFromStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodFrom().substring(0, 2)) - 1];
		String periodMonthToStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodTo().substring(0, 2)) - 1];
		String yearFromEN = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodFrom(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY,
				ConvertDateUtils.LOCAL_EN);
		String yearToEN = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodTo(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN);
		String yearFromTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodFrom(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY,
				ConvertDateUtils.LOCAL_TH);
		String yearToTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodTo(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH);

		request.setPeriodFromStr(yearFromEN.concat(periodMonthFromStr));
		request.setPeriodToStr(yearToEN.concat(periodMonthToStr));
		request.setPeriodFromDate(ExciseUtils.firstDateOfPeriod(periodMonthFromStr, yearFromTH));
		request.setPeriodToDate(ExciseUtils.firstDateOfPeriod(periodMonthToStr, yearToTH));
		request.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(request.getOfficeCode()));

		/* _________ find amount tabs _________ */
		List<WsIncfri8020Inc> tabsAmount = wsIncfri8020IncRepository.findTabs(request.getOfficeCode());

		List<Int0610TabVo> dataList = null;
		Int0610Vo tab = null;
		Int0610TabVo data = null;
		List<Int0610SumVo> summary = null;
		List<Int0610SumVo> dataFilter = null;
		for (WsIncfri8020Inc t : tabsAmount) {
			tab = new Int0610Vo();
			dataList = new ArrayList<Int0610TabVo>();
			summary = new ArrayList<Int0610SumVo>();

			request.setDeptDisb(t.getDeptDisb());
			request.setGlAccNo(t.getGlAccNo());
			summary = wsIncfri8020IncRepository.summaryByDisburseUnit(request);

			if (summary.size() > 0) {
				/* _______ loop for find key _______ */
				for (Int0610SumVo s : summary) {
					data = new Int0610TabVo();
					BigDecimal sumCarryForward = BigDecimal.ZERO;

					/* _______ find data key == accNo. _______ */
					dataFilter = summary.stream().filter(f -> s.getIncomeCode().equals(f.getIncomeCode())).collect(Collectors.toList());

					if (dataFilter.size() > 0) {
						for (Int0610SumVo int0610SumVo : dataFilter) {
							/* _______ sum carry forward _______ */
							sumCarryForward.add(NumberUtils.nullToZero(int0610SumVo.getCarryForward()));
						}
						data.setSummary(dataFilter);
						data.setAccNo(s.getAccNo());
						data.setAccName(dataFilter.get(0).getAccName());
						data.setCarryForward(sumCarryForward);
					}
					dataList.add(data);
				}
				tab.setTab(dataList);
				tab.setDeptDisb(t.getDeptDisb());
				tab.setGlAccNo(t.getGlAccNo());
				response.add(tab);
			}
		}

		return response;
	}

}
