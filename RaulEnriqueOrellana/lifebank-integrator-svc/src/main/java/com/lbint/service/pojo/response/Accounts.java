
package com.lbint.service.pojo.response;

import java.util.ArrayList;
import java.util.List;

public class Accounts {

    private List<Loan> loan = new ArrayList<Loan>();
    private List<CreditCard> creditCard = new ArrayList<CreditCard>();
    private List<Personal> personal = new ArrayList<Personal>();

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(List<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

    public List<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Personal> personal) {
        this.personal = personal;
    }

}
