/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.queue;

import java.util.PriorityQueue;

/**
 *
 * @author venkateshwarans
 */
public class PriorityQueueUsage {
  public static void main(String args[]){
    System.out.println(4>>1);
    int arr[] = new int[]{1,2,3,4,5,6,7,8};
    PriorityQueue<Integer> q = new PriorityQueue<Integer>((a,b) -> {
      return b-a;
    });
    for(int i=0;i<arr.length;i++){
        q.add(arr[i]);
    }
    while(!q.isEmpty()){
      System.out.println(q.poll());
    }
  }
}
