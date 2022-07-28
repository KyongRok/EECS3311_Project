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


public class Neo4jaddMovie {
	private Driver driver;
	private String uriDb;
	
	public Neo4jaddMovie() {
		uriDb = "bolt://localhost:7687";
		Config config = Config.builder().withoutEncryption().build();
		driver = GraphDatabase.driver(uriDb, AuthTokens.basic("neo4j","1234"), config);
	}
	
	public boolean checkMovieDuplicate(String MovieId) {
		 try (Session session = driver.session())
       {
       	try (Transaction tx = session.beginTransaction()) {
       		StatementResult result = tx.run("MATCH (m:movie)\n" +
       	 "WHERE m.movieId= $y\n" + "RETURN m.movieId as bool",parameters("y" , MovieId) );
       		session.close();
       		
       		if(result.hasNext()) {
       			return false;
       		}else {
       			return true;
       		}
       		
       	}
       }
	}
	
	public void addMovie(String name , String MovieId) {
		
		try (Session session = driver.session()){
			session.writeTransaction(tx -> tx.run("CREATE (m:movie {name: $x , movieId: $y})", 
					parameters("x", name , "y" , MovieId)));
			session.close();
		}
	}

	public void close() {
		driver.close();
	}
}
