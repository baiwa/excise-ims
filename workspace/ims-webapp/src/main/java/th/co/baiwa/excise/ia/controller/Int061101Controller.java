package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.vo.Int061101FormVo;
import th.co.baiwa.excise.ia.service.Int061101Service;

@Controller
@RequestMapping("api/ia/int061101")
public class Int061101Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int061101Service int061101Service;

	@PostMapping("/save")
	@ResponseBody
	public RentHouse save(@RequestBody RentHouse en) {
		logger.info("save Int061101");
		RentHouse data = new RentHouse();
		try {
			data = int061101Service.save(en);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return data;
	}

	@PostMapping("/upload")
	@ResponseBody
	public Message uploadList(@ModelAttribute Int061101FormVo uploadList) {
		logger.info("upload Int061101");
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		try {
			int061101Service.uploadList(uploadList);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	// @PostMapping("/getAuthLogin")
	// @ResponseBody
	// public Int061101GetUserLogin get() {
	// logger.info("get info user-login Int061101");
	// UserBean pf = null;
	//
	// Int061101GetUserLogin userLogin = new Int061101GetUserLogin();
	// pf = UserLoginUtils.getCurrentUserBean();
	// userLogin.setGetAccessAttr(pf.getAccessAttr());
	// userLogin.setGetCnName(pf.getCnName());
	// userLogin.setGetOfficeId(pf.getOfficeId());
	// userLogin.setGetTelephoneNo(pf.getTelephoneNo());
	// userLogin.setGetUserEngName(pf.getUserEngName());
	// userLogin.setGetUserEngSurname(pf.getUserEngSurname());
	// userLogin.setGetUserId(pf.getUserId());
	// userLogin.setGetUsername(pf.getUsername());
	// userLogin.setGetTitle(pf.getTitle());
	// userLogin.setGetUserThaiId(pf.getUserThaiId());
	// userLogin.setGetUserThaiName(pf.getUserThaiName());
	// userLogin.setGetUserThaiSurname(pf.getUserThaiSurname());
	// return userLogin;
	//
	// }

}
