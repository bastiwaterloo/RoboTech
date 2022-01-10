package edu.thi.java.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.java.beans.Order;

public class CreateOrderService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String product = (String) execution.getVariable("product");
        Long quantity = (Long) execution.getVariable("quantity");
        String comment = (String) execution.getVariable("comment");
        Order order = new Order(product, quantity.intValue(), comment);
        execution.setVariable("order", order);
        System.out.println("Order created! Product: " + product + ", Quantity: " + quantity + ", Comment: " + comment);
    }
}
