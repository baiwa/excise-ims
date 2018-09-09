package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "IA_STAMP_GENRE")
public class IaStamGenre extends BaseEntity {


    private static final long serialVersionUID = 3444977398738152310L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_GENRE_GEN")
    @SequenceGenerator(name = "IA_STAMP_GENRE_GEN", sequenceName = "IA_STAMP_GENRE_SEQ", allocationSize = 1)

    @Column(name = "STAMP_GENRE_ID")
    private long stampGenreId;
    @Column(name = "STAMP_TYPE_ID")
    private String stampTypeId;
    @Column(name = "STAMP_GENRE_NAME")
    private String stampGenreName;

    public long getStampGenreId() {
        return stampGenreId;
    }

    public void setStampGenreId(long stampGenreId) {
        this.stampGenreId = stampGenreId;
    }

    public String getStampTypeId() {
        return stampTypeId;
    }

    public void setStampTypeId(String stampTypeId) {
        this.stampTypeId = stampTypeId;
    }

    public String getStampGenreName() {
        return stampGenreName;
    }

    public void setStampGenreName(String stampGenreName) {
        this.stampGenreName = stampGenreName;
    }
}
