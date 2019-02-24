package th.go.excise.ims.preferences.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class BudgetYearService {

	public String getCurrentBudgetYear() {

		return getBudgetYearByLocalDate(LocalDate.now());
	}

	public String getBudgetYearByLocalDate(LocalDate localDate) {
		//  01/05/2561 == "2561"
		//  01/10/2561 == "2562"
		//  01/01/2562 == "2562"
		//  30/09/2562 == "2562"
		//  01/10/2562 == "2563"
		
		return "";
	}
}
