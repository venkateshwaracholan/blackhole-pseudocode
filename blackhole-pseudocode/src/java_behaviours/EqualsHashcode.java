/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_behaviours;

/**
 *
 * @author vshanmugham
 */
// hascode not being unique will caluse only perfomance problem as 
// different objects will be put inside same bucket.
// anyway hashmap is growing and its initial capacity is 16 in which 
// different objects are so likely to fall under same buckets
// anyways equals determine the true equality.


class Employee{
  private int id;
  private String name;
  
  Employee(int id,String name){
    this.id = id;
    this.name=name;
  }
  
  public boolean equals(Object obj){
    if(this==obj)
      return true;
    if(obj==null || obj.getClass()!=this.getClass())
      return false;
    
    Employee e = (Employee)obj;
    return (e.id==this.id && e.name.equals(this.name));
  }
  
  public int hashCode(){
    int result = 17;
    //result = 31 * result + name.hashCode();
    //result = 31 * result + id;
    return result;
  }
  
  public static void main(String args[]){
    Employee e1 = new Employee(1,"a");
    Employee e2 = new Employee(1,"a");
    System.out.println(e1.hashCode());
    System.out.println(e1.hashCode());
    System.out.println(e1.equals(e2));
  }
  
}

public class EqualsHashcode {
  
}
