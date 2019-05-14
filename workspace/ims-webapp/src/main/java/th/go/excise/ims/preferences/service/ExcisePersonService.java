package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepository;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepositoryCustom;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

@Service
public class ExcisePersonService {
	
	@Autowired
	private ExcisePersonRepositoryCustom excisePersonRepositoryCus;
	
	@Autowired
	private ExcisePersonRepository excisePersonRepo;
	
	@Transactional
	public List<ExcisePersonVoSelect> findPersonByName (String officeCode ,String name) {
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByName(offCode,name);
		return resList;
	}
	
	public List<ExcisePersonVoSelect> findPersonByEdLogin (String edLogin) {
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByEdLogin(edLogin);
		return resList;
	}

}
