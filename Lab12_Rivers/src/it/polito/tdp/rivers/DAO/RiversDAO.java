package it.polito.tdp.rivers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class RiversDAO {

	public Map<Integer, River> getRivers() {

		String sql = "SELECT * FROM river";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			Map<Integer, River> rivers = new TreeMap<>();

			while (rs.next()) {

				River r = new River(rs.getInt("id"), rs.getString("name"));
				rivers.put(r.getId(), r);	
			}
			return rivers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Flow> getFlow(River r) {

		String sql = "SELECT * FROM flow WHERE river=?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet rs = st.executeQuery();

			List<Flow> f = new ArrayList<>();

			while (rs.next()) {
				LocalDate l = rs.getDate("day").toLocalDate();
				Flow fl = new Flow(rs.getInt("id"), l, rs.getFloat("flow"), r);
				f.add(fl);	
			}
			return f;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}