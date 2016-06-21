package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
//	private static final String str = "jdbc:mysql://192.168.26.98:3306";
//	private static final String str = "jdbc:mysql://10.138.99.139:3306";
	private static final String str = "jdbc:mysql://127.0.0.1:3306";
	private static final String user = "root";
	private static final String password = "sin5";
	
	private static final String url = str + "/luggage?characterEncoding=UTF-8";
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public DBHelper(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void conn(){
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconn(){
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String sql){
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void execute(String sql){
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}