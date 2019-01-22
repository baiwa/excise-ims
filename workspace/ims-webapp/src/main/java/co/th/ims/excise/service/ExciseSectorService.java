package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseSectorDao;
import co.th.ims.excise.domain.ExciseSector;

@Service
public class ExciseSectorService {

	@Autowired
	private ExciseSectorDao exciseSectorDao;
	
	public List<ExciseSector> findAllExciseSector(){
		return exciseSectorDao.findAll();
	}
}
