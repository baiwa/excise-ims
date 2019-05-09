package th.co.baiwa.buckwaframework.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoAmphurRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoDistrictRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoProvinceRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoSectorRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterGroupRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.co.baiwa.buckwaframework.preferences.vo.GeoAmphurVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoDistrictVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoProvinceVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoSectorVo;
import th.co.baiwa.buckwaframework.preferences.vo.MessageVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterGroupVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterInfoVo;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.co.baiwa.buckwaframework.support.domain.GeoAmphur;
import th.co.baiwa.buckwaframework.support.domain.GeoDistrict;
import th.co.baiwa.buckwaframework.support.domain.GeoProvince;
import th.co.baiwa.buckwaframework.support.domain.GeoSector;
import th.co.baiwa.buckwaframework.support.domain.Message;
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ed.persistence.entity.ExciseCtrlDuty;
import th.go.excise.ims.ed.persistence.repository.ExciseCtrlDutyRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepartment;
import th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseDutyGroupRepository;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	
	private ParameterGroupRepository parameterGroupRepository;
	private ParameterInfoRepository parameterInfoRepository;
	private MessageRepository messageRepository;
	private ExciseDepartmentRepository exciseDepartmentRepository;
	private GeoSectorRepository geoSectorRepository;
	private GeoProvinceRepository geoProvinceRepository;
	private GeoAmphurRepository geoAmphurRepository;
	private GeoDistrictRepository geoDistrictRepository;
	private ExciseDutyGroupRepository exciseDutyGroupRepository;
	private ExciseCtrlDutyRepository exciseCtrlDutyRepository;
	
	private static final ConcurrentHashMap<String, ParamGroupWrapper> PARAM_GROUP_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Message> MESSAGE_MAP = new ConcurrentHashMap<>();

	private static final ConcurrentHashMap<String, ExciseDept> EXCISE_DEPT_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ExciseDept> EXCISE_SECTOR_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDept>> EXCISE_AREA_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDept>> EXCISE_BRANCH_MAP = new ConcurrentHashMap<>();

	private static final List<GeoSector> GEO_SECTOR_LIST = new ArrayList<>();
	private static final List<GeoProvince> GEO_PROVINCE_LIST = new ArrayList<>();
	private static final List<GeoAmphur> GEO_AMPHUR_LIST = new ArrayList<>();
	private static final List<GeoDistrict> GEO_DISTRICT_LIST = new ArrayList<>();
	private static final ConcurrentHashMap<String, List<GeoProvince>> GEO_PROVINCE_MAPPER = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<GeoAmphur>> GEO_AMPHUR_MAPPER = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<GeoDistrict>> GEO_DISTRICT_MAPPER = new ConcurrentHashMap<>();
	
	private static final ConcurrentHashMap<String, List<ExciseDutyGroup>> DUTY_GROUP = new ConcurrentHashMap<>();

	private static final ConcurrentHashMap<String, ExciseDutyGroup> EXCISE_DUTY_GROUP = new ConcurrentHashMap<>();
	private static final List<String> OFFICE_DUTY_ROLE = new ArrayList<>();
	@Autowired
	public ApplicationCache(
			ParameterGroupRepository parameterGroupRepository,
			ParameterInfoRepository parameterInfoRepository,
			MessageRepository messageRepository,
			ExciseDepartmentRepository exciseDepartmentRepository,
			GeoSectorRepository geoSectorRepository,
			GeoProvinceRepository geoProvinceRepository,
			GeoAmphurRepository geoAmphurRepository,
			GeoDistrictRepository geoDistrictRepository,
			ExciseDutyGroupRepository exciseDutyGroupRepository,
			ExciseCtrlDutyRepository exciseCtrlDutyRepository) {
		this.parameterGroupRepository = parameterGroupRepository;
		this.parameterInfoRepository = parameterInfoRepository;
		this.messageRepository = messageRepository;
		this.exciseDepartmentRepository = exciseDepartmentRepository;
		this.geoSectorRepository = geoSectorRepository;
		this.geoProvinceRepository = geoProvinceRepository;
		this.geoAmphurRepository = geoAmphurRepository;
		this.geoDistrictRepository = geoDistrictRepository;
		this.exciseDutyGroupRepository = exciseDutyGroupRepository;
		this.exciseCtrlDutyRepository = exciseCtrlDutyRepository;
		
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		loadParameter();
		loadMessage();
		loadExciseDepartment();
		loadGeography();
		loadExciseDutyGroup();
		loadExciseCtrlDuty();
		loadDutyGroup();
		logger.info("ApplicationCache Reloaded");
	}

	/********************* Method for Get Cache - Start *********************/
	/** Parameter Group & Parameter Info */
	public static List<ParamGroup> getParamGroupList() {
		List<ParamGroup> resultList = new ArrayList<>();
		for (Entry<String, ParamGroupWrapper> entry : PARAM_GROUP_MAP.entrySet()) {
			resultList.add(entry.getValue().getParamGroup());
		}
		return Collections.unmodifiableList(resultList);
	}
	
	public static ParamGroup getParamGroupByCode(String paramGroupCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamGroup();
	}
	public static List<String> getRoleDutyOffice() {
		return OFFICE_DUTY_ROLE;
	}
	
	public static ParamInfo getParamInfoByCode(String paramGroupCode, String paramInfoCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamInfoCodeMap().get(paramInfoCode);
	}

	public static List<ParamInfo> getParamInfoListByGroupCode(String paramGroupCode) {
		ParamGroupWrapper paramGroupWrapper = PARAM_GROUP_MAP.get(paramGroupCode);

		List<ParamInfo> resultList = new ArrayList<>();
		if (paramGroupWrapper != null) {
			for (Entry<String, ParamInfo> entry : paramGroupWrapper.getParamInfoCodeMap().entrySet()) {
				resultList.add(entry.getValue());
			}
		}

		return Collections.unmodifiableList(resultList);
	}

	final class ParamGroupWrapper {

		private ParamGroup paramGroup;
		private Map<String, ParamInfo> paramInfoCodeMap = new LinkedHashMap<>();

		public ParamGroupWrapper(ParamGroup paramGroup, List<? extends ParamInfo> paramInfoList) {
			this.paramGroup = paramGroup;
			for (ParamInfo paramInfo : paramInfoList) {
				paramInfoCodeMap.put(paramInfo.getParamCode(), paramInfo);
			}
		}

		public ParamGroup getParamGroup() {
			return paramGroup;
		}

		public Map<String, ParamInfo> getParamInfoCodeMap() {
			return paramInfoCodeMap;
		}

	}

	/** Message */
	public static Message getMessage(String messageCode) {
		return MESSAGE_MAP.get(messageCode);
	}

	public static String getMessage(String messageCode, String lang) {
		Message message = MESSAGE_MAP.get(messageCode);
		String msgDesc = null;
		if (MESSAGE_LANG.EN.equals(lang)) {
			msgDesc = message.getMessageEn();
		} else if (MESSAGE_LANG.TH.equals(lang)) {
			msgDesc = message.getMessageTh();
		}
		return msgDesc;
	}
	
	/** Excise Department */
	public static ExciseDept getExciseDept(String officeCode) {
		return EXCISE_DEPT_MAP.get(officeCode);
	}
	
	public static List<ExciseDept> getExciseSectorList() {
		List<ExciseDept> resultList = EXCISE_SECTOR_MAP.values().stream().collect(Collectors.toList());
		resultList.sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode()));
		return Collections.unmodifiableList(resultList);
	}

	public static List<ExciseDept> getExciseAreaList(String officeCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_AREA_MAP.get(officeCode), new ArrayList<>()));
	}

	public static List<ExciseDept> getExciseBranchList(String officeCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_BRANCH_MAP.get(officeCode), new ArrayList<>()));
	}
	
	/** Geography */
	public static List<GeoSector> getGeoSectorList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_SECTOR_LIST, new ArrayList<>()));
	}

	public static List<GeoProvince> getGeoProvinceList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_PROVINCE_LIST, new ArrayList<>()));
	}
	
	public static List<GeoProvince> getGeoProvinceList(String sectorCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_PROVINCE_MAPPER.get(sectorCode), new ArrayList<>()));
	}

	public static List<GeoAmphur> getGeoAmphurList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_AMPHUR_LIST, new ArrayList<>()));
	}
	
	public static List<GeoAmphur> getGeoAmphurList(String proviceCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_AMPHUR_MAPPER.get(proviceCode), new ArrayList<>()));
	}

	public static List<GeoDistrict> getGeoDistrictList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_DISTRICT_LIST, new ArrayList<>()));
	}
	
	public static List<GeoDistrict> getGeoDistrictList(String amphurCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_DISTRICT_MAPPER.get(amphurCode), new ArrayList<>()));
	}
	
	public static ExciseDutyGroup getExciseDutyGroup(String dutyCode) {
		return EXCISE_DUTY_GROUP.get(dutyCode);
	}
	public static List<ExciseDutyGroup> getExciseDutyListByType(String type) {
		return DUTY_GROUP.get(type);
	}
	/********************* Method for Get Cache - End *********************/

	
	private void loadParameter() {
		logger.info("load Paramter loading...");

		PARAM_GROUP_MAP.clear();

		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup> paramGroupList = parameterGroupRepository.findAll();
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo> paramInfoList = parameterInfoRepository.findAll();
		List<ParamInfo> paramInfoVoList = null;
		ParameterGroupVo paramGroupVo = null;
		ParameterInfoVo paramInfoVo = null;
		
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup paramGroup : paramGroupList) {
				paramInfoVoList = new ArrayList<>();
				for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo paramInfo : paramInfoList) {
					if (paramGroup.getParamGroupCode().equals(paramInfo.getParamGroupCode())) {
						paramInfoVo = new ParameterInfoVo();
						BeanUtils.copyProperties(paramInfoVo, paramInfo);
						paramInfoVoList.add(paramInfoVo);
					}
				}
				paramInfoVoList.sort((p1, p2) -> p1.getSortingOrder() - p2.getSortingOrder());
				PARAM_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParamGroupWrapper(paramGroupVo, paramInfoVoList));
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}

		logger.info("load Paramter loaded [{}]", PARAM_GROUP_MAP.size());
	}

	private void loadMessage() {
		logger.info("load Message loading...");
		
		MESSAGE_MAP.clear();
		
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.Message> messageList = messageRepository.findAll();
		
		MessageVo messageVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.Message message : messageList) {
				messageVo = new MessageVo();
				BeanUtils.copyProperties(messageVo, message);
				MESSAGE_MAP.put(messageVo.getMessageCode(), messageVo);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		logger.info("load Message loaded [{}]", MESSAGE_MAP.size());
	}
	
	private void loadExciseDepartment() {
		logger.info("load ExciseDepartment loading...");
		
		EXCISE_DEPT_MAP.clear();
		EXCISE_SECTOR_MAP.clear();
		EXCISE_AREA_MAP.clear();
		EXCISE_BRANCH_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseDepartment> exciseDepartmentList = exciseDepartmentRepository.findAll();
		
		ExciseDepartmentVo deptVo = null;
		List<ExciseDept> areaList = null;
		List<ExciseDept> branchList = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseDepartment exciseDepartment : exciseDepartmentList) {
			deptVo = new ExciseDepartmentVo();
			toDeptVo(deptVo, exciseDepartment);
			EXCISE_DEPT_MAP.put(deptVo.getOfficeCode(), deptVo);
			
			if (Pattern.matches("^.{2}0{4}$", exciseDepartment.getOffCode())) {
				deptVo = new ExciseDepartmentVo();
				toDeptVo(deptVo, exciseDepartment);
				EXCISE_SECTOR_MAP.put(deptVo.getOfficeCode(), deptVo);
			} else if (Pattern.matches(exciseDepartment.getOffCode().substring(0, 2) + "([0-9]{1}[1-9]{1}|[1-9][0-9])00", exciseDepartment.getOffCode())) {
				areaList = EXCISE_AREA_MAP.get(exciseDepartment.getOffCode().substring(0, 2) + "0000");
				deptVo = new ExciseDepartmentVo();
				toDeptVo(deptVo, exciseDepartment);
				if (areaList == null) {
					areaList = new ArrayList<>();
					areaList.add(deptVo);
				} else {
					areaList.add(deptVo);
				}
				EXCISE_AREA_MAP.put(deptVo.getOfficeCode().substring(0, 2) + "0000", areaList);

			} else if (Pattern.matches(exciseDepartment.getOffCode().substring(0, 4) + "([0-9]{1}[1-9]{1}|[1-9][0-9])", exciseDepartment.getOffCode())) {
				branchList = EXCISE_BRANCH_MAP.get(exciseDepartment.getOffCode().substring(0, 4) + "00");
				deptVo = new ExciseDepartmentVo();
				toDeptVo(deptVo, exciseDepartment);
				if (branchList == null) {
					branchList = new ArrayList<>();
					branchList.add(deptVo);
				} else {
					branchList.add(deptVo);
				}
				EXCISE_BRANCH_MAP.put(deptVo.getOfficeCode().substring(0, 4) + "00", branchList);

			} else {
				logger.warn("This officeCode is not match, [{}]", exciseDepartment.getOffCode());
			}
		}
		
		// Sorting
		EXCISE_AREA_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		EXCISE_BRANCH_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		
		logger.info("load ExciseDepartment Sector={}, Area={}, Branch={}", EXCISE_SECTOR_MAP.size(), EXCISE_AREA_MAP.size(), EXCISE_BRANCH_MAP.size());
	}
	
	private void toDeptVo(ExciseDepartmentVo deptVo, ExciseDepartment exciseDepartment) {
		deptVo.setOfficeCode(exciseDepartment.getOffCode());
		deptVo.setDeptName(exciseDepartment.getOffName());
		deptVo.setDeptShortName(exciseDepartment.getOffShortName());
	}

	private void loadGeography() {
		GEO_SECTOR_LIST.clear();
		GEO_PROVINCE_MAPPER.clear();
		GEO_AMPHUR_MAPPER.clear();
		GEO_DISTRICT_MAPPER.clear();
		
		/** Geography */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoSector> geoSectorList = geoSectorRepository.findAll();
		GeoSectorVo sectorVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoSector sector : geoSectorList) {
				sectorVo = new GeoSectorVo();
				BeanUtils.copyProperties(sectorVo, sector);
				GEO_SECTOR_LIST.add(sectorVo);
			}
			GEO_SECTOR_LIST.sort((p1, p2) -> p1.getSectorCode().compareTo(p2.getSectorCode()));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** Province */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
		List<GeoProvince> provinceList = null;
		GeoProvinceVo provinceVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoProvince province : geoProvinceList) {
				provinceList = GEO_PROVINCE_MAPPER.get(province.getSectorCode());
				if (provinceList == null) {
					provinceList = new ArrayList<GeoProvince>();
				}
				provinceVo = new GeoProvinceVo();
				BeanUtils.copyProperties(provinceVo, province);
				provinceList.add(provinceVo);
				GEO_PROVINCE_LIST.add(provinceVo);
				GEO_PROVINCE_MAPPER.put(provinceVo.getSectorCode(), provinceList);
			}
			GEO_PROVINCE_LIST.sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode()));
			GEO_PROVINCE_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** Amphur */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoAmphur> geoAmphurList = geoAmphurRepository.findAll();
		List<GeoAmphur> amphurList = null;
		GeoAmphurVo amphurVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoAmphur amphur : geoAmphurList) {
				amphurList = GEO_AMPHUR_MAPPER.get(amphur.getAmphurCode().substring(0, 2));
				if (amphurList == null) {
					amphurList = new ArrayList<GeoAmphur>();
				}
				amphurVo = new GeoAmphurVo();
				BeanUtils.copyProperties(amphurVo, amphur);
				amphurList.add(amphurVo);
				GEO_AMPHUR_LIST.add(amphurVo);
				GEO_AMPHUR_MAPPER.put(amphurVo.getAmphurCode().substring(0, 2), amphurList);
			}
			GEO_AMPHUR_LIST.sort((p1, p2) -> p1.getAmphurCode().compareTo(p2.getAmphurCode()));
			GEO_AMPHUR_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getAmphurCode().compareTo(p2.getAmphurCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** District */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoDistrict> geoDistricList = geoDistrictRepository.findAll();
		List<GeoDistrict> districtList = null;
		GeoDistrictVo districtVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoDistrict district : geoDistricList) {
				districtList = GEO_DISTRICT_MAPPER.get(district.getDistrictCode().substring(0, 4));
				if (districtList == null) {
					districtList = new ArrayList<GeoDistrict>();
				}
				districtVo = new GeoDistrictVo();
				BeanUtils.copyProperties(districtVo, district);
				districtList.add(districtVo);
				GEO_DISTRICT_LIST.add(districtVo);
				GEO_DISTRICT_MAPPER.put(districtVo.getDistrictCode().substring(0, 4), districtList);
			}
			GEO_DISTRICT_LIST.sort((p1, p2) -> p1.getDistrictCode().compareTo(p2.getDistrictCode()));
			GEO_DISTRICT_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getDistrictCode().compareTo(p2.getDistrictCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	private void loadExciseDutyGroup() {
		List<ExciseDutyGroup> exciseDutyGroupList = exciseDutyGroupRepository.findAll();
		for (ExciseDutyGroup exciseDutyGroup : exciseDutyGroupList) {
			EXCISE_DUTY_GROUP.put(exciseDutyGroup.getDutyGroupCode(), exciseDutyGroup);
		}
	}
	
	private void loadExciseCtrlDuty() {
		List<ExciseCtrlDuty> exciseCtrlDutieList = exciseCtrlDutyRepository.findAll();
		for (ExciseCtrlDuty exciseCtrlDuty : exciseCtrlDutieList) {
			OFFICE_DUTY_ROLE.add(exciseCtrlDuty.getId().getResOffcode());
		}
	}
	
	private void loadDutyGroup() {
		List<ExciseDutyGroup> exciseDutyGroupList = exciseDutyGroupRepository.findAllByDutyGroupStatus(FLAG.N_FLAG);
		List<ExciseDutyGroup> data = null;
		for (ExciseDutyGroup exciseDutyGroup : exciseDutyGroupList) {
			data = new ArrayList<ExciseDutyGroup>();
			data = DUTY_GROUP.get(exciseDutyGroup.getDutyGroupType());
			if(data == null ) {
				data = new ArrayList<ExciseDutyGroup>();
			}
			data.add(exciseDutyGroup);
			
			DUTY_GROUP.put(exciseDutyGroup.getDutyGroupType(), data);
		}
	}

}
