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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterGroupRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.co.baiwa.buckwaframework.preferences.vo.MessageVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterGroupVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterInfoVo;
import th.co.baiwa.buckwaframework.support.domain.ExciseAmphur;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.co.baiwa.buckwaframework.support.domain.ExciseDistrict;
import th.co.baiwa.buckwaframework.support.domain.ExciseGeo;
import th.co.baiwa.buckwaframework.support.domain.ExciseProvice;
import th.co.baiwa.buckwaframework.support.domain.Message;
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepartment;
import th.go.excise.ims.preferences.persistence.repository.ExciseAmphurRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseDistrictRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseGeoRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseProviceRepository;
import th.go.excise.ims.preferences.vo.ExciseAmphurVo;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.vo.ExciseDistrictVo;
import th.go.excise.ims.preferences.vo.ExciseGeoVo;
import th.go.excise.ims.preferences.vo.ExciseProvinceVo;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	
	private ParameterGroupRepository parameterGroupRepository;
	private ParameterInfoRepository parameterInfoRepository;
	private MessageRepository messageRepository;
	private ExciseDepartmentRepository exciseDepartmentRepository;
	private ExciseGeoRepository exciseGeoRepository;
	private ExciseProviceRepository exciseProvinceRepository;
	private ExciseAmphurRepository exciseAmphurRepository;
	private ExciseDistrictRepository exciseDistrictRepository;
	
	private static final ConcurrentHashMap<String, ParamGroupWrapper> PARAM_GROUP_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<Long, ParamInfo> PARAM_INFO_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Message> MESSAGE_MAP = new ConcurrentHashMap<>();

	private static final ConcurrentHashMap<String, ExciseDept> EXCISE_DEPT_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ExciseDept> EXCISE_SECTOR_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDept>> EXCISE_AREA_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDept>> EXCISE_BRANCH_MAP = new ConcurrentHashMap<>();

	private static final List<ExciseGeo> EXCISE_GEO_LIST = new ArrayList<ExciseGeo>();
	private static final ConcurrentHashMap<String, List<ExciseProvice>> EXCISE_PROVINCE_MAPPER = new ConcurrentHashMap<String, List<ExciseProvice>>();
	private static final ConcurrentHashMap<String, List<ExciseAmphur>> EXCISE_AMPHUR_MAPPER = new ConcurrentHashMap<String, List<ExciseAmphur>>();
	private static final ConcurrentHashMap<String, List<ExciseDistrict>> EXCISE_DISTRICT_MAPPER = new ConcurrentHashMap<String, List<ExciseDistrict>>();

	@Autowired
	public ApplicationCache(
			ParameterGroupRepository parameterGroupRepository,
			ParameterInfoRepository parameterInfoRepository,
			MessageRepository messageRepository,
			ExciseDepartmentRepository exciseDepartmentRepository,
			ExciseGeoRepository exciseGeoService,
			ExciseProviceRepository exciseProvinceService,
			ExciseAmphurRepository exciseAmphurService,
			ExciseDistrictRepository exciseDistrictService) {
		this.parameterGroupRepository = parameterGroupRepository;
		this.parameterInfoRepository = parameterInfoRepository;
		this.messageRepository = messageRepository;
		this.exciseDepartmentRepository = exciseDepartmentRepository;
		this.exciseGeoRepository = exciseGeoService;
		this.exciseProvinceRepository = exciseProvinceService;
		this.exciseAmphurRepository = exciseAmphurService;
		this.exciseDistrictRepository = exciseDistrictService;
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		loadParameter();
		loadMessage();
		loadExciseDepartment();
//		loadGeography();
		logger.info("ApplicationCache Reloaded");
	}

	/********************* Method for Get Cache - Start *********************/
	/** Parameter Group & Parameter Info */
	public static ParamGroup getParamGroupByCode(String paramGroupCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamGroup();
	}
	
	public static List<ParamGroup> getParamGroupList() {
		List<ParamGroup> resultList = new ArrayList<>();
		for (Entry<String, ParamGroupWrapper> entry : PARAM_GROUP_MAP.entrySet()) {
			resultList.add(entry.getValue().getParamGroup());
		}
		return Collections.unmodifiableList(resultList);
	}

	public static ParamInfo getParamInfoById(Long paramInfoId) {
		return PARAM_INFO_MAP.get(paramInfoId);
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
			resultList.sort((p1, p2) -> p1.getSortingOrder() - p2.getSortingOrder());
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
	public static List<ExciseGeo> getExciseGeoList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_GEO_LIST, new ArrayList<>()));
	}

	public static List<ExciseProvice> getExciseProviceList() {
		List<ExciseProvice> resultList = new ArrayList<>();
		for (Entry<String, List<ExciseProvice>> entry : EXCISE_PROVINCE_MAPPER.entrySet()) {
			resultList.addAll(entry.getValue());
		}
		resultList.sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode()));
		return Collections.unmodifiableList(resultList);
	}
	
	public static List<ExciseProvice> getExciseProviceListByGeoId(String geoId) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_PROVINCE_MAPPER.get(geoId), new ArrayList<>()));
	}

	public static List<ExciseAmphur> getExciseAmphurList(String proviceId) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_AMPHUR_MAPPER.get(proviceId), new ArrayList<>()));
	}

	public static List<ExciseDistrict> getExciseDistrictList(String amphurId) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_DISTRICT_MAPPER.get(amphurId), new ArrayList<>()));
	}
	/********************* Method for Get Cache - End *********************/

	
	private void loadParameter() {
		logger.info("load Paramter loading...");

		PARAM_GROUP_MAP.clear();
		PARAM_INFO_MAP.clear();

		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup> paramGroupList = parameterGroupRepository.findAll();
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo> paramInfoList = null;
		List<ParamInfo> paramInfoVoList = null;
		ParameterGroupVo paramGroupVo = null;
		ParameterInfoVo paramInfoVo = null;
		
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup paramGroup : paramGroupList) {
				logger.debug("load parameterGroupCode: {}", paramGroup.getParamGroupCode());
				
				paramInfoVoList = new ArrayList<>();
				paramInfoList = parameterInfoRepository.findByParamGroupCode(paramGroup.getParamGroupCode());
				for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo paramInfo : paramInfoList) {
					paramInfoVo = new ParameterInfoVo();
					BeanUtils.copyProperties(paramInfoVo, paramInfo);
					PARAM_INFO_MAP.put(paramInfoVo.getParamInfoId(), paramInfoVo);
					paramInfoVoList.add(paramInfoVo);
				}

				PARAM_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParamGroupWrapper(paramGroupVo, paramInfoVoList));
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
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
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseDepartment> exciseDepartmentList = exciseDepartmentRepository.findAllActiveDepartment();
		
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
		
		// Debug
		EXCISE_AREA_MAP.entrySet().forEach(e -> {
			System.out.println(e.getKey() + ":" + ToStringBuilder.reflectionToString(e.getValue(), ToStringStyle.JSON_STYLE));
		});
		
		logger.info("load ExciseDepartment Sector={}, Area={}, Branch={}", EXCISE_SECTOR_MAP.size(), EXCISE_AREA_MAP.size(), EXCISE_BRANCH_MAP.size());
	}
	
	private void toDeptVo(ExciseDepartmentVo deptVo, ExciseDepartment exciseDepartment) {
		deptVo.setOfficeCode(exciseDepartment.getOffCode());
		deptVo.setDeptName(exciseDepartment.getOffName());
		deptVo.setDeptShortName(exciseDepartment.getOffShortName());
	}

	private void loadGeography() {
		EXCISE_GEO_LIST.clear();
		EXCISE_PROVINCE_MAPPER.clear();
		EXCISE_AMPHUR_MAPPER.clear();
		EXCISE_DISTRICT_MAPPER.clear();
		
		/** Geography */
		List<th.go.excise.ims.preferences.persistence.entity.ExciseGeo> exciseGeoList = exciseGeoRepository.findAll();
		ExciseGeoVo geoVo = null;
		try {
			for (th.go.excise.ims.preferences.persistence.entity.ExciseGeo exciseGeo : exciseGeoList) {
				geoVo = new ExciseGeoVo();
				BeanUtils.copyProperties(geoVo, exciseGeo);
				EXCISE_GEO_LIST.add(geoVo);
			}
			EXCISE_GEO_LIST.sort((p1, p2) -> p1.getGeoId().compareTo(p2.getGeoId()));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** Province */
		List<th.go.excise.ims.preferences.persistence.entity.ExciseProvice> exciseProvinceList = exciseProvinceRepository.findAll();
		List<ExciseProvice> provinceList = null;
		ExciseProvinceVo provinceVo = null;
		try {
			for (th.go.excise.ims.preferences.persistence.entity.ExciseProvice province : exciseProvinceList) {
				provinceList = EXCISE_PROVINCE_MAPPER.get(province.getGeoId().toString());
				if (provinceList == null) {
					provinceList = new ArrayList<ExciseProvice>();
				}
				provinceVo = new ExciseProvinceVo();
				BeanUtils.copyProperties(provinceVo, province);
				provinceList.add(provinceVo);
				EXCISE_PROVINCE_MAPPER.put(provinceVo.getGeoId().toString(), provinceList);
			}
			EXCISE_PROVINCE_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** Amphur */
		List<th.go.excise.ims.preferences.persistence.entity.ExciseAmphur> exciseAmphurList = exciseAmphurRepository.findAll();
		List<ExciseAmphur> amphurList = null;
		ExciseAmphurVo amphurVo = null;
		try {
			for (th.go.excise.ims.preferences.persistence.entity.ExciseAmphur amphur : exciseAmphurList) {
				amphurList = EXCISE_AMPHUR_MAPPER.get(amphur.getProvinceId().toString());
				if (amphurList == null) {
					amphurList = new ArrayList<ExciseAmphur>();
				}
				amphurVo = new ExciseAmphurVo();
				BeanUtils.copyProperties(amphurVo, amphur);
				amphurList.add(amphurVo);
				EXCISE_AMPHUR_MAPPER.put(amphurVo.getProvinceId().toString(), amphurList);
			}
			EXCISE_AMPHUR_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getAmphurCode().compareTo(p2.getAmphurCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** District */
		List<th.go.excise.ims.preferences.persistence.entity.ExciseDistrict> exciseDistricList = exciseDistrictRepository.findAll();
		List<ExciseDistrict> districtList = null;
		ExciseDistrictVo districtVo = null;
		try {
			for (th.go.excise.ims.preferences.persistence.entity.ExciseDistrict district : exciseDistricList) {
				districtList = EXCISE_DISTRICT_MAPPER.get(district.getAmphurId().toString());
				if (districtList == null) {
					districtList = new ArrayList<ExciseDistrict>();
				}
				districtVo = new ExciseDistrictVo();
				BeanUtils.copyProperties(districtVo, district);
				districtList.add(districtVo);
				EXCISE_DISTRICT_MAPPER.put(districtVo.getAmphurId().toString(), districtList);
			}
			EXCISE_DISTRICT_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getDistrictCode().compareTo(p2.getDistrictCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

}
