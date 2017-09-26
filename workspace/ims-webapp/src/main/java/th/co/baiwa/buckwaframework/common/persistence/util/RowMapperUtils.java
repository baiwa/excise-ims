package th.co.baiwa.buckwaframework.common.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class RowMapperUtils {
	
	public static void mapCommonColumn(Object obj, ResultSet rs) throws SQLException {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			entity.setIsDeleted(rs.getString("is_deleted"));
			entity.setCreatedBy(rs.getString("created_by"));
			entity.setCreatedDate(rs.getDate("created_date"));
			entity.setUpdatedBy(rs.getString("updated_by"));
			entity.setUpdatedDate(rs.getDate("updated_date"));
		}
	}
	
}
