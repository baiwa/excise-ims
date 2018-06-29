package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.dao.QtnReportHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;

 
@Service
public class QtnReportHeaderService {
	
	
	@Autowired
	private QtnReportHeaderDao qtnReportHeaderDao;
	
	public List<QtnReportHeader> findByCriteria(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.findByCriteria(qtnReportHeader);
	}
	
	public Integer CreateQtnReportHeader(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.CreateQtnReportHeader(qtnReportHeader);
	}
	
}
