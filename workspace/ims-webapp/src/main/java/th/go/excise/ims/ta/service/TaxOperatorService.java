package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaDraftWorksheetJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaWorksheetCondDtlTaxJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaWorksheetCondHdrJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaxOperatorJdbcRepository;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Service
public class TaxOperatorService {

	@Autowired
	private TaxOperatorJdbcRepository taxOperatorRepository;

	@Autowired
	private TaWorksheetCondHdrJdbcRepository worksheetCondHdrJdbcRepository;

	@Autowired
	private TaWorksheetCondDtlTaxJdbcRepository worksheetCondDtlTaxJdbcRepository;

	@Autowired
	private TaMasCondHdrRepository masCondHdrRepository;

	@Autowired
	private TaMasCondDtlTaxRepository masCondDtlTaxRepository;

	@Autowired
	private TaWorksheetCondHdrRepository taWorksheetCondHdrRepository;

	@Autowired
	private TaWorksheetCondDtlTaxRepository taWorksheetCondDtlTaxRepository;

	@Autowired
	private TaxAuditFactorySelectionService taxAuditFactorySelectionService;

	@Autowired
	private TaDraftWorksheetDtlRepository draftWorksheetRepository;

	@Autowired
	private TaDraftWorksheetJdbcRepository draftWorksheetJdbcRepository;

	@Autowired
	private TaDraftWorksheetHdrRepository draftWorksheetHdrRepository;

	public TaxOperatorVo getOperator(TaxOperatorFormVo formVo) throws BusinessException {
		List<String> listCondGroups = this.taxOperatorRepository.listCondGroups(formVo.getAnalysisNumber());
		List<TaxOperatorDetailVo> list = this.taxOperatorRepository.getTaxOperator(formVo.getAnalysisNumber());

		TaxOperatorVo vo = new TaxOperatorVo();
		vo.setCondGroups(listCondGroups);
		vo.setDatas(taxAuditFactorySelectionService.summaryDatatable(list, formVo));
		return vo;
	}

	public List<String> findAllAnalysisNumber() {
		return this.worksheetCondHdrJdbcRepository.findAllAnalysisNumber();
	}

	public TaxOperatorVo getOperatorDraft(TaxOperatorFormVo formVo) {
		List<TaxOperatorDetailVo> list = this.taxOperatorRepository.getTaxOperatorDraft(formVo.getAnalysisNumber());

		TaxOperatorVo vo = new TaxOperatorVo();
		vo.setDatas(taxAuditFactorySelectionService.summaryDatatable(list, formVo));
		return vo;
	}

	public List<String> findAllAnalysisNumberDraft() {
		return this.draftWorksheetJdbcRepository.analysisNumberDraft();
	}

	public YearMonthVo monthStart(TaxOperatorFormVo formVo) {
		YearMonthVo obj = this.worksheetCondHdrJdbcRepository.monthStart(formVo.getAnalysisNumber());

		Date ymStart = ConvertDateUtils.parseStringToDate(obj.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		Date ymEnd = ConvertDateUtils.parseStringToDate(obj.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymStartStr = ConvertDateUtils.formatDateToString(ymStart, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEndStr = ConvertDateUtils.formatDateToString(ymEnd, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

		obj.setYearMonthStart(ymStartStr);
		obj.setYearMonthEnd(ymEndStr);
		return obj;
	}

	public List<CondGroupVo> findCondGroupDtl(TaxOperatorFormVo formVo) {
		return this.worksheetCondDtlTaxJdbcRepository.findCondGroupDtl(formVo.getAnalysisNumber());
	}

	public void saveDraft(TaxOperatorFormVo formVo) throws SQLException {

		formVo.setBudgetYear(ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYY));
		// TODO convert date MM/yyyy to yyyyMM
		Date dateStart = ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String dateStartStr = ConvertDateUtils.formatDateToString(dateStart, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		Date dateEnd = ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String dateEndStr = ConvertDateUtils.formatDateToString(dateEnd, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

		String analysisNumber = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN);

		TaMasCondHdr masHeader = this.masCondHdrRepository.findByBudgetYear(formVo.getBudgetYear());
		List<TaMasCondDtlTax> masDetail = this.masCondDtlTaxRepository.findByBudgetYear(masHeader.getBudgetYear());

		// ==> save header
		TaWorksheetCondHdr conHdr = new TaWorksheetCondHdr();
		conHdr.setAnalysisNumber(analysisNumber);
		conHdr.setBudgetYear(formVo.getBudgetYear());
		conHdr.setMonthNum(new BigDecimal(masHeader.getMonthNum()));
		conHdr.setYearMonthStart(dateStartStr);
		conHdr.setYearMonthEnd(dateEndStr);
		conHdr.setAreaSeeFlag(masHeader.getAreaSeeFlag());
		conHdr.setAreaSelectFlag(masHeader.getAreaSelectFlag());
		conHdr.setNoAuditYearNum(new BigDecimal(masHeader.getNoAuditYearNum()));
		this.taWorksheetCondHdrRepository.save(conHdr);

		// ==> save detail
		List<TaWorksheetCondDtlTax> condDetails = new ArrayList<>();
		for (TaMasCondDtlTax detail : masDetail) {

			TaWorksheetCondDtlTax condDetail = new TaWorksheetCondDtlTax();

			condDetail.setAnalysisNumber(analysisNumber);
			condDetail.setCondGroup(detail.getCondGroup());
			condDetail.setTaxMonthStart(new BigDecimal(detail.getTaxMonthStart()));
			condDetail.setTaxMonthEnd(new BigDecimal(detail.getTaxMonthEnd()));
			condDetail.setRangeStart(new BigDecimal(detail.getRangeStart()));
			condDetail.setRangeEnd(new BigDecimal(detail.getRangeEnd()));
			condDetail.setRiskLevel(detail.getRiskLevel());

			condDetails.add(condDetail);
		}

		this.taWorksheetCondDtlTaxRepository.saveAll(condDetails);

		// TODO ==>save draft
		// Header
		TaDraftWorksheetHdr draftHdr = new TaDraftWorksheetHdr();
		draftHdr.setAnalysisNumber(analysisNumber);
		draftHdr.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeId());
		draftHdr.setYearMonthStart(dateStartStr);
		draftHdr.setYearMonthEnd(dateEndStr);
	
		this.draftWorksheetHdrRepository.save(draftHdr);
		
		//Detail
		List<TaxOperatorDetailVo> rsSearch = this.taxAuditFactorySelectionService.prepareTaxOperatorDetailVoList(formVo);

		List<TaDraftWorksheetDtl> dratfs = new ArrayList<>();
		for (TaxOperatorDetailVo rs : rsSearch) {

			TaDraftWorksheetDtl draft = new TaDraftWorksheetDtl();

			draft.setAnalysisNumber(analysisNumber);
			draft.setNewRegId(rs.getNewRegId());

			draft.setSumTaxAmtG1(this.checkNull(rs.getSumTaxAmtG1()));
			draft.setSumTaxAmtG2(this.checkNull(rs.getSumTaxAmtG2()));
			draft.setTaxAmtChnPnt(this.checkNull(rs.getTaxAmtChnPnt()));
			draft.setTaxMonthNo(this.checkNull(rs.getTaxMonthNo()));

			draft.setTaxAmtG1M1(this.replaceStringNull(rs.getTaxAmtG1M1()));
			draft.setTaxAmtG1M2(this.replaceStringNull(rs.getTaxAmtG1M2()));
			draft.setTaxAmtG1M3(this.replaceStringNull(rs.getTaxAmtG1M3()));
			draft.setTaxAmtG1M4(this.replaceStringNull(rs.getTaxAmtG1M4()));
			draft.setTaxAmtG1M5(this.replaceStringNull(rs.getTaxAmtG1M5()));
			draft.setTaxAmtG1M6(this.replaceStringNull(rs.getTaxAmtG1M6()));
			draft.setTaxAmtG1M7(this.replaceStringNull(rs.getTaxAmtG1M7()));
			draft.setTaxAmtG1M8(this.replaceStringNull(rs.getTaxAmtG1M8()));
			draft.setTaxAmtG1M9(this.replaceStringNull(rs.getTaxAmtG1M9()));
			draft.setTaxAmtG1M10(this.replaceStringNull(rs.getTaxAmtG1M10()));
			draft.setTaxAmtG1M11(this.replaceStringNull(rs.getTaxAmtG1M11()));
			draft.setTaxAmtG1M12(this.replaceStringNull(rs.getTaxAmtG1M12()));

			draft.setTaxAmtG2M1(this.replaceStringNull(rs.getTaxAmtG2M1()));
			draft.setTaxAmtG2M2(this.replaceStringNull(rs.getTaxAmtG2M2()));
			draft.setTaxAmtG2M3(this.replaceStringNull(rs.getTaxAmtG2M3()));
			draft.setTaxAmtG2M4(this.replaceStringNull(rs.getTaxAmtG2M4()));
			draft.setTaxAmtG2M5(this.replaceStringNull(rs.getTaxAmtG2M5()));
			draft.setTaxAmtG2M6(this.replaceStringNull(rs.getTaxAmtG2M6()));
			draft.setTaxAmtG2M7(this.replaceStringNull(rs.getTaxAmtG2M7()));
			draft.setTaxAmtG2M8(this.replaceStringNull(rs.getTaxAmtG2M8()));
			draft.setTaxAmtG2M9(this.replaceStringNull(rs.getTaxAmtG2M9()));
			draft.setTaxAmtG2M10(this.replaceStringNull(rs.getTaxAmtG2M10()));
			draft.setTaxAmtG2M11(this.replaceStringNull(rs.getTaxAmtG2M11()));
			draft.setTaxAmtG2M12(this.replaceStringNull(rs.getTaxAmtG2M12()));

			draft.setCondTaxGrp(rs.getCondTaxGrp());
			draft.setCreatedBy(UserLoginUtils.getCurrentUsername());
			draft.setCreatedDate(LocalDateTime.now());
			draft.setOfficeCode(rs.getOfficeCode());
			dratfs.add(draft);
		}
		this.draftWorksheetRepository.saveBatchDraft(dratfs);
	}

	public BigDecimal checkNull(String amount) {

		if (amount != null) {
			return new BigDecimal(amount.replace(",", ""));
		}
		return null;
	}

	public String replaceStringNull(String amount) {

		if (amount != null) {
			return amount.replace(",", "");
		}
		return null;
	}
}
