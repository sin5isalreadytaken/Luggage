package utils;
public class Baggage {
	private String RFID=null;

	public Baggage(String rfid){
		this.RFID=rfid;
	}
	public String getRFID() {
		return RFID;
	}

	public void setRFID(String rFID) {
		RFID = rFID;
	}
}
