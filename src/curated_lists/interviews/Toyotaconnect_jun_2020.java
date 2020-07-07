/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curated_lists.interviews;

/**
 *
 * @author vshanmugham
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*; 

public class Toyotaconnect_jun_2020 {
  
}


/*
Concurrent read/write
Programming challenge description:
Write a program that concurrently does the following three tasks a) reads a number from standard input, one number per line and emits it to a channel/data structure b) reads the numbers emitted from (a) and prints only the odd numbers one per line in the format "odd:"<number> c) reads the numbers emitted from (a) and prints only the even numbers one per line in the format "even:"<number>. Ignore the scoring of the test results.

Input:
See Full Description

Output:
See Full Description

Test 1
Test Input
Download Test 1 Input
1
233
322
45
22
24
66
24
87
3831
Expected Output
Download Test 1 Input
odd:
Test 2
Test Input
Download Test 2 Input
1
233
322
45
22
24
66
24
87
3831
Expected Output
Download Test 2 Input
odd:

*/

class Main {
  /**
   * Iterate through each line of input.
   */
  public static ConcurrentLinkedQueue<Integer> q = new ConcurrentLinkedQueue<Integer>();
  public static boolean end = false;
  
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    
//    InputThread t1 = new InputThread("t1",in);
//    InputThread t2 = new InputThread("t2",in);
//    t1.start();
    q.add(1); 
    q.add(2); 
    q.add(3); 
    q.add(4); 
    q.add(5); 
    q.add(6); 
    q.add(7); 
    q.add(8); 
    q.add(9); 
    q.add(10);
//    t2.start();
    OddThread o = new OddThread();
    EvenThread e = new EvenThread();
    o.start();
    e.start();
    System.out.println("done:");
    
  }
}

class OddThread extends Thread {
    public void run() {
        while(!Main.end){
          //synchronized(Main.q){
            while(!Main.q.isEmpty()){
              int x = Main.q.poll();
              if(x%2==1) System.out.println("odd:"+x);
            }
            if(Main.q.isEmpty()) Main.end = true;
          //}
        }
    }
}

class EvenThread extends Thread {

    public void run() {
        while(!Main.end){
          //synchronized(Main.q){
            while(!Main.q.isEmpty()){
              int x = Main.q.poll();
              if(x%2==0) System.out.println("even:"+x);
            }
            if(Main.q.isEmpty()) Main.end = true;
          //}
        }
    }
}

class InputThread extends Thread {
    private BufferedReader in;
    private String name;

    public InputThread(String name, BufferedReader in)  {
      this.name = name;  
      this.in = in;
    }

    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                Main.q.add(Integer.valueOf(line));
                //System.out.println(this.name+":"+Integer.valueOf(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



//Question2
// CourseScheduleLexographic