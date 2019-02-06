package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.th.ims.excise.dao.ExciseDistrictDao;
import co.th.ims.excise.domain.ExiseDistrict;

public class ExciseDistrictService {
	
	@Autowired
	private ExciseDistrictDao exciseDistrictDao;
	
	public List<ExiseDistrict> findExciseDistrictListByCriteria(ExiseDistrict exciseDistrict){
		return exciseDistrictDao.findByCriteria(exciseDistrict);
	}

}
