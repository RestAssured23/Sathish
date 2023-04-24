package MF_Pojo.Portfolio_Dashboard;

import java.util.ArrayList;

public class Data {

    public String id;
    public String name;
    public String type;
    public ArrayList<Content> contents;
    public int orderId;
    public double currentValueOfInvestment;
    public double investedAmount;
    public String currentValueOfInvestmentFormatted;
    public String investedAmountFormatted;
    public double gainAmount;
    public String gainAmountFormatted;
    public double gainPercentage;
    public double changeAmount;
    public String changeAmountFormatted;
    public String changePercentage;
    public String annualReturns;
    public ArrayList<String> actions;
    public double redeemedAmount;
    public double averageCost;
    public String averageCostFormatted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getCurrentValueOfInvestment() {
        return currentValueOfInvestment;
    }

    public void setCurrentValueOfInvestment(double currentValueOfInvestment) {
        this.currentValueOfInvestment = currentValueOfInvestment;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public String getCurrentValueOfInvestmentFormatted() {
        return currentValueOfInvestmentFormatted;
    }

    public void setCurrentValueOfInvestmentFormatted(String currentValueOfInvestmentFormatted) {
        this.currentValueOfInvestmentFormatted = currentValueOfInvestmentFormatted;
    }

    public String getInvestedAmountFormatted() {
        return investedAmountFormatted;
    }

    public void setInvestedAmountFormatted(String investedAmountFormatted) {
        this.investedAmountFormatted = investedAmountFormatted;
    }

    public double getGainAmount() {
        return gainAmount;
    }

    public void setGainAmount(double gainAmount) {
        this.gainAmount = gainAmount;
    }

    public String getGainAmountFormatted() {
        return gainAmountFormatted;
    }

    public void setGainAmountFormatted(String gainAmountFormatted) {
        this.gainAmountFormatted = gainAmountFormatted;
    }

    public double getGainPercentage() {
        return gainPercentage;
    }

    public void setGainPercentage(double gainPercentage) {
        this.gainPercentage = gainPercentage;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeAmountFormatted() {
        return changeAmountFormatted;
    }

    public void setChangeAmountFormatted(String changeAmountFormatted) {
        this.changeAmountFormatted = changeAmountFormatted;
    }

    public String getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(String changePercentage) {
        this.changePercentage = changePercentage;
    }

    public String getAnnualReturns() {
        return annualReturns;
    }

    public void setAnnualReturns(String annualReturns) {
        this.annualReturns = annualReturns;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public double getRedeemedAmount() {
        return redeemedAmount;
    }

    public void setRedeemedAmount(double redeemedAmount) {
        this.redeemedAmount = redeemedAmount;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public String getAverageCostFormatted() {
        return averageCostFormatted;
    }

    public void setAverageCostFormatted(String averageCostFormatted) {
        this.averageCostFormatted = averageCostFormatted;
    }


}
