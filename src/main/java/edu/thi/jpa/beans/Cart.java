package edu.thi.jpa.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import edu.thi.constants.enums.Status;

@Entity
@NamedQuery(name = Cart.searchCart, query = "SELECT c FROM Cart c WHERE c.auftragsID = ?1")
@Table(name = "cart")
public class Cart implements Serializable {
	public final static String searchCart = "Cart.searchCart";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long auftragsID;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kundenid")
    private Customer customer;
	
	private Long robotertypID;
	private Long menge;
	private Double auftragspreis;
	private Double rabattpreis;
	private Double bruttopreis;
	private Boolean spezialdesign;
	private Boolean erhoeterFertigungsaufwand;
	private Boolean lautstaerkereduzierung;
	private Boolean leichtbauweise;
	private Boolean sonderzuschlag;
	private String status;
	private String spezifikation;
	
	//TODO insert date of order

	public Long getAuftragsID() {
		return auftragsID;
	}


	

	public Customer getCustomer() {
		return customer;
	}


	


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




	public Long getKundenID() {
		return customer.getCustomerid();
	}




	public void setKundenID(Long kundenID) {
		customer.setCustomerid(kundenID);
	}




	public Long getRobotertypID() {
		return robotertypID;
	}




	public void setRobotertypID(Long robotertypID) {
		this.robotertypID = robotertypID;
	}




	public Long getMenge() {
		return menge;
	}




	public void setMenge(Long menge) {
		this.menge = menge;
	}




	public Double getAuftragspreis() {
		return auftragspreis;
	}




	public void setAuftragspreis(Double auftragspreis) {
		this.auftragspreis = auftragspreis;
	}


	public Double getBurttopreis() {
		return bruttopreis;
	}



	public void setBurttopreis(Double burttopreis) {
		this.bruttopreis = burttopreis;
	}



	public Double getRabattpreis() {
		return rabattpreis;
	}




	public void setRabattpreis(Double rabattpreis) {
		this.rabattpreis = rabattpreis;
	}




	public Boolean getSpezialdesign() {
		return spezialdesign;
	}




	public void setSpezialdesign(Boolean spezialdesign) {
		this.spezialdesign = spezialdesign;
	}




	public Boolean getErhoeterFertigungsaufwand() {
		return erhoeterFertigungsaufwand;
	}




	public void setErhoeterFertigungsaufwand(Boolean erhoeterFertigungsaufwand) {
		this.erhoeterFertigungsaufwand = erhoeterFertigungsaufwand;
	}




	public Boolean getLautstaerkereduzierung() {
		return lautstaerkereduzierung;
	}




	public void setLautstaerkereduzierung(Boolean lautstaerkereduzierung) {
		this.lautstaerkereduzierung = lautstaerkereduzierung;
	}




	public Boolean getLeichtbauweise() {
		return leichtbauweise;
	}




	public void setLeichtbauweise(Boolean leichtbauweise) {
		this.leichtbauweise = leichtbauweise;
	}




	public Boolean getSonderzuschlag() {
		return sonderzuschlag;
	}




	public void setSonderzuschlag(Boolean sonderzuschlag) {
		this.sonderzuschlag = sonderzuschlag;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status.toString();
	}




	public String getSpezifikation() {
		return spezifikation;
	}




	public void setSpezifikation(String spezifikation) {
		this.spezifikation = spezifikation;
	}


	@Override
	public String toString() {
		return "Id: " + auftragsID + "; Kunde: " + customer.getCustomerid() + "; Typ: " + robotertypID + "; Menge: " + menge;
	}





}
