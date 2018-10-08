package th.co.baiwa.excise.ia.persistence.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int06113FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06113Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;
import th.co.baiwa.excise.utils.OracleUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
        List<Int06121Vo> list = jdbcTemplate.query(limit, params.toArray(), expensesRowmapper);
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

    /*Int06113*/
    public Long countCheckCost(Int06113FormVo formVo) {
        StringBuilder sql = new StringBuilder(SQL);
        List<Object> params = new ArrayList<>();

        if (StringUtils.isNotBlank(formVo.getSector())){
            sql.append(" AND SECTOR_ID = ?");
            params.add(StringUtils.trim(formVo.getSector()));
        }
        if (StringUtils.isNotBlank(formVo.getArea())){
            sql.append(" AND AREA_ID = ? ");
            params.add(StringUtils.trim(formVo.getArea()));
        }
        if (StringUtils.isNotBlank(formVo.getYear())){
        	sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
            params.add(StringUtils.trim(formVo.getYearFrom()));
            params.add(StringUtils.trim(formVo.getYearTo()));
        }

        String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

    public List<Int06113Vo> findAllCheckCost(Int06113FormVo formVo) {
        StringBuilder sql = new StringBuilder(SQL);
        List<Object> params = new ArrayList<>();

        if (StringUtils.isNotBlank(formVo.getSector())){
            sql.append(" AND SECTOR_ID = ?");
            params.add(StringUtils.trim(formVo.getSector()));
        }
        if (StringUtils.isNotBlank(formVo.getArea())){
            sql.append(" AND AREA_ID = ? ");
            params.add(StringUtils.trim(formVo.getArea()));
        }
        if (StringUtils.isNotBlank(formVo.getYear())){
            sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
            params.add(StringUtils.trim(formVo.getYearFrom()));
            params.add(StringUtils.trim(formVo.getYearTo()));
        }

        sql.append(" ORDER BY CREATED_DATE DESC ");
        List<Int06113Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), expensesCheckCostRowmapper);
        return list;

    }

    private RowMapper<Int06113Vo> expensesCheckCostRowmapper = new RowMapper<Int06113Vo>() {
        @Override
        public Int06113Vo mapRow(ResultSet rs, int arg1) throws SQLException {
            Int06113Vo vo = new Int06113Vo();

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

    public List<LabelValueBean> year(){
        String sql ="SELECT DISTINCT TO_CHAR(CREATED_DATE,'YYYYMMDD') YEAR FROM IA_EXPENSES";
        List<LabelValueBean> list = jdbcTemplate.query(sql.toString(), yearRowmapper);
        return list;
    }
    private RowMapper<LabelValueBean> yearRowmapper = new RowMapper<LabelValueBean>() {
        @Override
        public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
            Date date = DateConstant.convertStrToDate(rs.getString("YEAR"), ExciseConstants.FORMAT_DATE.YYYYMMDD, ExciseConstants.LOCALE.EN);
            
            String strDate = DateConstant.convertDateToStr(date,ExciseConstants.FORMAT_DATE.YYYYMMDD,ExciseConstants.LOCALE.EN);
            String yyyyEn = DateConstant.convertDateToStr(date,ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);            
            String yyyyTh = DateConstant.convertDateToStr(date,ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.TH);
            
            int intDate = Integer.parseInt(strDate); 
            int intYyyy = Integer.parseInt(yyyyEn+"1001");
            
            String result = yyyyTh;
            if (intDate >= intYyyy) {
            	result = DateConstant.convertDateToStr(DateUtils.addYears(date, 1), ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.TH);
			 
			}
            LabelValueBean vo = new LabelValueBean(result,result);
            return vo;
        }
    };
}
