package th.co.baiwa.excise.ws;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;

@Service
public class WebServiceExciseService {
	
	
	
	public List<RiskAssRiskWsDtl> getRiskAssRiskWsDtlList(RiskAssRiskWsDtl riskAssRiskWsDtl){
		List<RiskAssRiskWsDtl> riskAssRiskWsDtlList = new ArrayList<RiskAssRiskWsDtl>();
		RiskAssRiskWsDtl risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(1));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);
		
		
		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(2));
		risk.setProjectBase("โครงการจัดหาเครื่องไมโครคอมพิวเตอร์ เครื่องคอมพิวเตอร์แบบพกพา และอุปกรณ์เพื่อทดแทนเครื่องเดิมและเพิ่มเติมเพื่อใช้ในการปฏิบัติงาน");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(40800000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(3000000));
		risk.setSumMonth(new BigDecimal(43800000));
		risk.setApproveBudget(new BigDecimal(46800000));
		riskAssRiskWsDtlList.add(risk);
		
		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(3));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);
		
		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(4));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);
		
		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(5));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);
		
		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(6));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartment("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);
		return riskAssRiskWsDtlList;
	}
	
}
