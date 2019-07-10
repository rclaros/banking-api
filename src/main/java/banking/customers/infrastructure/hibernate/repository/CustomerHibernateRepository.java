package banking.customers.infrastructure.hibernate.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

@Transactional
@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {

    public Customer get(long customerId) {
        Customer customer = null;
        customer = getSession().get(Customer.class, customerId);
        return customer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> get(int page, int pageSize) {
        Query query = getSession().createSQLQuery(""
                + "  SELECT "
                + "  cu.customer_id as id,"
                + "  cu.first_name firstName,"
                + "  cu.last_name lastName,"
                + "  cu.active isActive "
                + "FROM "
                + "	customer cu "
                + "INNER JOIN ( "
                + "	SELECT "
                + "	   customer_id as id "
                + "	FROM "
                + "	   customer "
                + "	ORDER BY "
                + "	   id ASC "
                + "	LIMIT " + page + "," + pageSize + " "
                + "     ) AS t "
                + "ON t.id = cu.customer_id "
                + "ORDER BY cu.customer_id ASC")
                .addScalar("id", new LongType())
                .addScalar("firstName", new StringType())
                .addScalar("lastName", new StringType())
                .addScalar("isActive", new BooleanType())
                .setResultTransformer(Transformers.aliasToBean(Customer.class));
        List<Customer> customers = query.list();
        return customers;
    }

    public Customer save(Customer customer) {
        return super.save(customer);
    }
}
