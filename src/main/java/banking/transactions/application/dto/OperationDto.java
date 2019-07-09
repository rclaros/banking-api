package banking.transactions.application.dto;

import banking.transactions.application.dto.deserializer.RequestBankOperationDtoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.util.Date;

@JsonDeserialize(using = RequestBankOperationDtoDeserializer.class)
public class OperationDto {

    private int id;
    private Date create_time;
    private String accountNumber;
    private BigDecimal amount;

    public OperationDto(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
