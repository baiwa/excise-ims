package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.jdbc.TaMasCondDtlTaxJdbcRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaxOperatorJdbcRepository;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.TaxPay;

@Service
public class TaxOperatorService {

	@Autowired
	private TaxOperatorJdbcRepository taxOperatorRepository;

	@Autowired
	private TaMasCondDtlTaxJdbcRepository taMasCondDtlTaxJdbcRepository;

	public List<TaxOperatorVo> getOperator(TaxOperatorFormVo formVo) {

		formVo.setDateStart("201501");
		formVo.setDateEnd("201705");

		List<TaxOperatorVo> operator = this.taxOperatorRepository.getOperator(formVo);
		for (TaxOperatorVo taxOperatorVo : operator) {

			formVo.setNewRegId(taxOperatorVo.getNewRegId());
			List<String> years = this.taxOperatorRepository.getYearTax(formVo);
			taxOperatorVo.setTaxYear(years);

			List<TaxPay> monthsAndAmount = this.taxOperatorRepository.getMonthTax(formVo);

			// loop add month
			int countTaxMonth = 1;
			for (TaxPay taxPay : monthsAndAmount) {
				taxOperatorVo.getTaxMonth().add(taxPay.getMonth());

				if (taxPay.getTaxAmount() == null) {
					taxPay.setTaxAmount(new BigDecimal(0));
					taxOperatorVo.setCountTaxPayOfMonth(taxOperatorVo.getCountTaxPayOfMonth() + countTaxMonth);

					countTaxMonth++;
				}

				taxOperatorVo.getTaxAmount().add(taxPay.getTaxAmount());
			}
		}
		List<CondGroupVo> congroups = this.getCondGroup();
		// TODO summaryTaxAmount
		for (TaxOperatorVo taxOperatorVo : operator) {

			List<BigDecimal> taxAmounts = taxOperatorVo.getTaxAmount();
			int size = taxAmounts.size();
			int before = size / 2;
			int countBefore = 0;
			for (BigDecimal tax : taxAmounts) {
				countBefore++;
				if (countBefore < before) {
					taxOperatorVo.setTaxAmountBefore(taxOperatorVo.getTaxAmountBefore().add(tax));
					continue;

				} else {
					taxOperatorVo.setTaxAmountAfter(taxOperatorVo.getTaxAmountAfter().add(tax));
				}
			}

			BigDecimal sub = taxOperatorVo.getTaxAmountAfter().subtract(taxOperatorVo.getTaxAmountBefore());
			BigDecimal multipy = sub.multiply(new BigDecimal(100));
			BigDecimal result = new BigDecimal(0);
			if (taxOperatorVo.getTaxAmountBefore().compareTo(BigDecimal.ZERO) == 0) {
				result = null;
			} else {
				result = multipy.divide(taxOperatorVo.getTaxAmountBefore(), 2, RoundingMode.HALF_UP);
			}

			taxOperatorVo.setDiffTaxAmount(result);

			// TODO group risk
			int monthLenght = taxOperatorVo.getTaxMonth().size();
			int countTaxPayOfMonth = (int) taxOperatorVo.getCountTaxPayOfMonth();
			int month = monthLenght - countTaxPayOfMonth;

			for (CondGroupVo group : congroups) {

				BigDecimal rangeStart = group.getRangeStart();
				BigDecimal RangeEnd = group.getRangeEnd();
				Integer monthStart = group.getTaxMonthStart();
				Integer monthEnd = group.getTaxMonthEnd();
				String congroup = group.getCondGroup();

				if (month >= monthStart && month <= monthEnd) {

					int start = taxOperatorVo.getDiffTaxAmount().compareTo(rangeStart);
					int end = taxOperatorVo.getDiffTaxAmount().compareTo(RangeEnd);

					if (start >= 0 && end <= 0) {
						taxOperatorVo.setCondGroup(congroup);
					}
				}
			}
		}
		return operator;
	}

	public List<CondGroupVo> getCondGroup() {
		return this.taMasCondDtlTaxJdbcRepository.getCondGroup();
	}

}
