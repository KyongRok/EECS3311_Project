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

public class Neo4jgetPersonalRelation {
	private Driver driver;
	private String uriDb;
	
	public Neo4jgetPersonalRelation() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean checkActorID(String ActorId) {
		 try (Session session = driver.session())
       {
       	try (Transaction tx = session.beginTransaction()) {
       		StatementResult result = tx.run("MATCH (a:actor)\n" +
       	 "WHERE a.id= $y\n" + "RETURN a.id as bool",parameters("y" , ActorId) );
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
	public String getActor_info(String actorID) {
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH (a:actor)\n"
						+ "WHERE a.id = $x\n" + "RETURN a.name", parameters("x", actorID));
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
	
	public ArrayList<String> getActor_PersonalRelation(String actorID) {
		
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH((a:actor{id:$x})-[:PERSONAL_RELATION*1]->(m))\n"
			    + "RETURN DISTINCT m.id", parameters("x", actorID));
				ArrayList<String> list = new ArrayList<>();
				while(result.hasNext()) {
					
					list.add(result.peek().get(0).asString());
					result.next();
				}
				
				
				session.close();
				return list;
				//returning list of movies that this actor has acted in
			}
			
		}
	}	
	public void close() {
		driver.close();
	}
}
