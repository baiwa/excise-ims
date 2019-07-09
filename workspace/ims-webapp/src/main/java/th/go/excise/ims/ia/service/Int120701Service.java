package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaRentHouseJdbcRepository;
import th.go.excise.ims.ia.vo.Int120701FilterVo;
import th.go.excise.ims.ia.vo.Int120701Type6006Vo;

@Service
public class Int120701Service {

	@Autowired
	private IaRentHouseJdbcRepository iaRentHouseJdbcRepository;

	public List<Int120701Type6006Vo> filterByDate(Int120701FilterVo dataFilter) {
		List<Int120701Type6006Vo> dataRes = iaRentHouseJdbcRepository.filterByDate(dataFilter);
		return dataRes;
	}
}
