package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09113FormVo;

@Service
public class Int09113Service {
	private static Logger log = LoggerFactory.getLogger(Int09113Service.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;


	public void add(Int09113FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentType("หลักฐานการจ่ายเงิน");				
		formVo.setSubject("หลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ");
		iaTravelEstimatorDao.addDocument(formVo.getIdProcess(),formVo.getCreatedBy(),formVo.getDocumentType(),formVo.getSubject());
	}
	
}
