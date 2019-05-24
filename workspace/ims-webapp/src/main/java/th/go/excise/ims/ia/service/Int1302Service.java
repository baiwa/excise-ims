package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaAuditPy1D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy1DRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy1HRepository;
import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302Py1NoVo;
import th.go.excise.ims.ia.vo.Int1302SaveDtlFormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1HRepository;

@Service
public class Int1302Service {
	@Autowired
	private WsPmPy1HRepository wsPmPy1HRepository;

	@Autowired
	private WsPmPy1DRepository wsPmPy1DRepository;

	@Autowired
	private IaAuditPy1HRepository iaAuditPy1HRepository;

	@Autowired
	private IaAuditPy1DRepository iaAuditPy1DRepository;

	public Int1302Vo list(Int1302FormVo form) {
		form.setArea("010000");
		Int1302Vo int1302Vo = new Int1302Vo();
		WsPmPy1H wsPmPy1H = wsPmPy1HRepository.findByOffCodeByFormYear(form.getArea(), form.getBudgetYear());
		List<WsPmPy1D> wsPmPy1DList = wsPmPy1DRepository.findByFormCodeByOffCode(wsPmPy1H.getFormCode(),
				form.getArea());
		int1302Vo.setWsPmPy1H(wsPmPy1H);
		int1302Vo.setWsPmPy1DList(wsPmPy1DList);
		return int1302Vo;
	}

	public void saveData(Int1302SaveDtlFormVo form) {
		IaAuditPy1H dataHdrSave = new IaAuditPy1H();
		IaAuditPy1D dataDtlSave = null;
		String py1No = "PY1 ";
		
		dataHdrSave.setAuditPy1No(py1No);
		dataHdrSave.setBuggetYear(form.getBuggetYear());
		dataHdrSave.setOfficeCode(form.getOfficeCode());
		dataHdrSave.setOverallResules(form.getOverallResules());
		iaAuditPy1HRepository.save(dataHdrSave);
		for (IaAuditPy1D dataDtl : form.getIaAuditPy1DList()) {
			dataDtlSave = new IaAuditPy1D();
			dataDtlSave.setAuditPy1No(py1No);
			dataDtlSave.setTopicDesc(dataDtl.getTopicDesc());
			dataDtlSave.setTopicAnswer(dataDtl.getTopicAnswer());
			dataDtlSave.setAuditResult(dataDtl.getAuditResult());
			iaAuditPy1DRepository.save(dataDtlSave);
		}
	}
	
	public Int1302Py1NoVo findByPy1No(String py1No) {
		Int1302Py1NoVo dataRes = new Int1302Py1NoVo();
		IaAuditPy1H py1Hdr = iaAuditPy1HRepository.findByAuditPy1No(py1No);
		List<IaAuditPy1D> py1DtlList = iaAuditPy1DRepository.findByAuditPy1No(py1No);
		dataRes.setIaAuditPy1H(py1Hdr);
		dataRes.setIaAuditPy1DList(py1DtlList);
		return dataRes;
	}
	
	public List<IaAuditPy1H> getPy1NoList() {
		return iaAuditPy1HRepository.getAuditPy1NoList();
	}
}
