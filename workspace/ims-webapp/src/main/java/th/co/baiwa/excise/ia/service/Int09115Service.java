package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09115FormVo;

@Service
public class Int09115Service {
	private static Logger log = LoggerFactory.getLogger(Int09115Service.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;


	public void add(Int09115FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentType("บันทึกข้อความ"); 		 		
		formVo.setSubject("ขออนุมัติเดินทางไปราชการ");
		iaTravelEstimatorDao.addDocument(formVo.getIdProcess(),formVo.getCreatedBy(),formVo.getDocumentType(),formVo.getSubject());
	}
	
}
