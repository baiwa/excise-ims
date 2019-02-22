package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.vo.TaxDratfVo;

@Service
public class TaWorksheetHdrService {
	private final Logger logger = LoggerFactory.getLogger(TaWorksheetHdrService.class);

	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;

	@Autowired
	private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Autowired
	private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;

	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;

	@Autowired
	private TaMasCondHdrRepository taMasCondHdrRepository;

	@Autowired
	private TaMasCondDtlTaxRepository taMasCondDtlTaxRepository;

	@Autowired
	private TaWorksheetCondHdrRepository taWorksheetCondHdrRepository;

	@Autowired
	private TaWorksheetCondDtlTaxRepository taWorksheetCondDtlTaxRepository;

	public List<TaWorksheetHdr> findTaWorksheetHdrBySubConditionRegCapital(BigDecimal from, BigDecimal to) {
		logger.info("findTaWorksheetHdrBySubConditionRegCapital from {} to {} ", from, to);
		try {
			return taWorksheetHdrRepository.findSubConditionRegCapital(from, to);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return new ArrayList<TaWorksheetHdr>();
	}

	public void addTaWorksheetHdrByCondition(String draftNumber, String budgetYear) {
		String analysisNumber = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN);
		TaWorksheetHdr taWorksheetHdr = null;
		TaWorksheetDtl taWorksheetDtl = null;
		try {
			TaDraftWorksheetHdr taDraftWorksheetHdr = taDraftWorksheetHdrRepository.findByDraftNumber(draftNumber);
			TaMasCondHdr masHeader = this.taMasCondHdrRepository.findByBudgetYear(budgetYear);
			List<TaMasCondDtlTax> masDetail = this.taMasCondDtlTaxRepository.findByBudgetYear(masHeader.getBudgetYear());

			// ==> save header
			TaWorksheetCondHdr conHdr = new TaWorksheetCondHdr();
			conHdr.setAnalysisNumber(analysisNumber);
			conHdr.setBudgetYear(budgetYear);
			conHdr.setMonthNum(new BigDecimal(masHeader.getMonthNum()));
			conHdr.setYearMonthStart(taDraftWorksheetHdr.getYearMonthStart());
			conHdr.setYearMonthEnd(taDraftWorksheetHdr.getYearMonthEnd());
			conHdr.setAreaSeeFlag(masHeader.getAreaSeeFlag());
			conHdr.setAreaSelectFlag(masHeader.getAreaSelectFlag());
			conHdr.setNoAuditYearNum(new BigDecimal(masHeader.getNoAuditYearNum()));
			this.taWorksheetCondHdrRepository.save(conHdr);
			String indexLastCondition = "";
			// ==> save detail
			List<TaWorksheetCondDtlTax> condDetails = new ArrayList<>();
			for (TaMasCondDtlTax detail : masDetail) {
				TaWorksheetCondDtlTax condDetail = new TaWorksheetCondDtlTax();
				condDetail.setAnalysisNumber(analysisNumber);
				condDetail.setCondGroup(detail.getCondGroup());
				if (detail.getTaxMonthStart() != null) {
					condDetail.setTaxMonthStart(new BigDecimal(detail.getTaxMonthStart()));
				}
				if (detail.getTaxMonthEnd() != null) {
					condDetail.setTaxMonthEnd(new BigDecimal(detail.getTaxMonthEnd()));
				}
				if (detail.getRangeStart() != null) {
					condDetail.setRangeStart(new BigDecimal(detail.getRangeStart()));
				}
				if (detail.getRangeEnd() != null) {
					condDetail.setRangeEnd(new BigDecimal(detail.getRangeEnd()));
				}
				condDetail.setRiskLevel(detail.getRiskLevel());
				condDetail.setCondType(detail.getCondType());
				if (TA_MAS_COND_MAIN_TYPE.OTHER.equals(detail.getCondType())) {
					indexLastCondition = detail.getCondGroup();
				}
				condDetails.add(condDetail);
			}

			this.taWorksheetCondDtlTaxRepository.saveAll(condDetails);

			List<TaxDratfVo> taxDratfVoList = taWsReg4000Repository.findByDraftNumbwe(draftNumber);
			for (TaxDratfVo taxDratfVo : taxDratfVoList) {
				taWorksheetHdr = new TaWorksheetHdr();
				taWorksheetDtl = new TaWorksheetDtl();
				BeanUtils.copyProperties(taWorksheetHdr, taxDratfVo);
				BeanUtils.copyProperties(taWorksheetDtl, taxDratfVo);
				taWorksheetHdr.setAnalysisNumber(analysisNumber);
				taWorksheetDtl.setAnalysisNumber(analysisNumber);
				taWorksheetDtl.setCondMainGrp("0");
				if (taxDratfVo.getRegDate() != null) {
					// Check Case O
					String regisDate = taxDratfVo.getRegDate().format(DateTimeFormatter.ofPattern("yyyyMM"));
					if (regisDate.compareTo(taDraftWorksheetHdr.getYearMonthStart()) >= 0 && regisDate.compareTo(taDraftWorksheetHdr.getYearMonthEnd()) <= 0) {
						taWorksheetDtl.setCondMainGrp(indexLastCondition);
					} else {
						// Check Case T
						for (TaWorksheetCondDtlTax taWorksheetCondDtlTax : condDetails) {
							try {
								if(TA_MAS_COND_MAIN_TYPE.TAX.equals(taWorksheetCondDtlTax.getCondType())) {
									if (taWorksheetCondDtlTax.getTaxMonthStart().compareTo(taxDratfVo.getTaxMonthNo()) != 1 && taWorksheetCondDtlTax.getTaxMonthEnd().compareTo(taxDratfVo.getTaxMonthNo()) != -1 && taWorksheetCondDtlTax.getRangeStart().compareTo(taxDratfVo.getTaxAmtChnPnt()) != 1
											&& taWorksheetCondDtlTax.getRangeEnd().compareTo(taxDratfVo.getTaxAmtChnPnt()) != -1) {
										taWorksheetDtl.setCondMainGrp(taWorksheetCondDtlTax.getCondGroup());
										break;
									}
								}
								
							} catch (Exception e) {
								System.out.println("####################################");
								System.out.println("####################################");
								System.out.println(taxDratfVo.getNewRegId());
								System.out.println("####################################");
								System.out.println("####################################");
								e.printStackTrace();
							}
						}
					}
				}
				taWorksheetHdrRepository.save(taWorksheetHdr);
				taWorksheetDtlRepository.save(taWorksheetDtl);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
