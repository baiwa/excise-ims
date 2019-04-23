package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.MenuRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public List<MenuVo> list() {

		// ==> query list menu
		List<MenuVo> menuAll = menuRepository.listMenu();

		List<MenuVo> roots = menuAll.stream().filter(p -> p.getParentId() == null).collect(Collectors.toList());
		// ==> add root
		List<MenuVo> result = new ArrayList<MenuVo>();
		roots.forEach(e -> {
			
			MenuVo vo = new MenuVo();
			vo.setMenuId(e.getMenuId());
			vo.setParentId(e.getParentId());
			vo.setMenuName(e.getMenuName());
			vo.setUrl(e.getUrl());
			vo.setSortingOrder(e.getSortingOrder());
			if (1 == e.getLvl()) {
				List<MenuVo> list = this.recursiveFunc(e.getMenuId(), e.getUrl(), menuAll);
				vo.setMenuVoList(list);
			}
			result.add(vo);

		});

		return result;
	}

	public List<MenuVo> recursiveFunc(String id, String url, List<MenuVo> menuAll) {
		List<MenuVo> data = new ArrayList<MenuVo>();
		menuAll.forEach(e -> {
			if (id.equals(e.getParentId()))
				data.add(e);
		});
		for (int i = 0; i < data.size(); i++) {
			if (StringUtils.isNotBlank(data.get(i).getMenuId()) && data.get(i).getUrl() == null) {
				data.get(i).setMenuVoList(this.recursiveFunc(data.get(i).getMenuId(), data.get(i).getUrl(), menuAll));
			}
		}
		return data;
	}

}
