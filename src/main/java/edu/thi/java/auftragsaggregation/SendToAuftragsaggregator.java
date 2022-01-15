/*
 * 
 Autor dieser Klasse: Lukas Keßler
 *
 */

package edu.thi.java.auftragsaggregation;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.MessageCorrelationResultType;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import edu.thi.jpa.beans.Cart;

// Klasse sendet einen abgeschlossenen Auftrag an den Auftragsaggregator

public class SendToAuftragsaggregator implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Cart auftrag = (Cart) execution.getVariable("input_cart");
    	execution.setVariable("cart_info", auftrag);
        String status = auftrag.getStatus();
        
        
        //TODO Restliche Datenfelder hinzufügen
        
        
        Map<String, Object> auftragsdaten = new HashMap<String, Object>();
        auftragsdaten.put("current_auftrag", auftrag);
        
        //TODO Restliche Datenfelder hinzufügen

        
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        MessageCorrelationResult mcresult = runtimeService.createMessageCorrelation("NeuerAuftragMsg")
                                                            .processInstanceVariableEquals("form_status", status)
                                                            .setVariables(auftragsdaten)
                                                            .correlateWithResult();
        
        if (mcresult.getResultType() == MessageCorrelationResultType.Execution) {
            Execution exec = mcresult.getExecution();
            System.out.println("Waiting process with ProcessInstanceId " + exec.getProcessInstanceId() + " was continued!");
        }
        else {
            ProcessInstance processInstance = mcresult.getProcessInstance();
            System.out.println("New process with ProcessInstanceId " + processInstance.getProcessInstanceId() + " was started!");
        }
    }
}
