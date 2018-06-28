package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PdtDrawingWsDtl {
    private BigDecimal taPdtDrawingWsDtlId;
    private BigDecimal taPdtDrawingWsHeaderId;
    private String drawingDate;
    private String billNo;
    private String taPdtDrawingWsDtlOwder;
    private String rawMatReceive;
    private String pdtFormula;
    private String formulaDraw;
    private String draw;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public BigDecimal getTaPdtDrawingWsDtlId() {
        return taPdtDrawingWsDtlId;
    }

    public void setTaPdtDrawingWsDtlId(BigDecimal taPdtDrawingWsDtlId) {
        this.taPdtDrawingWsDtlId = taPdtDrawingWsDtlId;
    }

    public BigDecimal getTaPdtDrawingWsHeaderId() {
        return taPdtDrawingWsHeaderId;
    }

    public void setTaPdtDrawingWsHeaderId(BigDecimal taPdtDrawingWsHeaderId) {
        this.taPdtDrawingWsHeaderId = taPdtDrawingWsHeaderId;
    }

    public String getDrawingDate() {
        return drawingDate;
    }

    public void setDrawingDate(String drawingDate) {
        this.drawingDate = drawingDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTaPdtDrawingWsDtlOwder() {
        return taPdtDrawingWsDtlOwder;
    }

    public void setTaPdtDrawingWsDtlOwder(String taPdtDrawingWsDtlOwder) {
        this.taPdtDrawingWsDtlOwder = taPdtDrawingWsDtlOwder;
    }

    public String getRawMatReceive() {
        return rawMatReceive;
    }

    public void setRawMatReceive(String rawMatReceive) {
        this.rawMatReceive = rawMatReceive;
    }

    public String getPdtFormula() {
        return pdtFormula;
    }

    public void setPdtFormula(String pdtFormula) {
        this.pdtFormula = pdtFormula;
    }

    public String getFormulaDraw() {
        return formulaDraw;
    }

    public void setFormulaDraw(String formulaDraw) {
        this.formulaDraw = formulaDraw;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
