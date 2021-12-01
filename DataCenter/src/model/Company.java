package model;
public class Company{
	/**
	*is the nit of the company
	*/
	private String nit;
	/**
	*is the name of the company
	*/
	private String name;
	/**
	*is the array with the rooms rented by the company
	*/
	private MiniRoom [] rentedRooms;
	/**
	*is the possible amount of rents
	*/
	public static final int NUMRENTS=DataCenter.COLUMNS*DataCenter.ROWS;
	/**
	*constructor of company
	@param nit is the nit of the company
	@param name is the name of the company
	*/
	public Company(String nit,String name){
		this.nit=nit;
		this.name=name;
		rentedRooms=new MiniRoom [NUMRENTS];
	}
	/**
	*Constructor of company for project
	*/
	public Company(){
		nit="890.316.745-5";
		name="Icesi";
		rentedRooms=new MiniRoom [NUMRENTS];
	}
	public void setNit(String nit){
		this.nit=nit;
	}
	public String getNit(){
		return nit;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setRentedRooms(MiniRoom room){
		int i;
		
		boolean flag=false;
		
		for(i=0;i<rentedRooms.length&&!flag;i++){
			if(rentedRooms[i]==null){
				rentedRooms[i]=room;
				flag=true;
			}
		}
	}
	
	public MiniRoom [] getRentedRooms(){
		return rentedRooms;
	}
	
	public MiniRoom getARoom(int index){
		return rentedRooms[index];
	}
}