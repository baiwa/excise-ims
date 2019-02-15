package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.dao.ExciseGeoDao;
import th.go.excise.ims.preferences.domain.ExciseGeo;

@Service
public class ExciseGeoService {

	@Autowired
	private ExciseGeoDao exciseGeoDao;
	
	public List<ExciseGeo> findExciseGeoListByCriteria(ExciseGeo exciseGeo){
		return exciseGeoDao.findByCriteria(exciseGeo);
	}
}
