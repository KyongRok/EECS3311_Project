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
	   

	   JSONObject requestBody = new JSONObject();
	   requestBody.put("name", "Kevin Bacon");
	   requestBody.put("actorId", "1234");
	   
	   URL url = new URL("http://localhost:8080/api/v1/addActor");
	   
	   
	   HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	   connection.setDoOutput(true);
	   connection.setRequestMethod("PUT");
	   OutputStream outputStream = connection.getOutputStream();
	   OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
       outputStreamWriter.write(requestBody);
       outputStreamWriter.close();
       outputStream.close();
       connection.connect();
       
       int responseStatus = connection.getResponseCode();
	   
	   assertEquals(200,responseStatus);
    }
   
 
   
}
