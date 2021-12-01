package model;
public class Server{
	/**
	*is the cache memorie of the server
	*/
	private double cache;
	/**
	*is the ram of the server
	*/
	private double ram;
	/**
	*is the memorie of each disk
	*/
	private double disksCapacity;
	/**
	*is the number of disks
	*/
	private int numDisks;
	/**
	*is the number of processors
	*/
	private int processors;
	/**
	*is the brand of the processor
	*/
	private Brand brand;
	/**
	*Constructor of server
	@param cache is the cache memorie of the server
	@param ram is the ram of the server
	@param disksCapacity is the memorie of each disk
	@param numDisks is the number of disks
	@param processors is the number of processors
	@param brand is the brand of the processor
	*/
	public Server(double cache,double ram,double disksCapacity,int numDisks,int processors,Brand brand){
		this.cache=cache;
		this.ram=ram;
		this.disksCapacity=disksCapacity;
		this.numDisks=numDisks;
		this.processors=processors;
		this.brand=brand;
	}
	public double getCache(){
		return cache;
	}
	public void setCache(double cache){
		this.cache=cache;
	}
	public double getRam(){
		return ram;
	}
	public void setRam(double ram){
		this.ram=ram;
	}
	public double getDisksCapacity(){
		return disksCapacity;
	}
	
	public void setDisksCapacity(int disksCapacity){
		this.disksCapacity=disksCapacity;
	}
	public int getNumDisks(){
		return numDisks;
	}
	public void setNumDisks(int numDisks){
		this.numDisks=numDisks;
	}
	public int getProcessors(){
		return processors;
	}
	public void setProcessors(int processors){
		this.processors=processors;
	}
	public Brand getBrand(){
		return brand;
	}
	public void setBrand(int index){
		switch(index){
			case 1:
				brand=Brand.INTEL;
				break;
			case 2:
				brand=Brand.AMD;
				break;
		}
	}
	
	public String toString(){
		return "Ram: "+ram+"Gb ||"+" Disk capacity "+(numDisks*disksCapacity)+"Tb";
	}
}