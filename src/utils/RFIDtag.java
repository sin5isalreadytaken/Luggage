package utils;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RFIDtag {
	private String ID;
	private String Destination;
	private String RFIDnum;
	private String number;
	private String port;
	private Access acc;
	
	private DBHelper db1;
	private DBHelper db2;
	
	public RFIDtag(Access acc){
		this.acc = acc;
		db1 = new DBHelper();
		db1.conn();
		db2 = new DBHelper();
		db2.conn();
	}
	
	public void setRFID(){
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		rs1 = db1.executeQuery("select * from ticket");
		try {
			while(rs1.next()){
				ID = rs1.getString("ID");
				Destination = rs1.getString("destination");
				rs2 = db2.executeQuery("select * from local where destination = '"+ Destination+"'");
				if(rs2.next()){
					port = rs2.getString("port");
					number = rs2.getString("number");
				}
				RFIDnum = ID.substring(ID.length()-4, ID.length())+number+port;
				Baggage b = new Baggage(RFIDnum);
				acc.BaggageSort(b);
				db2.execute("update ticket set RFID = '"+ RFIDnum + "' where ID = '"+ID+"'");
				db2.execute("insert into positions values ('"+RFIDnum+"','A0','"+Destination+"')");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db1.disconn();
			db2.disconn();
		}
	}
	
	public String setandreturnRFID(String id){
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		rs1 = db1.executeQuery("select * from ticket where id = '" + id + "'");
		try {
			while(rs1.next()){
				ID = rs1.getString("ID");
				Destination = rs1.getString("destination");
				rs2 = db2.executeQuery("select * from local where destination = '"+ Destination+"'");
				if(rs2.next()){
					port = rs2.getString("port");
					number = rs2.getString("number");
				}
				RFIDnum = ID.substring(ID.length()-4, ID.length())+number+port;
				Baggage b = new Baggage(RFIDnum);
				acc.BaggageSort(b);
				db2.execute("update ticket set RFID = '"+ RFIDnum + "' where ID = '"+ID+"'");
				db2.execute("insert into positions values ('"+RFIDnum+"','A0','"+Destination+"')");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db1.disconn();
			db2.disconn();
		}
		return RFIDnum;
		
	}
	
}
