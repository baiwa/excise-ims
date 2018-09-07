package th.co.baiwa.excise.ws;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import baiwa.co.th.ws.LoginLdap;
import baiwa.co.th.ws.Response;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcNocDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcNocDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcPenDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ws.entity.api.RequestServiceExcise;
import th.co.baiwa.excise.ws.entity.api.ResponseServiceExcise;
import th.co.baiwa.excise.ws.entity.reques.IncFri8020;

@Service
public class WebServiceExciseService {

	private static final Logger logger = LoggerFactory.getLogger(WebServiceExciseService.class);

	@Value("${ws.excise.ipaddress}")
	private String ipAddress;

	@Value("${ws.excise.username}")
	private String username;

	@Value("${ws.excise.password}")
	private String password;

	@Value("${ws.excise.systemid}")
	private String systemId;

	@Value("${ws.excise.endpointIncFri8020}")
	private String endpointIncFri8020;
	
	
	@Autowired
	private LoginLdap loginLdapProxy;
	

	public Response webServiceLdap(String user , String pass) {
		Response response = loginLdapProxy.login(user, pass);
		System.out.println(response.toString());
		return response;
	}
	
	private String restfulService(String endPoint, Object object ) {

		RequestServiceExcise requestRestful = new RequestServiceExcise();
		requestRestful.setSystemid(systemId);
		requestRestful.setUsername(username);
		requestRestful.setPassword(password);
		requestRestful.setIpaddress(ipAddress);
		requestRestful.setRequestData(object);
		Gson gson = new Gson();
		String json = gson.toJson(requestRestful);
		logger.info("Body Service request : "+ json);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
		logger.info("Body Service response: "+ response.getBody());
		return response.getBody();
	}

	public ResponseServiceExcise IncFri8020(String officeCode, String yearMonthFrom, String yearMonthTo, String dateType, String pageNo, String dataPerPage) {
		logger.info("restful API : IncFri8020");
		IncFri8020 incFri8020 = new IncFri8020();
		incFri8020.setOfficeCode(officeCode);
		incFri8020.setYearMonthFrom(yearMonthFrom);
		incFri8020.setYearMonthTo(yearMonthTo);
		incFri8020.setDateType(dateType);
		incFri8020.setPageNo(pageNo);
		incFri8020.setDataPerPage(dataPerPage);
		String responseData = restfulService(endpointIncFri8020, incFri8020);
		Gson gson = new Gson();
		ResponseServiceExcise responseServiceExcise = gson.fromJson(responseData, ResponseServiceExcise.class);
		return responseServiceExcise;
	}
	
	
	public List<RiskAssRiskWsDtl> getRiskAssRiskWsDtlList(RiskAssRiskWsDtl riskAssRiskWsDtl) {
		List<RiskAssRiskWsDtl> riskAssRiskWsDtlList = new ArrayList<RiskAssRiskWsDtl>();
		RiskAssRiskWsDtl risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(1));
		risk.setProjectBase(
				"โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartmentName("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);

		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(2));
		risk.setProjectBase(
				"โครงการจัดหาเครื่องไมโครคอมพิวเตอร์ เครื่องคอมพิวเตอร์แบบพกพา และอุปกรณ์เพื่อทดแทนเครื่องเดิมและเพิ่มเติมเพื่อใช้ในการปฏิบัติงาน");
		risk.setDepartmentName("ศทส.");
		risk.setBudget(new BigDecimal(40800000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(3000000));
		risk.setSumMonth(new BigDecimal(43800000));
		risk.setApproveBudget(new BigDecimal(46800000));
		riskAssRiskWsDtlList.add(risk);

		
		return riskAssRiskWsDtlList;
	}

	public List<RiskAssInfDtl> getRiskAssInfDtlList(RiskAssInfDtl riskAssInfDtl) {
		List<RiskAssInfDtl> riskAssInfDtlList = new ArrayList<RiskAssInfDtl>();

		RiskAssInfDtl risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(1));
		risk.setInfName("ระบบงาน E-Services ยื่นแบบผ่านอินเตอร์เน็ต UAT https://edserver2-uat.excise.go.th/staesw");
		risk.setJan(new BigDecimal(10));
		risk.setFeb(new BigDecimal(12));
		risk.setMar(new BigDecimal(13));
		risk.setApril(new BigDecimal(7));
		risk.setMay(new BigDecimal(22));
		risk.setJun(new BigDecimal(28));
		risk.setJul(new BigDecimal(16));
		risk.setAug(new BigDecimal(0));
		risk.setSep(new BigDecimal(20));
		risk.setOct(new BigDecimal(18));
		risk.setNov(new BigDecimal(7));
		risk.setDec(new BigDecimal(9));
		risk.setTotal(new BigDecimal(162));
		riskAssInfDtlList.add(risk);

		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(2));
		risk.setInfName("เว็บไซต์กรมสรรพสามิต www.excise.go.th");
		risk.setJan(new BigDecimal(15));
		risk.setFeb(new BigDecimal(8));
		risk.setMar(new BigDecimal(14));
		risk.setApril(new BigDecimal(21));
		risk.setMay(new BigDecimal(0));
		risk.setJun(new BigDecimal(18));
		risk.setJul(new BigDecimal(14));
		risk.setAug(new BigDecimal(17));
		risk.setSep(new BigDecimal(9));
		risk.setOct(new BigDecimal(11));
		risk.setNov(new BigDecimal(1));
		risk.setDec(new BigDecimal(16));
		risk.setTotal(new BigDecimal(144));
		riskAssInfDtlList.add(risk);

		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(3));
		risk.setInfName("ระบบงานอีเมล์กรมสรรพสามิต http://mail.excise.go.th");
		risk.setJan(new BigDecimal(8));
		risk.setFeb(new BigDecimal(11));
		risk.setMar(new BigDecimal(18));
		risk.setApril(new BigDecimal(9));
		risk.setMay(new BigDecimal(13));
		risk.setJun(new BigDecimal(18));
		risk.setJul(new BigDecimal(21));
		risk.setAug(new BigDecimal(14));
		risk.setSep(new BigDecimal(10));
		risk.setOct(new BigDecimal(8));
		risk.setNov(new BigDecimal(5));
		risk.setDec(new BigDecimal(3));
		risk.setTotal(new BigDecimal(138));
		riskAssInfDtlList.add(risk);

		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(4));
		risk.setInfName("ระบบงานสารสนเทศหลัก http://Web.excise.go.th/EDINTRAWeb");
		risk.setJan(new BigDecimal(9));
		risk.setFeb(new BigDecimal(13));
		risk.setMar(new BigDecimal(9));
		risk.setApril(new BigDecimal(20));
		risk.setMay(new BigDecimal(13));
		risk.setJun(new BigDecimal(0));
		risk.setJul(new BigDecimal(14));
		risk.setAug(new BigDecimal(18));
		risk.setSep(new BigDecimal(0));
		risk.setOct(new BigDecimal(8));
		risk.setNov(new BigDecimal(11));
		risk.setDec(new BigDecimal(13));
		risk.setTotal(new BigDecimal(128));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(5));
		risk.setInfName("ระบบความปลอดภัยกลาง (SSO) http://authen.excise.go.th/oiddas");
		risk.setJan(new BigDecimal(22));
		risk.setFeb(new BigDecimal(0));
		risk.setMar(new BigDecimal(12));
		risk.setApril(new BigDecimal(8));
		risk.setMay(new BigDecimal(11));
		risk.setJun(new BigDecimal(9));
		risk.setJul(new BigDecimal(2));
		risk.setAug(new BigDecimal(4));
		risk.setSep(new BigDecimal(3));
		risk.setOct(new BigDecimal(7));
		risk.setNov(new BigDecimal(0));
		risk.setDec(new BigDecimal(9));
		risk.setTotal(new BigDecimal(87));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(6));
		risk.setInfName("ระบบงานกรมสรรพสามิต (Main Access)");
		risk.setJan(new BigDecimal(7));
		risk.setFeb(new BigDecimal(17));
		risk.setMar(new BigDecimal(9));
		risk.setApril(new BigDecimal(8));
		risk.setMay(new BigDecimal(5));
		risk.setJun(new BigDecimal(4));
		risk.setJul(new BigDecimal(11));
		risk.setAug(new BigDecimal(3));
		risk.setSep(new BigDecimal(7));
		risk.setOct(new BigDecimal(0));
		risk.setNov(new BigDecimal(4));
		risk.setDec(new BigDecimal(2));
		risk.setTotal(new BigDecimal(77));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(7));
		risk.setInfName("ระบบงานโครงการรถยนต์ใหม่คันแรก (อินเตอร์เน็ต) https://firstcar.excise.go.th");
		risk.setJan(new BigDecimal(9));
		risk.setFeb(new BigDecimal(4));
		risk.setMar(new BigDecimal(12));
		risk.setApril(new BigDecimal(8));
		risk.setMay(new BigDecimal(9));
		risk.setJun(new BigDecimal(4));
		risk.setJul(new BigDecimal(7));
		risk.setAug(new BigDecimal(3));
		risk.setSep(new BigDecimal(0));
		risk.setOct(new BigDecimal(6));
		risk.setNov(new BigDecimal(2));
		risk.setDec(new BigDecimal(1));
		risk.setTotal(new BigDecimal(65));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(8));
		risk.setInfName("ระบบงานโครงการรถยนต์ใหม่คันแรก (อินทราเน็ต) http://ed-firstcar.excise.go.th");
		risk.setJan(new BigDecimal(2));
		risk.setFeb(new BigDecimal(6));
		risk.setMar(new BigDecimal(3));
		risk.setApril(new BigDecimal(0));
		risk.setMay(new BigDecimal(4));
		risk.setJun(new BigDecimal(7));
		risk.setJul(new BigDecimal(5));
		risk.setAug(new BigDecimal(9));
		risk.setSep(new BigDecimal(1));
		risk.setOct(new BigDecimal(2));
		risk.setNov(new BigDecimal(4));
		risk.setDec(new BigDecimal(0));
		risk.setTotal(new BigDecimal(43));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(9));
		risk.setInfName("ระบบงานสารบรรณบูรณาการ http://192.168.3.123.8080/EDCSClient Web/pages/publile");
		risk.setJan(new BigDecimal(0));
		risk.setFeb(new BigDecimal(5));
		risk.setMar(new BigDecimal(7));
		risk.setApril(new BigDecimal(2));
		risk.setMay(new BigDecimal(1));
		risk.setJun(new BigDecimal(8));
		risk.setJul(new BigDecimal(1));
		risk.setAug(new BigDecimal(0));
		risk.setSep(new BigDecimal(1));
		risk.setOct(new BigDecimal(0));
		risk.setNov(new BigDecimal(8));
		risk.setDec(new BigDecimal(1));
		risk.setTotal(new BigDecimal(34));
		riskAssInfDtlList.add(risk);
		
		risk = new RiskAssInfDtl();
		risk.setRiskAssInfDtlId(new Long(10));
		risk.setInfName("ระบบงานสารสนเทศกฏหมายภาษีสรรพสามิต http://law.excise.go.th/exciselaw");
		risk.setJan(new BigDecimal(4));
		risk.setFeb(new BigDecimal(2));
		risk.setMar(new BigDecimal(1));
		risk.setApril(new BigDecimal(0));
		risk.setMay(new BigDecimal(2));
		risk.setJun(new BigDecimal(2));
		risk.setJul(new BigDecimal(3));
		risk.setAug(new BigDecimal(2));
		risk.setSep(new BigDecimal(3));
		risk.setOct(new BigDecimal(4));
		risk.setNov(new BigDecimal(1));
		risk.setDec(new BigDecimal(2));
		risk.setTotal(new BigDecimal(26));
		riskAssInfDtlList.add(risk);


		return riskAssInfDtlList;
	}

	public List<RiskAssExcAreaDtl> getRiskAssExcAreaDtlList(RiskAssExcAreaDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcAreaDtl> riskAssExcAreaDtlList = new ArrayList<RiskAssExcAreaDtl>();
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ. พระนครศรีอยุธยา 2");
		riskAssExcAreaDtl.setYears(new  BigDecimal(10));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2551");
		riskAssExcAreaDtl.setCloseDate("01/02/2551");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ. อ่างทอง");
		riskAssExcAreaDtl.setYears(new  BigDecimal(9));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2552");
		riskAssExcAreaDtl.setCloseDate("01/02/2552");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.จันทบุรี");
		riskAssExcAreaDtl.setYears(new  BigDecimal(8));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2553");
		riskAssExcAreaDtl.setCloseDate("01/02/2553");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ฉะเชิงเทรา");
		riskAssExcAreaDtl.setYears(new  BigDecimal(7));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2554");
		riskAssExcAreaDtl.setCloseDate("01/02/2554");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ชลบุรี 1");
		riskAssExcAreaDtl.setYears(new  BigDecimal(6));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2555");
		riskAssExcAreaDtl.setCloseDate("01/02/2555");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ชลบุรี 2");
		riskAssExcAreaDtl.setYears(new  BigDecimal(5));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2556");
		riskAssExcAreaDtl.setCloseDate("01/02/2556");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ตราด");
		riskAssExcAreaDtl.setYears(new  BigDecimal(4));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2557");
		riskAssExcAreaDtl.setCloseDate("01/02/2557");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.นครนายก");
		riskAssExcAreaDtl.setYears(new  BigDecimal(3));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2558");
		riskAssExcAreaDtl.setCloseDate("01/02/2558");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ปราจีนบุรี");
		riskAssExcAreaDtl.setYears(new  BigDecimal(2));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2559");
		riskAssExcAreaDtl.setCloseDate("01/02/2559");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ระยอง 1");
		riskAssExcAreaDtl.setYears(new  BigDecimal(1));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2560");
		riskAssExcAreaDtl.setCloseDate("01/02/2560");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);
		
		
		
		return riskAssExcAreaDtlList;
	}
	
	
	public List<RiskAssExcRecDtl> RiskAssExcAreaDtlByWebService(RiskAssExcRecDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcRecDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcRecDtl>();
		double resultsIncome = 0 , budgetIncome = 0 , diff = 0;
		String depName = "";
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ. พระนครศรีอยุธยา 2";
		resultsIncome = 3.494;
		budgetIncome = 6.651;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ. อ่างทอง";
		resultsIncome = 3.138;
		budgetIncome = 5.235;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.จันทบุรี";
		resultsIncome = 71.027;
		budgetIncome = 96.134;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ฉะเชิงเทรา";
		resultsIncome = 13239.131;
		budgetIncome = 17345.864;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ชลบุรี 1";
		resultsIncome = 18.877;
		budgetIncome = 18.548;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ชลบุรี 2";
		resultsIncome = 13145.039;
		budgetIncome = 12982.919;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ตราด";
		resultsIncome = 2527.32;
		budgetIncome = 2496.388;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.นครนายก";
		resultsIncome = 64.635;
		budgetIncome = 27.745;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ปราจีนบุรี";
		resultsIncome = 588.558;
		budgetIncome = 288.015;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcRecDtl();
		depName = "สสพ.ระยอง 1";
		resultsIncome = 18.556;
		budgetIncome = 11.769;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		return riskAssExcAreaDtlLi;
	}
	
	
	public List<RiskAssExcPenDtl> riskAssExcAreaDtlByWebService2(RiskAssExcPenDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcPenDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcPenDtl>();
		double resultsIncome = 0 , budgetIncome = 0 , diff = 0;
		String depName = "";
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ. พระนครศรีอยุธยา 2";
		resultsIncome = 106095;
		budgetIncome = 1516393;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ. อ่างทอง";
		resultsIncome = 718867.5;
		budgetIncome = 6016497;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.จันทบุรี";
		resultsIncome = 5831447.7;
		budgetIncome = 27864350;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ฉะเชิงเทรา";
		resultsIncome = 1428854.64;
		budgetIncome = 6328000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ชลบุรี 1";
		resultsIncome = 4286609.17;
		budgetIncome = 4200000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ชลบุรี 2";
		resultsIncome = 4286609.17;
		budgetIncome = 4200000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ตราด";
		resultsIncome = 4286609.17;
		budgetIncome = 4200000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.นครนายก";
		resultsIncome = 3379999.7;
		budgetIncome = 1080000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ปราจีนบุรี";
		resultsIncome = 2523877.4;
		budgetIncome = 887000;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcPenDtl();
		depName = "สสพ.ระยอง 1";
		resultsIncome = 4112468;
		budgetIncome = 1898746;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		return riskAssExcAreaDtlLi;
	}
	public List<RiskAssExcNocDtl> riskAssExcAreaDtlByWebService3(RiskAssExcNocDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcNocDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcNocDtl>();
		double resultsIncome = 0 , budgetIncome = 0 , diff = 0;
		String depName = "";
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ. พระนครศรีอยุธยา 2";
		resultsIncome = 116;
		budgetIncome = 177;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ. อ่างทอง";
		resultsIncome = 113;
		budgetIncome = 144;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.จันทบุรี";
		resultsIncome = 760;
		budgetIncome = 1077;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ฉะเชิงเทรา";
		resultsIncome = 232;
		budgetIncome = 270;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ชลบุรี 1";
		resultsIncome = 226;
		budgetIncome = 217;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ชลบุรี 2";
		resultsIncome = 683;
		budgetIncome = 675;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ตราด";
		resultsIncome = 441;
		budgetIncome = 436;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.นครนายก";
		resultsIncome = 169;
		budgetIncome = 48;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ปราจีนบุรี";
		resultsIncome = 565;
		budgetIncome = 288;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		riskAssExcAreaDtl = new RiskAssExcNocDtl();
		depName = "สสพ.ระยอง 1";
		resultsIncome = 558;
		budgetIncome = 360;
		diff = resultsIncome - budgetIncome;
		riskAssExcAreaDtl.setDepartmentName(depName);
		riskAssExcAreaDtl.setResultsIncome(new BigDecimal(resultsIncome));
		riskAssExcAreaDtl.setBudgetIncome(new BigDecimal(budgetIncome));
		riskAssExcAreaDtl.setBudgetDiff(new BigDecimal(diff));
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff*100/budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);
		
		
		return riskAssExcAreaDtlLi;
	}

}
