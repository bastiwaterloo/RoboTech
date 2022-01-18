package edu.thi.java.maschinenevent;




public class FuellstandEvent {

    // Füllstand in %
    private int fuellstand;
   
    
    public FuellstandEvent(int fuellstand) {
        this.fuellstand = fuellstand;
    }


    public int getFuellstand() {
        return fuellstand;
    }
       
    @Override
    public String toString() {
        return "Füllstand-Event [" + fuellstand + "%]";
    }

}
