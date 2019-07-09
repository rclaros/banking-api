/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.infrastucture.hibernate.repository;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.transactions.application.dto.TransactionDto;
import banking.transactions.domain.repository.HistoryTransactionRepository;
import banking.transactions.entity.HistoryTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public List<TransactionDto> getTransactions(Date start_transaction, Date end_trasaction, int page, int pageSize) throws Exception {
        List<TransactionDto> transactions = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(HistoryTransaction.class, "c");
        if (start_transaction != null) {
            criteria.add(Restrictions.gt("c.created", start_transaction));
        }
        if (end_trasaction != null) {
            criteria.add(Restrictions.gt("c.created", end_trasaction));
        }
        criteria.addOrder(Order.asc("c.created"));
        criteria.setFirstResult(page);
        criteria.setMaxResults(pageSize);
        List<HistoryTransaction> history_transactions = criteria.list();
        for (HistoryTransaction transactionDAO : history_transactions) {
            TransactionDto transaction = new TransactionDto();
            transaction.setId(transactionDAO.getId());
            transaction.setOriginAccountNumber(transactionDAO.getOrigin().getNumber());
            transaction.setDestinationAccountNumber(transactionDAO.getDestination().getNumber());
            transaction.setAmount(transactionDAO.getAmount());
            transaction.setCreate_time(transactionDAO.getCreated());
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public List<TransactionDto> getTransactionsByAccount(Date start_transaction, Date end_trasaction, long acountId, int page, int pageSize) throws Exception {
        List<TransactionDto> transactions = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(HistoryTransaction.class, "c");
        criteria.createAlias("c.origin", "or");
        criteria.add(Restrictions.eq("or.id", acountId));

        if (start_transaction != null) {
            criteria.add(Restrictions.gt("c.created", start_transaction));
        }
        if (end_trasaction != null) {
            criteria.add(Restrictions.gt("c.created", end_trasaction));
        }
        criteria.addOrder(Order.asc("c.created"));
        criteria.setFirstResult(page);
        criteria.setMaxResults(pageSize);
        List<HistoryTransaction> history_transactions = criteria.list();
        for (HistoryTransaction transactionDAO : history_transactions) {
            TransactionDto transaction = new TransactionDto();
            transaction.setId(transactionDAO.getId());
            transaction.setOriginAccountNumber(transactionDAO.getOrigin().getNumber());
            transaction.setDestinationAccountNumber(transactionDAO.getDestination().getNumber());
            transaction.setAmount(transactionDAO.getAmount());
            transaction.setCreate_time(transactionDAO.getCreated());
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public List<TransactionDto> getTransactionsByCustomer(Date start_transaction, Date end_trasaction, long customerId, int page, int pageSize) throws Exception {
        List<TransactionDto> transactions = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(HistoryTransaction.class, "c");
        criteria.createAlias("c.origin", "or");
        criteria.createAlias("or.customer", "cu");
        criteria.add(Restrictions.eq("cu.id", customerId));
        if (start_transaction != null) {
            criteria.add(Restrictions.gt("c.created", start_transaction));
        }
        if (end_trasaction != null) {
            criteria.add(Restrictions.gt("c.created", end_trasaction));
        }
        criteria.addOrder(Order.asc("c.created"));
        criteria.setFirstResult(page);
        criteria.setMaxResults(pageSize);
        List<HistoryTransaction> history_transactions = criteria.list();
        for (HistoryTransaction transactionDAO : history_transactions) {
            TransactionDto transaction = new TransactionDto();
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
