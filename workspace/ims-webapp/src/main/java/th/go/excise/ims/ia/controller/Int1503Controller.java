package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int1503Service;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;

@Controller
@RequestMapping("/api/ia/int15/03")
public class Int1503Controller {

	private Logger logger = LoggerFactory.getLogger(Int1503Controller.class);
	
	@Autowired
	private Int1503Service int1503Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<ExciseDepaccMas> listData() {
		DataTableAjax<ExciseDepaccMas> response = new DataTableAjax<ExciseDepaccMas>();
		List<ExciseDepaccMas> dataList = new ArrayList<ExciseDepaccMas>();
		try {
			dataList = int1503Service.listData();
			response.setData(dataList);
		} catch (Exception e) {
			logger.error("Int1503Controller : ", e);
		}
		return response;
	}
	
	
}
