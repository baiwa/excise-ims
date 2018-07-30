package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysAuditHistoryAction;

@Repository
public class SysAuditHistoryActionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertSysAuditHistoryAction(SysAuditHistoryAction value) {
		StringBuilder sql = new StringBuilder(" INSERT INTO SYS_AUDIT_HISTORY_ACTION (AUDIT_ID, ACTION_BY, TITLE, DETAIL, START_MONTH, END_MONTH, CREATED_BY, CREATED_DATE) ");
		sql.append(" VALUES (sys_guid(), ?, ?, ?, ?, ?,?, ? ) ");
		return jdbcTemplate.update(sql.toString(), sysAuditHistoryActionToArrayObject(value));
	}
	
	
	private Object[] sysAuditHistoryActionToArrayObject(SysAuditHistoryAction value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getActionBy());
			valueList.add(value.getTitle());
			valueList.add(value.getDetail());
			valueList.add(value.getStartMonth());
			valueList.add(value.getEndMonth());
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
		}
		return valueList.toArray();
	}
}
