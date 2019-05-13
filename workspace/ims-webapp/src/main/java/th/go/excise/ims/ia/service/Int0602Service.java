package th.go.excise.ims.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.Int0602JdbcRepository;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Service
public class Int0602Service {
	private static final Logger logger = LoggerFactory.getLogger(Int0602Service.class);
	
	@Autowired 
	private Int0602JdbcRepository int0602JdbcRepository;
	public List<WsLicfri6010> findByCriteria(Int0602FormVo int0602FormVo) {
		logger.info("findByCriterai");
		return int0602JdbcRepository.findByCriteria(int0602FormVo);
	}
}
