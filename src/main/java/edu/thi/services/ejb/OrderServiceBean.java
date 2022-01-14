package edu.thi.services.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.thi.jpa.beans.Cart;
import edu.thi.jpa.beans.Robotertype;

/**
 * Serviceklasse um die Entität Customer herum
 * Bei Session EJBs wird die Threadverwaltung vom Container übernommen - daher kann hier der EntityManager direkt injiziert werden.
 * Verwendet EJB-Transaktionen --> obwohl EntityManager nicht threadSafe ist, kann er in EJBs verwendet werden,
 * da der Container auch das Transaktionsmanagement übernimmt!
 * --> Diese Klasse kann für JAX-WS und JAX-RS Webservices sowie Servlets und in Camunda verwendet werden!
 */
@Stateless
@LocalBean
public class OrderServiceBean implements OrderServiceBeanRemote, OrderServiceBeanLocal {

    @PersistenceContext
    EntityManager em;
    

    public OrderServiceBean() {
        // TODO Auto-generated constructor stub
    }
    public Cart create(Cart order) {
        em.persist(order);
        return order;
    }
    public Cart read(Long id) {
    	System.out.println("reading orderdata...");
        return this.em.find(Cart.class, id);
    }

    public Cart[] search(Long kundenId) {
    	System.out.println("searching...");
        return null;
    }
    
    public Robotertype find(String bezeichnung) {
    	Robotertype type = null;
    	TypedQuery<Robotertype> query = em.createNamedQuery(Robotertype.getType, Robotertype.class);
    	query.setParameter(1, bezeichnung);
    	type = query.getSingleResult();
    	return type;
    }
    
    
    public void delete(Long id) {
    	Cart order = read(id);
        if (order != null)
            this.em.remove(order);
    }

    public void update(Cart order) {
        this.em.merge(order);
    }
}

