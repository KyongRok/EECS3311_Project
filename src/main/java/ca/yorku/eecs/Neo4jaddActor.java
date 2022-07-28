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

//adding actor
public class Neo4jaddActor {
	private Driver driver;
	private String uriDb;
	
	public Neo4jaddActor() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","1234"), config);
	}
	
	public boolean checkActorDuplicate(String name , String actorID) {
		 try (Session session = driver.session())
       {
       	try (Transaction tx = session.beginTransaction()) {
       		StatementResult result = tx.run("MATCH (a:actor)\n" +
       	 "WHERE a.actorID= $y\n" + "RETURN a.actorID as bool",parameters("x" , name
       			 , "y" , actorID) );
       		
       		if(result.hasNext()) {
       			return false;
       		}else {
       			return true;
       		}
       		
       	}
       }
	}
	
	public void addActor(String name , String actorID) {
		
		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("CREATE (a:actor {name: $x , actorID: $y})", 
					parameters("x", name , "y" , actorID)));
			session.close();
		}
	}

	public void close() {
		driver.close();
	}
}
