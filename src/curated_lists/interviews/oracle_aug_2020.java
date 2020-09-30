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
public class oracle_aug_2020 {
  
}

/*

[5,1,2,6,3]

5
max = -i
sxam = -i


public static int secMax(int arr[]){
  int max = Integer.MIN_VALUE, smax = Integer.MIN_VALUE;
  for(int x: arr){
    if(x>max){
      smax = max;
      max = x;    
    }else if(x> smax){
      smax = x;
    }
  }
  return smax;
}


1 -> 2 -> 3 -> 4 -> null
               i


public static void reverseLinkedList(ListNode n){
  if(n==null){
    return;
  }
  reverseLinkedList(n.next);
  System.out.println(n.val);
}



public static void reverseLinkedList(ListNode n){
  while(n!=null){
    reverseLinkedList(n.next);
    System.out.println(n.val);
  }
}



*/
