package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFeeChild;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeChildRepository;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061103Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int061103VoChild;

@Service
public class Int061103Service {

	@Autowired
	private TuitionFeeRepository tuitionFeeRepository;
	
	@Autowired
	private TuitionFeeChildRepository childRepository;
	
	public void save(Int061103Vo form){
		
		TuitionFee master = new TuitionFee();
		master.setName(form.getName());
		master.setPition(form.getPition());
		master.setBelong(form.getBelong());	
		master.setMate(form.getMate());
		master.setMateDescription(form.getMateDescription());
		master.setPitionMate(form.getPitionMate());
		master.setBelongMate(form.getBelongMate());
		master.setStatus(form.getStatus());
		master.setType(form.getType());
		master.setTypeRecive(form.getTypeRecive());
		master.setTypeReciveAmount(new BigDecimal(form.getTypeReciveAmount()));
		master.setOffer(form.getOffer());
		master.setOfferType(form.getOfferType());
		master.setSumAmount(new BigDecimal(form.getSumAmount()));
		TuitionFee masterResult = tuitionFeeRepository.save(master);
		
		List<Int061103VoChild> childForms= form.getItems();		
		List<TuitionFeeChild>  childs = new ArrayList<>();
		
		for (Int061103VoChild childForm : childForms) {
			TuitionFeeChild child = new  TuitionFeeChild();
			
			child.setIaTuitionFeeId(masterResult.getId());
			child.setName(childForm.getName());
			child.setBirth(DateConstant.convertStrToDate(StringUtils.trim(childForm.getBirth()), DateConstant.DD_MM_YYYY, DateConstant.LOCAL_TH));
			
			// ==> check blank
			childForm.setOrderFather((StringUtils.isBlank(childForm.getOrderFather()) ? "0" : childForm.getOrderFather()));
			childForm.setOrderMather((StringUtils.isBlank(childForm.getOrderMather()) ? "0" : childForm.getOrderMather()));
			childForm.setOrderReplace((StringUtils.isBlank(childForm.getOrderReplace()) ? "0" : childForm.getOrderReplace()));
			
			child.setOrderFather(new BigDecimal(childForm.getOrderFather()));
			child.setOrderMather(new BigDecimal(childForm.getOrderMather()));
			child.setOrderReplace(new BigDecimal(childForm.getOrderReplace()));
			child.setNameReplace(childForm.getNameReplace());
			child.setBirthReplace(DateConstant.convertStrToDate(StringUtils.trim(childForm.getBirthReplace()), DateConstant.DD_MM_YYYY, DateConstant.LOCAL_TH));
			child.setDateDeadReplace(DateConstant.convertStrToDate(StringUtils.trim(childForm.getDateDeadReplace()), DateConstant.DD_MM_YYYY, DateConstant.LOCAL_TH));
			child.setEducation(childForm.getEducation());
			child.setEducationProvince(childForm.getEducationProvince());
			child.setEducationDistrict(childForm.getEducationDistrict());
			child.setEducationClass(childForm.getEducationClass());
			child.setAmount(new BigDecimal(childForm.getAmount()));	
			childs.add(child);
			
		}
		childRepository.save(childs);				
	}

}

