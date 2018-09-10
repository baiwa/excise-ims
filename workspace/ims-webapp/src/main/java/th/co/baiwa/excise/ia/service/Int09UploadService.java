package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;

@Service
public class Int09UploadService {
	private static Logger log = LoggerFactory.getLogger(Int09UploadService.class);
	
	@Value("${app.datasource.path.upload}")
	private String pathed;
	
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;
	
	

	public boolean saveFileUpload(Long idProcess,String documentName,String document, MultipartFile files) {
		log.info("Int09 SaveFileUpload idProcess: {}, files: {}", idProcess, files);

	
		File f = new File(pathed + "Document_Travel_Estimator/"+idProcess+ "/"+ document); // initial file (folder)
        if (!f.exists()) { // check folder exists
            if (f.mkdirs()) {
            	log.info("Directory is created!");
                // System.out.println("Directory is created!");
            } else {
            	log.error("Failed to create directory!");
                // System.out.println("Failed to create directory!");
            }
        }
        try {
    		byte[] data = files.getBytes(); // get data
    		// set path
    		String path = pathed + "Document_Travel_Estimator/"+idProcess+ "/"+ document + "/" +documentName;
           OutputStream stream = new FileOutputStream(path);
    		    stream.write(data);
    		   
        		log.info("Created file: " + path);
        		// System.out.println("Created file: " + path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        
		return true;
	}
	
	
	
}
