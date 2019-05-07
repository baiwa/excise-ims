package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaEmpWorkingDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Service
public class Int091201Service {
	@Autowired
	private IaEmpWorkingDtlJdbcRepository iaEmpWorkingDtlJdbcRepository;
	
	public List<Int091201Vo> getList(){
		List<Int091201Vo> dataRes = iaEmpWorkingDtlJdbcRepository.getList();
		return dataRes;
	}
}
