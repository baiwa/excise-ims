package th.go.excise.ims.preferences.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
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
	public List<ExcisePersonVoSelect> findPersonByName ( String name) {
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByName(offCode,name);
		return resList;
	}
	
	public List<ExcisePersonVoSelect> findPersonByEdLogin (String edLogin) {
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByEdLogin(edLogin);
		return resList;
	}
	
	public  DataTableAjax<ExcisePersonVoSelect> findPersonForAssign (DataTableRequest quest) {
		DataTableAjax<ExcisePersonVoSelect> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(excisePersonRepositoryCus.findAllForAssign(quest));
		dataTableAjax.setDraw(quest.getDraw() + 1);
		int count = excisePersonRepositoryCus.countAllForAssign(quest).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);
		return dataTableAjax;
	}
	
	public  ExcisePerson savePerson (ExcisePersonVoSelect form) {
		Optional<ExcisePerson> person = excisePersonRepo.findById(form.getEdPersonSeq());
		ExcisePerson personRes = new ExcisePerson();
		if (person.isPresent()) {
			personRes = person.get();
			personRes.setAuSubdeptCode(form.getSubDeptCode());
			personRes.setAuSubdeptLevel(form.getLevel());
			excisePersonRepo.save(personRes);
		}
		return personRes;
	}
	
	public List<ExcisePersonVoSelect> findbyOfficeCode (String officeCode,String subDeptCode) {
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByOfficeCode(officeCode,subDeptCode);
		return resList;
	}

}
