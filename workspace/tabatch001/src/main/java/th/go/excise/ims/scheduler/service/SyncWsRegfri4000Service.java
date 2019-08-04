package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.SYSTEM_USER;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;
import th.go.excise.ims.ws.persistence.repository.WsRegfri4000DutyRepository;
import th.go.excise.ims.ws.persistence.repository.WsRegfri4000Repository;

@Service
public class SyncWsRegfri4000Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsRegfri4000Service.class);
	
	private int wsDataSize;
	private RegFri4000Service regFri4000Service;
	private WsRegfri4000Repository wsRegfri4000Repository;
	private WsRegfri4000DutyRepository wsRegfri4000DutyRepository;
	
	@Autowired
	public SyncWsRegfri4000Service(
			@Value("${ws.excise.reg.regfri4000.data-size}") int wsDataSize,
			RegFri4000Service regFri4000Service,
			WsRegfri4000Repository wsRegfri4000Repository,
			WsRegfri4000DutyRepository wsRegfri4000DutyRepository) {
		this.wsDataSize = wsDataSize;
		this.regFri4000Service = regFri4000Service;
		this.wsRegfri4000Repository = wsRegfri4000Repository;
		this.wsRegfri4000DutyRepository = wsRegfri4000DutyRepository;
	}
	
	public RequestData buildRequestData() {
		RequestData requestData = new RequestData();
		requestData.setType("1");
		requestData.setNid("");
		requestData.setNewregId("");
		requestData.setHomeOfficeId("");
		requestData.setActive("1");
		
		return requestData;
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws PccRestfulException {
		logger.info("syncData Regfri4000 Start");
		long start = System.currentTimeMillis();
		
		requestData.setDataPerPage(String.valueOf(wsDataSize));
		int indexPage = 0;
		
		List<RegMaster60> regMaster60List = null;
		WsRegfri4000 regfri4000 = null;
		List<WsRegfri4000> regfri4000List = new ArrayList<>();
		WsRegfri4000Duty regfri4000Duty = null;
		List<WsRegfri4000Duty> regfri4000DutyList = new ArrayList<>();
		LocalDate tmpRegDate = null;
		List<LocalDate> tmpRegDateList = null;
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
			if (regMaster60List != null && regMaster60List.size() > 0) {
				logger.info("Restful Post to Regfri4000 Response size: {}", regMaster60List.size());
				for (RegMaster60 regMaster60 : regMaster60List) {
					regfri4000 = new WsRegfri4000();
					regfri4000.setNewRegId(regMaster60.getNewregId());
					regfri4000.setRegId(regMaster60.getRegId());
					regfri4000.setRegStatus(regMaster60.getRegStatus());
					regfri4000.setRegStatusDesc(regMaster60.getRegStatusDesc());
					regfri4000.setStatusDate(LocalDate.parse(regMaster60.getStatusDate(), DateTimeFormatter.BASIC_ISO_DATE));
					regfri4000.setCusId(regMaster60.getCusId());
					regfri4000.setCusSeq(Integer.parseInt(regMaster60.getCusSeq()));
					regfri4000.setCusAddrSeq(Integer.parseInt(regMaster60.getCusAddrseq()));
					regfri4000.setCusFullname(regMaster60.getCusFullname());
					regfri4000.setCusHouseNo(regMaster60.getCusHouseno());
					regfri4000.setCusAddrNo(regMaster60.getCusAddrno());
					regfri4000.setCusBuildName(regMaster60.getCusBuildname());
					regfri4000.setCusFloorNo(regMaster60.getCusFloorno());
					regfri4000.setCusRoomNo(regMaster60.getCusRoomno());
					regfri4000.setCusMooNo(regMaster60.getCusMoono());
					regfri4000.setCusVillage(regMaster60.getCusVillage());
					regfri4000.setCusSoiName(regMaster60.getCusSoiname());
					regfri4000.setCusThnName(regMaster60.getCusThnname());
					regfri4000.setCusTambolCode(regMaster60.getCusTambolcode());
					regfri4000.setCusTambolName(regMaster60.getCusTambolname());
					regfri4000.setCusAmphurCode(regMaster60.getCusAmphurcode());
					regfri4000.setCusAmphurName(regMaster60.getCusAmphurname());
					regfri4000.setCusProvinceCode(regMaster60.getCusProvincecode());
					regfri4000.setCusProvinceName(regMaster60.getCusProvincename());
					regfri4000.setCusZipCode(regMaster60.getCusZipcode());
					regfri4000.setCusTelNo(regMaster60.getCusTelno());
					regfri4000.setCusEmail(regMaster60.getCusEmail());
					regfri4000.setCusUrl(regMaster60.getCusUrl());
					regfri4000.setFacId(regMaster60.getFacId());
					regfri4000.setFacSeq(Integer.parseInt(regMaster60.getFacSeq()));
					regfri4000.setFacAddrSeq(Integer.parseInt(regMaster60.getFacAddrseq()));
					regfri4000.setFacFullname(regMaster60.getFacFullname());
					regfri4000.setFacHouseNo(regMaster60.getFacHouseno());
					regfri4000.setFacAddrNo(regMaster60.getFacAddrno());
					regfri4000.setFacBuildName(regMaster60.getFacBuildname());
					regfri4000.setFacFloorNo(regMaster60.getFacFloorno());
					regfri4000.setFacRoomNo(regMaster60.getFacRoomno());
					regfri4000.setFacMooNo(regMaster60.getFacMoono());
					regfri4000.setFacVillage(regMaster60.getFacVillage());
					regfri4000.setFacSoiName(regMaster60.getFacSoiname());
					regfri4000.setFacThnName(regMaster60.getFacThnname());
					regfri4000.setFacTambolCode(regMaster60.getFacTambolcode());
					regfri4000.setFacTambolName(regMaster60.getFacTambolname());
					regfri4000.setFacAmphurCode(regMaster60.getFacAmphurcode());
					regfri4000.setFacAmphurName(regMaster60.getFacAmphurname());
					regfri4000.setFacProvinceCode(regMaster60.getFacProvincecode());
					regfri4000.setFacProvinceName(regMaster60.getFacProvincename());
					regfri4000.setFacZipCode(regMaster60.getFacZipcode());
					regfri4000.setFacTelNo(regMaster60.getFacTelno());
					regfri4000.setFacEmail(regMaster60.getFacEmail());
					regfri4000.setFacUrl(regMaster60.getFacUrl());
					regfri4000.setCapital(NumberUtils.toBigDecimal(regMaster60.getCapital()));
					regfri4000.setOffCode(regMaster60.getOffcode());
					regfri4000.setActiveFlag(regMaster60.getActiveFlag());
					regfri4000.setSyncDate(LocalDateTime.now());
					regfri4000.setCreatedBy(SYSTEM_USER.BATCH);
					regfri4000.setCreatedDate(LocalDateTime.now());
					regfri4000.setUpdatedBy(SYSTEM_USER.BATCH);
					regfri4000.setUpdatedDate(LocalDateTime.now());
					if (regMaster60.getRegDutyList() != null && regMaster60.getRegDutyList().size() > 0) {
						tmpRegDateList = new ArrayList<>();
						for (RegDuty regDuty : regMaster60.getRegDutyList()) {
							tmpRegDate = LocalDate.parse(regDuty.getRegDate().substring(0, 8), DateTimeFormatter.BASIC_ISO_DATE);
							tmpRegDateList.add(tmpRegDate);
							regfri4000Duty = new WsRegfri4000Duty();
							regfri4000Duty.setNewRegId(regMaster60.getNewregId());
							regfri4000Duty.setRegId(regMaster60.getRegId());
							regfri4000Duty.setGroupId(regDuty.getGroupId());
							regfri4000Duty.setGroupName(regDuty.getGroupName());
							regfri4000Duty.setRegDate(tmpRegDate);
							regfri4000Duty.setProdDate(null); // TODO WS no value
							regfri4000Duty.setCreatedBy(SYSTEM_USER.BATCH);
							regfri4000Duty.setCreatedDate(LocalDateTime.now());
							regfri4000Duty.setUpdatedBy(SYSTEM_USER.BATCH);
							regfri4000Duty.setUpdatedDate(LocalDateTime.now());
							regfri4000DutyList.add(regfri4000Duty);
						}
					}
					if (regMaster60.getRegDutyList() != null && regMaster60.getRegDutyList().size() > 1) {
						regfri4000.setMultiDutyFlag(FLAG.Y_FLAG);
					} else {
						regfri4000.setMultiDutyFlag(FLAG.N_FLAG);
					}
					regfri4000.setPinnitId(regfri4000.getNewRegId().substring(0, 13));
					regfri4000.setFacType(regfri4000.getNewRegId().substring(13, 14));
					regfri4000.setRegDate(tmpRegDateList.stream().min(Comparator.comparing(LocalDate::toEpochDay)).get());
					regfri4000List.add(regfri4000);
				}
			} else {
				logger.warn("WS Regfri4000 is empty ResponseData", regMaster60List.size());
			}
		} while (regMaster60List.size() == wsDataSize);
		
		wsRegfri4000Repository.truncate();
		wsRegfri4000Repository.batchInsert(regfri4000List);
		logger.info("Batch Insert WS_REGFRI4000 Success");
		
		wsRegfri4000DutyRepository.truncate();
		wsRegfri4000DutyRepository.batchInsert(regfri4000DutyList);
		logger.info("Batch Insert WS_REGFRI4000_DUTY Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Regfri4000 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
