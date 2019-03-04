package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.Int0401CalDataVo;
import th.go.excise.ims.ia.vo.Int0401CalVo;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401ListVo;
import th.go.excise.ims.ia.vo.Int0401Vo;

@Service
public class Int0401Service {

	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;

	@Autowired
	private Int0401JdbcRepository int0401JdbcRep;

	public List<Int0401Vo> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<Int0401Vo> lists = new ArrayList<>();
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		List<IaRiskFactors> factors = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		List<Int0401CalVo> cals = int0401JdbcRep.findDetails(budgetYear, inspectionWork);
		for (IaRiskSelectCase selectCase : selectCases) {
			Int0401Vo list = new Int0401Vo();
			List<Int0401ListVo> listVos = new ArrayList<>();
			for (IaRiskFactors factor : factors) {
				Int0401ListVo listVo = new Int0401ListVo();
				listVo.setRiskRate(new BigDecimal(1));
				listVo.setRiskText("LOW");
				listVo.setRiskCode("L");
				for (Int0401CalVo cal : cals) {
					// TODO `CALCULATE`
					if (factor.getId().compareTo(cal.getConfig().getIdFactors()) == 0) {
						if (factor.getId().compareTo(cal.getData().getIdFactors()) == 0) {
							if (cal.getData().getIdSelect().compareTo(selectCase.getId()) == 0) {
								if (cal.getConfig().getFactorsLevel().compareTo(new BigDecimal(5)) == 0) {
									listVo = calculateRating5th(cal.getData(), cal.getConfig());
								} else {
									listVo = calculateRating3rd(cal.getData(), cal.getConfig());
								}
							}
						}
					}
				}
				listVos.add(listVo);
			}
			list.setId(selectCase.getId());
			list.setBudgetYear(selectCase.getBudgetYear());
			list.setProjectName(selectCase.getProject());
			list.setExciseCode(selectCase.getExciseCode());
			list.setSectorName(selectCase.getSector());
			list.setAreaName(selectCase.getArea());
			list.setInspectionWork(selectCase.getInspectionWork());
			list.setStatus(selectCase.getStatus());
			list.setLists(listVos);
			list.setRiskItem(new BigDecimal(listVos.size()));
			list.setRiskRate(new BigDecimal(0));
			list.setRiskText("LOW");
			lists.add(list);
		}
		return lists;
	}

	public BigDecimal findStatusByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		return new BigDecimal(selectCases.size());
	}

	public List<Int0401HeaderVo> findHeadByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaRiskFactors> factors = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		List<Int0401HeaderVo> lists = new ArrayList<>();
		for (IaRiskFactors factor : factors) {
			Int0401HeaderVo header = new Int0401HeaderVo();
			header.setName(factor.getRiskFactors());
			lists.add(header);
		}
		return lists;
	}

	public List<Int0401Vo> updateRowByStatus(List<BigDecimal> ids, String status) {
		List<IaRiskSelectCase> selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.findAllById(ids);
		for (IaRiskSelectCase selectCase : selectCases) {
			selectCase.setStatus(status);
		}
		selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		List<Int0401Vo> currentLists = new ArrayList<>();
		if (selectCases != null && selectCases.size() > 0) {
			String budgetYear = selectCases.get(0).getBudgetYear();
			BigDecimal inspectionWork = selectCases.get(0).getInspectionWork();
			String newStatus = "C".equalsIgnoreCase(status) ? "S" : "C";
			currentLists = findByBudgetYearAndInspectionWork(budgetYear, inspectionWork.toString(), newStatus);
		}
		return currentLists;
	}

	private Int0401ListVo calculateRating5th(Int0401CalDataVo data, Int0401CalConfigVo config) {
		Int0401ListVo value = new Int0401ListVo();
		if (convertStr2Db(data.getRiskCost()) > convertStr2Db(config.getVeryhighStart())) {
			value.setRiskCode("VH");
			value.setRiskRate(config.getVeryhighRating());
			value.setRiskText(config.getVeryhigh());
		} else if (convertStr2Db(data.getRiskCost()) >= convertStr2Db(config.getHighStart())
				&& convertStr2Db(data.getRiskCost()) <= convertStr2Db(config.getHighEnd())) {
			value.setRiskCode("H");
			value.setRiskRate(config.getHighRating());
			value.setRiskText(config.getHigh());
		} else if (convertStr2Db(data.getRiskCost()) >= convertStr2Db(config.getMediumStart())
				&& convertStr2Db(data.getRiskCost()) <= convertStr2Db(config.getMediumEnd())) {
			value.setRiskCode("M");
			value.setRiskRate(config.getMediumRating());
			value.setRiskText(config.getMedium());
		} else if (convertStr2Db(data.getRiskCost()) >= convertStr2Db(config.getLowStart())
				&& convertStr2Db(data.getRiskCost()) <= convertStr2Db(config.getLowEnd())) {
			value.setRiskCode("L");
			value.setRiskRate(config.getLowRating());
			value.setRiskText(config.getLow());
		} else if (convertStr2Db(data.getRiskCost()) < convertStr2Db(config.getVerylowStart())) {
			value.setRiskCode("VL");
			value.setRiskRate(config.getVerylowRating());
			value.setRiskText(config.getVerylow());
		}
		return value;
	}

	private Int0401ListVo calculateRating3rd(Int0401CalDataVo data, Int0401CalConfigVo config) {
		Int0401ListVo value = new Int0401ListVo();
		if (convertStr2Db(data.getRiskCost()) > convertStr2Db(config.getHighStart())) {
			value.setRiskCode("H");
			value.setRiskRate(config.getHighRating());
			value.setRiskText(config.getHigh());
		} else if (convertStr2Db(data.getRiskCost()) >= convertStr2Db(config.getMediumStart())
				&& convertStr2Db(data.getRiskCost()) <= convertStr2Db(config.getMediumEnd())) {
			value.setRiskCode("M");
			value.setRiskRate(config.getMediumRating());
			value.setRiskText(config.getMedium());
		} else if (convertStr2Db(data.getRiskCost()) < convertStr2Db(config.getLowStart())) {
			value.setRiskCode("L");
			value.setRiskRate(config.getLowRating());
			value.setRiskText(config.getLow());
		}
		return value;
	}

	private double convertStr2Db(String value) {
		return new BigDecimal(value).doubleValue();
	}

}
