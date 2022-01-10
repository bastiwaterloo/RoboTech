package edu.thi.camundabpm.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import edu.thi.jpa.beans.Customer;
import edu.thi.services.ejb.CustomerServiceBean;

/**
 * Session Bean implementation class CustomerServiceDelegate
 */
@Stateless
@LocalBean
@Named
public class CustomerServiceDelegate implements CustomerServiceDelegateLocal {
    
	@Inject
    CustomerServiceBean customerService;    // Injizierte Service-EJB (Stateless)

    /**
     * Default constructor. 
     */
    public CustomerServiceDelegate() {
        // TODO Auto-generated constructor stub
    }

    public void createCustomer(DelegateExecution execution) {
    	System.out.println("creating customer....");
    	String kundenart = (String) execution.getVariable("formfield_kundenart");
    	String name = (String) execution.getVariable("formfield_name");
    	String vorname = (String) execution.getVariable("formfield_vorname");
    	Long plz = (Long) execution.getVariable("formfield_plz");
    	String stadt = (String) execution.getVariable("formfield_stadt");
    	String land = (String) execution.getVariable("formfield_land");
    	String email = (String) execution.getVariable("formfield_email");
    	String anfrage = (String) execution.getVariable("formfield_anfrage");
    	String eingangsdatum = (String) execution.getVariable("formfield_eingangsdatum");
    	String anmerkung = (String) execution.getVariable("formfield_auftragsdaten_anmerkungen");
    
        Customer customer = new Customer();
        
        customer.setKundenart(kundenart);
        customer.setNachname(name);
        customer.setVorname(vorname);
        customer.setPlz(plz);
        customer.setStadt(stadt);
        customer.setLand(land);
        customer.setEmail(email);
        
        customerService.create(customer);
        execution.setVariable("customer", customer);
    	System.out.println("customer: " + customer.getVorname() + " " + customer.getNachname());
        
    }
    
    public void createCustomer_test(DelegateExecution execution) {
    	
    	Customer customer = (Customer) execution.getVariable("customer");
    	customerService.create(customer);
    	execution.setVariable("customer", customer);
    	
    }
    
    public void read(DelegateExecution execution) {
        Long customerId = (Long) execution.getVariable("customerId");
        Customer customer = customerService.read(customerId);
        execution.setVariable("customer", customer);
    }
    
    public void delete(DelegateExecution execution) {
        Long customerId = (Long) execution.getVariable("customerId");
        customerService.delete(customerId);
    }
    
    public void search(DelegateExecution execution) {
        String email = (String) execution.getVariable("email");
        Customer[] customerList = customerService.search(email);
        execution.setVariable("customerList", customerList);
    }
    
    public void update(DelegateExecution execution) {
    	String kundenart = (String) execution.getVariable("formfield_kundenart");
    	String name = (String) execution.getVariable("formfield_name");
    	String vorname = (String) execution.getVariable("formfield_vorname");
    	Long plz = (Long) execution.getVariable("formfield_plz");
    	String stadt = (String) execution.getVariable("formfield_stadt");
    	String land = (String) execution.getVariable("formfield_land");
    	String email = (String) execution.getVariable("formfield_email");
    	String anfrage = (String) execution.getVariable("formfield_anfrage");
    	String eingangsdatum = (String) execution.getVariable("formfield_eingangsdatum");
    	String anmerkung = (String) execution.getVariable("formfield_auftragsdaten_anmerkungen");
    	Long customerId = (Long) execution.getVariable("customer_id");
        Customer customer = new Customer();
        customer.setCustomerid(customerId);
        customer.setKundenart(kundenart);
        customer.setNachname(name);
        customer.setVorname(vorname);
        customer.setPlz(plz);
        customer.setStadt(stadt);
        customer.setLand(land);
        customer.setEmail(email);
        customerService.update(customer);
        execution.setVariable("customer", customer);
    }

}
