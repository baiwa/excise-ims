package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.Int0802Vo;
import th.co.baiwa.excise.domain.RiskFullDataInt0802Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfOtherDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfOtherDtlRepository;

import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;


@Service
public class RiskAssInfService {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssInfService.class);

	private final RiskAssInfHdrRepository riskAssInfHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_2 = "INT08-2";
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private RiskAssInfOtherDtlRepository riskAssInfOtherDtlRepository;
	
	@Autowired
	private RiskAssInfDtlRepository riskAssInfDtlRepository;
	
	@Autowired
	private ConditionService conditionService;
	
	@Autowired
	public RiskAssInfService(RiskAssInfHdrRepository riskAssInfHdrRepository) {
		this.riskAssInfHdrRepository = riskAssInfHdrRepository;
	}

	public Message createRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("Risk Name : " + riskAssInfHdr.getRiskAssInfHdrName());
		Message message = null;
		List<RiskAssInfHdr> riskAssInfHdrList = riskAssInfHdrRepository.findByCriteria(riskAssInfHdr);

		if (BeanUtils.isEmpty(riskAssInfHdrList) && riskAssInfHdrList.size() == 0) {

			RiskAssInfHdr riskInfHdr = riskAssInfHdrRepository.save(riskAssInfHdr);

			if (BeanUtils.isNotEmpty(riskInfHdr.getRiskAssInfHdrId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}
	
	
	public ResponseDataTable<RiskAssInfHdr> findByCriteriaForDatatable(RiskAssInfHdr riskAssInfHdr, DataTableRequest dataTableRequest) {
		
		ResponseDataTable<RiskAssInfHdr> responseDataTable = new ResponseDataTable<RiskAssInfHdr>();
		List<RiskAssInfHdr> riskAssInfHdrList = findByBudgetYear(riskAssInfHdr.getBudgetYear());

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssInfHdrList);
		responseDataTable.setRecordsTotal((int) riskAssInfHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssInfHdrList.size());
		return responseDataTable;

	}
	
	public Message deleteRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("id : "+riskAssInfHdr.getRiskAssInfHdrId());
		riskAssInfHdrRepository.delete(riskAssInfHdr.getRiskAssInfHdrId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}
	
	
	public Message createBudgetYear(RiskAssInfHdr riskAssInfHdr) {
		Message message = null;
		Lov lov = new Lov(BUDGET_YEAR);
		lov.setValue1(riskAssInfHdr.getBudgetYear());
		lov.setSubType(INT08_2);
		
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if(lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_2);

			RiskAssInfHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssInfHdr();
				insertConfigData.setRiskAssInfHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssInfHdr.getBudgetYear());
				riskAssInfHdrRepository.save(insertConfigData);	
			}
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
		
	}
	
	
	
	public List<RiskAssInfDtl> findRiskAssInfDtlByWebService() {
	return webServiceExciseService.getRiskAssInfDtlList(new RiskAssInfDtl());
		
	}
	
	
	public RiskAssInfHdr findById(Long id) {
		return  riskAssInfHdrRepository.findOne(id);
	}
	
	public void updateRiskAssInfHdr(RiskAssInfHdr riskAssInfHdr) {
		riskAssInfHdrRepository.save(riskAssInfHdr);
	}

	public List<RiskAssInfDtl> findByGroupRiskInfHrdId(Long riskInfHrdId) {
		return riskAssInfDtlRepository.findByGroupRiskInfHrdId(riskInfHrdId);
	}
	
	public void updateRiskAssInfDtl(List<RiskAssInfDtl> riskAssInfDtls) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfDtls.get(0).getRiskInfHrdId(), "MAIN", "int08-2-3");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssInfDtl riskAssInfDtl : riskAssInfDtls) {
				for (Condition condition : conditionList) {
					long value = riskAssInfDtl.getTotal().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssInfDtlRepository.save(riskAssInfDtl);
			}
		}
	}
	
	public List<RiskAssInfOtherDtl> findByOtherRiskHrdId(Long riskInfHrdId) {
		return riskAssInfOtherDtlRepository.findByRiskInfHrdId(riskInfHrdId);
	}
	public void updateStatusRisk(RiskAssInfHdr riskAssInfHdr) {
		RiskAssInfHdr riskInfHrd = riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId());
		riskInfHrd.setActive(riskAssInfHdr.getActive());
		riskAssInfHdrRepository.save(riskInfHrd);
		
	}
	

	public void updateRiskAssInfOtherDtl(List<RiskAssInfOtherDtl> riskAssInfOtherDtlList) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfOtherDtlList.get(0).getRiskInfHrdId(), "OTHER", "int08-2-4");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssInfOtherDtl riskAssInfOtherDtl : riskAssInfOtherDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssInfOtherDtl.getRiskCost().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					}
				}

			}

		}
		RiskAssInfOtherDtl databaseData;
		for (RiskAssInfOtherDtl riskAssInfOtherDtl : riskAssInfOtherDtlList) {
			if (riskAssInfOtherDtl.getIsDeleted().equals("N")) {
				try {
					databaseData = riskAssInfOtherDtlRepository.findOne(riskAssInfOtherDtl.getRiskAssInfOtherId());
					if (databaseData != null && databaseData.getRiskAssInfOtherId() != null) {
						databaseData.setInfName(riskAssInfOtherDtl.getInfName());
						databaseData.setRiskCost(riskAssInfOtherDtl.getRiskCost());
						databaseData.setRl(riskAssInfOtherDtl.getRl());
						databaseData.setValueTranslation(riskAssInfOtherDtl.getValueTranslation());
						databaseData.setColor(riskAssInfOtherDtl.getColor());
						
						riskAssInfOtherDtlRepository.save(databaseData);
					} else {
						riskAssInfOtherDtlRepository.save(riskAssInfOtherDtl);
					}
				} catch (Exception e) {
					riskAssInfOtherDtlRepository.save(riskAssInfOtherDtl);
				}
				
			} else if (riskAssInfOtherDtl.getIsDeleted().equals("Y")) {
				databaseData = riskAssInfOtherDtlRepository.findOne(riskAssInfOtherDtl.getRiskAssInfOtherId());
				riskAssInfOtherDtlRepository.delete(databaseData);
			}
		}

	}
	
	
public List<RiskFullDataInt0802Vo> searchFullRiskByBudgetYear(String budgetYear , List<String> riskAssInfHdrNameList) {
		List<RiskFullDataInt0802Vo> riskFullDataVoList = new ArrayList<RiskFullDataInt0802Vo>();
		RiskFullDataInt0802Vo riskFullDataVo = new RiskFullDataInt0802Vo();
		List<Int0802Vo> infNameList = riskAssInfHdrRepository.findInfNameByBudgetYear(budgetYear);
		int index = 1;
		for (Int0802Vo infName : infNameList) {
			riskFullDataVo = new RiskFullDataInt0802Vo();
			int sumRl = 0;
			riskFullDataVo.setId(index+"");
			riskFullDataVo.setInfName(infName.getInfName());
			List<Int0802Vo> intList = riskAssInfHdrRepository.findData(budgetYear, infName.getInfName());
			List<String> rl = new ArrayList<String>();
			String rlDate = "";
			for (String riskAssInfHdrName : riskAssInfHdrNameList) {
				rlDate = "";
				for (Int0802Vo value : intList) {
					if(value.getInfName().equals(riskAssInfHdrName)) {
						sumRl += Integer.parseInt(value.getRl());
						rlDate = value.getRl();
						break;
					}
				}
				if(BeanUtils.isNotEmpty(rlDate)) {
					rl.add(rlDate); 
				}else {
					rl.add("0");
				}
			}
			riskFullDataVo.setRl(rl);
			riskFullDataVo.setSumRiskCost(sumRl+"");
			riskFullDataVoList.add(riskFullDataVo);
			index++;
		}
		return riskFullDataVoList;
	}
	
	public List<RiskAssInfHdr> findByBudgetYear(String year){
		return riskAssInfHdrRepository.findByBudgetYear(year);
	}
	
	public List<RiskAssInfHdr> updatePercent(List<RiskAssInfHdr> riskAssInfHdrs) {
		List<RiskAssInfHdr> riskAssInfHdrList = new ArrayList<RiskAssInfHdr>();
		for (RiskAssInfHdr riskAssInfHdr : riskAssInfHdrs) {
			riskAssInfHdrRepository.updatePercent(riskAssInfHdr.getPercent(), riskAssInfHdr.getRiskAssInfHdrId());
			riskAssInfHdrList.add(riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId()));
		}
		return riskAssInfHdrList;
	}
	
}
