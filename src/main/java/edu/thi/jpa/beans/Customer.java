package edu.thi.jpa.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = Customer.searchCustomer, query = "SELECT c FROM Customer c WHERE c.email LIKE ?1")
@Table(name = "customer")
public class Customer implements Serializable {
	public final static String searchCustomer = "Customer.searchCustomer";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerid;
	private String vorname;
	private String nachname;
	private Long plz;
	private String stadt;
	private String land;
	private String email;


	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	private String kundenart;

	public String getKundenart() {
		return kundenart;
	}

	public void setKundenart(String kundenart) {
		this.kundenart = kundenart;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Long getPlz() {
		return plz;
	}

	public void setPlz(Long plz) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Id: " + customerid + "; Firstname: " + vorname + "; Lastname: " + nachname + "; Email: " + email;
	}
}
