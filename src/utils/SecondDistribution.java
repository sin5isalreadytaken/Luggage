package utils;
import java.util.*;


public class SecondDistribution extends Thread{
	private String Bid;
	private TransportCar tran1=null;
	private TransportCar tran2=null;
	private TransportCar tran3=null;
	private LinkedList<Baggage> queue=new LinkedList<Baggage>();
	
	public SecondDistribution(String str){
		this.Bid=str;
	}
	public String getBid() {
		return Bid;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	public TransportCar getTran1() {
		return tran1;
	}
	public void setTran1(TransportCar tran1) {
		this.tran1 = tran1;
	}
	public TransportCar getTran2() {
		return tran2;
	}
	public void setTran2(TransportCar tran2) {
		this.tran2 = tran2;
	}
	public TransportCar getTran3() {
		return tran3;
	}
	public void setTran3(TransportCar tran3) {
		this.tran3 = tran3;
	}
	public LinkedList<Baggage> getQueue() {
		return queue;
	}
	public void setQueue(LinkedList<Baggage> queue) {
		this.queue = queue;
	}
	
	//行李加入一级分拣站
		public void push(Baggage bag){
			setnowplace(bag);
			queue.add(bag);
		}
		
		//告知数据库行李到达本站
		public void setnowplace(Baggage bag){
			System.out.println("i am "+bag.getRFID()+" now i am in "+Bid);
			SortSqlAccess.relocation(bag.getRFID(), this.Bid);
		}
		
		public void secondsort(Baggage e){
			if(e.getRFID().charAt(5)=='1'||e.getRFID().charAt(5)=='4'||e.getRFID().charAt(5)=='7'){
					this.tran1.push(e);
			}
			else if(e.getRFID().charAt(5)=='2'||e.getRFID().charAt(5)=='5'||e.getRFID().charAt(5)=='8'){
					this.tran2.push(e);
			}
			else if(e.getRFID().charAt(5)=='3'||e.getRFID().charAt(5)=='6'||e.getRFID().charAt(5)=='9'){
					this.tran3.push(e);
			}
		}
		
		public void run(){
			while(true){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(queue.size()>0){
					secondsort(this.queue.poll());
				}
			}
		}
	
}
