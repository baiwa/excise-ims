package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_MATERIALS_WORKSHEET_DETAIL database table.
 * 
 */
@Entity
@Table(name="TA_MATERIALS_WORKSHEET_DETAIL")
public class MaterialsWorksheetDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804428222875317592L;

	@Id
	@SequenceGenerator(name="TA_MTRA_WORKSHEET_DETAIL_GEN", sequenceName="TA_MTRA_WORKSHEET_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_MTRA_WORKSHEET_DETAIL_GEN")
	@Column(name="TA_MATERIALS_WS_DTL_ID")
	private long taMaterialsWsDtlId;

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

	@Column(name="TA_MATERIALS_WS_HEADER_ID")
	private Long taMaterialsWsHeaderId;

	public long getTaMaterialsWsDtlId() {
		return taMaterialsWsDtlId;
	}

	public void setTaMaterialsWsDtlId(long taMaterialsWsDtlId) {
		this.taMaterialsWsDtlId = taMaterialsWsDtlId;
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

	public Long getTaMaterialsWsHeaderId() {
		return taMaterialsWsHeaderId;
	}

	public void setTaMaterialsWsHeaderId(Long taMaterialsWsHeaderId) {
		this.taMaterialsWsHeaderId = taMaterialsWsHeaderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}