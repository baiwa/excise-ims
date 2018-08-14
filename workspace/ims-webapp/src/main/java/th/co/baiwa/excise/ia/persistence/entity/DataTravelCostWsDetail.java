package th.co.baiwa.excise.ia.persistence.entity;

        import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

        import javax.persistence.*;
        import java.math.BigDecimal;

@Entity
@Table(name = "IA_TRAVEL_COST_WS_DETAIL")

public class DataTravelCostWsDetail extends BaseEntity {

    private static final long serialVersionUID = 2514647179575934858L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_SHEET_DETAIL_ID")
    @SequenceGenerator(name = "WORK_SHEET_DETAIL_ID", sequenceName = "IA_TRAVEL_COST_WS_DETAIL_SEQ", allocationSize = 1)

    @Column(name = "HEADER_ID")
    private String headerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DEGREE")
    private String degree;

    @Column(name = "ALLOWANCE_DATE")
    private BigDecimal allowanceDate;

    @Column(name = "ALLOWANCE_COST")
    private BigDecimal allowanceCost;

    @Column(name = "RENT_DATE")
    private BigDecimal rentDate;

    @Column(name = "RENT_COST")
    private BigDecimal rentCost;

    @Column(name = "TRAVEL_COST")
    private BigDecimal travelCost;

    @Column(name = "OTHER_COST")
    private BigDecimal otherCost;

    @Column(name = "SUM_COST")
    private BigDecimal sumCost;

    @Column(name = "NOTE")
    private String note;

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public BigDecimal getAllowanceDate() {
        return allowanceDate;
    }

    public void setAllowanceDate(BigDecimal allowanceDate) {
        this.allowanceDate = allowanceDate;
    }

    public BigDecimal getAllowanceCost() {
        return allowanceCost;
    }

    public void setAllowanceCost(BigDecimal allowanceCost) {
        this.allowanceCost = allowanceCost;
    }

    public BigDecimal getRentDate() {
        return rentDate;
    }

    public void setRentDate(BigDecimal rentDate) {
        this.rentDate = rentDate;
    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

    public void setRentCost(BigDecimal rentCost) {
        this.rentCost = rentCost;
    }

    public BigDecimal getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(BigDecimal travelCost) {
        this.travelCost = travelCost;
    }

    public BigDecimal getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    public BigDecimal getSumCost() {
        return sumCost;
    }

    public void setSumCost(BigDecimal sumCost) {
        this.sumCost = sumCost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
