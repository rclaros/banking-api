package banking.transactions.application;

import banking.Translator;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.repository.HistoryTransactionRepository;
import banking.transactions.domain.service.TransferDomainService;
import banking.transactions.entity.HistoryTransaction;
import banking.utils.DateUtils;

@Service
public class TransactionApplicationService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransferDomainService transferDomainService;
    @Autowired
    private HistoryTransactionRepository historyRepository;

    @Transactional
    public void performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception {
        Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
        BankAccount destinationAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getToAccountNumber());
        this.transferDomainService.performTransfer(originAccount, destinationAccount, requestBankTransferDto.getAmount());
        this.bankAccountRepository.save(originAccount);
        this.bankAccountRepository.save(destinationAccount);
        HistoryTransaction history = new HistoryTransaction();
        history.setAmount(requestBankTransferDto.getAmount());
        history.setOrigin(originAccount);
        history.setDestination(destinationAccount);
        history.setCreated(DateUtils.getCurrentDate());
        this.historyRepository.save(history);
    }

    @Transactional
    public void performTransferDeposit(RequestBankTransferDto requestBankTransferDto) throws Exception {
        Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
        this.transferDomainService.depositMoney(originAccount, requestBankTransferDto.getAmount());
        this.bankAccountRepository.save(originAccount);
    }

    @Transactional
    public void performTransferWithdraw(RequestBankTransferDto requestBankTransferDto) throws Exception {
        Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
        this.transferDomainService.withdrawMoney(originAccount, requestBankTransferDto.getAmount());
        this.bankAccountRepository.save(originAccount);
    }

    private Notification validation(RequestBankTransferDto requestBankTransferDto) {
        Notification notification = new Notification();
        if (requestBankTransferDto == null || requestBankTransferDto.getFromAccountNumber().equals(RequestBodyType.INVALID.toString())) {
            notification.addError(Translator.toLocale("message.json.parse"));
        }
        return notification;
    }
}
