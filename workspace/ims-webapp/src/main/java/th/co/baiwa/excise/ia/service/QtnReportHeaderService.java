package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.controller.Int08Controller;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.repository.QtnReportHeaderRepository;

@Service
public class QtnReportHeaderService {

	@Autowired
	private QtnReportHeaderDao qtnReportHeaderDao;

	@Autowired
	private QtnReportHeaderRepository qtnReportHeaderRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<QtnReportHeader> findByCriteria(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.findByCriteria(qtnReportHeader);
	}

	public Message createQtnReportHeader(QtnReportHeader qtnReportHeader) {
		List<QtnReportHeader> qtnReportHeaderList = qtnReportHeaderDao.findByCriteria(qtnReportHeader);
		Message message;
		if (qtnReportHeaderList != null || qtnReportHeaderList.size() == 0) {
			qtnReportHeader.setCreator(UserLoginUtils.getCurrentUsername());
			qtnReportHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
			qtnReportHeader.setCreatedDate(new Date());
			if (qtnReportHeaderDao.createQtnReportHeader(qtnReportHeader) > 0) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}

	public Message saveQtnReportHeader(QtnReportHeader qtnReportHeader) {
		Message msg;
		List<QtnReportHeader> qtn = qtnReportHeaderDao.findByCriteria(qtnReportHeader);
		if (qtn != null || qtn.size() == 0) {
			qtnReportHeader.setCreator(UserLoginUtils.getCurrentUsername());
			qtnReportHeader.setCreatedBy(UserLoginUtils.getCurrentUsername());
			if (qtnReportHeaderRepository.save(qtnReportHeader) != null) {
				msg = ApplicationCache.getMessage("MSG_00002");
			} else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			msg = ApplicationCache.getMessage("MSG_00004");
		}
		return msg;
	}

	public ResponseDataTable<QtnReportHeader> findByCriteriaForDatatable(QtnReportHeader qtnReportHeader,
			DataTableRequest dataTableRequest) {

		ResponseDataTable<QtnReportHeader> responseDataTable = new ResponseDataTable<QtnReportHeader>();
		List<QtnReportHeader> qtnReportHeaderList = qtnReportHeaderDao.findByCriteriaDataTable(qtnReportHeader,
				dataTableRequest.getStart().intValue(), dataTableRequest.getLength().intValue());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		long count = qtnReportHeaderDao.countQtnReportHeader(qtnReportHeader);
		responseDataTable.setData(qtnReportHeaderList);
		responseDataTable.setRecordsTotal((int) count);
		responseDataTable.setRecordsFiltered((int) count);
		return responseDataTable;

	}

	public int deleteQtnReportHeader(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.deleteQtnReportHeader(qtnReportHeader);
	}

	public Message deleteQtnReportHeader(String id) {
		try {
			String[] str = id.split(",");
			List<Long> delValueList = new ArrayList<>();
			for (String string : str) {
				delValueList.add(Long.parseLong(string));
			}
			logger.info("length : {}", str.length);

			qtnReportHeaderRepository.delete(delValueList);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ApplicationCache.getMessage("MSG_00006");
		}

		return ApplicationCache.getMessage("MSG_00005");

	}

}
