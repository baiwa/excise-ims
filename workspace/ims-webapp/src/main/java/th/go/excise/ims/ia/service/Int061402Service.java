package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int061402JdbcRepository;
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;

@Service
public class Int061402Service {
	@Autowired
	private Int061402JdbcRepository int061402JdbcRepository;
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	public DataTableAjax<Ws_Reg4000Vo> filter(Int061402FilterVo formVo) {
		List<Ws_Reg4000Vo> data = int061402JdbcRepository.getDataFilter(formVo);
		/* convert date to string */

		DataTableAjax<Ws_Reg4000Vo> dataTableAjax = new DataTableAjax<Ws_Reg4000Vo>();
//		dataTableAjax.setDraw(formVo.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(int061402JdbcRepository.countDatafilter(formVo));
		dataTableAjax.setRecordsFiltered(int061402JdbcRepository.countDatafilter(formVo));

		return dataTableAjax;
	}

	public void save(List<TaWsReg4000> listCheckbox) {
		taWsReg4000Repository.saveAll(listCheckbox);
	}

}
