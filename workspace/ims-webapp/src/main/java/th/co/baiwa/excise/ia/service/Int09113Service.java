package th.co.baiwa.excise.ia.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;

@Service
public class Int09113Service {
	private static Logger log = LoggerFactory.getLogger(Int09113Service.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;
	
	@Autowired
	private Int09DataDtlService int09DataDtlService;

	public DataTableAjax<Int09TableDtlVo> findAll(Int09111And3FormVo formVo) {

		// query data
		formVo.setDocumentTypeCode("113");//DocumentType 113 = หน้าหลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ
		List<Int09TableDtlVo> list = iaTravelEstimatorDao.findAllTableDtl(formVo);
		Long count = iaTravelEstimatorDao.countTableDtl(formVo);

		// set data table
		DataTableAjax<Int09TableDtlVo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	public void delete(Long id) {	
		iaTravelEstimatorDao.deleteTableDtl(id);
	}
	public Long save(Int09111And3FormVo formVo) throws ParseException {
		Int09TableDtlVo data = int09DataDtlService.getDataDtl(formVo.getInt09FormDtlVo());
		data.setDocumentType("113");//DocumentType 113 = หน้าหลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ
		Long id = int09DataDtlService.saveDataDtlAndDataFormDtl(formVo.getInt09FormDtlVo(),data);
		return id ;
		
	}
	
	public void edit(Int09111And3FormVo formVo) throws ParseException {
		Int09TableDtlVo data = int09DataDtlService.getDataDtl(formVo.getInt09FormDtlVo());
		data.setId(formVo.getId());
		data.setDocumentType("113");//DocumentType 113 = หน้าหลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ
		int09DataDtlService.editDataDtlAndDataFormDtl(formVo.getInt09FormDtlVo(),data);
		
	}
	
	public void saveAll(Int09111And3FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentType("หลักฐานการจ่ายเงิน");				
		formVo.setSubject("หลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ");
		// เพิ่มเอกสาร
		iaTravelEstimatorDao.addDocument(formVo.getIdProcess(), formVo.getCreatedBy(), formVo.getDocumentType(),formVo.getSubject());
		// ล้างข้อมูล
		iaTravelEstimatorDao.deleteTravelEstimatorDtl(formVo.getIdProcess(),"113");
		
	}
	
}
