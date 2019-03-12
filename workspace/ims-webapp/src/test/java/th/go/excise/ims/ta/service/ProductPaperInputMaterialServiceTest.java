package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperInputMaterialServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";
	
	private static final String PRODUCT_PAPER_INPUT_MATERIAL = "product_paper_input_material";
	
	ProductPaperInputMaterialService productPaperInputMaterialService = new ProductPaperInputMaterialService();
	
	@Test
	public void test_exportProductPaperInputMaterial() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_INPUT_MATERIAL + TYPE)) {
			byte[] outArray = productPaperInputMaterialService.exportProductPaperInputMaterial();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_INPUT_MATERIAL + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_INPUT_MATERIAL + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
