package th.co.baiwa.buckwaframework.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

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
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.domain.ExciseAmphur;
import th.go.excise.ims.common.domain.ExciseDistrict;
import th.go.excise.ims.common.domain.ExciseGeo;
import th.go.excise.ims.common.domain.ExciseProvince;
import th.go.excise.ims.common.persistence.entity.ExciseDepartment;
import th.go.excise.ims.common.service.ExciseAmphurService;
import th.go.excise.ims.common.service.ExciseDepartmentService;
import th.go.excise.ims.common.service.ExciseDistrictService;
import th.go.excise.ims.common.service.ExciseGeoService;
import th.go.excise.ims.common.service.ExciseProvinceService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);

	@Autowired
	public final ExciseDepartmentService exciseDepartmentService = null;

	@Autowired
	public final ExciseGeoService exciseGeoService = null;

	@Autowired
	public final ExciseProvinceService exciseProvinceService = null;

	@Autowired
	public final ExciseAmphurService exciseAmphurService = null;

	@Autowired
	public final ExciseDistrictService exciseDistrictService = null;

	private static final List<ExciseDepartment> EXCISE_SECTOR_LIST = new ArrayList<ExciseDepartment>();
	private static final ConcurrentHashMap<String, List<ExciseDepartment>> EXCISE_AREA_MAPPER = new ConcurrentHashMap<String, List<ExciseDepartment>>();
	private static final ConcurrentHashMap<String, List<ExciseDepartment>> EXCISE_BRANCH_MAPPER = new ConcurrentHashMap<String, List<ExciseDepartment>>();

	private static final List<ExciseGeo> EXCISE_GEO_LIST = new ArrayList<ExciseGeo>();
	private static final ConcurrentHashMap<String, List<ExciseProvince>> EXCISE_PROVINCE_MAPPER = new ConcurrentHashMap<String, List<ExciseProvince>>();
	private static final ConcurrentHashMap<String, List<ExciseAmphur>> EXCISE_AMPHUR_MAPPER = new ConcurrentHashMap<String, List<ExciseAmphur>>();
	private static final ConcurrentHashMap<String, List<ExciseDistrict>> EXCISE_DISTRICT_MAPPER = new ConcurrentHashMap<String, List<ExciseDistrict>>();

	private static final ConcurrentHashMap<String, ParamGroupWrapper> PARAM_GROUP_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<Long, ParamInfo> PARAM_INFO_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Message> MESSAGE_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Object> COMMON_CACHE = new ConcurrentHashMap<>();

	@Autowired
	private final ParameterGroupRepository parameterGroupRepository = null;
	@Autowired
	private final ParameterInfoRepository parameterInfoRepository = null;
	@Autowired
	private final MessageRepository messageRepository = null;

	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		loadSectorAreaBranch();
		loadGioProviceAumhurDistrictAndMapping();
		loadParameter();
		loadMessage();
		logger.info("ApplicationCache Reloaded");
	}

	public void loadSectorAreaBranch() {
		logger.info("exciseSectorAreaBranchInitialData");
		List<ExciseDepartment> exciseDepartmentList = exciseDepartmentService.findAll();
		for (ExciseDepartment exciseDepartment : exciseDepartmentList) {
			if (Pattern.matches("^.{2}0{4}$", exciseDepartment.getOfficeCode())) {
				EXCISE_SECTOR_LIST.add(exciseDepartment);
			} else if (Pattern.matches(exciseDepartment.getOfficeCode().substring(0, 2) + "([0-9]{1}[1-9]{1}|[1-9][0-9])00", exciseDepartment.getOfficeCode())) {
				List<ExciseDepartment> areaList = EXCISE_AREA_MAPPER.get(exciseDepartment.getOfficeCode().substring(0, 2) + "0000");
				if (areaList == null) {
					areaList = new ArrayList<>();
					areaList.add(exciseDepartment);
				} else {
					areaList.add(exciseDepartment);
				}
				EXCISE_AREA_MAPPER.put(exciseDepartment.getOfficeCode().substring(0, 2) + "0000", areaList);

			} else if (Pattern.matches(exciseDepartment.getOfficeCode().substring(0, 4) + "([0-9]{1}[1-9]{1}|[1-9][0-9])", exciseDepartment.getOfficeCode())) {
				List<ExciseDepartment> branch = EXCISE_BRANCH_MAPPER.get(exciseDepartment.getOfficeCode().substring(0, 4) + "00");
				if (branch == null) {
					branch = new ArrayList<>();
					branch.add(exciseDepartment);
				} else {
					branch.add(exciseDepartment);
				}
				EXCISE_BRANCH_MAPPER.put(exciseDepartment.getOfficeCode().substring(0, 4) + "00", branch);

			} else {
				logger.info(exciseDepartment.getOfficeCode());
			}
		}
	}

	public void loadGioProviceAumhurDistrictAndMapping() {
		List<ExciseGeo> ecExciseGeoList = exciseGeoService.findExciseGeoListByCriteria(null);
		List<ExciseProvince> exciseProvinceList = exciseProvinceService.findProviceByCriteria(null);
		List<ExciseAmphur> exciseAmphurList = exciseAmphurService.findExciseAmphurListByCriteria(null);
		List<ExciseDistrict> exciseDistricList = exciseDistrictService.findExciseDistrictListByCriteria(null);
		for (ExciseGeo exciseGeo : ecExciseGeoList) {
			EXCISE_GEO_LIST.add(exciseGeo);
		}
		for (ExciseProvince province : exciseProvinceList) {
			List<ExciseProvince> provinceList = EXCISE_PROVINCE_MAPPER.get(province.getGeoId().toString());
			if(provinceList == null) {
				provinceList = new ArrayList<ExciseProvince>();
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

	public static List<ExciseDepartment> getExciseSectorList() {
		return Collections.unmodifiableList(EXCISE_SECTOR_LIST);
	}

	public static List<ExciseDepartment> getExciseAreaList(String officeCode) {
		return Collections.unmodifiableList(EXCISE_AREA_MAPPER.get(officeCode));
	}

	public static List<ExciseDepartment> getExciseBranchList(String officeCode) {
		return Collections.unmodifiableList(EXCISE_BRANCH_MAPPER.get(officeCode));
	}

	public static List<ExciseGeo> getExciseGeoList() {
		return Collections.unmodifiableList(EXCISE_GEO_LIST);
	}

	public static List<ExciseProvince> getExciseProvinceList(String geoId) {
		return Collections.unmodifiableList(EXCISE_PROVINCE_MAPPER.get(geoId));
	}

	public static List<ExciseAmphur> getExciseAmphurList(String proviceId) {
		return Collections.unmodifiableList(EXCISE_AMPHUR_MAPPER.get(proviceId));
	}

	public static List<ExciseDistrict> getExciseDistrictList(String amphurId) {
		return Collections.unmodifiableList(EXCISE_DISTRICT_MAPPER.get(amphurId));
	}

	/********************* Method for Get Cache - Start *********************/

	/** Parameter Group & Parameter Info */
	public static ParamGroup getParamGroupByCode(String paramGroupCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamGroup();
	}

	public static ParamInfo getParamInfoById(Long paramInfoId) {
		return PARAM_INFO_MAP.get(paramInfoId);
	}

	public static ParamInfo getParamInfoByCode(String paramGroupCode, String paramInfoCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamInfoCodeMap().get(paramInfoCode);
	}

	public static List<ParamGroup> getParamGroupList() {
		List<ParamGroup> resultList = new ArrayList<>();
		for (Entry<String, ParamGroupWrapper> entry : PARAM_GROUP_MAP.entrySet()) {
			resultList.add(entry.getValue().getParamGroup());
		}
		return Collections.unmodifiableList(resultList);
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

	/** Common Cache */
	public static Object getCommonCache(String cacheName) {
		return COMMON_CACHE.get(cacheName);
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

			paramInfoList = parameterInfoRepository.findByParamGroupId(paramGroup.getParamGroupId());
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

}
