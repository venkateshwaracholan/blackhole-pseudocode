/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

import com.google.gson.Gson;
import java.util.*;

class Node{
  int val;
  Node next=null;
  
  public Node(int val){
    this.val= val;
  }
}
/**
 *
 * @author venkateshwarans
 */
public class SinglyLinkedList {
  Node head = null;
  
  SinglyLinkedList(){
    
  }
  
  SinglyLinkedList(int val){
    this.add(val);
  }
  
  SinglyLinkedList(int[] valArr){
    for(int i=0;i<valArr.length;i++){
      this.add(valArr[i]);
    }
  }
  
  public int add(int val){
    if(this.head==null){
      this.head = new Node(val);
      return 0;
    }else{
      Node temp = this.head;
      int c = 1;
      while(temp.next!=null){
        temp=temp.next;
        c++;
      }
      temp.next = new Node(val);
      return c;
    }
  }
  
  public void print(){
    Node temp = this.head;
    while(temp!=null){
      System.out.print(temp.val+"->");
      temp = temp.next;
    }
    System.out.println("Null");
  }
  
  public ArrayList<Integer> toList(){
    Node temp = this.head;
    ArrayList<Integer> list = new ArrayList();
    while(temp!=null){
      list.add(temp.val);
      temp = temp.next;
    }
    return list;
  }
  
  public static ArrayList<Integer> toList(Node n){
    Node temp = n;
    ArrayList<Integer> list = new ArrayList();
    while(temp!=null){
      list.add(temp.val);
      temp = temp.next;
    }
    return list;
  }
  
  
  
  public Node find(int k){
    Node temp = this.head;
    while(temp!=null){
      if(temp.val == k){
        return temp;
      }
      temp = temp.next;
    }
    return null;
  }
  
  public Node remove(int k){
    Node temp = this.head;
    if(temp==null){
      return null;
    }else if(temp.val==k){
      this.head = this.head.next;
      return temp;
    }else{
      while(temp.next!=null){
        if(temp.next.val == k){
          Node rem = temp.next;
          temp.next = temp.next.next;
          return rem;
        }
        temp = temp.next;
      }
    }
    return null;
  }
  
  public static void main(String args[]){
    SinglyLinkedList list = new SinglyLinkedList(new int[]{1,2,3,4,5});
    test(list.toList(), new int[]{1,2,3,4,5});
    list.remove(1);
    list.remove(3);
    list.remove(5);
    list.remove(10);
    test(list.toList(), new int[]{2,4});

    
  }
  
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}
