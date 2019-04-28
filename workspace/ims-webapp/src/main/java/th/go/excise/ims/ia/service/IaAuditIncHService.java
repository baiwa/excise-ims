package th.go.excise.ims.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncHRepository;
import th.go.excise.ims.ia.vo.Int0601Vo;

@Service
public class IaAuditIncHService {

	private Logger logger = LoggerFactory.getLogger(IaAuditIncHService.class);

	@Autowired
	private IaAuditIncHRepository iaAuditIncHRepository;

	@Autowired
	private IaAuditIncD1Repository iaAuditIncD1Repository;

	@Autowired
	private IaAuditIncD2Repository iaAuditIncD2Repository;

	public IaAuditIncH createIaAuditInc(Int0601Vo vo) {
		logger.info("insert IaAuditIncH");
		IaAuditIncH iaAuditIncH = vo.getIaAuditIncH();
		String auditIncNo = "";
		if (iaAuditIncH != null && iaAuditIncH.getAuditIncSeq() != null) {

			iaAuditIncH = iaAuditIncHRepository.findById(vo.getIaAuditIncH().getAuditIncSeq()).get();
			auditIncNo = iaAuditIncH.getAuditIncNo();
		} else {
			auditIncNo = iaAuditIncH.getOfficeCode() + "/" + iaAuditIncHRepository.generateAuditIncNo();
			iaAuditIncH.setAuditIncNo(auditIncNo);
			iaAuditIncH = iaAuditIncHRepository.save(iaAuditIncH);
		}
		if (iaAuditIncH.getAuditIncSeq() != null) {
			logger.info("insert IaAuditIncH Completed ");
			if (vo.getIaAuditIncD1List() != null && vo.getIaAuditIncD1List().size() > 0) {
				logger.info("insert Drtail : 1 ");
				iaAuditIncD1Repository.batchInsert(vo.getIaAuditIncD1List(), auditIncNo);
			}
			if (vo.getIaAuditIncD2List() != null && vo.getIaAuditIncD2List().size() > 0) {
				logger.info("insert Drtail : 2 ");
				iaAuditIncD2Repository.batchInsert(vo.getIaAuditIncD2List());
			}
		} else {
			logger.info("insert IaAuditIncH incomplet ");
		}

		return iaAuditIncH;
	}

}
