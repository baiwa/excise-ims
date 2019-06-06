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
		String paperBaNumber = "";
		
		paperBaH.setOfficeCode(officeCode);
		paperBaH.setBudgetYear(form.getBudgetYear());
		paperBaH.setPlanNumber(form.getPlanNumber());
		paperBaH.setAuditPlanCode(form.getAuditPlanCode());
		paperBaH.setPaperBaNumber(paperBaNumber);
		paperBaH.setNewRegId(form.getNewRegId());
		paperBaH.setDutyGroupId(form.getDutyGroupId());
//		paperBaH.setStartDate(form.getStartDate());
//		paperBaH.setEndDate(form.getEndDate());
		paperBaH.setMonthIncType(form.getMonthIncType());
		paperBaH.setYearIncType(form.getYearIncType());
		paperBaH.setYearNum(Integer.parseInt(form.getYearNum()));
		paperBaH.setAnaResultText1(form.getAnaResultText1());
		paperBaH.setAnaResultText2(form.getAnaResultText2());
		paperBaH.setAnaResultText3(form.getAnaResultText3());
		paperBaH.setAnaResultText4(form.getAnaResultText4());
		paperBaH.setAnaResultText5(form.getAnaResultText5());
		paperBaH.setAnaResultText6(form.getAnaResultText6());
		paperBaH.setAnaResultText7(form.getAnaResultText7());
		paperBaH.setAnaResultText8(form.getAnaResultText8());
		
//		paperBaH.setBaDateStart(ConvertDateUtils.parseStringToLocalDate("01/"+form.getBaDateStart(), ConvertDateUtils.DD_MM_YYYY));
//		
//		paperBaH.setBaDateEnd(LocalDate.of(Integer.parseInt(form.getBaDateEnd().substring(3 , 7)), Integer.parseInt(form.getBaDateEnd().substring(0,2)), 1).with(TemporalAdjusters.lastDayOfMonth()));
//		paperBaH.setBaText(form.getBaText());
		
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
