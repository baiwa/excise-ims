package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigAllRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030103JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int030103Service {

	@Autowired
	private Int030103JdbcRepository int030103JdbcRepository;
	
	@Autowired
	private IaRiskFactorsConfigAllRepository iaRiskFactorsConfigAllRepository;
	
	
	
	public List<IaRiskFactorsConfigAll> listConfigAll(IaRiskFactorsConfigAll form) {
		List<IaRiskFactorsConfigAll> iaRiskFactorsConfigAllList = new ArrayList<IaRiskFactorsConfigAll>();
		iaRiskFactorsConfigAllList = int030103JdbcRepository.listConfigAll(form);
		return iaRiskFactorsConfigAllList;
	}
	
	public void updatePercent(Int0301FormVo form) {
		
		IaRiskFactorsConfigAll configData = new IaRiskFactorsConfigAll();
		IaRiskFactorsConfigAll formConfigAll = form.getIaRiskFactorsConfigAll();
		configData.setBudgetYear(formConfigAll.getBudgetYear());
		configData.setInspectionWork(formConfigAll.getInspectionWork());
		configData.setFactorsLevel(formConfigAll.getFactorsLevel());
		configData.setVerylow(formConfigAll.getVerylow());
		configData.setVerylowColor(formConfigAll.getVerylowColor());
		configData.setVerylowCondition(formConfigAll.getVerylowCondition());
		configData.setVerylowEnd(formConfigAll.getVerylowEnd());
		configData.setVerylowRating(formConfigAll.getVerylowRating());
		configData.setVerylowStart(formConfigAll.getVerylowStart());
		configData.setLow(formConfigAll.getLow());
		configData.setLowColor(formConfigAll.getLowColor());
		configData.setLowCondition(formConfigAll.getLowCondition());
		configData.setLowEnd(formConfigAll.getLowEnd());
		configData.setLowRating(formConfigAll.getLowRating());
		configData.setLowStart(formConfigAll.getLowStart());
		configData.setMedium(formConfigAll.getMedium());
		configData.setMediumColor(formConfigAll.getMediumColor());
		configData.setMediumCondition(formConfigAll.getMediumCondition());
		configData.setMediumEnd(formConfigAll.getMediumEnd());
		configData.setMediumRating(formConfigAll.getMediumRating());
		configData.setMediumStart(formConfigAll.getMediumStart());
		configData.setHigh(formConfigAll.getHigh());
		configData.setHighColor(formConfigAll.getHighColor());
		configData.setHighCondition(formConfigAll.getHighCondition());
		configData.setHighEnd(formConfigAll.getHighEnd());
		configData.setHighRating(formConfigAll.getHighRating());
		configData.setHighStart(formConfigAll.getHighStart());
		configData.setVeryhigh(formConfigAll.getVeryhigh());
		configData.setVeryhighColor(formConfigAll.getVeryhighColor());
		configData.setVeryhighCondition(formConfigAll.getVeryhighCondition());
		configData.setVeryhighEnd(formConfigAll.getVeryhighEnd());
		configData.setVeryhighRating(formConfigAll.getVeryhighRating());
		configData.setVeryhighStart(formConfigAll.getVeryhighStart());			
		iaRiskFactorsConfigAllRepository.save(configData);
		
		for (IaRiskFactorsConfig irfc : form.getIaRiskFactorsConfigList()) {
			int030103JdbcRepository.listUpdatePercent(irfc);			
		}
	}
}
