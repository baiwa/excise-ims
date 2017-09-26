package th.co.baiwa.buckwaframework.common.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class RowMapperUtils {
	
	public static void mapCommonColumn(Object obj, ResultSet rs) throws SQLException {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			entity.setIsDeleted(rs.getString("IS_DELETED"));
			entity.setCreatedBy(rs.getString("CREATED_BY"));
			entity.setCreatedDate(rs.getDate("CREATED_DATE"));
			entity.setUpdatedBy(rs.getString("UPDATED_BY"));
			entity.setUpdatedDate(rs.getDate("UPDATED_DATE"));
		}
	}
	
}
