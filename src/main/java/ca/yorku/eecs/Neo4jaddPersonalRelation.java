package ca.yorku.eecs;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

public class Neo4jaddPersonalRelation {
	private Driver driver;
	private String uriDb;
	
	public Neo4jaddPersonalRelation() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean checkActorExsist1(String actorID) {
		 try (Session session = driver.session())
       {
       	try (Transaction tx = session.beginTransaction()) {
       		StatementResult result = tx.run("MATCH (a:actor)\n" +
       	 "WHERE a.id= $x\n" + "RETURN a.id as bool",parameters("x" , actorID) );
       		
       		if(result.hasNext()) {
       			return true;
       			//has next is true, hence actor exists should work
       		}else {
       			return false;
       			//does not have actor in the DB, hence should not work
       		}
       		
       	}
       }
	}
	public boolean checkActorExsist2(String actorID) {
		 try (Session session = driver.session())
      {
      	try (Transaction tx = session.beginTransaction()) {
      		StatementResult result = tx.run("MATCH (a:actor)\n" +
      	 "WHERE a.id= $x\n" + "RETURN a.id as bool",parameters("x" , actorID) );
      		
      		if(result.hasNext()) {
      		//has next is true, there is actor with matching id
      			return true;
      		}else {
      			// has next is false, there isn't actor with matching id
      			return false;
      		}
      		
      	}
      }
	}
	
	public boolean checkRelationExsist(String actor1ID , String actor2ID) {
		 try (Session session = driver.session())
	      {
	      	try (Transaction tx = session.beginTransaction()) {
	      		StatementResult result = tx.run(
	      				"MATCH p = (a)-[:PERSONAL_RELATION]->(m)\n"+
	      				"WHERE a.id=$x AND m.id=$y\n"+
	      				"RETURN p"
						,parameters("x", actor1ID, "y", actor2ID) );
	      		
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
	
	public void addPersonalRelation(String actor1ID , String actor2ID) {
		
		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("MATCH (a:actor) , (m:actor)\n"+
		"WHERE a.id=$x AND m.id=$y\n"
					+ "CREATE (a)-[r:PERSONAL_RELATION]->(m)\n"
					
					+ "RETURN type(r)" ,
					parameters("x" , actor1ID , "y" , actor2ID)) );
			
		}
		
		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("MATCH (a:actor) , (m:actor)\n"+
		"WHERE a.id=$x AND m.id=$y\n"
					+ "CREATE (m)-[r:PERSONAL_RELATION]->(a)\n"
					
					+ "RETURN type(r)" ,
					parameters("x" , actor1ID , "y" , actor2ID)) );
			
		}
	}

	public void close() {
		driver.close();
	}
}

