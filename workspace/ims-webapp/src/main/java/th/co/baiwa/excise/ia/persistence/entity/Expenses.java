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
    @Column(name = "SERVICE_WITHDRAWE")
    private BigDecimal serviceWithdrawe;
    @Column(name = "SERVICE_BALANCE")
    private BigDecimal serviceBalance;
    @Column(name = "SUPPRESS_RECEIVE")
    private BigDecimal suppressReceive;
    @Column(name = "SUPPRESS_WITHDRAWE")
    private BigDecimal suppressWithdrawe;
    @Column(name = "SUPPRESS_BALANCE")
    private BigDecimal suppressBalance;
    @Column(name = "BUDGET_RECEIVE")
    private BigDecimal budgetReceive;
    @Column(name = "BUDGET_WITHDRAWE")
    private BigDecimal budgetWithdrawe;
    @Column(name = "BUDGET_BALANCE")
    private BigDecimal budgetBalance;
    @Column(name = "SUM_RECEIVE")
    private BigDecimal sumReceive;
    @Column(name = "SUM_WITHDRAWE")
    private BigDecimal sumWithdrawe;
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
    @Column(name = "AVERRAGE_FROM")
    private String averrageFrom;
    @Column(name = "AVERRATE_COME_COST")
    private BigDecimal averrateComeCost;

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

    public BigDecimal getServiceWithdrawe() {
        return serviceWithdrawe;
    }

    public void setServiceWithdrawe(BigDecimal serviceWithdrawe) {
        this.serviceWithdrawe = serviceWithdrawe;
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

    public BigDecimal getSuppressWithdrawe() {
        return suppressWithdrawe;
    }

    public void setSuppressWithdrawe(BigDecimal suppressWithdrawe) {
        this.suppressWithdrawe = suppressWithdrawe;
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

    public BigDecimal getBudgetWithdrawe() {
        return budgetWithdrawe;
    }

    public void setBudgetWithdrawe(BigDecimal budgetWithdrawe) {
        this.budgetWithdrawe = budgetWithdrawe;
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

    public BigDecimal getSumWithdrawe() {
        return sumWithdrawe;
    }

    public void setSumWithdrawe(BigDecimal sumWithdrawe) {
        this.sumWithdrawe = sumWithdrawe;
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

    public String getAverrageFrom() {
        return averrageFrom;
    }

    public void setAverrageFrom(String averrageFrom) {
        this.averrageFrom = averrageFrom;
    }

    public BigDecimal getAverrateComeCost() {
        return averrateComeCost;
    }

    public void setAverrateComeCost(BigDecimal averrateComeCost) {
        this.averrateComeCost = averrateComeCost;
    }
}
