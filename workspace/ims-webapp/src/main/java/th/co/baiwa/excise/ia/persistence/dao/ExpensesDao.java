package th.co.baiwa.excise.ia.persistence.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int06121FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;
import th.co.baiwa.excise.utils.OracleUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpensesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL = "SELECT * FROM IA_EXPENSES WHERE IS_DELETED = 'N' ";

    public Long count(Int06121FormVo formVo) {
        StringBuilder sql = new StringBuilder(SQL);
        List<Object> params = new ArrayList<>();

        if (StringUtils.isNotBlank(formVo.getAccountId())){
            sql.append(" AND ACCOUNT_ID LIKE ?");
            params.add("%"+StringUtils.trim(formVo.getAccountId())+"%");
        }
        if (StringUtils.isNotBlank(formVo.getAccountName())){
            sql.append(" AND ACCOUNT_NAME LIKE ? ");
            params.add("%"+StringUtils.trim(formVo.getAccountName())+"%");
        }

        String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

    public List<Int06121Vo> findAll(Int06121FormVo formVo) {
        StringBuilder sql = new StringBuilder(SQL);
        List<Object> params = new ArrayList<>();

        if (StringUtils.isNotBlank(formVo.getAccountId())){
            sql.append(" AND ACCOUNT_ID LIKE ?");
            params.add("%"+StringUtils.trim(formVo.getAccountId())+"%");
        }
        if (StringUtils.isNotBlank(formVo.getAccountName())){
            sql.append(" AND ACCOUNT_NAME LIKE ? ");
            params.add("%"+StringUtils.trim(formVo.getAccountName())+"%");
        }

        sql.append(" ORDER BY CREATED_DATE DESC ");
        List<Int06121Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), expensesRowmapper);
        return list;

    }

    private RowMapper<Int06121Vo> expensesRowmapper = new RowMapper<Int06121Vo>() {
        @Override
        public Int06121Vo mapRow(ResultSet rs, int arg1) throws SQLException {
            Int06121Vo vo = new Int06121Vo();

            vo.setId(rs.getString("ID"));
            vo.setAccountId(rs.getString("ACCOUNT_ID"));
            vo.setAccountName(rs.getString("ACCOUNT_NAME"));
            vo.setServiceReceive(rs.getString("SERVICE_RECEIVE"));
            vo.setServiceWithdraw(rs.getString("SERVICE_WITHDRAW"));
            vo.setServiceBalance(rs.getString("SERVICE_BALANCE"));
            vo.setSuppressReceive(rs.getString("SUPPRESS_RECEIVE"));
            vo.setSuppressWithdraw(rs.getString("SUPPRESS_WITHDRAW"));
            vo.setSuppressBalance(rs.getString("SUPPRESS_BALANCE"));
            vo.setBudgetReceive(rs.getString("BUDGET_RECEIVE"));
            vo.setBudgetWithdraw(rs.getString("BUDGET_WITHDRAW"));
            vo.setBudgetBalance(rs.getString("BUDGET_BALANCE"));
            vo.setSumReceive(rs.getString("SUM_RECEIVE"));
            vo.setSumWithdraw(rs.getString("SUM_WITHDRAW"));
            vo.setSumBalance(rs.getString("SUM_BALANCE"));
            vo.setMoneyBudget(rs.getString("MONEY_BUDGET"));
            vo.setMoneyOut(rs.getString("MONEY_OUT"));
            vo.setAverageCost(rs.getString("AVERAGE_COST"));
            vo.setAverageGive(rs.getString("AVERAGE_GIVE"));
            vo.setAverageFrom(rs.getString("AVERAGE_FROM"));
            vo.setAverageComeCost(rs.getString("AVERAGE_COME_COST"));
            vo.setCreatedBy(rs.getString("CREATED_BY"));
            vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE")));
            vo.setNote(rs.getString("NOTE"));
            return vo;
        }
    };
}
