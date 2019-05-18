package it.polito.tdp.porto.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getAllAuthors(Map<Integer, Author> aIdMap) {
		final String sql = "SELECT * FROM author";
		List<Author> authors = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
	
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(aIdMap.get(rs.getInt("id"))==null) {
					Author c = new Author(rs.getInt("id"),
							rs.getString("lastname"),
							rs.getString("firstName"));
					aIdMap.put(rs.getInt("id"), c);
					}
					authors.add(aIdMap.get(rs.getInt("id")));
			}

			return authors;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getCoautore(int a, Map<Integer, Author> aIdMap) {
		final String sql = "SELECT DISTINCT c.authorid FROM creator AS c WHERE c.authorid!= ? AND c.eprintid IN (SELECT c1.eprintid FROM creator as c1 WHERE c1.authorid=?)" ;
		List<Author> authors = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a);
			st.setInt(2, a);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			authors.add(aIdMap.get(rs.getInt("authorid")));
					}
			conn.close();
			return authors;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}