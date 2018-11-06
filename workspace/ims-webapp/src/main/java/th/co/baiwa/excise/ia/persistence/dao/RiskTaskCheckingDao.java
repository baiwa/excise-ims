package th.co.baiwa.excise.ia.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.vo.RisTaskChecking;
import th.co.baiwa.excise.ia.persistence.vo.RiskTaskChecking;

@Repository
public class RiskTaskCheckingDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public int createRiskTaskChecking(RisTaskChecking risTaskChecking) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into IA_RISK_TASK_CHECKING (ID,TASK_ID,CHECKED,DESCRIPTION)values (IA_RISK_TASK_CHECKING_SEQ.nextval,?,?,?)");
		List<Object> objList = new ArrayList<Object>();
		objList.add(risTaskChecking.getTaskId());
		objList.add(risTaskChecking.getChecked());
		objList.add(risTaskChecking.getDesc());
		return jdbcTemplate.update(sql.toString(), objList.toArray());
	}
	
	public int deleteRiskTaskChecking(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from IA_RISK_TASK_CHECKING where TASK_ID = ?");
		List<Object> objList = new ArrayList<Object>();
		objList.add(id);
		return jdbcTemplate.update(sql.toString(), objList.toArray());
	}
	

}
