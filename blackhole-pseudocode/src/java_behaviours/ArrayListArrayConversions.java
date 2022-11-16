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

import java.util.*;

public class ArrayListArrayConversions {
  public static void main(String args[]){
    
    //Best way to convert arraylist to array is iteration
    List<Integer> x = new ArrayList();
    x.add(1);x.add(2);
    //int y[] = x.toArray(new int[x.size()]);
    Integer y[] = x.toArray(new Integer[x.size()]);
    
    List<List<Integer>> w = new ArrayList();
    int z[][] = w.toArray(new int[1][1]);
    
    
    
  }
}
