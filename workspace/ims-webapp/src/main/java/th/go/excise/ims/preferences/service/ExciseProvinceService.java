package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.dao.ExciseProvinceDao;
import th.go.excise.ims.preferences.domain.ExciseProvince;

@Service
public class ExciseProvinceService {

	@Autowired
	private ExciseProvinceDao exciseProvinceDao;
	
	public List<ExciseProvince> findProviceByCriteria(ExciseProvince exciseProvince){
		return exciseProvinceDao.findByCriteria(exciseProvince);
	}
}
