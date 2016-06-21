package utils;
public class MainForTest {
	public static RFIDtag tag;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Access acc=new Access();
		acc.SystemStart();
		tag = new RFIDtag(acc);
//		tag.setRFID();

	}
	
	public static RFIDtag getRFIDtag(){
		Access acc=new Access();
		acc.SystemStart();
		tag = new RFIDtag(acc);
		return tag;
	}

}
