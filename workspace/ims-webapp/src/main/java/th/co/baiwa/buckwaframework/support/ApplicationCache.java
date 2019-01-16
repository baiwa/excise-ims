package th.co.baiwa.buckwaframework.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.common.util.BeanUtils;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterGroupRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.co.baiwa.excise.ta.service.ListOfValueService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);

	private static final ConcurrentHashMap<String, ParameterGroupWrapper> PARAMETER_GROUP_MAP = new ConcurrentHashMap<String, ParameterGroupWrapper>();
	private static final ConcurrentHashMap<Long, ParameterInfo> PARAMETER_INFO_MAP = new ConcurrentHashMap<Long, ParameterInfo>();
	private static final ConcurrentHashMap<String, Message> MESSAGE_MAP = new ConcurrentHashMap<String, Message>();
	private static final ConcurrentHashMap<String, Object> COMMON_CACHE = new ConcurrentHashMap<String, Object>();
	private static final ConcurrentHashMap<String, List<Lov>> LOV_GROUP_VALUE = new ConcurrentHashMap<String, List<Lov>>();

	private final ParameterGroupRepository parameterGroupRepository;
	private final ParameterInfoRepository parameterInfoRepository;
	private final MessageRepository messageRepository;
	private final ListOfValueService listOfValueService;

	@Autowired
	public ApplicationCache(ParameterGroupRepository parameterGroupRepository, ParameterInfoRepository parameterInfoRepository, MessageRepository messageRepository, ListOfValueService listOfValueService) {
		super();
		this.parameterGroupRepository = parameterGroupRepository;
		this.parameterInfoRepository = parameterInfoRepository;
		this.messageRepository = messageRepository;
		this.listOfValueService = listOfValueService;
	}

	/********************* Method for Get Cache - Start *********************/

	/** Parameter Group & Parameter Info */
	public static ParameterGroup getParameterGroupByCode(String paramGroupCode) {
		ParameterGroup result = PARAMETER_GROUP_MAP.get(paramGroupCode).getParameterGroup();
		return result;
	}

	public static ParameterInfo getParameterInfoById(Long paramInfoId) {
		return PARAMETER_INFO_MAP.get(paramInfoId);
	}

	public static ParameterInfo getParameterInfoByCode(String paramGroupCode, String paramInfoCode) {
		return PARAMETER_GROUP_MAP.get(paramGroupCode).getParameterInfoCodeMap().get(paramInfoCode);
	}

	public static List<Lov> getListOfValueByValueType(String lovType) {
		return LOV_GROUP_VALUE.get(lovType);
	}

	public static List<Lov> getListOfValueByTypeParentId(String lovType, Long parentId) {
		List<Lov> lovListByType = LOV_GROUP_VALUE.get(lovType);
		List<Lov> lovListByTypeParentId = new ArrayList<Lov>();
		for (Lov lov : lovListByType) {
			if(parentId == null ) {
				parentId = new Long(0);
			}
			//System.out.println(lov.getLovIdMaster());
			if (lov.getLovIdMaster().equals(parentId)) {
				lovListByTypeParentId.add(lov);
			}
		}
		if (lovListByTypeParentId.size() > 0) {
			Collections.sort(lovListByTypeParentId, new Comparator<Lov>() {
				@Override
				public int compare(final Lov object1, final Lov object2) {
					return object1.getLovId().compareTo(object2.getLovId());
				}
			});
		}
		return lovListByTypeParentId;
	}

	public static List<Lov> getListOfValueByValueType(String lovType, String subType) {
		List<Lov> lovBySubtype = new ArrayList<Lov>();
		List<Lov> lovList = LOV_GROUP_VALUE.get(lovType);
		if (BeanUtils.isNotEmpty(subType)) {
			for (Lov lov : lovList) {
				if (subType.equals(lov.getSubType())) {
					lovBySubtype.add(lov);
				}
			}
		} else {
			return lovList;
		}

		return lovBySubtype;
	}

	static final class ParameterGroupWrapper {

		private ParameterGroup parameterGroup;
		private Map<String, ParameterInfo> parameterInfoCodeMap = new HashMap<String, ParameterInfo>();

		public ParameterGroupWrapper(ParameterGroup paramGroup, List<ParameterInfo> paramInfoList) {
			this.parameterGroup = paramGroup;
			for (ParameterInfo paramInfo : paramInfoList) {
				parameterInfoCodeMap.put(paramInfo.getParamCode(), paramInfo);
			}
		}

		public ParameterGroup getParameterGroup() {
			return parameterGroup;
		}

		public void setParameterGroup(ParameterGroup parameterGroup) {
			this.parameterGroup = parameterGroup;
		}

		public Map<String, ParameterInfo> getParameterInfoCodeMap() {
			return parameterInfoCodeMap;
		}

		public void setParameterInfoCodeMap(Map<String, ParameterInfo> parameterInfoCodeMap) {
			this.parameterInfoCodeMap = parameterInfoCodeMap;
		}

	}

	/** Message */
	public static Map<String, Message> getMessages() {
		return MESSAGE_MAP;
	}

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

	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");

		loadParameterGroup();

		loadMessage();

		loadLov();

		logger.info("ApplicationCache Reloaded");
	}

	private void loadParameterGroup() {
		logger.info("load ParamterGroup-Info loading...");

		PARAMETER_GROUP_MAP.clear();
		PARAMETER_INFO_MAP.clear();

		List<ParameterGroup> paramGroupList = parameterGroupRepository.findAll();
		List<ParameterInfo> paramInfoList = null;
		for (ParameterGroup paramGroup : paramGroupList) {
			logger.debug("load ParameterGroup [id] : " + paramGroup.getParamGroupId() + ",\t[parameterGroupCode] : " + paramGroup.getParamGroupCode());

			paramInfoList = parameterInfoRepository.findByParamGroupId(paramGroup.getParamGroupId());
			for (ParameterInfo paramInfo : paramInfoList) {
				PARAMETER_INFO_MAP.put(paramInfo.getParamInfoId(), paramInfo);
			}

			PARAMETER_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParameterGroupWrapper(paramGroup, paramInfoList));
		}

		logger.info("load ParamterGroup-Info loaded [" + PARAMETER_GROUP_MAP.size() + "-" + PARAMETER_INFO_MAP.size() + "]");
	}

	private void loadMessage() {
		logger.info("load Message loading...");

		MESSAGE_MAP.clear();

		List<Message> messageList = messageRepository.findAll();
		for (Message message : messageList) {
			MESSAGE_MAP.put(message.getMessageCode(), message);
		}

		logger.info("load Message loaded [" + MESSAGE_MAP.size() + "]");
	}

	private void loadLov() {
		logger.info("load List Of value loading...");
		LOV_GROUP_VALUE.clear();
		List<Lov> lovList = null;
		List<String> lovTypeList = listOfValueService.queryLovTypeList();
		for (String type : lovTypeList) {
			lovList = new ArrayList<Lov>();
			lovList = listOfValueService.queryLovByCriteria(new Lov(type), "");
			if (BeanUtils.isNotEmpty(lovList)) {
				LOV_GROUP_VALUE.put(type, lovList);
			}
		}
	}

}
