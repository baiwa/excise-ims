package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;

@Service
public class Int090101Service {
	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;
	
	public List<Int090101Vo> findCompare(Int090101CompareFormVo form) {
		List<Int090101Vo> data = new ArrayList<Int090101Vo>();
		data = iaExpensesJdbcRepository.findCompare(form);
		return data;
	}
}
