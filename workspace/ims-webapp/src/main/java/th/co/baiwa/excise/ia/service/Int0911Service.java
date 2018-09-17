package th.co.baiwa.excise.ia.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911T2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;

@Service
public class Int0911Service {
	private static Logger log = LoggerFactory.getLogger(Int0911Service.class);
	
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;
	
	@Autowired
	private Int09UploadService int09UploadService;


	public DataTableAjax<Int0911Vo> findAll(Int0911FormVo formVo) {

		// query data
		List<Int0911Vo> list = iaTravelEstimatorDao.findAll0911(formVo);
		Long count = iaTravelEstimatorDao.count0911(formVo);

		// set data table
		DataTableAjax<Int0911Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public DataTableAjax<Int0911T2Vo> findAll2(Int0911FormVo formVo) {

		// query data
		List<Int0911T2Vo> list = iaTravelEstimatorDao.findAll0911T2(formVo);
		Long count = iaTravelEstimatorDao.count0911T2(formVo);

		// set data table
		DataTableAjax<Int0911T2Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public Int0911FormVo gethead(Int0911FormVo formVo) {
		Int0911FormVo data = new Int0911FormVo();
		data = iaTravelEstimatorDao.gethead(formVo);
		log.info("data : {}",data);
	return data;
}

	public void delete(Long id) {	
		iaTravelEstimatorDao.delete0911(id);
	}
	
	public void deleteT2(Long id) {
		iaTravelEstimatorDao.delete0911T2(id);	
	}
	public void approve(Int0911FormVo formVo) {	
		iaTravelEstimatorDao.approve0911(formVo.getId(),formVo.getApprove());
	}
	public void approveT2(Int0911FormVo formVo) {	
		iaTravelEstimatorDao.approve0911T2(formVo.getId(),formVo.getApprove());
	}
	public void upload(Int0911FormVo formVo) throws IOException {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentName(formVo.getFileUpload().getOriginalFilename());
		long s = formVo.getFileUpload().getSize();
		formVo.setDocumantSize(Long.toString(s/1000));
		
		String documentName = iaTravelEstimatorDao.addEvidence(formVo.getIdProcess(),formVo.getCreatedBy(),formVo.getDocumentName(),formVo.getDocumantSize());
		log.info(" Document Name : {}",documentName);
		log.info(" Documant Size : {}",formVo.getDocumantSize());
		int09UploadService.saveFileUpload(formVo.getIdProcess(),documentName,"Evidence",formVo.getFileUpload());
	}
	
	
}
