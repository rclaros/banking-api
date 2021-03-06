/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.application;

import banking.transactions.application.dto.TransactionDto;
import banking.transactions.domain.repository.HistoryTransactionRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RCLAROS
 */
@Service
public class HistoryApplicationService {

    @Autowired
    private HistoryTransactionRepository transactionRepository;

    public List<TransactionDto> getTransactions(Date start_transaction, Date end_trasaction, int page, int pageSize) throws Exception {
        return transactionRepository.getTransactions(start_transaction, end_trasaction, page, pageSize);
    }
    public List<TransactionDto> getTransactionsByAccount(Date start_transaction, Date end_trasaction,long accountId, int page, int pageSize) throws Exception {
        return transactionRepository.getTransactions(start_transaction, end_trasaction, page, pageSize);
    }
    public List<TransactionDto> getTransactionsByCustomer(Date start_transaction, Date end_trasaction,long customerId, int page, int pageSize) throws Exception {
        return transactionRepository.getTransactions(start_transaction, end_trasaction, page, pageSize);
    }

}
