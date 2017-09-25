package th.co.baiwa.buckwaframework.common.persistence.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.google.common.base.CaseFormat;

public class SqlGeneratorUtils {
	
	public static String genSqlSelect(String tableName, Collection<String> selectFieldNameList, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT ");
		builder.append(org.springframework.util.StringUtils.collectionToCommaDelimitedString(selectFieldNameList));
		builder.append(" FROM ").append(tableName);
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, " ", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
	public static String genSqlCount(String tableName, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(1) FROM ").append(tableName);
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, " ", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
	public static String genSqlInsert(String tableName, Collection<String> fieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO ").append(tableName);
		builder.append(" (").append(org.springframework.util.StringUtils.collectionToCommaDelimitedString(fieldNameList)).append(")");
		builder.append(" VALUES (").append(org.apache.commons.lang3.StringUtils.repeat("?", ",", fieldNameList.size())).append(")");
		return builder.toString();
	}
	
	public static String genSqlInsertByModel(String tableName, Class<?> clazz) {
		List<String> fieldNameList = new ArrayList<String>();
		
		ReflectionUtils.doWithFields(
			clazz,
			new FieldCallback() {
				@Override
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					fieldNameList.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName()));
				}
			},
			new FieldFilter() {
				@Override
				public boolean matches(Field field) {
					return (!Modifier.isStatic(field.getModifiers()) && !"serialVersionUID".equals(field.getName())) &&
						!Modifier.isTransient(field.getModifiers());
				}
			}
		);
		
		return genSqlInsert(tableName, fieldNameList);
	}
	
	public static String genSqlUpdate(String tableName, Collection<String> fieldNameList, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE ").append(tableName);
		builder.append(" SET ").append(org.springframework.util.StringUtils.collectionToDelimitedString(fieldNameList, ",", "", " = ?"));
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, ",", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
}
