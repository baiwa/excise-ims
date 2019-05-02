package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaCheckControlRegis;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int090303JdbcRepository;
import th.go.excise.ims.ia.vo.Int0900303FormVo;

@Service
public class Int090303Service {

	@Autowired
	Int090303JdbcRepository int090303JdbcRepository;
	
	public List<IaCheckControlRegis> list(Int0900303FormVo form) {
		List<IaCheckControlRegis> iaCheckControlRegis = new ArrayList<IaCheckControlRegis>();
		iaCheckControlRegis = int090303JdbcRepository.list(form);
		return iaCheckControlRegis;
	}

	public List<Int0900303FormVo> budgetTypeDropdown() {
		List<Int0900303FormVo> response = int090303JdbcRepository.budgetTypeDropdown();
		return response;	
	}
}
