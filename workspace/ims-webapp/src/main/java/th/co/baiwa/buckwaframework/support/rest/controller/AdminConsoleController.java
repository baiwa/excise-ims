//package th.co.baiwa.buckwaframework.support.rest.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import th.co.baiwa.framework.common.ApplicationCache;
//import th.co.baiwa.framework.common.MessageSessionBean;
//import th.co.baiwa.framework.common.bean.MessageBean;
//import th.co.baiwa.framework.common.constant.MessageConstants.MESSAGE_CODE;
//import th.co.baiwa.framework.common.util.UserLoginUtils;
//
//@Controller
//@RequestMapping("/consoles")
//public class AdminConsoleController {
//
//	private static final Logger logger = LoggerFactory.getLogger(AdminConsoleController.class);
//	
//	@Autowired
//	private PreferencesContextUtils applicationCache;
//	
//	@RequestMapping(value = "/admin-console.htm", method = RequestMethod.GET)
//	public ModelAndView adminConsole() {
//		logger.info(UserLoginUtils.logging("adminConsole"));
//
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("admin-console");
//
//		return mav;
//	}
//	
//	@RequestMapping(value = "/reload-cache.htm", method = RequestMethod.GET)
//	public String reloadCache() throws Exception {
//		logger.info(UserLoginUtils.logging("reloadCache"));
//		
//		try {
//			applicationCache.reloadCache();
//			SysMessage messageBean = SysMessage.createMessageCodeBean(MESSAGE_CODE.INFO_ACTION_SUCCESSFUL);
//			messageBean.addReplaceMessage("###1", "Reload Cache");
//			MessageSessionBean.getInstance().addMessageBean(messageBean);
//			
//		} catch(Exception e) {
//			SysMessage messageBean = SysMessage.createMessageCodeBean(MESSAGE_CODE.ERROR_ACTION_EXCEPTION);
//			messageBean.addReplaceMessage("###1", "Reload Cache");
//			MessageSessionBean.getInstance().addMessageBean(messageBean);
//		}
//	
//		return "redirect:/consoles/admin-console.htm";
//	}
//	
//}