package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="OA_PROD_DRAW_WS_HDR")
public class OaProdDrawWsHdr extends BaseEntity {
	
	private static final long serialVersionUID = 8379887892852417260L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PROD_DRAW_WS_HDR_GEN")
	@SequenceGenerator(name = "OA_PROD_DRAW_WS_HDR_GEN", sequenceName = "OA_PROD_DRAW_WS_HDR_SEQ", allocationSize = 1)
	@Column(name="PRODUCT_DRAWING_WS_HDR_ID")
	private Long productDrawingWsHdrId;
	
	@Column(name="ANALYSIS_ID")
	private Long analysisId;
	
	@Column(name="END_DATE")
	private String endDate;
	
	@Column(name="EXCISE_ID")
	private String exciseId;
	
	@Column(name="OPER_PLAN_ID")
	private Long operPlanId;
	
	@Column(name="PRODUCT_TYPE")
	private String productType;
	
	@Column(name="START_DATE")
	private String startDate;
	
	@Column(name="SUB_PRODUCT_TYPE")
	private String subProductType;
	
	@Column(name="TAXATION_ID")
	private String taxationId;

}