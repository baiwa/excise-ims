package th.co.baiwa.excise.cop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.ReportCheckOperationDao;
import th.co.baiwa.excise.ta.persistence.entity.DisbRmatWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.DisbRmatWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.MaterialsWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.repository.DisbRmatWsDetailRepository;
import th.co.baiwa.excise.ta.persistence.repository.DisbRmatWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.repository.MaterialsWsDetailRepository;
import th.co.baiwa.excise.ta.persistence.repository.MaterialsWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;
import th.co.baiwa.excise.ta.persistence.vo.Ope043DataTable;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Cop0612Service {
	private Logger logger = LoggerFactory.getLogger(Cop0612Service.class);
	
	@Autowired
	private ReportCheckOperationDao reportCheckOperationDao;
	
	@Autowired
	private MaterialsWsHeaderRepository materialsWsHeaderRepository;
	
	@Autowired
	private MaterialsWsDetailRepository materialsWsDetailRepository;
	
	public void cop0612Service(List<Ope043DataTable> allData) {
			logger.info("Save Ope043");
			Long hdr = 0L;
			MaterialsWorksheetHeader materialsWorksheetHeader = null;
			MaterialsWorksheetDetail dtl = new MaterialsWorksheetDetail();
			MaterialsWorksheetHeader hd = new MaterialsWorksheetHeader();
			for (Ope043DataTable value : allData) {

				if (BeanUtils.isNotEmpty(value.getExciseId())) {
					hd = new MaterialsWorksheetHeader();
					hd.setExciseId(value.getExciseId());
					hd.setTaAnalysisId(value.getAnalysNumber());
					hd.setStartDate(value.getStartDate());
					hd.setEndDate(value.getEndDate());
					materialsWorksheetHeader = materialsWsHeaderRepository.save(hd);
					hdr = materialsWorksheetHeader.getTaMaterialsWsHeaderId();
				}

				if (BeanUtils.isEmpty(value.getExciseId())) {
					dtl = new MaterialsWorksheetDetail();
					dtl.setTaMaterialsWsHeaderId(hdr);
					dtl.setMaterialsWsDtlNo(value.getNo());
					dtl.setMaterialsWsDtlOrder(value.getOrder());
					dtl.setMaterialsWsDtlBalance(value.getEx1());
					dtl.setMaterialsWsDtlStorage(value.getEx2());
					dtl.setMaterialsWsDtlCounting(value.getCounting());
					dtl.setResult(value.getResult1());
					dtl.setResult1(value.getResult2());
					materialsWsDetailRepository.save(dtl);
				}
			}
		
	}

}
