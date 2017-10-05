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
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.persistence.mapper.ParameterInfoRowMapper;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Repository("parameterInfoDao")
public class ParameterInfoDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterInfoDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<ParameterInfo> findAll() {
		logger.debug("findAll");
		
		String sql =
			" SELECT param_info_id, param_group_id, param_code, value_1, value_2, " +
			"        value_3, value_4, value_5, value_6, sorting_order, is_default " +
			" FROM sys_parameter_info " +
			" WHERE is_deleted = ? ";
		
		return commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			ParameterInfoRowMapper.getInstance()
		);
	}
	
	public ParameterInfo findById(Long paramInfoId) {
		logger.debug("findById paramInfoId={}", paramInfoId);
		
		String sql =
			" SELECT param_info_id, param_group_id, param_code, value_1, value_2, " +
			"        value_3, value_4, value_5, value_6, sorting_order, is_default " +
			" FROM sys_parameter_info " +
			" WHERE is_deleted = ? " +
			"   AND param_info_id = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				paramInfoId
			},
			ParameterInfoRowMapper.getInstance()
		);
	}
	
	public List<ParameterInfo> findByParamGroupId(Long paramGroupId) {
		logger.debug("findByParamGroupId paramGroupId={}", paramGroupId);
		
		String sql =
			" SELECT param_info_id, param_group_id, param_code, value_1, value_2, " +
			"        value_3, value_4, value_5, value_6, sorting_order, is_default " +
			" FROM sys_parameter_info " +
			" WHERE is_deleted = ? " +
			"   AND param_group_id = ? ";
		
		List<ParameterInfo> paramInfoList = (List<ParameterInfo>) commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG,
				paramGroupId
			},
			ParameterInfoRowMapper.getInstance()
		);
		
		return paramInfoList;
	}
	
	public int count() {
		logger.debug("count");
		
		String sql = SqlGeneratorUtils.genSqlCount("sys_parameter_info", Arrays.asList("is_deleted"));
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			Integer.class
		);
	}
	
	public int countByParamGroupId(Long paramGroupId) {
		logger.debug("countByParamGroupId paramGroupId={}", paramGroupId);
		
		String sql = SqlGeneratorUtils.genSqlCount("sys_parameter_info", Arrays.asList("is_deleted", "param_group_id"));
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				paramGroupId
			},
			Integer.class
		);
	}
	
	public Long insert(ParameterInfo paramInfo) {
		logger.debug("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("sys_parameter_info", Arrays.asList(
			"param_group_id",
			"param_code",
			"value_1",
			"value_2",
			"value_3",
			"value_4",
			"value_5",
			"value_6",
			"sorting_order",
			"is_default",
			"created_by",
			"created_date"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			paramInfo.getParamGroupId(),
			paramInfo.getParamCode(),
			paramInfo.getValue1(),
			paramInfo.getValue2(),
			paramInfo.getValue3(),
			paramInfo.getValue4(),
			paramInfo.getValue5(),
			paramInfo.getValue6(),
			paramInfo.getSortingOrder(),
			paramInfo.getIsDefault(),
			UserLoginUtils.getCurrentUsername(),
			new Date()
		});
		
		return key;
	}
	
	public int update(ParameterInfo paramInfo) {
		logger.debug("update");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_parameter_info",
			Arrays.asList(
				"param_code",
				"value_1",
				"value_2",
				"value_3",
				"value_4",
				"value_5",
				"value_6",
				"sorting_order",
				"is_default",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"param_info_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			paramInfo.getParamCode(),
			paramInfo.getValue1(),
			paramInfo.getValue2(),
			paramInfo.getValue3(),
			paramInfo.getValue4(),
			paramInfo.getValue5(),
			paramInfo.getValue6(),
			paramInfo.getSortingOrder(),
			paramInfo.getIsDefault(),
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			paramInfo.getParamInfoId()
		});
		
		return updateRow;
	}
	
	public int delete(Long paramInfoId) {
		logger.debug("delete");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("sys_parameter_info",
			Arrays.asList(
				"is_deleted",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"param_info_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			paramInfoId
		});
		
		return updateRow;
	}
	
}
