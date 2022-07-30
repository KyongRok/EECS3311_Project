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

public class Neo4jhasRelation {
	private Driver driver;
	private String uriDb;
	
	public Neo4jhasRelation() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean checkMovieID(String MovieId) {
		 try (Session session = driver.session())
      {
      	try (Transaction tx = session.beginTransaction()) {
      		StatementResult result = tx.run("MATCH (m:movie)\n" +
      	 "WHERE m.id= $y\n" + "RETURN m.id as bool",parameters("y" , MovieId) );
      		session.close();
      		if(result.hasNext()) {
      			//movie with this id exists in the DB
      			return true;
      		}else {
      			//movie with this id does not exists in the DB
      			return false;
      		}
      		
      	}
      }
	}
	
	public boolean checkActorID(String MovieId) {
		 try (Session session = driver.session())
     {
     	try (Transaction tx = session.beginTransaction()) {
     		StatementResult result = tx.run("MATCH (a:actor)\n" +
     	 "WHERE a.id= $y\n" + "RETURN a.id as bool",parameters("y" , MovieId) );
     		session.close();
     		if(result.hasNext()) {
     			//movie with this id exists in the DB
     			return true;
     		}else {
     			//movie with this id does not exists in the DB
     			return false;
     		}
     		
     	}
     }
	}
	
	public boolean checkRelationExsist(String actorID , String movieID) {
		 try (Session session = driver.session())
	      {
	      	try (Transaction tx = session.beginTransaction()) {
	      		StatementResult result = tx.run(
	      				"MATCH p = (a)-[:ACTED_IN]->(m)\n"+
	      				"WHERE a.id=$x AND m.id=$y\n"+
	      				"RETURN p"
						,parameters("x", actorID, "y", movieID) );
	      		
	      		if(result.hasNext()) {
	      			//there is relation existing
	      			return true;
	      		}else {
	      			//there is no relation existing
	      			return false;
	      		}
	      		
	      	}
	      }
		}
	
}
