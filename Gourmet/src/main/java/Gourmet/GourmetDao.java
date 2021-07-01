package Gourmet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GourmetDao {
	public List<Price> getPrice(int price) {
		List<Price> selected = new ArrayList<Price>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "select * from gourmet_price where gourmet_price<="+price+" and gourmet_price LIKE '%¿ø';";
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String gourmet_name = rset.getString(1);
				String gourmet_menu = rset.getString(2);
				String gourmet_price = rset.getString(3);
				
				Price gourmet = new Price();
				gourmet.setGourmet_name(gourmet_name);
				gourmet.setGourmet_menu(gourmet_menu);
				gourmet.setGourmet_price(gourmet_price);			
				selected.add(gourmet);
				
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return selected;
	}
	
	public Main getMain(String gourmet_name) {
		Main main = new Main();		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "select gourmet_index from gourmet where gourmet_name='"+gourmet_name+"';";
			int gourmet_index =0;
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				gourmet_index = rset.getInt(1);
			}
			
			QueryTxt = "select * from gourmet where gourmet_index='"+gourmet_index+"';";
			rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String gourmet_star = rset.getString(3);
				String gourmet_visitor_review_cnt = rset.getString(4);
				String gourmet_blogger_review_cnt = rset.getString(5);
				String gourmet_address = rset.getString(6);
				String gourmet_openinghour = rset.getString(7);
				String gourmet_service = rset.getString(8);
				String gourmet_info = rset.getString(9);
				
				main.setGourmet_name(gourmet_name);
				main.setGourmet_star(gourmet_star);
				main.setGourmet_visitor_review_cnt(gourmet_visitor_review_cnt);
				main.setGourmet_blogger_review_cnt(gourmet_blogger_review_cnt);
				main.setGourmet_address(gourmet_address);
				main.setGourmet_openinghour(gourmet_openinghour);
				main.setGourmet_service(gourmet_service);
				main.setGourmet_info(gourmet_info);	
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return main;
	}
	
	public Image getImage(String gourmet_name) {
		Image image = new Image();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "select gourmet_image from gourmet_image where gourmet_name='"+gourmet_name+"';";
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String gourmet_image = rset.getString(1);
				image.setGourmet_image(gourmet_image);			
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return image;
	}
	
}
