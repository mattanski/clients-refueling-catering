package com.casa.esercitazione.UltimoGiorno.it;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


	
	public class ConnectDb {

		private final String url = "jdbc:postgresql://localhost:5432/db_stazionirifornimento";					//dobbiamo usare un url predefinto 
		private final String user = "postgres";												// se avremmo usato sql avremmo inserito mysql
		private final String password = "password";
		
		private ArrayList<Persona> personaList;
		private final String insertPersonaSQL = "INSERT INTO abbonati (nome, cognome, numeroTessera) VALUES (?,?,?);"; 
		
		
		
		public Connection connect() {
			
			this.personaList = new ArrayList<Persona>();
			Connection connessione = null;
			
			try {	 // il try-catch non ha bisogno di if, e se va in errore bugga anche alla prima linea
				
				connessione = DriverManager.getConnection(url, user, password);
				System.out.println("Connessione riuscita");
				
				
			}catch(SQLException e){
				System.out.println(e.getMessage());
				System.err.println("CONNESSIONE NON RIUSCITA");
				
				return connessione;
				
			}
			
			return connessione;
			
		}
		
		
		public void getAbbonati(String query) {
			
			String querySql = query;
			
			try {
				Connection connessione = connect();
				Statement stmt = connessione.createStatement();     
				ResultSet rs = stmt.executeQuery(querySql); 
				System.out.println("Query eseguita correttamente");
				this.personaList=addPersonaList(rs);		// popolami la lista
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.err.println("Query non eseguita correttamente");
			}
			
			System.out.println(this.personaList.toString());
		}
		
		
		// ci ritornerà  una lista (è come un metodo statico perchè lo utlizzeremo solo all'interno di altri metodi come quello che sta sopra)
		//va a prendere i dati delle colonne dal databse e le mette nella lista
		public ArrayList<Persona> addPersonaList(ResultSet rs) throws SQLException{
			
			  ArrayList<Persona>  personaList = new ArrayList<Persona>();
			
			while(rs.next()){  					 // next() andrà a prenderci uno alla volta gli elementi che sono presenti nel Result Set
				Persona p = new Persona();
				p.setNome(rs.getString("nome"));
				p.setCognome(rs.getString("cognome"));
				
				
				personaList.add(p);
			}
			
			return personaList;
		}

	}

