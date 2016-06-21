package utils;
public class Access{
	private FirstDistribution access=null;
	public void SystemStart(){
		FirstDistribution A1=new FirstDistribution("A1");
		
		SecondDistribution B01=new SecondDistribution("B01");
		SecondDistribution B02=new SecondDistribution("B02");
		SecondDistribution C01=new SecondDistribution("C01");
		SecondDistribution C02=new SecondDistribution("C02");
		SecondDistribution D01=new SecondDistribution("D01");
		SecondDistribution D02=new SecondDistribution("D02");
		
		TransportCar B1=new TransportCar("B1");
		TransportCar B2=new TransportCar("B2");
		TransportCar B3=new TransportCar("B3");
		TransportCar C1=new TransportCar("C1");
		TransportCar C2=new TransportCar("C2");
		TransportCar C3=new TransportCar("C3");
		TransportCar D1=new TransportCar("D1");
		TransportCar D2=new TransportCar("D2");
		TransportCar D3=new TransportCar("D3");
		
		Plane P1=new Plane("P1");
		Plane P2=new Plane("P2");
		Plane P3=new Plane("P3");
		Plane P4=new Plane("P4");
		Plane P5=new Plane("P5");
		Plane P6=new Plane("P6");
		Plane P7=new Plane("P7");
		Plane P8=new Plane("P8");
		Plane P9=new Plane("P9");
		
		this.access=A1;
		
		A1.setSec11(B01);
		A1.setSec12(B02);
		A1.setSec21(C01);
		A1.setSec22(C02);
		A1.setSec31(D01);
		A1.setSec32(D02);
		
		B01.setTran1(B1);
		B01.setTran2(B2);
		B01.setTran3(B3);
		B02.setTran1(B1);
		B02.setTran2(B2);
		B02.setTran3(B3);
		
		C01.setTran1(C1);
		C01.setTran2(C2);
		C01.setTran3(C3);		
		C02.setTran1(C1);
		C02.setTran2(C2);
		C02.setTran3(C3);
		
		D01.setTran1(D1);
		D01.setTran2(D2);
		D01.setTran3(D3);		
		D02.setTran1(D1);
		D02.setTran2(D2);
		D02.setTran3(D3);
		
		B1.setPlane(P1);
		B2.setPlane(P2);
		B3.setPlane(P3);
		C1.setPlane(P4);
		C2.setPlane(P5);
		C3.setPlane(P6);
		D1.setPlane(P7);
		D2.setPlane(P8);
		D3.setPlane(P9);
		
		A1.start();
		
		B01.start();
		B02.start();
		C01.start();
		C02.start();
		D01.start();
		D02.start();
		
		B1.start();
		B2.start();
		B3.start();
		C1.start();
		C2.start();
		C3.start();
		D1.start();
		D2.start();
		D3.start();
		
		
		
	}
	public void BaggageSort(Baggage e){
		this.access.push(e);
	}
}
