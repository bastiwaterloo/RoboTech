package edu.thi.services;

import java.util.ArrayList;
import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.java.beans.Order;
import edu.thi.java.servicetask.Long;
import edu.thi.java.servicetask.Override;
import edu.thi.java.servicetask.String;
import edu.thi.jpa.beans.Cart;

public class EinkaufshistorieBerechnenDelegate implements JavaDelegate {
	
	@Override
    public void execute(DelegateExecution execution) throws Exception {
		
		Long sum_of_purchases;
		
		//Einkaufshistorie aus DB laden!!!!!!!!!!!!!!!!!!
		
		// Order auslesen aus DB 
		
		ArrayList<Cart> carts = new ArrayList<Cart>();
		
		 for (Cart singleCart : carts) {
			 sum_of_purchases += singleCart.getAuftragspreis();
		 }
		 
		 execution.setVariable("einkaufshistorie", sum_of_purchases);
		 
		 
		 
		 // Order in BPMN speichern
		 
		
        
    }

}
