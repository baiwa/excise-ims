package th.go.excise.ims.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseSectorDao;
import th.go.excise.ims.common.domain.ExciseSector;

@Service
public class ExciseSectorService {

	@Autowired
	private ExciseSectorDao exciseSectorDao;
	
	public List<ExciseSector> findAllExciseSector(){
		return exciseSectorDao.findAll();
	}
}
