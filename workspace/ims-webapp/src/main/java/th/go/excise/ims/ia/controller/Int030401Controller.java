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
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.service.Int030401Service;
import th.go.excise.ims.ia.vo.Int030102FormVo;

@Controller
@RequestMapping("/api/ia/int03/04/01")
public class Int030401Controller {
	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);

	@Autowired
	private Int030401Service int030401Service;

	@PostMapping("/factorsDataList")
	@ResponseBody
	public DataTableAjax<IaRiskFactorsData> factorsDataList(@RequestBody IaRiskFactorsData form) {
		DataTableAjax<IaRiskFactorsData> response = new DataTableAjax<IaRiskFactorsData>();

		try {
			List<IaRiskFactorsData> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsData>();
			iaRiskFactorsMasterList = int030401Service.factorsDataList(form);
			response.setData(iaRiskFactorsMasterList);
			
		} catch (Exception e) {
			logger.error("Int030102Controller factorsDataList : ", e);
		}
		return response;
	}

}
