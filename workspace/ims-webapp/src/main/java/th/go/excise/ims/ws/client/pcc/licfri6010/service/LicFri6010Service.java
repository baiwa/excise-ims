package th.go.excise.ims.ws.client.pcc.licfri6010.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;
import th.go.excise.ims.ws.client.pcc.common.service.AbstractPccRestfulService;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.License;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;
import th.go.excise.ims.ws.persistence.repository.WsLicfri6010Repository;

@Service
public class LicFri6010Service extends AbstractPccRestfulService<RequestData, ResponseData> {

	@Autowired
	public LicFri6010Service(@Value("${ws.excise.endpoint.lic.licfri6010}") String url, PccServiceProperties pccServicePrpperties, RestfulClientService restfulClientService, Gson gson) {
		super.url = url;
		super.pccServicePrpperties = pccServicePrpperties;
		super.restfulClientService = restfulClientService;
		super.gson = gson;
	}

	@Autowired
	private WsLicfri6010Repository wsLicfri6010Repository;

	@Override
	public ResponseData execute(RequestData requestData) throws PccRestfulException {
		return executePost(requestData);
	}

	@Override
	protected Type getResponseDataType() {
		return new TypeToken<PccResponseHeader<ResponseData>>() {
		}.getType();
	}

	public void syncDataLicFri6010ToExciseTable(String officeCode, String startDate, String endDate) {
		try {
			int maxPage = 500;
			int pageNo = 1;
			RequestData requestData = new RequestData();
			requestData.setOffcode(officeCode);
			requestData.setYearMonthFrom(startDate);
			requestData.setYearMonthTo(endDate);
			requestData.setPageNo(String.valueOf(pageNo++));
			List<WsLicfri6010> wsLicfri6010List = null;
			WsLicfri6010 wsLicfri6010 = null;
			List<License> licenseList = null;
			do {
				wsLicfri6010List = new ArrayList<>();
				licenseList = new ArrayList<>();
				requestData.setDataPerPage(String.valueOf(maxPage));
				ResponseData responseData = execute(requestData);
				if (responseData != null) {
					licenseList = responseData.getLicenseList();
				}
				for (License license : licenseList) {
					wsLicfri6010 = new WsLicfri6010();
					wsLicfri6010.setOffcode(license.getOffcode());
					wsLicfri6010.setLicType(license.getLicType());
					wsLicfri6010.setLicNo(license.getLicNo());
					wsLicfri6010.setLicName(license.getLicName());
					wsLicfri6010.setLicFee(NumberUtils.toBigDecimal(license.getLicFee()));
					wsLicfri6010.setLicInterior(NumberUtils.toBigDecimal(license.getLicInterior()));
					wsLicfri6010.setLicPrice(NumberUtils.toBigDecimal(license.getLicPrice()));
					wsLicfri6010.setLicDate(license.getLicDate());
					wsLicfri6010.setStartDate(license.getStartDate());
					wsLicfri6010.setExpDate(license.getExpDate());
					wsLicfri6010.setSendDate(license.getSendDate());
					wsLicfri6010.setPrintCount(NumberUtils.toBigDecimal(license.getPrintCount()));
					wsLicfri6010.setNid(license.getNid());
					wsLicfri6010.setNewRegId(license.getNewregId());
					wsLicfri6010.setCusFullname(license.getCusFullName());
					wsLicfri6010.setFacFullname(license.getFacFullName());
					wsLicfri6010.setIncCode(license.getIncCode());
					wsLicfri6010List.add(wsLicfri6010);
//					wsLicfri6010Repository.save(wsLicfri6010);
				}
				wsLicfri6010Repository.batchMerge(wsLicfri6010List);
			} while (licenseList.size() == maxPage);

		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}

}
