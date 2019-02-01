package edu.ivan.decl.entity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;

public class Transport {
    private Contract contract;
    private ITN itn;
    private String route;
    private String actDate;
    private Double profit;

    public Transport() {
    }

    public Transport(Contract contract, ITN itn, String route, String actDate, Double profit) {
        this.contract = contract;
        this.itn = itn;
        this.route = route;
        this.actDate = actDate;
        this.profit = profit;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ITN getItn() {
        return itn;
    }

    public void setItn(ITN itn) {
        this.itn = itn;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
