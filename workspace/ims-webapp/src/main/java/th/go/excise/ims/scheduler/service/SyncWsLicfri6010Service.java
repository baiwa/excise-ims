package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.License;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;
import th.go.excise.ims.ws.client.pcc.licfri6010.service.LicFri6010Service;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;
import th.go.excise.ims.ws.persistence.repository.WsLicfri6010Repository;

@Service
public class SyncWsLicfri6010Service {
	
private static final Logger logger = LoggerFactory.getLogger(SyncWsRegfri4000Service.class);
	
	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private LicFri6010Service licFri6010Service;
	
	@Autowired
	private WsLicfri6010Repository wsLicfri6010Repository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws PccRestfulException {
		logger.info("syncData Licfri6010 Start");
		long start = System.currentTimeMillis();
		
		requestData.setDataPerPage(String.valueOf(WS_DATA_SIZE));
		int indexPage = 0;
		
		List<License> licenseList = null;
		WsLicfri6010 licfri6010 = null;
		List<WsLicfri6010> licfri6010List = new ArrayList<>();
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			licenseList = licFri6010Service.execute(requestData).getLicenseList();
			if (licenseList != null && licenseList.size() > 0) {
				for (License license : licenseList) {
					licfri6010 = new WsLicfri6010();
					licfri6010.setOffcode(license.getOffcode());
					licfri6010.setLicType(license.getLicType());
					licfri6010.setLicNo(license.getLicNo());
					licfri6010.setLicName(license.getLicName());
					licfri6010.setLicFee(NumberUtils.toBigDecimal(license.getLicFee()));
					licfri6010.setLicInterior(NumberUtils.toBigDecimal(license.getLicInterior()));
					licfri6010.setLicPrice(NumberUtils.toBigDecimal(license.getLicPrice()));
					licfri6010.setLicDate(StringUtils.isNotEmpty(license.getLicDate()) ? ConvertDateUtils.parseStringToLocalDate(license.getLicDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN) : null);
					licfri6010.setStartDate(StringUtils.isNotEmpty(license.getLicDate()) ? ConvertDateUtils.parseStringToLocalDate(license.getStartDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN) : null);
					licfri6010.setExpDate(StringUtils.isNotEmpty(license.getLicDate()) ? ConvertDateUtils.parseStringToLocalDate(license.getExpDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN) : null);
					licfri6010.setSendDate(StringUtils.isNotEmpty(license.getLicDate()) ? ConvertDateUtils.parseStringToLocalDate(license.getSendDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN) : null);
					licfri6010.setPrintCount(NumberUtils.toBigDecimal(license.getPrintCount()));
					licfri6010.setNid(license.getNid());
					licfri6010.setNewRegId(license.getNewregId());
					licfri6010.setCusId(license.getCusId());
					licfri6010.setCusAddrseq(license.getCusAddrseq());
					licfri6010.setCusFullname(license.getCusFullName());
					licfri6010.setFacId(license.getFacId());
					licfri6010.setFacAddrseq(license.getFacAddrseq());
					licfri6010.setFacFullname(license.getFacFullName());
					licfri6010.setIncCode(license.getIncCode());
					licfri6010.setCreatedBy(SYSTEM_USER.BATCH);
					licfri6010.setCreatedDate(LocalDateTime.now());
					licfri6010List.add(licfri6010);
				}
				break;
			}
		} while (licenseList.size() == WS_DATA_SIZE);
		
		// Set dateStart and dateEnd
		LocalDate localDateStart = LocalDate.parse(requestData.getYearMonthFrom() + "01", DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate localDateEnd = LocalDate.parse(requestData.getYearMonthTo() + "01", DateTimeFormatter.BASIC_ISO_DATE);
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		wsLicfri6010Repository.forceDeleteByOfficeCodeAndLicDate(requestData.getOffcode(), dateStart, dateEnd);
		wsLicfri6010Repository.batchInsert(licfri6010List);
		logger.info("Batch Insert WS_INCFRI8000 Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Licfri6010 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
