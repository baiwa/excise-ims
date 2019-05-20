package th.go.excise.ims.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

public class ExcisePersonRepositoryImpl implements ExcisePersonRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public List<ExcisePersonVoSelect> findByName(String officeCode, String name) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND ED_OFFCODE = ?  ");
		sql.append(" AND IS_DELETED = 'N' ");
		params.add("%" + name + "%");
		params.add(officeCode);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);

	}
	
	@Override
	public List<ExcisePersonVoSelect> findByOfficeCode(String officeCode,String subDeptCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_OFFCODE = ?  AND AU_SUBDEPT_CODE = ? ");
		sql.append(" AND IS_DELETED = 'N' ");
		params.add(officeCode);
		params.add(subDeptCode);
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);

	}

	private static final RowMapper<ExcisePersonVoSelect> edPersonRowMapper = new RowMapper<ExcisePersonVoSelect>() {
		@Override
		public ExcisePersonVoSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonVoSelect person = new ExcisePersonVoSelect();
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			person.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			person.setEdOffcode(rs.getString("ED_OFFCODE"));
			person.setName(rs.getString("ED_PERSON_NAME"));
			person.setValue(rs.getString("ED_LOGIN"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			return person;
		}
	};

	@Override
	public List<ExcisePersonVoSelect> findByEdLogin(String edLogin) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND IS_DELETED = 'N' ");
		params.add(" IN (" + edLogin + ")");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);
	}

	@Override
	public List<ExcisePersonVoSelect> findAllForAssign(DataTableRequest quest) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT  PERSON.*,DPT.OFF_NAME ,SUBDPT.SUBDEPT_NAME ,SUBDPT.SUBDEPT_CODE FROM EXCISE_PERSON PERSON  ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT DPT ON PERSON.ED_OFFCODE = DPT.OFF_CODE  ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT SUBDPT ON PERSON.AU_SUBDEPT_CODE = SUBDPT.SUBDEPT_CODE  ");
		sql.append(" WHERE PERSON.IS_DELETED = 'N' ");

		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), quest.getStart(), quest.getLength()), edPersonRowMapperAssing);
	}
	
	private static final RowMapper<ExcisePersonVoSelect> edPersonRowMapperAssing = new RowMapper<ExcisePersonVoSelect>() {
		@Override
		public ExcisePersonVoSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonVoSelect person = new ExcisePersonVoSelect();
			person.setEdPersonSeq(rs.getLong("ED_PERSON_SEQ"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			person.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			person.setEdOffcode(rs.getString("ED_OFFCODE"));
			person.setName(rs.getString("ED_PERSON_NAME"));
			person.setValue(rs.getString("ED_LOGIN"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setAuSubdeptCode(rs.getString("AU_SUBDEPT_CODE"));
			person.setSubDeptName(rs.getString("SUBDEPT_NAME"));
			person.setLevel(rs.getString("AU_SUBDEPT_LEVEL"));
			person.setOfficeName(rs.getString("OFF_NAME"));
			return person;
		}
	};

	@Override
	public Integer countAllForAssign(DataTableRequest quest) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT   PERSON.* FROM EXCISE_PERSON PERSON  ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT DPT ON PERSON.ED_OFFCODE = DPT.OFF_CODE  ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT SUBDPT ON PERSON.AU_SUBDEPT_CODE = SUBDPT.SUBDEPT_CODE  ");
		sql.append(" WHERE PERSON.IS_DELETED = 'N' ");
		
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), Integer.class);
	}
	


}
