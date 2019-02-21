package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;

@Service
public class TaWorksheetHdrService {
	private final Logger logger = LoggerFactory.getLogger(TaWorksheetHdrService.class);
	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	
	public List<TaWorksheetHdr> findTaWorksheetHdrBySubConditionRegCapital(BigDecimal from , BigDecimal to){
		logger.info("findTaWorksheetHdrBySubConditionRegCapital from {} to {} " ,from , to);
		try {
			return taWorksheetHdrRepository.findSubConditionRegCapital(from, to);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return new ArrayList<TaWorksheetHdr>();
	}
}
