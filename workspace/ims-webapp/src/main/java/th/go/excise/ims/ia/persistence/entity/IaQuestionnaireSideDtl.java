
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_QUESTIONNAIRE_SIDE_DTL")
public class IaQuestionnaireSideDtl
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QUESTIONNAIRE_SIDE_DTL_GEN")
    @SequenceGenerator(name = "IA_QUESTIONNAIRE_SIDE_DTL_GEN", sequenceName = "IA_QUESTIONNAIRE_SIDE_DTL_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ID_SIDE")
    private BigDecimal idSide;
    @Column(name = "SIDE_DTL")
    private String sideDtl;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdSide() {
        return idSide;
    }

    public void setIdSide(BigDecimal idSide) {
        this.idSide = idSide;
    }

    public String getSideDtl() {
        return sideDtl;
    }

    public void setSideDtl(String sideDtl) {
        this.sideDtl = sideDtl;
    }

}