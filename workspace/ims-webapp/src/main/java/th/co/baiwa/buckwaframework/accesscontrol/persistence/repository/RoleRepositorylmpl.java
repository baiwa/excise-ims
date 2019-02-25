package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

public class RoleRepositorylmpl {

	private static final Logger logger = LoggerFactory.getLogger(RoleRepositorylmpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public RoleRepositorylmpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private void buildSearchQuery(StringBuilder sql, List<Object> params, RoleFormVo request) {
		sql.append(" SELECT role_id, role_code, role_desc");
		sql.append(" FROM adm_role ");
		sql.append(" WHERE is_deleted = ? ");

		params.add(FLAG.N_FLAG);

		if (StringUtils.isNotBlank(request.getRoleCode())) {
			sql.append(" AND role_code LIKE ? ");
			params.add("%" + StringUtils.trim(request.getRoleCode()) + "%");
		}

		if (StringUtils.isNotBlank(request.getRoleDesc())) {
			sql.append(" AND role_desc LIKE ? ");
			params.add("%" + StringUtils.trim(request.getRoleDesc()) + "%");
		}

	}

	public Integer countByCriteria(RoleFormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		logger.info("count={}", count);

		return count;
	}

	public List<Role> findByCriteria(RoleFormVo request) {
		logger.debug("findByCriteria role.roleCode={}, role.roleDesc={}", request.getRoleCode(), request.getRoleDesc());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, request);

		sql.append(" ORDER BY role_id ");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		List<Role> datas = this.commonJdbcTemplate.query(limit, params.toArray(), new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getLong("role_id"));
				role.setRoleCode(rs.getString("role_code"));
				role.setRoleDesc(rs.getString("role_desc"));
				return role;
			}

		});

		logger.info("datas.size()={}", datas.size());

		return datas;
	}

}
