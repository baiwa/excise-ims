package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "IA_STAMP_FIILE")
public class IaStampFile extends BaseEntity {

    private static final long serialVersionUID = 6239839768719246436L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_FIILE_GEN")
    @SequenceGenerator(name = "IA_STAMP_FIILE_GEN", sequenceName = "IA_STAMP_FIILE_SEQ", allocationSize = 1)

    @Column(name = "ID")
    private long id;
    @Column(name = "DETAIL_ID")
    private String detailId;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "VALUE1")
    private String value1;
    @Column(name = "VALUE2")
    private String value2;
    @Column(name = "VALUE3")
    private String value3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
