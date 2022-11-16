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
// volatile makes sure object is not cached in cpu and always use main memory to read an write
// volatile gaurantees visibility and not atomicity
// to gaurantee atomocity we nee synchronized or atomic variables.
// atmoicity is not desired in singleton because only once thread can create new instance, thanks to synchronized


public class SingletonClass {
  private static SingletonClass obj;
  // making private so taht no one can call
  private SingletonClass(){
    
  }
  
  public static SingletonClass getInstance(){
    if(obj==null){
      obj = new SingletonClass();
    }
    return obj;
  }
  
  public static SingletonClass getInstanceThreadSafe(){
    if(obj==null){ // this helps increase perfomance one object is initialized.
      
      synchronized(obj){
        if(obj==null){ // this check is required because waiting threads should not create another object.
          obj = new SingletonClass();
        }
      }
    }
    return obj;
  }
  
  public static void main(String args[]){
    
  }
}
