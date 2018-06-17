package th.go.excise.ims.mockup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.mockup.persistence.dao.LovDao;
import th.go.excise.ims.mockup.persistence.entity.sys.Lov;

@Service
public class ListOfValueService {

	
	@Autowired
	private LovDao listOfValueDao;
	public List<Lov> queryLovByCriteria(Lov lov){
		return listOfValueDao.queryLovByCriteria(lov);
	}
}
