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

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
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
    private TaMasCondMainHdrRepository taMasCondMainHdrRepository;
    @Autowired
    private TaMasCondMainDtlRepository taMasCondMainDtlRepository;

    @Autowired
    private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;
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
    public void saveWorksheet(String draftNumber, String budgetYear, String condNumber) throws Exception {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String analysisNumber = worksheetSeqCtrlService.getAnalysisNumber(officeCode, budgetYear);
        logger.info("saveWorksheet budgetYear={}, draftNumber={}, analysisNumber={}", budgetYear, draftNumber, analysisNumber);

        // ==> Save WorksheetMainCondHdr
        TaMasCondMainHdr masCondMainHdr = taMasCondMainHdrRepository.findByBudgetYearAndCondNumber(budgetYear, condNumber);
        TaDraftWorksheetHdr draftWorksheetHdr = taDraftWorksheetHdrRepository.findByDraftNumber(draftNumber);
        TaWorksheetCondMainHdr conMainHdr = new TaWorksheetCondMainHdr();
        conMainHdr.setAnalysisNumber(analysisNumber);
        conMainHdr.setMonthNum(masCondMainHdr.getMonthNum());
        conMainHdr.setYearMonthStart(draftWorksheetHdr.getYearMonthStart());
        conMainHdr.setYearMonthEnd(draftWorksheetHdr.getYearMonthEnd());
        taWorksheetCondMainHdrRepository.save(conMainHdr);

        // ==> Save WorksheetMainCondDtl
        String indexLastCondition = "";
        List<TaMasCondMainDtl> masCondMainDtlList = taMasCondMainDtlRepository.findByBudgetYear(budgetYear);
        List<TaWorksheetCondMainDtl> condMainDtlList = new ArrayList<>();
        TaWorksheetCondMainDtl condMainDtl = null;
        for (TaMasCondMainDtl masCondMainDtl : masCondMainDtlList) {
            condMainDtl = new TaWorksheetCondMainDtl();
            condMainDtl.setAnalysisNumber(analysisNumber);
            condMainDtl.setCondGroup(masCondMainDtl.getCondGroup());
            if (masCondMainDtl.getTaxMonthStart() != null) {
                condMainDtl.setTaxMonthStart(masCondMainDtl.getTaxMonthStart());
            }
            if (masCondMainDtl.getTaxMonthEnd() != null) {
                condMainDtl.setTaxMonthEnd(masCondMainDtl.getTaxMonthEnd());
            }
            if (masCondMainDtl.getRangeStart() != null) {
                condMainDtl.setRangeStart(masCondMainDtl.getRangeStart());
            }
            if (masCondMainDtl.getRangeEnd() != null) {
                condMainDtl.setRangeEnd(masCondMainDtl.getRangeEnd());
            }
            condMainDtl.setRiskLevel(masCondMainDtl.getRiskLevel());
            condMainDtl.setCondType(masCondMainDtl.getCondType());
            if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(masCondMainDtl.getCondType())) {
                indexLastCondition = masCondMainDtl.getCondGroup();
            }
            condMainDtlList.add(condMainDtl);
        }
        taWorksheetCondMainDtlRepository.saveAll(condMainDtlList);

        // ==> Save WorksheetHdr
        TaWorksheetHdr worksheetHdr = new TaWorksheetHdr();
        worksheetHdr.setAnalysisNumber(analysisNumber);
        worksheetHdr.setDraftNumber(draftNumber);
        worksheetHdr.setOfficeCode(officeCode);
        worksheetHdr.setBudgetYear(budgetYear);
        worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.CONDITION);
        taWorksheetHdrRepository.save(worksheetHdr);

        // ==> Save WorksheetDtl
        List<TaxDratfVo> taxDratfVoList = taDraftWorksheetDtlRepository.findByDraftNumber(draftNumber);
        List<TaWorksheetDtl> worksheetDtlList = new ArrayList<>();
        TaWorksheetDtl worksheetDtl = null;
        String regDate = null;
        for (TaxDratfVo taxDratfVo : taxDratfVoList) {
            worksheetDtl = new TaWorksheetDtl();
            worksheetDtl.setNewRegId(taxDratfVo.getNewRegId());
            worksheetDtl.setAnalysisNumber(analysisNumber);
            worksheetDtl.setCondMainGrp("0"); // Default Group
            if (taxDratfVo.getRegDate() != null) {
                // Check Case O
                regDate = taxDratfVo.getRegDate().format(DateTimeFormatter.ofPattern("yyyyMM"));
                if (regDate.compareTo(draftWorksheetHdr.getYearMonthStart()) >= 0 && regDate.compareTo(draftWorksheetHdr.getYearMonthEnd()) <= 0) {
                    worksheetDtl.setCondMainGrp(indexLastCondition);
                } else {
                    // Check Case T
                    for (TaWorksheetCondMainDtl taWorksheetCondDtlTax : condMainDtlList) {
                        if (TA_MAS_COND_MAIN_TYPE.TAX.equals(taWorksheetCondDtlTax.getCondType())) {
                            if (taxDratfVo.getTaxMonthNo().intValue() >= taWorksheetCondDtlTax.getTaxMonthStart().intValue()
                                    && taxDratfVo.getTaxMonthNo().intValue() <= taWorksheetCondDtlTax.getTaxMonthEnd().intValue()
                                    && taWorksheetCondDtlTax.getRangeStart().compareTo(floorAndCeilPercentage(taxDratfVo.getTaxAmtChnPnt())) != 1
                                    && taWorksheetCondDtlTax.getRangeEnd().compareTo(floorAndCeilPercentage(taxDratfVo.getTaxAmtChnPnt())) != -1) {
                                worksheetDtl.setCondMainGrp(taWorksheetCondDtlTax.getCondGroup());
                                break;
                            }
                        }
                    }
                }
            }

            worksheetDtl.setCreatedBy(UserLoginUtils.getCurrentUsername());
            worksheetDtl.setCreatedDate(LocalDateTime.now());

            worksheetDtlList.add(worksheetDtl);
        }
        taWorksheetDtlRepository.batchInsert(worksheetDtlList);
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
			})
			.collect(Collectors.toList());
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
