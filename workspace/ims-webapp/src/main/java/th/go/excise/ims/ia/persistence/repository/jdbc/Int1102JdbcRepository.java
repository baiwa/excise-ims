package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int1102Vo;

@Repository
public class Int1102JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int1102Vo> getData(Int1102FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT a.* , b.ISSUES ,b.WHAT_SHOULD_BE " );
		sql.append("FROM IA_CONCLUDE_FOLLOW_HDR a " );
		sql.append("LEFT JOIN IA_CONCLUDE_FOLLOW_DETAIL b " );
		sql.append("ON a.ID = b.ID_HDR ");
		sql.append("WHERE a.ID = ? AND a.IS_DELETED = 'N' " );
		
		params.add(request.getId());

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int1102Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int1102Vo.class));
		return datas;
	}
}
