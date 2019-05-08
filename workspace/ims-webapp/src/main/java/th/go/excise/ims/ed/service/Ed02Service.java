package th.go.excise.ims.ed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ed.persistence.repository.jdbc.ExcisePersonJdbcRepository;
import th.go.excise.ims.ed.persistence.repository.jdbc.ExcisePositionJdbcRepository;
import th.go.excise.ims.ed.vo.Ed01Vo;
import th.go.excise.ims.ed.vo.Ed02DepartmentVo;
import th.go.excise.ims.ed.vo.Ed02PositionVo;
import th.go.excise.ims.ed.vo.Ed02Vo;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int0401Vo;

@Service
public class Ed02Service {
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	@Autowired
	private ExcisePositionJdbcRepository excisePositionJdbcRepository;
	
	public List<Ed02Vo> listUser() {
		List<Ed02Vo> lists = new ArrayList<>();
		lists = excisePersonJdbcRepository.listUser();	
		for (Ed02Vo ed02Vo : lists) {
			if(ed02Vo.getEdOffcode() != null) {
				ExciseDepartmentVo exciseDepartmentVo = ExciseDepartmentUtil.getExciseDepartment(ed02Vo.getEdOffcode());
				ed02Vo.setExciseDepartmentVo(exciseDepartmentVo);
			}
		}
		return lists;
	}
	
	public List<Ed02PositionVo> listPosition() {
		List<Ed02PositionVo> dataList = excisePositionJdbcRepository.listPosition();
		return dataList;
	}
	
	public List<Ed02DepartmentVo> listDepartment00() {
		List<Ed02DepartmentVo> dataList = excisePositionJdbcRepository.listDepartment00();
		return dataList;
	}
	
	public List<Ed02DepartmentVo> listDepartment01() {
		List<Ed02DepartmentVo> dataList = excisePositionJdbcRepository.listDepartment01();
		return dataList;
	}
	
	
	
}
