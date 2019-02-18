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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterGroupRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.preferences.persistence.entity.ExciseAmphur;
import th.go.excise.ims.preferences.persistence.entity.ExciseDistrict;
import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;
import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.preferences.service.ExciseAmphurService;
import th.go.excise.ims.preferences.service.ExciseDistrictService;
import th.go.excise.ims.preferences.service.ExciseGeoService;
import th.go.excise.ims.preferences.service.ExciseProviceService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	
	private ParameterGroupRepository parameterGroupRepository;
	private ParameterInfoRepository parameterInfoRepository;
	private MessageRepository messageRepository;
	private ExciseDepartmentRepository exciseDepartmentRepository;

	@Autowired
	public final ExciseGeoService exciseGeoService = null;

	@Autowired
	public final ExciseProviceService exciseProvinceService = null;

	@Autowired
	public final ExciseAmphurService exciseAmphurService = null;

	@Autowired
	public final ExciseDistrictService exciseDistrictService = null;
	
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

	public ApplicationCache(
			@Autowired ParameterGroupRepository parameterGroupRepository,
			@Autowired ParameterInfoRepository parameterInfoRepository,
			@Autowired MessageRepository messageRepository,
			@Autowired ExciseDepartmentRepository exciseDepartmentRepository) {
		this.parameterGroupRepository = parameterGroupRepository;
		this.parameterInfoRepository = parameterInfoRepository;
		this.messageRepository = messageRepository;
		this.exciseDepartmentRepository = exciseDepartmentRepository;
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		loadParameter();
		loadMessage();
		loadExciseDepartment();
		loadGeography();
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
		for (Entry<String, ParamInfo> entry : paramGroupWrapper.getParamInfoCodeMap().entrySet()) {
			resultList.add(entry.getValue());
		}
		resultList.sort((p1, p2) -> p1.getSortingOrder() - p2.getSortingOrder());

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
		return Collections.unmodifiableList(EXCISE_AREA_MAP.get(officeCode));
	}

	public static List<ExciseDept> getExciseBranchList(String officeCode) {
		return Collections.unmodifiableList(EXCISE_BRANCH_MAP.get(officeCode));
	}
	
	/** Geography */
	public static List<ExciseGeo> getExciseGeoList() {
		return Collections.unmodifiableList(EXCISE_GEO_LIST);
	}

	public static List<ExciseProvice> getExciseProviceList(String geoId) {
		return Collections.unmodifiableList(EXCISE_PROVINCE_MAPPER.get(geoId));
	}

	public static List<ExciseAmphur> getExciseAmphurList(String proviceId) {
		return Collections.unmodifiableList(EXCISE_AMPHUR_MAPPER.get(proviceId));
	}

	public static List<ExciseDistrict> getExciseDistrictList(String amphurId) {
		return Collections.unmodifiableList(EXCISE_DISTRICT_MAPPER.get(amphurId));
	}
	/********************* Method for Get Cache - End *********************/

	
	private void loadParameter() {
		logger.info("load Paramter loading...");

		PARAM_GROUP_MAP.clear();
		PARAM_INFO_MAP.clear();

		List<? extends ParamGroup> paramGroupList = parameterGroupRepository.findAll();
		List<? extends ParamInfo> paramInfoList = null;
		for (ParamGroup paramGroup : paramGroupList) {
			logger.debug("load parameterGroupCode: {}", paramGroup.getParamGroupCode());

			paramInfoList = parameterInfoRepository.findByParamGroupCode(paramGroup.getParamGroupCode());
			for (ParamInfo paramInfo : paramInfoList) {
				PARAM_INFO_MAP.put(paramInfo.getParamInfoId(), paramInfo);
			}

			PARAM_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParamGroupWrapper(paramGroup, paramInfoList));
		}

		logger.info("load Paramter loaded [{}]", PARAM_GROUP_MAP.size());
	}

	private void loadMessage() {
		logger.info("load Message loading...");

		MESSAGE_MAP.clear();

		messageRepository.findAll().forEach(m -> MESSAGE_MAP.put(m.getMessageCode(), m));

		logger.info("load Message loaded [{}]", MESSAGE_MAP.size());
	}
	
	private void loadExciseDepartment() {
		logger.info("load ExciseDepartment loading...");
		
		EXCISE_DEPT_MAP.clear();
		EXCISE_SECTOR_MAP.clear();
		EXCISE_AREA_MAP.clear();
		EXCISE_BRANCH_MAP.clear();
		
		List<? extends ExciseDept> exciseDepartmentList = exciseDepartmentRepository.findAll();
		exciseDepartmentList.sort((p1, p2) -> p2.getOfficeCode().compareTo(p1.getOfficeCode()));
		
		for (ExciseDept exciseDepartment : exciseDepartmentList) {
			EXCISE_DEPT_MAP.put(exciseDepartment.getOfficeCode(), exciseDepartment);
			
			if (Pattern.matches("^.{2}0{4}$", exciseDepartment.getOfficeCode())) {
				EXCISE_SECTOR_MAP.put(exciseDepartment.getOfficeCode(), exciseDepartment);
			} else if (Pattern.matches(exciseDepartment.getOfficeCode().substring(0, 2) + "([0-9]{1}[1-9]{1}|[1-9][0-9])00", exciseDepartment.getOfficeCode())) {
				List<ExciseDept> areaList = EXCISE_AREA_MAP.get(exciseDepartment.getOfficeCode().substring(0, 2) + "0000");
				if (areaList == null) {
					areaList = new ArrayList<>();
					areaList.add(exciseDepartment);
				} else {
					areaList.add(exciseDepartment);
				}
				EXCISE_AREA_MAP.put(exciseDepartment.getOfficeCode().substring(0, 2) + "0000", areaList);

			} else if (Pattern.matches(exciseDepartment.getOfficeCode().substring(0, 4) + "([0-9]{1}[1-9]{1}|[1-9][0-9])", exciseDepartment.getOfficeCode())) {
				List<ExciseDept> branch = EXCISE_BRANCH_MAP.get(exciseDepartment.getOfficeCode().substring(0, 4) + "00");
				if (branch == null) {
					branch = new ArrayList<>();
					branch.add(exciseDepartment);
				} else {
					branch.add(exciseDepartment);
				}
				EXCISE_BRANCH_MAP.put(exciseDepartment.getOfficeCode().substring(0, 4) + "00", branch);

			} else {
				logger.warn("This officeCode is not match, [{}]", exciseDepartment.getOfficeCode());
			}
		}
		
		// Sorting
		EXCISE_AREA_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		EXCISE_BRANCH_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		
		logger.info("load ExciseDepartment Sector={}, Area={}, Branch={}", EXCISE_SECTOR_MAP.size(), EXCISE_AREA_MAP.size(), EXCISE_BRANCH_MAP.size());
	}

	private void loadGeography() {
		EXCISE_GEO_LIST.clear();
		EXCISE_PROVINCE_MAPPER.clear();
		EXCISE_AMPHUR_MAPPER.clear();
		EXCISE_DISTRICT_MAPPER.clear();
		
		List<ExciseGeo> ecExciseGeoList = exciseGeoService.findExciseGeoListByCriteria(null);
		List<ExciseProvice> exciseProvinceList = exciseProvinceService.findByCriteria(null);
		List<ExciseAmphur> exciseAmphurList = exciseAmphurService.findExciseAmphurListByCriteria(null);
		List<ExciseDistrict> exciseDistricList = exciseDistrictService.findExciseDistrictListByCriteria(null);
		for (ExciseGeo exciseGeo : ecExciseGeoList) {
			EXCISE_GEO_LIST.add(exciseGeo);
		}
		for (ExciseProvice province : exciseProvinceList) {
			List<ExciseProvice> provinceList = EXCISE_PROVINCE_MAPPER.get(province.getGeoId().toString());
			if(provinceList == null) {
				provinceList = new ArrayList<ExciseProvice>();
			}
			provinceList.add(province);
			EXCISE_PROVINCE_MAPPER.put(province.getGeoId().toString(), provinceList);
		}
		for (ExciseAmphur amphur : exciseAmphurList) {
			List<ExciseAmphur> amphurList = EXCISE_AMPHUR_MAPPER.get(amphur.getProvinceId().toString());
			if(amphurList == null) {
				amphurList = new ArrayList<ExciseAmphur>();
			}
			amphurList.add(amphur);
			EXCISE_AMPHUR_MAPPER.put(amphur.getProvinceId().toString(), amphurList);
		}
		for (ExciseDistrict district : exciseDistricList) {
			List<ExciseDistrict> districtList = EXCISE_DISTRICT_MAPPER.get(district.getAmphurId().toString());
			if(districtList == null) {
				districtList = new ArrayList<ExciseDistrict>();
			}
			districtList.add(district);
			EXCISE_DISTRICT_MAPPER.put(district.getAmphurId().toString(), districtList);
		}
		
	}

}
