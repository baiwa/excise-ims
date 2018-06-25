package th.co.baiwa.excise.service.ta;

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

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.ta.ExciseDetail;
import th.co.baiwa.excise.domain.ta.ExciseFile;
import th.co.baiwa.excise.entity.ta.ExciseFileUpload;
import th.co.baiwa.excise.persistence.dao.ExciseDetailDao;
import th.co.baiwa.excise.persistence.dao.ta.ExciseFileUploadDao;
import th.co.baiwa.excise.persistence.dao.ta.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.persistence.entity.ExciseTax;

@Service
public class ExciseDetailService {

	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);
	
	@Value("${app.datasource.path}")
	private String pathed;
	
	@Autowired
	private ExciseDetailDao exciseDao;
	
	@Autowired
	private ExciseFileUploadDao exciseFileUploadDao;
	
	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	public List<ExciseDetail> findById(String exciseId, String analysNum, int limit) {
		logger.info("ExciseDetailService.findById exciseId: {}, analysNum: {}, limit: {}", exciseId, analysNum, limit);
		List<ExciseDetail> li = exciseDao.queryByExciseId(exciseId, analysNum, limit);
		for (ExciseDetail led: li) {
			List<ExciseFileUpload> file = exciseFileUploadDao.queryByExciseId(exciseId);
			List<ExciseTax> tax = exciseDao.queryByTaxId(exciseId, limit);
			led.setExciseTax(tax);
			led.setFile(file);
		}
		return li;
	}
	
	public boolean saveExciseFileUpload(String exciseId, String analysNum, ExciseFile[] files) {
		logger.info("ExciseDetailService.saveExciseFileUpload exciseId: {}, files: {}", exciseId, files);
		ArrayList<ExciseFile> file = new ArrayList<ExciseFile>();
		for(ExciseFile fs: files) {
			if (fs.getName() != null) {
				file.add(fs);
				// System.out.println(fs.getName());
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
        for(ExciseFile fi: file) {
    		Date in = new Date(); // current date
        	String ext =  FilenameUtils.getExtension(fi.getType()); // get extension
    		byte[] data = Base64.getDecoder().decode(fi.getValue().split(",")[1]); // get data from base64
    		
    		// set path
    		String path = pathed + exciseId + "/" + fi.getName().toUpperCase() + '-';
    		path += new SimpleDateFormat("dd-MM-yyyy").format(in) + "." + ext;
    		
            try (OutputStream stream = new FileOutputStream(path)) {
    		    stream.write(data);
    		    ExciseFileUpload excise = new ExciseFileUpload();
        		excise.setExciseId(exciseId);
        		excise.setUploadPath(path);
        		excise.setCreatedBy(UserLoginUtils.getCurrentUsername());
        		excise.setUpdateBy(UserLoginUtils.getCurrentUsername());
        		excise.setCreateDate(in);
        		excise.setCreatedDatetime(in);
        		excise.setUpdateDatetime(in);
        		exciseFileUploadDao.insertExciseFileUpload(excise); // insert to database
        		planWorksheetHeaderDao.updatePlanWorksheetHeaderFlag("E", analysNum, exciseId);
        		logger.info("Created file: " + path);
        		// System.out.println("Created file: " + path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
		return true;
	}
	
}
