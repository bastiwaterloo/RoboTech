package edu.thi.webservice;
/*
 * 
 * @Author Lukas Keßler
 * 
 * */
import javax.inject.Inject;
import javax.jws.WebService;
import edu.thi.jpa.beans.Cart;
import edu.thi.services.ejb.OrderServiceBean;

/*
This class is for requesting the OrderServiceBean over a soap request
you can use those methods for communication with the database
-read
-search
-update
-delete
*/


@WebService
public class OrderServiceSOAP {

    @Inject
    OrderServiceBean orderService;
    
    // this method reads an order out of the cart-table and returns it
    public Cart read(Long id) {
        return this.orderService.read(id);
    }
    
    // this method searchs all carts with its kundenId and returns it 
    public Cart[] search(Long kundenId) {
        return this.orderService.search(kundenId);
    }
    
    // this method creates a raw cart object and places inside of the database table
    public void update() {
    	Cart order = new Cart();
        
        this.orderService.update(order);
    }
    
    // this methode deletes an existing cart with its id
    public void delete(Long id) {
        this.orderService.delete(id);
    }
}
