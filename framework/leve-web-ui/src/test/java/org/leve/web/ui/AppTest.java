package org.leve.web.ui;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.leve.ejb.UserBean;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    @EJB
    private UserBean userBean;
    
    @Override
    protected void setUp() throws Exception {
		 Properties p = new Properties();
	    p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
	    InitialContext initialContext = new InitialContext(p);
	    initialContext.bind("inject", this);
    	super.setUp();
    }
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    }
}
