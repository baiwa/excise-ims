package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheet;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetRepository;
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
	private TaDraftWorksheetRepository draftWorksheetRepository;

	@Autowired
	private TaDraftWorksheetJdbcRepository draftWorksheetJdbcRepository;
	
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

	public void getWorkSheetHdrDraft(TaxOperatorFormVo formVo) {

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

	public void saveDraft(TaxOperatorFormVo formVo) {

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
		List<TaxOperatorDetailVo> rsSearch = this.taxAuditFactorySelectionService.prepareTaxOperatorDetailVoList(formVo);

		List<TaDraftWorksheet> dratfs = new ArrayList<>();
		for (TaxOperatorDetailVo rs : rsSearch) {

			TaDraftWorksheet draft = new TaDraftWorksheet();

			draft.setAnalysisNumber(analysisNumber);
			draft.setNewRegId(rs.getNewRegId());
			draft.setSumTaxAmtG1(new BigDecimal(rs.getSumTaxAmtG1()));
			draft.setSumTaxAmtG2(new BigDecimal(rs.getSumTaxAmtG2()));
			draft.setTaxAmtChnPnt(new BigDecimal(rs.getTaxAmtChnPnt()));
			draft.setTaxMonthNo(new BigDecimal(rs.getTaxMonthNo()));

			draft.setTaxAmtG1M1(rs.getTaxAmtG1M1());
			draft.setTaxAmtG1M2(rs.getTaxAmtG1M2());
			draft.setTaxAmtG1M3(rs.getTaxAmtG1M3());
			draft.setTaxAmtG1M4(rs.getTaxAmtG1M4());
			draft.setTaxAmtG1M5(rs.getTaxAmtG1M5());
			draft.setTaxAmtG1M6(rs.getTaxAmtG1M6());
			draft.setTaxAmtG1M7(rs.getTaxAmtG1M7());
			draft.setTaxAmtG1M8(rs.getTaxAmtG1M8());
			draft.setTaxAmtG1M9(rs.getTaxAmtG1M9());
			draft.setTaxAmtG1M10(rs.getTaxAmtG1M10());
			draft.setTaxAmtG1M11(rs.getTaxAmtG1M11());
			draft.setTaxAmtG1M12(rs.getTaxAmtG1M12());

			draft.setTaxAmtG2M1(rs.getTaxAmtG2M1());
			draft.setTaxAmtG2M2(rs.getTaxAmtG2M2());
			draft.setTaxAmtG2M3(rs.getTaxAmtG2M3());
			draft.setTaxAmtG2M4(rs.getTaxAmtG2M4());
			draft.setTaxAmtG2M5(rs.getTaxAmtG2M5());
			draft.setTaxAmtG2M6(rs.getTaxAmtG2M6());
			draft.setTaxAmtG2M7(rs.getTaxAmtG2M7());
			draft.setTaxAmtG2M8(rs.getTaxAmtG2M8());
			draft.setTaxAmtG2M9(rs.getTaxAmtG2M9());
			draft.setTaxAmtG2M10(rs.getTaxAmtG2M10());
			draft.setTaxAmtG2M11(rs.getTaxAmtG2M11());
			draft.setTaxAmtG2M12(rs.getTaxAmtG2M12());

			draft.setCondTaxGrp(rs.getCondTaxGrp());

			dratfs.add(draft);
		}
		this.draftWorksheetRepository.saveAll(dratfs);
	}

}
