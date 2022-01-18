package edu.thi.java.maschinenevent;

/**
 * Source of this Interface is: http://www.adrianmilne.com/complex-event-processing-made-easy/
 */
public interface StatementSubscriber {

    /**
     * Get the EPL Stamement the Subscriber will listen to.
     * @return EPL Statement
     */
    public String getStatement();

}
