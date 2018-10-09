package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.IaStamGenre;
import th.co.baiwa.excise.ia.persistence.entity.IaStamType;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.entity.IaStampFile;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStamGenreRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStamTypeRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStampFileRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int051111Vo;
import th.co.baiwa.excise.ta.controller.ExciseDetailController;
import th.co.baiwa.excise.ta.persistence.entity.ExciseFile;

@Service
public class Int051111Service {
	private Logger logger = LoggerFactory.getLogger(ExciseDetailController.class);

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;

	@Autowired
    private IaStamTypeRepository iaStamTypeRepository;

	@Autowired
    private IaStamGenreRepository iaStamGenreRepository;
	
	@Autowired
	private IaStampFileRepository iaStampFileRepository;
	
	@Autowired
	private LovRepository lovRepository;
	
    @Value("${app.datasource.path.upload}")
    private String pathed;

	@Transactional
	public void save(List<Int051111Vo> formVos) {
        for (Int051111Vo form:formVos) {           
        	
        	/*upload file*/
        	uploadFile("FileUpload",form.getFile());
            IaStampDetail entity = new IaStampDetail();
            
            /*get sector*/
            String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();          
            entity.setOfficeCode(officeCode);        
            
            Lov lov = lovRepository.findBySubType(officeCode);
            if (lov != null) {
				entity.setOfficeDesc(lov.getSubTypeDescription());
			}
            /* set sector area and branch*/
            entity.setDateOfPay(DateConstant.convertStrDDMMYYYYToDate(form.getDateOfPay())); 
            entity.setDepartmentName(form.getDepartmentName());
            entity.setStatus(form.getStatus());            
            entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
            entity.setDateWithdrawStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateWithdrawStamp()));
            entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
            entity.setDateDeliverStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateDeliverStamp()));
            entity.setFivePartNumber(form.getFivePartNumber());
            entity.setFivePartDate(DateConstant.convertStrDDMMYYYYToDate(form.getFivePartDate()));
            entity.setStampCheckDate(DateConstant.convertStrDDMMYYYYToDate(form.getStampCheckDate()));
            entity.setStampChecker(form.getStampChecker());
            entity.setStampChecker2(form.getStampChecker2());
            entity.setStampChecker3(form.getStampChecker3());
            entity.setStampType(form.getStampType());
            entity.setStampBrand(form.getStampBrand());
            entity.setNumberOfBook(form.getNumberOfBook());
            entity.setNumberOfStamp(form.getNumberOfStamp());
            entity.setValueOfStampPrinted(form.getValueOfStampPrinted());
            entity.setSumOfValue(form.getSumOfValue());
            entity.setTaxStamp(form.getTaxStamp());
            entity.setStampCodeStart(form.getStampCodeStart());
            entity.setStampCodeEnd(form.getStampCodeEnd());
            entity.setNote(form.getNote());
            entity.setCreatedDate(DateConstant.convertStrDDMMYYYYToDate(form.getCreatedDate()));
            entity.setStampTypeId(form.getStampTypeId());
            entity.setStampBrandId(form.getStampBrandId());
            IaStampDetail detailId = iaStamDetailRepository.save(entity);
            
            /*insert table file*/
            List<IaStampFile> listFile = new ArrayList<>();
            if (form.getFile()!=null) {
            	for ( ExciseFile file : form.getFile()) {
                	IaStampFile fileEntity = new IaStampFile();
                	fileEntity.setDetailId(Long.toString(detailId.getWorkSheetDetailId()));
                	fileEntity.setFileName(file.getName());  
                	
                	listFile.add(fileEntity);
    			}
                iaStampFileRepository.save(listFile);
			}
            
        }
	}

	public List<IaStamType> stamTypes(){
        return iaStamTypeRepository.findAll();
    }

    public List<IaStamGenre> stamGenres(String stamTypeId){
	    return iaStamGenreRepository.findByStampTypeId(stamTypeId);
    }

    public void uploadFile(String exciseId, ExciseFile[] files){
    	if (files !=null) {
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
                } else {
                    logger.error("Failed to create directory!");
                }
            }
            
            for(ExciseFile fi: file) {
        		Date in = new Date(); // current date
            	String ext =  FilenameUtils.getExtension(fi.getType()); // get extension
        		byte[] data = Base64.getDecoder().decode(fi.getValue().split(",")[1]); // get data from base64
        		
        		// set path
        		String path = pathed + exciseId + "/" + fi.getName().toUpperCase() + '-';
        		path += new SimpleDateFormat("dd-MM-yyyy").format(in) + "." + ext;
        		
                try (OutputStream stream = new FileOutputStream(path)) {
        		    stream.write(data);
            		logger.info("Created file: " + path);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }
		}
        
    }
}
