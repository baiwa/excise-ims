package th.co.baiwa.buckwaframework.accesscontrol.vo;

import java.util.List;

public class MenuVo {

	private String menuCode;
	private String menuName;
	private String url;
	private List<MenuVo> menuVoList;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuVo> getMenuVoList() {
		return menuVoList;
	}

	public void setMenuVoList(List<MenuVo> menuVoList) {
		this.menuVoList = menuVoList;
	}

}
