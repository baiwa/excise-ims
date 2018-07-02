package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.SEQDao;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.utils.NumberUtils;

 
@Service
public class QtnReportHeaderService {
	
	
	@Autowired
	private QtnReportHeaderDao qtnReportHeaderDao;
	
	@Autowired
	private SEQDao seqDao;
	
	public List<QtnReportHeader> findByCriteria(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.findByCriteria(qtnReportHeader);
	}
	
	public Integer createQtnReportHeader(QtnReportHeader qtnReportHeader) {
//		String reportHeaderCode = NumberUtils.toStringFormat(seqDao.autoNumberRunningBySeqName("IA_QTN_HEADER_CODE_SEQ").longValue(), "H0000");
		qtnReportHeader.setCreator(UserLoginUtils.getCurrentUsername());
		qtnReportHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
		qtnReportHeader.setCreatedDatetime(new Date());
		return qtnReportHeaderDao.createQtnReportHeader(qtnReportHeader);
	}
	
	
	
}
