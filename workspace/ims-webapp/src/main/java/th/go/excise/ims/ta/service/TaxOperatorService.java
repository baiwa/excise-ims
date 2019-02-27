package th.go.excise.ims.ta.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
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
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	
	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;

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
		return taWorksheetHdrRepository.findAllAnalysisNumberByOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
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

	public YearMonthVo monthStart(TaxOperatorFormVo formVo) {
		YearMonthVo obj = taWorksheetCondMainHdrRepository.findMonthStartByAnalysisNumber(formVo.getAnalysisNumber());

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

	public List<CondGroupVo> findCondGroupDtl(TaxOperatorFormVo formVo) {
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
	
}
