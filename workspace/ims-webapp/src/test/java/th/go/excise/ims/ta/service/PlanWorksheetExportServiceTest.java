package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class PlanWorksheetExportServiceTest {
	
	private static final String OUTPUT_PATH = "/tmp/excise/ims/report";
	
	@Autowired
	private PlanWorksheetExportService planWorksheetExportService;
	
	@Test
	public void exportCondSubWorksheet() {
		PlanWorksheetVo formVo = new PlanWorksheetVo();
		formVo.setPlanNumber("000000-2562-000043");
		
		String fileName = "Plan Worksheet" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream Output = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] outArray = planWorksheetExportService.exportPlanWorksheet(formVo);
			Output.write(outArray);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

}
