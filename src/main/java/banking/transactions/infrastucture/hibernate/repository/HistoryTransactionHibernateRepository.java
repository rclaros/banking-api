/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.infrastucture.hibernate.repository;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.customers.domain.entity.Customer;
import banking.transactions.application.dto.TransactionDto;
import banking.transactions.domain.repository.HistoryTransactionRepository;
import banking.transactions.entity.HistoryTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
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
        Query query = getSession().createSQLQuery(""
                + "  SELECT "
                + "	cu.bank_history_id AS id, "
                + "	origin.number originAccountNumber, "
                + "	destination.number destinationAccountNumber, "
                + "	cu.amount amount, "
                + "	cu.created create_time "
                + "FROM "
                + "	bank_transaction cu "
                + "INNER JOIN ( "
                + "	SELECT "
                + "		bank_history_id id "
                + "	FROM "
                + "		bank_transaction "
                + "	ORDER BY "
                + "		created desc "
                + "	LIMIT " + page + "," + pageSize + " "
                + ") AS t ON t.id = cu.bank_history_id "
                + "INNER JOIN bank_account origin on origin.bank_account_id=cu.account_origin_id "
                + "INNER JOIN bank_account destination on destination.bank_account_id=cu.account_destination_id")
                .addScalar("id", new IntegerType())
                .addScalar("originAccountNumber", new StringType())
                .addScalar("destinationAccountNumber", new StringType())
                .addScalar("amount", new BigDecimalType())
                .addScalar("create_time", new TimestampType())
                .setResultTransformer(Transformers.aliasToBean(TransactionDto.class));
        List<TransactionDto> transactions = query.list();
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
