package edu.thi.java.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Auftrag implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private int auftragsID;
    private int kundenID;
    private String roboterart;
    private int menge;
    private double auftragspreis;
    private double rabattpreis;
    private boolean spezialdesign;
    private boolean erhoehterFertigungsaufwand;
    private boolean lautstaerkereduzierung;
    private boolean leichtbauweise;
    private boolean sonderzuschlag;
    private String status;
    private String spezifikation;
    private String datum;
    
    
    public Auftrag() {
        this.auftragsID = 0;
        this.kundenID = 0;
        this.roboterart = null;
        this.menge = 0;
        this.auftragspreis = 0;
        this.rabattpreis = 0;
        this.spezialdesign = false;
        this.erhoehterFertigungsaufwand = false;
        this.lautstaerkereduzierung = false;
        this.leichtbauweise = false;
        this.sonderzuschlag = false;
        this.status = null;
        this.spezifikation = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.mm.yyyy");  
        this.datum = dtf.format(LocalDateTime.now());
        		
        
        
    }
    
    public Auftrag(int auftragsID, String roboterart, int menge, String status) {
    	
    	this.auftragsID = (int)Math.ceil(Math.random()*1000000);
        this.roboterart = roboterart;
        this.menge = menge;
        this.status = status;
   	
    }
    
    public Auftrag(String roboterart, int menge, int auftragspreis, double angebotspreis, 
    		double rabattpreis, boolean spezialdesign, boolean erhoehterFertigungsaufwand,
    		boolean lautstaerkereduzierung, boolean leichtbauweise, boolean sonderzuschlag,
    		String status, String spezifikation) { 
   
    	
    	this.auftragsID = (int)Math.ceil(Math.random()*1000000);
        this.kundenID = (int)Math.ceil(Math.random()*1000000);
        //this.roboterart = (int)Math.ceil(Math.random()*1000000);
        this.menge = menge;
        this.auftragspreis = auftragspreis;
        this.rabattpreis = rabattpreis;
        this.spezialdesign = spezialdesign;
        this.erhoehterFertigungsaufwand = erhoehterFertigungsaufwand;
        this.lautstaerkereduzierung = lautstaerkereduzierung;
        this.leichtbauweise = leichtbauweise;
        this.sonderzuschlag = sonderzuschlag;
        this.status = status;
        this.spezifikation = spezifikation;
    	   
    }
    
    // Getter und Setter


	public int getAuftragsID() {
		return auftragsID;
	}

	public void setAuftragsID(int auftragsID) {
		this.auftragsID = auftragsID;
	}

	public int getKundenID() {
		return kundenID;
	}

	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
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

	public double getAuftragspreis() {
		return auftragspreis;
	}

	public void setAuftragspreis(double auftragspreis) {
		this.auftragspreis = auftragspreis;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpezifikation() {
		return spezifikation;
	}

	public void setSpezifikation(String spezifikation) {
		this.spezifikation = spezifikation;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
    	
}
