/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.domain.repository;

import banking.transactions.application.dto.HistoryTransactionDto;
import banking.transactions.entity.HistoryTransaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RCLAROS
 */
public interface HistoryTransactionRepository {

    HistoryTransaction save(HistoryTransaction historyTransaction) throws Exception;

    List<HistoryTransactionDto> getTransactions(Date start_transaction, Date end_trasaction, int page, int pageSize) throws Exception;
}
