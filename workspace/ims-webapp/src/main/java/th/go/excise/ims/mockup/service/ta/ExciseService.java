package th.go.excise.ims.mockup.service.ta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.mockup.domain.ta.ExciseFile;
import th.go.excise.ims.mockup.persistence.dao.ta.ExciseFileUploadDao;
import th.go.excise.ims.mockup.persistence.entity.ta.ExciseFileUpload;

@Service
public class ExciseService {
	
	@Autowired
	ExciseFileUploadDao exciseFileUploadDao;
	
	@Value("${app.datasource.path}")
	private String pathed;
	
	public boolean saveExciseFileUpload(String exciseId, ExciseFile[] file) {
		File f = new File(pathed + exciseId); // initial file (folder)
        if (!f.exists()) { // check folder exists
            if (f.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        for(ExciseFile fi: file) {
    		Date in = new Date(); // current date
        	String ext =  FilenameUtils.getExtension(fi.getType()); // get extension
    		byte[] data = Base64.getDecoder().decode(fi.getValue().split(",")[1]); // get data from base64
    		String path = pathed + exciseId + "/" + fi.getName() + in.getTime() + "." + ext; // set path
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
        		System.out.println("Created file: " + path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
		return true;
	}
}
