package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import oracle.net.aso.d;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.vo.Int062CwpDtlVo;

@Repository
public class CwpScwdDtlDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*-- findByHDRId --*/
	
	public List<CwpScwdDtl> findByHDRId(long idFile1) {
		logger.info("findByHDRId idFile1: {}", idFile1);
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT C.BUDGET_CODE FROM IA_CWP_SCWD_DTL C WHERE C.CWP_SCWD_HDR_ID = ? ");
		valueList.add(idFile1);

		List<CwpScwdDtl> CwpScwdDtl = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFindByHDRId);
		return CwpScwdDtl;
	}

	private RowMapper<CwpScwdDtl> fieldMappingFindByHDRId = new RowMapper<CwpScwdDtl>() {
		@Override
		public CwpScwdDtl mapRow(ResultSet rs, int arg1) throws SQLException {
			CwpScwdDtl dtlVo = new CwpScwdDtl();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			return dtlVo;
		}
	};
	
	/*-- findDivideMonth --*/
	
	public List<Int062CwpDtlVo> findDivideMonth(String budgetCode) {
		logger.info("findDevideMonth budgetCode: {}", budgetCode);
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT  C.BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') RECORD_DATE ");
		sql.append(" FROM IA_CWP_SCWD_DTL C ");
		sql.append(" WHERE C.BUDGET_CODE = ? ");
		sql.append(" GROUP BY BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') ");
		sql.append(" ORDER BY C.BUDGET_CODE ,to_char(C.RECORD_DATE , 'yyyy/mm') ");
		valueList.add(budgetCode);
		
		List<Int062CwpDtlVo> Int062CwpDtlVo = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingDivideMonth);
		return Int062CwpDtlVo;
	}
	
	private RowMapper<Int062CwpDtlVo> fieldMappingDivideMonth = new RowMapper<Int062CwpDtlVo>() {
		@Override
		public Int062CwpDtlVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int062CwpDtlVo dtlVo = new Int062CwpDtlVo();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			dtlVo.setCalMonth(rs.getString("RECORD_DATE"));
			return dtlVo;
		}
	};
	
	/*-- findGroupMonth --*/
	
	public List<CwpScwdDtl> findGroupMonth(String budgetCode, String calMonth) {
		logger.info("findGroupMonth budgetCode: {} | calMonth: {}", budgetCode, calMonth);
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_CWP_SCWD_DTL C ");
		sql.append(" WHERE C.BUDGET_CODE = ? ");
		sql.append(" AND to_char(C.RECORD_DATE , 'yyyy/mm') = ? ");
		valueList.add(budgetCode);
		valueList.add(calMonth);
		
		List<CwpScwdDtl> CwpScwdDtl = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFindGroupMonth);
		return CwpScwdDtl;
	}
	
	private RowMapper<CwpScwdDtl> fieldMappingFindGroupMonth = new RowMapper<CwpScwdDtl>() {
		@Override
		public CwpScwdDtl mapRow(ResultSet rs, int arg1) throws SQLException {
			CwpScwdDtl dtlVo = new CwpScwdDtl();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			dtlVo.setBankAccount(rs.getString("BANK_ACCOUNT"));
			dtlVo.setCwpScwdHdrId(rs.getLong("CWP_SCWD_HDR_ID"));
			dtlVo.setCwpScwdDtlId(rs.getInt("CWP_SCWD_DTL_ID"));
			dtlVo.setDucumentNumber(rs.getString("DUCUMENT_NUMBER"));
			dtlVo.setFee(rs.getBigDecimal("FEE"));
			dtlVo.setFines(rs.getBigDecimal("FINES"));
			dtlVo.setNetAmount(rs.getBigDecimal("NET_AMOUNT"));
			dtlVo.setPostDate(rs.getDate("POST_DATE"));
			dtlVo.setRecordDate(rs.getDate("RECORD_DATE"));
			dtlVo.setReferenceNo(rs.getString("REFERENCE_NO"));
			dtlVo.setSeller(rs.getString("SELLER"));
			dtlVo.setTypeCode(rs.getString("TYPE_CODE"));
			dtlVo.setWithdrawAmount(rs.getBigDecimal("WITHDRAW_AMOUNT"));
			dtlVo.setWithholdingTax(rs.getBigDecimal("WITHHOLDING_TAX"));
			
			return dtlVo;
		}
	};

}
