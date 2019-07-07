/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.application.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author RCLAROS
 */
public class TransactionDto {

    private int id;
    private Date create_time;
    private String originAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(String originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
