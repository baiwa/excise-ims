package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckStampAreaDao;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.entity.IaStampFile;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStampFileRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;

@Service
public class Int0511Service {

	@Autowired
	private CheckStampAreaDao checkStampAreaDao;

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;
	
	@Autowired
	private IaStampFileRepository iaStampFileRepository;
	
	@Autowired
	private LovRepository lovRepository;

	public DataTableAjax<Int0511Vo> findAll(Int0511FormVo formVo) {
	    
		DataTableAjax<Int0511Vo> dataTableAjax = new DataTableAjax<>();		
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			
			formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
		    formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int0511Vo> list = checkStampAreaDao.findAll(formVo);
			Long count = checkStampAreaDao.count(formVo);
			
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
		
		/*officeCode*/
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
		entity.setOfficeCode(officeCode);
		Lov lov  = lovRepository.findBySubType(officeCode);
		entity.setOfficeDesc(lov.getSubTypeDescription());
		
		entity.setDateOfPay(DateConstant.convertStrDDMMYYYYToDate(form.getDateOfPay()));
		entity.setStatus(form.getStatus());
		
		entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
		entity.setDateWithdrawStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateWithdrawStamp()));
		entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
		entity.setDateDeliverStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateDeliverStamp()));
		entity.setFivePartNumber(form.getFivePartNumber());
		entity.setFivePartDate(DateConstant.convertStrDDMMYYYYToDate(form.getFivePartDate()));
		entity.setStampCheckDate(DateConstant.convertStrDDMMYYYYToDate(form.getStampCheckDate()));
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
		entity.setCreatedDate(DateConstant.convertStrDDMMYYYYToDate(form.getCreatedDate()));
		entity.setFileName(form.getFileName());
		iaStamDetailRepository.save(entity);		
	}
	
	public void delete(Int0511FormVo formvo) {
		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(formvo.getData().getWorkSheetDetailId()));
		iaStamDetailRepository.delete(entity);
	}
	
	public List<String> listFile(String id){		
		List<String> fileName = new ArrayList<>();
		List<IaStampFile> list = iaStampFileRepository.findByDetailId(id);
		for (IaStampFile iaStampFile : list) {
			fileName.add(iaStampFile.getFileName());
		}
		return fileName;
	}
}
