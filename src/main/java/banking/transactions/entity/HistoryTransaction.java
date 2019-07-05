/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.entity;

import banking.accounts.domain.entity.BankAccount;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author RCLAROS
 */
public class HistoryTransaction {

    private int id;
    private BankAccount origin;
    private BankAccount destination;
    private BigDecimal amount;
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BankAccount getOrigin() {
        return origin;
    }

    public void setOrigin(BankAccount origin) {
        this.origin = origin;
    }

    public BankAccount getDestination() {
        return destination;
    }

    public void setDestination(BankAccount destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
