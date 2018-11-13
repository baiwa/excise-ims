package th.co.baiwa.excise.ws;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAud;
import th.co.baiwa.excise.ia.service.IncomeExciseAudService;
import th.co.baiwa.excise.ta.persistence.vo.PlanFromWsVo;
import th.co.baiwa.excise.ta.service.PlanFromWsHeaderService;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.IncFri8000Res;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.IncomeList8000Res;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.ResponseData8000Res;
//import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicenseList6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.ResponseData6020;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.RegMaster60List;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.Regfri4000Res;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.ResponseDataRes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebServiceTest {

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private IncomeExciseAudService incomeExciseAudService;
	
	@Autowired
	private PlanFromWsHeaderService planFromWsHeaderService;
	
	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;
	

	//@Test
	public void IncFri8020() {
		IncomeExciseAud incomeExciseAud = new IncomeExciseAud();
		incomeExciseAud.setStartMonth("201801");
		incomeExciseAud.setEndMonth("201810");
		incomeExciseAud = incomeExciseAudService.createIncomeExciseAud(incomeExciseAud);
		if(BeanUtils.isNotEmpty(incomeExciseAud)) {
			System.out.println(incomeExciseAud.getIaIncomeExciseAudId());
		}else {
			System.out.println("insert Fail.");
		}

	}
	@Test
	public void findExciseIdOrderByPercenTax() {
		Calendar calendar = Calendar.getInstance(DateConstant.LOCAL_TH);
		calendar.set(Integer.parseInt("2561"), 8, 30);
		exciseTaxReceiveDao.queryMonthShotName(calendar.getTime(), 12);
		
		PlanFromWsVo planFromWsVo = new PlanFromWsVo();
		planFromWsVo.setDateFrom("10/2560");
		planFromWsVo.setDateTo("09/2561");
		planFromWsVo.setMonthNonPay("2");
		planFromWsVo.setSymbol1("<");
		planFromWsVo.setPercent1("-40");
		planFromWsVo.setSymbol1(">");
		planFromWsVo.setPercent2("10");
		try {
			planFromWsHeaderService.findExciseIdOrderByPercenTax(planFromWsVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// @Test
	public void LicFri6010() {
		LicFri6020 re = webServiceExciseService.licFri6020("0105555155742", "", "1", "1000");
		ResponseData6020 xxx = re.getResponseData();
		for (LicenseList6020 income : xxx.getLicenseList()) {
			System.out.println("LicName : " + income.getLicName());
		}
	}

	// @Test
	public void regfri4000() {
		Regfri4000Res re = webServiceExciseService.IncFri4000("1", "1", "1", "100");
		ResponseDataRes xxx = re.getResponseData();
		for (RegMaster60List income : xxx.getRegMaster60List()) {
			System.out.println("LicName : " + income.getCusFullname());
		}
	}

	// @Test
	public void regfri8000() {
		IncFri8000Res re = webServiceExciseService.IncFri8000("201802", "201808", "Income", "1", "10");
		ResponseData8000Res xxx = re.getResponseData();
		for (IncomeList8000Res income : xxx.getIncomeList()) {
			System.out.println("LicName : " + income.getReceiptDate());
		}
	}
	// @Test
	// public void webServiceLdap() {
	// Response response = webServiceExciseService.webServiceLdap("kek1",
	// "password");
	// Gson gson = new Gson();
	// System.out.println(gson.toJson(response));
	// }
}