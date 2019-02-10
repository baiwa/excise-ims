package th.go.excise.ims.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseProvinceDao;
import th.go.excise.ims.common.domain.ExciseProvince;

@Service
public class ExciseProvinceService {

	@Autowired
	private ExciseProvinceDao exciseProvinceDao;
	
	public List<ExciseProvince> findProviceByCriteria(ExciseProvince exciseProvince){
		return exciseProvinceDao.findByCriteria(exciseProvince);
	}
}
