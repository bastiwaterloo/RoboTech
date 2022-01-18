package edu.thi.java.maschinenevent;

import java.util.Random;
import com.espertech.esper.client.EPRuntime;



public class RandomFuellstandEventSender implements Runnable {
    
    private static final long NUM_OF_FUEL_EVENTS = 1000;

    private EPRuntime epRuntime;

    public RandomFuellstandEventSender(EPRuntime epRuntime) {
        this.epRuntime = epRuntime;
    }

	public void run() {
		// TODO Auto-generated method stub
		int fuellstand1 = 100;
		int fuellstand2 = 100;
		int fuellstand3 = 100;

        Random random = new Random();
        for (int i = 0; i < NUM_OF_FUEL_EVENTS; i++) {
        	
        	fuellstand1 = fuellstand1 - random.nextInt(5);
        	fuellstand2 = fuellstand2 - random.nextInt(5);
        	fuellstand3 = fuellstand3 - random.nextInt(5);
        	
        	if(fuellstand1 < 0) {
        		fuellstand1 = 0;
        	}
        	if(fuellstand2 < 0) {
        		fuellstand2 = 0;
        	}
        	if(fuellstand3 < 0) {
        		fuellstand3 = 0;
        	}
        	        	
                	
        	FuellstandEvent fuellstandEvent1 = new FuellstandEvent(fuellstand1);
        	FuellstandEvent fuellstandEvent2 = new FuellstandEvent(fuellstand2);
        	FuellstandEvent fuellstandEvent3 = new FuellstandEvent(fuellstand3);

          	System.out.println("Füllstand Maschine 1: " + fuellstandEvent1.getFuellstand());
            System.out.println("Füllstand Maschine 2: " + fuellstandEvent2.getFuellstand());
            System.out.println("Füllstand Maschine 3: " + fuellstandEvent3.getFuellstand() + "\n");
            
            
            
            if (fuellstand1 < 20 && fuellstand2 < 20 && fuellstand3 < 20) {
            	epRuntime.sendEvent(fuellstandEvent1);
                epRuntime.sendEvent(fuellstandEvent2);
                epRuntime.sendEvent(fuellstandEvent3);
            	break;
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            	System.out.println("Thread Interrupted");
            	e.printStackTrace();
            }
        }
	}
}
