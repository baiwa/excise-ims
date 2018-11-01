package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09112FormVo;

@Service
public class Int09112Service {
	private static Logger log = LoggerFactory.getLogger(Int09112Service.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;


	public void add(Int09112FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentType("สัญญาการยืมเงิน"); 		 		
		formVo.setSubject("สัญญาการยืมเงิน");
//		iaTravelEstimatorDao.addDocument(formVo.getIdProcess(),formVo.getCreatedBy(),formVo.getDocumentType(),formVo.getSubject());
	}
	
}
