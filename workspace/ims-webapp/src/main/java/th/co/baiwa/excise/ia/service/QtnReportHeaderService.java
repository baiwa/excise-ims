package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;

 
@Service
public class QtnReportHeaderService {
	
	
	@Autowired
	private QtnReportHeaderDao qtnReportHeaderDao;
	
	
	
	public List<QtnReportHeader> findByCriteria(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.findByCriteria(qtnReportHeader);
	}
	
	public Message createQtnReportHeader(QtnReportHeader qtnReportHeader) {
		List<QtnReportHeader> qtnReportHeaderList = qtnReportHeaderDao.findByCriteria(qtnReportHeader);	
		Message message;
		if(qtnReportHeaderList != null  || qtnReportHeaderList.size() == 0) {
			qtnReportHeader.setCreator(UserLoginUtils.getCurrentUsername());
			qtnReportHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
			qtnReportHeader.setCreatedDate(new Date());
			if(qtnReportHeaderDao.createQtnReportHeader(qtnReportHeader) > 0) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}
	
	
	public ResponseDataTable<QtnReportHeader> findByCriteriaForDatatable(QtnReportHeader qtnReportHeader , DataTableRequest dataTableRequest) {
		
		ResponseDataTable<QtnReportHeader> responseDataTable = new ResponseDataTable<QtnReportHeader>();
		List<QtnReportHeader> qtnReportHeaderList = qtnReportHeaderDao.findByCriteriaDataTable(qtnReportHeader, dataTableRequest.getStart().intValue(), dataTableRequest.getLength().intValue());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		long count = qtnReportHeaderDao.countQtnReportHeader(qtnReportHeader);
		responseDataTable.setData(qtnReportHeaderList);
		responseDataTable.setRecordsTotal((int) count);
		responseDataTable.setRecordsFiltered((int) count);
		return responseDataTable;
				
				
	}
	public int deleteQtnReportHeader(QtnReportHeader qtnReportHeader ) {
		return qtnReportHeaderDao.deleteQtnReportHeader(qtnReportHeader);
	}
	
	
	
}
