package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int05113Vo;

import java.util.List;

@Service
public class Int05113Service {

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;

	@Transactional
	public void save(List<Int05113Vo> formVos) {
        String user = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        for (Int05113Vo form:formVos) {

            IaStampDetail entity = new IaStampDetail();

            // set sector area and branch
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
	}
}
