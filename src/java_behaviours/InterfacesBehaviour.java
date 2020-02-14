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

//All variables declared inside interface are implicitly public static final variables
// x and y both are static
interface AA{
  int x = 10;
  static int y = 20;
  void haha();
  default void huhu(){
    System.out.println("aatha,");
  }
  static void hehe(){
    
  }
  
// not possible, compiler error stating abstract methods cant have body
//  void haha(){
//    
//  }
  
}

//super in class make sense because there is no multiple inheritance
// there can be many interfaces implemented, so interface.super makes sense

class Ganth implements AA{
    // public here is necessary as we cannot reduce visibility of interfaces to private
    public void haha(){
      System.out.println("thavasi");
    }
    
    public void huhu(){
      AA.super.huhu();
      System.out.println("enakku vera vazhi therila");
    }
    
    // interface methods cannot be overriden(literally now way of doing it), this will be associated tho Ganth class
    public static void hehe(){
      
    }

}

public class InterfacesBehaviour {
  public static void main(String args[]){
    AA.hehe();
    System.out.println(AA.x);
    //AA.x = 100; //cannot change final variable
    Ganth g = new Ganth();
    g.haha();
    g.huhu();
  }
}
