package edu.thi.java.maschinenevent;

/**
 * Just a convenience interface to let us easily contain the Esper statements with the Subscribers -
 * just for clarity so it's easy to see the statements the subscribers are registered against.
 * Based on an article by Adrian Milne - http://www.adrianmilne.com/complex-event-processing-made-easy/
 */
public interface StatementSubscriber {

    /**
     * Get the EPL Stamement the Subscriber will listen to.
     * @return EPL Statement
     */
    public String getStatement();

}
