package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
		form.setStartYear(( Long.toString(Long.valueOf(form.getStartYear()) - 543)));
		form.setEndYear(( Long.toString(Long.valueOf(form.getEndYear()) - 543)));
		if(StringUtils.isNotBlank(form.getYear())) {			
			form.setYear(( Long.toString(Long.valueOf(form.getYear()) - 543)));
		}
		data = iaExpensesJdbcRepository.findCompare(form);
		return data;
	}
}
