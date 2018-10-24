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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOv3dDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcPenDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssPerDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ws.entity.api.RequestServiceExcise;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.req.RequestData8000Req;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.IncFri8000Res;
import th.co.baiwa.excise.ws.entity.response.assessment.Assessment;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.regfri4000.req.RequestDataReq;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.Regfri4000Res;

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

	@Value("${ws.excise.endpointLicFri6010}")
	private String endpointLicFri6010;

	@Value("${ws.excise.endpointLicFri6020}")
	private String endpointLicFri6020;

	@Value("${ws.excise.endpointIncFri8000}")
    private String endpointIncFri8000;

    @Value("${ws.excise.endpointRegFri4000}")
    private String endpointRegFri4000;

	@Value("${os.excise.applicationId}")
	private String applicationId;
	
	@Value("${ws.excise.get.assessment}")
	private String endpointAssessment;

	@Autowired
	private LoginLdap loginLdapProxy;

//	@Autowired
//	private LDPAGAuthenAndGetUserRolePortType ldapgAuthenAndGetUserRolePortTypeProxy;

//	public AuthenAndGetUserRoleResponse ldpagAuthenAndGetUserRoleServiceProxy(String user, String pass) {
//		logger.info("Excise ldpagAuthenAndGetUserRoleServiceProxy : {} ", user);
//		AuthenAndGetUserRoleRequest wsLdap = new AuthenAndGetUserRoleRequest();
//		wsLdap.setUserId(user);
//		wsLdap.setPassword(pass);
//		wsLdap.setApplicationId(applicationId);
//		AuthenAndGetUserRoleResponse response = ldapgAuthenAndGetUserRolePortTypeProxy.ldpagAuthenAndGetUserRoleOperation(wsLdap);
//		return response;
//	}

	public Response webServiceLdap(String user, String pass) {
		logger.info("Baiwa webServiceLdap : {} ", user);
		Response response = loginLdapProxy.login(user, pass);
		logger.info(response.toString());
		return response;
	}

	private String postRestful(String endPoint, Object object) {
		RequestServiceExcise requestRestful = new RequestServiceExcise();
		requestRestful.setSystemid(systemId);
		requestRestful.setUsername(username);
		requestRestful.setPassword(password);
		requestRestful.setIpaddress(ipAddress);
		requestRestful.setRequestData(object);
		Gson gson = new Gson();
		String json = gson.toJson(requestRestful);
		logger.info("Body Service request : " + json);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
		logger.info("Body Service response: " + response.getBody());
		return response.getBody();
	}
	private String getRestful(String endPoint) {
		logger.info("Restful HTTP GET : to" + endPoint);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.GET, entity, String.class);
		logger.info("Body Service response: " + response.getBody());
		return response.getBody();
	}

	public IncFri8020 IncFri8020(String officeCode, String yearMonthFrom, String yearMonthTo, String dateType, String pageNo, String dataPerPage) {
		logger.info("restful API : IncFri8020");
		IncFri8020 incFri8020 = new IncFri8020();
		incFri8020.setOfficeCode(officeCode);
		incFri8020.setYearMonthFrom(yearMonthFrom);
		incFri8020.setYearMonthTo(yearMonthTo);
		incFri8020.setDateType(dateType);
		incFri8020.setPageNo(pageNo);
		incFri8020.setDataPerPage(dataPerPage);
		String responseData = postRestful(endpointIncFri8020, incFri8020);
		Gson gson = new Gson();
		IncFri8020 responseServiceExcise = gson.fromJson(responseData, IncFri8020.class);
		return responseServiceExcise;
	}
	public Regfri4000Res IncFri4000(String type, String active, String pageNo, String dataPerPage) {
		logger.info("restful API : IncFri4000");
		RequestDataReq regfri4000Req = new RequestDataReq();
		regfri4000Req.setType(type);
		regfri4000Req.setActive(active);
		regfri4000Req.setNid("");
		regfri4000Req.setNewregId("");
		regfri4000Req.setActive(active);
		regfri4000Req.setPageNo(pageNo);
		regfri4000Req.setDataPerPage(dataPerPage);
		
		String responseData = postRestful(endpointRegFri4000, regfri4000Req);
		Gson gson = new Gson();
		Regfri4000Res responseServiceExcise = gson.fromJson(responseData, Regfri4000Res.class);
		return responseServiceExcise;
	}
	public IncFri8000Res IncFri8000(String yearMonthFrom, String yearMonthTo, String dateType, String pageNo, String dataPerPage) {
		logger.info("restful API : IncFri8000Res");
		RequestData8000Req rFri8000Req = new RequestData8000Req();
		rFri8000Req.setYearMonthFrom(yearMonthFrom);
		rFri8000Req.setYearMonthTo(yearMonthTo);
		rFri8000Req.setDateType(dateType);
		rFri8000Req.setPageNo(pageNo);
		rFri8000Req.setDataPerPage(dataPerPage);
		
		String responseData = postRestful(endpointIncFri8000, rFri8000Req);
		Gson gson = new Gson();
		IncFri8000Res responseServiceExcise = gson.fromJson(responseData, IncFri8000Res.class);
		return responseServiceExcise;
	}

	public LicFri6010 licFri6010(String officeCode, String yearMonthFrom, String yearMonthTo, String pageNo, String dataPerPage) {
		logger.info("restful API : LicFri6010");
		LicFri6010 licFri6010 = new LicFri6010();

		licFri6010.setOffcode(officeCode);
		licFri6010.setYearMonthFrom(yearMonthFrom);
		licFri6010.setYearMonthTo(yearMonthTo);
		licFri6010.setPageNo(pageNo);
		licFri6010.setDataPerPage(dataPerPage);
		String responseData = postRestful(endpointLicFri6010, licFri6010);
		Gson gson = new Gson();
		LicFri6010 responseServiceExcise = gson.fromJson(responseData, LicFri6010.class);
		return responseServiceExcise;
	}

	public LicFri6020 licFri6020(String nid, String newregId, String pageNo, String dataPerPage) {
		logger.info("restful API : LicFri6020");
		LicFri6020 licFri6020 = new LicFri6020();
		licFri6020.setNid(nid);
		licFri6020.setNewregId(newregId);
		licFri6020.setPageNo(pageNo);
		licFri6020.setDataPerPage(dataPerPage);
		String responseData = postRestful(endpointLicFri6020, licFri6020);
		Gson gson = new Gson();
		LicFri6020 responseServiceExcise = gson.fromJson(responseData, LicFri6020.class);
		return responseServiceExcise;
	}
	
	public Assessment assessmentForm1(String budgetYear ,String oCode ) {
		logger.info(" assessment restful API {} , {} " ,budgetYear,oCode);
		String responseData = getRestful(endpointAssessment).replaceAll("\\\\n", "<br/>");
		Gson gson = new Gson();
		Assessment responseServiceExcise = gson.fromJson(responseData, Assessment.class);
		return responseServiceExcise;
	}

	
	
	
	public List<RiskAssRiskWsDtl> getRiskAssRiskWsDtlList(RiskAssRiskWsDtl riskAssRiskWsDtl) {
		List<RiskAssRiskWsDtl> riskAssRiskWsDtlList = new ArrayList<RiskAssRiskWsDtl>();
		RiskAssRiskWsDtl risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(1));
		risk.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		risk.setDepartmentName("ศทส.");
		risk.setBudget(new BigDecimal(55000000));
		risk.setLocalBudget(new BigDecimal(0));
		risk.setOtherMoney(new BigDecimal(0));
		risk.setSumMonth(new BigDecimal(55000000));
		risk.setApproveBudget(new BigDecimal(55000000));
		riskAssRiskWsDtlList.add(risk);

		risk = new RiskAssRiskWsDtl();
		risk.setRiskAssRiskDtlId(new Long(2));
		risk.setProjectBase("โครงการจัดหาเครื่องไมโครคอมพิวเตอร์ เครื่องคอมพิวเตอร์แบบพกพา และอุปกรณ์เพื่อทดแทนเครื่องเดิมและเพิ่มเติมเพื่อใช้ในการปฏิบัติงาน");
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
		riskAssExcAreaDtl.setYears(new BigDecimal(10));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2551");
		riskAssExcAreaDtl.setCloseDate("01/02/2551");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ. อ่างทอง");
		riskAssExcAreaDtl.setYears(new BigDecimal(9));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2552");
		riskAssExcAreaDtl.setCloseDate("01/02/2552");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.จันทบุรี");
		riskAssExcAreaDtl.setYears(new BigDecimal(8));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2553");
		riskAssExcAreaDtl.setCloseDate("01/02/2553");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ฉะเชิงเทรา");
		riskAssExcAreaDtl.setYears(new BigDecimal(7));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2554");
		riskAssExcAreaDtl.setCloseDate("01/02/2554");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ชลบุรี 1");
		riskAssExcAreaDtl.setYears(new BigDecimal(6));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2555");
		riskAssExcAreaDtl.setCloseDate("01/02/2555");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ชลบุรี 2");
		riskAssExcAreaDtl.setYears(new BigDecimal(5));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2556");
		riskAssExcAreaDtl.setCloseDate("01/02/2556");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ตราด");
		riskAssExcAreaDtl.setYears(new BigDecimal(4));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2557");
		riskAssExcAreaDtl.setCloseDate("01/02/2557");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.นครนายก");
		riskAssExcAreaDtl.setYears(new BigDecimal(3));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2558");
		riskAssExcAreaDtl.setCloseDate("01/02/2558");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ปราจีนบุรี");
		riskAssExcAreaDtl.setYears(new BigDecimal(2));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2559");
		riskAssExcAreaDtl.setCloseDate("01/02/2559");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		riskAssExcAreaDtl = new RiskAssExcAreaDtl();
		riskAssExcAreaDtl.setDepartmentName("สสพ.ระยอง 1");
		riskAssExcAreaDtl.setYears(new BigDecimal(1));
		riskAssExcAreaDtl.setCheckOutDate("28/01/2560");
		riskAssExcAreaDtl.setCloseDate("01/02/2560");
		riskAssExcAreaDtlList.add(riskAssExcAreaDtl);

		return riskAssExcAreaDtlList;
	}

	public List<RiskAssExcRecDtl> RiskAssExcAreaDtlByWebService(RiskAssExcRecDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcRecDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcRecDtl>();
		double resultsIncome = 0, budgetIncome = 0, diff = 0;
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);

		return riskAssExcAreaDtlLi;
	}

	public List<RiskAssExcPenDtl> riskAssExcAreaDtlByWebService2(RiskAssExcPenDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcPenDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcPenDtl>();
		double resultsIncome = 0, budgetIncome = 0, diff = 0;
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);

		return riskAssExcAreaDtlLi;
	}

	public List<RiskAssExcNocDtl> riskAssExcAreaDtlByWebService3(RiskAssExcNocDtl riskAssExcAreaDtl) {
		logger.info("getRiskAssExcAreaDtlList WebService");
		List<RiskAssExcNocDtl> riskAssExcAreaDtlLi = new ArrayList<RiskAssExcNocDtl>();
		double resultsIncome = 0, budgetIncome = 0, diff = 0;
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
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
		riskAssExcAreaDtl.setPercenDiff(new BigDecimal(diff * 100 / budgetIncome));
		riskAssExcAreaDtlLi.add(riskAssExcAreaDtl);

		return riskAssExcAreaDtlLi;
	}

	public List<RiskAssExcOv3dDtl> riskAssExcOv3dDtlWS() {
		List<RiskAssExcOv3dDtl> list = new ArrayList<RiskAssExcOv3dDtl>();
		RiskAssExcOv3dDtl riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ. พระนครศรีอยุธยา 2");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(22));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ. อ่างทอง");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(18));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.จันทบุรี");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(17));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ฉะเชิงเทรา");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(12));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ชลบุรี 1");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(8));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ชลบุรี 2");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(8));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ตราด");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(6));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.นครนายก");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(3));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ปราจีนบุรี");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(2));
		list.add(riskAssExcOv3dDtl);

		riskAssExcOv3dDtl = new RiskAssExcOv3dDtl();
		riskAssExcOv3dDtl.setDepartmentName("สสพ.ระยอง 1");
		riskAssExcOv3dDtl.setRiskCost(new BigDecimal(0));
		list.add(riskAssExcOv3dDtl);

		return list;
	}

	public List<RiskAssPerDtl> riskAssPerDtllWS() {
		List<RiskAssPerDtl> list = new ArrayList<RiskAssPerDtl>();
		RiskAssPerDtl riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการจัดหาอุปกรณ์เครือข่ายสื่อสารและระบบความปลอดภัยเครือข่ายสำหรับอาคารศูนย์สำรองระบบเทคโนโลยีสารสนเทศ กรมสรรพสามิต (Network and Security System)");
		riskAssPerDtl.setDepartmentName("ศทส.");
		riskAssPerDtl.setRiskCost(new BigDecimal(720));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการจัดหาเครื่องไมโครคอมพิวเตอร์ เครื่องคอมพิวเตอร์แบบพกพา และอุปกรณ์เพื่อทดแทนเครื่องเดิมและเพิ่มเติมเพื่อใช้ในการปฏิบัติงาน");
		riskAssPerDtl.setDepartmentName("ศทส.");
		riskAssPerDtl.setRiskCost(new BigDecimal(622));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการติดตั้งมาตรวัดและคอมพิวเตอร์สื่อสารทางไกลมาใช้ในการบริหารจัดเก็บภาษีเครื่องดื่ม");
		riskAssPerDtl.setDepartmentName("สมฐ.1");
		riskAssPerDtl.setRiskCost(new BigDecimal(622));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการจัดหาระบบบริหารจัดการทรัพยากร ICT");
		riskAssPerDtl.setDepartmentName("ศทส.");
		riskAssPerDtl.setRiskCost(new BigDecimal(511));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการสำรวจราคาขายปลีกและจัดทำฐานข้อมูลราคาขายปลีกสำหรับสินค้าสุรา ยาสูบ และเครื่องดื่ม");
		riskAssPerDtl.setDepartmentName("สผษ.");
		riskAssPerDtl.setRiskCost(new BigDecimal(430));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการจัดซื้อพร้อมติดตั้งจำนวนผู้ใช้งาน(Concurrent User License) เพิ่มเติมระบบการจัดการข้อมูลของห้องปฏิบัติการทางวิทยาศาสตร์ (Laboratory Information Management System: LIMS)");
		riskAssPerDtl.setDepartmentName("กวข.");
		riskAssPerDtl.setRiskCost(new BigDecimal(222));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("ติดตั้งสื่อประชาสัมพันธ์ข้อมูลข่าวสาร กรมสรรพสามิต ระยะที่ 2");
		riskAssPerDtl.setDepartmentName("ศทส.");
		riskAssPerDtl.setRiskCost(new BigDecimal(212));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการ Excise Innovation Award ครั้งที่ 7");
		riskAssPerDtl.setDepartmentName("สบค.");
		riskAssPerDtl.setRiskCost(new BigDecimal(98));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการสร้างความรู้ความเข้าใจแก่ประชาชนอย่างมีประสิทธิภาพ");
		riskAssPerDtl.setDepartmentName("สลก.");
		riskAssPerDtl.setRiskCost(new BigDecimal(77));
		list.add(riskAssPerDtl);

		riskAssPerDtl = new RiskAssPerDtl();
		riskAssPerDtl.setProjectBase("โครงการสัมมนาหลักสูตรวิธีปฏิบัติราชการทางปกครองและการติดตามเงินคืนกรณีโครงการรถยนต์คันแรก");
		riskAssPerDtl.setDepartmentName("สกม.");
		riskAssPerDtl.setRiskCost(new BigDecimal(54));
		list.add(riskAssPerDtl);

		return list;
	}

}
