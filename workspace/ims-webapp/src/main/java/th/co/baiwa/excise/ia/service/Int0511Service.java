package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckStampAreaDao;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.entity.IaStampFile;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStampFileRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.upload.service.ExcalService;

@Service
public class Int0511Service {

	
	@Autowired
	private ExcalService excalService;
	
	@Autowired
	private CheckStampAreaDao checkStampAreaDao;

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;
	
	@Autowired
	private IaStampFileRepository iaStampFileRepository;
	
	@Autowired
	private LovRepository lovRepository;

	public DataTableAjax<Int0511Vo> findAll(Int0511FormVo formVo) {
	    
		DataTableAjax<Int0511Vo> dataTableAjax = new DataTableAjax<>();		
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			
			formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
		    formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int0511Vo> list = checkStampAreaDao.findAll(formVo);
			Long count = checkStampAreaDao.count(formVo);
			
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<LabelValueBean> sector() {
		return checkStampAreaDao.sector();
	}

	public List<LabelValueBean> area(String id) {
		return checkStampAreaDao.area(id);
	}

	@Transactional
	public void save(Int0511FormVo formVo) {
			
		Int0511Vo form = formVo.getData();		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(form.getWorkSheetDetailId()));
		
		/*officeCode*/
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
		entity.setOfficeCode(officeCode);
		Lov lov  = lovRepository.findBySubType(officeCode);
		entity.setOfficeDesc(lov.getSubTypeDescription());
		
		entity.setDateOfPay(DateConstant.convertStrDDMMYYYYToDate(form.getDateOfPay()));
		entity.setStatus(form.getStatus());
		
		entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
		entity.setDateWithdrawStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateWithdrawStamp()));
		entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
		entity.setDateDeliverStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateDeliverStamp()));
		entity.setFivePartNumber(form.getFivePartNumber());
		entity.setFivePartDate(DateConstant.convertStrDDMMYYYYToDate(form.getFivePartDate()));
		entity.setStampCheckDate(DateConstant.convertStrDDMMYYYYToDate(form.getStampCheckDate()));
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
		entity.setCreatedDate(DateConstant.convertStrDDMMYYYYToDate(form.getCreatedDate()));
		entity.setFileName(form.getFileName());
		iaStamDetailRepository.save(entity);		
	}
	
	public void delete(Int0511FormVo formvo) {
		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(formvo.getData().getWorkSheetDetailId()));
		iaStamDetailRepository.delete(entity);
	}
	
	public List<String> listFile(String id){		
		List<String> fileName = new ArrayList<>();
		List<IaStampFile> list = iaStampFileRepository.findByDetailId(id);
		for (IaStampFile iaStampFile : list) {
			fileName.add(iaStampFile.getFileName());
		}
		return fileName;
	}
	
	public String mappingOfficeCode(Int0511FormVo formVo) {
		Lov sectors = new Lov();
		Lov areas = new Lov();
		Lov branchs = new Lov();

		if (StringUtils.isNotBlank(formVo.getSector())) {
			sectors = lovRepository.findByLovId(Long.valueOf(formVo.getSector()));

			if (StringUtils.isNotBlank(formVo.getArea())) {
				areas = lovRepository.findByLovId(Long.valueOf(formVo.getArea()));

				if (StringUtils.isNotBlank(formVo.getBranch())) {
					branchs = lovRepository.findByLovId(Long.valueOf(formVo.getBranch()));
				}
			}
		}

		StringBuilder officeCode = new StringBuilder("");

		if (sectors != null) {
			if (StringUtils.isNoneBlank(sectors.getSubType())) {
				officeCode.append(sectors.getSubType());
			}
		}
		if (areas != null) {
			if (StringUtils.isNoneBlank(areas.getSubType())) {
				officeCode.append(areas.getSubType());
			}
		}
		if (branchs != null) {
			if (StringUtils.isNoneBlank(branchs.getSubType())) {
				officeCode.append(branchs.getSubType());
			}
		}

		return officeCode.toString();
	}
	
	
public void exportFile(Int0511FormVo formVo, HttpServletResponse response) throws IOException {
		
		/* create spreadsheet */
		XSSFWorkbook workbook = excalService.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		System.out.println("Creating excel");
		
		String[] columns = {"ลำดับ", "วันที่ การรับ-จ่าย-ส่งคืน	", "สถานะ การรับ-จ่าย ", "หน่วยงาน/ผู้ประกอบการที่รับ-จ่ายแสตมป์", "หนังสือขอเบิกแสตมป์", "หนังสือส่งแสตมป์","เลขที่ใบ 5 ตอน","ลงวันวันที่","วันที่ตรวจนับ","ผู้ตรวจนับ (1)","ผู้ตรวจนับ (2)","ผู้ตรวจนับ (3)","ชนิดแสตมป์/ขนาดบรรจุ","จำนวน (เล่ม)","จำนวน (ดวง)","มูลค่าที่พิมพ์ (บาทต่อดวง)","รวมค่าพิมพ์ (บาท)","ค่าภาษี (บาท)	รหัสแสตมป์	หมายเหตุ	จัดการ\r\n" + 
				"เลขที่หนังสือ	ลงวันที่	เลขที่หนังสือ	ลงวันที่	"};
		
		for (cellNum = 0; cellNum < columns.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(columns[cellNum]);
			cell.setCellStyle(excalService.thStyle);
		}
		
	
		formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
		formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
		List<Int0511Vo> list = checkStampAreaDao.exportFile(formVo);

		rowNum = 2;
		cellNum = 0;
		int no = 1;
		
        // Create cells
        for(Int0511Vo item : list) {
        	row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(excalService.cellCenter);
			cellNum++;
			
		
			no++;
			rowNum++;
			cellNum = 0;
        }
        
        for (int i = 0; i < columns.length; i++) {
        	   sheet.autoSizeColumn(i);
        	  }

		/*set	fileName*/		
		String fileName ="Checklist";
		System.out.println(fileName);
		
		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		byte [] outArray = outByteStream.toByteArray();
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
		
		System.out.println("Done");

	}

}
