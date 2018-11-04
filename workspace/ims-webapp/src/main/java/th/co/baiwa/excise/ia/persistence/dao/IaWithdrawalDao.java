package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.ia.persistence.vo.Int0610FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int111Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaWithdrawalDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String SQL_SEARCH_CRITERIA = "SELECT * FROM IA_WITHDRAWAL_LIST WHERE IS_DELETED='N'";
	private final String CHECK = "เช็ค";
	private final String CHECK_KTB = "KTB-Corporate";
	private final String SQL = "SELECT ps.PAYMENT_DATE PAYMENT_DATE , " + 
							"        ps.WITHDRAWAL_ID WITHDRAWAL_ID, " + 
							"        ps.REF_PAYMENT REF_PAYMENT, " + 
							"        ps.PAYEE PAYEE, " + 
							"        ps.BANK_NAME BANK_NAME, " + 
							"        LS.ITEM_DESC ITEM_DESC,"+
							"        ps.OFFICE_CODE OFFICE_CODE, " + 
							"        LS.BUDGET_TYPE BUDGET_TYPE,  "+ 
							"        ps.AMOUNT  AMOUNT" + 
							"        FROM  " + 
							"   		(SELECT TO_CHAR(ps.PAYMENT_DATE,'YYYYMMDD')PAYMENT_DATE , " + 
							"            ps.WITHDRAWAL_ID WITHDRAWAL_ID, " + 
							"            ps.REF_PAYMENT REF_PAYMENT, "+ 
							"            ps.OFFICE_CODE," + 
							"            ps.BANK_NAME BANK_NAME, " + 
							"            ps.PAYEE PAYEE, "+
							"            ps.AMOUNT AMOUNT" + 
							"   FROM IA_WITHDRAWAL_PERSONS ps WHERE PAYMENT_METHOD = ? ) PS  " +
							"   LEFT JOIN  " +
							"   (SELECT WITHDRAWAL_ID,ITEM_DESC,BUDGET_TYPE " + 
							"        FROM IA_WITHDRAWAL_LIST " + 
							"        WHERE WITHDRAWAL_ID in (SELECT WITHDRAWAL_ID FROM IA_WITHDRAWAL_PERSONS WHERE PAYMENT_METHOD = ? GROUP BY WITHDRAWAL_ID  " +
							"    )) LS " + 
							"    ON  LS.WITHDRAWAL_ID=ps.WITHDRAWAL_ID WHERE 1=1";	
	public Long count(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK);
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
        if (StringUtils.isNotBlank(formVo.getBudgetType())){
        	sql.append(" AND BUDGET_TYPE = ?");
        	params.add(formVo.getBudgetType());
		}
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int065Vo> findAll(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK);
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
		if (StringUtils.isNotBlank(formVo.getBudgetType())){
			sql.append(" AND BUDGET_TYPE = ?");
			params.add(formVo.getBudgetType());
		}
         String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
		List<Int065Vo> list = jdbcTemplate.query(limit, params.toArray(), stamRowmapper);
		return list;

	}
	
	public Long countKtb(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK_KTB);
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
		if (StringUtils.isNotBlank(formVo.getBudgetType())){
			sql.append(" AND BUDGET_TYPE = ?");
			params.add(formVo.getBudgetType());
		}
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Int065Vo> findAllKtb(Int065FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK_KTB);
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
		if (StringUtils.isNotBlank(formVo.getBudgetType())){
			sql.append(" AND BUDGET_TYPE = ?");
			params.add(formVo.getBudgetType());
		}
         String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
		List<Int065Vo> list = jdbcTemplate.query(limit, params.toArray(), stamRowmapper);
		return list;

	}

	private RowMapper<Int065Vo> stamRowmapper = new RowMapper<Int065Vo>() {
		@Override
		public Int065Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int065Vo vo = new Int065Vo();
			Date date = DateConstant.convertStrToDate(rs.getString("PAYMENT_DATE"), ExciseConstants.FORMAT_DATE.YYYYMMDD,ExciseConstants.LOCALE.EN);
			String dateStr = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.DDMMYYYY,ExciseConstants.LOCALE.TH);
			vo.setPaymentDate(dateStr);
			vo.setRefPayment(rs.getString("REF_PAYMENT"));
			vo.setPayee(rs.getString("PAYEE"));
			vo.setBankName(rs.getString("BANK_NAME"));
			vo.setItemDesc(rs.getString("ITEM_DESC"));
			vo.setBudgetType(rs.getString("BUDGET_TYPE"));
			vo.setAmount(rs.getString("AMOUNT"));
			return vo;
		}
	};
	
	public Long counwith(Int0610FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_SEARCH_CRITERIA);
		List<Object> param = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ?");
			param.add(formVo.getOfficeCode()+"%");
		}
		if (StringUtils.isNotBlank(formVo.getBudge())) {
    		sql.append(" AND BUDGE = ? ");
    		param.add(formVo.getBudge());
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getActivity())) {
    		sql.append(" AND ACTIVITY = ? ");
    		param.add(formVo.getActivity());
    	}
    	 if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())){
             sql.append(" AND TO_CHAR(WITHDRAWAL_DATE,'YYYYMMDD') BETWEEN ? AND ?");
             param.add(formVo.getDateForm());
             param.add(formVo.getDateTo());
         }
    	 
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, param.toArray(), Long.class);
		return count;
	}

	 public List<Int0610Vo> findAll(Int0610FormVo formVo) {
	    	StringBuilder sql = new StringBuilder(SQL_SEARCH_CRITERIA);
	    	List<Object> param = new ArrayList<>();
	    	
	    	if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
				sql.append(" AND OFFICE_CODE LIKE ?");
				param.add(formVo.getOfficeCode()+"%");
			}
	    	if (StringUtils.isNotBlank(formVo.getBudge())) {
	    		sql.append(" AND BUDGE = ? ");
	    		param.add(formVo.getBudge());
	    	}
	    	
	    	if (StringUtils.isNotBlank(formVo.getActivity())) {
	    		sql.append(" AND ACTIVITY = ? ");
	    		param.add(formVo.getActivity());
	    	}
	    	 if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())){
	             sql.append(" AND TO_CHAR(WITHDRAWAL_DATE,'YYYYMMDD') BETWEEN ? AND ?");
	             param.add(formVo.getDateForm());
	             param.add(formVo.getDateTo());
	         }
	    	
	    	List<Int0610Vo> list = jdbcTemplate.query(sql.toString(), param.toArray(), iaWithdrawalRowmapper);
	    	return list;
	    }
	 
	 private RowMapper<Int0610Vo> iaWithdrawalRowmapper = new RowMapper<Int0610Vo>() {
			@Override
			public Int0610Vo mapRow(ResultSet rs, int arg1) throws SQLException {
				Int0610Vo vo = new Int0610Vo();
				Date date = rs.getDate("WITHDRAWAL_DATE");
				String dateStr = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.DDMMYYYY,ExciseConstants.LOCALE.TH);
				vo.setWithdrawaldate(dateStr);
				vo.setWithdrawalid(rs.getString("WITHDRAWAL_ID"));
				vo.setRefnum(rs.getString("REF_NUM"));
				vo.setActivities(rs.getString("ACTIVITIES"));
				vo.setBudgettype(rs.getString("BUDGET_TYPE"));
				vo.setWithdrawalamount(rs.getString("WITHDRAWAL_AMOUNT"));
				vo.setSocialsecurity(rs.getString("SOCIAL_SECURITY"));
				vo.setWithholdingtax(rs.getString("WITHHOLDING_TAX"));
				vo.setReceivedamount(rs.getString("RECEIVED_AMOUNT"));
				vo.setAnotheramount(rs.getString("ANOTHER_AMOUNT"));
				vo.setPaymentdocnum(rs.getString("PAYMENT_DOC_NUM"));
				vo.setWithdrawaldocnum(rs.getString("WITHDRAWAL_DOC_NUM"));
				vo.setItemdesc(rs.getString("ITEM_DESC"));
				vo.setNote(rs.getString("NOTE"));
				vo.setBudgetname(rs.getString("BUDGET_NAME"));
				vo.setCategoryname(rs.getString("CATEGORY_NAME"));
				vo.setListname(rs.getString("LIST_NAME"));
				return vo;
			}
		};
	public List<Int065Vo> exportFile(Int065FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		params.add(CHECK_KTB);
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
		if (StringUtils.isNotBlank(formVo.getBudgetType())){
			sql.append(" AND BUDGET_TYPE = ?");
			params.add(formVo.getBudgetType());
		}
  
		List<Int065Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), stamRowmapper);
		return list;
	}
}
