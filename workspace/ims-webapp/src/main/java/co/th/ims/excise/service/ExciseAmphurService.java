package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.th.ims.excise.dao.ExciseAmphurDao;
import co.th.ims.excise.domain.ExciseAmphur;

public class ExciseAmphurService {
	
	@Autowired
	private ExciseAmphurDao exciseAmphurDao;
	
	public List<ExciseAmphur> findExciseAmphurListByCriteria(ExciseAmphur exciseAmphur){
		return exciseAmphurDao.findByCriteria(exciseAmphur);
	}

}
