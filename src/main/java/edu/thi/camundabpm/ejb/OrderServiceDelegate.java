package edu.thi.camundabpm.ejb;

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
		Double auftragspreis = rt.getPreis() * menge;
		Double rabattpreis = 0.00;
		String spezialdesign = "";
		Boolean erhoehterFertigungsaufwand = false;
		Boolean lautstaerkereduzierung = false;
		Boolean leichtbauweise = false;
		Double sonderzuschlag = 0.00;
		Status status = Status.EINGEGANGEN;
		String spezifikation = "";

		cart.setRobotertypID(robotertypId);
		cart.setMenge(menge);
		cart.setAuftragspreis(auftragspreis);
		cart.setRabattpreis(rabattpreis);
		cart.setSpezialdesign(spezialdesign);
		cart.setErhoeterFertigungsaufwand(erhoehterFertigungsaufwand);
		cart.setLautstaerkereduzierung(lautstaerkereduzierung);
		cart.setLeichtbauweise(leichtbauweise);
		cart.setSonderzuschlag(sonderzuschlag);
		cart.setStatus(status);
		cart.setSpezifikation(spezifikation);
		cart.setCustomer(customer);

		execution.setVariable("customer", customer);
		execution.setVariable("cart", cart);

		orderService.create(cart);
		customer.getCarts().add(cart);

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

	public void updateStatus(DelegateExecution execution, String status) {
		Cart order = (Cart) execution.getVariable("input_cart");
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
		case "EINGESCHRAENKTUMSETZBAR":
			newState = Status.EINGESCHRAENKTUMSETZBAR;
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
		default:
			System.out.println("triggered default case...");
			newState = Status.EINGEGANGEN;
			break;
		}
		System.out.println(newState.toString());
		order.setStatus(newState);
		orderService.update(order);
	}

}
