package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ta.persistence.entity.ReceiveRmatWsDetail;
import th.co.baiwa.excise.ta.persistence.entity.ReceiveRmatWsHeader;
import th.co.baiwa.excise.ta.persistence.repository.ReceiveRmatWsDetailRepository;
import th.co.baiwa.excise.ta.persistence.repository.ReceiveRmatWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class ReceiveRmatWsService {
	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsService.class);

	@Autowired
	private ReceiveRmatWsDetailRepository receiveRmatWsDetailRepository;

	@Autowired
	private ReceiveRmatWsHeaderRepository receiveRmatWsHeaderRepository;

	public void insertReceiveRmatWsService(List<Ope041DataTable> allData) {
		logger.info("Save Ope041");
		ReceiveRmatWsHeader receiveRmatWsHeader = null;
		for (Ope041DataTable value : allData) {

			if (BeanUtils.isNotEmpty(value.getExciseId())) {
				ReceiveRmatWsHeader hd = new ReceiveRmatWsHeader();
				hd.setExciseId(value.getExciseId());
				hd.setTaAnalysisId(value.getAnalysNumber());
				hd.setStartDate(value.getStartDate());
				hd.setEndDate(value.getEndDate());
				receiveRmatWsHeader = receiveRmatWsHeaderRepository.save(hd);
			}

			if (BeanUtils.isEmpty(value.getExciseId())) {
				ReceiveRmatWsDetail dtl = new ReceiveRmatWsDetail();
				dtl.setTaReceiveRmatHeaderId(receiveRmatWsHeader.getTaReceiveRmatHeaderId());
				dtl.setReceiveRmatDetailNo(value.getNo());
				dtl.setReceiveRmatDetailOrder(value.getProduct());
				dtl.setPurchaseTaxInv(value.getTaxInvoice());
				dtl.setDayBook(value.getDayRecieve());
				dtl.setMonthBook(value.getMonthRecieve());
				dtl.setExternalData(value.getExd1());
				dtl.setMaxValues(value.getCalMax());
				dtl.setResult(value.getDiff());
				receiveRmatWsDetailRepository.save(dtl);
			}
		}
	}

}
