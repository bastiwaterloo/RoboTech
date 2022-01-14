/*
 * 
 Autor dieser Klasse: Lukas Keßler
 *
 */

package edu.thi.java.auftragsaggregation;

import java.util.ArrayList;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.java.beans.Auftrag;

public class AddAuftragToListe implements JavaDelegate{ 
	

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long auftragsID = (Long) execution.getVariable("form_auftragsID");
        String roboterart = (String) execution.getVariable("form_roboterart");
        Long menge = (Long) execution.getVariable("form_menge");
        String status = (String) execution.getVariable("form_status");
        
        Auftrag auftrag = new Auftrag(auftragsID.intValue(), roboterart, menge.intValue(), status);

        @SuppressWarnings("unchecked")
        ArrayList<Auftrag> auftragsliste = (ArrayList<Auftrag>) execution.getVariable("auftragsliste");
        if (auftragsliste == null) {
        	auftragsliste = new ArrayList<Auftrag>();
        }
        
        auftragsliste.add(auftrag);
        execution.setVariable("auftragsliste", auftragsliste);
        
        
        
        if(auftragsliste.size() == 1) {
        	int auftragscounter = 0;
        	for (int i=0; i< auftragsliste.size(); i++) {        	
            	Auftrag auftr = auftragsliste.get(i);
            	auftragscounter += auftr.getMenge();
            }
                              
            execution.setVariable("auftragscounter", auftragscounter);
        }
        else {
        	int auftragscounter = (Integer) execution.getVariable("auftragscounter");
        	for (int i=0; i< auftragsliste.size(); i++) {        	
            	Auftrag auftr = auftragsliste.get(i);
            	auftragscounter += auftr.getMenge();
            }
                              
            execution.setVariable("auftragscounter", auftragscounter);
        }
      
       
    }
}
