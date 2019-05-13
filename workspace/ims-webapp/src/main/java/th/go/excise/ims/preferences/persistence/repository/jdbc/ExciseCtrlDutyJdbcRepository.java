package th.go.excise.ims.preferences.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Repository
public class ExciseCtrlDutyJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed03Vo> findByDutyGroupName(Ed03FormVo form) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_CTRL_DUTY WHERE DUTY_GROUP_NAME LIKE ? AND IS_DELETED ='N' ");
		params.add("%" + form.getDutyGroupName().replaceAll(" ", "%") + "%");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed03Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed03Vo.class));
		return datas;
	}

	public boolean checkByDutyGroupName(Ed03FormVo form) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_CTRL_DUTY ");
		sql.append(" WHERE DUTY_GROUP_NAME = ? AND DUTY_GROUP_CODE = ? AND RES_OFFCODE = ? AND IS_DELETED ='N' ");
		params.add(form.getDutyGroupName());
		params.add(form.getDutyGroupCode());
		params.add(form.getResOffcode());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed03Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed03Vo.class));
		boolean repeat = false;
		if (datas.size() == 0) {
			repeat = true;
		}
		return repeat;
	}
}
