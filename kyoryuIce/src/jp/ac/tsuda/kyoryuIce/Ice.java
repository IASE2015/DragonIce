package jp.ac.tsuda.kyoryuIce;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.net.URL;

@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Ice implements Serializable{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
    @Persistent
    private int number;
    @Persistent
	private String name;
    @Persistent
	private int price;
	
	public Ice(int number,String name,int price){
		super();
		this.number = number;
		this.name = name;
		this.price = price;
	}
	public Ice(){}
	
	public Long getId(){ return id; }
	public int getNumber(){ return number; }
	public String getName(){ return name; }
	public int getPrice(){ return price; }
	
	
}
