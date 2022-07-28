package ca.yorku.eecs;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

public class Neo4jaddRelation {
	private Driver driver;
	private String uriDb;
	
	public Neo4jaddRelation() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","1234"), config);
	}
	
	public boolean checkActorExsist(String actorID) {
		 try (Session session = driver.session())
       {
       	try (Transaction tx = session.beginTransaction()) {
       		StatementResult result = tx.run("MATCH (a:actor)\n" +
       	 "WHERE a.actorId= $x\n" + "RETURN a.actorId as bool",parameters("x" , actorID) );
       		
       		if(result.hasNext()) {
       			return true;
       		}else {
       			return false;
       		}
       		
       	}
       }
	}
	public boolean checkMovieExsist(String movieID) {
		 try (Session session = driver.session())
      {
      	try (Transaction tx = session.beginTransaction()) {
      		StatementResult result = tx.run("MATCH (m:movie)\n" +
      	 "WHERE m.movieId= $x\n" + "RETURN m.movieId as bool",parameters("x" , movieID) );
      		
      		if(result.hasNext()) {
      		
      			return true;
      		}else {
      			
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
	      				"WHERE a.actorId=$x AND m.movieId=$y\n"+
	      				"RETURN p"
						,parameters("x", actorID, "y", movieID) );
	      		//"RETURN EXISTS( (:actor {actorID: $x})-[:ACTED_IN]->(:movie {movieID: $y}) ) as bool"
	      		if(result.hasNext()) {
	      			
	      			return true;
	      		}else {
	      			
	      			return false;
	      		}
	      		
	      	}
	      }
		}
	
	public void addRelation(String actorID , String movieID) {
		
		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("MATCH (a:actor) , (m:movie)\n"+
		"WHERE a.actorId=$x AND m.movieId=$y\n"
					+ "CREATE (a)-[r:ACTED_IN]->(m)\n" +"RETURN type(r)",
					parameters("x" , actorID , "y" , movieID)) );
			
		}
	}

	public void close() {
		driver.close();
	}
}
