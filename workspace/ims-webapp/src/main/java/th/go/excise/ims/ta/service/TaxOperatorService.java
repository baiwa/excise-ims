package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaxOperatorRepository;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo.TaxPay;

@Service
public class TaxOperatorService {

	@Autowired
	private TaxOperatorRepository taxOperatorRepository;

	public List<TaxOperatorVo> getOperator(
			TaxOperatorVo.TaxOperatorFormVo formVo) {
		formVo.setYearStart("2015");
		formVo.setYearEnd("2017");
		formVo.setMonthStart("1");
		formVo.setMonthEnd("2");
		List<TaxOperatorVo> operatoe = this.taxOperatorRepository.getOperator(formVo);
		for (TaxOperatorVo taxOperatorVo : operatoe) {
			formVo.setNewRegId(taxOperatorVo.getNewRegId());
			List<String> years = this.taxOperatorRepository.getYearTax(formVo);

			taxOperatorVo.setTaxYear(years);
			for (String year : years) {
				List<TaxPay> months = this.taxOperatorRepository.getMonthTax(
						formVo, year);

				// loop add month
				for (TaxPay taxPay : months) {
//					Integer start = this.formatDate(formVo.getYearStart(),formVo.getMonthStart());
//					Integer end = this.formatDate(formVo.getYearEnd(),formVo.getMonthEnd());
//					Integer value = this.formatDate(taxPay.getYear(),taxPay.getMonth());
//
//					if (value >= start && value <= end) {
//						taxOperatorVo.getTaxMonth().add(taxPay.getMonth());
//						taxOperatorVo.getTaxAmount().add(taxPay.getTaxAmount());
//					}
				}
			}
		}
		return operatoe;
	}

}
