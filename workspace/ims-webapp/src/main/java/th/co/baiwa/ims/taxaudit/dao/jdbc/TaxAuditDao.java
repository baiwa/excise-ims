package th.co.baiwa.ims.taxaudit.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.ims.taxaudit.vo.TaxAuditVo;

@Repository
public class TaxAuditDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<TaxAuditVo> findAll() {
		StringBuilder sql = new StringBuilder(" select * from table_tax");
		List<TaxAuditVo> list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
	}
	
	private RowMapper<TaxAuditVo> rowMapper = new RowMapper<TaxAuditVo>() {
		@Override
		public TaxAuditVo mapRow(ResultSet rs, int arg1) throws SQLException {
			TaxAuditVo ta = new TaxAuditVo();
			return ta;
		}
	};
}
