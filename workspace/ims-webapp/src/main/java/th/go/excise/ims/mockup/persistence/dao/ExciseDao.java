package th.go.excise.ims.mockup.persistence.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.go.excise.ims.mockup.domain.Excise;
import th.go.excise.ims.mockup.persistence.entity.ExciseEntity;
import th.go.excise.ims.mockup.persistence.entity.ExciseTax;
import th.go.excise.ims.mockup.utils.MysqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExciseDao {

    @Autowired
    private ExciseEntity exciseEntity;

    @Autowired
    private ExciseTax exciseTax;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String sql = " select *  from ta_excise_registtion_number as ta_n left join ta_excise_tax_receive as ta_r on ta_n.ta_excise_id = ta_r.ta_excise_id ";

    private final String sqlById = " select *  from ta_excise_registtion_number as ta_n left join ta_excise_tax_receive as ta_r on ta_n.ta_excise_id = ta_r.ta_excise_id where ta_n.ta_excise_id = ? ";

    public List<ExciseEntity> queryByExciseId(String id) {
        List<ExciseEntity> li = jdbcTemplate.query(sqlById, new Object[] {id}, exciseRowmapper);
        return li;
    }

    private RowMapper<ExciseEntity> exciseRowmapper = new RowMapper<ExciseEntity>() {
        @Override
        public ExciseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
            ExciseEntity vo = new ExciseEntity();
            ExciseTax vi = new ExciseTax();
            ArrayList<ExciseTax> ve = new ArrayList<ExciseTax>();
            vo.setExciseRegisttionNumberId(rs.getInt("TA_EXCISE_REGISTTION_NUMBER_ID"));
            vo.setExciseId(rs.getString("TA_EXCISE_ID"));
            vo.setExciseOperatorName(rs.getString("TA_EXCISE_OPERATOR_NAME"));
            vo.setExciseIdenNumber(rs.getString("TA_EXCISE_IDEN_NUMBER"));
            vo.setExciseFacName(rs.getString("TA_EXCISE_FAC_NAME"));
            vo.setIndustrialAddress(rs.getString("TA_EXCISE_FAC_ADDRESS"));
            vo.setExciseArea(rs.getString("TA_EXCISE_AREA"));
            vo.setExciseRegisCapital(rs.getInt("TA_EXCISE_REGIS_CAPITAL"));
            vo.setExciseRemark(rs.getString("TA_EXCISE_REMARK"));
            vo.setCreatedBy(rs.getString("CREATED_BY"));
            vo.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME") != null ? rs.getTimestamp("CREATED_DATETIME").toLocalDateTime() : null);
            vo.setUpdateBy(rs.getString("UPDATE_BY"));
            vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME") != null ? rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime() : null);
            vo.setTaexciseProductType(rs.getString("TA_EXCISE_PRODUCT_TYPE"));
            vo.setTaexciseSectorArea(rs.getString("TA_EXCISE_SECTOR_AREA"));

            vi.setExciseTaxReceiveId(rs.getInt("TA_EXCISE_TAX_RECEIVE_ID"));
            vi.setExciseId(rs.getString("TA_EXCISE_ID"));
            vi.setExciseTaxReceiveMonth(rs.getString("TA_EXCISE_TAX_RECEIVE_MONTH"));
            vi.setExciseTaxReceiveAmount(rs.getString("TA_EXCISE_TAX_RECEIVE_AMOUNT"));
            ve.add(vi);
            vo.setExciseTax(ve);
            return vo;
        }
    };
}
