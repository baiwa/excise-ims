package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;

@Service
public class UpdateStatusRiskFactorsService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateStatusRiskFactorsService.class);

//	****************** JDBC ****************** 


//	****************** JPA ****************** 
	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;


	
	public BigDecimal updateStatusIaRiskFactors(BigDecimal idFactors,String statusScreen) {
		if (idFactors != null) {
			if(false) {
			
			} else {
				
				IaRiskFactors entity = iaRiskFactorsRepository.findById(idFactors).get();
				
				entity.setStatusScreen(statusScreen);
				entity.setDateCriteria(ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.DD_MM_YYYY));
				
				iaRiskFactorsRepository.save(entity);
				
			}
		}
		
		return idFactors;
	}
	
	public BigDecimal downStatusIaRiskFactors(BigDecimal idHdr) {
	

		return idHdr;
	}
	

}
