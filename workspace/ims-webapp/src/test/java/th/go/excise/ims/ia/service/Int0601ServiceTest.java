package th.go.excise.ims.ia.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.junit.Test;

public class Int0601ServiceTest {
	 private static final String PATH = "/tmp/";
	 private static final String TYPE = ".xlsx";
	 private static final String MATERIAL_RECEIVE = "service";

	 @Test
	 public void test_PriceServiceVo() throws IOException {

	  String fileName = URLEncoder.encode(MATERIAL_RECEIVE, "UTF-8");
	  Int0601Service int0601Service = new Int0601Service();

	  try {
	   byte[] outArray = int0601Service.export();
	   FileOutputStream Output = new FileOutputStream(PATH + fileName + TYPE);
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
