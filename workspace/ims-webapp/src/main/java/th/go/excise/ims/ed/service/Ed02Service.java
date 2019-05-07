package th.go.excise.ims.ed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ed.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.ed.vo.Ed02Vo;

@Service
public class Ed02Service {
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	public List<Ed02Vo> listUser() {
		return excisePersonJdbcRepository.listUser();
	}
	


}
