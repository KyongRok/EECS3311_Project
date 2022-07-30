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
import org.neo4j.driver.v1.Value;
public class Neo4jcomputeBaconPath {
	private Driver driver;
	private String uriDb;
	
	public Neo4jcomputeBaconPath(){
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","123456"), config);
	}
	
	public boolean checkActor(String actorId) {
		 try (Session session = driver.session())
	     {
	     	try (Transaction tx = session.beginTransaction()) {
	     		StatementResult result = tx.run("MATCH (a:actor)\n" +
	     	 "WHERE a.id= $y\n" + "RETURN a.id as bool",parameters("y" , actorId) );
	     		session.close();
	     		if(result.hasNext()) {
	     			//actor with this id exists in the DB
	     			return true;
	     		}else {
	     			//actor with this id does not exists in the DB
	     			return false;
	     		}
	     		
	     	}
	     }
		}
	public ArrayList<String> computeBaconPath(String actorId , String kevin){
		try (Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				
				StatementResult result = tx.run(
					"MATCH (a:actor {id:$x}),(bacon:actor {id:$y}),"
					+ "p = shortestPath((a)-[:ACTED_IN*]-(bacon))\n"
			        +"RETURN DISTINCT nodes(p)",parameters("x" , actorId , "y" , kevin));
				
				ArrayList<String> list = new ArrayList<>();
				
				while(result.hasNext()) {
					Value temp_list = result.next().values().get(0);
					for(int i = 0; i < temp_list.size(); i++) {
						list.add(temp_list.get(i).get("id").toString());
					}
					
				}
				session.close();
				return list;
				}
			}
		}
	
	}

