package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302SaveDtlFormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1HRepository;

@Service
public class Int1302Service {
	@Autowired
	private WsPmPy1HRepository wsPmPy1HRepository;
	
	@Autowired
	private WsPmPy1DRepository wsPmPy1DRepository;
	
	public Int1302Vo list(Int1302FormVo form) {
		form.setArea("010000");
		Int1302Vo int1302Vo = new Int1302Vo();
		WsPmPy1H wsPmPy1H = wsPmPy1HRepository.findByOffCodeByFormYear(form.getArea(), form.getBudgetYear());
		List<WsPmPy1D> wsPmPy1DList = wsPmPy1DRepository.findByFormCodeByOffCode(wsPmPy1H.getFormCode(), form.getArea());
		int1302Vo.setWsPmPy1H(wsPmPy1H);
		int1302Vo.setWsPmPy1DList(wsPmPy1DList);
		return int1302Vo;
	}
	
	public void saveData(Int1302SaveDtlFormVo form) {
		
	}
}
