package utils;

public class SQLStr {
	
	public static final String getIDs = "SELECT ID FROM ticket WHERE RFID is null or RFID = ''";
	public static final String getRFIDs = "SELECT RFID FROM positions";
	public static final String getPosition = "SELECT currentlocation FROM positions WHERE RFID = 'default'";
	public static final String getDestination = "SELECT destination FROM ticket WHERE ID = 'default'";
	
	public static String getCurrentPosition(String rfid){
		return getPosition.replace("default", rfid);
	}

	public static String getDestinationByID(String ID){
		return getDestination.replace("default", ID);
	}
	
}
