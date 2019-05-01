package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.monitorjbl.xlsx.StreamingReader;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;

@Service
public class IaGfledgerAccountService {
	
	private final String[] KEY_FILTER = {"..รหัสหน่วยงาน","เลขที่บัญชี G/L","**"};
	
	public void addDataByExcel(File file) {
		try {
			List<List<String>> ex = ExcelUtils.readExcel(file);
			for (List<String> list : ex) {
				for (int i = 0; i < list.size(); i++) {
					System.out.print(i + " : " + list.get(i) + "||");				
				}
				if(list.get(0) != null ) {
					
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readExcel2(File file) throws FileNotFoundException {
		List<IaGfledgerAccount> iaGfledgerAccountList = new ArrayList<>();
		IaGfledgerAccount iaGfledgerAccount = new IaGfledgerAccount();
		InputStream is = new FileInputStream(file);
		Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		String departmentCode = "";
		String glAccountCode = "";
		String glAccountName = "";
		for (Sheet sheet : workbook) {
		
			for (Row r : sheet) {
				iaGfledgerAccount = new IaGfledgerAccount();
				
				for (Cell c : r) {
					System.out.print(c.getColumnIndex() + ":" + c.getStringCellValue() + "||");
//					if() {
//						
//					}
				}
				System.out.println();
			}
		}
	}
}
