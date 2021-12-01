package model;
import java.util.ArrayList;
/*
	public get(){
		return ;
	}
	public void set(){
		
	}
*/
public class MiniRoom{
	/**
	*is the value of the room
	*/
	private double value;
	/**
	*is the original value of the room after its discounts or recharges
	*/
	private double originalValue;
	/**
	*is the base value of the minirooms
	*/
	private double baseValue;
	/**
	*is the company that reserved this room
	*/
	private Company reserved;
	/**
	*is the indicator of the window,YES or NO
	*/
	private Indicator window;
	/**
	*is the position where its saved
	*/
	private int [] matrixPosition;
	/**
	*is the number of the room
	*/
	private int number;
	/**
	*is the server in the room
	*/
	private Server [] rack;
	/**
	*is the state of the room
	*/
	private State state;
	/**
	*is the date of the reserve
	*/
	private Date date;
	
	public MiniRoom(int [] position,int number,double baseValue){
		matrixPosition= new int [position.length];
		for(int i=0;i<position.length;i++){
			matrixPosition[i]=position[i];
		}
		double discount=0;
		this.baseValue=baseValue;
		if(matrixPosition[0]==1||matrixPosition[0]==5){
			discount=0.25;
		}
		if(matrixPosition[0]==6){
			discount=-0.15;
		}
		if(matrixPosition[0]==0||matrixPosition[0]==DataCenter.COLUMNS-1||matrixPosition[1]==0||matrixPosition[1]==DataCenter.ROWS-1){
			discount=-0.1;
			window=Indicator.YES;
		}else window=Indicator.NO;
		value=baseValue+(discount*baseValue);
		originalValue=value;
		this.number=number;
		state=State.OFF;
	}
	public int getNumber(){
		return number;
	}
	public double getValue(){
		return value;
	}
	public void setValue(double value){
		this.value=value;
	}
	
	public Company getReserved(){
		return reserved;
	}
	
	public void setRack(int servers){
		rack= new Server [servers];
	}
	
	public Server [] getRack(){
		return rack;
	}
	
	public void setReserved(Company reserved){
		this.reserved=reserved;
	}
	
	public void setState(State state){
		this.state=state;
	}
	
	public State getState(){
		return state;
	}
	
	public Indicator getWindow(){
		return window;
	}
	
	public void setDate(int day,int mounth,int year){
		Date newDate= new Date(day,mounth,year);
		date=newDate;
	}
	
	public Date getDate(){
		return date;
	}
	/**
	*Add a server 
	@param index, is the index of the server
	@param cache is the cache memorie of the server
	@param ram is the ram of the server
	@param disksCapacity is the memorie of each disk
	@param numDisks is the number of disks
	@param processors is the number of processors
	@param brand is the brand of the processor
	*/
	public void addAServer(int index,double cache,double ram,double disksCapacity,int numDisks,int processors,Brand brand){
		rack[index]=new Server(cache,ram,disksCapacity,numDisks,processors,brand);
	}
	/**
	*free the room
	*/
	public void free(){
		reserved=null;
		rack=null;
		value=originalValue;
		date=null;
		state=State.OFF;
	}
	
	public String toString(){
		String reserve="";
		String thereIsWindow="";
		String space1="";
		String space2="";
		String space3="";
		String space4="";
		if(number<10){
			space1="  ";
		}else if(number<100){
			space1=" ";
		}
		if(reserved==null){
			reserve="Unreserved";
		}else {
			reserve="Reserved";
			space2="  ";
		}
		if(matrixPosition[1]+1<10){
			space3=" ";
		}
		if(window.equals(Indicator.YES)){
			thereIsWindow="There is a Window";
			space4="    ";
		}else thereIsWindow="There is not a Window";
		return "Room "+number+": "+space1+"Passage: "+(matrixPosition[0]+1)+" || "+"Column: "+(matrixPosition[1]+1)+space3+
				" || "+reserve+space2+" ||"+thereIsWindow+space4+" || "+ value;
	}
}