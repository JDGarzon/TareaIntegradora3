package ui;
import java.util.Scanner;
import model.DataCenter;
public class Main{
	private Scanner sc;
	private DataCenter dataCenter;
	/**
	Contructor of main
	*/
	public Main(){
		sc=new Scanner(System.in);
	}
	/**
	initialize the data center
	*/
	public void init(){
		double baseValue;
		System.out.println("**Starting program**");
		System.out.println("Digite the base value for the minirooms");
		baseValue=sc.nextDouble();
		sc.nextLine();
		dataCenter=new DataCenter(baseValue);
	}
	/**
	method main
	*/
	public static void main(String [] args){	
		Main sys=new Main();
		sys.init();
		sys.menu();
	}
	
	/**
	menu
	*/
	public void menu(){
		int selection;
		boolean flag=false;
		do{
			System.out.println("_____Menu_____");
			System.out.println("1. List of rooms");
			System.out.println("2. Rent a room");
			System.out.println("3. Free room");
			System.out.println("4. Data Center Map");
			System.out.println("5. Turn on all minirooms");
			System.out.println("6. Turn of minirooms");
			System.out.println("0. Finish");
			selection=sc.nextInt();
			sc.nextLine();
			switch(selection){
				case 0:
					flag=true;
					break;
				case 1:
					printRoomsInformation();
					break;
				case 2:
					rentARoom();
					break;
				case 3:
					freeRoom();
					break;
				case 4:
					dataCenterMap();
					break;	
				case 5:
					turnOn();
					break;
				case 6:
					turnOff();
					break;
				default:
					System.out.println("Wrong selection");
				
			}
		}while(!flag);
	}
	/**
	Turn off the minirooms
	*/
	public void turnOff(){
		String option;
		int index=-1;
		String mesage="";
		System.out.println("Digite a letter");
		System.out.println("L: turns off the first minirooms of all corridors, along with the minirooms of the first corridor");
		System.out.println("Z: turn off the minirooms of the first and last corridor, along with the minirooms of the reverse diagonal");
		System.out.println("H: turns off the mini-rooms located in the odd-numbered corridors");
		System.out.println("O: turns off the mini-rooms located in the windows");
		System.out.println("M: Turn off a column");
		System.out.println("P: turns off a corridor");
		option=sc.nextLine();
		if(option.equalsIgnoreCase("l")){
			dataCenter.turnOff(1,index);
		}else if(option.equalsIgnoreCase("z")){
			dataCenter.turnOff(2,index);
		}else if(option.equalsIgnoreCase("h")){
			dataCenter.turnOff(3,index);
		}else if(option.equalsIgnoreCase("o")){
			dataCenter.turnOff(4,index);
		}else if(option.equalsIgnoreCase("m")){
			System.out.println("Digite a column 1 - 50");
			index=sc.nextInt();
			sc.nextLine();
			dataCenter.turnOff(5,index-1);
		}else if(option.equalsIgnoreCase("p")){
			System.out.println("Digite a corridor 1 - 8");
			index=sc.nextInt();
			sc.nextLine();
			dataCenter.turnOff(6,index-1);
		}else System.out.println("Wrong selection");
		dataCenterMap();
	}
	/**
	Print thr rooms information
	*/
	public void printRoomsInformation(){
		int i;
		int j;
		int count=0;
		System.out.println(dataCenter.getRoomList());
	}
	
	/**
	*Rent a miniroom
	*/
	public void rentARoom(){
		String name;
		String nit;
		String number="";
		int selection;
		
		do{
			System.out.println("Type of assignation");
			System.out.println("1. Company");
			System.out.println("2. Project");
			selection=sc.nextInt();
			sc.nextLine();
		}while(selection<1&&selection>2);
		
		int index=-1;
		if(selection==1){
			do{
				System.out.println("1.Add a company");
				System.out.println("2.Select a company");
				selection=sc.nextInt();
				sc.nextLine();
			}while(selection<1&&selection>2);
			if(selection==1){
				System.out.println("Companies name");
				name=sc.nextLine();
				System.out.println("Nit");
				nit=sc.nextLine();
				index=dataCenter.createACompany(nit,name);
				if(index==-1){
					System.out.println("There is not space for another company");
				}
			}else {
				index=indexCompany();
				if(index==-1){
					System.out.println("There is not a company to select");
				}
			}
			if(index>=0&&index<400){
				renting(index,1);
			}
		}else {
			System.out.println("Number");
			number=sc.nextLine();
			index=dataCenter.addAProject(number);
			if(index!=-1){
				renting(index,2);
			}
		}	
	}
	/**
	* select a company
	@return index, is the index of the company
	*/
	public int indexCompany(){
		int index=-1;
		if(dataCenter.thereIsACompany()>0){
			do{
				System.out.println(dataCenter.companiesList());
				index=sc.nextInt();
				sc.nextLine();
				index--;
			}while(index<0&&index>dataCenter.thereIsACompany());
		}
		return index;
	}
	/**
	*Continuation of rentARoom
	@param index, is the index of the company or project
	@param type, if is a company 1, 2 if is a project
	*/
	public void renting(int index, int type){
		int number=0;
		int [] miniroom=new int [2];
		int i;
		int day,mounth,year;
		int selection;
		System.out.println("Select a room");
		System.out.println("Digite the number");
		printRoomsInformation();
		int numberSelection=sc.nextInt();
		sc.nextLine();
		System.out.println("Day");
		day=sc.nextInt();
		sc.nextLine();
		System.out.println("Mounth");
		mounth=sc.nextInt();
		sc.nextLine();
		System.out.println("Year");
		year=sc.nextInt();
		sc.nextLine();
		System.out.println("Number of servers");
		number=sc.nextInt();
		sc.nextLine();
		double value=dataCenter.calculateValue(number,number);
		System.out.println("The value is: "+value);
		
		do{
			System.out.println("Continue:");
			System.out.println("1. Yes");
			System.out.println("2. No");
			selection=sc.nextInt();
			sc.nextLine();
		}while(selection<1&&selection>2);
		
		if(selection==1){
			if(numberSelection>0&&numberSelection<=400){
				miniroom=dataCenter.rentARoom(numberSelection,index,type,number,day,mounth,year);
				for(i=0;i<number;i++){
					rackInformation(miniroom);
				}
			}
		}else System.out.println("Thanks");
		
	}
	/**
	*Add a server in the RACK
	@param miniroom, is an array with the value of the colum in 0 position and the value of the row in position 1
	*/
	public void rackInformation(int [] miniroom){
		double cache;
		double ram;
		double diskCapacity;
		int processors;
		int indexBrand;
		int disks;
		System.out.println("Cache memorie (Gb)");
		cache=sc.nextDouble();
		sc.nextLine();
		System.out.println("RAM (Gb)");
		ram=sc.nextDouble();
		sc.nextLine();
		System.out.println("Quantity of disks");
		disks=sc.nextInt();
		sc.nextLine();
		System.out.println("Disks capacity (Tb)");
		diskCapacity=sc.nextDouble();
		sc.nextLine();
		System.out.println("Quantity of processors");
		processors=sc.nextInt();
		sc.nextLine();
		do{
			System.out.println("Brand");
			System.out.println("1. Intel");
			System.out.println("2. AMD");
			indexBrand=sc.nextInt();
			sc.nextLine();	
		}while(indexBrand<1&&indexBrand>2);
		dataCenter.addServers(miniroom,cache,ram,diskCapacity,disks,processors,indexBrand);
	}
	/**
	*Free a room
	*/
	public void freeRoom(){
		int selection;
		int index=0;
		do{
			System.out.println("1. Free a room of a company");
			System.out.println("2. Free all company´s rooms");
			System.out.println("3. Free a project´s rooms");
			selection=sc.nextInt();
			sc.nextLine();
		}while(selection<1&&selection>3);
		
		
		if(selection==1||selection==2){
			index=indexCompany();
			if(index!=-1){
				if(selection==1){
					System.out.println(dataCenter.getReservedRooms(index));
					index=sc.nextInt();
					sc.nextLine();
					System.out.println(dataCenter.freeARoom(index));
				}else if(selection==2){
					dataCenter.deleteACompany(index);
					System.out.println("Companys information deleted");
				}	
			}else System.out.println("There is not a company");
			
		}
		 if(selection==3){
			System.out.println("Digite the projects number");
			index=sc.nextInt();
			sc.nextLine();
			if(dataCenter.thereIsAProject(index)){
				System.out.println(dataCenter.deleteAproject(index));
			}else System.out.println("Wrong project index");
			
		}
	}
	/**
	*Print the map of the data center
	*/
	public void dataCenterMap(){
		System.out.println(dataCenter.dataCenterMap());
	}
	/**
	*Turn on all the servers 
	*/
	public void turnOn(){
		System.out.println(dataCenter.turnOn());
	}
}