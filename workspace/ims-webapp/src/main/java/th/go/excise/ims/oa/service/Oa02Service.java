package th.go.excise.ims.oa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.vo.Oa02Vo;

@Service
public class Oa02Service {
	
	@Autowired
	private OaPlanRepository oaPlanRepo;

	public Oa02Vo findOaPlanByYear(String fiscolYear) {
		Oa02Vo plan = new Oa02Vo();
		List<OaPlan> listPlan  = new ArrayList<>();
		listPlan = oaPlanRepo.findByfiscolYearAndIsDeleted(fiscolYear, "N");
		plan.setListPlan(listPlan);
		return plan;
	}
}
