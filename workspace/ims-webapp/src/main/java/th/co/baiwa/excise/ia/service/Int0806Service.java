package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.persistence.dao.Int0806Dao;

@Service
public class Int0806Service {
	@Autowired
	private Int0806Dao int0806Dao;
	
	public List<Lov> getValue1(Lov en) {	
		return int0806Dao.getValue1(en);
	}
	
}
