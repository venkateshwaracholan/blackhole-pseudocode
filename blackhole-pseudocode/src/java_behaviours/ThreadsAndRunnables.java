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
/*

New - created not run.
Runnable - running
Blocked - blocked by io/ 
Waiting - wait(),
Timed Waiting - wait(time), sleep(time)
Terminated - finished execution

*/

public class ThreadsAndRunnables {
  public static void main(String args[])throws InterruptedException {
    
    Thread t1 = new Thread(){
      public void run(){
        try{
          //Thread.sleep(1000);
        }catch(Exception e){
          
        }
        System.out.println("haha");
      }
    };
    
    Thread t2 = new Thread(new Runnable(){
      public void run(){
        System.out.println("hehe");
      }
    });
    
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("huhu");
  }
}
