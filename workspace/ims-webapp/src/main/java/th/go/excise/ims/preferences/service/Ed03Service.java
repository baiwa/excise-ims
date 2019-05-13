package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty;
import th.go.excise.ims.preferences.persistence.repository.ExciseCtrlDutyRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExciseCtrlDutyJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed0101DepartmentVo;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Service
public class Ed03Service {
	@Autowired
	private ExciseCtrlDutyJdbcRepository exciseCtrlDutyJdbcRepository;

	@Autowired
	private ExciseCtrlDutyRepository exciseCtrlDutyRepository;

	public List<Ed03Vo> findByDutyGroupName(Ed03FormVo form) {
		List<Ed03Vo> resList = exciseCtrlDutyJdbcRepository.findByDutyGroupName(form);
		ExciseDepartmentVo exciseDepartmentVo = null;
		for (Ed03Vo ed03Vo : resList) {
			if (ed03Vo.getResOffcode() != null) {
				exciseDepartmentVo = ExciseDepartmentUtil.getExciseDepartment(ed03Vo.getResOffcode());
				ed03Vo.setExciseDepartmentVo(exciseDepartmentVo);
			}
		}
		return resList;
	}

	public boolean saveExciseCtrlDuty(Ed03FormVo form) {
		boolean check = exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form);
		if (check) {
			ExciseCtrlDuty data = new ExciseCtrlDuty();
			data.setDutyGroupCode(form.getDutyGroupCode());
			data.setDutyGroupName(form.getDutyGroupName());
			data.setResOffcode(form.getResOffcode());
			exciseCtrlDutyRepository.save(data);
		}
		return check;

	}

	public boolean editExciseCtrlDuty(Long id, Ed03FormVo form) {
		boolean check = exciseCtrlDutyJdbcRepository.checkByDutyGroupName(form);
		if (check) {
			ExciseCtrlDuty data = exciseCtrlDutyRepository.findById(id).get();
			data.setDutyGroupCode(form.getDutyGroupCode());
			data.setDutyGroupName(form.getDutyGroupName());
			data.setResOffcode(form.getResOffcode());
			exciseCtrlDutyRepository.save(data);
		}
		return check;
	}

	public void deleteExciseCtrlDuty(Long id) {
		exciseCtrlDutyRepository.deleteById(id);
	}

	public List<Ed0101DepartmentVo> listDepartment0014() {
		List<Ed0101DepartmentVo> dataList = exciseCtrlDutyJdbcRepository.listDepartment0014();
		return dataList;
	}
}
