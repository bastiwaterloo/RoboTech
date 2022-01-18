package edu.thi.camundabpm.ejb;
/*
 * @Author Lukas Kessler
 * */
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;


import edu.thi.jpa.beans.Robotertype;
import edu.thi.services.ejb.RobotertypeServiceBean;

/**
 * Session Bean implementation class CustomerServiceDelegate
 */
@Stateless
@LocalBean
@Named
public class RobotertypeServiceDelegate {
    
	@Inject
    RobotertypeServiceBean robotertypeService; // Injizierte Service-EJB (Stateless)

    /**
     * Default constructor. 
     */
    public RobotertypeServiceDelegate() {
        // TODO Auto-generated constructor stub
    }

    public void createRobotertype(DelegateExecution execution) {
    	System.out.println("creating order....");
    	Robotertype type = new Robotertype();
    	type.setBezeichnung("test");
    	type.setPreis(99.99);
    	robotertypeService.create(type);
    }
    
    public Robotertype find(String bezeichnung) {
    	return robotertypeService.find(bezeichnung);
    }
    
    public void createRobotertype_test(DelegateExecution execution) {
    	
    	System.out.println("creating order....");
    	
    }
    
    public void read(DelegateExecution execution) {
        Robotertype type = robotertypeService.read(45L);
        execution.setVariable("robotertype", type);
    }
    
    public void delete(DelegateExecution execution) {
    	System.out.println("delete order....");
    }
    
    public void search(DelegateExecution execution) {
    	System.out.println("search order....");
    }
    
    public void update(DelegateExecution execution) {
    	System.out.println("update order....");
    }

}
