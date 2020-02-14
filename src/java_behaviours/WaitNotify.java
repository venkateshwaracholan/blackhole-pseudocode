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
// Java program to demonstrate inter-thread communication 
// (wait(), join() and notify()) in Java 

/*
1. Most obvious difference, both are present different packages, the wait()
method is declared in java.lang.Object class while join() is declared in java.lang.Thread class.
2. The wait() is used for inter-thread communication while the join() is used 
for adding sequencing between multiple threads, one thread starts execution after first thread execution finished.
3. We can start a waiting thread (went into this state by calling wait()) by 
using notify() and notifyAll() method but we can not break the waiting imposed
by join without unless or interruption the thread on which join is called has execution finished.
4. One most important difference between wait() and join() that is wait() must
be called from synchronized context i.e. synchronized block or method otherwise
it will throw IllegalMonitorStateException but On the other hand, we can call 
join() method with and without synchronized context in Java.

*/

import java.util.Scanner; 
public class WaitNotify 
{ 
    public static void main(String[] args) 
                           throws InterruptedException 
    { 
        final PC pc = new PC(); 
  
        // Create a thread object that calls pc.produce() 
        Thread t1 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    pc.produce(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
        // Create another thread object that calls 
        // pc.consume() 
        Thread t2 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    pc.consume(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
        // Start both threads 
        t1.start(); 
        t2.start(); 
  
        // t1 finishes before t2 
        t1.join(); 
        t2.join(); 
    } 
  
    // PC (Produce Consumer) class with produce() and 
    // consume() methods. 
    public static class PC 
    { 
        // Prints a string and waits for consume() 
        public void produce()throws InterruptedException 
        { 
            // synchronized block ensures only one thread 
            // running at a time. 
            synchronized(this) 
            { 
                System.out.println("producer thread running"); 
  
                // releases the lock on shared resource 
                //wait(); 
                Thread.sleep(2000); 
                wait();
                // and waits till some other method invokes notify(). 
                System.out.println("Resumed"); 
                //notify();
            } 
        } 
  
        // Sleeps for some time and waits for a key press. After key 
        // is pressed, it notifies produce(). 
        public void consume()throws InterruptedException 
        { 
            // this makes the produce thread to run first. 
            //sThread.sleep(1000); 
//            Scanner s = new Scanner(System.in); 
  
            // synchronized block ensures only one thread 
            // running at a time. 
            synchronized(this) 
            { 
                System.out.println("consumer thread"); 
                //s.nextLine(); 
                //System.out.println("Return key pressed"); 
  
                // notifies the produce thread that it 
                // can wake up. 
                notify(); 
                
                //wait();
  
                // Sleep 
                Thread.sleep(2000); 
                System.out.println("hahah"); 
            } 
        } 
    } 
} 