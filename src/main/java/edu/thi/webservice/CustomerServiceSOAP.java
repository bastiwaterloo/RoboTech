package edu.thi.webservice;

import javax.inject.Inject;
import javax.jws.WebService;

import edu.thi.jpa.beans.Customer;
import edu.thi.services.ejb.CustomerServiceBean;

/**
 * WebService implementation class CustomerServiceSOAP
 * Korrekte Implementierung über eine Session EJB, die sich dann um das Threadmanagement des EntityManagers kümmert.
 * Da Webservices nur einmalig instanziiert werden, müsste sonst mit jedem Aufruf explizit ein EntityManager
 * über die EntityManagerFactory erzeugt werden, da der EntityManager selbst nicht threadsafe ist.
 */
@WebService
public class CustomerServiceSOAP {

    @Inject
    CustomerServiceBean customerService;
    
    public Customer create(String kundenart, String vorname, String nachname, Long plz, String stadt, String land, String email) {
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
    
    public Customer read(Long id) {
        return this.customerService.read(id);
    }
    
    public Customer[] search(String email) {
        return this.customerService.search(email);
    }
    
    public void update(Long customerid, String kundenart, String vorname, String nachname, Long plz, String stadt, String land, String email) {
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
        
    public void delete(Long id) {
        this.customerService.delete(id);
    }
}
