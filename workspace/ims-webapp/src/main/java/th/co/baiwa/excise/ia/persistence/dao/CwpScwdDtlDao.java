package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.dao.BatchSetter;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.vo.Int062AddFieldVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062CwpDtlVo;

@Repository
public class CwpScwdDtlDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;

	/*-- findByHDRId --*/
	
	public List<Int062CwpDtlVo> findByHDRId(long idFile1, Long sortSystemID) {
		logger.info("findByHDRId idFile1: {}", idFile1);
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT ");
		if(sortSystemID == 1322) {
			sql.append(" C.BUDGET_CODE BUDGET_CODE ");
		}
		if(sortSystemID == 1323) {
			sql.append(" to_char(C.RECORD_DATE , 'yyyy/mm') RECORD_DATE  ");
		}
		sql.append(" FROM IA_CWP_SCWD_DTL C ");
		sql.append(" WHERE C.CWP_SCWD_HDR_ID = ? ");
		if(sortSystemID == 1322) {
			sql.append(" ORDER BY C.BUDGET_CODE ");
		}
		if(sortSystemID == 1323) {
			sql.append(" ORDER BY TO_CHAR(C.RECORD_DATE , 'yyyy/mm') ");
		}
		valueList.add(idFile1);

		List<Int062CwpDtlVo> findByHDRId = jdbcTemplate.query(sql.toString(), valueList.toArray(), new RowMapper<Int062CwpDtlVo>() {
			@Override
			public Int062CwpDtlVo mapRow(ResultSet rs, int arg1) throws SQLException {
				Int062CwpDtlVo dtlVo = new Int062CwpDtlVo();
				if(sortSystemID == 1322) {
					dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
				}
				if(sortSystemID == 1323) {
					dtlVo.setCalMonth(rs.getString("RECORD_DATE"));
				}
				return dtlVo;
			}
		});
		return findByHDRId;
	}
	
	/*-- findDivideMonth --*/
	
	public List<Int062CwpDtlVo> findDivideMonth(Int062CwpDtlVo cwp, Long sortSystemID) {
		logger.info("findDevideMonth budgetCode: {} recdordDate: {}", cwp.getBudgetCode(), cwp.getRecordDate());
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT  C.BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') RECORD_DATE ");
		sql.append(" FROM IA_CWP_SCWD_DTL C ");
		sql.append(" WHERE C.BUDGET_CODE = ? ");
		valueList.add(cwp.getBudgetCode());
		
		sql.append(" GROUP BY BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') ");
		sql.append(" ORDER BY to_char(C.RECORD_DATE , 'yyyy/mm') ");
		
		
		List<Int062CwpDtlVo> findDivideMonth = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingDivideMonth);
		return findDivideMonth;
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
	
	public List<Int062AddFieldVo> findGroupMonth(Int062CwpDtlVo vo, Long sortSystemID) {
		logger.info("findGroupMonth budgetCode: {} | calMonth: {}", vo.getBudgetCode(), vo.getCalMonth());
		
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_CWP_SCWD_DTL C ");
		sql.append(" WHERE 1 = 1");
		if(sortSystemID == 1322){
			sql.append(" AND C.BUDGET_CODE = ? ");
			valueList.add(vo.getBudgetCode());
			sql.append(" AND to_char(C.RECORD_DATE , 'yyyy/mm') = ? ");
			valueList.add(vo.getCalMonth());
			sql.append(" ORDER BY C.BUDGET_CODE ");
		}
		if(sortSystemID == 1323){
			sql.append(" AND to_char(C.RECORD_DATE , 'yyyy/mm') = ? ");
			valueList.add(vo.getCalMonth());
			sql.append(" ORDER BY C.RECORD_DATE ");
		}
		
		List<Int062AddFieldVo> CwpScwdDtl = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFindGroupMonth);
		return CwpScwdDtl;
	}
	
	private RowMapper<Int062AddFieldVo> fieldMappingFindGroupMonth = new RowMapper<Int062AddFieldVo>() {
		@Override
		public Int062AddFieldVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int062AddFieldVo dtlVo = new Int062AddFieldVo();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			dtlVo.setBankAccount(rs.getString("BANK_ACCOUNT"));
			dtlVo.setCwpScwdHdrId(rs.getLong("CWP_SCWD_HDR_ID"));
			dtlVo.setCwpScwdDtlId(rs.getLong("CWP_SCWD_DTL_ID"));
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
			
			//change format date -> string
			String recordDate = DateConstant.convertDateToStrDDMMYYYY(dtlVo.getRecordDate());
			String postDate = DateConstant.convertDateToStrDDMMYYYY(dtlVo.getPostDate());
			dtlVo.setRecordDateStr(recordDate);
			dtlVo.setPostDateStr(postDate);
			
			return dtlVo;
		}
	};
	
	public List<CwpScwdDtl> queryBudget(CwpScwdDtl en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT D.BUDGET_CODE, D.CWP_SCWD_HDR_ID FROM IA_CWP_SCWD_DTL D ");
		sql.append(" WHERE D.CWP_SCWD_HDR_ID = ? ");
		sql.append(" ORDER BY D.BUDGET_CODE");
		valueList.add(en.getCwpScwdHdrId());

		List<CwpScwdDtl> budgetList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingQueryBudget);
		return budgetList;
	}

	private RowMapper<CwpScwdDtl> fieldMappingQueryBudget = new RowMapper<CwpScwdDtl>() {
		@Override
		public CwpScwdDtl mapRow(ResultSet rs, int arg1) throws SQLException {
			CwpScwdDtl dtlVo = new CwpScwdDtl();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			dtlVo.setCwpScwdHdrId(rs.getLong("CWP_SCWD_HDR_ID"));
			return dtlVo;
		}
	};
	
	public BigDecimal sumNetAmount(Long cwpScwdHdrId, String budgetCode) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT SUM(D.NET_AMOUNT) AS TOTAL_NETAMOUNT ");
		sql.append(" FROM IA_CWP_SCWD_DTL D ");
		sql.append(" WHERE D.CWP_SCWD_HDR_ID = ? ");
		sql.append(" AND D.BUDGET_CODE = ? ");
		valueList.add(cwpScwdHdrId);
		valueList.add(budgetCode);

		BigDecimal totalNetAmount = jdbcTemplate.queryForObject(sql.toString(), valueList.toArray(), BigDecimal.class);
		return totalNetAmount;
	}
	
	public int[][] cwpScwdDtlInsert(final List<CwpScwdDtl> detailList, int executeSize) throws SQLException {
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO IA_CWP_SCWD_DTL (CWP_SCWD_DTL_ID, CWP_SCWD_HDR_ID, RECORD_DATE,POST_DATE, TYPE_CODE, DUCUMENT_NUMBER, SELLER, BANK_ACCOUNT, REFERENCE_NO, BUDGET_CODE, WITHDRAW_AMOUNT, WITHHOLDING_TAX, FINES, FEE, NET_AMOUNT, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_DELETED, VERSION) ");
		sql.append(" values(IA_CWP_SCWD_DTL_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		
		return commonJdbcDao.executeBatch(sql.toString(), new BatchSetter<CwpScwdDtl>() {
			@Override
			public List<CwpScwdDtl> getBatchObjectList() {
				return detailList;
			}
			
			@Override
			public Object[] toObjects(CwpScwdDtl obj) {
				return new Object[] {
					obj.getCwpScwdHdrId(),
					obj.getRecordDate(),
					obj.getPostDate(),
					obj.getTypeCode(),
					obj.getDucumentNumber(),
					obj.getSeller(),
					obj.getBankAccount(),
					obj.getReferenceNo(),
					obj.getBudgetCode(),
					obj.getWithdrawAmount(),
					obj.getWithholdingTax(),
					obj.getFines(),
					obj.getFee(),
					obj.getNetAmount(),
					user,
					date,
					obj.getUpdatedBy(),
					obj.getUpdatedDate(),
					"N",
					1
				};
			}
			
			@Override
			public int getExecuteSize() {
				return executeSize;
			}
		});
	}

	public List<Int062AddFieldVo> addField() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT C.* , WITHDRAWAL_ID, W.LIST_NAME , W.WITHDRAWAL_DOC_NUM, W.WITHDRAWAL_AMOUNT, W.RECEIVED_AMOUNT, W.REF_NUM ");
			sql.append(" FROM IA_CWP_SCWD_DTL C ");
			sql.append(" INNER JOIN IA_WITHDRAWAL_LIST W ");
			sql.append(" ON C.DUCUMENT_NUMBER = W.WITHDRAWAL_DOC_NUM ");
			
		List<Int062AddFieldVo> addFieldList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFindAddField);
		return addFieldList;
	}
	
	private RowMapper<Int062AddFieldVo> fieldMappingFindAddField = new RowMapper<Int062AddFieldVo>() {
		@Override
		public Int062AddFieldVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int062AddFieldVo dtlVo = new Int062AddFieldVo();
			dtlVo.setBudgetCode(rs.getString("BUDGET_CODE"));
			dtlVo.setBankAccount(rs.getString("BANK_ACCOUNT"));
			dtlVo.setCwpScwdHdrId(rs.getLong("CWP_SCWD_HDR_ID"));
			dtlVo.setCwpScwdDtlId(rs.getLong("CWP_SCWD_DTL_ID"));
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
			
			//change format date -> string
			String recordDate = DateConstant.convertDateToStrDDMMYYYY(dtlVo.getRecordDate());
			String postDate = DateConstant.convertDateToStrDDMMYYYY(dtlVo.getPostDate());
			dtlVo.setRecordDateStr(recordDate);
			dtlVo.setPostDateStr(postDate);
			
			//--- add field table *IA_WITHDRAWAL_LIST* ---
			dtlVo.setWithdrawalId(rs.getLong("WITHDRAWAL_ID"));
			dtlVo.setWithdrawalDocNum(rs.getString("WITHDRAWAL_DOC_NUM"));
			dtlVo.setListName(rs.getString("LIST_NAME"));
			dtlVo.setWithdrawalAmount(rs.getBigDecimal("WITHDRAWAL_AMOUNT"));
			dtlVo.setReceivedAmount(rs.getBigDecimal("RECEIVED_AMOUNT"));
			dtlVo.setRefNum(rs.getString("REF_NUM"));
			
			return dtlVo;
		}
	};
	
	
	

}
