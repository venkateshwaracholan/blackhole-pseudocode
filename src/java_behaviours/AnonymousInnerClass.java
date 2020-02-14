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

// really useful while extending Thread, implementing runnable and
// passing implemented runnable into the thread.
public class AnonymousInnerClass {
  public static void main(String args[]){
    A a = new A(){
      @Override
      public void haha(){
        System.out.println("poda");
      }
    };
    a.haha();
    
    X x = new X(){
      void haha(){
        System.out.println("poda dei");
      }
    };
    x.haha();
  }
}

// interfaces are abstract;
class X{
  void haha(){
    
  }
}

interface A{
  void haha();
}