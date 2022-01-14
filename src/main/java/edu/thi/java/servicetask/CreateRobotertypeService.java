package edu.thi.java.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateRobotertypeService implements JavaDelegate{

	@Override
	 public void execute(DelegateExecution execution) throws Exception {
    	
        System.out.println("type created !!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
}
