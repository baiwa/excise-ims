package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseGeoDao;
import co.th.ims.excise.domain.ExciseGeo;

@Service
public class ExciseGeoService {

	@Autowired
	private ExciseGeoDao exciseGeoDao;
	
	public List<ExciseGeo> findExciseGeoListByCriteria(ExciseGeo exciseGeo){
		return exciseGeoDao.findByCriteria(exciseGeo);
	}
}
