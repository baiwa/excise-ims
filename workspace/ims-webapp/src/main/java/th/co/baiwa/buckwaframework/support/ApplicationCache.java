package th.co.baiwa.buckwaframework.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

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
import th.go.excise.ims.common.domain.ExciseArea;
import th.go.excise.ims.common.domain.ExciseBranch;
import th.go.excise.ims.common.domain.ExciseDistrict;
import th.go.excise.ims.common.domain.ExciseGeo;
import th.go.excise.ims.common.domain.ExciseProvince;
import th.go.excise.ims.common.domain.ExciseSector;
import th.go.excise.ims.common.service.ExciseAmphurService;
import th.go.excise.ims.common.service.ExciseAreaService;
import th.go.excise.ims.common.service.ExciseBranchService;
import th.go.excise.ims.common.service.ExciseDistrictService;
import th.go.excise.ims.common.service.ExciseGeoService;
import th.go.excise.ims.common.service.ExciseProvinceService;
import th.go.excise.ims.common.service.ExciseSectorService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	
	@Autowired
	public final ExciseSectorService exciseSectorService = null;
	
	@Autowired
	public final ExciseAreaService exciseAreaService = null;
	
	@Autowired
	public final ExciseBranchService exciseBranchService = null;
	
	@Autowired
	public final ExciseGeoService exciseGeoService = null;
	
	@Autowired
	public final ExciseProvinceService exciseProvinceService = null;
	
	@Autowired
	public final ExciseAmphurService exciseAmphurService = null;

	@Autowired
	public final ExciseDistrictService exciseDistrictService = null;
	
	private static final List<ExciseSector> EXCISE_SECTOR_LIST = new ArrayList<ExciseSector>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseArea>> EXCISE_AREA_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseArea>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseBranch>> EXCISE_BRANCH_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseBranch>>();
	
	private static final List<ExciseGeo> EXCISE_GEO_LIST = new ArrayList<ExciseGeo>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseProvince>> EXCISE_PROVINCE_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseProvince>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseAmphur>> EXCISE_AMPHUR_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseAmphur>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseDistrict>> EXCISE_DISTRICT_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseDistrict>>();
	
	
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
		
		logger.info("ApplicationCache Reloaded");
	}
	
	public void loadSectorAreaBranch() {
		logger.info("exciseSectorAreaBranchInitialData");
		List<ExciseSector> exciseSectorList = exciseSectorService.findAllExciseSector();
		if(exciseSectorList != null && exciseSectorList.size() > 0) {
			for (ExciseSector exciseSector : exciseSectorList) {
				EXCISE_SECTOR_LIST.add(exciseSector);
				
			}
		}
//		EXCISE_AREA_MAPPER
		List<ExciseArea> exciseAreaList = null;
		List<ExciseBranch> exciseBranchList = null;
		for (ExciseSector exciseSector : exciseSectorList) {
			exciseAreaList = new ArrayList<>();
			exciseAreaList = exciseAreaService.findAllExciseSector(exciseSector.getSectorId());
			if(exciseAreaList != null && exciseAreaList.size() >= 0) {
				EXCISE_AREA_MAPPER.put(exciseSector.getSectorId(),exciseAreaList);
				for (ExciseArea exciseArea : exciseAreaList) {
					exciseBranchList =  new ArrayList<ExciseBranch>();
					exciseBranchList = exciseBranchService.findBySectorId(exciseArea.getAreaId());
					if(exciseBranchList != null) {
						EXCISE_BRANCH_MAPPER.put(exciseArea.getAreaId(), exciseBranchList);
					}
				}
			}
		}
	}
	
	public void loadGioProviceAumhurDistrictAndMapping() {
		List<ExciseGeo> ecExciseGeoList = exciseGeoService.findExciseGeoListByCriteria(null);
		List<ExciseAmphur> exciseAmphurList;
		ExciseProvince exciseProvince;
		ExciseAmphur exciseAmphur;
		ExciseDistrict exciseDistrict;
		List<ExciseProvince> exciseProvinceList;
		List<ExciseDistrict> exciseDistricList;
		for (ExciseGeo exciseGeo : ecExciseGeoList) {
			EXCISE_GEO_LIST.add(exciseGeo);
			exciseProvince = new ExciseProvince();
			exciseProvince.setGeoId(exciseGeo.getGeoId());
			exciseProvinceList = new ArrayList<>();
			exciseProvinceList = exciseProvinceService.findProviceByCriteria(exciseProvince);
			EXCISE_PROVINCE_MAPPER.put(exciseGeo.getGeoId(), exciseProvinceList);
			for (ExciseProvince provice : exciseProvinceList) {
				exciseAmphur = new ExciseAmphur();
				exciseAmphur.setProvinceId(provice.getProviceId());
				exciseAmphurList = new ArrayList<ExciseAmphur>();
				exciseAmphurList = exciseAmphurService.findExciseAmphurListByCriteria(exciseAmphur);
				EXCISE_AMPHUR_MAPPER.put(provice.getProviceId(), exciseAmphurList);
				for (ExciseAmphur amphur : exciseAmphurList) {
					exciseDistrict = new ExciseDistrict();
					exciseDistrict.setAmphurId(amphur.getAmphurId());
					exciseDistricList = new ArrayList<ExciseDistrict>();
					exciseDistricList = exciseDistrictService.findExciseDistrictListByCriteria(exciseDistrict);
					EXCISE_DISTRICT_MAPPER.put(amphur.getAmphurId(), exciseDistricList);
				}
			}
		}
	}
	
	public static List<ExciseSector> getExciseSectorList(){
		return EXCISE_SECTOR_LIST;
	}
	
	public static List<ExciseArea> getExciseAreaList(BigDecimal sectorId){
		return EXCISE_AREA_MAPPER.get(sectorId);
	}
	
	public static List<ExciseBranch> getExciseBranchList(BigDecimal areaId){
		return EXCISE_BRANCH_MAPPER.get(areaId);
	}
	
	public static List<ExciseGeo> getExciseGeoList(BigDecimal geoId){
		return EXCISE_GEO_LIST;
	}
	
	public static List<ExciseProvince> getExciseProvinceList(BigDecimal proviceId){
		return EXCISE_PROVINCE_MAPPER.get(proviceId);
	}
	
	public static List<ExciseAmphur> getExciseAmphurList(BigDecimal amphurId){
		return EXCISE_AMPHUR_MAPPER.get(amphurId);
	}
	
	public static List<ExciseDistrict> getExciseDistrictList(BigDecimal districtId){
		return EXCISE_DISTRICT_MAPPER.get(districtId);
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
