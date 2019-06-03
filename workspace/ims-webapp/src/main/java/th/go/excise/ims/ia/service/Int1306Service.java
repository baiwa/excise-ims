package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.Int1306JdbcRepository;
import th.go.excise.ims.ia.vo.Int1306DataVo;
import th.go.excise.ims.ia.vo.Int1306FormVo;
import th.go.excise.ims.ia.vo.Int1306Vo;

@Service
public class Int1306Service {

	private static final Logger logger = LoggerFactory.getLogger(Int1306Service.class);

	@Autowired
	private Int1306JdbcRepository int1306JdbcRepository;

	public Int1306Vo findCriteria(Int1306FormVo request) {
		Int1306Vo response = null;
		List<Int1306DataVo> int1306List = new ArrayList<Int1306DataVo>();
		Int1306DataVo data1 = null;
		Int1306DataVo data2 = null;
		Int1306DataVo data3 = null;
		Int1306DataVo data4 = null;
		Int1306DataVo data5 = null;
		try {
			data1 = int1306JdbcRepository.findIaAuditPmassessHByCriteria(request);
			data2 = int1306JdbcRepository.findIaAuditPmQtHByCriteria(request);
			data3 = int1306JdbcRepository.findIaAuditPy1HCriteria(request);
			data4 = int1306JdbcRepository.findIaAuditPy2HCriteria(request);
			data5 = int1306JdbcRepository.findIaAuditPmCommitHCriteria(request);
			int1306List.add(data1);
			int1306List.add(data2);
			int1306List.add(data3);
			int1306List.add(data4);
			int1306List.add(data5);
			// set response
			response = new Int1306Vo();
			response.setAuditPmassessNo(data1.getAuditNo());
			response.setAuditPmqtNo(data2.getAuditNo());
			response.setAuditPy1No(data3.getAuditNo());
			response.setAuditPy2No(data4.getAuditNo());
			response.setAuditPmcommitNo(data5.getAuditNo());
			response.setDataList(int1306List);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return response;
	}

}
