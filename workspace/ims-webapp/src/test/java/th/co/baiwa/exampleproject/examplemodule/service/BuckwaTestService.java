package th.co.baiwa.exampleproject.examplemodule.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.exampleproject.examplemodule.persistence.dao.BuckwaTestDao;
import th.co.baiwa.exampleproject.examplemodule.persistence.entity.BuckwaTest;

@Service
public class BuckwaTestService {
	
	@Autowired
	private BuckwaTestDao buckwaTestDao;
	
	public List<BuckwaTest> getAll() {
		return buckwaTestDao.findAllWithBeanProperty();
	}
	
	public List<BuckwaTest> getAllWithBeanProperty() {
		return buckwaTestDao.findAllWithBeanProperty();
	}
	
	public int count() {
		return buckwaTestDao.count();
	}
	
	public int insert(BuckwaTest buckwaTest) {
		return buckwaTestDao.insert(buckwaTest);
	}
	
	@Transactional
	public int insertTransactional(BuckwaTest buckwaTest) throws Exception {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			buckwaTest.setColVarchar(buckwaTest.getColVarchar() + (i + 1));
			buckwaTestDao.insert(buckwaTest);
			if (i == 2) {
				throw new Exception("Bomb!!");
			}
		}
		return result;
	}
	
	public int update(BuckwaTest buckwaTest) {
		return buckwaTestDao.update(buckwaTest);
	}
	
	@Transactional
	public int[][] insertList(List<BuckwaTest> buckwaTestList, int batchSize) throws SQLException {
		return buckwaTestDao.batchInsert(buckwaTestList, batchSize);
	}
	
}
