package th.co.baiwa.buckwaframework.admin.persistence.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.admin.persistence.entity.Role;
import th.co.baiwa.buckwaframework.admin.persistence.mapper.RoleRowMapper;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;

@Repository("roleDao")
public class RoleDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<Role> findAll() {
		logger.debug("findAll");
		
		String sql = SqlGeneratorUtils.genSqlSelect("adm_role",
			Arrays.asList(
				"role_id",
				"role_code",
				"role_desc"
			),
			Arrays.asList(
				"is_deleted"
			)
		);
		
		return commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			RoleRowMapper.getInstance()
		);
	}
	
	public Role findById(Long roleId) {
		logger.info("findById roleId={}", roleId);
		
		String sql = SqlGeneratorUtils.genSqlSelect("adm_role",
			Arrays.asList(
				"role_id",
				"role_code",
				"role_desc"
			),
			Arrays.asList(
				"is_deleted",
				"role_id"
			)
		);
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				roleId
			},
			RoleRowMapper.getInstance()
		);
	}
	
	public int count() {
		logger.info("count");
		
		String sql = SqlGeneratorUtils.genSqlCount("adm_role",
			Arrays.asList(
				"is_deleted"
			)
		);
		
		return commonJdbcDao.executeQueryForObject(sql, Integer.class);
	}

	public Long insert(Role role) {
		logger.info("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("adm_role", Arrays.asList(
			"role_code",
			"role_desc",
			"created_by",
			"created_date"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			role.getRoleCode(),
			role.getRoleDesc(),
			"SYSTEM", // FIXME Will get username from SecurityContext
			new Date()
		});
		
		return key;
	}

	public int update(Role role) {
		logger.info("update");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_role",
			Arrays.asList(
				"role_code",
				"role_desc",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"role_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			role.getRoleCode(),
			role.getRoleDesc(),
			"SYSTEM", // FIXME Will get username from SecurityContext
			new Date(),
			role.getRoleId()
		});
		
		return updateRow;
	}

	public int delete(Long roleId) {
		logger.info("delete");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_role",
			Arrays.asList(
				"is_deleted",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"role_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			"SYSTEM", // FIXME Will get username from SecurityContext
			new Date(),
			roleId
		});
		
		return updateRow;
	}
	
}
