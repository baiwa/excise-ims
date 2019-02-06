package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseDistrictDao;
import co.th.ims.excise.domain.ExciseDistrict;

@Service
public class ExciseDistrictService {
	
	@Autowired
	private ExciseDistrictDao exciseDistrictDao;
	
	public List<ExciseDistrict> findExciseDistrictListByCriteria(ExciseDistrict exciseDistrict){
		return exciseDistrictDao.findByCriteria(exciseDistrict);
	}

}
