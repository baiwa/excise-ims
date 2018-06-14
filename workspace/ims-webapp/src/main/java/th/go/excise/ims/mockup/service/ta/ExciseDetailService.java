package th.go.excise.ims.mockup.service.ta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.mockup.domain.ta.ExciseFile;
import th.go.excise.ims.mockup.persistence.dao.ExciseDetailDao;
import th.go.excise.ims.mockup.persistence.dao.ta.ExciseFileUploadDao;
import th.go.excise.ims.mockup.persistence.entity.ExciseDetail;
import th.go.excise.ims.mockup.persistence.entity.ExciseTax;
import th.go.excise.ims.mockup.persistence.entity.ta.ExciseFileUpload;

@Service
public class ExciseDetailService {
	
	@Value("${app.datasource.path}")
	private String pathed;
	
	@Autowired
	private ExciseDetailDao exciseDao;
	
	@Autowired
	private ExciseFileUploadDao exciseFileUploadDao;
	
	public List<ExciseDetail> findById(String id, int limit) {
		List<ExciseDetail> li = exciseDao.queryByExciseId(id, limit);
		Collection<ExciseDetail> list = li;
		List<ExciseDetail> listed = list.stream().filter(distinctByKey(p -> p.getExciseId()))
				.collect(Collectors.toList());
		for (ExciseDetail l : li) {
			for (ExciseDetail led : listed) {
				if (led.getExciseId().equals(l.getExciseId())) {
					ArrayList<ExciseTax> result = led.getExciseTax();
					if (l.getExciseTax().get(0).getExciseTaxReceiveId().equals(result.get(0).getExciseTaxReceiveId())) {
						continue;
					}
					result.add(l.getExciseTax().get(0));
					led.setExciseTax(result);
				}
			}
		}
		for (ExciseDetail led : listed) {
			List<ExciseFileUpload> file = exciseFileUploadDao.queryByExciseId(id);
			led.setFile(file);
		}
		return listed;
	}
	
	public boolean saveExciseFileUpload(String exciseId, ExciseFile[] files) {
		ArrayList<ExciseFile> file = new ArrayList<ExciseFile>();
		for(ExciseFile fs: files) {
			if (fs.getName() != null) {
				file.add(fs);
				System.out.println(fs.getName());
			}	
		}
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
        		System.out.println("Created file: " + path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
		return true;
	}
	
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> key) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
	}
}
