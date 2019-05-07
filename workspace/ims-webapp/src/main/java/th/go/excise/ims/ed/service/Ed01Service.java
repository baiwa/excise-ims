package th.go.excise.ims.ed.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ed.persistence.entity.ExcisePerson;
import th.go.excise.ims.ed.persistence.repository.ExcisePersonRepository;
import th.go.excise.ims.ed.vo.Ed01FormVo;

@Service
public class Ed01Service {
	@Autowired
	private ExcisePersonRepository excisePersonRepository;
	
	@Transactional
	public void saveUserProfile(Ed01FormVo form) {
		ExcisePerson entity = new ExcisePerson();
		entity.setEdLogin(form.getUsername());
		entity.setEdPersonName(form.getName());
		entity.setEdPositionName(form.getPosition());
		entity.setEdOffcode(form.getOfficeCode());
		entity.setEdPersonId(form.getIdCardNumber());
		excisePersonRepository.save(entity);
	}
	
}
