package edu.thi.java.servicetask;

import java.sql.Date;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;

import edu.thi.demo.robotech_process_jpa.Customer;
import edu.thi.java.beans.CustomerBean;


public class SetCustomerData implements JavaDelegate{

	  @Override
	    public void execute(DelegateExecution execution) throws Exception {
		  	String kundenart = (String) execution.getVariable("formfield_kundenart");
		  	String name = (String) execution.getVariable("formfield_name");
		  	String vorname = (String) execution.getVariable("formfield_vorname");
		  	Long plz = (Long) execution.getVariable("formfield_plz");
		  	String stadt = (String) execution.getVariable("formfield_stadt");
		  	String land = (String) execution.getVariable("formfield_land");
		  	String email = (String) execution.getVariable("formfield_email");
		  	String anfrage = (String) execution.getVariable("formfield_anfrage");
		  	Date eingangsdatum = (Date) execution.getVariable("formfield_eingangsdatum");
		  
		  
	        CustomerBean customerBean = new CustomerBean(kundenart,name,vorname,plz,stadt,land,email,anfrage);
	        Customer customer = new Customer();
	        customer.setCustomerid(1L);
	        customer.setLastname(name);
	        customer.setFirstname(vorname);
	        customer.setEmail(email);
	        
	        System.out.print(customerBean.getName());
	        System.out.println(customerBean.getVorname());
	        
	        System.out.println(Customer.searchCustomer);
	    }
	  
}
