package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountDetil;
import th.co.baiwa.excise.ia.persistence.repository.VerifyAccountDtlRepository;

@Service
public class Int072Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VerifyAccountDtlRepository verifyAccountDtlRepository;

	public DataTableAjax<VerifyAccountDetil> queryDatatable(VerifyAccountDetil vo) {
		logger.info("Query for datatable");
		
		List<VerifyAccountDetil> data = new ArrayList<>();
		try {
			data = verifyAccountDtlRepository.findByHeaderId(vo.getVerifyAccountHeaderId());
		} catch (Exception e) {
			// TODO: handle exception
		}

		DataTableAjax<VerifyAccountDetil> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(Long.valueOf(data.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(data.size()));
		dataTableAjax.setData(data);

		return dataTableAjax;
	}

}
