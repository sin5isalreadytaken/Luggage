package utils;
public class SortSqlAccess {
	public static void relocation(String RFID,String locationNow){
		//����Ӧ����RFIDΪString RFID�ĵ�ǰλ�������ΪString locationNow
		//ע�����ݿ����ʱ�����ӺͶϿ�
		//��׽�쳣������쳣��Ϣ
		DBHelper db = new DBHelper();
		db.conn();
		db.execute("update positions set currentlocation = '"+locationNow+"' where RFID = '"+RFID+"'");
		db.disconn();
	}
}
