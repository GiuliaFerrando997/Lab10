package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.Model;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		Model model = new Model();
		System.out.println("Risultato: "+pd.getCoautore(719, model.getaIdMap()));
		//System.out.println(pd.getArticolo(2293546));
		//System.out.println(pd.getArticolo(1941144));
		
	}

}
