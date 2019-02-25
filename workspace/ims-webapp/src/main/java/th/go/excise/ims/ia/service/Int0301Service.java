package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0301JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int0301Service {

	@Autowired
	private Int0301JdbcRepository int0301JdbcRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

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
					
					String lowS = int0301Vo.getIaRiskFactorsConfig().getLowStart();
					String low = (StringUtils.isNotBlank(lowS))?"น้อยกว่า" + lowS:"";
					listdynamic.add(low);
					
					String mediumS = int0301Vo.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo.getIaRiskFactorsConfig().getMediumEnd();
					String medium = (StringUtils.isNotBlank(mediumS)&&StringUtils.isNotBlank(mediumE))?"ระหว่าง" + mediumS + "ถึง" + mediumE:"";
					listdynamic.add(medium);
					
					String highS = int0301Vo.getIaRiskFactorsConfig().getHighStart();
					String high = (StringUtils.isNotBlank(highS))?"มากกว่า" + highS:"";
					listdynamic.add(high);

					int0301Vo.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo);
				}
			} else if (new BigDecimal("5").equals(factorsLevel)) {

				for (Int0301Vo int0301Vo2 : iaRiskFactorsList) {
					List<String> listdynamic = new ArrayList<String>();

					String veryLowS = int0301Vo2.getIaRiskFactorsConfig().getVerylowStart();
					String veryLow = (StringUtils.isNotBlank(veryLowS))?"น้อยกว่า" + veryLowS:"";
					listdynamic.add(veryLow);

					String lowS = int0301Vo2.getIaRiskFactorsConfig().getLowStart();
					String lowE = int0301Vo2.getIaRiskFactorsConfig().getLowEnd();
					String low = (StringUtils.isNotBlank(lowS)&&StringUtils.isNotBlank(lowE))?"ระหว่าง" + lowS + "ถึง" + lowE:"";
					listdynamic.add(low);
					
					String mediumS = int0301Vo2.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo2.getIaRiskFactorsConfig().getMediumEnd();
					String medium = (StringUtils.isNotBlank(mediumS)&&StringUtils.isNotBlank(mediumE))?"ระหว่าง" + mediumS + "ถึง" + mediumE:"";
					listdynamic.add(medium);
					
					String highS = int0301Vo2.getIaRiskFactorsConfig().getHighStart();
					String highE= int0301Vo2.getIaRiskFactorsConfig().getHighEnd();
					String high = (StringUtils.isNotBlank(highS)&&StringUtils.isNotBlank(highE))?"ระหว่าง" + highS + "ถึง" + highE:"";
					listdynamic.add(high);

					String veryHighS = int0301Vo2.getIaRiskFactorsConfig().getVeryhighStart();
					String veryHigh = (StringUtils.isNotBlank(veryHighS))?"มากกว่า" + veryHighS:"";
					listdynamic.add(veryHigh);

					int0301Vo2.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo2);
				}
			}

		}

	return iaRiskFactorsList2;

	}

	public void saveRiskFactorsLevel(Int0301FormVo form) {
		int0301JdbcRepository.saveRiskFactorsLevel(form);

	}

	public void saveRiskFactorsConfig(Int0301FormVo form) {
		IaRiskFactorsConfig entity = new IaRiskFactorsConfig();
		IaRiskFactorsConfig formConfig = form.getIaRiskFactorsConfig();
		entity = iaRiskFactorsConfigRepository.findById(formConfig.getId()).get();
		entity.setIdFactors(formConfig.getIdFactors());
		entity.setInfoUsedRiskDesc(formConfig.getInfoUsedRiskDesc());

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

	}

}
