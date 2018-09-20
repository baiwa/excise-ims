package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.IaProcurementUlFile;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;

@Service
public class Int0541UploadService {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.datasource.path.upload}")
	private String pathed;
	
	public boolean saveFileUpload(IaProcurementUlFile upload, Int0541Vo vo) {
//		logger.info("Int09 SaveFileUpload idProcess: {}, files: {}", idProcess, files);

		File f = new File(pathed + "Document_IaProcurement/" + "ProcurementId_" + upload.getProcurementId() + "/"); // initial file (folder)
        if (!f.exists()) { // check folder exists
            if (f.mkdirs()) {
            	logger.info("Directory is created!");
                // System.out.println("Directory is created!");
            } else {
            	logger.error("Failed to create directory!");
                // System.out.println("Failed to create directory!");
            }
        }
        try {
    		byte[] data = vo.getFile().getBytes(); // get data
    		// set path
    		String path = pathed + "Document_IaProcurement/" + "ProcurementId_" + upload.getProcurementId() + "/" + upload.getNameFile();
           @SuppressWarnings("resource")
		OutputStream stream = new FileOutputStream(path);
    		    stream.write(data);
    		   
    		    logger.info("Created file: " + path);
        		// System.out.println("Created file: " + path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        
		return true;
	}

}
