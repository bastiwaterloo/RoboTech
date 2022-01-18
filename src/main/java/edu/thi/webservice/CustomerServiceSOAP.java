package edu.thi.webservice;
/*
 * 
 * @Author Dominik Knauer
 * 
 * */
import javax.inject.Inject;
import javax.jws.WebService;
import edu.thi.jpa.beans.Customer;
import edu.thi.services.ejb.CustomerServiceBean;

/*
This class is for requesting the CustomerServiceBean over a soap request
you can use those methods for communication with the database
-create
-read
-search
-update
-delete
*/

@WebService
public class CustomerServiceSOAP {

	@Inject
	CustomerServiceBean customerService;

	// this method creates a customer in the entity-table and returns it
	public Customer create(String kundenart, String vorname, String nachname, Long plz, String stadt, String land,
			String email) {
		Customer customer = new Customer();
		customer.setKundenart(kundenart);
		customer.setNachname(nachname);
		customer.setVorname(vorname);
		customer.setPlz(plz);
		customer.setStadt(stadt);
		customer.setLand(land);
		customer.setEmail(email);
		return this.customerService.create(customer);
	}

	// this method reads a customer out of the customer-table and returns it
	public Customer read(Long id) {
		return this.customerService.read(id);
	}

	// this method searchs a customer with its email adress and returns it
	public Customer[] search(String email) {
		return this.customerService.search(email);
	}

	// this method updates an existing customer in the database, if the customer
	// isnt inside of
	// the database, it will be created
	public void update(Long customerid, String kundenart, String vorname, String nachname, Long plz, String stadt,
			String land, String email) {
		Customer customer = new Customer();
		customer.setKundenart(kundenart);
		customer.setNachname(nachname);
		customer.setVorname(vorname);
		customer.setPlz(plz);
		customer.setStadt(stadt);
		customer.setLand(land);
		customer.setEmail(email);
		this.customerService.update(customer);
	}

	// this method deletes an existing customer in the entity
	public void delete(Long id) {
		this.customerService.delete(id);
	}
}
