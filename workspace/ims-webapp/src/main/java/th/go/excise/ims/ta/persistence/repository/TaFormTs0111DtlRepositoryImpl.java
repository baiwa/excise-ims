package th.go.excise.ims.ta.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class TaFormTs0111DtlRepositoryImpl implements TaFormTs0111DtlRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void setIsDeleteY(String office, String budgetYear, String formTsNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TA_FORM_TS0111_DTL SET IS_DELETED='Y' WHERE OFFICE_CODE=? AND BUDGET_YEAR=? AND FORM_TS_NUMBER=?");

		List<Object> params = new ArrayList<>();
		params.add(office);
		params.add(budgetYear);
		params.add(formTsNumber);
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}
}
