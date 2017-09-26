package th.co.baiwa.buckwaframework.admin.web.controller;
//package th.co.baiwa.framework.admin.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import th.co.baiwa.framework.admin.persistence.entity.User;
//import th.co.baiwa.framework.admin.service.UserService;
//import th.co.baiwa.framework.common.bean.DataTableAjax;
//import th.co.baiwa.framework.common.bean.EditDataTable;
//import th.co.baiwa.framework.common.constant.CommonConstants.ANGULAR2;
//import th.co.baiwa.framework.preferences.entity.ParameterInfo;
//
//@Controller
//@RequestMapping(value = "/admin/user")
//public class UserManagementController {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private UserService userService;
//
//	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
//	public ModelAndView newDemoForm(HttpServletRequest httpRequest) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("listUser");
//		System.out.println(" ####### listUser ####");
//
//		return mav;
//	}
//
//	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
//	public ModelAndView home(HttpServletRequest httpRequest) {
//		logger.info("home");
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(ANGULAR2.VIWE_NAME);
//		mav.addObject(ANGULAR2.APP_NAME, "baiwa.framework/user.main");
//
//		return mav;
//	}
//
//	@RequestMapping(value = "/getUser.json", method = RequestMethod.POST)
//	public @ResponseBody DataTableAjax<User> getUser(Integer start, Integer length) {
//		DataTableAjax<User> dataTableAjax = new DataTableAjax<>();
//		dataTableAjax = userService.queryUserList(start, length);
//
//		return dataTableAjax;
//	}
//	
//	
//	@RequestMapping(value = "/userAction.json", method = RequestMethod.POST)
//	public @ResponseBody EditDataTable<User> userAction(@RequestBody EditDataTable<User> editDataTable) {
//		logger.info("userAction");
//		
//		try {
//			userService.userAction(editDataTable);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			editDataTable.setError(e.getMessage());
//		}
//		
//		return editDataTable;
//	}
//}
