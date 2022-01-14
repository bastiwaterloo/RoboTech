package edu.thi.services.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.thi.jpa.beans.Customer;

/**
 * Serviceklasse um die Entität Customer herum
 * Bei Session EJBs wird die Threadverwaltung vom Container übernommen - daher kann hier der EntityManager direkt injiziert werden.
 * Verwendet EJB-Transaktionen --> obwohl EntityManager nicht threadSafe ist, kann er in EJBs verwendet werden,
 * da der Container auch das Transaktionsmanagement übernimmt!
 * --> Diese Klasse kann für JAX-WS und JAX-RS Webservices sowie Servlets und in Camunda verwendet werden!
 */
@Stateless
@LocalBean
public class CustomerServiceBean implements CustomerServiceBeanRemote, CustomerServiceBeanLocal {

    @PersistenceContext
    EntityManager em;
    

    public CustomerServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Boolean checkCustomer(String email) {
    	System.out.println("checking for customer with mail: " + email);
    	if(this.search(email) == null) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public Customer create(Customer customer) {
        em.persist(customer);
        return customer;
    }
    public Customer read(Long id) {
    	System.out.println("reading userdata...");
        return this.em.find(Customer.class, id);
    }

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

