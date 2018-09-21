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
import th.co.baiwa.excise.domain.CommonManageReq;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.controller.Int08Controller;
import th.co.baiwa.excise.ia.persistence.dao.QtnReportHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.repository.qtn.rep.QtnReportHeaderRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int022Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class QtnReportHeaderService {

	@Autowired
	private QtnReportHeaderDao qtnReportHeaderDao;

	@Autowired
	private QtnReportHeaderRepository qtnReportHeaderRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Int022Vo> findByCriteria(QtnReportHeader qtnReportHeader) {
		return qtnReportHeaderDao.findByCriteria(qtnReportHeader);
	}

	public Message createQtnReportHeader(QtnReportHeader qtnReportHeader) {
		List<Int022Vo> qtnReportHeaderList = qtnReportHeaderDao.findByCriteria(qtnReportHeader);
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

	public Message saveQtnReportHeader(CommonManageReq<QtnReportHeader> req) {
		Message msg = ApplicationCache.getMessage("MSG_00003");
		if (req.getSave().size() != 0) {
			try {
				List<QtnReportHeader> li = new ArrayList<>(); // List For Save
				for(QtnReportHeader qtn: req.getSave()) {
					if (BeanUtils.isEmpty(qtn.getQtnReportHdrId())) {
						li.add(qtn);
					} else {
						li.add(qtnReportHeaderRepository.findOne(qtn.getQtnReportHdrId()));
					}
				}
				qtnReportHeaderRepository.save(li);
				msg = ApplicationCache.getMessage("MSG_00002");
			} catch (Exception e) {
				e.printStackTrace();
				return msg;
			}
		}
		if (req.getDelete().size() != 0) {
			try {
				List<Long> li = new ArrayList<>(); // List For Delete
				for(QtnReportHeader qtn: req.getDelete()) {
					li.add(qtn.getQtnReportHdrId());
				}
				qtnReportHeaderRepository.delete(li);
				msg = ApplicationCache.getMessage("MSG_00002");
			} catch (Exception e) {
				e.printStackTrace();
				return msg;
			}
		}
		return msg;
	}

	public Message saveQtnReportHeader(List<QtnReportHeader> qtnReportHeader) {
		Message msg;
		for (QtnReportHeader qtn : qtnReportHeader) {
			qtn.setCreator(UserLoginUtils.getCurrentUsername());
			qtn.setCreatedBy(UserLoginUtils.getCurrentUsername());
		}
		if (qtnReportHeaderRepository.save(qtnReportHeader) != null) {
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		return msg;
	}

	public Message saveQtnReportHeader(QtnReportHeader qtnReportHeader) {
		Message msg;
		List<Int022Vo> qtn = qtnReportHeaderDao.findByCriteria(qtnReportHeader);
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

	public ResponseDataTable<Int022Vo> findForNullDatatable(DataTableRequest dataTableRequest) {
		ResponseDataTable<Int022Vo> response = new ResponseDataTable<Int022Vo>();
		response.setData(new ArrayList<Int022Vo>());
		response.setStart(dataTableRequest.getStart().intValue());
		response.setDraw(dataTableRequest.getDraw().intValue() + 1);
		response.setLength(0);
		response.setRecordsFiltered(0);
		response.setRecordsTotal(0);
		return response;
	}

	public ResponseDataTable<Int022Vo> findByMasterIdForDatatable(QtnReportHeader qtnReportHeader,
			DataTableRequest dataTableRequest) {
		ResponseDataTable<Int022Vo> responseDataTable = new ResponseDataTable<Int022Vo>();
		List<Int022Vo> qtnReportHeaderList = qtnReportHeaderDao.findByCriteriaDataTable(qtnReportHeader,
				dataTableRequest.getStart().intValue(), dataTableRequest.getLength().intValue());
		long count = qtnReportHeaderDao.countQtnReportHeader(qtnReportHeader);
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setStart(dataTableRequest.getStart().intValue());
		responseDataTable.setData(qtnReportHeaderList);
		responseDataTable.setLength((int) count);
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
