package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.service.Int061300Service;

@RequestMapping("api/ia/int061300")
public class Int061300Controller {

	@Autowired
	private Int061300Service int061300Service;

	@GetMapping("/findManagementType")
	@ResponseBody
	public List<Lov> findManagementType() {
		return int061300Service.findManagementType();
	}
}
