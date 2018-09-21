package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.Int0533Vo;
import th.co.baiwa.excise.ia.service.AssetBalanceService;

@Controller
@RequestMapping("api/ia/int0533")
public class Int0533Controller {
	private Logger logger = LoggerFactory.getLogger(Int0533Controller.class);
	
	@Autowired
	private AssetBalanceService assetBalanceService;
	
	@PostMapping("/addAssetBalance")
	@ResponseBody
	public Message addAssetBalance(@RequestBody Int0533Vo int0533Vo) {
		logger.info("addAssetBalance" + int0533Vo.getAssetBalance().getAssetType());
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		
		return null;
	} 

}
