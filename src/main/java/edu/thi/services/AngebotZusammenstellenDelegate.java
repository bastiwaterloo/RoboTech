package edu.thi.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AngebotZusammenstellenDelegate implements JavaDelegate {
	
	@Override
    public void execute(DelegateExecution execution) throws Exception{
		
		/*
		 * -> Rabatt auslesen
		 * 
		 * -> Order einlesen aus BPMN
		 * 
		 * -> rabattpreis = order.spezifikationspreis * (rabatt/100);
		 * 
		 * -> rabattpreis in Order schreiben
		 * 
		 * -> auftragspreis = bruttopreis + sonderzuschlag - rabattpreis;
		 * 
		 * -> auftragspreis in Order schreiben
		 * 
		 * -> Order zurückgeben 
		 */
	}

}
