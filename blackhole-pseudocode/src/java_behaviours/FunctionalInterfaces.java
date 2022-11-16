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


// lambda expressions are instances of functional interfaces
// lambda expression can only be applied on function interface because
// only functional interfaces can have one abstarct methods which lambdas 
// can write implementation for in the block
interface FuncTest{
  void haha();
  default void hehe(){
    
  }
  static void huhu(){
    
  }
}

interface NonFunc{
  void haha();
  void huhu();
}

public class FunctionalInterfaces {
  public static void main(String args[]){
    FuncTest f = () -> {
      System.out.println("haha");
    };     // ignore error if no semicolon, its from dumb netbeans
    f.haha();
    
    // compiler error stating NonFunc is nota functional interface.
//    NonFunc f2 = () -> {
//      System.out.println("haha");
//    }
  }
}
