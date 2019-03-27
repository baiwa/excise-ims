package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int1101Vo;

@Repository
public class IaConcludeFollowDetailJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int1101Vo> getDataDetailList(String id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_CONCLUDE_FOLLOW_DETAIL WHERE ID_HDR = ? AND Is_Deleted = 'N' ORDER by SEQ ASC ");

		params.add(id);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int1101Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int1101Vo.class));

		return datas;
	}
	
}
