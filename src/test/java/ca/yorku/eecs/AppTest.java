package ca.yorku.eecs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

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
    

   public void testAddActorPass() throws JSONException, IOException{
	   

	   
	   URI url = new URI("http://localhost:8080/api/v1/addActor");
	   
	   HttpURLConnection connector = (HttpURLConnection) url.openConnection();
	   
	   connector.addRequestProperty(getName(), getName())
	   
	   connector.setDoOutput(true);
	   connector.setDoInput(true);
	   connector.setRequestMethod("PUT");
	   connector.connect();
	   JSONObject json = new JSONObject();
	   json.put("name", "Kevin Bacon");
	   json.put("actorId", "1234");
	   String name = json.getString("name");
	   String actorId = json.getString("actorId");

	   handler h = new handler();
	   h.PUThandler(null)
	   
	 
	   int result = connector.getResponseCode();

	   
	   assertEquals(200,result);
    }
   
 
   
}
