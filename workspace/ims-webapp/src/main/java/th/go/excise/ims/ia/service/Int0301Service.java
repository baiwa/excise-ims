package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0301JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int0301Service {
	private Logger logger = LoggerFactory.getLogger(Int0301Service.class);

	@Autowired
	private Int0301JdbcRepository int0301JdbcRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;
	
	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	public List<Int0301Vo> list(Int0301FormVo form) {
		List<Int0301Vo> iaRiskFactorsList = new ArrayList<Int0301Vo>();
		iaRiskFactorsList = int0301JdbcRepository.list(form);
		return iaRiskFactorsList;
	}

	public List<Int0301Vo> listdynamic(Int0301FormVo form) {

		List<Int0301Vo> iaRiskFactorsList = new ArrayList<Int0301Vo>();
		List<Int0301Vo> iaRiskFactorsList2 = new ArrayList<Int0301Vo>();

		iaRiskFactorsList = int0301JdbcRepository.list(form);

		if (iaRiskFactorsList.size() != 0) {

			BigDecimal factorsLevel = iaRiskFactorsList.get(0).getIaRiskFactorsConfig().getFactorsLevel();

			if (new BigDecimal("3").equals(factorsLevel)) {

				for (Int0301Vo int0301Vo : iaRiskFactorsList) {
					List<String> listdynamic = new ArrayList<String>();
					String riskUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					String lowCon = int0301Vo.getIaRiskFactorsConfig().getLowCondition();
					String lowS = int0301Vo.getIaRiskFactorsConfig().getLowStart();
					String lowUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();

					listdynamic.add(convertCondition(lowCon, lowS, "", lowUnit, riskUnit));

					String mediumS = int0301Vo.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo.getIaRiskFactorsConfig().getMediumEnd();
					String mediumCon = int0301Vo.getIaRiskFactorsConfig().getMediumCondition();
					String mediumUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(mediumCon, mediumS, mediumE, mediumUnit, riskUnit));

					String highS = int0301Vo.getIaRiskFactorsConfig().getHighStart();
					String highCon = int0301Vo.getIaRiskFactorsConfig().getHighCondition();
					String highUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(highCon, highS, "", highUnit, riskUnit));

					int0301Vo.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo);
				}
			} else if (new BigDecimal("5").equals(factorsLevel)) {

				for (Int0301Vo int0301Vo2 : iaRiskFactorsList) {
					List<String> listdynamic = new ArrayList<String>();
					String riskUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					String veryLowS = int0301Vo2.getIaRiskFactorsConfig().getVerylowStart();
					String veryLowCon = int0301Vo2.getIaRiskFactorsConfig().getVerylowCondition();
					String veryLowUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(veryLowCon, veryLowS, "", veryLowUnit, riskUnit));

					String lowS = int0301Vo2.getIaRiskFactorsConfig().getLowStart();
					String lowE = int0301Vo2.getIaRiskFactorsConfig().getLowEnd();
					String lowCon = int0301Vo2.getIaRiskFactorsConfig().getLowCondition();
					String lowUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(lowCon, lowS, lowE, lowUnit, riskUnit));

					String mediumS = int0301Vo2.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo2.getIaRiskFactorsConfig().getMediumEnd();
					String mediumCon = int0301Vo2.getIaRiskFactorsConfig().getMediumCondition();
					String mediumUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(mediumCon, mediumS, mediumE, mediumUnit, riskUnit));

					String highS = int0301Vo2.getIaRiskFactorsConfig().getHighStart();
					String highE = int0301Vo2.getIaRiskFactorsConfig().getHighEnd();
					String highCon = int0301Vo2.getIaRiskFactorsConfig().getHighCondition();
					String highUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(highCon, highS, highE, highUnit, riskUnit));

					String veryHighS = int0301Vo2.getIaRiskFactorsConfig().getVeryhighStart();
					String veryHighCon = int0301Vo2.getIaRiskFactorsConfig().getVeryhighCondition();
					String veryHighUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(veryHighCon, veryHighS, "", veryHighUnit, riskUnit));

					int0301Vo2.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo2);
				}
			}

		}

		return iaRiskFactorsList2;

	}

	public String convertCondition(String condition, String start, String end, String unit, String riskUnit) {
//		if("บาท".equals(riskUnit)) {
		if (StringUtils.isNotBlank(start)) {
			start = String.format("%,.0f", new BigDecimal(start));
		}
		if (StringUtils.isNotBlank(end)) {
			end = String.format("%,.0f", new BigDecimal(end));
		}
//		}
		String res = "";
		if (StringUtils.isNotBlank(condition)) {
			if ("<".equals(condition)) {
				res = " น้อยกว่า  " + start + " " + unit;
			} else if ("<>".equals(condition)) {
				res = " ระหว่าง  " + start + " ถึง " + end + " " + unit;
			} else if (">".equals(condition)) {
				res = " มากกว่า  " + start + " " + unit;
			}
		}
		return res;
	}

	public void saveRiskFactorsLevel(Int0301FormVo form) {
		int0301JdbcRepository.saveRiskFactorsLevel(form);
		int0301JdbcRepository.claerDateCir(form);

	}

	@Transactional
	public void saveRiskFactorsConfig(Int0301FormVo form) {
		IaRiskFactorsConfig entity = new IaRiskFactorsConfig();
		IaRiskFactorsConfig formConfig = form.getIaRiskFactorsConfig();
		entity = iaRiskFactorsConfigRepository.findById(formConfig.getId()).get();
		entity.setIdFactors(formConfig.getIdFactors());
		entity.setInfoUsedRisk(formConfig.getInfoUsedRisk());

		entity.setVerylow(formConfig.getVerylow());
		entity.setVerylowStart(formConfig.getVerylowStart());
		entity.setVerylowEnd(formConfig.getVerylowEnd());
		entity.setVerylowRating(formConfig.getVerylowRating());
		entity.setVerylowColor(formConfig.getVerylowColor());
		entity.setVerylowCondition(formConfig.getVerylowCondition());

		entity.setLow(formConfig.getLow());
		entity.setLowStart(formConfig.getLowStart());
		entity.setLowEnd(formConfig.getLowEnd());
		entity.setLowRating(formConfig.getLowRating());
		entity.setLowColor(formConfig.getLowColor());
		entity.setLowCondition(formConfig.getLowCondition());

		entity.setMedium(formConfig.getMedium());
		entity.setMediumStart(formConfig.getMediumStart());
		entity.setMediumEnd(formConfig.getMediumEnd());
		entity.setMediumRating(formConfig.getMediumRating());
		entity.setMediumColor(formConfig.getMediumColor());
		entity.setMediumCondition(formConfig.getMediumCondition());

		entity.setHigh(formConfig.getHigh());
		entity.setHighStart(formConfig.getHighStart());
		entity.setHighEnd(formConfig.getHighEnd());
		entity.setHighRating(formConfig.getHighRating());
		entity.setHighColor(formConfig.getHighColor());
		entity.setHighCondition(formConfig.getHighCondition());

		entity.setVeryhigh(formConfig.getVeryhigh());
		entity.setVeryhighStart(formConfig.getVeryhighStart());
		entity.setVeryhighEnd(formConfig.getVeryhighEnd());
		entity.setVeryhighRating(formConfig.getVeryhighRating());
		entity.setVeryhighColor(formConfig.getVeryhighColor());
		entity.setVeryhighCondition(formConfig.getVeryhighCondition());

		Date startDate = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setStartDate(startDate);

		Date endDate = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setEndDate(endDate);
		iaRiskFactorsConfigRepository.save(entity);
		
		updateStatusRiskFactorsService.updateStatusIaRiskFactors(formConfig.getIdFactors(), IaConstants.IA_STATUS_RISK_FACTORS.STATUS_2_CODE);
		

	}

}
