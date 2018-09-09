package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "IA_STAMP_TYPE")
public class IaStamType extends BaseEntity {

    private static final long serialVersionUID = -3997536090394694616L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_TYPE_GEN")
    @SequenceGenerator(name = "IA_STAMP_TYPE_GEN", sequenceName = "IA_STAMP_TYPE_SEQ", allocationSize = 1)

    @Column(name = "STAMP_TYPE_ID")
    private long stampTypeId;

    @Column(name = "STAMP_TYPE_NAME")
    private String stampTypeName;

    public long getStampTypeId() {
        return stampTypeId;
    }

    public void setStampTypeId(long stampTypeId) {
        this.stampTypeId = stampTypeId;
    }

    public String getStampTypeName() {
        return stampTypeName;
    }

    public void setStampTypeName(String stampTypeName) {
        this.stampTypeName = stampTypeName;
    }
}
