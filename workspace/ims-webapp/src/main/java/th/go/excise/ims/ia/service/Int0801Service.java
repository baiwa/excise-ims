package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0801Tabs;
import th.go.excise.ims.ia.vo.Int0801Vo;
import th.go.excise.ims.ia.vo.SearchVo;

@Service
public class Int0801Service {
	
	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;
	
	public List<Int0801Tabs> search(SearchVo request) {
		List<Int0801Tabs> tabs = new ArrayList<Int0801Tabs>();
		List<Int0801Vo> resTable = null;
		Int0801Tabs table = null;
		
		request.setPeriodYear(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
		for (int i = 1; i <= 5; i++) {
			resTable = new ArrayList<Int0801Vo>();
			table = new Int0801Tabs();
			request.setFlag(Integer.toString(i));
			resTable = iaGftrialBalanceRepository.findDataAccByRequest(request);
			
			table.setTable(resTable);
			tabs.add(table);
		}
		return tabs;
	}
}
