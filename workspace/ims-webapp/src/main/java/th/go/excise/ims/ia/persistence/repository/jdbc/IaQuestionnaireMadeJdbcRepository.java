package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int020201JoinVo;
import th.go.excise.ims.ia.vo.Int020201SidesFormVo;

@Repository
public class IaQuestionnaireMadeJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public BigDecimal findIdSideByIdSideDtl(BigDecimal idSideDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT SD.ID_SIDE FROM IA_QUESTIONNAIRE_MADE M ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE_DTL SD ");
		sql.append(" ON M.ID_SIDE_DTL = SD.ID_SIDE ");
		sql.append(" WHERE M.IS_DELETED = 'N' ");
		sql.append(" AND SD.IS_DELETED = 'N' ");
		sql.append(" AND M.ID_MADE_HDR = ? ");
		sql.append(" AND ROWNUM <= 1 ");
		
		params.add(idSideDtl);
		
		return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), BigDecimal.class);
	}
	
	public List<Int020201JoinVo> findLvl1ByIdMadeHdr(Int020201SidesFormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT M.ID, M.ID_MADE_HDR, SD.ID_SIDE, M.ID_SIDE_DTL, SD.SIDE_DTL, M.QTN_LEVEL, ");
		sql.append(" 	M.NOTE, SD.SEQ, SD.SEQ_DTL, M.CHECK_FLAG, M.OFFICE_CODE, SD.ID_HEADING, M.STATUS ");
		sql.append(" FROM IA_QUESTIONNAIRE_MADE M ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE_DTL SD ");
		sql.append(" 	ON M.ID_SIDE_DTL = SD.ID ");
		sql.append(" WHERE M.IS_DELETED = 'N' ");
		sql.append(" 	AND SD.IS_DELETED = 'N' ");
		sql.append(" 	AND M.ID_MADE_HDR = ? ");
		sql.append(" 	AND SD.ID_SIDE =  ? ");
		sql.append(" 	AND M.QTN_LEVEL = 1 ");
		sql.append(" ORDER BY SD.SEQ ASC ");

		params.add(request.getIdMadeHdr());
		params.add(request.getIdSide());
		 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020201JoinVo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Int020201JoinVo.class));
		return data;
	}
	
	public List<Int020201JoinVo> findLvl2ByIdMadeHdr(Int020201JoinVo objLVL1) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT M.ID, M.ID_MADE_HDR, SD.ID_SIDE, M.ID_SIDE_DTL, SD.SIDE_DTL, M.QTN_LEVEL, ");
		sql.append(" 	M.NOTE, SD.SEQ, SD.SEQ_DTL, M.CHECK_FLAG, M.OFFICE_CODE, SD.ID_HEADING, M.STATUS ");
		sql.append(" FROM IA_QUESTIONNAIRE_MADE M ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE_DTL SD ");
		sql.append(" 	ON M.ID_SIDE_DTL = SD.ID ");
		sql.append(" WHERE M.IS_DELETED = 'N' ");
		sql.append(" 	AND SD.IS_DELETED = 'N' ");
		sql.append(" 	AND M.ID_MADE_HDR = ? ");
		sql.append(" 	AND SD.ID_SIDE =  ? ");
		sql.append(" 	AND M.QTN_LEVEL = 2 ");
		sql.append(" 	AND SD.SEQ = ? ");
		sql.append(" 	AND SD.ID_HEADING = ? ");
		sql.append(" ORDER BY SD.SEQ_DTL ASC ");

		params.add(objLVL1.getIdMadeHdr());
		params.add(objLVL1.getIdSide());
		params.add(objLVL1.getSeq());
		params.add(objLVL1.getIdSideDtl());

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020201JoinVo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Int020201JoinVo.class));
		return data;
	}
	
	public List<Int020201JoinVo> findLvl3ByIdMadeHdr(Int020201JoinVo objLVL2) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT M.ID, M.ID_MADE_HDR, SD.ID_SIDE, M.ID_SIDE_DTL, SD.SIDE_DTL, M.QTN_LEVEL, ");
		sql.append(" 	M.NOTE, SD.SEQ, SD.SEQ_DTL, M.CHECK_FLAG, M.OFFICE_CODE, SD.ID_HEADING, M.STATUS ");
		sql.append(" FROM IA_QUESTIONNAIRE_MADE M ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE_DTL SD ");
		sql.append(" 	ON M.ID_SIDE_DTL = SD.ID ");
		sql.append(" WHERE M.IS_DELETED = 'N' ");
		sql.append(" 	AND SD.IS_DELETED = 'N' ");
		sql.append(" 	AND M.ID_MADE_HDR = ? ");
		sql.append(" 	AND SD.ID_SIDE =  ? ");
		sql.append(" 	AND M.QTN_LEVEL = 3 ");
		sql.append(" 	AND SD.SEQ = ? ");
		sql.append(" 	AND SD.ID_HEADING = ? ");
		sql.append(" ORDER BY SD.SEQ_DTL ASC ");

		params.add(objLVL2.getIdMadeHdr());
		params.add(objLVL2.getIdSide());
		params.add(objLVL2.getSeqDtl());
		params.add(objLVL2.getIdSideDtl());
		 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int020201JoinVo> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Int020201JoinVo.class));
		return data;
	}
	
	public Integer countCheckNull(Int020201JoinVo request, Integer level) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT COUNT(*) FROM IA_QUESTIONNAIRE_MADE MD ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_SIDE_DTL SD ");
		sql.append(" 	ON MD.ID_SIDE_DTL = SD.ID ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" 	AND SD.QTN_LEVEL = ? ");
		sql.append(" 	AND MD.ID_MADE_HDR = ? ");
		sql.append(" 	AND SD.ID_SIDE = ? ");
		sql.append(" 	AND MD.IS_DELETED = 'N' ");
		sql.append(" 	AND SD.IS_DELETED = 'N' ");
		sql.append(" 	AND MD.CHECK_FLAG IS NULL ");

		params.add(level);
		params.add(request.getIdMadeHdr());
		params.add(request.getIdSide());
		
		if(level == 2) {
			params.add(request.getSeqDtl());
			sql.append("	AND SD.SEQ_DTL = ? ");
		}
		if(level != 3) {
			params.add(request.getSeq());
			sql.append("	AND SD.SEQ = ? ");
		}
		 
		Integer countNull = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
		return countNull;
	}
	
	public BigDecimal updateStatus(BigDecimal idMadeHdr,String status) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_QUESTIONNAIRE_MADE SET STATUS = ? WHERE ID_MADE_HDR  = ? ");
		List<Object> params = new ArrayList<Object>();
		
		params.add(status);
		params.add(idMadeHdr);
		
		commonJdbcTemplate.update(sql.toString(), params.toArray());

		return idMadeHdr;
	}
	
	public BigDecimal deleteByIdHdr(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" DELETE FROM IA_QUESTIONNAIRE_MADE WHERE ID IN( ");
		sql.append("        SELECT md.ID AS ID FROM IA_QUESTIONNAIRE_MADE_HDR mh  ");
		sql.append("        INNER JOIN IA_QUESTIONNAIRE_MADE md  ");
		sql.append("        ON mh.id=md.ID_MADE_HDR  ");
		sql.append("        WHERE mh.ID_HDR = ? ) ");
		
		params.add(idHdr);		
		commonJdbcTemplate.update(sql.toString(), params.toArray());

		return idHdr;
	}
	
}
