package edu.thi.java.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.jpa.beans.Customer;

public class CreateOrderService implements JavaDelegate {

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
        execution.setVariable("customer", customer);
        System.out.println("We made it customer created!!!!");
        System.out.println("We made it customer created!!!!");
        System.out.println("We made it customer created!!!!");
        System.out.println("We made it customer created!!!!");
    }
}
