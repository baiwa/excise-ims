package th.co.baiwa.buckwaframework.common.bean;

import java.io.Serializable;

public class DataTableRequest implements Serializable {

    private static final long serialVersionUID = 498837492551085248L;

    private Integer start;
    private Integer length;
    private Long draw;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

}
