package th.co.baiwa.excise.ws;

import java.math.BigDecimal;
import java.nio.charset.Charset;
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
import org.springframework.http.converter.StringHttpMessageConverter;
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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssPerDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.entity.api.RequestServiceExcise;
import th.co.baiwa.excise.ws.entity.api.RequestServiceExciseFromSubmit;
import th.co.baiwa.excise.ws.entity.api.ResponseServiceExciseFromSubmit;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.req.RequestData8000Req;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.IncFri8000Res;
import th.co.baiwa.excise.ws.entity.response.assessment.Assessment;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.pmgetdetailproject.KpiDetail;
import th.co.baiwa.excise.ws.entity.response.pmgetdetailproject.RequestPmGetDetailProject;
import th.co.baiwa.excise.ws.entity.response.pmgetdetailproject.ResponsePmGetDetailProject;
import th.co.baiwa.excise.ws.entity.response.pmgetdetailproject.TaskDetail;
import th.co.baiwa.excise.ws.entity.response.projectbase.ListPMProject;
import th.co.baiwa.excise.ws.entity.response.projectbase.RequestData;
import th.co.baiwa.excise.ws.entity.response.projectbase.RequestHeader;
import th.co.baiwa.excise.ws.entity.response.regfri4000.req.RequestDataReq;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.Regfri4000Res;
import th.co.baiwa.excise.ws.entity.response.systemError.Datum;
import th.co.baiwa.excise.ws.entity.response.systemError.ErrorDetail;
import th.co.baiwa.excise.ws.entity.response.systemError.SystemError;

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

	@Value("${ws.excise.sourceSystem}")
	private String sourceSystem;
	
	@Value("${ws.excise.destinationSystem}")
	private String destinationSystem;
	
	@Value("${ws.excise.options}")
	private String options;
	
	@Value("${ws.excise.ipaddress}")
	private String ipaddress;

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
	

	@Value("${ws.excise.get.systemError}")
	private String endpointSystemError;
	

	@Value("${ws.excise.pmGetListProject}")
	private String endpointPmGetListProject;
	
	
	@Value("${ws.excise.pmGetDetailProject}")
	private String endpointPmGetDetailProject;


	@Autowired
	private LoginLdap loginLdapProxy;
	
	public static String RESPONSE_CODE = "OK";
	
	
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
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
		logger.info("Body Service response: " + response.getBody());
		return response.getBody();
	}
	
	
	private String postRestfulFormat2(String endPoint, Object object) {
		RequestServiceExciseFromSubmit requestPmGetListProject= new RequestServiceExciseFromSubmit();
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setDestinationSystem(destinationSystem);
		requestHeader.setIpaddress(ipaddress);
		requestHeader.setOptions(options);
		requestHeader.setSourceSystem(sourceSystem);
		requestPmGetListProject.setRequestHeader(requestHeader);
		requestPmGetListProject.setRequestData(object);
		Gson gson = new Gson();
		String json = gson.toJson(requestPmGetListProject);
		logger.info("Body Service request : " + json);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
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
	public SystemError systemError(String budgetYear) {
		logger.info(" systemError restful API {} , {} " ,budgetYear);
		String responseData = getRestful(endpointSystemError+"?year="+budgetYear);
		Gson gson = new Gson();
		SystemError responseServiceExcise = gson.fromJson(responseData, SystemError.class);
		return responseServiceExcise;
	}

	
	
	
	public List<RiskAssRiskWsDtl> pmGetListProject(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		List<RiskAssRiskWsDtl> riskAssRiskWsDtlList = new ArrayList<RiskAssRiskWsDtl>();
		logger.info("restful API : pmGetListProject");
		
		RequestData requestData = new RequestData();
		requestData.setProjectTypeCode("A");
		requestData.setProjectYear(riskAssRiskWsHdr.getBudgetYear());
		
		
		String responseData = postRestfulFormat2(endpointPmGetListProject, requestData);
		Gson gson = new Gson();
		ResponseServiceExciseFromSubmit responseServiceExcise = gson.fromJson(responseData, ResponseServiceExciseFromSubmit.class);
		if(BeanUtils.isNotEmpty(responseServiceExcise) && BeanUtils.isNotEmpty(responseServiceExcise.getResponseData()) && BeanUtils.isNotEmpty(responseServiceExcise.getResponseData().getListPMProject())) {
			List<ListPMProject> pmProjectList = responseServiceExcise.getResponseData().getListPMProject();
			RiskAssRiskWsDtl riskAssRiskWsDtl = new RiskAssRiskWsDtl();
			for (ListPMProject project : pmProjectList) {
				riskAssRiskWsDtl = new RiskAssRiskWsDtl();
				riskAssRiskWsDtl.setProjectBase(project.getProjectName());
				riskAssRiskWsDtl.setDepartmentName(project.getOwnerOfficeName());
				riskAssRiskWsDtl.setBudget(new BigDecimal(project.expenseBudgetAmountM));
				riskAssRiskWsDtl.setLocalBudget(new BigDecimal(project.expenseBudgetAmountX));
				riskAssRiskWsDtl.setOtherMoney(new BigDecimal(project.expenseBudgetAmountA));
				riskAssRiskWsDtl.setSumMoney(riskAssRiskWsDtl.getBudget().add(riskAssRiskWsDtl.getLocalBudget()).add(riskAssRiskWsDtl.getOtherMoney()));
				riskAssRiskWsDtl.setApproveBudget(new BigDecimal(project.approvedBudgetAmount));
				riskAssRiskWsDtlList.add(riskAssRiskWsDtl);
			}
		}
		return riskAssRiskWsDtlList;
		}

	public List<RiskAssInfDtl> getRiskAssInfDtlList(RiskAssInfHdr rAssInfHdr) {
		List<RiskAssInfDtl> riskAssInfDtlList = new ArrayList<RiskAssInfDtl>();
		SystemError systemError = systemError(rAssInfHdr.getBudgetYear());
		if(BeanUtils.isNotEmpty(systemError) && BeanUtils.isNotEmpty(systemError.getData())) {
			List<Datum> datumList = systemError.getData();
			RiskAssInfDtl reAssInfDtl = new RiskAssInfDtl();
			for (Datum datum : datumList) {
				reAssInfDtl = new RiskAssInfDtl();
				reAssInfDtl.setTotal(new BigDecimal(datum.getCountError()));
				reAssInfDtl.setInfName(datum.getSystemName());
				ErrorDetail errorDetail = datum.getErrorDetail();
					reAssInfDtl.setJan(new BigDecimal(errorDetail.getError01()));
					reAssInfDtl.setFeb(new BigDecimal(errorDetail.getError02()));
					reAssInfDtl.setMar(new BigDecimal(errorDetail.getError03()));
					reAssInfDtl.setApril(new BigDecimal(errorDetail.getError04()));
					reAssInfDtl.setMay(new BigDecimal(errorDetail.getError05()));
					reAssInfDtl.setJun(new BigDecimal(errorDetail.getError06()));
					reAssInfDtl.setJul(new BigDecimal(errorDetail.getError07()));
					reAssInfDtl.setAug(new BigDecimal(errorDetail.getError08()));
					reAssInfDtl.setSep(new BigDecimal(errorDetail.getError09()));
					reAssInfDtl.setOct(new BigDecimal(errorDetail.getError10()));
					reAssInfDtl.setNov(new BigDecimal(errorDetail.getError11()));
					reAssInfDtl.setDec(new BigDecimal(errorDetail.getError12()));
				riskAssInfDtlList.add(reAssInfDtl);
			}
		}
		
		
		

		
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

	public List<RiskAssPerDtl> pmGetDetailProject(String projectId) {
		List<RiskAssPerDtl> riskAssPerDtlList = new ArrayList<RiskAssPerDtl>();
		logger.info("restful API : pmGetListProject");
		
		RequestPmGetDetailProject requestData = new RequestPmGetDetailProject();
		requestData.setProjectId(projectId);
		String responseData = postRestfulFormat2(endpointPmGetDetailProject, requestData);
		Gson gson = new Gson();
		ResponsePmGetDetailProject responseServiceExcise = gson.fromJson(responseData, ResponsePmGetDetailProject.class);
		if(RESPONSE_CODE.equals(responseServiceExcise.getResponseHeader().getResponseCode())) {
			RiskAssPerDtl riskAssPerDtl = null;
			if(BeanUtils.isNotEmpty(responseServiceExcise.getResponseData())) {
				double riskCost = 0;
				double kpiTargetAmount = 0;
				for (TaskDetail taskDetail : responseServiceExcise.getResponseData().getTaskDetail()) {
					riskAssPerDtl = new RiskAssPerDtl();
					riskAssPerDtl.setProjectBase(taskDetail.getTaskDescriptionText());
					riskAssPerDtl.setDepartmentName(responseServiceExcise.getResponseData().getOwnerOfficeName());
					
					for (KpiDetail kpi : taskDetail.getKpiDetail()) {
						try {
							riskCost = Double.parseDouble(kpi.getKpiExpenseActualAmount());
							
						} catch (Exception e) {
							logger.error("parseDouble {}", kpi.getKpiExpenseActualAmount() );
						}
						try {
							kpiTargetAmount = Double.parseDouble(kpi.getKpiTargetAmount());
							
						} catch (Exception e) {
							logger.error("parseDouble {}", kpi.getKpiTargetAmount() );
						}
					}
				}
				riskAssPerDtl.setRiskCost(new BigDecimal(riskCost - kpiTargetAmount));
				riskAssPerDtlList.add(riskAssPerDtl);
			}
		}
		return riskAssPerDtlList;
	}

}
