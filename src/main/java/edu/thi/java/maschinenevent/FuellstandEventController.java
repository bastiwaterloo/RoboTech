package edu.thi.java.maschinenevent;



import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


/**
 * Main class to kick off the scenario
 * Based on an article by Adrian Milne - http://www.adrianmilne.com/complex-event-processing-made-easy/
 */
public class FuellstandEventController {

    /** Esper service */
    private static EPServiceProvider epService;
    private static EPStatement criticalEventStatement;
    private static StatementSubscriber criticalEventSubscriber;


	public static void main(String[] args) {
        // Create Esper instance
        epService = EPServiceProviderManager.getDefaultProvider();
        
        // Add TemperatureEvent
        epService.getEPAdministrator().getConfiguration().addEventType(FuellstandEvent.class);

        
        // Wird ausgelöst, wenn der Füllstand aller Maschinen unter 20% sinkt
        criticalEventSubscriber = new CriticalEventSubscriber();
        criticalEventStatement = epService.getEPAdministrator().createEPL(criticalEventSubscriber.getStatement());
        criticalEventStatement.setSubscriber(criticalEventSubscriber);

        // Start event sender
        System.out.println("Starting FuelEventSender-Thread");
        Thread evtSenderThread = new Thread(new RandomFuellstandEventSender(epService.getEPRuntime()));
        evtSenderThread.start();
	}
}