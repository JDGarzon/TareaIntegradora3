package model;
public class DataCenter{
	/**
	*rooms , is a matrix of all minirooms
	*/
	private MiniRoom [][] rooms;
	/**
	*companies, is an array with all companies
	*/
	private Company[] companies;
	/**
	*projects, is an array of all projects
	*/
	private Project[] projects;
	/**
	* COLUMNS is the number of colums in the matrix
	*/
	public static final int COLUMNS=8;
	/**
	*ROWS is the number of rows in the matrix
	*/
	public static final int ROWS=50;
	/**
	*NUMBER_OF_TENANTS is the max amount of posible tenants of rooms
	*/
	public static final int NUMBER_OF_TENANTS=400;
	/**
	*Constructor of DataCenter
	@param baseValue, is the base value of the minirooms
	*/
	public DataCenter(double baseValue){
		rooms=new MiniRoom[COLUMNS][ROWS];
		companies=new Company[NUMBER_OF_TENANTS];
		projects=new Project[NUMBER_OF_TENANTS];
		createRooms(baseValue);
	}
	 /**
	*intialize the rooms
	@param baseValue, is the base value of the minirooms
	*/
	public void createRooms(double baseValue){
		MiniRoom miniRoom;
		int [] position=new int [2];
		int count=0;
		for(int i=0;i<COLUMNS;i++){
			for(int j=0;j<ROWS;j++){
				count++;
				position[0]=i;
				position[1]=j;
				miniRoom=new MiniRoom(position,count,baseValue);
				rooms[i][j]=miniRoom;
			}
		}
		
	}
	/**
	*get the information of all the unreserved rooms 
	@return list, is the list of rooms
	*/
	public String getRoomList(){
		String list="";
		for(int i=0;i<COLUMNS;i++){
			for(int j=0;j<ROWS;j++){
				if(rooms[i][j].getReserved()==null){
					list+=rooms[i][j]+"\n";
				}
			}
		}
		return list;
	}
	/**
	*get the number of the rooms reserved by a company
	@param index is the index of the company
	@return list is the list of rooms of a company
	*/
	public String getReservedRooms(int index){
		int i;
		MiniRoom [] companyRooms=companies[index].getRentedRooms();
		String list="";
		for(i=0;i<companyRooms.length;i++){
			if(companyRooms[i]!=null){
				list+="Room: "+companyRooms[i].getNumber()+"\n";
			}
		}
		return list;
	}
	/**
	*calculate the amount of companies
	@return count, is the amount of companies
	*/
	public int thereIsACompany(){
		int count=0;
		for(int i=0;i<companies.length;i++){
			if(companies[i]!=null){
				count++;
			}
		}
		return count;
	}
	/**
	*Search all companies
	@return list, is the list of companies
	*/
	public String companiesList(){
		String list="";
		for(int i=0;i<companies.length;i++){
			if(companies[i]!=null){
				list+=(i+1)+". "+companies[i].getName()+"\n";
			}
		}
		return list;
	}
	/**
	*
	@param nit, 
	@param name,
	@return index
	*/
	public int createACompany(String nit,String name){
		int i;
		int index=-1;
		boolean flag=false;
		for(i=0;i<companies.length&&!flag;i++){
			if(companies[i]==null){
				companies[i]=new Company(nit,name);
				index=i;
				flag=true;
			}
		}
		return index;
	}
	/**
	*Rent a room
	@param number is the number of the room
	@param index is the index of the company or project
	@param type 1 if is a company, 2 if is a project
	@param servers is the amount of servers
	@param day is the day of the reserve
	@param mounth is the mount of the reserve
	@param year is the year of the reserve
	@return position is the position where the reserve was realized position 0 to colum and position 1 to row
	*/
	public int [] rentARoom(int number,int index,int type,int servers,int day,int mounth,int year){
		int i;
		int j;
		int[] position=new int [2]; 
		double value=calculateValue(number,servers);
		for(i=0;i<COLUMNS;i++){
			for(j=0;j<ROWS;j++){
				if(number==rooms[i][j].getNumber()){
					if(type==1){
						companies[index].setRentedRooms(rooms[i][j]);
						rooms[i][j].setReserved(companies[index]);
					}else if (type==2){
						projects[index].setRentedRooms(rooms[i][j]);
						rooms[i][j].setReserved(projects[index]);	
					}
					rooms[i][j].setRack(servers);
					rooms[i][j].setState(State.ON);
					if(servers<4){
						rooms[i][j].setValue(value);
					}
					position[0]=i;
					position[1]=j;
				}
			}
		}
		return position;
	}
	/**
	*Calculate the value of a miniRoom
	@param number is the number of the miniroom
	@param servers, is the number of servers
	@return value, is the value calculated
	*/
	public double calculateValue(int number,int servers){
		int i,j;
		double value=0;
		double recharge=0.15;
		for(i=0;i<COLUMNS;i++){
			for(j=0;j<ROWS;j++){
				if(rooms[i][j].getNumber()==number){
					value=rooms[i][j].getValue();
				}
			}
		}
		if(servers<4){
			value=value+(value*recharge);
		}
		return value;
	}
	/**
	*add a server
	@param miniRoom is the position where the server will be added
	@param cache is the cache memorie of the server
	@param ram is the ram of the server
	@param disksCapacity is the memorie of each disk
	@param numDisks is the number of disks
	@param processors is the number of processors
	@param indexBrand is the index of the brand of the processor
	*/
	public void addServers(int [] miniRoom,double cache,double ram,double disksCapacity,int numDisks,int processors,int indexBrand){
		int i;
		Brand brand=Brand.INTEL;
		if(indexBrand==1){
			brand=Brand.INTEL;
		}else if(indexBrand==2){
			brand=Brand.AMD;
		}
		boolean flag=false;
		for(i=0;i<rooms[miniRoom[0]][miniRoom[1]].getRack().length&&!flag;i++){
			if(rooms[miniRoom[0]][miniRoom[1]].getRack()[i]==null){
				
				rooms[miniRoom[0]][miniRoom[1]].addAServer(i,cache,ram,disksCapacity,numDisks,processors,brand);
				System.out.println(rooms[miniRoom[0]][miniRoom[1]].getRack()[i]);
				flag=true;
			}
		}
	}
	/**
	*Add a project
	@param number is the number of the project
	@return index, is the index where the information of the project is saved
	*/
	public int addAProject(String number){
		int i;
		int index=-1;
		boolean flag=false;
		for(i=0;i<projects.length&&!flag;i++){
			if(projects[i]==null){
				projects[i]=new Project(number);
				index=i;
				flag=true;
			}
		}
		return index;
	}
	/**
	*Determine if a project exist
	@param index is the number of the project
	@return out, true if there is a project with this number, false otherwise
	*/
	public boolean thereIsAProject(int index){
		String number=""+index;
		int i;
		boolean out=false;
		for(i=0;i<projects.length&&!out;i++){
			if(projects[i]!=null){
				if(projects[i].getNumber().equals(number)){
					out=true;
				}
			}
			
		}
		return out;
	}
	/**
	*Delete a project
	@param index is the index of the project
	@return mesage is the information of the rack
	*/
	public String deleteAproject(int index){
		String number=""+index;
		int i;
		for(i=0;i<projects.length;i++){
			if(projects[i]!=null){
				if(projects[i].getNumber().equals(number)){
				index=i;
				}
			}
			
		}
		MiniRoom room=projects[index].getARoom(0);
		String mesage=freeARoom(room.getNumber());
		return mesage;
	}
	/**
	*Free a room
	@param number, is the number of the room
	@return mesage, is the information of the rack
	*/
	public String freeARoom(int number){
		int i;
		int j;
		int count;
		double ram=0;
		double capacity=0;
		String mesage="";
		boolean flag=false;
		for(i=0;i<COLUMNS&&!flag;i++){
			for(j=0;j<ROWS&&!flag;j++){
				if(rooms[i][j].getNumber()==number){
					Server [] rack =rooms[i][j].getRack();
					for(count=0;i<rack.length;count++){
						if(count<rack.length){
							ram+=rack[count].getRam();
							capacity+=rack[count].getDisksCapacity()*rack[count].getNumDisks();
						}
						if(count==rack.length-1){
							break;
						}
					}
					
					flag=true;
					rooms[i][j].free();
					
				}
			}
		}
		mesage="Ram: "+ram+"Gb ||"+" Disk capacity "+capacity+"Tb";
		return mesage;
	}
	/**
	*Form a string in a form of a matrix that represent the data center
	@return out; is the map of the data center
	*/
	public String dataCenterMap(){
		int i;
		int j;
		int index;
		String out="   ";
		for(i=0;i<COLUMNS;i++){
			for(j=0;j<ROWS;j++){
				if(i==0&&j==0){
					for(index=0;index<ROWS;index++){
						if(index<10){
							out+=" "+(index+1)+" ";
						}else out+=(index+1)+" ";
					}
					out+="\n"+(i+1)+". ";
				}
				if(rooms[i][j].getState().equals(State.ON)){
					out+="[O]";
				}else out+="[X]";
			}
			if(i<COLUMNS-1){
				out+="\n" + (i+2) +". ";
			}
		
		}
		return out;
	}
	/**
	*Turn on all the minirooms
	@return map, is the map after turn on all rooms
	*/
	public String turnOn(){
		int i;
		int j;
		for(i=0;i<COLUMNS;i++){
			for(j=0;j<ROWS;j++){
				rooms[i][j].setState(State.ON);
			}
		}
		String map=dataCenterMap();
		return map;
	}
	/**
	*Turn off the servers in an specific way determinated 
	@param type determine the type of turn off
	@param index is the index for 2 specific types of turn off
	*/
	public void turnOff(int type,int index){
		int i;
		int j;
		int countR=ROWS-1;
		int countC=0;
		boolean condition=false;
		
		for(i=0;i<COLUMNS;i++){
			for(j=0;j<ROWS;j++){
				if(type==1){
					if(i==0||j==0){
						condition=true;
					}
				}else if(type==2){
					if(i==COLUMNS-1||i==0||(i==countC&&j==countR)||
						(i==countC&&j==countR-8)||(i==countC&&j==countR-8*2)||
						(i==countC&&j==countR-8*3)||(i==countC&&j==countR-8*4)||
						(i==countC&&j==countR-8*5)||(i==countC&&j==countR-8*6)){
						
						condition=true;
					}
				}else if(type==3){
					if(i==0||i==2||i==4||i==6){
						condition=true;
					}
				}else if(type==4){
					if(rooms[i][j].getWindow().equals(Indicator.YES)){
						condition=true;
					}
				}else if(type==5){
					if(j==index){
						condition=true;
					}
				}else if(type==6){
					if(i==index){
						condition=true;
					}
				}
				if(condition){
					rooms[i][j].setState(State.OFF);
				}
				condition=false;
				
			}
			countR--;
			countC++;
		}
	}
	/**
	*Delete all information of a company
	@param index is the index of the companie to delete
	*/
	public void deleteACompany(int index){
		MiniRoom [] companyRooms= companies[index].getRentedRooms();
		int i;
		for(i=0;i<companyRooms.length;i++){
			if(companyRooms[i]!=null){
				companyRooms[i].free();
			}
			
		}
		companyRooms=null;
		companies[index]=null;
	}
	
}