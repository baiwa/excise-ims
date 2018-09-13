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
import th.co.baiwa.excise.domain.Int0803Vo;
import th.co.baiwa.excise.domain.QtnMasterVo;
import th.co.baiwa.excise.domain.RiskFullDataVo;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcNocDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcPenDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcAreaDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcAreaHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcNocDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcOtherDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcPenDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssExcRecDtlRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssExcAreaService {
	private static final Logger logger = LoggerFactory.getLogger(RiskAssExcAreaService.class);
	private final RiskAssExcAreaHdrRepository riskAssExcAreaHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_3 = "INT08-3";

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private RiskAssExcOtherDtlRepository riskAssExcOtherDtlRepository;

	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private RiskAssExcAreaDtlRepository riskAssExcAreaDtlRepository;

	@Autowired
	private RiskAssExcOtherDtlRepository reAssExcOtherDtlRepository;

	@Autowired
	private ConditionService conditionService;

	@Autowired
	private RiskAssExcRecDtlRepository riskAssExcRecDtlRepository;

	@Autowired
	private RiskAssExcPenDtlRepository riskAssExcPenDtlRepository;

	@Autowired
	private RiskAssExcNocDtlRepository riskAssExcNocDtlRepository;

	@Autowired
	private QtnMasterService qtnMasterService;

	@Autowired
	public RiskAssExcAreaService(RiskAssExcAreaHdrRepository riskAssRiskWsHdrRepository) {
		this.riskAssExcAreaHdrRepository = riskAssRiskWsHdrRepository;
	}

	public Message createRiskAssExcAreaHdrRepository(RiskAssExcAreaHdr riskAssExcAreaHdr) {
		logger.info("Risk Name : " + riskAssExcAreaHdr.getRiskHdrName());
		Message message = null;
		List<RiskAssExcAreaHdr> riskWsHdrList = riskAssExcAreaHdrRepository.findByCriteria(riskAssExcAreaHdr);
		if (BeanUtils.isEmpty(riskWsHdrList) && riskWsHdrList.size() == 0) {
			RiskAssExcAreaHdr riskWsHdr = riskAssExcAreaHdrRepository.save(riskAssExcAreaHdr);
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

	public ResponseDataTable<RiskAssExcAreaHdr> findByCriteriaForDatatable(RiskAssExcAreaHdr riskAssExcAreaHdr, DataTableRequest dataTableRequest) {
		logger.info("findByCriteriaForDatatable");
		ResponseDataTable<RiskAssExcAreaHdr> responseDataTable = new ResponseDataTable<RiskAssExcAreaHdr>();
		List<RiskAssExcAreaHdr> riskAssRiskWsHdrList = findRiskAssExcAreaHdrByCriteria(riskAssExcAreaHdr);
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal(riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered(riskAssRiskWsHdrList.size());
		return responseDataTable;

	}

	public List<RiskAssExcAreaHdr> findRiskAssExcAreaHdrByCriteria(RiskAssExcAreaHdr riskAssExcAreaHdr) {
		logger.info("findRiskAssExcAreaHdrByCriteria");
		return riskAssExcAreaHdrRepository.findRiskAssExcAreaHdrByCriteria(riskAssExcAreaHdr);
	}

	public List<RiskAssExcAreaDtl> findRiskAssRiskDtlByWebService() {
		logger.info("findRiskAssRiskDtlByWebService");
		return webServiceExciseService.getRiskAssExcAreaDtlList(new RiskAssExcAreaDtl());

	}

	public Message deleteRiskAssExcAreaHdrRepository(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		riskAssExcAreaHdrRepository.delete(riskAssRiskWsHdr.getRiskHrdId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}

	public Message createBuggetYear(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("findRiskAssRiskDtlByWebService : " + riskAssRiskWsHdr.getBudgetYear());
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
				insertConfigData.setActive("Y");
				riskAssExcAreaHdrRepository.save(insertConfigData);

			}
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;

	}

	public List<RiskAssExcAreaDtl> findByGroupRiskHrdId(Long riskHrdId) {
		logger.info("findByGroupRiskHrdId : " + riskHrdId);
		return riskAssExcAreaDtlRepository.findByGroupRiskHrdId(riskHrdId);
	}

	public void updateRiskAssExcAreaDtl(List<RiskAssExcAreaDtl> riskAssRiskWsDtls) {
		logger.info("updateRiskAssExcAreaDtl : ");
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssRiskWsDtls.get(0).getRiskHrdId(), "MAIN", "int08-3-3");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcAreaDtl riskAssExcAreaDtl : riskAssRiskWsDtls) {
				for (Condition condition : conditionList) {
					long value = riskAssExcAreaDtl.getYears() != null ? riskAssExcAreaDtl.getYears().longValue() : 0;
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {

						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setColor(condition.getColor());
						riskAssExcAreaDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setColor(condition.getColor());
						riskAssExcAreaDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssExcAreaDtl.setRl(condition.getValueRl());
						riskAssExcAreaDtl.setColor(condition.getColor());
						riskAssExcAreaDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssExcAreaDtlRepository.save(riskAssExcAreaDtl);
			}
		}
	}

	public RiskAssExcAreaHdr findById(Long id) {
		logger.info("RiskAssExcAreaHdr findById : " + id);
		return riskAssExcAreaHdrRepository.findOne(id);
	}

	public List<RiskAssExcAreaHdr> updatePercent(List<RiskAssExcAreaHdr> riskAssRiskWsHdrs) {
		logger.info("updatePercent");
		List<RiskAssExcAreaHdr> RiskAssExcAreaHdrList = new ArrayList<RiskAssExcAreaHdr>();
		for (RiskAssExcAreaHdr riskAssRiskWsHdr : riskAssRiskWsHdrs) {
			riskAssExcAreaHdrRepository.updatePercent(riskAssRiskWsHdr.getPercent(), riskAssRiskWsHdr.getRiskHrdId());
			RiskAssExcAreaHdrList.add(riskAssExcAreaHdrRepository.findOne(riskAssRiskWsHdr.getRiskHrdId()));
		}
		return RiskAssExcAreaHdrList;
	}

	public void updateRiskAssExcAreaHdr(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("updateRiskAssExcAreaHdr");
		riskAssExcAreaHdrRepository.save(riskAssRiskWsHdr);
	}

	public List<RiskAssExcAreaHdr> findByCriteria(RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("RiskAssExcAreaHdr findByCriteria");
		return riskAssExcAreaHdrRepository.findByCriteria(riskAssRiskWsHdr);
	}

	public List<RiskAssExcOtherDtl> findByRiskHrdId(Long riskHrdId) {
		logger.info("RiskAssExcOtherDtl findByRiskHrdId : " + riskHrdId);
		return reAssExcOtherDtlRepository.findByRiskHrdId(riskHrdId);
	}

	public void updateRiskAssOtherDtl(List<RiskAssExcOtherDtl> riskAssExcOtherDtlList) {
		logger.info("updateRiskAssOtherDtl");
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssExcOtherDtlList.get(0).getRiskHrdId(), "OTHER", "int08-3-4");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcOtherDtl riskAssOtherDtl : riskAssExcOtherDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssOtherDtl.getRiskCost().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					}
				}

			}

		}
		RiskAssExcOtherDtl databaseData;
		for (RiskAssExcOtherDtl riskAssOtherDtl : riskAssExcOtherDtlList) {
			if (riskAssOtherDtl.getIsDeleted().equals("N")) {
				try {
					databaseData = riskAssExcOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
					if (databaseData != null && databaseData.getRiskOtherDtlId() != null) {
						databaseData.setDepartmentName(riskAssOtherDtl.getDepartmentName());
						databaseData.setRiskCost(riskAssOtherDtl.getRiskCost());
						databaseData.setRl(riskAssOtherDtl.getRl());
						databaseData.setValueTranslation(riskAssOtherDtl.getValueTranslation());
						riskAssExcOtherDtlRepository.save(databaseData);
					} else {
						riskAssExcOtherDtlRepository.save(riskAssOtherDtl);
					}
				} catch (Exception e) {
					riskAssExcOtherDtlRepository.save(riskAssOtherDtl);
				}

			} else if (riskAssOtherDtl.getIsDeleted().equals("Y")) {
				databaseData = riskAssExcOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
				riskAssExcOtherDtlRepository.delete(databaseData);
			}
		}

	}

	public void updateRiskAssQtnOtherDtl(List<RiskAssExcOtherDtl> riskAssExcOtherDtlList) {
		logger.info("updateRiskAssQtnOtherDtl");
		for (RiskAssExcOtherDtl riskAssExcOtherDtl : riskAssExcOtherDtlList) {
			if (BeanUtils.isEmpty(riskAssExcOtherDtl.getRiskOtherDtlId())) {
				riskAssExcOtherDtlRepository.save(riskAssExcOtherDtlList);
			}
		}

	}

	public List<RiskFullDataVo> searchFullRiskByBudgetYear(String budgetYear, List<String> depNameList) {
		logger.info("searchFullRiskByBudgetYear : " + budgetYear);
		List<Condition> conditionList = conditionService.findConditionByParentId(new Long(budgetYear), "ALL", "int08-3-5");
		List<RiskFullDataVo> riskFullDataVoList = new ArrayList<RiskFullDataVo>();
		RiskFullDataVo riskFullDataVo = new RiskFullDataVo();
		List<Int0803Vo> projectDepName = riskAssExcAreaHdrRepository.findProjectBaseByBudgetYear(budgetYear);
		int index = 1;
		for (Int0803Vo projectBase : projectDepName) {
			riskFullDataVo = new RiskFullDataVo();
			int sumRl = 0;
			riskFullDataVo.setId(index + "");
			riskFullDataVo.setProjectBase(projectBase.getProjectBase());
			riskFullDataVo.setDepartmentName(projectBase.getDepartmentName());
			List<Int0803Vo> intList = riskAssExcAreaHdrRepository.findData(budgetYear, projectBase.getDepartmentName());
			List<String> rl = new ArrayList<String>();
			String rlDate = "";
			for (String riskHdrName : depNameList) {
				rlDate = "";
				for (Int0803Vo value : intList) {
					if (value.getProjectBase().equals(riskHdrName)) {
						sumRl += Integer.parseInt(value.getRl());
						rlDate = value.getRl();
						break;
					}
				}
				if (BeanUtils.isNotEmpty(rlDate)) {
					rl.add(rlDate);
				} else {
					rl.add("0");
				}
			}
			riskFullDataVo.setRl(rl);
			riskFullDataVo.setSumRiskCost(sumRl + "");
			for (Condition condition : conditionList) {
				long value = sumRl;
				if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
					riskFullDataVo.setRlAll(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setValueTranslation(condition.getConvertValue());
				} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
					riskFullDataVo.setRlAll(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setValueTranslation(condition.getConvertValue());
				} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
					riskFullDataVo.setRlAll(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setValueTranslation(condition.getConvertValue());
				}
			}
			riskFullDataVoList.add(riskFullDataVo);
			index++;
		}
		return riskFullDataVoList;
	}

	// riskAssExcRecDtlRepository //
	public List<RiskAssExcRecDtl> findriskAssExcRecDtlByHrdId(Long riskHrdId) {
		logger.info("RiskAssExcRecDtl findriskAssExcRecDtlByHrd: " + riskHrdId);
		return riskAssExcRecDtlRepository.findByRiskHrdId(riskHrdId);
	}

	public RiskAssExcRecDtl saveRiskAssExcRecDtl(RiskAssExcRecDtl riskAssExcRecDtl) {
		logger.info("saveRiskAssExcRecDtl");
		return riskAssExcRecDtlRepository.save(riskAssExcRecDtl);
	}

	public List<RiskAssExcRecDtl> riskAssExcAreaDtlByWebService() {
		logger.info("RiskAssExcAreaDtlByWebService");
		return webServiceExciseService.RiskAssExcAreaDtlByWebService(new RiskAssExcRecDtl());

	}

	public void updateRiskAssExcRecDtl(List<RiskAssExcRecDtl> riskAssExcRecDtlList) {
		logger.info("updateRiskAssExcAreaDtl : ");
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssExcRecDtlList.get(0).getRiskHrdId(), "MAIN", "int08-3-6");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcRecDtl riskAssExcRecDtl : riskAssExcRecDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssExcRecDtl.getPercenDiff() != null ? riskAssExcRecDtl.getPercenDiff().longValue() : 0;
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssExcRecDtl.setRl(condition.getValueRl());
						riskAssExcRecDtl.setColor(condition.getColor());
						riskAssExcRecDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssExcRecDtl.setRl(condition.getValueRl());
						riskAssExcRecDtl.setColor(condition.getColor());
						riskAssExcRecDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssExcRecDtl.setRl(condition.getValueRl());
						riskAssExcRecDtl.setColor(condition.getColor());
						riskAssExcRecDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssExcRecDtlRepository.save(riskAssExcRecDtl);
			}
		}
	}

	public List<RiskAssExcPenDtl> findRiskAssExcPenDtlByHrdId(Long riskHrdId) {
		logger.info("RiskAssExcRecDtl findriskAssExcRecDtlByHrd: " + riskHrdId);
		return riskAssExcPenDtlRepository.findByRiskHrdId(riskHrdId);
	}

	public List<RiskAssExcPenDtl> riskAssExcPenDtlByWebService() {
		logger.info("RiskAssExcAreaDtlByWebService");
		return webServiceExciseService.riskAssExcAreaDtlByWebService2(new RiskAssExcPenDtl());

	}

	public void updateRiskAssExcPenDtl(List<RiskAssExcPenDtl> riskAssExcPenDtlList) {
		logger.info("updateRiskAssExcAreaDtl : ");
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssExcPenDtlList.get(0).getRiskHrdId(), "MAIN", "int08-3-7");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcPenDtl riskAssExcPenDtl : riskAssExcPenDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssExcPenDtl.getPercenDiff() != null ? riskAssExcPenDtl.getPercenDiff().longValue() : 0;
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssExcPenDtl.setRl(condition.getValueRl());
						riskAssExcPenDtl.setColor(condition.getColor());
						riskAssExcPenDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssExcPenDtl.setRl(condition.getValueRl());
						riskAssExcPenDtl.setColor(condition.getColor());
						riskAssExcPenDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssExcPenDtl.setRl(condition.getValueRl());
						riskAssExcPenDtl.setColor(condition.getColor());
						riskAssExcPenDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssExcPenDtlRepository.save(riskAssExcPenDtl);
			}
		}
	}

	public List<RiskAssExcNocDtl> riskAssExcNocDtlByWebService() {
		logger.info("RiskAssExcAreaDtlByWebService");
		return webServiceExciseService.riskAssExcAreaDtlByWebService3(new RiskAssExcNocDtl());

	}

	public void updateRiskAssExcNocDtl(List<RiskAssExcNocDtl> riskAssExcNocDtlList) {
		logger.info("updateRiskAssExcNocDtl : ");
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssExcNocDtlList.get(0).getRiskHrdId(), "MAIN", "int08-3-8");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssExcNocDtl riskAssExcNocDtl : riskAssExcNocDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssExcNocDtl.getPercenDiff() != null ? riskAssExcNocDtl.getPercenDiff().longValue() : 0;
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssExcNocDtl.setRl(condition.getValueRl());
						riskAssExcNocDtl.setColor(condition.getColor());
						riskAssExcNocDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssExcNocDtl.setRl(condition.getValueRl());
						riskAssExcNocDtl.setColor(condition.getColor());
						riskAssExcNocDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssExcNocDtl.setRl(condition.getValueRl());
						riskAssExcNocDtl.setColor(condition.getColor());
						riskAssExcNocDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssExcNocDtlRepository.save(riskAssExcNocDtl);
			}
		}
	}

	public List<RiskAssExcNocDtl> findRiskAssExcNocDtlByHrdId(Long riskHrdId) {
		logger.info("findRiskAssExcNocDtlByHrdId: " + riskHrdId);
		return riskAssExcNocDtlRepository.findByRiskHrdId(riskHrdId);
	}

	public void updateStatusRisk(RiskAssExcAreaHdr riskAssExcAreaHdr) {
		RiskAssExcAreaHdr risk = riskAssExcAreaHdrRepository.findOne(riskAssExcAreaHdr.getRiskHrdId());
		risk.setActive(riskAssExcAreaHdr.getActive());
		riskAssExcAreaHdrRepository.save(risk);
	}

	public List<QtnMasterVo> searchQtn(RiskAssExcAreaHdr riskAssExcAreaHdr) {
		QtnMaster qtnMaster = new QtnMaster();
		qtnMaster.setQtnYear(riskAssExcAreaHdr.getBudgetYear());
		List<QtnMasterVo> qtnMasterVoList = qtnMasterService.calRiskPoint(riskAssExcAreaHdr.getBudgetYear());
		return qtnMasterVoList;
	}

}
