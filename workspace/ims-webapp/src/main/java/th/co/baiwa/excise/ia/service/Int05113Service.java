package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;
import th.co.baiwa.excise.ia.persistence.entity.IaStamType;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStamGenreRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStamTypeRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int05113Vo;
import th.co.baiwa.excise.ta.controller.ExciseDetailController;
import th.co.baiwa.excise.ta.persistence.entity.ExciseFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class Int05113Service {

    private Logger logger = LoggerFactory.getLogger(ExciseDetailController.class);

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;

	@Autowired
    private IaStamTypeRepository iaStamTypeRepository;

	@Autowired
    private IaStamGenreRepository iaStamGenreRepository;

    @Value("${app.datasource.path.upload}")
    private String pathed;

	@Transactional
	public void save(List<Int05113Vo> formVos) {
        String user = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        for (Int05113Vo form:formVos) {

            uploadFile("123",form.getFile());

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
          //  entity.setFileName(form.getFileName());

            iaStamDetailRepository.save(entity);
        }
	}

	public List<IaStamType> stamTypes(){
        return iaStamTypeRepository.findAll();
    }

    public List<IaStamGenre> stamGenres(String stamTypeId){
	    return iaStamGenreRepository.findByStampTypeId(stamTypeId);
    }

    public void uploadFile(String exciseId, ExciseFile[] files){

        ArrayList<ExciseFile> file = new ArrayList<>();
        for(ExciseFile fs: files) {
            if (fs.getName() != null) {
                file.add(fs);
            }
        }
        File f = new File(pathed + exciseId); // initial file (folder)
        if (!f.exists()) { // check folder exists
            if (f.mkdirs()) {
                logger.info("Directory is created!");
                // System.out.println("Directory is created!");
            } else {
                logger.error("Failed to create directory!");
                // System.out.println("Failed to create directory!");
            }
        }
    }

}
