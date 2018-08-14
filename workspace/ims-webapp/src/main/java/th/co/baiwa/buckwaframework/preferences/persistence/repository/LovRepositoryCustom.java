package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

public interface LovRepositoryCustom {
	public List<Lov> queryLovByCriteria(Lov lov , String orderBy);
	public List<String> queryLovTypeList();
}
