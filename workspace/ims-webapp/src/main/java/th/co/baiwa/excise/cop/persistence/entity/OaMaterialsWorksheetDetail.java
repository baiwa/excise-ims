package th.co.baiwa.excise.cop.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the OA_MATERIALS_WORKSHEET_DETAIL database table.
 * 
 */
@Entity
@Table(name="OA_MATERIALS_WORKSHEET_DETAIL")
public class OaMaterialsWorksheetDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804428222875317592L;

	@Id
	@SequenceGenerator(name="OA_MTRA_WORKSHEET_DETAIL_GEN", sequenceName="OA_MATERIALS_WS_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OA_MTRA_WORKSHEET_DETAIL_GEN")
	@Column(name="OA_MATERIALS_WS_DTL_ID")
	private long oaMaterialsWsDtlId;

	@Column(name="MATERIALS_WS_DTL_BALANCE")
	private Long materialsWsDtlBalance;

	@Column(name="MATERIALS_WS_DTL_COUNTING")
	private Long materialsWsDtlCounting;

	@Column(name="MATERIALS_WS_DTL_NO")
	private Long materialsWsDtlNo;

	@Column(name="MATERIALS_WS_DTL_ORDER")
	private String materialsWsDtlOrder;

	@Column(name="MATERIALS_WS_DTL_STORAGE")
	private Long materialsWsDtlStorage;

	@Column(name="MAX_VALUES")
	private Long maxValues;

	@Column(name="RESULT")
	private Long result;

	@Column(name="RESULT_1")
	private Long result1;

	@Column(name="OA_MATERIALS_WS_HEADER_ID")
	private Long oaMaterialsWsHeaderId;

	public long getOaMaterialsWsDtlId() {
		return oaMaterialsWsDtlId;
	}

	public void setOaMaterialsWsDtlId(long oaMaterialsWsDtlId) {
		this.oaMaterialsWsDtlId = oaMaterialsWsDtlId;
	}

	public Long getMaterialsWsDtlBalance() {
		return materialsWsDtlBalance;
	}

	public void setMaterialsWsDtlBalance(Long materialsWsDtlBalance) {
		this.materialsWsDtlBalance = materialsWsDtlBalance;
	}

	public Long getMaterialsWsDtlCounting() {
		return materialsWsDtlCounting;
	}

	public void setMaterialsWsDtlCounting(Long materialsWsDtlCounting) {
		this.materialsWsDtlCounting = materialsWsDtlCounting;
	}

	public Long getMaterialsWsDtlNo() {
		return materialsWsDtlNo;
	}

	public void setMaterialsWsDtlNo(Long materialsWsDtlNo) {
		this.materialsWsDtlNo = materialsWsDtlNo;
	}

	public String getMaterialsWsDtlOrder() {
		return materialsWsDtlOrder;
	}

	public void setMaterialsWsDtlOrder(String materialsWsDtlOrder) {
		this.materialsWsDtlOrder = materialsWsDtlOrder;
	}

	public Long getMaterialsWsDtlStorage() {
		return materialsWsDtlStorage;
	}

	public void setMaterialsWsDtlStorage(Long materialsWsDtlStorage) {
		this.materialsWsDtlStorage = materialsWsDtlStorage;
	}

	public Long getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(Long maxValues) {
		this.maxValues = maxValues;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Long getResult1() {
		return result1;
	}

	public void setResult1(Long result1) {
		this.result1 = result1;
	}

	public Long getOaMaterialsWsHeaderId() {
		return oaMaterialsWsHeaderId;
	}

	public void setOaMaterialsWsHeaderId(Long oaMaterialsWsHeaderId) {
		this.oaMaterialsWsHeaderId = oaMaterialsWsHeaderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}