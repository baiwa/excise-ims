package th.co.baiwa.excise.upload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.form.FormUpload;
import th.co.baiwa.excise.ta.service.ExciseDetailService;

@Service
public class UploadFileExciseService {

	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);

	public List<Object> readFileExcel(FormUpload multipartFile)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		logger.info("UploadFileExciseService.readFileExcel");

		if ( multipartFile.getFileExel() != null) {
			byte[] byt =  multipartFile.getFileExel().getBytes();
			Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
			// Get Sheet at index 0
			Sheet sheet = workbook.getSheetAt(0);

			List<Object> res = new ArrayList<Object>();
			List<Object> li = new ArrayList<Object>();
			Row row;
			Cell cell;

			for (int i = 1; i <= 6; i++) {
				row = sheet.getRow(i);
				li = new ArrayList<Object>();
				for (int j = 2; j <= 3; j++) {
					cell = row.getCell(j);
					li.add(new Float(cell.toString()));
				}
				res.add(li);
			}
			return res;
		}

		return null;
	}

}
