package th.go.excise.ims.preferences.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.preferences.service.BudgetYearService;

@RestController
@RequestMapping("/api/perterences/budget-year")
public class BudgetYearController {

	@Autowired
	private BudgetYearService budgetYearService;

	public ResponseData<String> BudgetYear() {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			responseData.setData(budgetYearService.getCurrentBudgetYear());
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
