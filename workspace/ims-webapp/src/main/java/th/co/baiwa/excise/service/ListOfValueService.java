package th.co.baiwa.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.persistence.dao.LovDao;
import th.co.baiwa.excise.persistence.entity.sys.Lov;

@Service
public class ListOfValueService {

	
	@Autowired
	private LovDao listOfValueDao;
	public List<Lov> queryLovByCriteria(Lov lov, String oderby){
		return listOfValueDao.queryLovByCriteria(lov, oderby);
	}
	
	public List<String> queryLovTypeList() {
		return listOfValueDao.queryLovTypeList();
	}
}
