package th.go.excise.ims.mockup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.mockup.persistence.dao.ListOfValueDao;
import th.go.excise.ims.mockup.persistence.entity.ListOfValue;

@Service
public class ListOfValueService {

	
	@Autowired
	private ListOfValueDao listOfValueDao;
	public List<ListOfValue> queryLovByCriteria(ListOfValue lov){
		return listOfValueDao.queryLovByCriteria(lov);
	}
}
