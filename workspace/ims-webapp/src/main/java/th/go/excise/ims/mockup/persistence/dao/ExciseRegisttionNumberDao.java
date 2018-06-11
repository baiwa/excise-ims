package th.go.excise.ims.mockup.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.mockup.persistence.entity.ExciseRegistartionNumber;
import th.go.excise.ims.mockup.utils.OracleUtils;

@Repository
public class ExciseRegisttionNumberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String sqlTaExciseId = " select *  from EXCISEADM.ta_excise_registtion_number ";
	
	public List<ExciseRegistartionNumber> queryByExciseId(String register, String exciseProductType,int start,int length) {	
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		sql.append(" where 1=1 ");
		if(exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = '"+exciseProductType.trim()+"' ");
		}
		
		sql.append(" order By TA_EXCISE_REGISTTION_NUMBER_ID ");
		List<ExciseRegistartionNumber> list = jdbcTemplate.query(OracleUtils.limitForDataTable(sql.toString(), start, length), exciseRegisttionRowmapper);
		
		return list;
	}
	public long queryCountByExciseId(String exciseProductType) {	
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		sql.append(" where 1=1 ");
		if(exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = '"+exciseProductType.trim()+"' ");
		}
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), Long.class);
		
		return count;
	}
	private RowMapper<ExciseRegistartionNumber> exciseRegisttionRowmapper = new RowMapper<ExciseRegistartionNumber>() {
		@Override
		public ExciseRegistartionNumber mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseRegistartionNumber vo = new ExciseRegistartionNumber();
			
			vo.setExciseRegisttionNumberId(rs.getInt("TA_EXCISE_REGISTTION_NUMBER_ID"));
			vo.setExciseId(rs.getString("TA_EXCISE_ID"));
			vo.setExciseOperatorName(rs.getString("TA_EXCISE_OPERATOR_NAME"));
			vo.setExciseIdenNumber(rs.getString("TA_EXCISE_IDEN_NUMBER"));
			vo.setExciseFacName(rs.getString("TA_EXCISE_FAC_NAME"));
			vo.setIndustrialAddress(rs.getString("TA_EXCISE_FAC_ADDRESS"));
			vo.setExciseArea(rs.getString("TA_EXCISE_AREA"));
			vo.setExciseRegisCapital(rs.getInt("TA_EXCISE_REGIS_CAPITAL"));
			vo.setExciseRemark(rs.getString("TA_EXCISE_REMARK"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME") != null ? rs.getTimestamp("CREATED_DATETIME").toLocalDateTime() : null);
			vo.setUpdateBy(rs.getString("UPDATE_BY"));
			vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME") != null ? rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime() : null);
			vo.setTaexciseProductType(rs.getString("TA_EXCISE_PRODUCT_TYPE"));
			vo.setTaexciseSectorArea(rs.getString("TA_EXCISE_SECTOR_AREA"));
			return vo;

		}

	};
	
	public List<ExciseRegistartionNumber> queryByExciseRegistionNumber(String exciseProductType) {	
		StringBuilder sql = new StringBuilder(sqlTaExciseId);
		sql.append(" where 1=1 ");
		if(exciseProductType != null && exciseProductType.length() > 0) {
			sql.append(" and  TA_EXCISE_PRODUCT_TYPE = '"+exciseProductType.trim()+"' ");
		}
		List<ExciseRegistartionNumber> list = jdbcTemplate.query(sql.toString(), exciseRegisttionRowmapper);
		
		return list;
	}

}
