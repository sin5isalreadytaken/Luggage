package utils;
import java.util.*;


public class TransportCar extends Thread{
	private String Bid=null;
	private Plane plane=null;
	private LinkedList<Baggage> queue=new LinkedList<Baggage>();
	public TransportCar(String str){
		this.Bid=str;
	}
	public String getBid() {
		return Bid;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	public LinkedList<Baggage> getQueue() {
		return queue;
	}
	public void setQueue(LinkedList<Baggage> queue) {
		this.queue = queue;
	}
	
	public void push(Baggage bag){
		setnowplace(bag);
		queue.add(bag);
	}
	
	//告知数据库行李到达本站
	public void setnowplace(Baggage bag){
		System.out.println("i am "+bag.getRFID()+" now i am in "+Bid);
		SortSqlAccess.relocation(bag.getRFID(), this.Bid);
	}
	
	public void trans(Baggage e){
		this.plane.push(e);
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.queue.size()>0)
				this.plane.push(this.queue.poll());
		}
	}
}
