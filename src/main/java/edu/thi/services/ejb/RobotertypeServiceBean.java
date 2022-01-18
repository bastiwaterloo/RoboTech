package edu.thi.services.ejb;
/*
 * 
 * @Author Dominik Knauer
 * 
 * */
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.thi.jpa.beans.Robotertype;

//ServiceBean to handle all robotertype DB operations
@Stateless
@LocalBean
public class RobotertypeServiceBean {

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
    
    //find robotertype by designation
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

