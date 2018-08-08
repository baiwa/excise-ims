package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
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
	
	public CommonMessage<QtnMaster> saveQtnMaster(QtnMaster qtnMaster) {
		logger.info(qtnMaster.getQtnName());
		Message msg;
		CommonMessage<QtnMaster> response = new CommonMessage<>();
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
