package th.go.excise.ims.ta.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaWorksheetCondDtlTaxJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaWorksheetCondHdrJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaxOperatorJdbcRepository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
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
	private TaWorksheetCondHdrJdbcRepository taWorksheetCondHdrJdbcRepository;
	@Autowired
	private TaWorksheetCondDtlTaxJdbcRepository taWorksheetCondDtlTaxJdbcRepository;

	@Autowired
	private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;

	public TaxOperatorVo getOperator(TaxOperatorFormVo formVo) {
		//List<String> listCondGroups = this.taxOperatorRepository.listCondGroups(formVo.getDraftNumber());
		List<TaxOperatorDetailVo> list = taxOperatorRepository.getTaxOperator(formVo);

		TaxOperatorVo vo = new TaxOperatorVo();
		//vo.setCondGroups(listCondGroups);
		vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo));
		vo.setCount(taxOperatorRepository.countTaxOperator(formVo));
		return vo;
	}

	public List<String> findAllAnalysisNumber() {
		return this.taWorksheetCondHdrJdbcRepository.findAllAnalysisNumber();
	}

	public TaxOperatorVo getOperatorDraft(TaxOperatorFormVo formVo) {
		
		String officeCode = formVo.getOfficeCode();
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
			formVo.setOfficeCode(officeCode);
		} else {
			formVo.setOfficeCode(null);
		}
		
		List<TaxOperatorDetailVo> list = taxOperatorRepository.getTaxOperatorDraft(formVo);

		TaxOperatorVo vo = new TaxOperatorVo();
		vo.setDatas(TaxAuditUtils.prepareTaxOperatorDatatable(list, formVo));
		vo.setCount(taxOperatorRepository.countTaxOperatorDraft(formVo));
		
		return vo;
	}

	public List<String> findAllDraftNumber() {
		return taDraftWorksheetHdrRepository.findAllDraftNumber();
	}

	public YearMonthVo monthStart(TaxOperatorFormVo formVo) {
		YearMonthVo obj = taWorksheetCondHdrJdbcRepository.monthStart(formVo.getAnalysisNumber());

		if (obj != null){
			Date ymStart = ConvertDateUtils.parseStringToDate(obj.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
			Date ymEnd = ConvertDateUtils.parseStringToDate(obj.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
			String ymStartStr = ConvertDateUtils.formatDateToString(ymStart, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
			String ymEndStr = ConvertDateUtils.formatDateToString(ymEnd, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

			obj.setYearMonthStart(ymStartStr);
			obj.setYearMonthEnd(ymEndStr);
		}
		return obj;
	}
	public YearMonthVo monthStartDraft(TaxOperatorFormVo formVo) {
		YearMonthVo obj = taDraftWorksheetHdrRepository.findMonthStartByDraftNumber(formVo.getDraftNumber());

		Date ymStart = ConvertDateUtils.parseStringToDate(obj.getYearMonthStart(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		Date ymEnd = ConvertDateUtils.parseStringToDate(obj.getYearMonthEnd(), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymStartStr = ConvertDateUtils.formatDateToString(ymStart, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
		String ymEndStr = ConvertDateUtils.formatDateToString(ymEnd, ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);

		obj.setYearMonthStart(ymStartStr);
		obj.setYearMonthEnd(ymEndStr);
		return obj;
	}

	public List<CondGroupVo> findCondGroupDtl(TaxOperatorFormVo formVo) {
		return taWorksheetCondDtlTaxJdbcRepository.findCondGroupDtl(formVo.getAnalysisNumber());
	}
	
}
