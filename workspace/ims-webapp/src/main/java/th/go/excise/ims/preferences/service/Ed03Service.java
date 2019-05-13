package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty;
import th.go.excise.ims.preferences.persistence.repository.ExciseCtrlDutyRepository;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExciseCtrlDutyJdbcRepository;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Service
public class Ed03Service {
	@Autowired
	private ExciseCtrlDutyJdbcRepository exciseCtrlDutyJdbcRepository;
	
	@Autowired
	private ExciseCtrlDutyRepository exciseCtrlDutyRepository;
	
	
	public List<Ed03Vo> findByDutyGroupName(Ed03FormVo form) {
		return exciseCtrlDutyJdbcRepository.findByDutyGroupName(form);
	}
	
	public void saveExciseCtrlDuty(Ed03FormVo form) {
		ExciseCtrlDuty data = new ExciseCtrlDuty();
		data.getId().setDutyGroupCode(form.getDutyGroupCode());
		data.getId().setDutyGroupName(form.getDutyGroupName());
		data.getId().setResOffcode(form.getResOffcode());
		exciseCtrlDutyRepository.save(data);
	}
}
