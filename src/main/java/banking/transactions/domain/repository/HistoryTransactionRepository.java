/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.domain.repository;

import banking.transactions.application.dto.TransactionDto;
import banking.transactions.entity.HistoryTransaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RCLAROS
 */
public interface HistoryTransactionRepository {

    HistoryTransaction save(HistoryTransaction historyTransaction) throws Exception;
    
    List<TransactionDto> getTransactionsByAccount(Date start_transaction, Date end_trasaction,long acountId, int page, int pageSize) throws Exception;
    
    List<TransactionDto> getTransactionsByCustomer(Date start_transaction, Date end_trasaction,long customerId, int page, int pageSize) throws Exception;

    List<TransactionDto> getTransactions(Date start_transaction, Date end_trasaction, int page, int pageSize) throws Exception;
}
