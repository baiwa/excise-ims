package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgGfmisRepository;

@Service
public class Int15Service {
	
	@Autowired
	private ExciseOrgGfmisRepository exciseOrgGfmisRepository;
	
	
	public List<ExciseOrgGfmis> findGfDisburseUnitAndName(){
		return exciseOrgGfmisRepository.findGfDisburseUnitAndName();
	}
	
	
}
