package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;
import th.go.excise.ims.preferences.persistence.repository.ExciseProviceRepository;

@Service
public class ExciseProviceService {

	@Autowired
	private ExciseProviceRepository exciseProviceRepository;
	
	public List<ExciseProvice> findByCriteria(ExciseProvice exciseProvice){
		return exciseProviceRepository.findByCriteria(exciseProvice);
		
	}
}
