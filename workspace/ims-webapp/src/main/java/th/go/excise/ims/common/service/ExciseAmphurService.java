package th.go.excise.ims.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.dao.ExciseAmphurDao;
import th.go.excise.ims.common.domain.ExciseAmphur;

@Service
public class ExciseAmphurService {
	
	@Autowired
	private ExciseAmphurDao exciseAmphurDao;
	
	public List<ExciseAmphur> findExciseAmphurListByCriteria(ExciseAmphur exciseAmphur){
		return exciseAmphurDao.findByCriteria(exciseAmphur);
	}

}
