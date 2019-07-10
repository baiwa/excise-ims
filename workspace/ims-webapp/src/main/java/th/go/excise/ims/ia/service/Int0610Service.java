package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfd;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfh;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncGfdRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncGfhRepository;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int0610HeaderVo;
import th.go.excise.ims.ia.vo.Int0610SaveVo;
import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610SumVo;
import th.go.excise.ims.ia.vo.Int0610TabVo;
import th.go.excise.ims.ia.vo.Int0610Vo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8020IncRepository;

@Service
public class Int0610Service {

	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;

	@Autowired
	private ExciseOrgGfmisService exciseOrgGfmisService;

	@Autowired
	private IaAuditIncGfhRepository iaAuditIncGfhRepository;

	@Autowired
	private IaAuditIncGfdRepository iaAuditIncGfdRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;

	public List<Int0610Vo> getTabs(Int0610SearchVo request) throws Exception {
		List<Int0610Vo> response = new ArrayList<Int0610Vo>();

		/* _________ set request _________ */
		String periodMonthFromStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodFrom().substring(0, 2)) - 1];
		String periodMonthToStr = ExciseUtils.PERIOD_MONTH_STR[Integer.parseInt(request.getPeriodTo().substring(0, 2)) - 1];
		String yearFromTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodFrom(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY,
				ConvertDateUtils.LOCAL_TH);
		String yearToTH = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodTo(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH);

		request.setPeriodFromStr(ExciseUtils.monthYearStrOfPeriod(request.getPeriodFrom().split("/")[0], request.getPeriodFrom().split("/")[1]));
		request.setPeriodToStr(ExciseUtils.monthYearStrOfPeriod(request.getPeriodTo().split("/")[0], request.getPeriodTo().split("/")[1]));
		request.setPeriodFromDate(ExciseUtils.firstDateOfPeriod(periodMonthFromStr, yearFromTH));
		request.setPeriodToDate(ExciseUtils.firstDateOfPeriod(periodMonthToStr, yearToTH));

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
			summary = wsIncfri8020IncRepository.summaryByDisburseUnit(request);

			if (summary.size() > 0) {
				/* _______ loop for find key _______ */
				for (Int0610SumVo s : summary) {
					data = new Int0610TabVo();
					BigDecimal sumCarryForward = BigDecimal.ZERO;
					BigDecimal sumNetTaxAmt = BigDecimal.ZERO;

					/* _______ find data key == accNo. _______ */
					dataFilter = summary.stream().filter(f -> s.getIncomeCode().equals(f.getIncomeCode())).collect(Collectors.toList());

					if (dataFilter.size() > 0) {
						for (Int0610SumVo int0610SumVo : dataFilter) {
							/* _______ sum carry forward _______ */
							sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(int0610SumVo.getCarryForward()));
							sumNetTaxAmt = sumNetTaxAmt.add(NumberUtils.nullToZero(int0610SumVo.getNetTaxAmt()));
						}
						data.setSummary(dataFilter);
						data.setAccNo(s.getAccNo());
						data.setAccName(dataFilter.get(0).getAccName());
						data.setCarryForward(sumCarryForward);
						data.setDifference(sumNetTaxAmt.subtract(sumCarryForward));
					}
					dataList.add(data);
				}
				tab.setTab(dataList);
				tab.setExciseOrgDisb(exciseOrgGfmisService.findExciseOrgGfmisByGfDisburseUnit(t.getDeptDisb()));
				tab.setOfficeCode(request.getOfficeCode());
				tab.setIncMonthFrom(request.getPeriodFromStr().substring(4, 7));
				tab.setIncMonthTo(request.getPeriodToStr().substring(4, 7));
				tab.setIncYearFrom(request.getPeriodFromStr().substring(0, 4));
				tab.setIncYearTo(request.getPeriodToStr().substring(0, 4));
				response.add(tab);
			}
		}

		return response;
	}

	public void save(Int0610SaveVo request) {
		/* ________ generate auditIncGfNo ________ */
		String auditIncGfNo = iaCommonService.autoGetRunAuditNoBySeqName("AIG", request.getHeader().getOfficeCode(), "AUDIT_INC_GF_NO_SEQ", 8);
		request.getHeader().setAuditIncGfNo(auditIncGfNo);
		/* ________ save header ________ */
		iaAuditIncGfhRepository.save(request.getHeader());

		for (IaAuditIncGfd entity : request.getDetails()) {
			entity.setAuditIncGfNo(auditIncGfNo);
		}
		/* ________ save details ________ */
		iaAuditIncGfdRepository.saveAll(request.getDetails());
	}

	public List<IaAuditIncGfh> getAuditIncGfNoDropdown() {
		return iaAuditIncGfhRepository.findAuditIncGfNoOrderByAuditIncGfNo();
	}

	public Int0610HeaderVo findByAuditIncGfNo(String auditIncGfNo) {
		List<Int0610Vo> tabList = new ArrayList<Int0610Vo>();
		Int0610Vo tab = null;
		List<Int0610TabVo> dataList = null;
		Int0610TabVo data = null;
		List<Int0610SumVo> summaryList = null;
		Int0610SumVo summary = null;

		IaAuditIncGfh header = iaAuditIncGfhRepository.findByAuditIncGfNoAndIsDeleted(auditIncGfNo, "N");
		List<IaAuditIncGfd> details = iaAuditIncGfdRepository.findByAuditIncGfNoAndIsDeleted(auditIncGfNo, "N");

		for (IaAuditIncGfd d : details) {
			summaryList = new ArrayList<>();
			List<IaAuditIncGfd> dataFilter = details.stream().filter(f -> d.getDisburseUnit().equals(f.getDisburseUnit())).collect(Collectors.toList());
			for (IaAuditIncGfd iaAuditIncGfd : dataFilter) {
				summary = new Int0610SumVo();
				summary.setIncomeCode(iaAuditIncGfd.getIncomeCode());
				summary.setIncomeName(wsIncfri8020IncRepository.findIncomeName(iaAuditIncGfd.getIncomeCode()));
				summary.setNetTaxAmt(iaAuditIncGfd.getIncNetTaxAmt());
				summaryList.add(summary);
			}

			data = new Int0610TabVo();
			if (StringUtils.isNotBlank(d.getGlAccNo())) {
				data.setAccNo(d.getGlAccNo());
				data.setAccName(iaGftrialBalanceRepository.findAccName(d.getGlAccNo()));
			}
			data.setCarryForward(d.getGlNetTaxAmt());
			data.setDifference(summary.getNetTaxAmt().subtract(data.getCarryForward()));
			data.setSummary(summaryList);

			dataList = new ArrayList<>();
			dataList.add(data);

			tab = new Int0610Vo();
			tab.setTab(dataList);
			tab.setExciseOrgDisb(exciseOrgGfmisService.findExciseOrgGfmisByGfDisburseUnit(d.getDisburseUnit()));
			tab.setOfficeCode(header.getOfficeCode());
			tabList.add(tab);
		}

		Int0610HeaderVo response = new Int0610HeaderVo();
		response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(header.getOfficeCode()));
		response.setAuditIncGfNo(auditIncGfNo);
		response.setAuditFlag(header.getAuditFlag());
		response.setIncgfConditionText(header.getIncgfConditionText());
		response.setIncgfCreteriaText(header.getIncgfCreteriaText());
		response.setMonthPeriodFrom(ConvertDateUtils.formatDateToString(ExciseUtils.firstDateOfPeriod(header.getIncMonthFrom(), header.getIncYearFrom()), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH));
		response.setMonthPeriodTo(ConvertDateUtils.formatDateToString(ExciseUtils.lastDateOfPeriod(header.getIncMonthTo(), header.getIncYearTo()), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH));
		response.setDataList(tabList);

		return response;
	}

}
