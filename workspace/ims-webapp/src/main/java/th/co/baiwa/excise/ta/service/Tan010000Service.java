package th.co.baiwa.excise.ta.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.Tan010000Dao;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000Vo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600Vo;

@Service
public class Tan010000Service {

	@Autowired
	private Tan010000Dao tan010000Dao;

	public DataTableAjax<Tan010000Vo> search(Tan010000FormVo formVo) {
		DataTableAjax<Tan010000Vo> dataTableAjax = new DataTableAjax<Tan010000Vo>();

		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Tan010000Vo> list = tan010000Dao.search(formVo);
			long count = tan010000Dao.count(formVo);

			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public void edit(Tan010000FormVo formVo) throws ParseException {
		tan010000Dao.editTan01(formVo);
	}
}
