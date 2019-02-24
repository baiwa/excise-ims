package th.go.excise.ims.preferences.service;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;

import org.springframework.stereotype.Service;

@Service
public class BudgetYearService {

	public String getCurrentBudgetYear() {
		return getBudgetYearByLocalDate(LocalDate.now());
	}

	public String getBudgetYearByLocalDate(LocalDate localDate) {
		int budgetYear = ThaiBuddhistDate.from(localDate).get(ChronoField.YEAR);
		if (localDate.getMonthValue() >= 10) {
			budgetYear++;
		}
		return String.valueOf(budgetYear);
	}
}
