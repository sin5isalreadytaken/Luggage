package utils;
import java.util.LinkedList;


public class Plane {
	private String Bid=null;
	private LinkedList queue=new LinkedList<Baggage>();
	
	public Plane(String str){
		this.Bid=str;
	}
	
	public void push(Baggage bag){
		setnowplace(bag);
		queue.add(bag);
	}
	
	//��֪���ݿ�����ﱾվ
	public void setnowplace(Baggage bag){
		System.out.println("i am "+bag.getRFID()+" now i am in "+Bid);
		SortSqlAccess.relocation(bag.getRFID(), this.Bid);
	}
}
