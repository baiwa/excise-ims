
package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "ADM_ROLE_OPERATION")
public class AdmRoleOperation
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_ROLE_OPERATION_GEN")
    @SequenceGenerator(name = "ADM_ROLE_OPERATION_GEN", sequenceName = "ADM_ROLE_OPERATION_SEQ", allocationSize = 1)
    @Column(name = "ROLE_OPERATION_ID")
    private BigDecimal roleOperationId;
    @Column(name = "ROLE_ID")
    private BigDecimal roleId;
    @Column(name = "OPERATION_ID")
    private BigDecimal operationId;

    public BigDecimal getRoleOperationId() {
        return roleOperationId;
    }

    public void setRoleOperationId(BigDecimal roleOperationId) {
        this.roleOperationId = roleOperationId;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getOperationId() {
        return operationId;
    }

    public void setOperationId(BigDecimal operationId) {
        this.operationId = operationId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
