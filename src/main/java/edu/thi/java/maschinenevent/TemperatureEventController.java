/*
 * 
 * Klasse erstellt von: Lukas Keßler
 * 
 */

package edu.thi.java.maschinenevent;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


/**
 * Main class to kick off the scenario
 * Based on an article by Adrian Milne - http://www.adrianmilne.com/complex-event-processing-made-easy/
 */
public class TemperatureEventController {

    /** Esper service */
    private static EPServiceProvider epService;
    private static EPStatement criticalEventStatement;
    private static EPStatement warningEventStatement;
    private static EPStatement monitorEventStatement;
    private static StatementSubscriber criticalEventSubscriber;
    //private static StatementSubscriber warningEventSubscriber;
    //private static StatementSubscriber monitorEventSubscriber;

	public static void main(String[] args) {
        // Create Esper instance
        epService = EPServiceProviderManager.getDefaultProvider();
        
        // Add TemperatureEvent
        epService.getEPAdministrator().getConfiguration().addEventType(TemperatureEvent.class);

        // Setting up the system
        // EPL to monitor the average temperature every 5 seconds. Will call listener on every event.
        /*
        monitorEventSubscriber = new MonitorEventSubscriber();
        monitorEventStatement = epService.getEPAdministrator().createEPL(monitorEventSubscriber.getStatement());
        monitorEventStatement.setSubscriber(monitorEventSubscriber);
        */
        
        // EPL to check for 2 consecutive Temperature events over the threshold - if matched, will alert
        // listener.
        /*
        warningEventSubscriber = new WarningEventSubscriber();
        warningEventStatement = epService.getEPAdministrator().createEPL(warningEventSubscriber.getStatement());
        warningEventStatement.setSubscriber(warningEventSubscriber);
        */
        
        // EPL to check for a sudden critical rise across 4 events, where the last event is 1.5x greater
        // than the first event. This is checking for a sudden, sustained escalating rise in the
        // temperature
        criticalEventSubscriber = new CriticalEventSubscriber();
        criticalEventStatement = epService.getEPAdministrator().createEPL(criticalEventSubscriber.getStatement());
        criticalEventStatement.setSubscriber(criticalEventSubscriber);

        // Start event sender
        System.out.println("Starting TempEventSender-Thread");
        Thread evtSenderThread = new Thread(new RandomTempEventSender(epService.getEPRuntime()));
        evtSenderThread.start();
	}
}