package edu.thi.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ModifikationspreisBerechnenDelegate implements JavaDelegate {
	
	@Override
    public void execute(DelegateExecution execution) throws Exception{
		/*
		 * 
		 * ->Order über BPMN übergeben und Modifikationen auslesen
		 *   
		 * -> Roboterpreise aus Order-DB auslesen 
		 *
		 *   
		 * ->XML-Variablen aus BPMN auslesen evtln. in einer Map
		 * 
		 * -> bruttoPreis = menge * Roboterpreis
		 * 
		 * 
		 *  if abfragen oder switch case
		 *  
		 * ->for(modifikation : modifikationen){
		 *   	
		 *   	sum_modifikation += Map.get(modifikation);
		 *   
		 *   }
		 *   
		 * 
		 * -> Spezifikationspreis =  bruttopreis * (sum_modifikation/100);
		 * 
		 * -> bruttoPreis und spezifikationspreis in Order schreiben
		 * 
		 * -> Order zurückgeben in BPMN
		 * 
		 */
	}

}
