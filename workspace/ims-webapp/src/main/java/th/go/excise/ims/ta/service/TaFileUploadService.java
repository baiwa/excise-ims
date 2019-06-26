package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFileUpload;
import th.go.excise.ims.ta.persistence.repository.TaFileUploadRepository;
import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

@Service
public class TaFileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(TaFileUploadService.class);

	@Value("${app.path.upload}")
	private String uploadPath;

	@Autowired
	private TaFileUploadSequenceService taFileUploadSequenceService;
	@Autowired
	private TaFileUploadRepository taFileUploadRepository;

	@Transactional(rollbackOn = {Exception.class})
	public String upload(FileUploadFormVo formVo) throws IOException {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = null;
		if (StringUtils.isNotEmpty(formVo.getBudgetYear())) {
			budgetYear = formVo.getBudgetYear();
		} else {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		String uploadFileNumber = taFileUploadSequenceService.getUploadNumber(officeCode, budgetYear);
		logger.info("upload moduleCode={}, refNo={}, fileName={}, uploadFileNumber={}",
			formVo.getModuleCode(), formVo.getRefNo(), formVo.getFile().getOriginalFilename(), uploadFileNumber);
		
		formVo.setUploadNo(uploadFileNumber);
		String fullUploadFilePath = writeUploadFile(formVo);
		
		TaFileUpload entity = new TaFileUpload();
		entity.setUploadNo(uploadFileNumber);
		entity.setModuleCode(formVo.getModuleCode());
		entity.setRefNo(formVo.getRefNo());
		entity.setFilePath(FilenameUtils.getPath(fullUploadFilePath));
		entity.setFileName(FilenameUtils.getName(fullUploadFilePath));
		taFileUploadRepository.save(entity);
		
		return uploadFileNumber;
	}
	
	private String writeUploadFile(FileUploadFormVo formVo) throws IOException {
		logger.info("writeUploadFile");
		final String taDic = "ta/";
		
		String orgFileName = formVo.getFile().getOriginalFilename();
		String fullUploadFilePath = uploadPath + taDic;
		String fullUploadFileName = fullUploadFilePath + formVo.getUploadNo() + FilenameUtils.getExtension(orgFileName);
		
		File fullUploadFileDic = new File(fullUploadFilePath);
		if (fullUploadFileDic.mkdirs()) {
			logger.info("Create Path={}", fullUploadFileDic.getAbsolutePath());
		}
		
		FileCopyUtils.copy(formVo.getFile().getInputStream(), new FileOutputStream(new File(fullUploadFileName)));
		
		return fullUploadFilePath;
	}

	public List<FileUploadVo> getUploadFileList(FileUploadFormVo formVo) {
		logger.info("getUploadFileList refNo={}", formVo.getRefNo());
		
		List<FileUploadVo> voList = null;
		
		return voList;
	}
	
	public void deleteUploadFile(FileUploadFormVo formVo) {
		logger.info("deleteUploadFile uploadNumber={}", formVo.getUploadNo());
		
	}
	
	public void getUploadFile(String uploadNumber) {
		logger.info("getUploadFile uploadNumber={}", uploadNumber);
	}

}
