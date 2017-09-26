//package th.co.baiwa.buckwaframework.support;
//
//public class ApplicationCache {
//	
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired
//	private ParameterGroupDao parameterGroupDao;
//	@Autowired
//	private ParameterInfoDao parameterInfoDao;
//	
//	private static final ConcurrentHashMap<String, ParameterGroupWrapper> PARAMETER_GROUP_MAP = new ConcurrentHashMap<String, ParameterGroupWrapper>();
//	private static final ConcurrentHashMap<Long, SysParameterInfo> PARAMETER_INFO_MAP = new ConcurrentHashMap<Long, SysParameterInfo>();
//	private static final ConcurrentHashMap<String, SysMessage> MESSAGE_MAP = new ConcurrentHashMap<String, SysMessage>();
//	private static final ConcurrentHashMap<String, Object> COMMON_CACHE = new ConcurrentHashMap<String, Object>();
//	
//	
//	/********************* Method for Get Cache - Start *********************/
//	
//	/** Parameter Group & Parameter Info */
//	public static SysParameterGroup getParameterGroupByCode(String groupCode) {
//		SysParameterGroup result = PARAMETER_GROUP_MAP.get(groupCode).getParameterGroup();
//		return result;
//	}
//	
//	public static SysParameterInfo getParameterInfoById(Long parameterInfoId) {
//		return PARAMETER_INFO_MAP.get(parameterInfoId);
//	}
//	
//	public static SysParameterInfo getParameterInfoByCode(String groupCode, String infoCode) {
//		return PARAMETER_GROUP_MAP.get(groupCode).getParameterInfoCodeMap().get(infoCode);
//	}
//	
//	static final class ParameterGroupWrapper {
//		private SysParameterGroup parameterGroup;
//		private Map<String, SysParameterInfo> parameterInfoCodeMap = new HashMap<String, SysParameterInfo>();
//		
//		public ParameterGroupWrapper(SysParameterGroup paramGroup, List<SysParameterInfo> paramInfoList) {
//			this.parameterGroup = paramGroup;
//			for (SysParameterInfo paramInfo : paramInfoList) {
//				parameterInfoCodeMap.put(paramInfo.getParamCode(), paramInfo);
//			}
//		}
//		
//		public SysParameterGroup getParameterGroup() {
//			return parameterGroup;
//		}
//		
//		public void setParameterGroup(SysParameterGroup parameterGroup) {
//			this.parameterGroup = parameterGroup;
//		}
//		
//		public Map<String, SysParameterInfo> getParameterInfoCodeMap() {
//			return parameterInfoCodeMap;
//		}
//		
//		public void setParameterInfoCodeMap(Map<String, SysParameterInfo> parameterInfoCodeMap) {
//			this.parameterInfoCodeMap = parameterInfoCodeMap;
//		}
//	}
//	
//	/** Message Response */
//	public static String getMessage(String messageCode) {
//		SysMessage message = MESSAGE_MAP.get(messageCode);
//		return message != null ? message.getMessageTh() : null;
//	}
//	
//	/** Common Cache */
//	public static Object getCommonCache(String cacheName) {
//		return COMMON_CACHE.get(cacheName);
//	}
//	
//	/********************* Method for Get Cache - End *********************/
//	
//	
//	/** Reload */
//	public synchronized void reloadCache() {
//		logger.info("ApplicationCache Reloading...");
//		
//		loadParameterGroup();
//		
//		loadMessageResponse();
//		
//		loadCommonCache();
//		
//		logger.info("ApplicationCache Reloaded");
//	}
//	
//	private void loadParameterGroup() {
//		logger.info("load ParamterGroup-Info loading...");
//		
//		PARAMETER_GROUP_MAP.clear();
//		PARAMETER_INFO_MAP.clear();
//		
//		List<SysParameterGroup> paramGroupList = parameterGroupDao.findAllForCache();
//		List<SysParameterInfo> paramInfoList = null;
//		for (SysParameterGroup paramGroup : paramGroupList) {
//			logger.debug("load ParameterGroup [id] : " + paramGroup.getParamGroupId() + " \t,[parameterGroupCode] : " + paramGroup.getParamGroupCode());
//
//			paramInfoList = parameterInfoDao.findByParamGroupId(paramGroup.getParamGroupId());
//			for (SysParameterInfo paramInfo : paramInfoList) {
//				PARAMETER_INFO_MAP.put(paramInfo.getParamInfoId(), paramInfo);
//			}
//
//			PARAMETER_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParameterGroupWrapper(paramGroup, paramInfoList));
//		}
//		logger.info("load ParamterGroup-Info loaded [" + PARAMETER_GROUP_MAP.size() + "-" + PARAMETER_INFO_MAP.size() + "]");
//	}
//	
//	private void loadMessageResponse() {
//		logger.info("load Message loading...");
////		ParameterGroup group = parameterGroupDao.findByParamGroupForCache(PARAMETER_GROUP.MESSAGE_CODE);
////		if (group != null) {
////			MESSAGE_RESPONSE.clear();
////			for (ParameterInfo info : group.getParameterInfos()) {
////				if (ApplicationConstants.FLAG.N.equals(info.getIsDeleted())) {
////					MESSAGE_RESPONSE.put(info.getParamCode(), info);
////				}
////			}
////		}
//		logger.info("load Message loaded [" + MESSAGE_MAP.size() + "]");
//	}
//	
//	private void loadCommonCache() {
//		logger.info("load CommonCache loading...");
//		
//		COMMON_CACHE.clear();
//		//COMMON_CACHE.put(COMMON_CACHE_NAME.REQUEST_TAB_MAP, requestTabMap);
//		
//		logger.info("load CommonCache loaded");
//	}
//	
//}
