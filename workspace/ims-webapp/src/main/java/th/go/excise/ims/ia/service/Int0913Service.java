package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.persistence.repository.IaUtilityBillRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0913JdbcRepository;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SaveVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Service
public class Int0913Service {
	
	@Autowired
	private Int0913JdbcRepository int0913JdbcRepository;
	
	@Autowired
	private IaUtilityBillRepository iaUtilityBillRepository;
	
	public List<Int091301ResultSearchVo> findIaUtilityBill(Int091301SearchVo int091301SearchVo){
		
		return int0913JdbcRepository.findIaUtilityBillByCriteria(int091301SearchVo);
	}
	
	public IaUtilityBill saveIaUtilityBill(Int091301SaveVo vo) {
		IaUtilityBill entity = null;
		if(vo.getUtilityBillSeq() != null ) {
			entity = iaUtilityBillRepository.findById(vo.getUtilityBillSeq()).get();
		}else {
			entity = new IaUtilityBill();
		}
		entity.setExciseCode(vo.getExciseCode());
		entity.setUbillType(vo.getUbillType());
		entity.setMonthWdPay(vo.getMonthWdPay());
		entity.setInvoiceSeq(vo.getInvoiceSeq());
		entity.setInvoiceMonth(vo.getInvoiceMonth());
		entity.setInvoiceNo(vo.getInvoiceNo());
		entity.setTelInvNumber(vo.getTelInvNumber());
		entity.setInvoiceDate(ConvertDateUtils.parseStringToDate(vo.getInvoiceDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH) );
		entity.setReceiveInvDate(ConvertDateUtils.parseStringToDate(vo.getReceiveInvDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setInvoiceAmt(vo.getInvoiceAmt());
		entity.setReqWdDate(ConvertDateUtils.parseStringToDate(vo.getReqWdDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setReqWdNo(vo.getReqWdNo());
		entity.setReqWdAmt(vo.getReqWdAmt());
		entity.setReqTaxAmt(vo.getReqTaxAmt());
		entity.setReqNetAmt(vo.getReqNetAmt());
		entity.setReqPayNo(vo.getReqPayNo());
		entity.setReqReceiptDate(ConvertDateUtils.parseStringToDate(vo.getReqReceiptDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setLatePayCause(vo.getLatePayCause());
		entity.setUbillRemark(vo.getUbillRemark());
		
		return iaUtilityBillRepository.save(entity);
	}
	
	public void deleteIaUtilityBillById(Long id) {
		 iaUtilityBillRepository.deleteById(id);
	}

}
