package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.WithdrawFileUpload;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRepository;
import th.co.baiwa.excise.ia.persistence.repository.WithdrawFileUploadRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061101FormVo;

@Service
public class Int061101Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${app.datasource.path.upload}")
	private String pathed;

	@Autowired
	private RentHouseRepository rentHouseRepository;
	
	@Autowired
	private WithdrawFileUploadRepository withdrawFileUploadRepository;

	public RentHouse save(RentHouse en) {
		RentHouse data = new RentHouse();
		data.setAffiliation(en.getAffiliation());
		data.setBillAmount(en.getBillAmount());
		data.setName(en.getName());
		data.setNotOver(en.getNotOver());
		data.setPaymentCost(en.getPaymentCost());
		data.setPaymentFor(en.getPaymentFor());
		data.setPeriod(en.getPeriod());
		data.setPeriodWithdraw(en.getPeriodWithdraw());
		data.setPosition(en.getPosition());
		data.setReceipts(en.getReceipts());
		data.setRefReceipts(en.getRefReceipts());
		data.setRequestNo(en.getRequestNo());
		data.setSalary(en.getSalary());
		data.setTotalMonth(en.getTotalMonth());
		data.setTotalWithdraw(en.getTotalWithdraw());
		data.setStatus(ExciseConstants.IA.STATUS.PROCESS);

		return rentHouseRepository.save(data);
	}

	public void uploadList(Int061101FormVo uploadList) {
		if (uploadList.getFiles() != null) {
			MultipartFile[] files = uploadList.getFiles();
			for (MultipartFile file : files) {
				WithdrawFileUpload w = new WithdrawFileUpload();
				File f = new File(pathed + "Document_IaRentHouse/" + "rentHouseId_" + uploadList.getRentHouseId() + "/"); // initial																												// file
																															// (folder)
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
					byte[] data = file.getBytes(); // get data
					// set path
					String path = pathed + "Document_IaRentHouse/" + "rentHouseId_" + uploadList.getRentHouseId() + "/"
							+ file.getOriginalFilename();
					@SuppressWarnings("resource")
					OutputStream stream = new FileOutputStream(path);
					stream.write(data);
					stream.close();
					logger.info("Created file: " + path);
					// System.out.println("Created file: " + path);
					/**
					 * save
					 */
					w.setFilename(file.getOriginalFilename());
					w.setType(uploadList.getType());
					 w.setIdMaster(uploadList.getRentHouseId());
					w.setPathFiil(path);
					withdrawFileUploadRepository.save(w);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

}
