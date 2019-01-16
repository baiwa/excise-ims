package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.IaIceReportHdr;
import th.co.baiwa.excise.ia.persistence.repository.IaIceReportHdrRepository;

@Service
public class Int02m052Service {

	@Autowired
	private IaIceReportHdrRepository iaIceReportHdrRepository;
	
	public ResponseDataTable<IaIceReportHdr> findByCriteriaForDatatable(DataTableRequest dataTableRequest, IaIceReportHdr iaIceReportHdr){
		ResponseDataTable<IaIceReportHdr> data = new ResponseDataTable<>();
		int count = (int) iaIceReportHdrRepository.count();
		data.setData(this.findBySubSectionName(iaIceReportHdr.getSubSectionName()));
		data.setDraw(count);
		data.setLength(count);
		return data;
	}
	
	public List<IaIceReportHdr> findBySubSectionName(String subSectionName){
		return iaIceReportHdrRepository.findBySubSectionName(subSectionName);
	}
}
