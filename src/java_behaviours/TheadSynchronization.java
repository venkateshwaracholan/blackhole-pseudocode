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

import java.io.*; 
import java.util.*; 

/*
key takeaways:
  threads are only blocked if the passing object of synchoonized is same
  join statement blocks and waits then and there.
  passing ThreadedSend.class to synchronize always blocks thread irrespective of the object

  synchronized in methods will take this as the deciding object
  in the below example,
  sender.send(msg); is called from 2 threads and one is blocked, when 1 thread get executed,
  then sender2 and sender are simultaneously executed becasue they are different objects
  and the other sender2 is blocked until this sender2 executes.
*/
// A Class used to send a message 
class Sender 
{ 
    public void send(String msg) 
    { 
        System.out.println("Sending\t"  + msg ); 
        try
        { 
            Thread.sleep(1000); 
        } 
        catch (Exception e) 
        { 
            System.out.println("Thread  interrupted."); 
        } 
        //System.out.println("\n" + msg + "Sent"); 
    } 
} 
  
// Class for send a message using Threads 
class ThreadedSend extends Thread 
{ 
    private String msg; 
    Sender  sender; 
    Sender sender2;
  
    // Recieves a message object and a string 
    // message to be sent 
    ThreadedSend(String m,  Sender obj, Sender obj2) 
    { 
        msg = m; 
        sender = obj; 
        sender2 = obj2; 
    } 
  
    public void run() 
    { 
        // Only one thread can send a message 
        // at a time. 
        
        synchronized(sender) 
        { 
            // synchronizing the snd object 
            sender.send(msg);
            sender2.send(msg);
        } 
    } 
} 
  
// Driver class 
class TheadSynchronization 
{ 
    public static void main(String args[]) 
    { 
        Sender snd = new Sender(); 
        Sender snd2 = new Sender(); 
        ThreadedSend S1 = 
            new ThreadedSend( " Hi " , snd, snd2 ); 
        ThreadedSend S2 = 
            new ThreadedSend( " Bye " , snd, snd2 ); 
  
        // Start two threads of ThreadedSend type 
        S1.start(); 
        S2.start(); 
  
        // wait for threads to end 
        try
        { 
            S1.join(); 
            System.out.println("haha");
            S2.join(); 
            System.out.println("huhu");
        } 
        catch(Exception e) 
        { 
            System.out.println("Interrupted"); 
        } 
    } 
} 