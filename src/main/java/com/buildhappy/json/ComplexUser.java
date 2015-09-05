package com.buildhappy.json;

import java.util.List;

/** 
 * 包含复杂类型的实体类 
 * @author TT_2009 
 */  
public class ComplexUser extends SimpleUser {  
  
    private List<String> address;  
  
    public ComplexUser() {  
    }  
  
    public ComplexUser(int id, String name,  
            String sex, int age, String province,  
            List<String> address) {  
        super(id, name, sex, age, province);
        this.address = address;  
    }  
  
    public List<String> getAddress() {  
        return address;  
    }  
  
    public void setAddress(List<String> address) {  
        this.address = address;  
    }  
  
} 