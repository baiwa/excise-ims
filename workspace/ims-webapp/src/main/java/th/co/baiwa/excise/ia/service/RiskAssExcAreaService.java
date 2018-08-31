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
import th.co.baiwa.excise.domain.Int0801Vo;
import th.co.baiwa.excise.domain.RiskFullDataVo;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssOtherDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcAreaDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcAreaHdrRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssExcAreaService {
	private static final Logger logger = LoggerFactory.getLogger(RiskAssExcAreaService.class);
	private final RiskAssExcAreaHdrRepository riskAssRiskWsHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_3 = "INT08-3";

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private RiskAssExcAreaDtlRepository riskAssExcAreaDtlRepository;

	@Autowired
	private RiskAssOtherDtlRepository riskAssOtherDtlRepository;

	@Autowired
	private ConditionService conditionService;

	@Autowired
	public RiskAssExcAreaService(RiskAssExcAreaHdrRepository riskAssRiskWsHdrRepository) {
		this.riskAssRiskWsHdrRepository = riskAssRiskWsHdrRepository;
	}

	public Message createRiskAssExcAreaHdrRepository(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		Message message = null;
		List<RiskAssExcAreaHdr> riskWsHdrList = riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
		if (BeanUtils.isEmpty(riskWsHdrList) && riskWsHdrList.size() == 0) {
			RiskAssExcAreaHdr riskWsHdr = riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
			if (BeanUtils.isNotEmpty(riskWsHdr.getRiskHrdId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}

	public ResponseDataTable<RiskAssExcAreaHdr> findByCriteriaForDatatable(RiskAssExcAreaHdr riskAssRiskWsHdr, DataTableRequest dataTableRequest) {

		ResponseDataTable<RiskAssExcAreaHdr> responseDataTable = new ResponseDataTable<RiskAssExcAreaHdr>();
		List<RiskAssExcAreaHdr> riskAssRiskWsHdrList = findByBudgetYear(riskAssRiskWsHdr.getBudgetYear());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal(riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered(riskAssRiskWsHdrList.size());
		return responseDataTable;

	}

	public List<RiskAssExcAreaHdr> findByBudgetYear(String year) {
		return riskAssRiskWsHdrRepository.findByBudgetYear(year);
	}

	public List<RiskAssExcAreaDtl> findRiskAssRiskDtlByWebService() {
		return webServiceExciseService.getRiskAssExcAreaDtlList(new RiskAssExcAreaDtl());

	}

	public Message deleteRiskAssExcAreaHdrRepository(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		riskAssRiskWsHdrRepository.delete(riskAssRiskWsHdr.getRiskHrdId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}

	public Message createBuggetYear(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		Message message = null;
		Lov lov = new Lov(BUDGET_YEAR);
		lov.setValue1(riskAssRiskWsHdr.getBudgetYear());
		lov.setSubType(INT08_3);
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if (lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_3);
			RiskAssExcAreaHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssExcAreaHdr();
				insertConfigData.setRiskHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssRiskWsHdr.getBudgetYear());
				riskAssRiskWsHdrRepository.save(insertConfigData);

			}
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;

	}

	public List<RiskAssExcAreaDtl> findByGroupRiskHrdId(Long riskHrdId) {
		return riskAssExcAreaDtlRepository.findByGroupRiskHrdId(riskHrdId);
	}

	public void updateRiskAssExcAreaDtl(List<RiskAssExcAreaDtl> riskAssRiskWsDtls) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssRiskWsDtls.get(0).getRiskHrdId(), "MAIN", "int08-3-3");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcAreaDtl  riskAssExcAreaDtl : riskAssRiskWsDtls) {
				for (Condition condition : conditionList) {
					long value = riskAssExcAreaDtl.getYears() != null  ? riskAssExcAreaDtl.getYears().longValue() : 0;
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setValueTranslation(condition.getColor());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setValueTranslation(condition.getColor());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setValueTranslation(condition.getColor());
					}
				}

				riskAssExcAreaDtlRepository.save(riskAssRiskWsDtls);
			}
		}
	}

	public RiskAssExcAreaHdr findById(Long id) {
		return riskAssRiskWsHdrRepository.findOne(id);
	}
	
	public List<RiskAssExcAreaHdr> updatePercent(List<RiskAssExcAreaHdr> riskAssRiskWsHdrs) {
		List<RiskAssExcAreaHdr> RiskAssExcAreaHdrList = new ArrayList<RiskAssExcAreaHdr>();
		for (RiskAssExcAreaHdr riskAssRiskWsHdr : riskAssRiskWsHdrs) {
			riskAssRiskWsHdrRepository.updatePercent(riskAssRiskWsHdr.getPercent(), riskAssRiskWsHdr.getRiskHrdId());
			RiskAssExcAreaHdrList.add(riskAssRiskWsHdrRepository.findOne(riskAssRiskWsHdr.getRiskHrdId()));
		}
		return RiskAssExcAreaHdrList;
	}

	public void updateRiskAssExcAreaHdr(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
	}

	public List<RiskAssExcAreaHdr> findByCriteria(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		return riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
	}

	public List<RiskAssOtherDtl> findByRiskHrdId(Long riskHrdId) {
		return riskAssOtherDtlRepository.findByRiskHrdId(riskHrdId);
	}
	
	
	public void updateRiskAssOtherDtl(List<RiskAssOtherDtl> riskAssOtherDtlList) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssOtherDtlList.get(0).getRiskHrdId(), "OTHER", "int08-1-6");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssOtherDtl riskAssOtherDtl : riskAssOtherDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssOtherDtl.getRiskCost().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setValueTranslation(condition.getColor());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setValueTranslation(condition.getColor());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setValueTranslation(condition.getColor());
					}
				}

			}

		}
		RiskAssOtherDtl databaseData;
		for (RiskAssOtherDtl riskAssOtherDtl : riskAssOtherDtlList) {
			if (riskAssOtherDtl.getIsDeleted().equals("N")) {
				try {
					databaseData = riskAssOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
					if (databaseData != null && databaseData.getRiskOtherDtlId() != null) {
						databaseData.setProjectBase(riskAssOtherDtl.getProjectBase());
						databaseData.setDepartmentName(riskAssOtherDtl.getDepartmentName());
						databaseData.setRiskCost(riskAssOtherDtl.getRiskCost());
						databaseData.setRl(riskAssOtherDtl.getRl());
						databaseData.setValueTranslation(riskAssOtherDtl.getValueTranslation());
						riskAssOtherDtlRepository.save(databaseData);
					} else {
						riskAssOtherDtlRepository.save(riskAssOtherDtl);
					}
				} catch (Exception e) {
					riskAssOtherDtlRepository.save(riskAssOtherDtl);
				}
				
			} else if (riskAssOtherDtl.getIsDeleted().equals("Y")) {
				databaseData = riskAssOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
				riskAssOtherDtlRepository.delete(databaseData);
			}
		}

	}

	public List<RiskFullDataVo> searchFullRiskByBudgetYear(String budgetYear , List<String> riskHdrNameList) {
		List<RiskFullDataVo> riskFullDataVoList= new ArrayList<RiskFullDataVo>();
		RiskFullDataVo riskFullDataVo = new RiskFullDataVo();
		List<Int0801Vo> projectDepName = riskAssRiskWsHdrRepository.findProjectBaseByBudgetYear(budgetYear);
		int index = 1;
		for (Int0801Vo projectBase : projectDepName) {
			riskFullDataVo = new RiskFullDataVo();
			int sumRl = 0;
			riskFullDataVo.setId(index+"");
			riskFullDataVo.setProjectBase(projectBase.getProjectBase());
			riskFullDataVo.setDepartmentName(projectBase.getDepartmentName());
			List<Int0801Vo> intList = riskAssRiskWsHdrRepository.findData(budgetYear, projectBase.getProjectBase() , projectBase.getDepartmentName());
			List<String> rl = new ArrayList<String>();
			String rlDate = "";
			for (String riskHdrName : riskHdrNameList) {
				rlDate = "";
				for (Int0801Vo value : intList) {
					if(value.getProjectBase().equals(riskHdrName)) {
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

}
