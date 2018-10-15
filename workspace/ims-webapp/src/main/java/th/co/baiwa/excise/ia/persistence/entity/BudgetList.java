package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_BUDGET_LIST database table.
 * 
 */
@Entity
@Table(name="IA_BUDGET_LIST")
public class BudgetList extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1583550147693789331L;

	@Id
	@Column(name="SYS_BUDGET_ID")
	private Long sysBudgetId;

	@Column(name="BUDGET_ID")
	private String budgetId;

	@Column(name="BUDGET_NAME")
	private String budgetName;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	@Column(name="LIST_ID")
	private String listId;

	@Column(name="LIST_NAME")
	private String listName;

	public Long getSysBudgetId() {
		return sysBudgetId;
	}

	public void setSysBudgetId(Long sysBudgetId) {
		this.sysBudgetId = sysBudgetId;
	}

	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	

}