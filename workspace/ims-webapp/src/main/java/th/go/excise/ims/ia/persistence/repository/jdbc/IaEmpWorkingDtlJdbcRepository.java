package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Repository
public class IaEmpWorkingDtlJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int091201Vo> getList() {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT WD.USER_NAME , WD.USER_POSITION , COUNT(0)  DAY FROM IA_EMP_WORKING_DTL WD " + 
				" WHERE WD.IS_DELETED = 'N' " + 
				" AND WD.WORKING_FLAG = '2' " + 
				" AND WD.REIMBURSE_EXP_FLAG = 'Y' " + 
				" GROUP BY WD.USER_NAME ,WD.USER_POSITION ");
                  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int091201Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int091201Vo.class));

		return datas;
	}
}
