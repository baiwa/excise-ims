package th.co.baiwa.excise.ia.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "IA_EXPENSES")
public class Expenses extends BaseEntity {

    private static final long serialVersionUID = -4326675540624605365L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXPENSES_GEN")
    @SequenceGenerator(name = "IA_EXPENSES_GEN", sequenceName = "IA_EXPENSES_SEQ", allocationSize = 1)

    @Column(name = "ID")
    private String id;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "SERVICE_RECEIVE")
    private String serviceReceive;
    @Column(name = "SERVICE_WITHDRAWE")
    private String serviceWithdrawe;
    @Column(name = "SERVICE_BALANCE")
    private String serviceBalance;
    @Column(name = "SUPPRESS_RECEIVE")
    private String suppressReceive;
    @Column(name = "SUPPRESS_WITHDRAWE")
    private String suppressWithdrawe;
    @Column(name = "SUPPRESS_BALANCE")
    private String suppressBalance;
    @Column(name = "BUDGET_RECEIVE")
    private String budgetReceive;
    @Column(name = "BUDGET_WITHDRAWE")
    private String budgetWithdrawe;
    @Column(name = "BUDGET_BALANCE")
    private String budgetBalance;
    @Column(name = "SUM_RECEIVE")
    private String sumReceive;
    @Column(name = "SUM_WITHDRAWE")
    private String sumWithdrawe;
    @Column(name = "SUM_BALANCE")
    private String sumBalance;
    @Column(name = "MONEY_BUDGET")
    private String moneyBudget;
    @Column(name = "MONEY_OUT")
    private String moneyOut;
    @Column(name = "AVERAGE_COST")
    private String averageCost;
    @Column(name = "AVERAGE_GIVE")
    private String averageGive;
    @Column(name = "AVERRAGE_FROM")
    private String averrageFrom;
    @Column(name = "AVERRATE_COME_COST")
    private String averrateComeCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getServiceReceive() {
        return serviceReceive;
    }

    public void setServiceReceive(String serviceReceive) {
        this.serviceReceive = serviceReceive;
    }

    public String getServiceWithdrawe() {
        return serviceWithdrawe;
    }

    public void setServiceWithdrawe(String serviceWithdrawe) {
        this.serviceWithdrawe = serviceWithdrawe;
    }

    public String getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(String serviceBalance) {
        this.serviceBalance = serviceBalance;
    }

    public String getSuppressReceive() {
        return suppressReceive;
    }

    public void setSuppressReceive(String suppressReceive) {
        this.suppressReceive = suppressReceive;
    }

    public String getSuppressWithdrawe() {
        return suppressWithdrawe;
    }

    public void setSuppressWithdrawe(String suppressWithdrawe) {
        this.suppressWithdrawe = suppressWithdrawe;
    }

    public String getSuppressBalance() {
        return suppressBalance;
    }

    public void setSuppressBalance(String suppressBalance) {
        this.suppressBalance = suppressBalance;
    }

    public String getBudgetReceive() {
        return budgetReceive;
    }

    public void setBudgetReceive(String budgetReceive) {
        this.budgetReceive = budgetReceive;
    }

    public String getBudgetWithdrawe() {
        return budgetWithdrawe;
    }

    public void setBudgetWithdrawe(String budgetWithdrawe) {
        this.budgetWithdrawe = budgetWithdrawe;
    }

    public String getBudgetBalance() {
        return budgetBalance;
    }

    public void setBudgetBalance(String budgetBalance) {
        this.budgetBalance = budgetBalance;
    }

    public String getSumReceive() {
        return sumReceive;
    }

    public void setSumReceive(String sumReceive) {
        this.sumReceive = sumReceive;
    }

    public String getSumWithdrawe() {
        return sumWithdrawe;
    }

    public void setSumWithdrawe(String sumWithdrawe) {
        this.sumWithdrawe = sumWithdrawe;
    }

    public String getSumBalance() {
        return sumBalance;
    }

    public void setSumBalance(String sumBalance) {
        this.sumBalance = sumBalance;
    }

    public String getMoneyBudget() {
        return moneyBudget;
    }

    public void setMoneyBudget(String moneyBudget) {
        this.moneyBudget = moneyBudget;
    }

    public String getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(String moneyOut) {
        this.moneyOut = moneyOut;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
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

    public String getAverrateComeCost() {
        return averrateComeCost;
    }

    public void setAverrateComeCost(String averrateComeCost) {
        this.averrateComeCost = averrateComeCost;
    }
}
