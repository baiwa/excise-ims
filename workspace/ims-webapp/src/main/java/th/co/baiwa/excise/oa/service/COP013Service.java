package th.co.baiwa.excise.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.oa.persistence.entity.OaOperPlan;
import th.co.baiwa.excise.oa.persistence.repository.OaOperPlanRepository;

@Service
public class COP013Service {
	
	@Autowired
	private OaOperPlanRepository oaOperPlanRepository;
	
//	public ResponseDataTable<OaOperPlan> findAllOaOperPlan(){
//		ResponseDataTable<OaOperPlan> data = new ResponseDataTable<>();
//		int count = (int) oaOperPlanRepository.count();
//		data.setData(oaOperPlanRepository.findAll());
//		data.setDraw(count);
//		data.setLength(count);
//		return data;
//	}
	
	public ResponseDataTable<OaOperPlan> findByCriteriaForDatatable(OaOperPlan oaOperPlan, DataTableRequest dataTableRequest){
		ResponseDataTable<OaOperPlan> data = new ResponseDataTable<>();
		int count = (int) oaOperPlanRepository.count();
		data.setData(this.findByPlanStart(oaOperPlan.getPlanStart()));
		data.setDraw(count);
		data.setLength(count);
		return data;
	}
	
	public List<OaOperPlan> findByPlanStart(String planStart){
		return oaOperPlanRepository.findByPlanStart(planStart);
	}

}
