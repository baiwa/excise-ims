
package th.go.excise.ims.ia.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD3Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0601JdbcRepository;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD2DatatableDtlVo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Service
public class Int0601Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0601Service.class);
	
	@Autowired
	private Int0601JdbcRepository int0601JdbcRepository;
	
	@Autowired
	private IaAuditIncHRepository iaAuditIncHRepository;

	@Autowired
	private IaAuditIncD1Repository iaAuditIncD1Repository;
	
	@Autowired
	private IaAuditIncD2Repository iaAuditIncD2Repository;
	
	@Autowired
	private IaAuditIncD3Repository iaAuditIncD3Repository;

	public List<WsIncfri8020Inc> findTab1ByCriteria(Int0601RequestVo int0601Vo){
		logger.info("findByCriterai");
		return int0601JdbcRepository.findByCriteria(int0601Vo);
	}
	
	public IaAuditIncH createIaAuditInc(Int0601SaveVo int0601SaveVo) throws IllegalAccessException, InvocationTargetException {
		logger.info("insert IaAuditIncH");
		IaAuditIncH iaAuditIncH = int0601SaveVo.getIaAuditIncH();
		String auditIncNo = "";
		if (iaAuditIncH != null && iaAuditIncH.getAuditIncSeq() != null) {
			iaAuditIncH = iaAuditIncHRepository.findById(int0601SaveVo.getIaAuditIncH().getAuditIncSeq()).get();
			auditIncNo = iaAuditIncH.getAuditIncNo();
		} else {
			auditIncNo = iaAuditIncH.getOfficeCode() + "/" + iaAuditIncHRepository.generateAuditIncNo();
			iaAuditIncH.setAuditIncNo(auditIncNo);
			iaAuditIncH = iaAuditIncHRepository.save(iaAuditIncH);
		}
		if (iaAuditIncH.getAuditIncSeq() != null) {
			logger.info("insert IaAuditIncH Completed ");
			if (int0601SaveVo.getIaAuditIncD1List() != null && int0601SaveVo.getIaAuditIncD1List().size() > 0) {
				logger.info("insert Drtail : 1 ");
				List<IaAuditIncD1> entitySaveList = new ArrayList<>();
				List<IaAuditIncD1> entityUpdateList = new ArrayList<>();
				IaAuditIncD1 d1 = null;
				for (IaAuditIncD1Vo vo : int0601SaveVo.getIaAuditIncD1List()) {
					d1 = new IaAuditIncD1();
					d1.setIaAuditIncDId(vo.getIaAuditIncDId());
					d1.setAuditIncNo(vo.getAuditIncNo());
					d1.setOfficeCode(vo.getOfficeCode());
					d1.setDocCtlNo(vo.getDocCtlNo());
					d1.setReceiptNo(vo.getReceiptNo());
					d1.setRunCheck(vo.getRunCheck());
					d1.setReceiptDate(ConvertDateUtils.parseStringToDate(vo.getReceiptDate(), ConvertDateUtils.YYYY_MM_DD , ConvertDateUtils.LOCAL_TH));
					d1.setTaxName(vo.getTaxName());
					d1.setTaxCode(vo.getTaxCode());
					d1.setAmount(vo.getAmount());
					d1.setRemark(vo.getRemark());
					if(d1.getIaAuditIncDId() == null) {
						entitySaveList.add(d1);
					}else {
						entityUpdateList.add(d1);
					}
				}
				iaAuditIncD1Repository.batchInsert(entitySaveList, auditIncNo);
			}
			if (int0601SaveVo.getIaAuditIncD2List() != null && int0601SaveVo.getIaAuditIncD2List().size() > 0) {
				logger.info("insert Drtail : 2 ");
				iaAuditIncD2Repository.batchInsert(int0601SaveVo.getIaAuditIncD2List());
			}
			if (int0601SaveVo.getIaAuditIncD3List() != null && int0601SaveVo.getIaAuditIncD3List().size() > 0) {
				logger.info("insert Drtail : 3 ");
				iaAuditIncD3Repository.batchInsert(int0601SaveVo.getIaAuditIncD3List());
			}
		} else {
			logger.info("insert IaAuditIncH incomplet ");
		}

		return iaAuditIncH;
	}
	
	public List<IaAuditIncH> findAllIaAuditIncH(){
		return iaAuditIncHRepository.findByIsDeletedOrderByAuditIncNoAsc(FLAG.N_FLAG);
	}
	
	public List<IaAuditIncD2Vo> findIaAuditIncD2ByCriteria(Int0601RequestVo criteria){
		return int0601JdbcRepository.findDataTab2(criteria);
	}
	
	public List<IaAuditIncD1> findIaAuditIncD1ByAuditIncNo(String  auditIncNo){
		return iaAuditIncD1Repository.findByAuditIncNoOrderByReceiptNo(auditIncNo);
	} 
	
	public List<IaAuditIncD3> findIaAuditIncD3ByCriteria(Int0601RequestVo criteria){
		return int0601JdbcRepository.findDataTab3(criteria);
	}
	public IaAuditIncD2DatatableDtlVo findTab2Dtl(Int0601RequestVo criteria){
		IaAuditIncD2DatatableDtlVo iaAuditIncD2DatatableDtlVo = new IaAuditIncD2DatatableDtlVo();
		List<WsIncfri8020Inc> wsIncfri8020IncList = int0601JdbcRepository.findByCriteria(criteria);
		BigDecimal sumAmt = BigDecimal.ZERO;
		for (WsIncfri8020Inc wsIncfri8020Inc : wsIncfri8020IncList) {
			sumAmt.add(wsIncfri8020Inc.getNetLocAmt());
		}
		iaAuditIncD2DatatableDtlVo.setWsIncfri8020Inc(wsIncfri8020IncList);
		iaAuditIncD2DatatableDtlVo.setSumAmt(sumAmt);
		return iaAuditIncD2DatatableDtlVo;
	}
	
	
	
	
	
}
