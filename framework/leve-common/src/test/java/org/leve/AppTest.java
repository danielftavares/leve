package org.leve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testesString(){
    	System.out.println(String.format("Teste %s", 1) );
    	
    	Pattern pattern = Pattern.compile("constraint \"(.*?)\"");
    	Matcher matcher = pattern.matcher("ERROR: duplicate key value violates unique constraint \"cmo_country_name_key\"Detail: Key (name)=(BRAZIL) already exists.");
    	if (matcher.find()) {
    	    System.out.println(matcher.group(1));
    	}
    	
    }
}
