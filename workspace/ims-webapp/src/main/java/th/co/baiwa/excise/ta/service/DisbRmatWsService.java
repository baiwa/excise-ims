package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ta.persistence.entity.DisbRmatWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.DisbRmatWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.repository.DisbRmatWsDetailRepository;
import th.co.baiwa.excise.ta.persistence.repository.DisbRmatWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class DisbRmatWsService {
	private Logger logger = LoggerFactory.getLogger(DisbRmatWsService.class);
	
	@Autowired
	private DisbRmatWsDetailRepository disbRmatWorksheetDetailRepository;
	
	@Autowired
	private DisbRmatWsHeaderRepository disbRmatWsHeaderRepository;
	
	public void insertDisbRmatWsService(List<Ope041DataTable> allData) {
		logger.info("Save Ope042");
		Long hdr = 0L;
		DisbRmatWorksheetHeader disbRmatWsHeaderJpa = null;
		DisbRmatWorksheetDetail dtl = new DisbRmatWorksheetDetail();
		DisbRmatWorksheetHeader hd = new DisbRmatWorksheetHeader();
		for (Ope041DataTable value : allData) {

			if (BeanUtils.isNotEmpty(value.getExciseId())) {
				hd = new DisbRmatWorksheetHeader();
				hd.setExciseId(value.getExciseId());
				hd.setTaAnalysisId(value.getAnalysNumber());
				hd.setStartDate(value.getStartDate());
				hd.setEndDate(value.getEndDate());
				disbRmatWsHeaderJpa = disbRmatWsHeaderRepository.save(hd);
				hdr = disbRmatWsHeaderJpa.getTaDisburseRmatHeaderId();
			}

			if (BeanUtils.isEmpty(value.getExciseId())) {
				dtl = new DisbRmatWorksheetDetail();
				dtl.setTaDisburseRmatHeaderId(hdr);
				dtl.setDisburseRawMatDtlNo(value.getNo());
				dtl.setDisburseRawMatDtlOrder(value.getProduct());
				dtl.setRawMatRequisition(value.getTaxInvoice());
				dtl.setDayBook0701(value.getDayRecieve());
				dtl.setMonthBook0704(value.getMonthRecieve());
				dtl.setMonthlyReport(value.getExd1());
				dtl.setMaxValues(value.getCalMax());
				dtl.setResult(value.getDiff());
				disbRmatWorksheetDetailRepository.save(dtl);
			}
		}
	}

}
