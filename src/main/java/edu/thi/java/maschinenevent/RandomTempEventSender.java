/*
 * 
 * Klasse erstellt von: Lukas Keßler
 * 
 */

package edu.thi.java.maschinenevent;

import java.util.Date;
import java.util.Random;
import com.espertech.esper.client.EPRuntime;

/**
 * Sends random temperature events
 * Based on an article by Adrian Milne - http://www.adrianmilne.com/complex-event-processing-made-easy/
 */
public class RandomTempEventSender implements Runnable {
    /** Number of temperature events. */
    private static final long NUM_OF_TEMP_EVENTS = 1000;

    private EPRuntime epRuntime;

    public RandomTempEventSender(EPRuntime epRuntime) {
        this.epRuntime = epRuntime;
    }

	public void run() {
		// TODO Auto-generated method stub
        Random random = new Random();
        for (int i = 0; i < NUM_OF_TEMP_EVENTS; i++) {
        	TemperatureEvent tempEvent = new TemperatureEvent(random.nextInt(500), new Date());
        	System.out.println("RandomTempEventSender: " + tempEvent.getTemperature());
            epRuntime.sendEvent(tempEvent);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            	System.out.println("Thread Interrupted");
            	e.printStackTrace();
            }
        }
	}
}
