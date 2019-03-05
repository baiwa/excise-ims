package th.go.excise.ims.ia.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class IntCalculateCriteriaUtil {

	public IntCalculateCriteriaVo calculateCriteria(BigDecimal dataCal, Int0401CalConfigVo config){
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		if(new BigDecimal(3).equals(config.getFactorsLevel())) {
			
			calculateRating3Level(dataCal, config);
			
		}else if(new BigDecimal(5).equals(config.getFactorsLevel())) {
			
			calculateRating5Level(dataCal, config);
			
		}
		
		return cal;
		
	}
	
	private IntCalculateCriteriaVo calculateRating3Level(BigDecimal dataCal, Int0401CalConfigVo config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(), "")) {
			
			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(), config.getMediumEnd())) {
			
			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(), "")) {

			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());
			
		}
		return value;
	}

	
	private IntCalculateCriteriaVo calculateRating5Level(BigDecimal dataCal, Int0401CalConfigVo config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getVerylowCondition(), config.getVerylowStart(), "")) {
			
			value.setRiskRate(config.getVerylowRating());
			value.setTranslatingRisk(config.getVerylow());
			value.setColor(config.getVerylowColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(), config.getLowEnd())) {
			
			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(), config.getMediumEnd())) {
			
			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(), config.getHighEnd())) {

			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());
			
		} else if (checkDataCal(dataCal.floatValue(), config.getVeryhighCondition(), config.getVeryhighStart(), "")) {
			
			value.setRiskRate(config.getVeryhighRating());
			value.setTranslatingRisk(config.getVeryhigh());
			value.setColor(config.getVeryhighColor());
		}
		return value;
	}

	
	public Boolean checkDataCal(Float dataCal,String condition,String start,String end) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		Boolean res = false;
		Float startB = (start!=null)?Float.valueOf(start):null;
		Float endB = (end!=null)?Float.valueOf(end):null;
		
		if(!"".equals(condition)&&condition!=null&&startB!=null&&endB!=null) {
			
			if(("<".equals(condition))&&(dataCal<startB)) {
			
				res = true;	
				
			}else if ("<>".equals(condition)&&((dataCal>=startB)&&(dataCal<=endB))) {
				
				res = true;	
				
			}else if (">".equals(condition)&&(dataCal>startB)) {
				
				res = true;	
			}
		}
		return res;
	}

}
