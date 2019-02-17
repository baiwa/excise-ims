package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseDistrict;
import th.go.excise.ims.preferences.persistence.repository.ExciseDistrictRepository;

@Service
public class ExciseDistrictService {
	
	@Autowired
	private ExciseDistrictRepository exciseDistrictRepository;
	
	public List<ExciseDistrict> findExciseDistrictListByCriteria(ExciseDistrict exciseDistrict){
		return exciseDistrictRepository.findByCriteria(exciseDistrict);
	}

}
