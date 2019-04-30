package th.go.excise.ims.ia.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExcelUtils;

@Service
public class IaRepDisbPerMonthService {

	
	public void addDataByExcel(File file) {
		try {
			List<List<String>> ex = ExcelUtils.readExcel(file);
			for (List<String> list : ex) {
					for (int i = 0; i < list.size(); i++) {
						System.out.print(i+" : "+list.get(i) + "\t");
					}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
