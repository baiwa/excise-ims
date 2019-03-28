package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.vo.TaFormTS0114DtlVo;

import java.util.List;

public interface TaFormTs0114DtlRepositoryCustom {

    void setIsDeleteY(String office, String budgetYear, String formTsNumber);

    TaFormTS0114DtlVo formDtl(String office, String budgetYear, String formTsNumber);
}
