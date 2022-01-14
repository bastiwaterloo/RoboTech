package edu.thi.services.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
public class RobotertypeServiceBean implements RobotertypeServiceBeanRemote, RobotertypeServiceBeanLocal {

    @PersistenceContext
    EntityManager em;
    

    public RobotertypeServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Robotertype create(Robotertype type) {
        em.persist(type);
        return type;
    }
    
    
    public Robotertype read(Long id) {
    	System.out.println("reading type...");
        return this.em.find(Robotertype.class, id);
    }
    
    public Robotertype find(String bezeichnung) {
    	System.out.println("searching for .... " + bezeichnung);
    	Robotertype type = null;
    	TypedQuery<Robotertype> query = em.createNamedQuery(Robotertype.getType, Robotertype.class);
    	query.setParameter(1, bezeichnung);
    	type = query.getSingleResult();
    	System.out.println("found... " + type);
    	return type;
    }

    public Robotertype[] search(String bezeichnung) {
    	System.out.println("searching...1");
        return null;
    }
    
    public void delete(Long id) {
    	Robotertype type = read(id);
        if (type != null)
            this.em.remove(type);
    }

    public void update(Robotertype type) {
        this.em.merge(type);
    }

}

