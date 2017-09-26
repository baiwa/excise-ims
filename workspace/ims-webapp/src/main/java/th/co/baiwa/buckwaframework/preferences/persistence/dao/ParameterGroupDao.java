package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysParameterGroup;
import th.co.baiwa.buckwaframework.preferences.persistence.mapper.ParameterGroupRowMapper;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Repository("parameterGroupDao")
public class ParameterGroupDao {

	private static final Logger logger = LoggerFactory.getLogger(ParameterGroupDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<SysParameterGroup> findAll() {
		logger.debug("findAll");
		
		String sql =
			" SELECT param_group_id, param_group_code, param_group_desc " +
			" FROM sys_parameter_group " +
			" WHERE is_deleted = ? " +
			" ORDER BY param_group_code ";
		
		return commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			ParameterGroupRowMapper.getInstance()
		);
	}
	
	public SysParameterGroup findById(Long paramGroupId) {
		logger.debug("findById");
		
		String sql =
			" SELECT param_group_id, param_group_code, param_group_desc " +
			" FROM sys_parameter_group " +
			" WHERE is_deleted = ? " +
			"   AND param_group_id = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				paramGroupId
			},
			ParameterGroupRowMapper.getInstance()
		);
	}
	
	public int count() {
		logger.info("count");
		
		String sql = SqlGeneratorUtils.genSqlCount("sys_parameter_group", Arrays.asList("is_deleted"));
		
		return commonJdbcDao.executeQueryForObject(sql, Integer.class);
	}
	
	public Long insert(SysParameterGroup paramGroup) {
		logger.debug("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("sys_parameter_group", Arrays.asList(
			"param_group_code",
			"param_group_desc",
			"created_by",
			"created_date"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			paramGroup.getParamGroupCode(),
			paramGroup.getParamGroupDesc(),
			UserLoginUtils.getCurrentUsername(),
			new Date()
		});
		
		return key;
	}
	
	public int update(SysParameterGroup paramGroup) {
		logger.debug("update");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_parameter_group",
			Arrays.asList(
				"param_group_code",
				"param_group_desc",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"param_group_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			paramGroup.getParamGroupCode(),
			paramGroup.getParamGroupDesc(),
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			paramGroup.getParamGroupId()
		});
		
		return updateRow;
	}
	
	public int delete(Long paramGroupId) {
		logger.info("delete");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_parameter_group",
			Arrays.asList(
				"is_deleted",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"param_group_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			paramGroupId
		});
		
		return updateRow;
	}

}
