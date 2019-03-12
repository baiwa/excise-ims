package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public class TaWsReg4000RepositoryImpl implements TaWsReg4000RepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void batchInsert(List<TaWsReg4000> wsReg4000List) {
        String sql = SqlGeneratorUtils.genSqlInsert(
                "TA_WS_REG4000",
                Arrays.asList(
                        "WS_REG4000_ID", "NEW_REG_ID", "CUS_ID", "CUS_FULLNAME", "CUS_ADDRESS",
                        "CUS_TELNO", "CUS_EMAIL", "CUS_URL", "FAC_ID", "FAC_FULLNAME",
                        "FAC_ADDRESS", "FAC_TELNO", "FAC_EMAIL", "FAC_URL", "OFFICE_CODE",
                        "ACTIVE_FLAG", "DUTY_CODE", "CREATED_BY", "CREATED_DATE"
                ),
                "TA_WS_REG4000_SEQ"
        );

        commonJdbcTemplate.batchUpdate(sql, wsReg4000List, 1000, new ParameterizedPreparedStatementSetter<TaWsReg4000>() {
            public void setValues(PreparedStatement ps, TaWsReg4000 wsReg4000) throws SQLException {
                List<Object> paramList = new ArrayList<Object>();
                paramList.add(wsReg4000.getNewRegId());
                paramList.add(wsReg4000.getCusId());
                paramList.add(wsReg4000.getCusFullname());
                paramList.add(wsReg4000.getCusAddress());
                paramList.add(wsReg4000.getCusTelno());
                paramList.add(wsReg4000.getCusEmail());
                paramList.add(wsReg4000.getCusUrl());
                paramList.add(wsReg4000.getFacId());
                paramList.add(wsReg4000.getFacFullname());
                paramList.add(wsReg4000.getFacAddress());
                paramList.add(wsReg4000.getFacTelno());
                paramList.add(wsReg4000.getFacEmail());
                paramList.add(wsReg4000.getFacUrl());
                paramList.add(wsReg4000.getOfficeCode());
                paramList.add(wsReg4000.getActiveFlag());
                paramList.add(wsReg4000.getDutyCode());
                paramList.add(SYSTEM_USER.BATCH);
                paramList.add(LocalDateTime.now());
                commonJdbcTemplate.preparePs(ps, paramList.toArray());
            }
        });
    }

    @Override
    public void truncateTaWsReg4000() {
        commonJdbcTemplate.update("TRUNCATE TABLE TA_WS_REG4000");
    }

    private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
        sql.append(" SELECT * ");
        sql.append(" FROM TA_WS_REG4000 ");
        sql.append(" WHERE IS_DELETED = ? ");

        params.add(FLAG.N_FLAG);

        // Factory Type
        if (StringUtils.isNotBlank(formVo.getFacType())) {
            params.add(formVo.getFacType());
            sql.append(" AND FAC_TYPE = ?");
        }

        // Duty Code
        if (StringUtils.isNotBlank(formVo.getDutyCode())) {
        	sql.append(" AND DUTY_CODE = ?");
            params.add(formVo.getDutyCode());
        }

        // Office Code
        if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
        	sql.append(" AND OFFICE_CODE like ?");
            params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
        }

        //Fac fullname
        if (StringUtils.isNotBlank(formVo.getFacFullname())) {
            sql.append(" AND FAC_FULLNAME like ?");
            params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
        }

        //Cus fullname
        if (StringUtils.isNotBlank(formVo.getCusFullname())) {
            sql.append(" AND CUS_FULLNAME like ?");
            params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
        }
    }

    @Override
    public List<TaWsReg4000> findByCriteria(TaxOperatorFormVo formVo) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        buildByCriteriaQuery(sql, params, formVo);

        sql.append(" ORDER BY DUTY_CODE, OFFICE_CODE, NEW_REG_ID ");

        return this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), wsReg4000RowMapper);
    }

    @Override
    public Long countByCriteria(TaxOperatorFormVo formVo) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        buildByCriteriaQuery(sql, params, formVo);

        return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
    }

    private static final RowMapper<TaWsReg4000> wsReg4000RowMapper = new RowMapper<TaWsReg4000>() {
        @Override
        public TaWsReg4000 mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaWsReg4000 vo = new TaWsReg4000();
            vo.setWsReg4000Id(rs.getLong("WS_REG4000_ID"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setCusId(rs.getString("CUS_ID"));
            vo.setCusFullname(rs.getString("CUS_FULLNAME"));
            vo.setCusAddress(rs.getString("CUS_ADDRESS"));
            vo.setCusTelno(rs.getString("CUS_TELNO"));
            vo.setCusEmail(rs.getString("CUS_EMAIL"));
            vo.setCusUrl(rs.getString("CUS_URL"));
            vo.setFacId(rs.getString("FAC_ID"));
            vo.setFacFullname(rs.getString("FAC_FULLNAME"));
            vo.setFacAddress(rs.getString("FAC_ADDRESS"));
            vo.setFacTelno(rs.getString("FAC_TELNO"));
            vo.setFacEmail(rs.getString("FAC_EMAIL"));
            vo.setFacUrl(rs.getString("FAC_URL"));
            vo.setFacType(rs.getString("FAC_TYPE"));
            vo.setRegStatus(rs.getString("REG_STATUS"));
            vo.setRegDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_DATE")));
            vo.setRegCapital(rs.getString("REG_CAPITAL"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE"));
            vo.setActiveFlag(rs.getString("ACTIVE_FLAG"));
            vo.setDutyCode(rs.getString("DUTY_CODE"));
            return vo;
        }
    };

}
