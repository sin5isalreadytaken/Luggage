package utils;
public class SortSqlAccess {
	public static void relocation(String RFID,String locationNow){
		//把相应表中RFID为String RFID的当前位置项更新为String locationNow
		//注意数据库访问时的连接和断开
		//捕捉异常，输出异常信息
		DBHelper db = new DBHelper();
		db.conn();
		db.execute("update positions set currentlocation = '"+locationNow+"' where RFID = '"+RFID+"'");
		db.disconn();
	}
}
