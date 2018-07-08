package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
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
	
	public Integer createQtnReportHeader(QtnReportHeader qtnReportHeader) {
//		String reportHeaderCode = NumberUtils.toStringFormat(seqDao.autoNumberRunningBySeqName("IA_QTN_HEADER_CODE_SEQ").longValue(), "H0000");
		qtnReportHeader.setCreator(UserLoginUtils.getCurrentUsername());
		qtnReportHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
		qtnReportHeader.setCreatedDate(new Date());
		return qtnReportHeaderDao.createQtnReportHeader(qtnReportHeader);
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
