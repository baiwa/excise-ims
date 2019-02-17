package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseAmphur;
import th.go.excise.ims.preferences.persistence.repository.ExciseAmphurRepository;

@Service
public class ExciseAmphurService {
	
	@Autowired
	private ExciseAmphurRepository exciseAmphurRepository;
	
	public List<ExciseAmphur> findExciseAmphurListByCriteria(ExciseAmphur exciseAmphur){
		return exciseAmphurRepository.findByCriteria(exciseAmphur);
	}

}
