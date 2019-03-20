package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;

public class TaFormTS0108ServiceTest {
	private static final String PATH = "/tmp/";
	private static final String NAME = "TaFormTS0108.pdf";
	@Test
	public void test_exportTaFormTS0108DtlPdf() throws Throwable, IOException  {
		TaFormTS0108Service service08 = new TaFormTS0108Service();
		
		List<TaFormTS0108DtlVo> ts0108DtlList = new ArrayList<>();
		TaFormTS0108DtlVo ts0108DtlVo = null;
	
			ts0108DtlVo = new TaFormTS0108DtlVo();
			ts0108DtlVo.setRecNo(1);
			
			ts0108DtlVo.setAuditDate( new Date());
			ts0108DtlVo.setOfficerFullName("ธนพล ชัยภูมิ");
			ts0108DtlVo.setOfficerPosition("ตรวจสอบภาษี");
			ts0108DtlVo.setAuditTime("13:30");
			ts0108DtlVo.setAuditDest("โรงผลิตเหล้า");
			ts0108DtlVo.setAuditTopic("การหมักเหล้า");
			ts0108DtlVo.setApprovedAck("สุรศักดิ์ ");
			ts0108DtlVo.setAuditResultDate(new Date());
			ts0108DtlVo.setAuditComment("");
			ts0108DtlList.add(ts0108DtlVo);
	

		

		byte[] reportFile = service08.export(ts0108DtlVo);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));

     
	}
}
