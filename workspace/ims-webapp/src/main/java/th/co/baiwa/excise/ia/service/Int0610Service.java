package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaWithdrawalDao;
import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalListRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0610FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0610Vo;


@Service
public class Int0610Service {
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private IaWithdrawalDao iaWithdrawalDao;
	
	@Autowired
	private IaWithdrawalListRepository iaWithdrawalListRepository;

	public List<Lov> activity() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("ACTIVITY");
	}

	public List<Lov> budge() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("BUDGET_TYPE");
	}

	public DataTableAjax<Int0610Vo> findAll(Int0610FormVo formVo) {
		// TODO Auto-generated method stub
		DataTableAjax<Int0610Vo> dataTableAjax = new DataTableAjax<>();
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			
			String officeCode = mappingOfficeCode(formVo);
			formVo.setOfficeCode(officeCode);
			formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
			formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int0610Vo> list = iaWithdrawalDao.findAll(formVo);
			Long count = iaWithdrawalDao.counwith(formVo);			
			
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public List<Lov> sector() {
		List<Lov> lov = lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("SECTOR_VALUE");
		return lov;
	}

	public List<Lov> area(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }
	
	public List<Lov> branch(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }

	public String mappingOfficeCode(Int0610FormVo formVo) {
		Lov sectors = new Lov();
		Lov areas = new Lov();
		Lov branchs = new Lov();
		
		if (StringUtils.isNotBlank(formVo.getSecter())) {
			sectors = lovRepository.findByLovId(Long.valueOf(formVo.getSecter()));
			
			if (StringUtils.isNotBlank(formVo.getArea())) {
				areas = lovRepository.findByLovId(Long.valueOf(formVo.getArea()));
				
				if (StringUtils.isNotBlank(formVo.getBranch())) {
					branchs = lovRepository.findByLovId(Long.valueOf(formVo.getBranch()));
				}
			}
		}
		
		StringBuilder officeCode = new StringBuilder("");
		
		if (sectors != null) {
			if(StringUtils.isNoneBlank(sectors.getSubType())) {
				officeCode.append(sectors.getSubType());
			}
		}
		if (areas != null) {
			if(StringUtils.isNoneBlank(areas.getSubType())) {
				officeCode.append(areas.getSubType());
			}
		}
		if (branchs != null) {
			if(StringUtils.isNoneBlank(branchs.getSubType())) {
				officeCode.append(branchs.getSubType());
			}
		}
				
		return officeCode.toString();
	}
	
//	@Transactional
//	public void save(Int0610FormVo formVo) {
//
//		Int0610Vo form = formVo.getData();
//		IaWithdrawalList entity = iaWithdrawalListRepository.findOne(Long.valueOf(form.getWithdrawalid()));
//
//		/* officeCode */
//		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
//		entity.setOfficeCode(officeCode);
//		Lov lov = lovRepository.findBySubType(officeCode);
//		entity.setOFFICEDesc(lov.getSubTypeDescription());
//	
//		entity.setDateOfPay(DateConstant.convertStrDDMMYYYYToDate(form.getDateOfPay()));
//		entity.setStatus(form.getStatus());
//		entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
//		entity.setDateWithdrawStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateWithdrawStamp()));
//		entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
//		entity.setDateDeliverStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateDeliverStamp()));
//		entity.setFivePartNumber(form.getFivePartNumber());
//		entity.setFivePartDate(DateConstant.convertStrDDMMYYYYToDate(form.getFivePartDate()));
//		entity.setStampCheckDate(DateConstant.convertStrDDMMYYYYToDate(form.getStampCheckDate()));
//		entity.setStampChecker(form.getStampChecker());
//		entity.setStampType(form.getStampType());
//		entity.setStampBrand(form.getStampBrand());
//		entity.setNumberOfBook(form.getNumberOfBook());
//		entity.setNumberOfStamp(form.getNumberOfStamp());
//		entity.setValueOfStampPrinted(form.getValueOfStampPrinted());
//		entity.setSumOfValue(form.getSumOfValue());
//		entity.setTaxStamp(form.getTaxStamp());
//		entity.setStampCodeStart(form.getStampCodeStart());
//		entity.setStampCodeEnd(form.getStampCodeEnd());
//		entity.setNote(form.getNote());
//		entity.setCreatedDate(DateConstant.convertStrDDMMYYYYToDate(form.getCreatedDate()));
//		iaStamDetailRepository.save(entity);
//	}
//
//	public void delete(Int0610FormVo formvo) {
//
//		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(formvo.getData().getWorkSheetDetailId()));
//		iaStamDetailRepository.delete(entity);
//	}


}
