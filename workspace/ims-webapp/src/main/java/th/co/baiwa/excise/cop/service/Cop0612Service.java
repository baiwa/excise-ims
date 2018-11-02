package th.co.baiwa.excise.cop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.ReportCheckOperationDao;
import th.co.baiwa.excise.cop.persistence.entity.OaMaterialsWorksheetDetail;
import th.co.baiwa.excise.cop.persistence.entity.OaMaterialsWorksheetHeader;
import th.co.baiwa.excise.cop.persistence.repository.OaMaterialsWsDetailRepository;
import th.co.baiwa.excise.cop.persistence.repository.OaMaterialsWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.vo.Ope043DataTable;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Cop0612Service {
	private Logger logger = LoggerFactory.getLogger(Cop0612Service.class);
	
	@Autowired
	private OaMaterialsWsHeaderRepository oaMaterialsWsHeaderRepository;
	
	@Autowired
	private OaMaterialsWsDetailRepository oaMaterialsWsDetailRepository;
	
	public void cop0612Service(List<Ope043DataTable> allData) {
			logger.info("Save Ope043");
			Long hdr = 0L;
			OaMaterialsWorksheetHeader oaMaterialsWorksheetHeader = null;
			OaMaterialsWorksheetDetail dtl = new OaMaterialsWorksheetDetail();
			OaMaterialsWorksheetHeader hd = new OaMaterialsWorksheetHeader();
			for (Ope043DataTable value : allData) {

				if (BeanUtils.isNotEmpty(value.getExciseId())) {
					hd = new OaMaterialsWorksheetHeader();
					hd.setExciseId(value.getExciseId());
					hd.setOaAnalysisId(value.getAnalysNumber());
					hd.setStartDate(value.getStartDate());
					hd.setEndDate(value.getEndDate());
					oaMaterialsWorksheetHeader = oaMaterialsWsHeaderRepository.save(hd);
					hdr = oaMaterialsWorksheetHeader.getOaMaterialsWsHeaderId();
				}

				if (BeanUtils.isEmpty(value.getExciseId())) {
					dtl = new OaMaterialsWorksheetDetail();
					dtl.setOaMaterialsWsHeaderId(hdr);
					dtl.setMaterialsWsDtlNo(value.getNo());
					dtl.setMaterialsWsDtlOrder(value.getOrder());
					dtl.setMaterialsWsDtlBalance(value.getEx1());
					dtl.setMaterialsWsDtlStorage(value.getEx2());
					dtl.setMaterialsWsDtlCounting(value.getCounting());
					dtl.setResult(value.getResult1());
					dtl.setResult1(value.getResult2());
					oaMaterialsWsDetailRepository.save(dtl);
				}
			}
		
	}

}
