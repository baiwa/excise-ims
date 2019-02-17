package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;
import th.go.excise.ims.preferences.persistence.repository.ExciseGeoRepository;

@Service
public class ExciseGeoService {

	@Autowired
	private ExciseGeoRepository exciseGeoDao;
	
	public List<ExciseGeo> findExciseGeoListByCriteria(ExciseGeo exciseGeo){
		return exciseGeoDao.findByCriteria(exciseGeo);
	}
}
