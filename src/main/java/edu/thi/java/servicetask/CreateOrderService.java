package edu.thi.java.servicetask;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.constants.enums.Status;
import edu.thi.jpa.beans.Cart;

public class CreateOrderService implements JavaDelegate{

	@Override
	 public void execute(DelegateExecution execution) throws Exception {
    	Long robotertypID = 1L;
    	Long kundenID = 1L;
    	Long menge = 1L;
    	Long auftragspreis = (long) 99.99;
    	Long rabattpreis = (long) 59.99;
    	Boolean spezialdesign = (Boolean) execution.getVariable("formfield_anfrage");
    	Boolean erhoehterFertigungsaufwand = false;
    	Boolean lautstaerkereduzierung = false;
    	Boolean leichtbauweise = false;
    	Boolean sonderzuschlag = false;
    	Status status = Status.EINGEGANGEN;
    	String spezifikation = "";
    	
    	Cart cart = new Cart();
    	cart.setKundenID(kundenID);
    	cart.setRobotertypID(robotertypID);
    	cart.setMenge(menge);
    	cart.setAuftragspreis(auftragspreis);
    	cart.setRabattpreis(rabattpreis);
    	cart.setSpezialdesign(spezialdesign);
    	cart.setErhoeterFertigungsaufwand(erhoehterFertigungsaufwand);
    	cart.setLautstaerkereduzierung(lautstaerkereduzierung);
    	cart.setLeichtbauweise(leichtbauweise);
    	cart.setSonderzuschlag(sonderzuschlag);
    	cart.setStatus(Status.EINGEGANGEN);
    	cart.setSpezifikation(spezifikation);;
    	
        execution.setVariable("cart", cart);
        System.out.println("order created !!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
}
