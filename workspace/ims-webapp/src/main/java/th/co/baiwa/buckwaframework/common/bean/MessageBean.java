//package th.co.baiwa.framework.common.bean;
//
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import th.co.baiwa.framework.common.ApplicationCache;
//
//public class MessageBean implements Serializable {
//	
//	private static final long serialVersionUID = 5936643790764604468L;
//	
//	private String messageType;
//	private String message;
//	private StackTraceElement[] stackTrace;
//
//	public MessageBean(String messageType, String message, StackTraceElement[] stackTrace) {
//		this.messageType = messageType;
//		this.message = message;
//		this.stackTrace = stackTrace;
//	}
//
//	public MessageBean addReplaceMessage(String regex, String replacement) {
//		this.message = message.replaceAll(regex, replacement);
//		return this;
//	}
//
//	/*
//	 * Static Method
//	 */
//	public static MessageBean createMessageBean(String messageType, String message) {
//		return new MessageBean(messageType, message, null);
//	}
//
//	public static MessageBean createMessageBean(String messageType, String message, StackTraceElement[] stackTrace) {
//		return new MessageBean(messageType, message, stackTrace);
//	}
//
//	public static MessageBean createMessageCodeBean(String messageCode) {
//		return createMessageCodeBean(messageCode, null, null);
//	}
//
//	public static MessageBean createMessageCodeBean(String messageCode, StackTraceElement[] stackTrace) {
//		return createMessageCodeBean(messageCode, null, stackTrace);
//	}
//
//	public static MessageBean createMessageCodeBean(String messageCode, Map<String, String> replaceMessageMap) {
//		String message = ApplicationCache.getMessage(messageCode);
//		
//		if (replaceMessageMap != null && !replaceMessageMap.isEmpty()) {
//			for (Entry<String, String> entry : replaceMessageMap.entrySet()) {
//				message = message.replaceAll(entry.getKey(), entry.getValue());
//			}
//		}
//		
//		return createMessageCodeBean(messageCode, replaceMessageMap, null);
//	}
//
//	public static MessageBean createMessageCodeBean(String messageCode, Map<String, String> replaceMessageMap, StackTraceElement[] stackTrace) {
//		/** Check messageType */
//		String messageType = messageCode.substring(0, 1);
//
//		/** Get & Replace Message */
//		String message = ApplicationCache.getMessage(messageCode);
//		if (replaceMessageMap != null) {
//			for (Entry<String, String> entry : replaceMessageMap.entrySet()) {
//				message = message.replaceAll(entry.getKey(), entry.getValue());
//			}
//		}
//		
//		return new MessageBean(messageType, message, stackTrace);
//	}
//
//	/*
//	 * Getter & Setter
//	 */
//	public String getMessageType() {
//		return messageType;
//	}
//
//	public void setMessageType(String messageType) {
//		this.messageType = messageType;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public StackTraceElement[] getStackTrace() {
//		return stackTrace;
//	}
//
//	public void setStackTrace(StackTraceElement[] stackTrace) {
//		this.stackTrace = stackTrace;
//	}
//
//}
