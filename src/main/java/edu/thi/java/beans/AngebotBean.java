package edu.thi.java.beans;

import java.io.Serializable;

public class AngebotBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private int angebotsID;
    private String roboterart;
    private int menge;
    private double angebotspreis;
    private double rabattpreis;
    private boolean spezialdesign;
    private boolean erhoehterFertigungsaufwand;
    private boolean lautstaerkereduzierung;
    private boolean leichtbauweise;
    private boolean sonderzuschlag;
    
    
    public AngebotBean() {
        this.angebotsID = 0;
        this.roboterart = null;
        this.menge = 0;
        this.angebotspreis = 0;
        this.rabattpreis = 0;
        this.spezialdesign = false;
        this.erhoehterFertigungsaufwand = false;
        this.lautstaerkereduzierung = false;
        this.leichtbauweise = false;
        this.sonderzuschlag = false;
        
    }
    
    public AngebotBean(String roboterart, int menge, double angebotspreis, 
    		double rabattpreis, boolean spezialdesign, boolean erhoehterFertigungsaufwand,
    		boolean lautstaerkereduzierung, boolean leichtbauweise, boolean sonderzuschlag) { 
    	
        this.angebotsID = (int)Math.ceil(Math.random()*1000000);
        this.roboterart = roboterart;
        this.menge = menge;
        this.angebotspreis = angebotspreis;
        this.rabattpreis = rabattpreis;
        this.spezialdesign = spezialdesign;
        this.erhoehterFertigungsaufwand = erhoehterFertigungsaufwand;
        this.lautstaerkereduzierung = lautstaerkereduzierung;
        this.leichtbauweise = leichtbauweise;
        this.sonderzuschlag = sonderzuschlag;
        
    }
    
    // Getter und Setter

	public int getAngebotsID() {
		return angebotsID;
	}

	public void setAngebotsID(int angebotsID) {
		this.angebotsID = angebotsID;
	}

	public String getRoboterart() {
		return roboterart;
	}

	public void setRoboterart(String roboterart) {
		this.roboterart = roboterart;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public double getAngebotspreis() {
		return angebotspreis;
	}

	public void setAngebotspreis(double angebotspreis) {
		this.angebotspreis = angebotspreis;
	}

	public double getRabattpreis() {
		return rabattpreis;
	}

	public void setRabattpreis(double rabattpreis) {
		this.rabattpreis = rabattpreis;
	}

	public boolean isSpezialdesign() {
		return spezialdesign;
	}

	public void setSpezialdesign(boolean spezialdesign) {
		this.spezialdesign = spezialdesign;
	}

	public boolean isErhoehterFertigungsaufwand() {
		return erhoehterFertigungsaufwand;
	}

	public void setErhoehterFertigungsaufwand(boolean erhoehterFertigungsaufwand) {
		this.erhoehterFertigungsaufwand = erhoehterFertigungsaufwand;
	}

	public boolean isLautstaerkereduzierung() {
		return lautstaerkereduzierung;
	}

	public void setLautstaerkereduzierung(boolean lautstaerkereduzierung) {
		this.lautstaerkereduzierung = lautstaerkereduzierung;
	}

	public boolean isLeichtbauweise() {
		return leichtbauweise;
	}

	public void setLeichtbauweise(boolean leichtbauweise) {
		this.leichtbauweise = leichtbauweise;
	}

	public boolean isSonderzuschlag() {
		return sonderzuschlag;
	}

	public void setSonderzuschlag(boolean sonderzuschlag) {
		this.sonderzuschlag = sonderzuschlag;
	}
    	
}
