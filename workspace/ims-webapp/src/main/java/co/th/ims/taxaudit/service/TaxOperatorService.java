package co.th.ims.taxaudit.service;

import co.th.baiwa.buckwaframework.common.util.AppDateUtils;
import co.th.ims.taxaudit.dao.jdbc.TaxOperatorRepository;
import co.th.ims.taxaudit.vo.TaxOperatorVo;
import co.th.ims.taxaudit.vo.TaxOperatorVo.TaxPay;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
					Integer start = this.formatDate(formVo.getYearStart(),formVo.getMonthStart());
					Integer end = this.formatDate(formVo.getYearEnd(),formVo.getMonthEnd());
					Integer value = this.formatDate(taxPay.getYear(),taxPay.getMonth());

					if (value >= start && value <= end) {
						taxOperatorVo.getTaxMonth().add(taxPay.getMonth());
						taxOperatorVo.getTaxAmount().add(taxPay.getTaxAmount());
					}
				}
			}
		}
		return operatoe;
	}

	private Integer formatDate(String year, String month) {

		int yearInt = NumberUtils.toInt(year);
		int monthInt = NumberUtils.toInt(month);
		LocalDate dateStart = LocalDate.of(yearInt, monthInt, 1);
		Date date = AppDateUtils.parseStringToDate(dateStart.toString(),
				AppDateUtils.YYYY_MM_DD_DAT, AppDateUtils.LOCAL_EN);
		String dateStr = AppDateUtils.formatDateToString(date,
				AppDateUtils.YYYYMM, AppDateUtils.LOCAL_EN);

		return NumberUtils.toInt(dateStr);
	}

}
