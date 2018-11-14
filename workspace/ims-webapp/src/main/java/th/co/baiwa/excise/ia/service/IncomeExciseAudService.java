package th.co.baiwa.excise.ia.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAud;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudRpt;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudRepository;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudRptRepository;
import th.co.baiwa.excise.ia.persistence.vo.CheckList;
import th.co.baiwa.excise.ia.persistence.vo.DataQuery;
import th.co.baiwa.excise.ia.persistence.vo.InputIncomeExciseAudit;
import th.co.baiwa.excise.ia.persistence.vo.ResponseMobileCheckIncomeExciseAudit;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

@Service
public class IncomeExciseAudService {

	private Logger logger = LoggerFactory.getLogger(IncomeExciseAudService.class);
	
	@Autowired
	private IncomeExciseAudRepository incomeExciseAudRepository;
	
	@Autowired
	private IncomeExciseAudDtlRepository incomeExciseAudDtlRepository;
	
	@Autowired
	private IncomeExciseAudRptRepository inExciseAudRptRepository;
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	
	public IncomeExciseAud createIncomeExciseAud(IncomeExciseAud incomeExciseAud) {
		logger.info("createIncomeExciseAud startMonth = {} || endMonth = {} ", incomeExciseAud.getStartMonth() , incomeExciseAud.getEndMonth());
		List<IncomeList> incomeLists = new ArrayList<IncomeList>();
		List<IncomeExciseAudDtl> incDetailList = new ArrayList<IncomeExciseAudDtl>();
		IncomeExciseAudDtl incomeExciseAudDtl = new IncomeExciseAudDtl();
		int indexPage = 0;
		int pageSize = 100;
		do {
			
			incomeLists = new ArrayList<IncomeList>();
			incomeExciseAud = incomeExciseAudRepository.save(incomeExciseAud);
			IncFri8020 incFri8020 = webServiceExciseService.IncFri8020("", incomeExciseAud.getStartMonth(), incomeExciseAud.getEndMonth(), "Income", indexPage,++pageSize );
			if(BeanUtils.isNotEmpty(incFri8020) && BeanUtils.isNotEmpty(incFri8020.getResponseData())) {
				incomeLists = incFri8020.getResponseData().getIncomeList();
				if(BeanUtils.isNotEmpty(incomeLists)) {
					
					for (IncomeList incomeList : incomeLists) {
						incomeExciseAudDtl = new IncomeExciseAudDtl();
						incomeExciseAudDtl.setIaIncomeExciseAudId(incomeExciseAud.getIaIncomeExciseAudId());
						incomeExciseAudDtl.setReceiptNo(incomeList.getReceiptNo());
						incomeExciseAudDtl.setReceiptDate(incomeList.getReceiptDate());
						incomeExciseAudDtl.setReceiptStatus(incomeList.getReceiptStatus());
						incomeExciseAudDtl.setIncomeName(incomeList.getIncomeName());
						incomeExciseAudDtl.setIncomeCode(incomeList.getIncomeCode());
						incomeExciseAudDtl.setOfficeCode(incomeList.getOfficeReceive());
						incomeExciseAudDtl.setPinNidId(incomeList.getPinNidId());
						incDetailList.add(incomeExciseAudDtl);
					}
					try {
						incomeExciseAudDtlRepository.insertIncomeExciseAudDtlBatch(incDetailList);
					} catch (SQLException e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
			
		}while(BeanUtils.isNotEmpty(incomeLists) && incomeLists.size() == pageSize);
		
		return incomeExciseAud;
	}
	
	public ResponseMobileCheckIncomeExciseAudit findbyAssignToAndStatus(InputIncomeExciseAudit user){
		ResponseMobileCheckIncomeExciseAudit responseMobileCheckIncomeExciseAudit = new ResponseMobileCheckIncomeExciseAudit();
		List<IncomeExciseAudRpt> incomeExciseAudRptList = inExciseAudRptRepository.findbyAssignToAndStatus(user.getUsername(), FLAG.N_FLAG);
		
		if(BeanUtils.isNotEmpty(incomeExciseAudRptList)) {
			DataQuery data = new DataQuery();
			List<DataQuery> datas = new ArrayList<DataQuery>();
			responseMobileCheckIncomeExciseAudit.setAssignTo(user.getUsername());
			responseMobileCheckIncomeExciseAudit.setCreateBy(incomeExciseAudRptList.get(0).getCreatedBy());
			for (IncomeExciseAudRpt incomeExciseAudRpt : incomeExciseAudRptList) {
				data = new DataQuery();
				data.setCheckId(incomeExciseAudRpt.getIaIncomeExciseAudRptId());
				String secterCode = incomeExciseAudRpt.getOfficeCode().substring(0, 2)+"0000";
				List<Lov> sectorList = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", secterCode);
				if(BeanUtils.isNotEmpty(sectorList)) {
					data.setSectorName(sectorList.get(0).getSubTypeDescription());
					data.setSectorCode("secterCode");
				}
				if(!"0000".equals(incomeExciseAudRpt.getOfficeCode().substring(2))) {
					data.setBranchCode(incomeExciseAudRpt.getOfficeCode());
					List<Lov> branchList = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", incomeExciseAudRpt.getOfficeCode());
					if(BeanUtils.isNotEmpty(branchList)) {
						data.setBranchCode(incomeExciseAudRpt.getOfficeCode());
						data.setBranchName(branchList.get(0).getSubTypeDescription());
					}
				}
				
				data.setChecked("N");
				List<CheckList> checkLists = new ArrayList<CheckList>();
				checkLists.add(new CheckList(1, "ตรวจสอบรายได้", "01"));
				data.setCheckList(checkLists);
				datas.add(data);
			}
			responseMobileCheckIncomeExciseAudit.setDatas(datas);
			
		}
		return responseMobileCheckIncomeExciseAudit;
	}
	
	
}
