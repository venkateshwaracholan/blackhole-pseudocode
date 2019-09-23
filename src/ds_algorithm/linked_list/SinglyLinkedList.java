/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;
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
    list.print();
    list.remove(1);
    list.print();
    list.remove(3);
    list.print();
    list.remove(5);
    list.remove(10);
    list.print();
    
  }
}
