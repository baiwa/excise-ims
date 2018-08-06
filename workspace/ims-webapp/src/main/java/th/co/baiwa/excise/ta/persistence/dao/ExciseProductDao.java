package th.co.baiwa.excise.ta.persistence.dao;

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

import th.co.baiwa.excise.ta.persistence.entity.ExciseFileUpload;

@Repository
public class ExciseProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(ExciseProductDao.class);

	private final String sql = " SELECT * FROM TA_EXCISE_PRODUCT ";

	public List<ExciseFileUpload> queryById(String register) {
		logger.info("register: {}", register);
		List<ExciseFileUpload> list = jdbcTemplate.query(sql, new Object[] { register }, rowMapper);
		return list;
	}
	
	public void insert(ExciseFileUpload value) {
		// Initial SQL for insert to database
		StringBuilder sql = new StringBuilder(" INSERT INTO TA_EXCISE_FILE_UPLOAD (TA_EXCISE_FILE_UPLOAD_ID," + 
				"EXCISE_ID," + 
				"CREATE_DATE," + 
				"UPLOAD_PATH," + 
				"CREATED_BY," + 
				"CREATED_DATE," + 
				"UPDATED_BY," + 
				"UPDATED_DATE)");
		sql.append(" values (TA_EXCISE_FILE_UPLOAD_SEQ.nextval,?,?,?,?,?,?,?) ");
		// For set Object
		jdbcTemplate.update(sql.toString(), toArrayObject(value) );
	}
	
	private Object[] toArrayObject(ExciseFileUpload value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getExciseId());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUploadPath());
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUpdatedBy());
			valueList.add(value.getUpdatedDate());
		}
		return valueList.toArray();
	}
	
	private RowMapper<ExciseFileUpload> rowMapper = new RowMapper<ExciseFileUpload>() {

		@Override
		public ExciseFileUpload mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseFileUpload vo = new ExciseFileUpload();
			vo.setTaExciseFileUploadId(rs.getBigDecimal("TA_EXCISE_FILE_UPLOAD_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			
			return vo;

		}

	};
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

