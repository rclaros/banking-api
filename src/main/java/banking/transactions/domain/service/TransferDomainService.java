package banking.transactions.domain.service;

import banking.Translator;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.common.application.Notification;

@Service
public class TransferDomainService {

    public void performTransfer(BankAccount originAccount, BankAccount destinationAccount, BigDecimal amount)
            throws IllegalArgumentException {
        Notification notification = this.validation(originAccount, destinationAccount, amount);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        originAccount.withdrawMoney(amount);
        destinationAccount.depositMoney(amount);
    }

    public void withdrawMoney(BankAccount originAccount, BigDecimal amount)
            throws IllegalArgumentException {
        Notification notification = this.validation(originAccount, amount);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        originAccount.withdrawMoney(amount);
    }

    public void depositMoney(BankAccount originAccount, BigDecimal amount)
            throws IllegalArgumentException {
        Notification notification = this.validation(originAccount, amount);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        originAccount.depositMoney(amount);
    }

    private Notification validation(BankAccount originAccount, BankAccount destinationAccount, BigDecimal amount) {
        Notification notification = new Notification();
        this.validateAmount(notification, amount);
        this.validateBankAccount(notification, originAccount);
        return notification;
    }

    private Notification validation(BankAccount originAccount, BigDecimal amount) {
        Notification notification = new Notification();
        this.validateAmount(notification, amount);
        this.validateBankAccount(notification, originAccount);
        return notification;
    }

    private void validateAmount(Notification notification, BigDecimal amount) {
        if (amount == null) {
            notification.addError(Translator.toLocale("message.account.missing"));
            return;
        }
        if (amount.signum() <= 0) {
            notification.addError(Translator.toLocale("message.account.less"));
        }
    }

    private void validateBankAccounts(Notification notification, BankAccount originAccount, BankAccount destinationAccount) {
        if (originAccount == null || destinationAccount == null) {
            notification.addError(Translator.toLocale("message.account.incomplete"));
            return;
        }
        if (originAccount.getNumber().equals(destinationAccount.getNumber())) {
            notification.addError(Translator.toLocale("message.account.some"));
        }
    }

    private void validateBankAccount(Notification notification, BankAccount originAccount) {
        if (originAccount == null) {
            notification.addError(Translator.toLocale("message.account.incomplete"));
            return;
        }
    }
}
