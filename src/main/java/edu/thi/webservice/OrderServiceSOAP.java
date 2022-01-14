package edu.thi.webservice;

import javax.inject.Inject;
import javax.jws.WebService;

import edu.thi.constants.enums.Status;
import edu.thi.jpa.beans.Cart;
import edu.thi.services.ejb.OrderServiceBean;

/**
 * WebService implementation class CustomerServiceSOAP
 * Korrekte Implementierung über eine Session EJB, die sich dann um das Threadmanagement des EntityManagers kümmert.
 * Da Webservices nur einmalig instanziiert werden, müsste sonst mit jedem Aufruf explizit ein EntityManager
 * über die EntityManagerFactory erzeugt werden, da der EntityManager selbst nicht threadsafe ist.
 */
@WebService
public class OrderServiceSOAP {

    @Inject
    OrderServiceBean orderService;
    
    public Cart create() {
    	Cart order = new Cart();
    	order.setKundenID(1L);
    	order.setRobotertypID(1L);
    	order.setMenge(1L);
    	order.setAuftragspreis(100.00);
    	order.setRabattpreis(50.00);
    	order.setSpezialdesign("special");
    	order.setErhoeterFertigungsaufwand(true);
    	order.setLautstaerkereduzierung(true);
    	order.setLeichtbauweise(true);
    	order.setSonderzuschlag(10.00);
    	order.setStatus(Status.EINGEGANGEN);
    	order.setSpezifikation("test");
    	System.out.println("creating order");
        return orderService.create(order);
    }
    
    public Cart read(Long id) {
        return this.orderService.read(id);
    }
    
    public Cart[] search(Long kundenId) {
        return this.orderService.search(kundenId);
    }
    
    public void update() {
    	Cart order = new Cart();
        
        this.orderService.update(order);
    }
        
    public void delete(Long id) {
        this.orderService.delete(id);
    }
}
