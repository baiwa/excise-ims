package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;
import th.go.excise.ims.ia.persistence.repository.IaEstimateExpHRepository;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;
import th.go.excise.ims.ia.vo.IaAuditIncHVo;
import th.go.excise.ims.ia.vo.IaEstimateExpHVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501SaveVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Service
public class Int0501Service {
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	@Autowired
	private IaEstimateExpHRepository iaEstimateExpHRepository;
	
	public List<Int0501Vo> listPerson(Int0501FormVo form) {
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		personList = excisePersonJdbcRepository.listPerson(form);
		return personList;
	}
	
	public IaEstimateExpHVo saveIaEstimateExp(Int0501SaveVo vo) {

		IaEstimateExpH estimateExpH = null;
		try {
			if (StringUtils.isNotBlank(vo.getIaEstimateExpHVo().getEstExpNo())) {
				estimateExpH = new IaEstimateExpH();
				estimateExpH = iaEstimateExpHRepository.findByEstExpNo(vo.getIaEstimateExpHVo().getEstExpNo());
				estimateExpH.setPersonResp(vo.getIaEstimateExpHVo().getPersonResp());
				BigDecimal respDeptCode = new BigDecimal(vo.getIaEstimateExpHVo().getRespDeptCode());
				estimateExpH.setRespDeptCode(respDeptCode);
				Date expReqDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getExpReqDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setExpReqDate(expReqDate);
				
				estimateExpH = iaEstimateExpHRepository.save(estimateExpH);
//				vo.getIaAuditIncH().setAuditIncSeq(auditIncH.getAuditIncSeq());
//				vo.getIaAuditIncH().setAuditIncNo(auditIncH.getAuditIncNo());
			} else {
//				auditIncH = new IaAuditIncH();
//				auditIncH.setOfficeCode(vo.getIaAuditIncH().getOfficeCode());
//				auditIncH.setReceiptDateFrom(ConvertDateUtils.parseStringToDate(vo.getIaAuditIncH().getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//				auditIncH.setReceiptDateTo(ConvertDateUtils.parseStringToDate(vo.getIaAuditIncH().getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//				auditIncH.setAuditIncNo(iaCommonService.autoGetRunAuditNoBySeqName("INC", vo.getIaAuditIncH().getOfficeCode(), "AUDIT_INC_NO_SEQ", 8));
//				auditIncH.setD1AuditFlag(vo.getIaAuditIncH().getD1AuditFlag());
//				auditIncH.setD1ConditionText(vo.getIaAuditIncH().getD1ConditionText());
//				auditIncH.setD1CriteriaText(vo.getIaAuditIncH().getD1CriteriaText());
//				auditIncH.setD2ConditionText(vo.getIaAuditIncH().getD2ConditionText());
//				auditIncH.setD2CriteriaText(vo.getIaAuditIncH().getD2CriteriaText());
//				auditIncH.setD3ConditionText(vo.getIaAuditIncH().getD3ConditionText());
//				auditIncH.setD3CriteriaText(vo.getIaAuditIncH().getD3CriteriaText());
//				auditIncH.setD4ConditionText(vo.getIaAuditIncH().getD4ConditionText());
//				auditIncH.setD4CriteriaText(vo.getIaAuditIncH().getD4CriteriaText());
//				auditIncH = iaAuditIncHRepository.save(auditIncH);
//				vo.getIaAuditIncH().setAuditIncSeq(auditIncH.getAuditIncSeq());
//				vo.getIaAuditIncH().setAuditIncNo(auditIncH.getAuditIncNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		// D1
//		if (vo.getIaAuditIncD1List() != null && vo.getIaAuditIncD1List().size() > 0) {
//			IaAuditIncD1 val1 = null;
//			List<IaAuditIncD1> iaAuditIncD1List = new ArrayList<>();
//			for (IaAuditIncD1Vo data1 : vo.getIaAuditIncD1List()) {
//				val1 = new IaAuditIncD1();
//				if (data1.getIaAuditIncDId() != null) {
//					val1 = iaAuditIncD1Repository.findById(data1.getIaAuditIncDId()).get();
//					try {
//						val1.setRunCheck(data1.getRunCheck());
//						val1.setRemark(data1.getRemark());
//						val1.setCheckTax0307(data1.getCheckTax0307());
//						val1.setCheckStamp(data1.getCheckStamp());
//						val1.setCheckTax0704(data1.getCheckTax0704());
//						val1.setRemarkTax(data1.getRemarkTax());
//						val1 = iaAuditIncD1Repository.save(val1);
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//				} else {
//					try {
//						val1.setAuditIncNo(auditIncH.getAuditIncNo());
//						val1.setOfficeCode(data1.getOfficeCode());
//						val1.setDocCtlNo(data1.getDocCtlNo());
//						val1.setReceiptNo(data1.getReceiptNo());
//						val1.setRunCheck(data1.getRunCheck());
//						val1.setReceiptDate(ConvertDateUtils.parseStringToDate(data1.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//						val1.setTaxName(data1.getTaxName());
//						val1.setTaxCode(data1.getTaxCode());
//						val1.setAmount(data1.getAmount());
//						val1.setRemark(data1.getRemark());
//						val1.setCheckTax0307(data1.getCheckTax0307());
//						val1.setCheckStamp(data1.getCheckStamp());
//						val1.setCheckTax0704(data1.getCheckTax0704());
//						val1.setRemarkTax(data1.getRemarkTax());
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//
//					iaAuditIncD1List.add(val1);
//				}
//			}
//			iaAuditIncD1Repository.saveAll(iaAuditIncD1List);
//		}
//		// D2
//		if (vo.getIaAuditIncD2List() != null && vo.getIaAuditIncD2List().size() > 0) {
//			IaAuditIncD2 val2 = null;
//			List<IaAuditIncD2> iaAuditIncD2List = new ArrayList<>();
//			for (IaAuditIncD2Vo data2 : vo.getIaAuditIncD2List()) {
//				val2 = new IaAuditIncD2();
//				if (data2.getIaAuditIncD2Id() != null) {
//					val2 = iaAuditIncD2Repository.findById(data2.getIaAuditIncD2Id()).get();
//					try {
//						val2.setAuditCheck(data2.getAuditCheck());
//						val2.setRemark(data2.getRemark());
//						val2 = iaAuditIncD2Repository.save(val2);
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//				} else {
//					try {
//						val2.setAuditIncNo(auditIncH.getAuditIncNo());
//						val2.setReceiptDate(ConvertDateUtils.parseStringToDate(data2.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//						val2.setAmount(data2.getAmount());
//						val2.setPrintPerDay(data2.getPrintPerDay());
//						val2.setAuditCheck(data2.getAuditCheck());
//						val2.setRemark(data2.getRemark());
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//
//					iaAuditIncD2List.add(val2);
//				}
//			}
//			iaAuditIncD2Repository.saveAll(iaAuditIncD2List);
//		}
//
//		// D3
//		if (vo.getIaAuditIncD3List() != null && vo.getIaAuditIncD3List().size() > 0) {
//			IaAuditIncD3 val3 = null;
//			List<IaAuditIncD3> iaAuditIncD3List = new ArrayList<>();
//			for (IaAuditIncD3Vo data3 : vo.getIaAuditIncD3List()) {
//				val3 = new IaAuditIncD3();
//				if (data3.getIaAuditIncD3Id() != null) {
//					val3 = iaAuditIncD3Repository.findById(data3.getIaAuditIncD3Id()).get();
//					try {
//						val3.setAuditCheck(data3.getAuditCheck());
//						val3.setRemark(data3.getRemark());
//						val3 = iaAuditIncD3Repository.save(val3);
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//				} else {
//					try {
//						val3.setAuditIncNo(auditIncH.getAuditIncNo());
//						val3.setTaxCode(data3.getTaxCode());
//						val3.setTaxName(data3.getTaxName());
//						val3.setAmount(data3.getAmount());
//						val3.setCountReceipt(data3.getCountReceipt());
//						val3.setAuditCheck(data3.getAuditCheck());
//						val3.setRemark(data3.getRemark());
//
//					} catch (Exception e) {
//						e.printStackTrace();
//						logger.error(e.getMessage());
//					}
//
//					iaAuditIncD3List.add(val3);
//				}
//			}
//			iaAuditIncD3Repository.saveAll(iaAuditIncD3List);
//		}
		return vo.getIaEstimateExpHVo();
	}


}
