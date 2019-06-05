package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaHRepository;
import th.go.excise.ims.ta.vo.TaPaperBaHFormVo;

@Service
public class PaperBaHService {
	
	@Autowired
	private TaPaperBaHRepository paperBaHRepository;

	public void insertBaH(TaPaperBaHFormVo form) {
		TaPaperBaH paperBaH = new TaPaperBaH();
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (StringUtils.isNotEmpty(form.getPaperBaNumber())) {
			paperBaH = paperBaHRepository.findByPaperBaNumber(form.getPaperBaNumber());
		}
		paperBaH.setOfficeCode(officeCode);
		paperBaH.setBudgetYear(form.getBudgetYear());
		
		String paperBaNumber = "";
		
		paperBaH.setPlanNumber(form.getPlanNumber());
		paperBaH.setAuditPlanCode(form.getAuditPlanCode());
		paperBaH.setPaperBaNumber(paperBaNumber);
		paperBaH.setAuditPlanCode(form.getAuditPlanCode());
		paperBaH.setNewRegId(form.getNewRegId());
		paperBaH.setDutyGroupId(form.getDutyGroupId());
		paperBaH.setBaDateStart(ConvertDateUtils.parseStringToLocalDate("01/"+form.getBaDateStart(), ConvertDateUtils.DD_MM_YYYY));
		
		paperBaH.setBaDateEnd(LocalDate.of(Integer.parseInt(form.getBaDateEnd().substring(3 , 7)), Integer.parseInt(form.getBaDateEnd().substring(0,2)), 1).with(TemporalAdjusters.lastDayOfMonth()));
		paperBaH.setBaText(form.getBaText());
		
		paperBaHRepository.save(paperBaH);
	}
	
	public TaPaperBaH findBaH(TaPaperBaHFormVo form) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		TaPaperBaH paperBaH = paperBaHRepository.findByNewRegIdAndOfficeCodeAndBudgetYear(form.getNewRegId(), officeCode, form.getBudgetYear());
		if (null == paperBaH) {
			paperBaH = new TaPaperBaH();
		}
		return paperBaH;
	}

}
