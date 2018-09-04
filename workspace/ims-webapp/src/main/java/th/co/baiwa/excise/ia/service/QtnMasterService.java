package th.co.baiwa.excise.ia.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.repository.QtnMasterRepository;

@Service
public class QtnMasterService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QtnMasterRepository qtnMasterRepository;
	
	public ResponseDataTable<QtnMaster> findAllQtnMaster() {
		ResponseDataTable<QtnMaster> data = new ResponseDataTable<>();
		int count = (int) qtnMasterRepository.count();
		data.setData(qtnMasterRepository.findAll());
		data.setDraw(count);
		data.setLength(count);
		return data;
	}
	
	public QtnMaster findByIdQtnMaster(String id) {
		return qtnMasterRepository.findOne(Long.parseLong(id));
	}
	
	public CommonMessage<QtnMaster> updateQtnMaster(String id, @RequestBody QtnMaster qtnMaster) {
		Message msg;
		CommonMessage<QtnMaster> response = new CommonMessage<>();
		
		// QtnMaster
		QtnMaster qtnMasterOne = qtnMasterRepository.findOne(Long.parseLong(id));
		qtnMasterOne.setQtnFinished(qtnMaster.getQtnFinished());
		qtnMasterOne.setIsDeleted(qtnMaster.getIsDeleted());
		
		QtnMaster data = qtnMasterRepository.save(qtnMasterOne);
		if (data != null) {
			response.setData(data);
			if (data.getQtnFinished().equals("Y"))
				msg = ApplicationCache.getMessage("MSG_00007");
			else
				msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			response.setData(qtnMasterOne);
			if (qtnMasterOne.getQtnFinished().equals("Y"))
				msg = ApplicationCache.getMessage("MSG_00008");
			else 
				msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		return response;
	}
	
	public CommonMessage<QtnMaster> saveQtnMaster(QtnMaster qtnMaster) {
		
		logger.info(qtnMaster.getQtnName());
		
		Message msg;
		String user = UserLoginUtils.getCurrentUsername();
		
		CommonMessage<QtnMaster> response = new CommonMessage<>();
		Date date = new Date();
		
		qtnMaster.setQtnFinished("N");
		qtnMaster.setCreatedBy(user);
		qtnMaster.setCreatedDate(date);
		qtnMaster.setUpdatedBy(user);
		qtnMaster.setUpdatedDate(date);
		
		QtnMaster data = qtnMasterRepository.save(qtnMaster);
		
		if (data != null) {
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		response.setData(data);
		return response;
	}
	
	public Message deleteQtnMaster(String id) {
		try {
			qtnMasterRepository.delete(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}
	
}
