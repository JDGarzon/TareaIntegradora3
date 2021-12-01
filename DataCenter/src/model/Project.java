package model;

public class Project extends Company{
	/**
	*is the number of the project as a String
	*/
	private String number;
	/**
	*constructor of project
	@param number is the number of the project as a String
	*/
	public Project(String number){
		super();
		this.number=number;
	}
	
	public void setNumber(String num){
		this.number=num;
	}
	public String getNumber(){
		return number;
	}
}