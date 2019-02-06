package co.th.ims.excise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.excise.dao.ExciseProviceDao;
import co.th.ims.excise.domain.ExciseProvice;

@Service
public class ExciseProviceService {

	@Autowired
	private ExciseProviceDao exciseProviceDao;
	
	public List<ExciseProvice> findProviceByCriteria(ExciseProvice exciseProvice){
		return exciseProviceDao.findByCriteria(exciseProvice);
	}
}
