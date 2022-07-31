package ca.yorku.eecs;

import static org.neo4j.driver.v1.Values.parameters;

import java.io.IOException;
import java.io.OutputStream;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

import com.sun.net.httpserver.HttpExchange;
public class Neo4jwonCannesAward {
	private Driver driver;
	private String uriDb;
	
	public Neo4jwonCannesAward() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean check_movie(String movieId) {
		 try (Session session = driver.session())
	       {
	       	try (Transaction tx = session.beginTransaction()) {
	       		StatementResult result = tx.run("MATCH (m:movie)\n" +
	       	 "WHERE m.id= $y\n" + "RETURN m.id as bool",parameters("y" , movieId) );
	       		
	       		if(result.hasNext()) {
	       			session.close();;
	       			return true;
	       			//has next, there is movie so, it should work
	       		}else {
	       			session.close();;
	       			return false;
	       			//else its not there so should not work
	       		}
	       		
	       	}
	       }
	}
	
	public void updatecannesWon(String actorId , int target_number) {

		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("MATCH (m:movie)\n"
					+ "WHERE m.id =$x\n"
					+ "SET m.cannesAwardWon=$z", 
					parameters("x", actorId , "z" , target_number)));
			session.close();
		}
	}
	
	public void close() {
		driver.close();
	}
}
