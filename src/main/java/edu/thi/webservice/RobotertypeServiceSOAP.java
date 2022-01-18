package edu.thi.webservice;

/*
 * 
 * @Author Sebastian Waterloo
 * 
 * */

import javax.inject.Inject;
import javax.jws.WebService;
import edu.thi.jpa.beans.Robotertype;
import edu.thi.services.ejb.RobotertypeServiceBean;

/*
This class is for requesting the RobotertypeServiceBean over a soap request
you can use those methods for communication with the database
-create
-read
-search
-update
-delete
*/
@WebService
public class RobotertypeServiceSOAP {

    @Inject
    RobotertypeServiceBean robotertypeService;
    
    //this method creates a robotertype in the entity-table and returns it
    public Robotertype create(String bezeichnung, Double preis) {
    	Robotertype type = new Robotertype();
    	type.setBezeichnung(bezeichnung);
    	type.setPreis(preis);
        return robotertypeService.create(type);
    }
    
    // this method reads a robotertype out of the robotertype-table and returns it
    public Robotertype read(Long id) {
        return this.robotertypeService.read(id);
    }
    
    // this method searchs all robotertypes with its bezeichnung and returns it
    public Robotertype[] search(String bezeichnung) {
        return this.robotertypeService.search(bezeichnung);
    }
    
    // this method creates a raw robotertype inside of the database-table
    public void update() {
    	Robotertype type = new Robotertype();
        this.robotertypeService.update(type);
    }
    
    // this method deletes an existing robotertype in the entity
    public void delete(Long id) {
        this.robotertypeService.delete(id);
    }
}
