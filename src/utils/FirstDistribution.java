package utils;
import java.util.*;


public class FirstDistribution extends Thread{
	private String Bid=null;
	private SecondDistribution sec11=null;
	private SecondDistribution sec12=null;
	private SecondDistribution sec21=null;
	private SecondDistribution sec22=null;
	private SecondDistribution sec31=null;
	private SecondDistribution sec32=null;
	private LinkedList<Baggage> queue1=new LinkedList<Baggage>();
	private LinkedList<Baggage> queue2=new LinkedList<Baggage>();

	public FirstDistribution(String str){
		this.Bid=str;
	}
	public String getBid() {
		return Bid;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	public SecondDistribution getSec11() {
		return sec11;
	}
	public void setSec11(SecondDistribution sec11) {
		this.sec11 = sec11;
	}
	public SecondDistribution getSec12() {
		return sec12;
	}
	public void setSec12(SecondDistribution sec12) {
		this.sec12 = sec12;
	}
	public SecondDistribution getSec21() {
		return sec21;
	}
	public void setSec21(SecondDistribution sec21) {
		this.sec21 = sec21;
	}
	public SecondDistribution getSec22() {
		return sec22;
	}
	public void setSec22(SecondDistribution sec22) {
		this.sec22 = sec22;
	}
	public SecondDistribution getSec31() {
		return sec31;
	}
	public void setSec31(SecondDistribution sec31) {
		this.sec31 = sec31;
	}
	public SecondDistribution getSec32() {
		return sec32;
	}
	public void setSec32(SecondDistribution sec32) {
		this.sec32 = sec32;
	}
	public LinkedList<Baggage> getQueue1() {
		return queue1;
	}
	public void setQueue1(LinkedList<Baggage> queue1) {
		this.queue1 = queue1;
	}
	public LinkedList<Baggage> getQueue2() {
		return queue2;
	}
	public void setQueue2(LinkedList<Baggage> queue2) {
		this.queue2 = queue2;
	}

	
	//行李加入一级分拣站
	public void push(Baggage bag){
		setnowplace(bag);
		if(this.queue1==null||this.queue2==null){
			System.out.println("null queue");
		}
		else{
			if(queue1.size()<=queue2.size())
				queue1.add(bag);
			else
				queue2.add(bag);
		}
	}
	
	//告知数据库行李到达本站
	public void setnowplace(Baggage bag){
		System.out.println("i am "+bag.getRFID()+" now i am in "+Bid);
		SortSqlAccess.relocation(bag.getRFID(), this.Bid);
	}
	
	public void firstsort(Baggage e){
		if(e.getRFID().charAt(5)=='1'||e.getRFID().charAt(5)=='2'||e.getRFID().charAt(5)=='3'){
			if(this.sec11.getQueue().size()<=this.sec12.getQueue().size()){
				this.sec11.push(e);
			}
			else
				this.sec12.push(e);
		}
		else if(e.getRFID().charAt(5)=='4'||e.getRFID().charAt(5)=='5'||e.getRFID().charAt(5)=='6'){
			if(this.sec21.getQueue().size()<=this.sec22.getQueue().size()){
				this.sec21.push(e);
			}
			else
				this.sec22.push(e);
		}
		else if(e.getRFID().charAt(5)=='7'||e.getRFID().charAt(5)=='8'||e.getRFID().charAt(5)=='9'){
			if(this.sec31.getQueue().size()<=this.sec32.getQueue().size()){
				this.sec31.push(e);
			}
			else
				this.sec32.push(e);
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
			if(queue1.size()>0){
				firstsort(queue1.poll());
			}
			if(queue2.size()>0){
				firstsort(queue2.poll());
			}
		}
	}
	
	
}
