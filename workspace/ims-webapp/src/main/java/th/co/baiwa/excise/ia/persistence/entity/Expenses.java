package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "IA_EXPENSES")
public class Expenses extends BaseEntity {

    private static final long serialVersionUID = -4326675540624605365L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXPENSES_GEN")
    @SequenceGenerator(name = "IA_EXPENSES_GEN", sequenceName = "IA_EXPENSES_SEQ", allocationSize = 1)

    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "SERVICE_RECEIVE")
    private BigDecimal serviceReceive;
    @Column(name = "SERVICE_WITHDRAW")
    private BigDecimal serviceWithdraw;
    @Column(name = "SERVICE_BALANCE")
    private BigDecimal serviceBalance;
    @Column(name = "SUPPRESS_RECEIVE")
    private BigDecimal suppressReceive;
    @Column(name = "SUPPRESS_WITHDRAW")
    private BigDecimal suppressWithdraw;
    @Column(name = "SUPPRESS_BALANCE")
    private BigDecimal suppressBalance;
    @Column(name = "BUDGET_RECEIVE")
    private BigDecimal budgetReceive;
    @Column(name = "BUDGET_WITHDRAW")
    private BigDecimal budgetWithdraw;
    @Column(name = "BUDGET_BALANCE")
    private BigDecimal budgetBalance;
    @Column(name = "SUM_RECEIVE")
    private BigDecimal sumReceive;
    @Column(name = "SUM_WITHDRAW")
    private BigDecimal sumWithdraw;
    @Column(name = "SUM_BALANCE")
    private BigDecimal sumBalance;
    @Column(name = "MONEY_BUDGET")
    private BigDecimal moneyBudget;
    @Column(name = "MONEY_OUT")
    private BigDecimal moneyOut;
    @Column(name = "AVERAGE_COST")
    private BigDecimal averageCost;
    @Column(name = "AVERAGE_GIVE")
    private String averageGive;
    @Column(name = "AVERAGE_FROM")
    private String averageFrom;
    @Column(name = "AVERAGE_COME_COST")
    private BigDecimal averageComeCost;
    @Column(name = "NOTE")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getServiceReceive() {
        return serviceReceive;
    }

    public void setServiceReceive(BigDecimal serviceReceive) {
        this.serviceReceive = serviceReceive;
    }

    public BigDecimal getServiceWithdraw() {
        return serviceWithdraw;
    }

    public void setServiceWithdraw(BigDecimal serviceWithdraw) {
        this.serviceWithdraw = serviceWithdraw;
    }

    public BigDecimal getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(BigDecimal serviceBalance) {
        this.serviceBalance = serviceBalance;
    }

    public BigDecimal getSuppressReceive() {
        return suppressReceive;
    }

    public void setSuppressReceive(BigDecimal suppressReceive) {
        this.suppressReceive = suppressReceive;
    }

    public BigDecimal getSuppressWithdraw() {
        return suppressWithdraw;
    }

    public void setSuppressWithdraw(BigDecimal suppressWithdraw) {
        this.suppressWithdraw = suppressWithdraw;
    }

    public BigDecimal getSuppressBalance() {
        return suppressBalance;
    }

    public void setSuppressBalance(BigDecimal suppressBalance) {
        this.suppressBalance = suppressBalance;
    }

    public BigDecimal getBudgetReceive() {
        return budgetReceive;
    }

    public void setBudgetReceive(BigDecimal budgetReceive) {
        this.budgetReceive = budgetReceive;
    }

    public BigDecimal getBudgetWithdraw() {
        return budgetWithdraw;
    }

    public void setBudgetWithdraw(BigDecimal budgetWithdraw) {
        this.budgetWithdraw = budgetWithdraw;
    }

    public BigDecimal getBudgetBalance() {
        return budgetBalance;
    }

    public void setBudgetBalance(BigDecimal budgetBalance) {
        this.budgetBalance = budgetBalance;
    }

    public BigDecimal getSumReceive() {
        return sumReceive;
    }

    public void setSumReceive(BigDecimal sumReceive) {
        this.sumReceive = sumReceive;
    }

    public BigDecimal getSumWithdraw() {
        return sumWithdraw;
    }

    public void setSumWithdraw(BigDecimal sumWithdraw) {
        this.sumWithdraw = sumWithdraw;
    }

    public BigDecimal getSumBalance() {
        return sumBalance;
    }

    public void setSumBalance(BigDecimal sumBalance) {
        this.sumBalance = sumBalance;
    }

    public BigDecimal getMoneyBudget() {
        return moneyBudget;
    }

    public void setMoneyBudget(BigDecimal moneyBudget) {
        this.moneyBudget = moneyBudget;
    }

    public BigDecimal getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(BigDecimal moneyOut) {
        this.moneyOut = moneyOut;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public String getAverageGive() {
        return averageGive;
    }

    public void setAverageGive(String averageGive) {
        this.averageGive = averageGive;
    }

    public String getAverageFrom() {
        return averageFrom;
    }

    public void setAverageFrom(String averageFrom) {
        this.averageFrom = averageFrom;
    }

    public BigDecimal getAverageComeCost() {
        return averageComeCost;
    }

    public void setAverageComeCost(BigDecimal averageComeCost) {
        this.averageComeCost = averageComeCost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
