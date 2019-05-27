package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.Int1304Vo;
import th.go.excise.ims.ia.vo.WsPmQtHVo;
import th.go.excise.ims.ws.persistence.repository.WsPmQtDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmQtHRepository;

@Service
public class Int1304Service {
	
	@Autowired
	private WsPmQtHRepository wsPmQtHRepository;
	
	@Autowired
	private WsPmQtDRepository wsPmQtDRepository;
	
	
	public Int1304Vo getWsQt(Int1304FormVo request) {
		Int1304Vo response = new Int1304Vo();
		
		/* find header */
		List<WsPmQtHVo> resHeader = wsPmQtHRepository.filterWsPmQt(request);
		for (WsPmQtHVo wsPmQtHVo : resHeader) {
			/* find and set data detail */
			wsPmQtHVo.setDetail(wsPmQtDRepository.filterWsPmQtD(wsPmQtHVo.getFormCode()));
		}
		response.setHeader(resHeader);
		return response;
	}

}
