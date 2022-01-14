package edu.thi.jpa.beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = Robotertype.getType, query="SELECT t from Robotertype t WHERE t.bezeichnung = ?1")
@Table(name="robotertype")
public class Robotertype implements Serializable{

	public final static String getType = "Robotertype.getType";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long robotertypID;
	
	private String bezeichnung;
	private Double preis;
	
	public Long getRobotertypID() {
		return robotertypID;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public Double getPreis() {
		return preis;
	}
	public void setPreis(Double preis) {
		this.preis = preis;
	}
	
	@Override
	public String toString() {
		return "Id: " + robotertypID + "; Bezeichnung: " + bezeichnung + "; Preis: " + preis;
	}
	
}
