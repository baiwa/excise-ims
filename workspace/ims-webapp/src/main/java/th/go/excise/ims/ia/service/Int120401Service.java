package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Service
public class Int120401Service {

	public List<IaExpenses> findByYearByCoa(Int120401FormVo form) {
		List<IaExpenses> data = new ArrayList<>();
		
		return data;
	}
}
