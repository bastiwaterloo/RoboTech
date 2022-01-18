package edu.thi.services.ejb;

/*
 * @Author Lukas Keßler
 * */
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import edu.thi.jpa.beans.Customer;

//ServiceBean to handle all customer DB operations
@Stateless
@LocalBean
public class CustomerServiceBean {

	@PersistenceContext
	EntityManager em;

	public CustomerServiceBean() {
		// TODO Auto-generated constructor stub
	}

	public Customer create(Customer customer) {
		em.persist(customer);
		return customer;
	}

	public Customer read(Long id) {
		System.out.println("reading userdata...");
		return this.em.find(Customer.class, id);
	}

	//find customer by email adress
	public Customer[] search(String email) {
		System.out.println("searching...");
		List<Customer> customers = null;
		String searchEmail = (email == null || email == "") ? "%" : "%" + email + "%";
		TypedQuery<Customer> query = em.createNamedQuery(Customer.searchCustomer, Customer.class);
		query.setParameter(1, searchEmail);
		customers = query.getResultList();
		Customer[] customerArray = new Customer[customers.size()];
		customerArray = customers.toArray(customerArray);
		return customerArray;
	}

	public void delete(Long id) {
		Customer customer = read(id);
		if (customer != null)
			this.em.remove(customer);
	}

	public void update(Customer customer) {
		this.em.merge(customer);
	}
}
