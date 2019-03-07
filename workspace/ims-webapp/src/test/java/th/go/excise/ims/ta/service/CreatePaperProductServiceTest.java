package th.go.excise.ims.ta.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class CreatePaperProductServiceTest {

	
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";
	private static final String MATERIAL_RECEIVE = "material_receive";

	@Test
	public void test_materialReceive() throws IOException {

		CreatePaperProductService createPaperProductService = new CreatePaperProductService();

		try {
			byte[] outArray = createPaperProductService.exportRawMaterialReceive();
			FileOutputStream Output = new FileOutputStream(PATH + MATERIAL_RECEIVE + TYPE);
			Output.write(outArray);
			Output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");

	}
}
