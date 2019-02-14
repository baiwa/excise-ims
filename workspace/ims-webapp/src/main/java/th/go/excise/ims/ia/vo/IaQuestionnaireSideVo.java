package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class IaQuestionnaireSideVo extends BaseEntity {

    private BigDecimal id;
    private BigDecimal idHead;
    private String sideName;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdHead() {
        return idHead;
    }

    public void setIdHead(BigDecimal idHead) {
        this.idHead = idHead;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

}
