package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private Graph<Author, DefaultEdge> grafo;
	private Map<Integer, Author> aIdMap;
	private List<Author> authors;
	private PortoDAO dao;
	
	public Model() {
		this.dao=new PortoDAO();
		this.grafo=new SimpleGraph<>(DefaultEdge.class);
		this.aIdMap=new HashMap<>();
		this.authors=new ArrayList<>();
	}
	
	public void creaGrafo() {
		this.authors=dao.getAllAuthors(this.aIdMap);
		Graphs.addAllVertices(this.grafo, this.aIdMap.values());
		
		for(Author a : this.grafo.vertexSet()) {
			for(Author c : dao.getCoautore(a.getId(), this.aIdMap)){
				this.grafo.addEdge(a, c);
			}
		}
	}

	public List<Author> getAllAuthors() {
		return dao.getAllAuthors(this.aIdMap);
	}

	public Map<Integer, Author> getaIdMap() {
		return aIdMap;
	}

	public List<Author> getCoautori(Author author) {	
		return Graphs.neighborListOf(this.grafo, author);
	}
	
	

}
