package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(MenuRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<MenuVo> listMenu() {
		logger.info("call listMenu");
		StringBuilder sql = new StringBuilder();
		sql.append(" WITH t1(MENU_ID, parent_id, lvl, root_id, MENU_CODE, MENU_NAME, URL, SORTING_ORDER) AS ( ");
		sql.append("   SELECT MENU_ID, ");
		sql.append("          parent_id, ");
		sql.append("          1 AS lvl, ");
		sql.append("          MENU_ID AS root_id, ");
		sql.append("          MENU_CODE, ");
		sql.append("          MENU_NAME, ");
		sql.append("          URL, ");
		sql.append("          SORTING_ORDER ");
		sql.append("   FROM   ADM_MENU ");
		sql.append("   WHERE  parent_id IS NULL ");
		sql.append("   UNION ALL ");
		sql.append("   SELECT t2.MENU_ID, ");
		sql.append("          t2.parent_id, ");
		sql.append("          lvl+1, ");
		sql.append("          t1.root_id, ");
		sql.append("          t2.MENU_CODE, ");
		sql.append("          t2.MENU_NAME,          ");
		sql.append("          t2.URL, ");
		sql.append("          t2.SORTING_ORDER ");
		sql.append("   FROM   ADM_MENU t2, t1 ");
		sql.append("   WHERE  t2.parent_id = t1.MENU_ID ");
		sql.append(" ) ");
		sql.append(" SEARCH DEPTH FIRST BY MENU_ID SET order1 ");
		sql.append(" SELECT MENU_ID, ");
		sql.append("        parent_id, ");
		sql.append("        RPAD('.', (lvl-1)*2, '.') || MENU_ID AS tree, ");
		sql.append("        lvl, ");
		sql.append("        root_id, ");
		sql.append("        MENU_CODE, ");
		sql.append("        MENU_NAME, ");
		sql.append("        URL, ");
		sql.append("        SORTING_ORDER ");
		sql.append(" FROM t1 ");
		sql.append(" ORDER BY order1 ");

		List<MenuVo> list = commonJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(MenuVo.class));
		return list;

	}

}
