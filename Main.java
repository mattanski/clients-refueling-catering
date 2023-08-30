package com.casa.esercitazione.UltimoGiorno.it;

import corso.test.javaAvanzato.ConnectDb;

public class Main {

	public static void main(String[]args){
		
		

		ConnectDb connessioneDataBase4 = new ConnectDb();
		
		connessioneDataBase4.connect();
		
		connessioneDataBase4.getAbbonati("SELECT * FROM  Abbonati");
		
		
	}
}



 
 //quanto deve essere il parcheggio tipo timer e il pagamento;