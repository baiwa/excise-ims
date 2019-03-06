package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_MAIN_COND_RANGE;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.TaxDratfVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Service
public class WorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(WorksheetService.class);

	@Autowired
	private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;

	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;

	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;

	@Autowired
	private WorksheetSequenceService worksheetSeqCtrlService;

	@Autowired
	private TaPlanWorksheetSendRepository taPlanWorksheetSendRepository;

	@Transactional(rollbackOn = Exception.class)
	public void saveWorksheet(String draftNumber, String budgetYear) throws Exception {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String analysisNumber = worksheetSeqCtrlService.getAnalysisNumber(officeCode, budgetYear);
		logger.info("saveWorksheet budgetYear={}, draftNumber={}, analysisNumber={}", budgetYear, draftNumber, analysisNumber);

		TaWorksheetCondMainHdr condMainHdr = taWorksheetCondMainHdrRepository.findByDraftNumber(draftNumber);

		// ==> Save WorksheetHdr
		TaWorksheetHdr worksheetHdr = new TaWorksheetHdr();
		worksheetHdr.setOfficeCode(officeCode);
		worksheetHdr.setBudgetYear(budgetYear);
		worksheetHdr.setDraftNumber(draftNumber);
		worksheetHdr.setAnalysisNumber(analysisNumber);
		worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.CONDITION);
		taWorksheetHdrRepository.save(worksheetHdr);

		// ==> Save WorksheetDtl
		List<TaWorksheetCondMainDtl> condMainDtlList = taWorksheetCondMainDtlRepository.findByDraftNumber(draftNumber);
		String indexLastCondition = null;
		for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
			if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(condMainDtl.getCondType())) {
				indexLastCondition = condMainDtl.getCondGroup();
			}
		}
		List<TaxDratfVo> taxDratfVoList = taDraftWorksheetDtlRepository.findByDraftNumber(draftNumber);
		List<TaWorksheetDtl> worksheetDtlList = new ArrayList<>();
		TaWorksheetDtl worksheetDtl = null;
		String regDate = null;
		for (TaxDratfVo taxDratfVo : taxDratfVoList) {
			worksheetDtl = new TaWorksheetDtl();
			worksheetDtl.setNewRegId(taxDratfVo.getNewRegId());
			worksheetDtl.setAnalysisNumber(analysisNumber);
			worksheetDtl.setCondMainGrp("0"); // Default Group
			if (FLAG.Y_FLAG.equals(condMainHdr.getNewFacFlag())) {
				// Check Case T
				for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
					if (TA_MAS_COND_MAIN_TYPE.TAX.equals(condMainDtl.getCondType())) {
						if (isConditionGroup(condMainDtl, taxDratfVo)) {
							worksheetDtl.setCondMainGrp(condMainDtl.getCondGroup());
							break;
						}
					}
				}
			} else {
				// Check Case O
				if (taxDratfVo.getRegDate() != null) {
					regDate = taxDratfVo.getRegDate().format(DateTimeFormatter.ofPattern("yyyyMM"));
				} else {
					regDate = "999912";
				}
				if (regDate.compareTo(condMainHdr.getYearMonthStart()) >= 0 && regDate.compareTo(condMainHdr.getYearMonthEnd()) <= 0) {
					worksheetDtl.setCondMainGrp(indexLastCondition);
				} else {
					// Check Case T
					for (TaWorksheetCondMainDtl worksheetCondMainDtl : condMainDtlList) {
						if (TA_MAS_COND_MAIN_TYPE.TAX.equals(worksheetCondMainDtl.getCondType())) {
							if (isConditionGroup(worksheetCondMainDtl, taxDratfVo)) {
								worksheetDtl.setCondMainGrp(worksheetCondMainDtl.getCondGroup());
								break;
							}
						}
					}
				}
			}
			logger.debug("newRegId=" + taxDratfVo.getNewRegId() + "\ttaxAmtChnPnt=" + taxDratfVo.getTaxAmtChnPnt() + "\tcondMainGrp=" + worksheetDtl.getCondMainGrp());
			worksheetDtl.setCreatedBy(UserLoginUtils.getCurrentUsername());
			worksheetDtl.setCreatedDate(LocalDateTime.now());
			
			worksheetDtlList.add(worksheetDtl);
		}
		taWorksheetDtlRepository.batchInsert(worksheetDtlList);
	}

	private boolean isConditionGroup(TaWorksheetCondMainDtl worksheetCondMainDtl, TaxDratfVo taxDratfVo) {
		boolean condGroup = false;
		BigDecimal rangeStart = worksheetCondMainDtl.getRangeStart();
		BigDecimal rangeEnd = worksheetCondMainDtl.getRangeEnd();
		BigDecimal value = floorAndCeilPercentage(taxDratfVo.getTaxAmtChnPnt());
		if (TA_MAIN_COND_RANGE.GREATER_THAN.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			if (rangeEnd != null) {
				if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = isGreaterThan(rangeStart, value) && isLessThan(rangeEnd, value);
				} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = isGreaterThan(rangeStart, value) && isLessThanOrEquals(rangeEnd, value);
				} else {
					condGroup = isGreaterThan(rangeStart, value);
				}
			} else {
				condGroup = isGreaterThan(rangeStart, value);
			}
		} else if (TA_MAIN_COND_RANGE.GREATER_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			if (rangeEnd != null) {
				if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = isGreaterThanOrEquals(rangeStart, value) && isLessThan(rangeEnd, value);
				} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeEnd())) {
					condGroup = isGreaterThanOrEquals(rangeStart, value) && isLessThanOrEquals(rangeEnd, value);
				} else {
					condGroup = isGreaterThanOrEquals(rangeStart, value);
				}
			} else {
				condGroup = isGreaterThanOrEquals(rangeStart, value);
			}
		} else if (TA_MAIN_COND_RANGE.EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = isEquals(rangeStart, value);
		} else if (TA_MAIN_COND_RANGE.LESS_THAN_OR_EQUALS.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = isLessThanOrEquals(rangeStart, value);
		} else if (TA_MAIN_COND_RANGE.LESS_THAN.equals(worksheetCondMainDtl.getRangeTypeStart())) {
			condGroup = isLessThan(rangeStart, value);
		}
		return taxDratfVo.getTaxMonthNo().intValue() >= worksheetCondMainDtl.getTaxMonthStart().intValue() && taxDratfVo.getTaxMonthNo().intValue() <= worksheetCondMainDtl.getTaxMonthEnd().intValue() && condGroup;
	}

	private boolean isGreaterThan(BigDecimal range, BigDecimal taxAmtChnPnt) {
		return taxAmtChnPnt.compareTo(range) > 0;
	}

	private boolean isGreaterThanOrEquals(BigDecimal range, BigDecimal taxAmtChnPnt) {
		return taxAmtChnPnt.compareTo(range) >= 0;
	}

	private boolean isEquals(BigDecimal range, BigDecimal taxAmtChnPnt) {
		return taxAmtChnPnt.compareTo(range) == 0;
	}

	private boolean isLessThanOrEquals(BigDecimal range, BigDecimal taxAmtChnPnt) {
		return taxAmtChnPnt.compareTo(range) <= 0;
	}

	private boolean isLessThan(BigDecimal range, BigDecimal taxAmtChnPnt) {
		return taxAmtChnPnt.compareTo(range) < 0;
	}

	private BigDecimal floorAndCeilPercentage(BigDecimal amount) {
		amount = NumberUtils.nullToZero(amount);
		BigDecimal amountPlus100 = new BigDecimal("100");
		BigDecimal amountMinus100 = new BigDecimal("-100");
		if (amount.compareTo(amountPlus100) == 1) {
			return amountPlus100;
		} else if (amount.compareTo(amountMinus100) == -1) {
			return amountMinus100;
		} else {
			return amount;
		}
	}

	public List<String> findAllAnalysisNumber(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		if (StringUtils.isEmpty(budgetYear)) {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		logger.info("findAllAnalysisNumber officeCode={}, budgetYear={}", officeCode, budgetYear);
		return taWorksheetHdrRepository.findAllAnalysisNumberByOfficeCodeAndBudgetYear(officeCode, budgetYear);
	}

	public YearMonthVo getMonthStart(TaxOperatorFormVo formVo) {
		logger.info("getMonthStart analysisNumber = {}", formVo.getAnalysisNumber());

		YearMonthVo obj = taWorksheetCondMainHdrRepository.findMonthStartByAnalysisNumber(formVo.getAnalysisNumber());

		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(obj.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(obj.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

		obj.setYearMonthStart(ymStart);
		obj.setYearMonthEnd(ymEnd);

		return obj;
	}

	public List<CondGroupVo> findCondGroupDtl(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("findCondGroupDtl officeCode={}, budgetYear={}", officeCode, budgetYear);
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (StringUtils.isNotBlank(formVo.getSeeDataSelect()) && planSend == null) {
			return new ArrayList<>();
		}
		return taWorksheetCondMainDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber())
			.stream()
			.map(t -> {
				CondGroupVo vo = new CondGroupVo();
				vo.setAnalysisNumber(t.getAnalysisNumber());
				vo.setCondGroup(t.getCondGroup());
				vo.setTaxMonthStart(t.getTaxMonthStart());
				vo.setTaxMonthEnd(t.getTaxMonthEnd());
				vo.setRangeStart(t.getRangeStart());
				vo.setRangeEnd(t.getRangeEnd());
				return vo;
			}).collect(Collectors.toList());
	}

	public TaxOperatorVo getWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("getWorksheet officeCode={}, budgetYear={}, analysisNumber={}", officeCode, budgetYear, formVo.getAnalysisNumber());

		if (StringUtils.isBlank(formVo.getAnalysisNumber())) {
			formVo.setAnalysisNumber("");
		}

		TaxOperatorVo vo = new TaxOperatorVo();
		formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(officeCode));

		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		if (StringUtils.isNotBlank(formVo.getSeeDataSelect()) && planSend == null) {
			vo.setDatas(new ArrayList<>());
			vo.setCount(0L);
		} else {
			List<TaxOperatorDetailVo> list = taWorksheetDtlRepository.findByCriteria(formVo);
			vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo));
			vo.setCount(taWorksheetDtlRepository.countByCriteria(formVo));
		}

		return vo;
	}

}
