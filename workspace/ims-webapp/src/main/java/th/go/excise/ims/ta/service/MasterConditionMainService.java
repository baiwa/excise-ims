package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.constant.ProjectConstants.TAX_COMPARE_TYPE;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubNoAuditRepository;
import th.go.excise.ims.ta.vo.ConditionMessageVo;
import th.go.excise.ims.ta.vo.MasCondMainRequestVo;
import th.go.excise.ims.ta.vo.MasCondMainResponseVo;
import th.go.excise.ims.ta.vo.MasterConditionMainHdrDtlVo;
import th.go.excise.ims.ta.vo.Ta010101Vo;

@Service
public class MasterConditionMainService {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterConditionMainService.class);
	
    @Autowired
    private TaMasCondMainHdrRepository taMasCondMainHdrRepository;

    @Autowired
    private TaMasCondMainDtlRepository taMasCondMainDtlRepository;
    
    @Autowired
    private TaMasCondSubNoAuditRepository condSubNoAuditRepository;

    @Autowired
    private MasterConditionSequenceService masterConditionSequenceService;

    public void insertCondMainHdr(TaMasCondMainHdr form) {
    	logger.info("insertCondMainHdr");
    	
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        TaMasCondMainHdr hdr = new TaMasCondMainHdr();
        hdr.setOfficeCode(officeCode);
        hdr.setBudgetYear(form.getBudgetYear());
        hdr.setCondNumber(masterConditionSequenceService.getConditionNumber(officeCode, form.getBudgetYear()));
        hdr.setCondGroupDesc(form.getCondGroupDesc());
        hdr.setMonthNum(form.getMonthNum());
        hdr.setCondGroupNum(form.getCondGroupNum());
        hdr.setNewFacFlag(form.getNewFacFlag());
        hdr.setCompType(getTaxCompareType(form.getMonthNum()));
        taMasCondMainHdrRepository.save(hdr);
    }

    public void updateCondMainHdr(TaMasCondMainHdr form) {
    	logger.info("updateCondMainHdr");
    	
        TaMasCondMainHdr hdr = taMasCondMainHdrRepository.findByCondNumber(form.getCondNumber());
        hdr.setBudgetYear(form.getBudgetYear());
        hdr.setCondGroupDesc(form.getCondGroupDesc());
        hdr.setMonthNum(form.getMonthNum());
        hdr.setCondGroupNum(form.getCondGroupNum());
        hdr.setNewFacFlag(form.getNewFacFlag());
        hdr.setCompType(getTaxCompareType(form.getMonthNum()));
        taMasCondMainHdrRepository.save(hdr);
        
        List<TaMasCondMainDtl> dtlList = taMasCondMainDtlRepository.findByBudgetYearAndCondNumberAndCondType(hdr.getBudgetYear(), hdr.getCondNumber(), ProjectConstants.TA_MAS_COND_MAIN_TYPE.TAX);
        if (hdr.getCondGroupNum() < dtlList.size()) {
			int numLoop = dtlList.size() - hdr.getCondGroupNum();
			Collections.sort(dtlList, new Comparator<TaMasCondMainDtl>() {
                public int compare(TaMasCondMainDtl dtlTax, TaMasCondMainDtl dtlTax2) {
                    int condGroup = dtlTax.getCondGroup().compareTo(dtlTax2.getCondGroup());
                    if (condGroup == 0) {
                        return condGroup;
                    }
                    return Long.valueOf(dtlTax.getCondGroup()) > Long.valueOf(dtlTax2.getCondGroup()) ? 1 : Long.valueOf(dtlTax.getCondGroup()) < Long.valueOf(dtlTax2.getCondGroup()) ? -1 : 0;
                }
            });
			for (int i = 0; i < numLoop; i++) {
				TaMasCondMainDtl dtl = dtlList.get(dtlList.size()-i-1);
				dtl.setIsDeleted(FLAG.Y_FLAG);
				taMasCondMainDtlRepository.save(dtl);
			}
		}
    }

    public void deleteCondMain(TaMasCondMainHdr form) {
    	logger.info("deleteCondMain");
    	
        TaMasCondMainHdr hdr = taMasCondMainHdrRepository.findByCondNumber(form.getCondNumber());
        hdr.setIsDeleted(FLAG.Y_FLAG);
        TaMasCondMainDtl dtl = null;
        taMasCondMainHdrRepository.save(hdr);
        List<TaMasCondMainDtl> listDtl = taMasCondMainDtlRepository.findByCondNumber(form.getCondNumber());
        for (TaMasCondMainDtl obj : listDtl) {
            dtl = taMasCondMainDtlRepository.findById(obj.getMasCondMainDtlId()).get();
            dtl.setIsDeleted(FLAG.Y_FLAG);
            taMasCondMainDtlRepository.save(dtl);
        }
    }

    public void insertCondMainDtl(MasterConditionMainHdrDtlVo formVo) {
    	logger.info("insertCondMainDtl");
    	
        TaMasCondMainDtl dtl = null;
        List<TaMasCondMainDtl> dtlList = new ArrayList<>();
        TaMasCondMainHdr header = formVo.getHeader();
        if (header.getBudgetYear() != null) {
            for (TaMasCondMainDtl obj : formVo.getDetail()) {
                dtl = new TaMasCondMainDtl();
                dtl.setBudgetYear(header.getBudgetYear());
                dtl.setOfficeCode(header.getOfficeCode());
                dtl.setCondGroup(obj.getCondGroup());
                dtl.setCondNumber(header.getCondNumber());
                dtl.setTaxFreqType(obj.getTaxFreqType());
                dtl.setTaxMonthStart(obj.getTaxMonthStart());
                dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
                dtl.setRangeTypeStart(obj.getRangeTypeStart());
                dtl.setRangeTypeEnd(obj.getRangeTypeEnd());
                dtl.setRangeStart(obj.getRangeStart());
                dtl.setRangeEnd(obj.getRangeEnd());
                dtl.setRiskLevel(obj.getRiskLevel());
                dtl.setCondDtlDesc(obj.getCondDtlDesc());
                dtl.setCondType(TA_MAS_COND_MAIN_TYPE.TAX);
                dtlList.add(dtl);
            }
            if (!FLAG.Y_FLAG.equals(header.getNewFacFlag())) {
            	dtl = new TaMasCondMainDtl();
            	dtl.setBudgetYear(header.getBudgetYear());
            	dtl.setOfficeCode(header.getOfficeCode());
            	dtl.setCondNumber(header.getCondNumber());
            	dtl.setCondGroup(String.valueOf(formVo.getDetail().size() + 1));
            	dtl.setCondDtlDesc(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "NEW_COMP").getValue1());
            	dtl.setCondType(TA_MAS_COND_MAIN_TYPE.OTHER);
            	dtlList.add(dtl);
            }
            taMasCondMainDtlRepository.saveAll(dtlList);
        }
    }

    public void updateCondMainDtl(MasterConditionMainHdrDtlVo formVo) {
    	logger.info("updateCondMainDtl");
    	
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        TaMasCondMainDtl dtl = null;
        List<TaMasCondMainDtl> dtlList = new ArrayList<>();
        TaMasCondMainHdr header = formVo.getHeader();
        if (header.getBudgetYear() != null) {
            List<TaMasCondMainDtl> list = taMasCondMainDtlRepository.findByBudgetYearAndCondNumberAndCondTypeAndOfficeCode(formVo.getHeader().getBudgetYear(), formVo.getHeader().getCondNumber(), TA_MAS_COND_MAIN_TYPE.TAX, officeCode);
//			List<TaMasCondDtlTax> listY = taMasCondDtlTaxRepository.findByBudgetYearAndIsDeleted(formVo.getHeader().getBudgetYear(), FLAG.Y_FLAG);

            Collections.sort(list, new Comparator<TaMasCondMainDtl>() {
                public int compare(TaMasCondMainDtl dtlTax, TaMasCondMainDtl dtlTax2) {
                    int condGroup = dtlTax.getCondGroup().compareTo(dtlTax2.getCondGroup());
                    if (condGroup == 0) {
                        return condGroup;
                    }
                    return Long.valueOf(dtlTax.getCondGroup()) > Long.valueOf(dtlTax2.getCondGroup()) ? 1 : Long.valueOf(dtlTax.getCondGroup()) < Long.valueOf(dtlTax2.getCondGroup()) ? -1 : 0;
                }
            });

            if (list.size() - formVo.getDetail().size() > 0) {
                boolean checkDelete = true;
                for (TaMasCondMainDtl obj : list) {
                    checkDelete = true;
                    for (TaMasCondMainDtl ob : formVo.getDetail()) {
                        if (ob.getMasCondMainDtlId() == obj.getMasCondMainDtlId()) {
                            checkDelete = false;
                            break;
                        }
                    }
                    if (checkDelete) {
                        dtl = taMasCondMainDtlRepository.findById(obj.getMasCondMainDtlId()).get();
                        dtl.setIsDeleted(FLAG.Y_FLAG);
                        dtlList.add(dtl);
                    }
                }
                taMasCondMainDtlRepository.saveAll(dtlList);
            }

            dtlList = new ArrayList<>();
            int i = 0;
			int condGroupNum = 0;
            for (TaMasCondMainDtl obj : formVo.getDetail()) {
                if (obj.getMasCondMainDtlId() == null) {
                	i++;
					condGroupNum =  list.size()+i;
                    dtl = new TaMasCondMainDtl();
                    dtl.setBudgetYear(header.getBudgetYear());
                    dtl.setOfficeCode(header.getOfficeCode());
                    dtl.setCondGroup(Integer.toString(condGroupNum));
                    dtl.setCondNumber(header.getCondNumber());
                    dtl.setTaxFreqType(obj.getTaxFreqType());
                    dtl.setTaxMonthStart(obj.getTaxMonthStart());
                    dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
                    dtl.setRangeTypeStart(obj.getRangeTypeStart());
                    dtl.setRangeTypeEnd(obj.getRangeTypeEnd());
                    dtl.setRangeStart(obj.getRangeStart());
                    dtl.setRangeEnd(obj.getRangeEnd());
                    dtl.setRiskLevel(obj.getRiskLevel());
                    dtl.setCondDtlDesc(obj.getCondDtlDesc());
                    dtl.setCondType(TA_MAS_COND_MAIN_TYPE.TAX);

                } else {
                    dtl = taMasCondMainDtlRepository.findById(obj.getMasCondMainDtlId()).get();
                    dtl.setCondGroup(obj.getCondGroup());
                    dtl.setTaxFreqType(obj.getTaxFreqType());
                    dtl.setTaxMonthStart(obj.getTaxMonthStart());
                    dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
                    dtl.setRangeTypeStart(obj.getRangeTypeStart());
                    dtl.setRangeTypeEnd(obj.getRangeTypeEnd());
                    dtl.setRangeStart(obj.getRangeStart());
                    dtl.setRangeEnd(obj.getRangeEnd());
                    dtl.setRiskLevel(obj.getRiskLevel());
                    dtl.setCondDtlDesc(obj.getCondDtlDesc());
                }

                dtlList.add(dtl);

            }
            dtl = taMasCondMainDtlRepository.findByBudgetYearAndCondNumberAndCondTypeAndOfficeCode(formVo.getHeader().getBudgetYear(), dtl.getCondNumber(), TA_MAS_COND_MAIN_TYPE.OTHER, officeCode).get(0);
            dtl.setCondGroup(String.valueOf(formVo.getDetail().size() + 1));
            dtlList.add(dtl);
            taMasCondMainDtlRepository.saveAll(dtlList);
        }
    }

    public TaMasCondMainHdr findHdr(TaMasCondMainHdr hdr) {
    	logger.info("findHdr");
    	
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        return taMasCondMainHdrRepository.findByOfficeCodeAndBudgetYearAndCondNumber(officeCode, hdr.getBudgetYear(), hdr.getCondNumber());
    }

    public List<TaMasCondMainHdr> findHdrAll(TaMasCondMainHdr hdr) {
    	logger.info("findHdrAll");
    	
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        return taMasCondMainHdrRepository.findByOfficeCodeAndBudgetYear(officeCode, hdr.getBudgetYear());
    }

    public List<TaMasCondMainDtl> findDtl(TaMasCondMainDtl dtl) {
    	logger.info("findDtl");
    	
        List<TaMasCondMainDtl> budgetYear = new ArrayList<TaMasCondMainDtl>();
        budgetYear = taMasCondMainDtlRepository.findByBudgetYearAndCondNumberAndCondTypeAndOfficeCode(dtl.getBudgetYear(), dtl.getCondNumber(), TA_MAS_COND_MAIN_TYPE.TAX, UserLoginUtils.getCurrentUserBean().getOfficeCode());
        return budgetYear;
    }

    public List<TaMasCondMainHdr> findAllHdr() {
    	logger.info("findAllHdr");
    	
        List<TaMasCondMainHdr> list = taMasCondMainHdrRepository.findAll();
        return list;
    }

    public ConditionMessageVo conditionMessage() {
    	logger.info("conditionMessage");
    	
        ConditionMessageVo msgVo = new ConditionMessageVo();
        msgVo.setMsgMonth1(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "MONTH1").getValue1());
        msgVo.setMsgMonth2(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "MONTH2").getValue1());
        msgVo.setMsgTax1(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "TAX1").getValue1());
        msgVo.setMsgTax2(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_MAS_COND_MAIN_DESC, "TAX2").getValue1());
        return msgVo;
    }

    public List<ParamInfo> getMainCondRange() {
    	logger.info("getMainCondRange");
    	
        List<ParamInfo> list = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_MAIN_COND_RANGE);
        return list;
    }

    public List<TaMasCondMainHdr> getMainCondHdr(MasCondMainRequestVo formVo) {
    	logger.info("getMainCondHdr");
    	
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
    	List<TaMasCondMainHdr> mainData = taMasCondMainHdrRepository.findByOfficeCodeAndBudgetYear(officeCode, formVo.getBudgetYear());
    	if (CollectionUtils.isEmpty(mainData)) {
    		mainData = taMasCondMainHdrRepository.findByOfficeCodeAndBudgetYear(EXCISE_OFFICE_CODE.TA_CENTRAL_SELECTOR, formVo.getBudgetYear());
		}
        return mainData;
    }

    public List<MasCondMainResponseVo> getMainCondDtl(MasCondMainRequestVo formVo) {
    	logger.info("getMainCondDtl");
    	
        List<TaMasCondMainDtl> listDtl = taMasCondMainDtlRepository.findByBudgetYearAndCondNumber(formVo.getBudgetYear(), formVo.getCondNumber());

        List<MasCondMainResponseVo> listVo = new ArrayList<>();
        for (TaMasCondMainDtl dtl : listDtl) {
            MasCondMainResponseVo vo = new MasCondMainResponseVo();

            vo.setMasCondMainDtlId(dtl.getMasCondMainDtlId());
            vo.setOfficeCode(dtl.getOfficeCode());
            vo.setBudgetYear(dtl.getBudgetYear());
            vo.setCondNumber(dtl.getCondNumber());
            vo.setCondGroup(dtl.getCondGroup());
            if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(dtl.getCondType())) {
                vo.setCondTypeDesc(ApplicationCache.getParamInfoByCode("TA_MAS_COND_MAIN_DESC", "NEW_COMP").getValue1());
            } else {
                vo.setTaxFreqType(dtl.getTaxFreqType());
                vo.setTaxFreqTypeDesc(ApplicationCache.getParamInfoByCode("TA_MAIN_COND_FREQ_TYPE", dtl.getTaxFreqType()).getValue1());
                vo.setRangeTypeStart(dtl.getRangeTypeStart());
                vo.setRangeTypeStartDesc(ApplicationCache.getParamInfoByCode("TA_MAIN_COND_RANGE", dtl.getRangeTypeStart()).getValue1());
                vo.setRangeTypeEnd(dtl.getRangeTypeEnd());
                if (dtl.getRangeTypeEnd() != null) {
                    vo.setRangeTypeEndDesc(ApplicationCache.getParamInfoByCode("TA_MAIN_COND_RANGE", dtl.getRangeTypeEnd()).getValue1());
                }
                vo.setRiskLevelDesc(ApplicationCache.getParamInfoByCode("TA_RISK_LEVEL", dtl.getRiskLevel()).getValue1());
            }
            vo.setTaxMonthStart(dtl.getTaxMonthStart());
            vo.setTaxMonthEnd(dtl.getTaxMonthEnd());
            vo.setRangeStart(dtl.getRangeStart());
            vo.setRangeEnd(dtl.getRangeEnd());
            vo.setRiskLevel(dtl.getRiskLevel());
            vo.setCondType(dtl.getCondType());
            vo.setCondDtlDesc(dtl.getCondDtlDesc());

            listVo.add(vo);

        }

        return listVo;
    }
    
    public List<ParamInfo> getMainCondFreqType() {
    	logger.info("getMainCondFreqType");
    	
        List<ParamInfo> list = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_MAIN_COND_FREQ_TYPE);
        return list;
    }
    
    private String getTaxCompareType(int monthNum) {
    	final int MONTH_NUM = 24;
    	if (monthNum >= MONTH_NUM) {
    		return TAX_COMPARE_TYPE.HALF;
    	} else {
    		return TAX_COMPARE_TYPE.MONTH;
    	}
    }
    
//    =============== ta010101 =================
    public String insertCondMain(Ta010101Vo form) {
    	logger.info("insertCondMainHdr");
    	// insert condMainHdr
    	int condGroupNum = 2;
    	int monthNum = form.getCompMonthNum()*2;
    	String condGroupDesc = "เปรียบเทียบการชำระภาษีปีนี้กับปีก่อน";
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
    	TaMasCondMainHdr hdr = new TaMasCondMainHdr();			
    	if (StringUtils.isNotEmpty(form.getCondNumber())) {
    		hdr = taMasCondMainHdrRepository.findByCondNumber(form.getCondNumber());
		} else {
			hdr.setCondNumber(masterConditionSequenceService.getConditionNumber(officeCode, form.getBudgetYear()));			
		}
        hdr.setOfficeCode(officeCode);
        hdr.setBudgetYear(form.getBudgetYear());
        hdr.setCondGroupDesc(condGroupDesc);
        hdr.setMonthNum(monthNum);
        hdr.setCondGroupNum(condGroupNum);
        hdr.setNewFacFlag(FLAG.N_FLAG);
        hdr.setCompType(getTaxCompareType(form.getCompMonthNum()));
        hdr.setRegDateStart(ConvertDateUtils.parseStringToLocalDate(form.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
        hdr.setRegDateEnd(ConvertDateUtils.parseStringToLocalDate(form.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
        hdr.setCompMonthNum(form.getCompMonthNum());
        taMasCondMainHdrRepository.save(hdr);
        // insert condMainDtl
        int taxMonthStart = 1;
        List<TaMasCondMainDtl> dtlList = new ArrayList<>();
        if (StringUtils.isNotEmpty(form.getCondNumber())) {
        	dtlList = taMasCondMainDtlRepository.findByCondNumber(form.getCondNumber());
        	for (TaMasCondMainDtl dtl : dtlList) {
        		dtl.setRangeStart(new BigDecimal(form.getRangeStart()));
				dtl.setRiskLevel(form.getRiskLevel());
			}
		} else {
			for (int i = 0; i < 2; i++) {
				TaMasCondMainDtl dtl = new TaMasCondMainDtl();
				
				dtl.setOfficeCode(officeCode);
				dtl.setBudgetYear(form.getBudgetYear());
				dtl.setCondNumber(hdr.getCondNumber());
				dtl.setCondGroup(String.valueOf(i+1));
				dtl.setTaxFreqType(form.getTaxFreqType());
				dtl.setTaxMonthStart(taxMonthStart--);
				dtl.setTaxMonthEnd(monthNum--);
				dtl.setRangeTypeStart(form.getRangeTypeStart());
				dtl.setRangeStart(new BigDecimal(form.getRangeStart()));
				dtl.setRiskLevel(form.getRiskLevel());
				dtl.setCondType(form.getCondType());
				dtlList.add(dtl);
			}
		}
        taMasCondMainDtlRepository.saveAll(dtlList);
        // insert condSubNoAudit
        TaMasCondSubNoAudit noAudit = new TaMasCondSubNoAudit();
        if (StringUtils.isNotEmpty(form.getCondNumber())) {
			noAudit = condSubNoAuditRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), officeCode);
		}
        noAudit.setBudgetYear(form.getBudgetYear());
        noAudit.setOfficeCode(officeCode);
        noAudit.setNoTaxAuditYearNum(form.getNoTaxAuditYearNum());
        condSubNoAuditRepository.save(noAudit);
        return hdr.getCondNumber();
    }
    
    public Ta010101Vo getCondMain(Ta010101Vo form) {
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
    	List<TaMasCondMainHdr> hdr = taMasCondMainHdrRepository.findByOfficeCodeAndBudgetYear(officeCode, form.getBudgetYear());
    	TaMasCondSubNoAudit noAudit = condSubNoAuditRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), officeCode);
    	Ta010101Vo condForm = new Ta010101Vo();
    	int zero = 0;
    	if (zero < hdr.size()) {
    		List<TaMasCondMainDtl> dtlList = taMasCondMainDtlRepository.findByBudgetYearAndCondNumber(form.getBudgetYear(), hdr.get(0).getCondNumber());
    		condForm.setBudgetYear(hdr.get(0).getBudgetYear());
    		condForm.setCondNumber(hdr.get(0).getCondNumber());
    		condForm.setNoTaxAuditYearNum(noAudit.getNoTaxAuditYearNum());
    		condForm.setCompMonthNum(hdr.get(0).getCompMonthNum());
    		condForm.setRangeTypeStart(dtlList.get(0).getRangeTypeStart());
    		condForm.setRangeStart(dtlList.get(0).getRangeStart().intValueExact());
    		condForm.setRegDateStart(ConvertDateUtils.formatLocalDateToString(hdr.get(0).getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
    		condForm.setRegDateEnd(ConvertDateUtils.formatLocalDateToString(hdr.get(0).getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));			
		}
    	return condForm; 
    }

}
