package th.co.baiwa.buckwaframework.accesscontrol.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Controller
@RequestMapping("/api/access-control/menu")
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@PostMapping("/list")
	@ApiOperation(
		tags = MODULE_NAME.ACCESS_CONTROL,
		value = "Get Menu List"
	)
	@ResponseBody
	public ResponseData<List<MenuVo>> getMenu() {
		logger.info("getMenu");

		ResponseData<List<MenuVo>> responseData = new ResponseData<>();
		try {
			responseData.setData(getMockMenu());
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(e.getMessage());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	private List<MenuVo> getMockMenu() {
		List<MenuVo> menuList = new ArrayList<>();
		
		// Level 0
		MenuVo menuTaRoot = new MenuVo();
		menuTaRoot.setMenuName("ตรวจสอบภาษี");
		menuTaRoot.setMenuVoList(new ArrayList<>());
		menuList.add(menuTaRoot);
		
		// Level 1 Record 1
		MenuVo menuTaL11 = new MenuVo();
		menuTaL11.setMenuName("1. กำหนดเงื่อนไขการวิเคราะห์ข้อมูล");
		menuTaL11.setUrl("/tax-audit-new/ta01/01");
		menuTaL11.setMenuVoList(new ArrayList<>());
		menuTaRoot.getMenuVoList().add(menuTaL11);
		
		// Level 1 Record 2
		MenuVo menuTaL12 = new MenuVo();
		menuTaL12.setMenuName("2. ค้นหาข้อมูลการชำระภาษีของผู้ประกอบการตามเงื่อนไขที่กำหนด");
		menuTaL12.setUrl("/tax-audit-new/ta0401");
		menuTaL12.setMenuVoList(new ArrayList<>());
		menuTaRoot.getMenuVoList().add(menuTaL12);
		
		// Level 1 Record 3
		MenuVo menuTaL13 = new MenuVo();
		menuTaL13.setMenuName("3. การคัดเลือกราย");
		menuTaL13.setMenuVoList(new ArrayList<>());
		menuTaRoot.getMenuVoList().add(menuTaL13);
		
		// Level 2
		{
			MenuVo menuTaL131 = new MenuVo();
			menuTaL131.setMenuName("3.1 รายการข้อมูลผู้ประกอบการที่เสียภาษีสรรพสามิต");
			menuTaL131.setUrl("/tax-audit-new/ta01/03");
			menuTaL131.setMenuVoList(new ArrayList<>());
			menuTaL13.getMenuVoList().add(menuTaL131);
			
			MenuVo menuTaL132 = new MenuVo();
			menuTaL132.setMenuName("3.2 ส่งกระดาษทำการข้อมูลผู้ประกอบการ");
			menuTaL132.setUrl("/tax-audit-new/ta01/04");
			menuTaL132.setMenuVoList(new ArrayList<>());
			menuTaL13.getMenuVoList().add(menuTaL132);
		}
		
		// Level 1 Record 4
		MenuVo menuTaL14 = new MenuVo();
		menuTaL14.setMenuName("5. บันทึกข้อความ หนังสือราชการ");
		menuTaL14.setUrl("/tax-audit-new/ta03/01");
		menuTaL14.setMenuVoList(new ArrayList<>());
		menuTaRoot.getMenuVoList().add(menuTaL14);
		
		return menuList;
	}
	
}
