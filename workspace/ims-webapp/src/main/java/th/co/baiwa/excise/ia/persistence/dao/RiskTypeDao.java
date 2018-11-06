package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.vo.RiskType;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class RiskTypeDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int createRiskTask(RiskType riskType) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into IA_RISK_TASK (ID,CHECK_ID,RISK_TYPE,REMARK)values (?,?,?,?)");
		List<Object> objList = new ArrayList<Object>();
		objList.add(riskType.getId());
		objList.add(riskType.getCheckId());
		objList.add(riskType.getRiskType());
		objList.add(riskType.getRemark());
		return jdbcTemplate.update(sql.toString(), objList.toArray());
	}
	
	public List<RiskType> findRiskTypeByCriteria(RiskType riskType) {
		StringBuilder sql = new StringBuilder(" select * from IA_RISK_TASK t where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(BeanUtils.isNotEmpty(riskType)) {
			if(BeanUtils.isNotEmpty(riskType.getCheckId())) {
				sql.append(" and CHECK_ID = ? ");
				params.add(riskType.getCheckId());
			}
			if(BeanUtils.isNotEmpty(riskType.getRiskType())) {
				sql.append(" and RISK_TYPE = ? ");
				params.add(riskType.getCheckId());
			}
			if(BeanUtils.isNotEmpty(riskType.getRemark())) {
				sql.append(" and REMARK = ? ");
				params.add(riskType.getRemark());
			}
		}

		sql.append(" ORDER BY ID  ");
		List<RiskType> list = jdbcTemplate.query(sql.toString(), params.toArray(), expensesRowmapper);
		return list;
	}
	
	private RowMapper<RiskType> expensesRowmapper = new RowMapper<RiskType>() {
		
		@Override
		public RiskType mapRow(ResultSet rs, int arg1) throws SQLException {
			RiskType vo = new RiskType();
			vo.setId(rs.getLong("ID"));
			vo.setCheckId(rs.getLong("CHECK_ID"));
			vo.setRiskType(rs.getString("RISK_TYPE"));
			vo.setRemark(rs.getString("REMARK"));
			return vo;
		}
	};
	
	
	public int deleteRiskTask(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from IA_RISK_TASK where CHECK_ID = ?");
		List<Object> objList = new ArrayList<Object>();
		objList.add(id);
		return jdbcTemplate.update(sql.toString(), objList.toArray());
	}
	
	public BigDecimal getRiskTaskId() {
		String sql = "SELECT IA_RISK_TASK_SEQ.nextval as SEQ FROM DUAL";
		BigDecimal id = jdbcTemplate.queryForObject(sql, BigDecimal.class);
		return id;
	}
	
	
}
