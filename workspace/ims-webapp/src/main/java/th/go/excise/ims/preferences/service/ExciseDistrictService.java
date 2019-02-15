package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.dao.ExciseDistrictDao;
import th.go.excise.ims.preferences.domain.ExciseDistrict;

@Service
public class ExciseDistrictService {
	
	@Autowired
	private ExciseDistrictDao exciseDistrictDao;
	
	public List<ExciseDistrict> findExciseDistrictListByCriteria(ExciseDistrict exciseDistrict){
		return exciseDistrictDao.findByCriteria(exciseDistrict);
	}

}
