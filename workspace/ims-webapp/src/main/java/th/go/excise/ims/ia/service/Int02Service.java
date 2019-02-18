package th.go.excise.ims.ia.service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {

	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;

	public List<Int02Vo> filterQtnHdr(Int02FormVo request) {
		List<Int02Vo> data = iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int02Vo obj : data) {
			DateTimeFormatter d = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT, Locale.US);
			obj.getCreatedDate().format(d);
			
			if(obj.getCreatedDate() != null) {
				obj.setCreatedDateStr(obj.getCreatedDate().format(d));
			}
			if(obj.getUpdatedDate() != null) {
				obj.setUpdatedDateStr(obj.getUpdatedDate().format(d));
			}
		}
		
		return data;
	}

}
