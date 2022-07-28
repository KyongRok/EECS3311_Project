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

public class Neo4jgetMovie {
	private Driver driver;
	private String uriDb;
	
	public Neo4jgetMovie() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","1234"), config);
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
	public String getMovie_info(String movieID) {
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH (m:movie)\n"
						+ "WHERE m.id = $x\n" + "RETURN m.name", parameters("x", movieID));
				String Movie_name = "";
				if(result.hasNext()) {
					Movie_name += result.single().get(0).asString();
				}
				session.close();
				return Movie_name;
				//get the name of the movie with it's ID
			}
			
		}
	}
	
	public ArrayList<String> getMovie_actors(String movieID) {
		
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				StatementResult result = tx.run("MATCH((m:movie{id:$x})<-[:ACTED_IN*1]-(a))\n"
			    + "RETURN DISTINCT a.id", parameters("x", movieID));
				
				ArrayList<String> list = new ArrayList<>();
				while(result.hasNext()) {
					
					list.add(result.peek().get(0).asString());
					result.next();
				}
				
				
				session.close();
				return list;
				//return list of actors in the movie
			}
			
		}
	}	
	public void close() {
		driver.close();
	}
}
