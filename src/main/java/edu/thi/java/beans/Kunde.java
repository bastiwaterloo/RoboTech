package edu.thi.java.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Kunde implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private int kundenid;
    private String kundenart;
    private String name;
    private String vorname;
    private int plz;
    private String stadt;
    private String land;
    private String email;
    private String anfrage;
    private String date;
    
    public Kunde() {
        this.kundenid = 0;
        this.kundenart = "Geschäftskunde";
        this.name = "";
        this.vorname = "";
        this.plz = 0;
        this.stadt = "";
        this.land = "";
        this.email = "";
        this.anfrage = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.mm.yyyy");  
        this.date = dtf.format(LocalDateTime.now());
    }
    
    public Kunde(String kundenart, String name, String vorname, int plz, String stadt, String land, String email, String anfrage) {
        this.kundenid = (int)Math.ceil(Math.random()*10000);
        this.kundenart = kundenart;
        this.name = name;
        this.vorname = vorname;
        this.plz = plz;
        this.stadt = stadt;
        this.land = land;
        this.email = email;
        this.anfrage = anfrage;
    }
    
    
    // Getter und Setter

	public int getKundenid() {
		return kundenid;
	}

	public void setKundenid(int kundenid) {
		this.kundenid = kundenid;
	}

	public String getKundenart() {
		return kundenart;
	}

	public void setKundenart(String kundenart) {
		this.kundenart = kundenart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnfrage() {
		return anfrage;
	}

	public void setAnfrage(String anfrage) {
		this.anfrage = anfrage;
	}

}
