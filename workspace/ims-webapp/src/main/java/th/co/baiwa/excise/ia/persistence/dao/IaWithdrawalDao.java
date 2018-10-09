package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaWithdrawalDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String CHECK = "เช็ค";
	private final String CHECK_KTB = "KTB-Corporate";
	private final String SQL = "SELECT TO_CHAR(ps.PAYMENT_DATE,'YYYYMMDD')PAYMENT_DATE , " + 
							"        ps.WITHDRAWAL_ID WITHDRAWAL_ID, " + 
							"        ps.REF_PAYMENT REF_PAYMENT, " + 
							"        ps.PAYEE PAYEE, " + 
							"        LS.ITEM_DESC ITEM_DESC,"+
							"        ps.OFFICE_CODE OFFICE_CODE, " + 
							"        LS.BUDGET_TYPE BUDGET_TYPE" + 
							"        FROM  " + 
							"   		(SELECT TO_CHAR(ps.PAYMENT_DATE,'YYYYMMDD')PAYMENT_DATE , " + 
							"            ps.WITHDRAWAL_ID WITHDRAWAL_ID, " + 
							"            ps.REF_PAYMENT REF_PAYMENT, "+ 
							"            ps.OFFICE_CODE," + 
							"            ps.PAYEE PAYEE " + 
							"   FROM IA_WITHDRAWAL_PERSONS ps ) PS  " + 
							"   INNER JOIN  " + 
							"   (SELECT WITHDRAWAL_ID,ITEM_DESC,BUDGET_TYPE " + 
							"        FROM IA_WITHDRAWAL_LIST " + 
							"        WHERE WITHDRAWAL_ID= (SELECT WITHDRAWAL_ID FROM IA_WITHDRAWAL_PERSONS WHERE PAYMENT_METHOD = ? GROUP BY WITHDRAWAL_ID  " + 
							"    )) LS " + 
							"    ON  LS.WITHDRAWAL_ID=ps.WITHDRAWAL_ID WHERE 1=1";	
	public Long count(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK);
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ?");
			params.add(formVo.getOfficeCode()+"%");
		}
        if (StringUtils.isNotBlank(formVo.getDateFrom()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND PAYMENT_DATE BETWEEN ? AND ?");
            params.add(formVo.getDateFrom());
            params.add(formVo.getDateTo());
        }
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int065Vo> findAll(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK);
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ?");
			params.add(formVo.getOfficeCode()+"%");
		}
        if (StringUtils.isNotBlank(formVo.getDateFrom()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND PAYMENT_DATE BETWEEN ? AND ?");
            params.add(formVo.getDateFrom());
            params.add(formVo.getDateTo());
        }
         String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
		List<Int065Vo> list = jdbcTemplate.query(limit, params.toArray(), stamRowmapper);
		return list;

	}
	
	public Long countKtb(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK_KTB);
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ?");
			params.add(formVo.getOfficeCode()+"%");
		}
        if (StringUtils.isNotBlank(formVo.getDateFrom()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND PAYMENT_DATE BETWEEN ? AND ?");
            params.add(formVo.getDateFrom());
            params.add(formVo.getDateTo());
        }
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int065Vo> findAllKtb(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK_KTB);
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ?");
			params.add(formVo.getOfficeCode()+"%");
		}
        if (StringUtils.isNotBlank(formVo.getDateFrom()) && StringUtils.isNotBlank(formVo.getDateTo())){
            sql.append(" AND PAYMENT_DATE BETWEEN ? AND ?");
            params.add(formVo.getDateFrom());
            params.add(formVo.getDateTo());
        }
         String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
		List<Int065Vo> list = jdbcTemplate.query(limit, params.toArray(), stamRowmapper);
		return list;

	}

	private RowMapper<Int065Vo> stamRowmapper = new RowMapper<Int065Vo>() {
		@Override
		public Int065Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int065Vo vo = new Int065Vo();
			vo.setPaymentDate(rs.getString("PAYMENT_DATE"));
			vo.setRefPayment(rs.getString("REF_PAYMENT"));
			vo.setPayee(rs.getString("PAYEE"));
			vo.setItemDesc(rs.getString("ITEM_DESC"));
			vo.setBudgetType(rs.getString("BUDGET_TYPE"));
			
			return vo;
		}
	};
}
