package com.telusko.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AlienRepository {

//	List<Alien> aliens;
	
	Connection con=null;

	public AlienRepository() {
//		aliens = new ArrayList<Alien>();
//
//		Alien a1 = new Alien();
//		a1.setId(101);
//		a1.setName("Maho");
//		a1.setPoints(60);
//
//		Alien a2 = new Alien();
//		a2.setId(102);
//		a2.setName("Uzaylı");
//		a2.setPoints(70);
//
//		aliens.add(a1);
//		aliens.add(a2);
		//mysql server'da database ve table oluşturulur. 11.video bak.
		String url="jdbc:mysql://localhost:3306/restdb";
		String username="root";
		String password="password";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Alien> getAliens(){
		List<Alien> aliens=new ArrayList<>();
		String sql="select * from alien";
		try {
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a=new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id) {
//		Alien a1=null;
//		
//		for (Alien alien : aliens) {
//			if (alien.getId()==id) {
//				return alien;
//			}
//		}
//		return new Alien();
		
		String sql="select * from alien where id="+id;
		Alien a=new Alien();
		try {
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
		
	}

	public void create(Alien a1) {
//		aliens.add(a1);
		String sql="insert into alien values (?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Alien a1) {
		String sql="update alien set name=?,points=? where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		String sql="delete from alien where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
