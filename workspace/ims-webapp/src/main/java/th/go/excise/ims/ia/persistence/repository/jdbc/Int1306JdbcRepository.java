package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.vo.Int1306DataVo;
import th.go.excise.ims.ia.vo.Int1306FormVo;

@Repository
public class Int1306JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public Int1306DataVo findIaAuditPmassessHByCriteria(Int1306FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PMASSESS_NO,PMA_AUDIT_EVIDENT,PMA_AUDIT_SUGGESTION,PMA_AUDIT_RESULT ");
		sql.append(" FROM IA_AUDIT_PMASSESS_H ");
		sql.append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND OFF_CODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}

		if (StringUtils.isNotBlank(vo.getBudgetYear())) {
			sql.append(" AND FORM_YEAR LIKE ? ");
			paramList.add(vo.getBudgetYear());
		}

		if (StringUtils.isNotBlank(vo.getAssessAuditNo())) {
			sql.append(" AND AUDIT_PMASSESS_NO LIKE ? ");
			paramList.add(vo.getAssessAuditNo());
		}
		sql.append(" AND ROWNUM <= 1 ");
		sql.append(" ORDER BY AUDIT_PMASSESS_H_ID DESC ");

		List<Int1306DataVo> dataList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), auditPmassessHRowMapper);
		Int1306DataVo data = null;

		if (dataList != null && dataList.size() > 0) {
			data = dataList.get(0);
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT01").getValue1());
		} else {
			data = new Int1306DataVo();
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT01").getValue1());
		}

		return data;

	}

	private RowMapper<Int1306DataVo> auditPmassessHRowMapper = new RowMapper<Int1306DataVo>() {
		@Override
		public Int1306DataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int1306DataVo vo = new Int1306DataVo();
			vo.setAuditNo(rs.getString("AUDIT_PMASSESS_NO"));
			vo.setEvident(rs.getString("PMA_AUDIT_EVIDENT"));
			vo.setSuggestion(rs.getString("PMA_AUDIT_SUGGESTION"));
			vo.setResult(rs.getString("PMA_AUDIT_RESULT"));
			return vo;
		}
	};

	public Int1306DataVo findIaAuditPmQtHByCriteria(Int1306FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PMQT_NO,QT_AUDIT_EVIDENT,QT_AUDIT_SUGGESTION,QT_AUDIT_RESULT ");
		sql.append(" FROM IA_AUDIT_PMQT_H ");
		sql.append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND OFF_CODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}

		if (StringUtils.isNotBlank(vo.getBudgetYear())) {
			sql.append(" AND FORM_YEAR LIKE ? ");
			paramList.add(vo.getBudgetYear());
		}

		if (StringUtils.isNotBlank(vo.getQtAuditNo())) {
			sql.append(" AND AUDIT_PMQT_NO LIKE ? ");
			paramList.add(vo.getQtAuditNo());
		}

		sql.append(" AND ROWNUM <= 1 ");
		sql.append(" ORDER BY AUDIT_PMQT_H_ID DESC ");

		List<Int1306DataVo> dataList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), auditPmQtHRowMapper);
		Int1306DataVo data = null;

		if (dataList != null && dataList.size() > 0) {
			data = dataList.get(0);
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT02").getValue1());
		} else {
			data = new Int1306DataVo();
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT02").getValue1());
		}

		return data;

	}

	private RowMapper<Int1306DataVo> auditPmQtHRowMapper = new RowMapper<Int1306DataVo>() {
		@Override
		public Int1306DataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int1306DataVo vo = new Int1306DataVo();
			vo.setAuditNo(rs.getString("AUDIT_PMQT_NO"));
			vo.setEvident(rs.getString("QT_AUDIT_EVIDENT"));
			vo.setSuggestion(rs.getString("QT_AUDIT_SUGGESTION"));
			vo.setResult(rs.getString("QT_AUDIT_RESULT"));
			return vo;
		}
	};

	public Int1306DataVo findIaAuditPy1HCriteria(Int1306FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PY1_NO,CONDITION_TEXT,CRITERIA_TEXT,AUDIT_RESULT ");
		sql.append(" FROM IA_AUDIT_PY1_H ");
		sql.append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}

		if (StringUtils.isNotBlank(vo.getBudgetYear())) {
			sql.append(" AND BUGGET_YEAR LIKE ? ");
			paramList.add(vo.getBudgetYear());
		}

		if (StringUtils.isNotBlank(vo.getPy1AuditNo())) {
			sql.append(" AND AUDIT_PY1_NO LIKE ? ");
			paramList.add(vo.getPy1AuditNo());
		}

		sql.append(" AND ROWNUM <= 1 ");
		sql.append(" ORDER BY IA_AUDIT_PY1_H_ID DESC ");

		List<Int1306DataVo> dataList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), auditPy1HRowMapper);
		Int1306DataVo data = null;

		if (dataList != null && dataList.size() > 0) {
			data = dataList.get(0);
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT03").getValue1());
		} else {
			data = new Int1306DataVo();
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT03").getValue1());
		}

		return data;

	}

	private RowMapper<Int1306DataVo> auditPy1HRowMapper = new RowMapper<Int1306DataVo>() {
		@Override
		public Int1306DataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int1306DataVo vo = new Int1306DataVo();
			vo.setAuditNo(rs.getString("AUDIT_PY1_NO"));
			vo.setEvident(rs.getString("CONDITION_TEXT"));
			vo.setSuggestion(rs.getString("CRITERIA_TEXT"));
			vo.setResult(rs.getString("AUDIT_RESULT"));
			return vo;
		}
	};

	public Int1306DataVo findIaAuditPy2HCriteria(Int1306FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PY2_NO,PY2_AUDIT_EVIDENT,PY2_AUDIT_SUGGESTION,PY2_AUDIT_RESULT ,PY2_ACTIVITY_RESULT ");
		sql.append(" FROM IA_AUDIT_PY2_H ");
		sql.append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE = ? ");
			paramList.add(vo.getOfficeCode());
		}

		if (StringUtils.isNotBlank(vo.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			paramList.add(vo.getBudgetYear());
		}

		if (StringUtils.isNotBlank(vo.getPy2AuditNo())) {
			sql.append(" AND AUDIT_PY2_NO = ? ");
			paramList.add(vo.getPy2AuditNo());
		}

		sql.append(" AND ROWNUM <= 1 ");
		sql.append(" ORDER BY IA_AUDIT_PY2_H_ID DESC ");

		List<Int1306DataVo> dataList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), auditPy2HRowMapper);
		Int1306DataVo data = null;

		if (dataList != null && dataList.size() > 0) {
			data = dataList.get(0);
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT04").getValue1());
		} else {
			data = new Int1306DataVo();
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT04").getValue1());
		}
		return data;
	}

	private RowMapper<Int1306DataVo> auditPy2HRowMapper = new RowMapper<Int1306DataVo>() {
		@Override
		public Int1306DataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int1306DataVo vo = new Int1306DataVo();
			vo.setAuditNo(rs.getString("AUDIT_PY2_NO"));
			vo.setEvident(rs.getString("PY2_AUDIT_EVIDENT"));
			vo.setSuggestion(rs.getString("PY2_AUDIT_SUGGESTION"));
			vo.setResult(rs.getString("PY2_AUDIT_RESULT"));
			vo.setResult2(rs.getString("PY2_ACTIVITY_RESULT"));
			return vo;
		}
	};

	public Int1306DataVo findIaAuditPmCommitHCriteria(Int1306FormVo vo) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_PMCOMMIT_NO,CONDITION_TEXT,CRITERIA_TEXT,AUDIT_FLAG ");
		sql.append(" FROM IA_AUDIT_PMCOMMIT_H ");
		sql.append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("'");

		if (StringUtils.isNotBlank(vo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ? ");
			paramList.add(vo.getOfficeCode());
		}

		if (StringUtils.isNotBlank(vo.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR LIKE ? ");
			paramList.add(vo.getBudgetYear());
		}

		if (StringUtils.isNotBlank(vo.getCommitteeAuditNo())) {
			sql.append(" AND AUDIT_PMCOMMIT_NO LIKE ? ");
			paramList.add(vo.getCommitteeAuditNo());
		}

		sql.append(" AND ROWNUM <= 1 ");
		sql.append(" ORDER BY AUDIT_PMCOMMIT_ID DESC ");

		List<Int1306DataVo> dataList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), auditPmCommitHRowMapper);
		Int1306DataVo data = null;

		if (dataList != null && dataList.size() > 0) {
			data = dataList.get(0);
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT05").getValue1());
		} else {
			data = new Int1306DataVo();
			data.setTopic(ApplicationCache.getParamInfoByCode("IA_AUDIT_RESULT", "IA_AUDIT05").getValue1());
		}

		return data;

	}

	private RowMapper<Int1306DataVo> auditPmCommitHRowMapper = new RowMapper<Int1306DataVo>() {
		@Override
		public Int1306DataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int1306DataVo vo = new Int1306DataVo();
			vo.setAuditNo(rs.getString("AUDIT_PMCOMMIT_NO"));
			vo.setEvident(rs.getString("CONDITION_TEXT"));
			vo.setSuggestion(rs.getString("CRITERIA_TEXT"));
			vo.setResult(rs.getString("AUDIT_FLAG"));
			return vo;
		}
	};

}