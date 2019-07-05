/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.infrastucture.hibernate.repository;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.transactions.application.dto.HistoryTransactionDto;
import banking.transactions.domain.repository.HistoryTransactionRepository;
import banking.transactions.entity.HistoryTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RCLAROS
 */
@Transactional
@Repository
public class HistoryTransactionHibernateRepository extends BaseHibernateRepository<HistoryTransaction> implements HistoryTransactionRepository {

    @Override
    public List<HistoryTransactionDto> getTransactions(Date start_transaction, Date end_trasaction, int page, int pageSize) throws Exception {
        List<HistoryTransactionDto> transactions = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(HistoryTransaction.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(pageSize);
        List<HistoryTransaction> history_transactions = criteria.list();
        for (HistoryTransaction transactionDAO : history_transactions) {
            HistoryTransactionDto transaction = new HistoryTransactionDto();
            transaction.setId(transactionDAO.getId());
            transaction.setOriginAccountNumber(transactionDAO.getOrigin().getNumber());
            transaction.setDestinationAccountNumber(transactionDAO.getDestination().getNumber());
            transaction.setAmount(transactionDAO.getAmount());
            transaction.setCreate_time(transactionDAO.getCreated());
            transactions.add(transaction);
        }
        return transactions;
    }

}
