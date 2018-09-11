package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09111Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;

@Service
public class Int09111Service {
	private static Logger log = LoggerFactory.getLogger(Int09111Service.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;

	public DataTableAjax<Int09111Vo> findAll(Int09111FormVo formVo) {

		// query data
		List<Int09111Vo> list = iaTravelEstimatorDao.findAll09111(formVo);
		Long count = iaTravelEstimatorDao.count09111(formVo);

		// set data table
		DataTableAjax<Int09111Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public void add(Int09111FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		formVo.setDocumentType("ประมาณการค่าใช้จ่าย");
		formVo.setSubject("ประมาณการค่าใช้จ่ายในการเดินทางไปราชการ");
		iaTravelEstimatorDao.addDocument(formVo.getIdProcess(), formVo.getCreatedBy(), formVo.getDocumentType(),
				formVo.getSubject());
	}

}
