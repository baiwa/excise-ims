package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckStampAreaDao;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;

import java.util.List;

@Service
public class Int0511Service {

	@Autowired
	private CheckStampAreaDao checkStampAreaDao;

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;

	public DataTableAjax<Int0511Vo> findAll(Int0511FormVo formVo) {
	    formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
	    formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
		List<Int0511Vo> list = checkStampAreaDao.findAll(formVo);
		Long count = checkStampAreaDao.count(formVo);
		DataTableAjax<Int0511Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<LabelValueBean> sector() {
		return checkStampAreaDao.sector();
	}

	public List<LabelValueBean> area(String id) {
		return checkStampAreaDao.area(id);
	}

	@Transactional
	public void save(Int0511FormVo formVo) {
			
		Int0511Vo form = formVo.getData();		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(form.getWorkSheetDetailId()));
		//entity.setExciseDepartment(form.getExciseDepartment());
		//entity.setExciseRegion(form.getExciseRegion());
		//entity.setExciseDistrict(form.getExciseDistrict());
		entity.setDateOfPay(DateConstant.convertStrDD_MM_YYYYToDate(form.getDateOfPay()));
		entity.setStatus(form.getStatus());
		entity.setDepartmentName(form.getDepartmentName());
		entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
		entity.setDateWithdrawStamp(DateConstant.convertStrDD_MM_YYYYToDate(form.getDateWithdrawStamp()));
		entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
		entity.setDateDeliverStamp(DateConstant.convertStrDD_MM_YYYYToDate(form.getDateDeliverStamp()));
		entity.setFivePartNumber(form.getFivePartNumber());
		entity.setFivePartDate(DateConstant.convertStrDD_MM_YYYYToDate(form.getFivePartDate()));
		entity.setStampCheckDate(DateConstant.convertStrDD_MM_YYYYToDate(form.getStampCheckDate()));
		entity.setStampChecker(form.getStampChecker());
		entity.setStampType(form.getStampType());
		entity.setStampBrand(form.getStampBrand());
		entity.setNumberOfBook(form.getNumberOfBook());
		entity.setNumberOfStamp(form.getNumberOfStamp());
		entity.setValueOfStampPrinted(form.getValueOfStampPrinted());
		entity.setSumOfValue(form.getSumOfValue());
		entity.setSerialNumber(form.getSerialNumber());
		entity.setTaxStamp(form.getTaxStamp());
		entity.setStampCodeStart(form.getStampCodeStart());
		entity.setStampCodeEnd(form.getStampCodeEnd());
		entity.setNote(form.getNote());
		entity.setCreatedDate(DateConstant.convertStrDD_MM_YYYYToDate(form.getCreatedDate()));
		entity.setFileName(form.getFileName());
		iaStamDetailRepository.save(entity);		
	}
	
	public void delete(Int0511FormVo formvo) {
		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(formvo.getData().getWorkSheetDetailId()));
		iaStamDetailRepository.delete(entity);
	}
}
