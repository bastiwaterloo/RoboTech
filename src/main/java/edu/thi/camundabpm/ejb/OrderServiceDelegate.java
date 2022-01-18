package edu.thi.camundabpm.ejb;

import java.util.Collection;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import edu.thi.constants.enums.Status;
import edu.thi.jpa.beans.Cart;
import edu.thi.jpa.beans.Customer;
import edu.thi.jpa.beans.Robotertype;
import edu.thi.services.ejb.OrderServiceBean;

/**
 * Session Bean implementation class CustomerServiceDelegate
 */
@Stateless
@LocalBean
@Named
public class OrderServiceDelegate implements OrderServiceDelegateLocal {

	@Inject
	OrderServiceBean orderService; // Injizierte Service-EJB (Stateless)

	/**
	 * Default constructor.
	 */
	public OrderServiceDelegate() {
		// TODO Auto-generated constructor stub
	}

	public void createOrder(DelegateExecution execution) {

		Cart cart = new Cart();

		Customer customer = (Customer) execution.getVariable("customer");
		Robotertype rt = (Robotertype) execution.getVariable("robotertype");

		Long robotertypId = rt.getRobotertypID();
		Long menge = (Long) execution.getVariable("input_menge");
		Long auftragspreis = (long) (rt.getPreis() * menge);
		Long rabattpreis = (long) 0.00;
		Boolean spezialdesign = false;
		Boolean erhoehterFertigungsaufwand = false;
		Boolean lautstaerkereduzierung = false;
		Boolean leichtbauweise = false;
		Boolean sonderzuschlag = false;
		Status status = Status.EINGEGANGEN;
		String spezifikation = (String) execution.getVariable("formfield_spezifikation");
		Date datum = new Date();

		System.out.println(datum.toString());

		cart.setRobotertypID(robotertypId);
		cart.setMenge(menge);
		cart.setAuftragspreis(auftragspreis);
		cart.setRabattpreis(rabattpreis);
		cart.setSpezialdesign(spezialdesign);
		cart.setBurttopreis(auftragspreis);
		cart.setErhoehterFertigungsaufwand(erhoehterFertigungsaufwand);
		cart.setLautstaerkereduzierung(lautstaerkereduzierung);
		cart.setLeichtbauweise(leichtbauweise);
		cart.setSonderzuschlag(sonderzuschlag);
		cart.setStatus(status);
		cart.setSpezifikation(spezifikation);
		cart.setCustomer(customer);
		cart.setEingangsdatum(datum);

		orderService.create(cart);

		Collection<Cart> customerCarts = customer.getCarts();
		customerCarts.add(cart);
		customer.setCarts(customerCarts);

		System.out.println(customer.getCarts().size());

		execution.setVariable("customer", customer);
		execution.setVariable("cart", cart);
	}

	public void createOrder_test(DelegateExecution execution) {
		System.out.println("creating order....");
	}

	public void read(Long id, DelegateExecution execution) {
		Cart order = orderService.read(id);
		execution.setVariable("order", order);
	}

	public void delete(DelegateExecution execution) {
		System.out.println("delete order....");
	}

	public void requestConditions(DelegateExecution execution) {
		String processId = execution.getProcessInstanceId();
	}

	public void setLifetimeValue(DelegateExecution execution) {
		Customer customer = (Customer) execution.getVariable("input_customer");
		Collection<Cart> carts = customer.getCarts();
		Double customerValue = summarizeCustomerLifetimeValue(carts);
		execution.setVariable("customerValue", customerValue);
	}

	private Double summarizeCustomerLifetimeValue(Collection<Cart> carts) {
		Double lifetimeValue = 0.00;
		for (Cart cart : carts) {
			if (cart.getStatus().equals(Status.ABGESCHLOSSEN)) {
				lifetimeValue = lifetimeValue + cart.getAuftragspreis();
			}
		}
		return lifetimeValue;
	}

	public void calcDiscount(DelegateExecution execution) {
		Cart cart = (Cart) execution.getVariable("input_cart");
		Double rabatt = ((cart.getAuftragspreis() / 100) * (Double) execution.getVariable("rabatt"));
		cart.setAuftragspreis((long) (cart.getAuftragspreis() - rabatt));
		execution.setVariable("input_cart", cart);
		orderService.update(cart);
	}

	public void addExtraCharge(DelegateExecution execution, Double percentage) {
		Cart cart = (Cart) execution.getVariable("input_cart");
		Long auftragspreis = cart.getAuftragspreis();
		Double aufschlag = 0.00;
		if (cart.getSonderzuschlag()) {
			aufschlag = aufschlag + ((auftragspreis / 100) * percentage);
		}
		if (cart.getLautstaerkereduzierung()) {
			aufschlag = aufschlag + ((auftragspreis / 100) * percentage);
		}
		if (cart.getLeichtbauweise()) {
			aufschlag = aufschlag + ((auftragspreis / 100) * percentage);
		}
		if (cart.getErhoehterFertigungsaufwand()) {
			aufschlag = aufschlag + ((auftragspreis / 100) * percentage);
		}
		if (cart.getSpezialdesign()) {
			aufschlag = aufschlag + ((auftragspreis / 100) * percentage);
		}
		auftragspreis = (long) (auftragspreis + aufschlag);
		cart.setAuftragspreis(auftragspreis);
		execution.setVariable("input_cart", cart);
		orderService.update(cart);
	}

	public void updateStatus(DelegateExecution execution, String status) {
		Cart order = (Cart) execution.getVariable("input_cart");
		int level = 1;
		if (order == null) {
			order = (Cart) execution.getVariable("input_cart_L2");
			level = 2;
		}
		Status newState = null;
		System.out.println(status);
		switch (status) {
		case "EINGEGANGEN":
			newState = Status.EINGEGANGEN;
			break;
		case "INPRUEFUNG":
			newState = Status.INPRUEFUNG;
			break;
		case "UMSETZBAR":
			newState = Status.UMSETZBAR;
			break;
		case "NICHTUMSETZBAR":
			newState = Status.NICHTUMSETZBAR;
			break;
		case "GENEHMIGT":
			newState = Status.GENEHMIGT;
			break;
		case "ABGELEHNT":
			newState = Status.ABGELEHNT;
			break;
		case "INBEARBEITUNG":
			newState = Status.INBEARBEITUNG;
			break;
		case "TERMINVEREINBART":
			newState = Status.TERMINVEREINBART;
			break;
		case "ABGESCHLOSSEN":
			newState = Status.ABGESCHLOSSEN;
			break;
		case "NICHTERFOLGREICH":
			newState = Status.NICHTERFOLGREICH;
			break;
		default:
			System.out.println("triggered default case...");
			newState = Status.EINGEGANGEN;
			break;
		}
		order.setStatus(newState);
		if (level == 1) {
			execution.setVariable("input_cart", order);
		} else {
			execution.setVariable("input_cart_L2", order);
		}
		orderService.update(order);
	}

	public void update(DelegateExecution execution) {
		Cart cart = (Cart) execution.getVariable("input_cart");
		Boolean spezialdesign = (Boolean) execution.getVariable("formfield_spezialdesign");
		Boolean erhoehterFertigungsaufwand = (Boolean) execution.getVariable("formfield_erhoehterFertigungsaufwand");
		Boolean lautstaerkereduzierung = (Boolean) execution.getVariable("formfield_lautstaerkereduzierung");
		Boolean leichtbauweise = (Boolean) execution.getVariable("formfield_leichtbauweise");
		Boolean sonderzuschlag = (Boolean) execution.getVariable("formfield_sonderzuschlag");
		Long menge = null;
		if ((Long) execution.getVariable("formfield_angepasstes_angebot") != null
				&& (Long) execution.getVariable("formfield_angepasstes_angebot") > 0) {
			System.out.println("update angebot");
			Long preis = cart.getAuftragspreis();
			Long einzelpreis = (preis / cart.getMenge());
			menge = (Long) execution.getVariable("formfield_angepasstes_angebot");
			Long newPreis = einzelpreis * menge;
			cart.setAuftragspreis(newPreis);
			cart.setBurttopreis(newPreis);
			cart.setMenge(menge);
		}
		cart.setSpezialdesign(spezialdesign);
		cart.setErhoehterFertigungsaufwand(erhoehterFertigungsaufwand);
		cart.setLautstaerkereduzierung(lautstaerkereduzierung);
		cart.setLeichtbauweise(leichtbauweise);
		cart.setSonderzuschlag(sonderzuschlag);
		execution.setVariable("input_cart", cart);
		orderService.update(cart);

	}

	public void calculateAdditionalCosts(DelegateExecution execution) {
		Cart cart = (Cart) execution.getVariable("input_cart");
		Double auftragspreis = cart.getAuftragspreis().doubleValue();
		System.out.println("Auftragspreis vor zuschlägen: " + auftragspreis);
		Double valueErhoehterFertigungsaufwand = Double.valueOf((String) execution.getVariable("erhoehterFertigungsaufwand"));
		Double valueLeichtbauweise = Double.valueOf((String) execution.getVariable("leichtbauweise"));
		Double valueLautstaerkereduzierung = Double.valueOf((String) execution.getVariable("lautstaerkereduzierung"));
		Double valueSpezialdesign = Double.valueOf((String) execution.getVariable("spezialdesign"));
		Double valueSonderzuschlag = Double.valueOf((String) execution.getVariable("sonderzuschlag"));

		Double additonalCharges = 0.00;

		if (cart.getErhoehterFertigungsaufwand() && valueErhoehterFertigungsaufwand != null && valueErhoehterFertigungsaufwand > 0) {
			additonalCharges += ((auftragspreis/100) * valueErhoehterFertigungsaufwand);
		}
		if (cart.getLeichtbauweise() && valueLeichtbauweise != null && valueLeichtbauweise > 0) {
			additonalCharges += ((auftragspreis/100) * valueLeichtbauweise);
		}
		if (cart.getLautstaerkereduzierung() && valueLautstaerkereduzierung != null && valueLautstaerkereduzierung > 0) {
			additonalCharges += ((auftragspreis/100) * valueLautstaerkereduzierung);
		}
		if (cart.getSpezialdesign() && valueSpezialdesign != null && valueSpezialdesign > 0) {
			additonalCharges += ((auftragspreis/100) * valueSpezialdesign);
		}
		if (cart.getSonderzuschlag() && valueSonderzuschlag != null && valueSonderzuschlag > 0) {
			additonalCharges += ((auftragspreis/100) * valueSonderzuschlag);
		}

		System.out.println("Zuschläge: " + additonalCharges);
		
		auftragspreis += additonalCharges;
		
		cart.setAuftragspreis(auftragspreis.longValue());
		
		execution.setVariable("input_cart", cart);
		
		orderService.update(cart);
	}
	
	public void calculateFinalDiscountedPrice(DelegateExecution execution) {
		Cart cart = (Cart) execution.getVariable("input_cart");
		Double auftragspreis = cart.getAuftragspreis().doubleValue();
		Long rabatt = (Long) execution.getVariable("rabatt");
		System.out.println("Rabatt in %: " + rabatt);
		System.out.println("Rabbat wert: " + (auftragspreis / 100) * rabatt.doubleValue());
		Double rabattValue = (double) ((auftragspreis / 100) * rabatt);
		Double rabattPreis = auftragspreis - rabattValue;
		cart.setRabattpreis(rabattPreis.longValue());
		System.out.println("final price: " + rabattPreis);
		execution.setVariable("input_cart", cart);
		orderService.update(cart);
	}

}
