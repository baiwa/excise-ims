package th.go.excise.ims.ta.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.EXCISE_FACTORY_TYPE;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAMETER_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.preferences.service.BudgetYearService;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@Service
public class DraftWorksheetService {
	
	private static final Logger logger = LoggerFactory.getLogger(DraftWorksheetService.class);

	private static final String NO_TAX_AMOUNT = "-";

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;
	
	@Autowired
	private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;
	@Autowired
	private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;
	
	@Autowired
	private TaWorksheetSeqCtrlService taWorksheetSeqCtrlService;
	@Autowired
	private BudgetYearService budgetYearService;

	public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
		TaxOperatorVo vo = new TaxOperatorVo();
		try {
			List<TaxOperatorDetailVo> taxOperatorDetailVoList = prepareTaxOperatorDetailVoList(formVo);
			vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(taxOperatorDetailVoList, formVo));
			vo.setCount(taWsReg4000Repository.countAll(formVoToWsReg4000(formVo)));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;
	}
	
	public List<TaxOperatorDetailVo> prepareTaxOperatorDetailVoList(TaxOperatorFormVo formVo) throws SQLException {
		logger.info("prepareTaxOperatorDetailVoList startDate={}, endDate={}, dateRange={}", formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());
		
		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		
		List<TaWsReg4000> wsReg4000List = taWsReg4000Repository.findAllPagination(formVoToWsReg4000(formVo), formVo.getStart(), formVo.getLength());
		Map<String, List<TaWsInc8000M>> wsInc8000MMap = taWsInc8000MRepository.findByMonthRange(ymStart, ymEnd);
		List<TaWsInc8000M> wsInc8000MList = null;
		BigDecimal sumTaxAmtG1 = null;
		BigDecimal sumTaxAmtG2 = null;
		BigDecimal taxAmtChnPnt = null;
		ExciseDept exciseDeptSector = null;
		ExciseDept exciseDeptArea = null;
		String taxAmount = null;

		TaxOperatorDetailVo detailVo = null;
		List<TaxOperatorDetailVo> detailVoList = new ArrayList<>();
		for (TaWsReg4000 wsReg4000 : wsReg4000List) {
			logger.debug("wsReg4000.newRegId={}", wsReg4000.getNewRegId());

			int countTaxMonthNo = 0;
			int countG1 = 0;
			int countG2 = 0;
			sumTaxAmtG1 = BigDecimal.ZERO;
			sumTaxAmtG2 = BigDecimal.ZERO;

			detailVo = new TaxOperatorDetailVo();

			detailVo.setDutyCode(wsReg4000.getDutyCode());
			detailVo.setNewRegId(wsReg4000.getNewRegId());
			detailVo.setCusFullname(wsReg4000.getCusFullname());
			detailVo.setFacFullname(wsReg4000.getFacFullname());
			detailVo.setFacAddress(wsReg4000.getFacAddress());
			detailVo.setOfficeCode(wsReg4000.getOfficeCode());
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
				detailVoList.add(detailVo);
				continue;
			}

			for (TaWsInc8000M wsInc8000M : wsInc8000MList) {
				if (countG1 < formVo.getDateRange() / 2) {
					// Group 1
					if (wsInc8000M.getTaxAmount() != null) {
						taxAmount = wsInc8000M.getTaxAmount().toString();
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
			
			if (EXCISE_FACTORY_TYPE.PRODUCT.equals(wsReg4000.getFacType()) || EXCISE_FACTORY_TYPE.IMPORT.equals(wsReg4000.getFacType())) {
				detailVo.setDutyName(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.EXCISE_PRODUCT_TYPE, wsReg4000.getDutyCode()).getValue1());
			} else {
				detailVo.setDutyName(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.EXCISE_SERVICE_TYPE, wsReg4000.getDutyCode()).getValue1());
			}

			detailVoList.add(detailVo);
		}

		return detailVoList;
	}
	
	private TaWsReg4000 formVoToWsReg4000(TaxOperatorFormVo formVo) {
		TaWsReg4000 wsReg4000 = new TaWsReg4000();
		try {
			BeanUtils.copyProperties(wsReg4000, formVo);
			String officeCode = wsReg4000.getOfficeCode();
			if (StringUtils.isNotBlank(officeCode) && officeCode.length() == 6) {
				if ("000000".equals(officeCode)) {
					officeCode = null;
				} else if ("00".equals(officeCode.substring(officeCode.length() - 2, officeCode.length()))) {
					if ("00".equals(officeCode.substring(officeCode.length() - 4, officeCode.length() - 2))) {
						officeCode = officeCode.substring(0, officeCode.length() - 4) + "____";
					} else {
						officeCode = officeCode.substring(0, officeCode.length() - 2) + "__";
					}
				}
				wsReg4000.setOfficeCode(officeCode);
			} else {
				wsReg4000.setOfficeCode(null);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}

		return wsReg4000;
	}
	
	public void saveDraft(TaxOperatorFormVo formVo) throws SQLException {
		logger.info("saveDraft");
		
		formVo.setBudgetYear(budgetYearService.getCurrentBudgetYear());
		String dateStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String dateEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		String draftNumber = taWorksheetSeqCtrlService.getDraftNumber(UserLoginUtils.getCurrentUserBean().getOfficeCode(), formVo.getBudgetYear());

		// Header
		TaDraftWorksheetHdr draftHdr = new TaDraftWorksheetHdr();
		draftHdr.setDraftNumber(draftNumber);
		draftHdr.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		draftHdr.setYearMonthStart(dateStart);
		draftHdr.setYearMonthEnd(dateEnd);
		draftHdr.setMonthNum(formVo.getDateRange());
		draftHdr.setBudgetYear(formVo.getBudgetYear());
		taDraftWorksheetHdrRepository.save(draftHdr);

		// Detail
		List<TaxOperatorDetailVo> rsSearch = prepareTaxOperatorDetailVoList(formVo);

		List<TaDraftWorksheetDtl> dratfDtlList = new ArrayList<>();
		TaDraftWorksheetDtl draftDtl = new TaDraftWorksheetDtl();
		for (TaxOperatorDetailVo rs : rsSearch) {
			draftDtl = new TaDraftWorksheetDtl();

			draftDtl.setDraftNumber(draftNumber);
			draftDtl.setNewRegId(rs.getNewRegId());

			draftDtl.setSumTaxAmtG1(NumberUtils.nullToZero(NumberUtils.toBigDecimal(rs.getSumTaxAmtG1())));
			draftDtl.setSumTaxAmtG2(NumberUtils.nullToZero(NumberUtils.toBigDecimal(rs.getSumTaxAmtG2())));
			draftDtl.setTaxAmtChnPnt(NumberUtils.nullToZero(NumberUtils.toBigDecimal(rs.getTaxAmtChnPnt())));
			draftDtl.setTaxMonthNo(Integer.parseInt(rs.getTaxMonthNo()));

			draftDtl.setTaxAmtG1M1(rs.getTaxAmtG1M1());
			draftDtl.setTaxAmtG1M2(rs.getTaxAmtG1M2());
			draftDtl.setTaxAmtG1M3(rs.getTaxAmtG1M3());
			draftDtl.setTaxAmtG1M4(rs.getTaxAmtG1M4());
			draftDtl.setTaxAmtG1M5(rs.getTaxAmtG1M5());
			draftDtl.setTaxAmtG1M6(rs.getTaxAmtG1M6());
			draftDtl.setTaxAmtG1M7(rs.getTaxAmtG1M7());
			draftDtl.setTaxAmtG1M8(rs.getTaxAmtG1M8());
			draftDtl.setTaxAmtG1M9(rs.getTaxAmtG1M9());
			draftDtl.setTaxAmtG1M10(rs.getTaxAmtG1M10());
			draftDtl.setTaxAmtG1M11(rs.getTaxAmtG1M11());
			draftDtl.setTaxAmtG1M12(rs.getTaxAmtG1M12());

			draftDtl.setTaxAmtG2M1(rs.getTaxAmtG2M1());
			draftDtl.setTaxAmtG2M2(rs.getTaxAmtG2M2());
			draftDtl.setTaxAmtG2M3(rs.getTaxAmtG2M3());
			draftDtl.setTaxAmtG2M4(rs.getTaxAmtG2M4());
			draftDtl.setTaxAmtG2M5(rs.getTaxAmtG2M5());
			draftDtl.setTaxAmtG2M6(rs.getTaxAmtG2M6());
			draftDtl.setTaxAmtG2M7(rs.getTaxAmtG2M7());
			draftDtl.setTaxAmtG2M8(rs.getTaxAmtG2M8());
			draftDtl.setTaxAmtG2M9(rs.getTaxAmtG2M9());
			draftDtl.setTaxAmtG2M10(rs.getTaxAmtG2M10());
			draftDtl.setTaxAmtG2M11(rs.getTaxAmtG2M11());
			draftDtl.setTaxAmtG2M12(rs.getTaxAmtG2M12());

			draftDtl.setCreatedBy(UserLoginUtils.getCurrentUsername());
			draftDtl.setCreatedDate(LocalDateTime.now());
			
			dratfDtlList.add(draftDtl);
		}
		
		taDraftWorksheetDtlRepository.save(dratfDtlList);
	}
	
}
