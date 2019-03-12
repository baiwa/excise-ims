package th.go.excise.ims.ta.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubRisk;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubRiskRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubRiskRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Service
public class DraftWorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(DraftWorksheetService.class);

	private static final String NO_TAX_AMOUNT = "-";

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;

	@Autowired
	private TaMasCondMainHdrRepository taMasCondMainHdrRepository;
	@Autowired
	private TaMasCondMainDtlRepository taMasCondMainDtlRepository;
	@Autowired
	private TaMasCondSubCapitalRepository taMasCondSubCapitalRepository;
	@Autowired
	private TaMasCondSubRiskRepository taMasCondSubRiskRepository;
	@Autowired
	private TaMasCondSubNoAuditRepository taMasCondSubNoAuditRepository;

	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;
	@Autowired
	private TaWorksheetCondSubCapitalRepository taWorksheetCondSubCapitalRepository;
	@Autowired
	private TaWorksheetCondSubRiskRepository taWorksheetCondSubRiskRepository;
	@Autowired
	private TaWorksheetCondSubNoAuditRepository taWorksheetCondSubNoAuditRepository;

	@Autowired
	private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;
	@Autowired
	private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
		TaxOperatorVo vo = new TaxOperatorVo();
		try {
			List<TaxOperatorDetailVo> taxOperatorDetailVoList = prepareTaxOperatorDetailVoList(formVo);
			vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(taxOperatorDetailVoList, formVo));
			vo.setCount(taWsReg4000Repository.countByCriteria(formVoToWsReg4000(formVo)));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;
	}

	private List<TaxOperatorDetailVo> prepareTaxOperatorDetailVoList(TaxOperatorFormVo formVo) {
		logger.info("prepareTaxOperatorDetailVoList startDate={}, endDate={}, dateRange={}", formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());

		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		List<TaWsReg4000> wsReg4000List = taWsReg4000Repository.findByCriteria(formVoToWsReg4000(formVo), formVo.getStart(), formVo.getLength());
		Map<String, List<TaWsInc8000M>> wsInc8000MMap = taWsInc8000MRepository.findByMonthRange(ymStart, ymEnd);
		List<TaWsInc8000M> wsInc8000MList = null;
		BigDecimal sumTaxAmtG1 = null;
		BigDecimal sumTaxAmtG2 = null;
		BigDecimal taxAmtChnPnt = null;
		ExciseDept exciseDeptSector = null;
		ExciseDept exciseDeptArea = null;
		String taxAmount = null;
		List<Double> taxAmountList = null;

		TaxOperatorDetailVo detailVo = null;
		List<TaxOperatorDetailVo> detailVoList = new ArrayList<>();
		for (TaWsReg4000 wsReg4000 : wsReg4000List) {
			logger.debug("wsReg4000.newRegId={}", wsReg4000.getNewRegId());

			int countTaxMonthNo = 0;
			int countG1 = 0;
			int countG2 = 0;
			sumTaxAmtG1 = BigDecimal.ZERO;
			sumTaxAmtG2 = BigDecimal.ZERO;
			taxAmountList = new ArrayList<>();

			detailVo = new TaxOperatorDetailVo();
			detailVo.setDutyCode(wsReg4000.getDutyCode());
			detailVo.setDutyName(ExciseUtils.getDutyDesc(wsReg4000.getDutyCode()));
			detailVo.setNewRegId(wsReg4000.getNewRegId());
			detailVo.setCusFullname(wsReg4000.getCusFullname());
			detailVo.setFacFullname(wsReg4000.getFacFullname());
			detailVo.setFacAddress(wsReg4000.getFacAddress());
			detailVo.setOfficeCode(wsReg4000.getOfficeCode());
			detailVo.setRegCapital(wsReg4000.getRegCapital());
			exciseDeptSector = ApplicationCache.getExciseDept(wsReg4000.getOfficeCode().substring(0, 2) + "0000");

			if (exciseDeptSector != null) {
				detailVo.setSecCode(exciseDeptSector.getOfficeCode());
				detailVo.setSecDesc(exciseDeptSector.getDeptShortName());
			}
			exciseDeptArea = ApplicationCache.getExciseDept(wsReg4000.getOfficeCode().substring(0, 4) + "00");

			if (exciseDeptArea != null) {
				detailVo.setAreaCode(exciseDeptArea.getOfficeCode());
				detailVo.setAreaDesc(exciseDeptArea.getDeptShortName());
			}

			wsInc8000MList = wsInc8000MMap.get(wsReg4000.getNewRegId());
			if (CollectionUtils.isEmpty(wsInc8000MList)) {
				// Set Default Value
				taxAmount = NO_TAX_AMOUNT;
				detailVo.setTaxAmtG1M1(taxAmount);
				detailVo.setTaxAmtG1M2(taxAmount);
				detailVo.setTaxAmtG1M3(taxAmount);
				detailVo.setTaxAmtG1M4(taxAmount);
				detailVo.setTaxAmtG1M5(taxAmount);
				detailVo.setTaxAmtG1M6(taxAmount);
				detailVo.setTaxAmtG1M7(taxAmount);
				detailVo.setTaxAmtG1M8(taxAmount);
				detailVo.setTaxAmtG1M9(taxAmount);
				detailVo.setTaxAmtG1M10(taxAmount);
				detailVo.setTaxAmtG1M11(taxAmount);
				detailVo.setTaxAmtG1M12(taxAmount);
				detailVo.setTaxAmtG2M1(taxAmount);
				detailVo.setTaxAmtG2M2(taxAmount);
				detailVo.setTaxAmtG2M3(taxAmount);
				detailVo.setTaxAmtG2M4(taxAmount);
				detailVo.setTaxAmtG2M5(taxAmount);
				detailVo.setTaxAmtG2M6(taxAmount);
				detailVo.setTaxAmtG2M7(taxAmount);
				detailVo.setTaxAmtG2M8(taxAmount);
				detailVo.setTaxAmtG2M9(taxAmount);
				detailVo.setTaxAmtG2M10(taxAmount);
				detailVo.setTaxAmtG2M11(taxAmount);
				detailVo.setTaxAmtG2M12(taxAmount);
				detailVo.setSumTaxAmtG1(taxAmount);
				detailVo.setSumTaxAmtG2(taxAmount);
				detailVo.setTaxMonthNo(String.valueOf(BigDecimal.ZERO));
				detailVo.setTaxAmtChnPnt(taxAmount);
				detailVo.setTaxAmtSd(taxAmount);
				detailVo.setTaxAmtMean(taxAmount);
				detailVo.setTaxAmtMaxPnt(taxAmount);
				detailVo.setTaxAmtMinPnt(taxAmount);
				detailVoList.add(detailVo);
				continue;
			}

			for (TaWsInc8000M wsInc8000M : wsInc8000MList) {
				if (countG1 < formVo.getDateRange() / 2) {
					// Group 1
					if (wsInc8000M.getTaxAmount() != null) {
						taxAmount = wsInc8000M.getTaxAmount().toString();
						taxAmountList.add(wsInc8000M.getTaxAmount().doubleValue());
						sumTaxAmtG1 = sumTaxAmtG1.add(wsInc8000M.getTaxAmount());
						countTaxMonthNo++;
					} else {
						taxAmount = NO_TAX_AMOUNT;
					}
					countG1++;
					if (countG1 == 1) {
						detailVo.setTaxAmtG1M1(taxAmount);
					} else if (countG1 == 2) {
						detailVo.setTaxAmtG1M2(taxAmount);
					} else if (countG1 == 3) {
						detailVo.setTaxAmtG1M3(taxAmount);
					} else if (countG1 == 4) {
						detailVo.setTaxAmtG1M4(taxAmount);
					} else if (countG1 == 5) {
						detailVo.setTaxAmtG1M5(taxAmount);
					} else if (countG1 == 6) {
						detailVo.setTaxAmtG1M6(taxAmount);
					} else if (countG1 == 7) {
						detailVo.setTaxAmtG1M7(taxAmount);
					} else if (countG1 == 8) {
						detailVo.setTaxAmtG1M8(taxAmount);
					} else if (countG1 == 9) {
						detailVo.setTaxAmtG1M9(taxAmount);
					} else if (countG1 == 10) {
						detailVo.setTaxAmtG1M10(taxAmount);
					} else if (countG1 == 11) {
						detailVo.setTaxAmtG1M11(taxAmount);
					} else if (countG1 == 12) {
						detailVo.setTaxAmtG1M12(taxAmount);
					}
				} else {
					// Group 2
					if (wsInc8000M.getTaxAmount() != null) {
						taxAmount = wsInc8000M.getTaxAmount().toString();
						taxAmountList.add(wsInc8000M.getTaxAmount().doubleValue());
						sumTaxAmtG2 = sumTaxAmtG2.add(wsInc8000M.getTaxAmount());
						countTaxMonthNo++;
					} else {
						taxAmount = NO_TAX_AMOUNT;
					}
					countG2++;
					if (countG2 == 1) {
						detailVo.setTaxAmtG2M1(taxAmount);
					} else if (countG2 == 2) {
						detailVo.setTaxAmtG2M2(taxAmount);
					} else if (countG2 == 3) {
						detailVo.setTaxAmtG2M3(taxAmount);
					} else if (countG2 == 4) {
						detailVo.setTaxAmtG2M4(taxAmount);
					} else if (countG2 == 5) {
						detailVo.setTaxAmtG2M5(taxAmount);
					} else if (countG2 == 6) {
						detailVo.setTaxAmtG2M6(taxAmount);
					} else if (countG2 == 7) {
						detailVo.setTaxAmtG2M7(taxAmount);
					} else if (countG2 == 8) {
						detailVo.setTaxAmtG2M8(taxAmount);
					} else if (countG2 == 9) {
						detailVo.setTaxAmtG2M9(taxAmount);
					} else if (countG2 == 10) {
						detailVo.setTaxAmtG2M10(taxAmount);
					} else if (countG2 == 11) {
						detailVo.setTaxAmtG2M11(taxAmount);
					} else if (countG2 == 12) {
						detailVo.setTaxAmtG2M12(taxAmount);
					}
				}
			}

			detailVo.setSumTaxAmtG1((sumTaxAmtG1.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
			detailVo.setSumTaxAmtG2((sumTaxAmtG2.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
			detailVo.setTaxMonthNo(String.valueOf(countTaxMonthNo));

			// Calculate Percentage
			if ((sumTaxAmtG2.compareTo(BigDecimal.ZERO) == 0) && (sumTaxAmtG1.compareTo(BigDecimal.ZERO) == 0)) {
				taxAmtChnPnt = BigDecimal.ZERO;
			} else {
				taxAmtChnPnt = (sumTaxAmtG2.subtract(sumTaxAmtG1)).multiply(new BigDecimal("100")).divide(NumberUtils.ZeroToOne(sumTaxAmtG1), 2, BigDecimal.ROUND_HALF_UP);
			}
			detailVo.setTaxAmtChnPnt(taxAmtChnPnt.toString());

			// Calculate S.D.
			calculateSD(detailVo, taxAmountList);

			// Find DutyCode Description
			detailVo.setDutyName(ExciseUtils.getDutyDesc(wsReg4000.getDutyCode()));

			detailVoList.add(detailVo);
		}

		return detailVoList;
	}

	private TaWsReg4000 formVoToWsReg4000(TaxOperatorFormVo formVo) {
		TaWsReg4000 wsReg4000 = new TaWsReg4000();

		try {
			BeanUtils.copyProperties(wsReg4000, formVo);
			wsReg4000.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}

		return wsReg4000;
	}

	private void calculateSD(TaxOperatorDetailVo detailVo, List<Double> taxAmountList) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");

		double[] taxAmounts = taxAmountList.stream().mapToDouble(d -> d).toArray();
		double mean = StatUtils.mean(taxAmounts);
		if (!Double.isNaN(mean)) {
			detailVo.setTaxAmtMean(decimalFormat.format(mean));
		} else {
			detailVo.setTaxAmtMean(NO_TAX_AMOUNT);
		}

		StandardDeviation standardDeviation = new StandardDeviation();
		double sd = standardDeviation.evaluate(taxAmounts, mean);
		if (!Double.isNaN(sd)) {
			detailVo.setTaxAmtSd(decimalFormat.format(sd));
		} else {
			detailVo.setTaxAmtSd(NO_TAX_AMOUNT);
		}

		double max = StatUtils.max(taxAmounts);
		double taxAmtMaxPnt = ((max - mean) / mean) * 100;
		if (!Double.isNaN(taxAmtMaxPnt)) {
			detailVo.setTaxAmtMaxPnt(decimalFormat.format(taxAmtMaxPnt));
		} else {
			detailVo.setTaxAmtMaxPnt(NO_TAX_AMOUNT);
		}

		double min = StatUtils.min(taxAmounts);
		double taxAmtMinPnt = ((min - mean) / mean) * 100;
		if (!Double.isNaN(taxAmtMaxPnt)) {
			detailVo.setTaxAmtMaxPnt(decimalFormat.format(taxAmtMinPnt));
		} else {
			detailVo.setTaxAmtMaxPnt(NO_TAX_AMOUNT);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public void saveDraftWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		String draftNumber = worksheetSequenceService.getDraftNumber(officeCode, budgetYear);
		logger.info("saveDraftWorksheet officeCode={}, budgetYear={}, condNumber={}, draftNumber={}", officeCode, budgetYear, formVo.getCondNumber(), draftNumber);

		formVo.setBudgetYear(ExciseUtils.getCurrentBudgetYear());
		String dateStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String dateEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		// ==> Save WorksheetMainCondHdr
		TaMasCondMainHdr masCondMainHdr = taMasCondMainHdrRepository.findByCondNumber(formVo.getCondNumber());
		TaWorksheetCondMainHdr conMainHdr = new TaWorksheetCondMainHdr();
		conMainHdr.setDraftNumber(draftNumber);
		conMainHdr.setCondGroupDesc(masCondMainHdr.getCondGroupDesc());
		conMainHdr.setMonthNum(masCondMainHdr.getMonthNum());
		conMainHdr.setYearMonthStart(dateStart);
		conMainHdr.setYearMonthEnd(dateEnd);
		conMainHdr.setCondGroupNum(String.valueOf(masCondMainHdr.getCondGroupNum()));
		conMainHdr.setNewFacFlag(masCondMainHdr.getNewFacFlag());
		taWorksheetCondMainHdrRepository.save(conMainHdr);

		// ==> Save WorksheetMainCondDtl
		List<TaMasCondMainDtl> masCondMainDtlList = taMasCondMainDtlRepository.findByCondNumber(formVo.getCondNumber());
		List<TaWorksheetCondMainDtl> condMainDtlList = new ArrayList<>();
		TaWorksheetCondMainDtl condMainDtl = null;
		for (TaMasCondMainDtl masCondMainDtl : masCondMainDtlList) {
			condMainDtl = new TaWorksheetCondMainDtl();
			condMainDtl.setDraftNumber(draftNumber);
			condMainDtl.setCondGroup(masCondMainDtl.getCondGroup());
			condMainDtl.setTaxFreqType(masCondMainDtl.getTaxFreqType());
			condMainDtl.setTaxMonthStart(masCondMainDtl.getTaxMonthStart());
			condMainDtl.setTaxMonthEnd(masCondMainDtl.getTaxMonthEnd());
			condMainDtl.setRangeTypeStart(masCondMainDtl.getRangeTypeStart());
			condMainDtl.setRangeStart(masCondMainDtl.getRangeStart());
			condMainDtl.setRangeTypeEnd(masCondMainDtl.getRangeTypeEnd());
			condMainDtl.setRangeEnd(masCondMainDtl.getRangeEnd());
			condMainDtl.setRiskLevel(masCondMainDtl.getRiskLevel());
			condMainDtl.setCondType(masCondMainDtl.getCondType());
			condMainDtl.setCondDtlDesc(masCondMainDtl.getCondDtlDesc() );
			condMainDtlList.add(condMainDtl);
		}
		taWorksheetCondMainDtlRepository.saveAll(condMainDtlList);

		// ==> Save Condition Sub
		// ==> Capital
		if (StringUtils.isNotBlank(formVo.getCondSub1())) {
			List<TaMasCondSubCapital> masCondSubCapitalList = taMasCondSubCapitalRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
			for (TaMasCondSubCapital masCondSubCapital : masCondSubCapitalList) {
				TaWorksheetCondSubCapital worksheetCondSubCapital = new TaWorksheetCondSubCapital();
				worksheetCondSubCapital.setDraftNumber(draftNumber);
				worksheetCondSubCapital.setDutyCode(masCondSubCapital.getDutyCode());
				worksheetCondSubCapital.setHugeCapitalAmount(masCondSubCapital.getHugeCapitalAmount());
				worksheetCondSubCapital.setLargeCapitalAmount(masCondSubCapital.getLargeCapitalAmount());
				worksheetCondSubCapital.setMediumCapitalAmount(masCondSubCapital.getMediumCapitalAmount());
				worksheetCondSubCapital.setSmallCapitalAmount(masCondSubCapital.getSmallCapitalAmount());
				taWorksheetCondSubCapitalRepository.save(worksheetCondSubCapital);
			}
		}
		// ==> Risk
		if (StringUtils.isNotBlank(formVo.getCondSub2())) {
			List<TaMasCondSubRisk> masCondSubRiskList = taMasCondSubRiskRepository.findByBudgetYearAndOfficeCode(budgetYear, officeCode);
			TaWorksheetCondSubRisk worksheetCondSubRisk = null;
			for (TaMasCondSubRisk masCondSubRisk : masCondSubRiskList) {
				worksheetCondSubRisk = new TaWorksheetCondSubRisk();
				worksheetCondSubRisk.setDraftNumber(draftNumber);
				worksheetCondSubRisk.setDutyCode(masCondSubRisk.getDutyCode());
				worksheetCondSubRisk.setRiskLevel(masCondSubRisk.getRiskLevel());
				taWorksheetCondSubRiskRepository.save(worksheetCondSubRisk);
			}
		}
		// ==> No Audit
		if (StringUtils.isNotBlank(formVo.getCondSub3())) {
			TaMasCondSubNoAudit masCondSubNoAudit = taMasCondSubNoAuditRepository.findByBudgetYearAndOfficeCode(budgetYear, officeCode);
			TaWorksheetCondSubNoAudit worksheetCondSubNoAudit = new TaWorksheetCondSubNoAudit();
			worksheetCondSubNoAudit.setDraftNumber(draftNumber);
			worksheetCondSubNoAudit.setNoTaxAuditYearNum(masCondSubNoAudit.getNoTaxAuditYearNum());
			taWorksheetCondSubNoAuditRepository.save(worksheetCondSubNoAudit);
		}

		// ==> Save DraftWorksheetHdr
		TaDraftWorksheetHdr draftHdr = new TaDraftWorksheetHdr();
		draftHdr.setDraftNumber(draftNumber);
		draftHdr.setOfficeCode(officeCode);
		draftHdr.setYearMonthStart(dateStart);
		draftHdr.setYearMonthEnd(dateEnd);
		draftHdr.setMonthNum(formVo.getDateRange());
		draftHdr.setBudgetYear(budgetYear);
		taDraftWorksheetHdrRepository.save(draftHdr);

		// ==> Save DraftWorksheetDtl
		List<TaxOperatorDetailVo> detailVoList = prepareTaxOperatorDetailVoList(formVo);
		List<TaDraftWorksheetDtl> dratfDtlList = new ArrayList<>();
		TaDraftWorksheetDtl draftDtl = new TaDraftWorksheetDtl();
		for (TaxOperatorDetailVo detailVo : detailVoList) {
			draftDtl = new TaDraftWorksheetDtl();

			draftDtl.setDraftNumber(draftNumber);
			draftDtl.setNewRegId(detailVo.getNewRegId());

			draftDtl.setSumTaxAmtG1(NO_TAX_AMOUNT.equals(detailVo.getSumTaxAmtG1()) ? null : NumberUtils.toBigDecimal(detailVo.getSumTaxAmtG1()));
			draftDtl.setSumTaxAmtG2(NO_TAX_AMOUNT.equals(detailVo.getSumTaxAmtG2()) ? null : NumberUtils.toBigDecimal(detailVo.getSumTaxAmtG2()));
			draftDtl.setTaxAmtChnPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtChnPnt()) ? null : NumberUtils.toBigDecimal(detailVo.getTaxAmtChnPnt()));
			draftDtl.setTaxMonthNo(Integer.parseInt(detailVo.getTaxMonthNo()));

			draftDtl.setTaxAmtG1M1(detailVo.getTaxAmtG1M1());
			draftDtl.setTaxAmtG1M2(detailVo.getTaxAmtG1M2());
			draftDtl.setTaxAmtG1M3(detailVo.getTaxAmtG1M3());
			draftDtl.setTaxAmtG1M4(detailVo.getTaxAmtG1M4());
			draftDtl.setTaxAmtG1M5(detailVo.getTaxAmtG1M5());
			draftDtl.setTaxAmtG1M6(detailVo.getTaxAmtG1M6());
			draftDtl.setTaxAmtG1M7(detailVo.getTaxAmtG1M7());
			draftDtl.setTaxAmtG1M8(detailVo.getTaxAmtG1M8());
			draftDtl.setTaxAmtG1M9(detailVo.getTaxAmtG1M9());
			draftDtl.setTaxAmtG1M10(detailVo.getTaxAmtG1M10());
			draftDtl.setTaxAmtG1M11(detailVo.getTaxAmtG1M11());
			draftDtl.setTaxAmtG1M12(detailVo.getTaxAmtG1M12());

			draftDtl.setTaxAmtG2M1(detailVo.getTaxAmtG2M1());
			draftDtl.setTaxAmtG2M2(detailVo.getTaxAmtG2M2());
			draftDtl.setTaxAmtG2M3(detailVo.getTaxAmtG2M3());
			draftDtl.setTaxAmtG2M4(detailVo.getTaxAmtG2M4());
			draftDtl.setTaxAmtG2M5(detailVo.getTaxAmtG2M5());
			draftDtl.setTaxAmtG2M6(detailVo.getTaxAmtG2M6());
			draftDtl.setTaxAmtG2M7(detailVo.getTaxAmtG2M7());
			draftDtl.setTaxAmtG2M8(detailVo.getTaxAmtG2M8());
			draftDtl.setTaxAmtG2M9(detailVo.getTaxAmtG2M9());
			draftDtl.setTaxAmtG2M10(detailVo.getTaxAmtG2M10());
			draftDtl.setTaxAmtG2M11(detailVo.getTaxAmtG2M11());
			draftDtl.setTaxAmtG2M12(detailVo.getTaxAmtG2M12());

			draftDtl.setTaxAmtSd(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtSd()) ? null : NumberUtils.toBigDecimal(detailVo.getTaxAmtSd()));
			draftDtl.setTaxAmtMean(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMean()) ? null : NumberUtils.toBigDecimal(detailVo.getTaxAmtMean()));
			draftDtl.setTaxAmtMaxPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMaxPnt()) ? null : NumberUtils.toBigDecimal(detailVo.getTaxAmtMaxPnt()));
			draftDtl.setTaxAmtMinPnt(NO_TAX_AMOUNT.equals(detailVo.getTaxAmtMinPnt()) ? null : NumberUtils.toBigDecimal(detailVo.getTaxAmtMinPnt()));

			draftDtl.setCreatedBy(UserLoginUtils.getCurrentUsername());
			draftDtl.setCreatedDate(LocalDateTime.now());

			dratfDtlList.add(draftDtl);
		}

		taDraftWorksheetDtlRepository.batchInsert(dratfDtlList);
	}

	public List<String> findAllDraftNumber(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		if (StringUtils.isEmpty(budgetYear)) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		logger.info("findAllDraftNumber officeCode={}, budgetYear={}", officeCode, budgetYear);
		return taDraftWorksheetHdrRepository.findDraftNumberByOfficeCodeAndBudgetYear(officeCode, budgetYear);
	}

	public YearMonthVo getMonthStart(TaxOperatorFormVo formVo) {
		logger.info("getMonthStart draftNumber = {}", formVo.getDraftNumber());

		YearMonthVo ymVo = taDraftWorksheetHdrRepository.findMonthStartByDraftNumber(formVo.getDraftNumber());

		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(ymVo.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(ymVo.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

		ymVo.setYearMonthStart(ymStart);
		ymVo.setYearMonthEnd(ymEnd);

		return ymVo;
	}

	public TaxOperatorVo getDraftWorksheet(TaxOperatorFormVo formVo) {
		logger.info("getDraftWorksheetDtl draftNumber = {}", formVo.getDraftNumber());

		String officeCode = formVo.getOfficeCode();
		formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(officeCode));

		List<TaxOperatorDetailVo> draftDtlList = taDraftWorksheetDtlRepository.findByCriteria(formVo);

		TaxOperatorVo vo = new TaxOperatorVo();
		vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(draftDtlList, formVo));
		vo.setCount(taDraftWorksheetDtlRepository.countByCriteria(formVo));

		return vo;
	}

}
