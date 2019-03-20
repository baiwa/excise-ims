
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_ALCOHOL_SUMMARY")
public class OaAlcoholSummary
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_SUMMARY_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_SUMMARY_GEN", sequenceName = "OA_ALCOHOL_SUMMARY_SEQ", allocationSize = 1)
    @Column(name = "OA_ACH_SUMARY_ID")
    private BigDecimal oaAchSumaryId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "LUB_NAME")
    private String lubName;
    @Column(name = "MONTH")
    private String month;
    @Column(name = "YEAR")
    private String year;
    @Column(name = "STOCK_LATS_MONTH")
    private BigDecimal stockLatsMonth;
    @Column(name = "RECEIVE")
    private BigDecimal receive;
    @Column(name = "SENDING")
    private BigDecimal sending;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "STOCK")
    private BigDecimal stock;

    public BigDecimal getOaAchSumaryId() {
        return oaAchSumaryId;
    }

    public void setOaAchSumaryId(BigDecimal oaAchSumaryId) {
        this.oaAchSumaryId = oaAchSumaryId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getLubName() {
        return lubName;
    }

    public void setLubName(String lubName) {
        this.lubName = lubName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getStockLatsMonth() {
        return stockLatsMonth;
    }

    public void setStockLatsMonth(BigDecimal stockLatsMonth) {
        this.stockLatsMonth = stockLatsMonth;
    }

    public BigDecimal getReceive() {
        return receive;
    }

    public void setReceive(BigDecimal receive) {
        this.receive = receive;
    }

    public BigDecimal getSending() {
        return sending;
    }

    public void setSending(BigDecimal sending) {
        this.sending = sending;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

}
