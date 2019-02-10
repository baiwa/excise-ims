package th.go.excise.ims.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseDistrictDao;
import th.go.excise.ims.common.domain.ExciseDistrict;

@Service
public class ExciseDistrictService {
	
	@Autowired
	private ExciseDistrictDao exciseDistrictDao;
	
	public List<ExciseDistrict> findExciseDistrictListByCriteria(ExciseDistrict exciseDistrict){
		return exciseDistrictDao.findByCriteria(exciseDistrict);
	}

}
