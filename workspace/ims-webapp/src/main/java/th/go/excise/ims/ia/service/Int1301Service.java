package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.Int1301Vo;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessHRepository;

@Service
public class Int1301Service {
	
	@Autowired 
	private WsPmAssessDRepository wsPmAssessDRepository;
	
	@Autowired
	private WsPmAssessHRepository wsPmAssessHRepository;
	
	public Int1301Vo getWsPaAssess(Int1301Filter request) {
		Int1301Vo response = new Int1301Vo();
		
		/* find header */
		List<WsPmAssessHVo> resHeader = wsPmAssessHRepository.filterWsPaAssess(request);
		for (WsPmAssessHVo header : resHeader) {
			/* find and set data detail */
			header.setDetail(wsPmAssessDRepository.findByFormCodeAndIsDeleted(header.getFormCode(), "N"));
		}
		response.setHeader(resHeader);
		return response;
	}

}
