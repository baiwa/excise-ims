package th.go.excise.ims.ia.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Component
public class IntCalculateCriteriaUtil {

	public static IntCalculateCriteriaVo calculateCriteria(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		if (config.getFactorsLevel() != null) {

			if (3 == config.getFactorsLevel().intValue()) {

				cal = calculateRating3Level(dataCal, config);

			} else if (5 == config.getFactorsLevel().intValue()) {

				cal = calculateRating5Level(dataCal, config);

			}
		}

		return cal;

	}

	private static IntCalculateCriteriaVo calculateRating3Level(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(), null)) {

			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());
//			ApplicationCache.getParamInfoByCode(PARAM_GROUP.IA_RISK_COLOR, paramCode);
//			value.setC
		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(),
				config.getMediumEnd())) {

			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());

		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(), null)) {

			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());

		}
		return value;
	}

	private static IntCalculateCriteriaVo calculateRating5Level(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getVerylowCondition(), config.getVerylowStart(), null)) {

			value.setRiskRate(config.getVerylowRating());
			value.setTranslatingRisk(config.getVerylow());
			value.setColor(config.getVerylowColor());

		} else if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(),
				config.getLowEnd())) {

			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());

		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(),
				config.getMediumEnd())) {

			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());

		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(),
				config.getHighEnd())) {

			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());

		} else if (checkDataCal(dataCal.floatValue(), config.getVeryhighCondition(), config.getVeryhighStart(), null)) {

			value.setRiskRate(config.getVeryhighRating());
			value.setTranslatingRisk(config.getVeryhigh());
			value.setColor(config.getVeryhighColor());
		}
		return value;
	}

	public static Boolean checkDataCal(Float dataCal, String condition, String start, String end) {
		Boolean res = false;
		Float startB = (start != null) ? Float.valueOf(start) : null;
		Float endB = (end != null) ? Float.valueOf(end) : null;

		if (!"".equals(condition) && condition != null) {

			if (("<".equals(condition)) && (dataCal < startB)) {

				res = true;

			} else if ("<>".equals(condition) && ((startB <= dataCal) && (dataCal <= endB))) {

				res = true;

			} else if (">".equals(condition) && (dataCal > startB)) {

				res = true;
			}
		}
		return res;
	}

	public static String colorToColorCode(String color) {
		String colorCode = "";
		if ("เขียวเข้ม".equals(color)) {
			colorCode = "COLOR1";
		} else if ("เขียว".equals(color)) {
			colorCode = "COLOR2";
		} else if ("เหลือง".equals(color)) {
			colorCode = "COLOR3";
		} else if ("ส้ม".equals(color)) {
			colorCode = "COLOR4";
		} else if ("แดง".equals(color)) {
			colorCode = "COLOR5";
		}
		return colorCode;

	}

}
