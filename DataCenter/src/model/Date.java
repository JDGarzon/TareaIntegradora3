package model;

public class Date{
	/**
	*is the day of the date
	*/
	private int day;
	/**
	*is the mount of the date
	*/
	private int month;
	/**
	*is the year of the date
	*/
	private int year;
	/**
	*is the constructor of date
	@param newDay is the day of the date
	@param newMonth is the mount of the date 
	@param newYear is the year of the date
	*/
	public Date(int newDay,int newMonth, int newYear){
		day = newDay;
		month=newMonth;
		year=newYear;
	}
	
	public void setDay(int newDay){
		day=newDay;
	}
	public int getDay(){
		return day;
	}
	public void setMonth(int newMonth){
		month=newMonth;
	}
	public int getMonth(){
		return month;
	}
	public void setYear(int newYear){
		year=newYear;
	}
	public int getYear(){
		return year;
	}
	
	
	public String toString(){
		return "	Day:"+day+"\n"+"	Month:"+month+"\n"+"	Year:"+year;
	}
}