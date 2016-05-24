package com.buildhappy.structureddata.json;

/** 
 * 只包含基本数据类型的简单实体类 
 * @author TT_2009 
 */  
public class SimpleUser {  
  
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	private int id;  
  
    private String name;  
  
    private String sex;  
  
    private int age;  
  
    private String province;  
    
    public SimpleUser(int id,String name, String sex, int age, String province){
    	this.id = id;
    	this.name = name;
    	this.sex = sex;
    	this.age = age;
    	this.province = province;
    }
    public SimpleUser(){
    	
    }
}  