package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbD;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbH;
import th.go.excise.ims.ia.persistence.repository.IaAuditGftbDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditGftbHRepository;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0801SaveVo;
import th.go.excise.ims.ia.vo.Int0801Tabs;
import th.go.excise.ims.ia.vo.Int0801Vo;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgDisbRepository;

@Service
public class Int0801Service {
	
	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditGftbHRepository iaAuditGftbHRepository;
	
	@Autowired
	private IaAuditGftbDRepository iaAuditGftbDRepository;
	
	@Autowired
	private ExciseOrgDisbRepository exciseOrgDisbRepository;
	
	public List<Int0801Tabs> search(SearchVo request) {
		List<Int0801Tabs> tabs = new ArrayList<Int0801Tabs>();
		List<Int0801Vo> resTable = null;
		Int0801Tabs table = null;
		
		request.setPeriodYear(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
		for (int i = 1; i <= 5; i++) {
			resTable = new ArrayList<Int0801Vo>();
			table = new Int0801Tabs();
			request.setFlag(Integer.toString(i));
			resTable = iaGftrialBalanceRepository.findDataAccByRequest(request);
			
			/* set check flag */
			for (Int0801Vo int0801Vo : resTable) {
				if (IaConstants.CHART_OF_ACC_SYMBOLS.ZERO.equals(int0801Vo.getValueTrueType()) && IaConstants.CHART_OF_ACC_SYMBOLS.ZERO.equals(int0801Vo.getCarryForward().toString())) {
					int0801Vo.setCheckFlag("Y");
				} else if (IaConstants.CHART_OF_ACC_SYMBOLS.POSITIVE.equals(int0801Vo.getValueTrueType()) && int0801Vo.getCarryForward().intValue() >= 0) {
					int0801Vo.setCheckFlag("Y");
				} else if (IaConstants.CHART_OF_ACC_SYMBOLS.NEGATIVE.equals(int0801Vo.getValueTrueType()) && int0801Vo.getCarryForward().intValue() <= 0) {
					int0801Vo.setCheckFlag("Y");
				} else {
					int0801Vo.setCheckFlag("N");
				}
			}
			
			table.setTable(resTable);
			tabs.add(table);
		}
		return tabs;
	}

	public String save(Int0801SaveVo request) throws Exception {
		/* __________ deptDisb -> find office code !! __________ */
		ExciseOrgDisb exciseOrgDisb = exciseOrgDisbRepository.findExciseOrgGfmisByGfDisburseUnit(request.getDeptDisb());
		
		/* __________ generate paper No. __________ */
		String auditGftbNo = iaCommonService.autoGetRunAuditNoBySeqName("GFT", exciseOrgDisb.getOfficeCode(), "AUDIT_GFTB_NO_SEQ", 8);
		
		/* __________ header __________ */
		IaAuditGftbH header = new IaAuditGftbH();
		header.setDeptDisb(request.getDeptDisb());
		header.setPeriodMonth(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN).concat(request.getPeriod()));
		header.setGftbConditionText(request.getGftbConditionText());
		header.setGftbCreteriaText(request.getGftbCreteriaText());
		header.setGftbFlag(request.getGftbFlag());
		header.setAuditGftbNo(auditGftbNo);
		iaAuditGftbHRepository.save(header);
		
		/* __________ details __________ */
		IaAuditGftbD detail = null;
		for (Int0801Tabs tab : request.getTabs()) {
			int i = 1; 
			for (Int0801Vo table : tab.getTable()) {
				detail = new IaAuditGftbD();
				BeanUtils.copyProperties(detail, table);
				detail.setAuditGftbNo(auditGftbNo);
				detail.setGftbSeq(new BigDecimal(i));
				iaAuditGftbDRepository.save(detail);
			}
		}
		
		return auditGftbNo;
	}
	
	public List<IaAuditGftbH> getauditGftbNoList(){
		return 	iaAuditGftbHRepository.findAllOrderByAuditGftbNo();
	}
	
	public List<Int0801Tabs> findByAuditGftbNo(String auditGftbNo){
		iaAuditGftbHRepository.findByAuditGftbNoAndIsDeletedOrderByAuditGftbNo(auditGftbNo, "N");
		iaAuditGftbDRepository.findByAuditGftbNoAndIsDeletedOrderByAuditGftbNoAscGftbSeqAsc(auditGftbNo, "N");
		return null;
	}
}
