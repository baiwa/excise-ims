package th.go.excise.ims.ta.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_MAIN_COND_RANGE;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.*;
import th.go.excise.ims.ta.persistence.repository.*;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorksheetService {

    private static final Logger logger = LoggerFactory.getLogger(WorksheetService.class);

    private static final String MAX_DATE = "999912";

    @Autowired
    private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;
    @Autowired
    private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;

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
//		String analysisNumber = worksheetSeqCtrlService.getAnalysisNumber(officeCode, budgetYear);
        String analysisNumber = draftNumber;
        logger.info("saveWorksheet budgetYear={}, draftNumber={}, analysisNumber={}", budgetYear, draftNumber, analysisNumber);

        // ==> Update WorksheetCondMainHdr
        TaWorksheetCondMainHdr condMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(analysisNumber);
        //condMainHdr.setAnalysisNumber(analysisNumber);
        //taWorksheetCondMainHdrRepository.save(condMainHdr);

        // ==> Update WorksheetCondMainDtl
        List<TaWorksheetCondMainDtl> condMainDtlList = taWorksheetCondMainDtlRepository.findByAnalysisNumber(analysisNumber);
        String indexLastCondition = null;
        for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
            if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(condMainDtl.getCondType())) {
                indexLastCondition = condMainDtl.getCondGroup();
            }
            condMainDtl.setAnalysisNumber(analysisNumber);
        }
        taWorksheetCondMainDtlRepository.saveAll(condMainDtlList);

        // ==> Update WorksheetCondSubCapital
		/*List<TaWorksheetCondSubCapital> worksheetCondSubCapitalList = taWorksheetCondSubCapitalRepository.findByDraftNumber(draftNumber);
		if (CollectionUtils.isNotEmpty(worksheetCondSubCapitalList)) {
			for (TaWorksheetCondSubCapital worksheetCondSubCapital : worksheetCondSubCapitalList) {
				worksheetCondSubCapital.setAnalysisNumber(analysisNumber);
			}
			taWorksheetCondSubCapitalRepository.saveAll(worksheetCondSubCapitalList);
		}*/

        // ==> Update WorksheetCondSubRisk
		/*List<TaWorksheetCondSubRisk> worksheetCondSubRiskList = taWorksheetCondSubRiskRepository.findByDraftNumber(draftNumber);
		if (CollectionUtils.isNotEmpty(worksheetCondSubRiskList)) {
			for (TaWorksheetCondSubRisk worksheetCondSubRisk : worksheetCondSubRiskList) {
				worksheetCondSubRisk.setAnalysisNumber(analysisNumber);
			}
			taWorksheetCondSubRiskRepository.saveAll(worksheetCondSubRiskList);
		}*/

        // ==> Update WorksheetCondSubNoAudit
		/*TaWorksheetCondSubNoAudit worksheetCondSubNoAudit = taWorksheetCondSubNoAuditRepository.findByDraftNumber(draftNumber);
		if (worksheetCondSubNoAudit != null) {
			worksheetCondSubNoAudit.setAnalysisNumber(analysisNumber);
			taWorksheetCondSubNoAuditRepository.save(worksheetCondSubNoAudit);
		}*/

        // ==> Update DraftWorksheetHdr
//		TaDraftWorksheetHdr draftWorksheetHdr = taDraftWorksheetHdrRepository.findByDraftNumber(draftNumber);
//		draftWorksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.CONDITION);
//		taDraftWorksheetHdrRepository.save(draftWorksheetHdr);

        // ==> Save WorksheetHdr
        TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(analysisNumber);
//		worksheetHdr.setOfficeCode(officeCode);
//		worksheetHdr.setBudgetYear(budgetYear);
//		worksheetHdr.setDraftNumber(draftNumber);
//		worksheetHdr.setAnalysisNumber(analysisNumber);
        worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.CONDITION);
//		worksheetHdr.setCondSubCapitalFlag(draftWorksheetHdr.getCondSubCapitalFlag());
//		worksheetHdr.setCondSubRiskFlag(draftWorksheetHdr.getCondSubRiskFlag());
//		worksheetHdr.setCondSubNoAuditFlag(draftWorksheetHdr.getCondSubNoAuditFlag());
        taWorksheetHdrRepository.save(worksheetHdr);

        // ==> Save WorksheetDtl
        //List<TaxDratfVo> taxDratfVoList = taDraftWorksheetDtlRepository.findByDraftNumber(draftNumber);
        List<TaxDratfVo> taxDratfVoList = taDraftWorksheetDtlRepository.findByDraftNumber(analysisNumber);

        List<TaWorksheetDtl> worksheetDtlList = new ArrayList<>();
        TaWorksheetDtl worksheetDtl = null;
        String regDate = null;
        for (TaxDratfVo taxDratfVo : taxDratfVoList) {
            worksheetDtl = taWorksheetDtlRepository.findByAnalysisNumberAndNewRegId(analysisNumber, taxDratfVo.getNewRegId());
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
                    regDate = taxDratfVo.getRegDate().format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM));
                } else {
                    regDate = MAX_DATE;
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

            worksheetDtl.setCondSubCapital(checkCapital(taxDratfVo.getRegCapital(), analysisNumber, taxDratfVo.getDutyCode()));
            TaWorksheetCondSubRisk risk = taWorksheetCondSubRiskRepository.findByAnalysisNumberAndDutyCode(analysisNumber, taxDratfVo.getDutyCode());
            worksheetDtl.setCondSubRisk(risk != null ? risk.getRiskLevel() : null);
            worksheetDtlList.add(worksheetDtl);
        }
        taWorksheetDtlRepository.saveAll(worksheetDtlList);
        //taWorksheetDtlRepository.batchInsert(worksheetDtlList);
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

    private String checkCapital(String capital, String analysisNumber, String dutyCode) {

        TaWorksheetCondSubCapital ct = taWorksheetCondSubCapitalRepository.findByAnalysisNumberAndDutyCode(analysisNumber, dutyCode);
        if (StringUtils.isBlank(capital) || ct == null) {
            return null;
        } else {
            BigDecimal capitalBigDecimal = new BigDecimal(capital);

            int rsCompareHuge = ct.getHugeCapitalAmount().compareTo(capitalBigDecimal);
            int rsCompareLarge = ct.getLargeCapitalAmount().compareTo(capitalBigDecimal);
            int rsCompareMedium = ct.getMediumCapitalAmount().compareTo(capitalBigDecimal);
            if (rsCompareHuge >= 0) {
                return ApplicationCache.getParamInfoByCode("TA_SUB_COND_CAPITAL", "1").getParamCode();
            } else if (rsCompareLarge >= 0) {
                return ApplicationCache.getParamInfoByCode("TA_SUB_COND_CAPITAL", "2").getParamCode();
            } else if (rsCompareMedium >= 0) {
                return ApplicationCache.getParamInfoByCode("TA_SUB_COND_CAPITAL", "3").getParamCode();
            } else {
                return ApplicationCache.getParamInfoByCode("TA_SUB_COND_CAPITAL", "4").getParamCode();
            }
        }
    }

    public List<String> findAllAnalysisNumber(TaxOperatorFormVo formVo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String budgetYear = formVo.getBudgetYear();
        if (StringUtils.isEmpty(budgetYear)) {
            budgetYear = ExciseUtils.getCurrentBudgetYear();
        }
        logger.info("findAllAnalysisNumber officeCode={}, budgetYear={}", officeCode, budgetYear);
        return taWorksheetHdrRepository.findAllAnalysisNumberByOfficeCodeAndBudgetYearAndWorksheetStatus(officeCode, budgetYear, TA_WORKSHEET_STATUS.CONDITION);
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
        return taWorksheetCondMainDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber()).stream().map(t -> {
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
        formVo.setBudgetYear(budgetYear);
        logger.info("getWorksheet officeCode={}, budgetYear={}, analysisNumber={}", officeCode, budgetYear, formVo.getAnalysisNumber());

        if (StringUtils.isBlank(formVo.getAnalysisNumber())) {
            formVo.setAnalysisNumber("");
        }

        TaxOperatorVo vo = new TaxOperatorVo();

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

    public List<LabelValueBean> groupCondSubCapital(String analysisNumber) {
        return taWorksheetDtlRepository.groupCondSubCapital(analysisNumber);
    }

    public List<LabelValueBean> groupCondSubRisk(String analysisNumber) {
        return taWorksheetDtlRepository.groupCondSubRisk(analysisNumber);
    }

    public List<TaWorksheetCondMainDtl> worksheetCondMainDtls(TaxOperatorFormVo formVo) {
        if (StringUtils.isNotBlank(formVo.getAnalysisNumber())) {
            return taWorksheetCondMainDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
        } else {
            return taWorksheetCondMainDtlRepository.findByDraftNumber(formVo.getDraftNumber());
        }

    }

    public TaWorksheetHdr checkEvaluateCondition(TaxOperatorFormVo formVo) {
        return taWorksheetHdrRepository.findByDraftNumber(formVo.getDraftNumber());
    }
}
