package th.go.excise.ims.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseGeoDao;
import th.go.excise.ims.common.domain.ExciseGeo;

@Service
public class ExciseGeoService {

	@Autowired
	private ExciseGeoDao exciseGeoDao;
	
	public List<ExciseGeo> findExciseGeoListByCriteria(ExciseGeo exciseGeo){
		return exciseGeoDao.findByCriteria(exciseGeo);
	}
}
