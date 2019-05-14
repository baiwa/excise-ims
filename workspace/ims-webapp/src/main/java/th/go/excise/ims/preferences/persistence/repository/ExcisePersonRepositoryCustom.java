package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

public interface ExcisePersonRepositoryCustom {

	public List<ExcisePersonVoSelect> findByName(String officeCode, String name);

	public List<ExcisePersonVoSelect> findByEdLogin(String edLogin);

}
