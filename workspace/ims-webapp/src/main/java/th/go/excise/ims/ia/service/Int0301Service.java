package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public void saveRiskFactorsLevel(Int0301FormVo form) {
		int0301JdbcRepository.saveRiskFactorsLevel(form);
	}
	
	public void saveRiskFactorsConfig(Int0301FormVo form) {		
		IaRiskFactorsConfig entity = new IaRiskFactorsConfig();	
		IaRiskFactorsConfig formConfig = form.getIaRiskFactorsConfig();
		entity = iaRiskFactorsConfigRepository.findById(formConfig.getId()).get();
		entity.setIdFactors(formConfig.getIdFactors());	
		entity.setVerylow(formConfig.getVerylow());
		entity.setVerylowStart(formConfig.getVerylowStart());
		entity.setVerylowEnd(formConfig.getVerylowEnd());
		entity.setVerylowRating(formConfig.getVerylowRating());	
		entity.setVerylowCondition(formConfig.getVerylowCondition());
		
		Date startDate = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setStartDate(startDate);
		
		Date endDate = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setEndDate(endDate);
		
		iaRiskFactorsConfigRepository.save(entity);
		
	}

}
