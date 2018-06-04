package th.go.excise.ims.mockup.utils;

public class MysqlUtils {

	 public static String countForDatatable(String sql) {
	  StringBuilder sqlBuilger = new StringBuilder();
	  sqlBuilger.append(" select count(1)  from ( ");
	  sqlBuilger.append(sql);
	  sqlBuilger.append(" ) AS tmp_count_tb ");
	  return sqlBuilger.toString();
	 }

	 public static String countForDatatable(StringBuilder sql) {
	  return countForDatatable(sql.toString());
	 }
	 
	 public static String limitForDataTable(String sql, int start, int length) {
	  StringBuilder sqlBuilger = new StringBuilder(sql);
	  sqlBuilger.append(" limit ").append(start).append(", ").append(length);

	  return sqlBuilger.toString();
	 }

	 public static String limitSql(String sql, int limit) {
		StringBuilder sqlBuilger = new StringBuilder(sql);
		sqlBuilger.append(" limit ").append(limit);

		return sqlBuilger.toString();
	 }

}