package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Service
public class Int120401Service {

	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;
	
	public List<IaExpenses> findByYearByCoa(Int120401FormVo form) {
		List<IaExpenses> data = new ArrayList<>();
		data = iaExpensesJdbcRepository.findByYearByCoa(form);
		return data;
	}
}
