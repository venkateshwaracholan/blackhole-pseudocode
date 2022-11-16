/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author venkateshwarans
 */
public class MapUsage {
  public static void main(String args[]){
    Map<Integer[], Integer> m = new HashMap();
    Integer a[] = new Integer[]{0,0};
    m.put(a,0);
    System.out.println(m.get(a));
    System.out.println(m.get(new Integer[]{0,0}));
  }
}
