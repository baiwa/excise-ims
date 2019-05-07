
package th.go.excise.ims.ed.persistence.entity;

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
@Table(name = "EXCISE_PERSON")
public class ExcisePerson
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_PERSON_GEN")
    @SequenceGenerator(name = "EXCISE_PERSON_GEN", sequenceName = "EXCISE_PERSON_SEQ", allocationSize = 1)
    @Column(name = "ED_PERSON_SEQ")
    private BigDecimal edPersonSeq;
    @Column(name = "ED_LOGIN")
    private String edLogin;
    @Column(name = "ED_PERSON_NAME")
    private String edPersonName;
    @Column(name = "ED_POSITION_SEQ")
    private BigDecimal edPositionSeq;
    @Column(name = "ED_POSITION_NAME")
    private String edPositionName;
    @Column(name = "ED_OFFCODE")
    private String edOffcode;
    @Column(name = "ED_PERSON_ID")
    private String edPersonId;

    public BigDecimal getEdPersonSeq() {
        return edPersonSeq;
    }

    public void setEdPersonSeq(BigDecimal edPersonSeq) {
        this.edPersonSeq = edPersonSeq;
    }

    public String getEdLogin() {
        return edLogin;
    }

    public void setEdLogin(String edLogin) {
        this.edLogin = edLogin;
    }

    public String getEdPersonName() {
        return edPersonName;
    }

    public void setEdPersonName(String edPersonName) {
        this.edPersonName = edPersonName;
    }

    public BigDecimal getEdPositionSeq() {
        return edPositionSeq;
    }

    public void setEdPositionSeq(BigDecimal edPositionSeq) {
        this.edPositionSeq = edPositionSeq;
    }

    public String getEdPositionName() {
        return edPositionName;
    }

    public void setEdPositionName(String edPositionName) {
        this.edPositionName = edPositionName;
    }

    public String getEdOffcode() {
        return edOffcode;
    }

    public void setEdOffcode(String edOffcode) {
        this.edOffcode = edOffcode;
    }

    public String getEdPersonId() {
        return edPersonId;
    }

    public void setEdPersonId(String edPersonId) {
        this.edPersonId = edPersonId;
    }

}
