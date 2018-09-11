package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.excise.ia.persistence.vo.QtnScore;

public class QtnFinalRepHeaderRepositoryImpl implements QtnFinalRepHeaderRepositoryCustom {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<QtnScore> calScore(Long headerId) {
		List<Object> param = new ArrayList<Object>();
		String SQL = " select (select distinct H.QTN_REPORT_HDR_NAME from IA_QTN_FINAL_REP_HEADER FH left join IA_QTN_REPORT_HEADER H on H.QTN_REPORT_HDR_ID = FH.QTN_REPORT_HDR_ID where FH.QTN_REPORT_HDR_ID = ?) TITLE, (select count(1) from IA_QTN_FINAL_REP_HEADER FH inner join IA_QTN_FINAL_REP_MAIN M on FH.QTN_FINAL_REP_HDR_ID = M.QTN_FINAL_REP_HDR_ID right join IA_QTN_FINAL_REP_DETAIL D on M.QTN_FINAL_REP_MAN_ID = D.QTN_FINAL_REP_MAN_ID where FH.QTN_REPORT_HDR_ID = ? and D.QTN_POINT = 'N' and D.IS_DELETED = 'N') N, (select count(1) from IA_QTN_FINAL_REP_HEADER FH inner join IA_QTN_FINAL_REP_MAIN M on FH.QTN_FINAL_REP_HDR_ID = M.QTN_FINAL_REP_HDR_ID right join IA_QTN_FINAL_REP_DETAIL D on M.QTN_FINAL_REP_MAN_ID = D.QTN_FINAL_REP_MAN_ID where FH.QTN_REPORT_HDR_ID = ? and D.QTN_POINT = 'Y' and D.IS_DELETED = 'N') Y from dual ";
		for(int i=0; i<3; i++) {
			param.add(headerId);
		}
		List<QtnScore> header = jdbcTemplate.query(SQL, param.toArray(), _row);
		return header;
	}
	
	private RowMapper<QtnScore> _row = new RowMapper<QtnScore>() {

		@Override
		public QtnScore mapRow(ResultSet rs, int arg1) throws SQLException {
			QtnScore header = new QtnScore();
			header.setTitle(rs.getString("TITLE"));
			header.setApprove(rs.getInt("Y"));
			header.setReject(rs.getInt("N"));
			return header;
		}
		
	};

}
