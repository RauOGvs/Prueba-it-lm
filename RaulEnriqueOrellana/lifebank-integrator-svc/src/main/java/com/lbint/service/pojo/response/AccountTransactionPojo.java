
package com.lbint.service.pojo.response;

import java.util.ArrayList;
import java.util.List;

public class AccountTransactionPojo {

    private String id;
    private String startDate;
    private String endDate;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
