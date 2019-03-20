package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

public class TaFormTS0107ServiceTest {

	private TaFormTS0107Service taFormTS0107Service = new TaFormTS0107Service();
	private static final String PATH = "/tmp/";
	private static final String NAME = "TaFormTS0107.pdf";
	@Test
	public void test_exportTaFormTS0107() throws Throwable, IOException {

		TaFormTS0107Vo data = new TaFormTS0107Vo();
		//set  data
		data.setBookNumber1("TEST");
		data.setBookNumber2("TEST2");

		byte[] reportFile = taFormTS0107Service.exportTaFormTS0107(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));

	}
}
