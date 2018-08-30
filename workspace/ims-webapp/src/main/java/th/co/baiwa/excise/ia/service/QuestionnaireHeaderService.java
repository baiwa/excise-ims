package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.repository.QuestionnaireHeaderRepository;

@Service
public class QuestionnaireHeaderService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuestionnaireHeaderDao QuestionnaireHeaderDao;

	@Autowired
	private QuestionnaireHeaderRepository questionnaireHeaderRepository;

	public List<QuestionnaireHeader> findByCriteria(QuestionnaireHeader QuestionnaireHeader) {
		return QuestionnaireHeaderDao.findByCriteria(QuestionnaireHeader);
	}

	public ResponseDataTable<QuestionnaireHeader> findByCriteriaJpa() {
		ResponseDataTable<QuestionnaireHeader> data = new ResponseDataTable<>();
		int count = (int) questionnaireHeaderRepository.count();
		
//		questionnaireHeaderRepository.findAll()
		data.setData(questionnaireHeaderRepository.oderByHeaderId());
		data.setDraw(count);
		data.setLength(count);
		return data;
	}

	public CommonMessage<QuestionnaireHeader> saveQtnHeader(QuestionnaireHeader questionnaireHeader) {
		logger.info(questionnaireHeader.getQtnHeaderName());

		Message msg;
//		String user = UserLoginUtils.getCurrentUsername();

		CommonMessage<QuestionnaireHeader> response = new CommonMessage<>();
//		Date date = new Date();
//
//		qtnMaster.setQtnFinished("N");
//		qtnMaster.setCreatedBy(user);
//		qtnMaster.setCreatedDate(date);
//		qtnMaster.setUpdatedBy(user);
//		qtnMaster.setUpdatedDate(date);

		QuestionnaireHeader data = questionnaireHeaderRepository.save(questionnaireHeader);

		if (data != null) {
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		response.setData(data);
		return response;
	}

	public CommonMessage<QuestionnaireHeader> updateQtnHeader(String id, QuestionnaireHeader questionnaireHeader) {
		Message msg;
		CommonMessage<QuestionnaireHeader> response = new CommonMessage<>();
		
		QuestionnaireHeader dataFindByid = questionnaireHeaderRepository.findOne(Long.parseLong(id));
		dataFindByid.setQtnHeaderName(questionnaireHeader.getQtnHeaderName());
		QuestionnaireHeader data = questionnaireHeaderRepository.save(dataFindByid);
		
		if (data != null) {
			response.setData(data);
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			response.setData(dataFindByid);
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		return response;
	}

	@Transactional
	public Message deleteQtnHeader(String ids) {
		try {
			String[] str = ids.split(",");
			List<Long> id = new ArrayList<>();
			for (String value : str) {
				id.add(Long.valueOf(value));
			}
			questionnaireHeaderRepository.delete(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}

}
