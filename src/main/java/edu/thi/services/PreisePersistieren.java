package edu.thi.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PreisePersistieren implements JavaDelegate {
	
	@Override
    public void execute(DelegateExecution execution) throws Exception{
		
		/*
		 * Order einlesen aus BPMN
		 * 
		 * Status ändern
		 * 
		 * Order persistieren
		 * 
		 * 
		 */
		
	}

}
