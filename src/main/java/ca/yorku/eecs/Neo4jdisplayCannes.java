package ca.yorku.eecs;
import static org.neo4j.driver.v1.Values.parameters;

import java.util.ArrayList;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
public class Neo4jdisplayCannes {
	private Driver driver;
	private String uriDb;
	
	public Neo4jdisplayCannes() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean checkmovieID(String movieId) {
		 try (Session session = driver.session())
      {
      	try (Transaction tx = session.beginTransaction()) {
      		StatementResult result = tx.run("MATCH (m:movie)\n" +
      	 "WHERE m.id= $y\n" + "RETURN m.id as bool",parameters("y" , movieId) );
      		session.close();
      		if(result.hasNext()) {
      			//there is actor in the DB with matching id
      			return true;
      		}else {
      			return false;
      			//there isnt actor in the DB with matching id
      		}
      		
      	}
      }
	}
	
	public String getMovie_info(String movieID) {
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH (m:movie)\n"
						+ "WHERE m.id = $x\n" + "RETURN m.name", parameters("x", movieID));
				String actor_name = "";
				if(result.hasNext()) {
					actor_name += result.single().get(0).asString();
				}
				session.close();
				return actor_name;
				//getting the name of the actor with it's id
			}
			
		}
	}
	
	public int displayCannes(String movieId) {
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH (m:movie)\n"
						+ "WHERE m.id = $x\n" + "RETURN m.cannesAwardWon", parameters("x", movieId));
				int number = 0;
				if(result.hasNext()) {
					number += result.single().get(0).asInt();
				}
				session.close();
				return number;
				//getting the number of Oscars for this actor
			}
			
		}
	}
}

