package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Service
public class Int0501Service {
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	public List<Int0501Vo> listPerson(Int0501FormVo form) {
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		personList = excisePersonJdbcRepository.listPerson(form);
		return personList;
	}
	
	
	

}
