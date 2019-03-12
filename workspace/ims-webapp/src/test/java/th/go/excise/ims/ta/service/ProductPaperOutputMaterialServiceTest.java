package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperOutputMaterialServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_OUTPUT_MATERIAL = "product_paper_output_material";

	ProductPaperOutputMaterialService productPaperOutputMaterialService = new ProductPaperOutputMaterialService();

	@Test
	public void test_exportProductPaperOutputMaterial() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_OUTPUT_MATERIAL + TYPE)) {
			byte[] outArray = productPaperOutputMaterialService.exportProductPaperOutputMaterial();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_OUTPUT_MATERIAL + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out
					.println(PRODUCT_PAPER_OUTPUT_MATERIAL + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
