package edu.thi.webservice;

import javax.inject.Inject;
import javax.jws.WebService;

import edu.thi.jpa.beans.Robotertype;
import edu.thi.services.ejb.RobotertypeServiceBean;

/**
 * WebService implementation class CustomerServiceSOAP
 * Korrekte Implementierung über eine Session EJB, die sich dann um das Threadmanagement des EntityManagers kümmert.
 * Da Webservices nur einmalig instanziiert werden, müsste sonst mit jedem Aufruf explizit ein EntityManager
 * über die EntityManagerFactory erzeugt werden, da der EntityManager selbst nicht threadsafe ist.
 */
@WebService
public class RobotertypeServiceSOAP {

    @Inject
    RobotertypeServiceBean robotertypeService;
    
    public Robotertype create(String bezeichnung, Double preis) {
    	Robotertype type = new Robotertype();
    	type.setBezeichnung(bezeichnung);
    	type.setPreis(preis);
        return robotertypeService.create(type);
    }
    
    
    public Robotertype read(Long id) {
        return this.robotertypeService.read(id);
    }
    
    public Robotertype[] search(String bezeichnung) {
        return this.robotertypeService.search(bezeichnung);
    }
    
    public void update() {
    	Robotertype type = new Robotertype();
        this.robotertypeService.update(type);
    }
        
    public void delete(Long id) {
        this.robotertypeService.delete(id);
    }
}
