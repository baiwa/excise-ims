package th.co.baiwa.excise.cop.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.cop.persistence.vo.Cop061FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop061Vo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064Vo;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711Vo;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ReportCheckOperationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_TA_EXCISE_REGISTTION_NUMBER = " SELECT * FROM TA_EXCISE_REGISTTION_NUMBER WHERE 1=1 ";
	
	private final String SQL_COP_CHECK_FISCAL_YEAR_DTL = " SELECT a.ENTREPRENEUR_NO FROM COP_CHECK_FISCAL_YEAR_DTL a " + 
		  												 " LEFT JOIN COP_CHECK_FISCAL_YEAR b ON a.ID_MASTER = b.ID " + 
		  												 " WHERE a.IS_DELETED = 'N' AND a.STATUS != '1874' AND SUBSTR(b.FISCAL_YEAR,4,4) = ? ";
	
	private final String SQL_DETAIL = " SELECT * FROM OA_TAX_REDUCE_WS_DTL_MONEY WHERE 1=1 ";

	public List<String> findExciseIdAll(String fiscalYear) {
		
		List<String> list = jdbcTemplate.query(SQL_COP_CHECK_FISCAL_YEAR_DTL, new Object[] { fiscalYear }, exciseIdRowmapper);
		return list;
	}

	private RowMapper<String> exciseIdRowmapper = new RowMapper<String>() {
		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("ENTREPRENEUR_NO");
		}
	};

	public List<Cop061FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder(SQL_TA_EXCISE_REGISTTION_NUMBER);
		sql.append("AND  TA_EXCISE_ID = ? ");
		List<Cop061FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Cop061FormVo> createPeperRowmapper = new RowMapper<Cop061FormVo>() {
		@Override
		public Cop061FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop061FormVo vo = new Cop061FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("TA_EXCISE_REGISTTION_NUMBER_ID"));
			
			vo.setExciseId(rs.getString("TA_EXCISE_ID"));
			vo.setExciseName(rs.getString("TA_EXCISE_FAC_NAME"));
			vo.setExciseAddress(rs.getString("TA_EXCISE_FAC_ADDRESS"));
			
			String type = rs.getString("TA_EXCISE_ID").split("-")[1];
			String exciseType = "";
			if("1".equals(type)) {
				exciseType = "สินค้า";
			}else if("2".equals(type)) {
				exciseType = "บริการ";
			}else if("3".equals(type)) {
				exciseType = "สินค้านำเข้า";
			}
			vo.setExciseType(exciseType);
			vo.setProductType(rs.getString("TA_EXCISE_PRODUCT_TYPE"));
			
			return vo;
		}
	};

	public Long count(Cop061FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Cop061Vo> findAll(Cop061FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY LIST DESC ");
		
		List<Cop061Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Cop061Vo> detailRowmapper = new RowMapper<Cop061Vo>() {
		@Override
		public Cop061Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop061Vo vo = new Cop061Vo();

			vo.setTaExciseAcc0502DtlId(rs.getString("ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaExciseAcc0502DtlList(rs.getString("LIST"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setTaxPerAmount(rs.getBigDecimal("TAX_PER_AMOUNT"));

			return vo;
		}
	};
	
	
	public Long count064(Cop064FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}
	
	public List<Cop064Vo> findAll064(Cop064FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY LIST DESC ");
		
		List<Cop064Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detail064Rowmapper);
		return list;

	}

	private RowMapper<Cop064Vo> detail064Rowmapper = new RowMapper<Cop064Vo>() {
		@Override
		public Cop064Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop064Vo vo = new Cop064Vo();

			vo.setTaExciseAcc0502DtlId(rs.getString("ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaExciseAcc0502DtlList(rs.getString("LIST"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setTaxPerAmount(rs.getBigDecimal("TAX_PER_AMOUNT"));

			return vo;
		}
	};
    public Long saveHeadCop064 (Cop064FormVo vo) {
    	Long id = jdbcTemplate.queryForObject(" SELECT OA_TAX_REDUCE_WS_HDR_SEQ.NEXTVAL FROM dual ",Long.class);

    	jdbcTemplate.update(" INSERT INTO OA_TAX_REDUCE_WS_HDR( " + 
    			"TAX_REDUCE_WS_HDR_ID, " + 
    			"EXCISE_ID, " + 
    			"EXCISE_NAME, " + 
    			"PRODUCT_TYPE, " + 
    			"START_DATE, " + 
    			"END_DATE, " + 
    			"IS_DELETED, " + 
    			"VERSION " + 
    			" ) VALUES ( " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"? " + 
    			" ) ",new Object[] {
    					id,
    					vo.getExciseId(),
    					vo.getExciseName(),
    					vo.getProductType(),
    					vo.getDateFrom(),
    					vo.getDateTo(),
    					"N",
    					1});
    	
    	return id;
}
	
    public Long saveHeadCop0611 (Ope041DataTable vo) {
    	Long id = jdbcTemplate.queryForObject(" SELECT OA_DISB_RMAT_WS_HDR_SEQ.NEXTVAL FROM dual ",Long.class);

    	jdbcTemplate.update(" INSERT INTO OA_DISB_RMAT_WS_HDR( " + 
    			"OA_DISB_RMAT_WS_HDR_ID, " + 
    			"EXCISE_ID, " +
    			"YEAR, " +
    			"START_DATE, " + 
    			"END_DATE " + 
    			" ) VALUES ( " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"? " + 
    			" ) ",new Object[] {
    					id,
    					vo.getExciseId(),
    					vo.getFiscalYear(),
    					vo.getStartDate(),
    					vo.getEndDate()
    					});
    	
    	return id;
    }
    public Long saveDtlCop0611 (Ope041DataTable vo,Long idHead) {
    	Long id = jdbcTemplate.queryForObject(" SELECT OA_DISB_RMAT_WS_DTL_SEQ.NEXTVAL FROM dual ",Long.class);

    	jdbcTemplate.update(" INSERT INTO OA_DISB_RMAT_WS_DTL( " + 
    			"OA_DISB_RMAT_WS_DTL_ID, " + 
    			"OA_DISB_RMAT_WS_HDR_ID, " + 
    			"DISBURSE_RAW_MAT_DTL_NO, " + 
    			"DISBURSE_RAW_MAT_DTL_ORDE, " + 
    			"RAW_MAT_REQUISITION, " + 
    			"DAY_BOOK_07_01, " + 
    			"MONTH_BOOK_07_04, " + 
    			"MONTHLY_REPORT, " + 
    			"MAX_VALUES, " + 
    			"RESULT " + 
    			") VALUES( " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"?, " + 
    			"? " + 
    			" ) ",new Object[] {
    					id,
    					idHead,
    					vo.getNo(),
    				    vo.getProduct(),
    					vo.getTaxInvoice(),
    					vo.getDayRecieve(),
    					vo.getMonthRecieve(),
    					vo.getExd1(),
    					vo.getCalMax(),
    					vo.getDiff()
    					});
    	
    	return id;
    }

}
