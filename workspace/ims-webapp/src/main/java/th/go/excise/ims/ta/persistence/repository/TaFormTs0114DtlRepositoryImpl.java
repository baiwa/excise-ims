package th.go.excise.ims.ta.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.TaFormTS0114DtlVo;

import java.util.ArrayList;
import java.util.List;

public class TaFormTs0114DtlRepositoryImpl implements TaFormTs0114DtlRepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void setIsDeleteY(String office, String budgetYear, String formTsNumber) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE TA_FORM_TS0114_DTL SET IS_DELETED='Y' WHERE OFFICE_CODE=? AND BUDGET_YEAR=? AND FORM_TS_NUMBER=?");

        List<Object> params = new ArrayList<>();
        params.add(office);
        params.add(budgetYear);
        params.add(formTsNumber);
        commonJdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public TaFormTS0114DtlVo formDtl(String office, String budgetYear, String formTsNumber) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TA_FORM_TS0114_DTL WHERE OFFICE_CODE=? AND BUDGET_YEAR=? AND FORM_TS_NUMBER=?");

        List<Object> params = new ArrayList<>();
        params.add(office);
        params.add(budgetYear);
        params.add(formTsNumber);
        return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(TaFormTS0114DtlVo.class));
    }

}
