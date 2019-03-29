package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.service.Int0606Service;
import th.go.excise.ims.ia.vo.Int0606FormVo;

@Controller
@RequestMapping("/api/ia/int06/06")
public class Int0606Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0606Controller.class);

	@Autowired
	Int0606Service int0606Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaCheckLicense> list(@RequestBody Int0606FormVo form) {
		DataTableAjax<IaCheckLicense> response = new DataTableAjax<IaCheckLicense>();
		List<IaCheckLicense> iaCheckLicenseList = new ArrayList<IaCheckLicense>();
		try {	
			iaCheckLicenseList = int0606Service.list(form);
			response.setData(iaCheckLicenseList);
		} catch (Exception e) {
			logger.error("Int070101Controller : " , e);
		}
		return response;
	}
	
	

}
