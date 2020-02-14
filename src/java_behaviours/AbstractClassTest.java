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

// abstract calss can be used to have default implementation in java 7
// java 8 interfaces have default impl.
// but java 7 abstract solution does not support multiple inheritance

public class AbstractClassTest {
    public static void main(String args[]){
      Test t = new Test();
      //TestAb tab = new TestAb(); //compiler error
      Derived d = new Derived();
      TestAb d2 = new Derived();
      
      d2.fun2(); //d2 cant execute haha due to reference type being parent
      d.haha();
    }
}

class Test { 
    void fun(){
      
    }; 
} 

abstract class TestAb {
  TestAb() { System.out.println("Base Constructor Called"); }
    abstract void fun(); 
    
    // default impl
    void fun2(){
       System.out.println("hola amigo");
    }
} 

class Derived extends TestAb { 
  Derived() { System.out.println("Derived Constructor Called"); }
    void fun(){
      
    }; 
    
    void haha(){
      
    }
} 