package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;

@Repository
public class Int020301JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int020301HeaderVo> findHeaderByIdSide(BigDecimal idSide, String budgetYear) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT IQS.SIDE_NAME FROM IA_QUESTIONNAIRE_HDR IQH ");
		sqlBuilder.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE IQS ");
		sqlBuilder.append(" ON IQH.ID = IQS.ID_HEAD WHERE 1=1 ");
		sqlBuilder.append(" AND IQH.ID = ? ");
		sqlBuilder.append(" AND IQH.BUDGET_YEAR = ? ");
		List<Object> params = new ArrayList<>();
		params.add(idSide);
		params.add(budgetYear);
		List<Int020301HeaderVo> data = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), headerRowMapper);
		return data;
	}
	
	private RowMapper<Int020301HeaderVo> headerRowMapper = new RowMapper<Int020301HeaderVo>() {
		@Override
		public Int020301HeaderVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020301HeaderVo vo = new Int020301HeaderVo();
			vo.setName(rs.getString("SIDE_NAME"));
			return vo;
		}
	};
	
	public List<Int020301InfoVo> findInfoByIdSide(BigDecimal idSide, String budgetYear) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select distinct qme.OFFICE_CODE as OFFICE_CODE, qme.UPDATED_DATE as UPDATED_DATE, qme.STATUS as STATUS from IA_QUESTIONNAIRE_HDR qhr ");
		sqlBuilder.append(" inner join IA_QUESTIONNAIRE_SIDE qse on qse.ID_HEAD = qhr.ID ");
		sqlBuilder.append(" inner join IA_QUESTIONNAIRE_SIDE_DTL qdl on qdl.ID_SIDE = qse.ID ");
		sqlBuilder.append(" inner join IA_QUESTIONNAIRE_MADE qme on qme.ID_SIDE_DTL = qdl.ID ");
		sqlBuilder.append(" where qhr.BUDGET_YEAR = ? and qhr.ID = ? ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(idSide);
		List<Int020301InfoVo> data = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), infoRowMapper);
		return data;
	}
	
	private RowMapper<Int020301InfoVo> infoRowMapper = new RowMapper<Int020301InfoVo>() {
		@Override
		public Int020301InfoVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int020301InfoVo vo = new Int020301InfoVo();
			vo.setSectorName(rs.getString("OFFICE_CODE"));
			vo.setAreaName(rs.getString("OFFICE_CODE"));
			vo.setSentDate(rs.getDate("UPDATED_DATE"));
			vo.setStatusText(rs.getString("STATUS"));
			return vo;
		}
	};
	
}
