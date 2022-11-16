/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.combined_data_structures;

/**
 *
 * @author vshanmugham
 */



/*
hi




n 

urls

5

a,s,d,a,a,w,d


5
a,b,c,d,e,f,b

[b,c,e,f,d]
f a
r a

b null
c b
e c
f e
d f




public void url_cache(String arr[],int n){
    Queue<ListNode> q = new CustomLinkedList();
    Map<ListNode,ListNode> m = new HashMap();
    for(int i=0;i<arr.length;i++){
        ListNode nod = new ListNode(arr[i]);
        if(map.containKey(nod)){
            ListNode prev = map.get(nod);
            if(prev==null){
                q.poll();     
            }else if(q.peekLast()==nod){
                map.put(nod,prev);
            }
            else{
                map.put(prev.next.next,prev);
                
            }
        }else if(q.size>=n){
            nod = q.poll();
            map.delete(nod);
        }
        ListNode val = q.size()!=0? q.peekLast() : null ;
        map.put(nod,val);
        q.add(nod);
    }
}
  
O(n)

Queue + Map
[a,s,d,s]  O(n**2)

*/

//  https://leetcode.com/problems/lru-cache/
import java.util.*;


// Time get: O(1) put: O(n) space: O(capacity+2)
// approach: map + doubly linked list
// core idea: add dummy head and tail node ro avoid end cases;
// we need simple methods like, addNode, removenode, moveToHead, popLast
// head, tail and a cache map
// in moveToHead first remove and then add, or else problem with object property
// in put, if key is already present remove equivalent node 
// if size reached capacity, remove tail's prev from map use tail.prev.key and remove that node as well
// else increase size, add node and add in map
// get is fairly simple
class LRUCache {
    class DListNode {
        int key, val;
        DListNode prev,next;
        DListNode(){}
        DListNode(int k, int v){
            key = k;val = v;
        }
    }
    
    DListNode head, tail;
    Map<Integer, DListNode> cache = new HashMap();
    int capacity, size;
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DListNode node = cache.get(key);
        if(node==null) return -1;
        moveToHead(node);
        //printList();
        return node.val;
    }
    
    
    
    public void addNode(DListNode node){
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
    
    public void moveToHead(DListNode node){
        removeNode(node);
        addNode(node);
    }
    
    public void popLast(){
        removeNode(tail.prev);
    }
    
    public void removeNode(DListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void printList(){
        DListNode temp = head.next;
        while(temp!=tail){
            System.out.println(temp.key);
            temp = temp.next;
        }
        System.out.println();
    }
    
    public void put(int key, int value) {
        DListNode node = new DListNode(key,value);
        if(cache.get(key)!=null) removeNode(cache.get(key));
        else if(size==capacity){
            cache.remove(tail.prev.key);
            popLast();
        }else size++;
        addNode(node);
        cache.put(key,node);
        //printList();
    }
    
  public static void main(String args[]){
    LRUCache cache = new LRUCache( 2 /* capacity */ );
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Time get: O(1) put: O(n) space: O(capacity+2)
// approach: cache map + singly linked list + map for storing prev of everynode and a tail
// core idea: add dummy head node ro avoid end cases;
// we need simple methods like, addNode, removenode, moveToHead, popLast
// head, tail, prevMap and a cache map
// in moveToHead first remove and then add, or else problem with object property
// in put, if key is already present remove equivalent node 
// if size reached capacity, remove tail's prev from map use tail.prev.key and remove that node as well
// else increase size, add node and add in map
// get is fairly simple
class LRUCacheSingly {
    class ListNode {
        int key, val;
        ListNode next;
        ListNode(){}
        ListNode(int k, int v){
            key = k;
            val = v;
        }
    }
    
    ListNode head, tail;
    Map<Integer, ListNode> cache = new HashMap();
    Map<ListNode, ListNode> prevMap = new HashMap();
    int capacity, size;
    public LRUCacheSingly(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new ListNode();
    }
    
    public int geth(int key) {
        ListNode node = cache.get(key);
        if(node==null) return -1;
        moveToHead(node);
        //printList();
        return node.val;
    }
    public int get(int key) {
      int x = geth(key);  
      //System.out.println(x);
      return x;
    }
    
//  if values are present, put node as value prev for head.next
//  head will be the prev of incoming node
//  if value is not present node is tail  
    public void addNode(ListNode node){
        node.next = head.next;
        if(head.next!=null) prevMap.put(head.next,node);
        else tail = node;
        prevMap.put(node, head);
        head.next = node;
    }
//    get prev from map
//    if node is tail, update tail to prev
//    remove ode from prev map
//    update next node's prev value in map
    public void removeNode(ListNode node){
      ListNode prev = prevMap.get(node);
      if(node==tail) tail = prev;
      prev.next = node.next;
      prevMap.remove(node);
      prevMap.put(node.next, prev);
    }
    
    public void moveToHead(ListNode node){
        removeNode(node);
        addNode(node);
    }
    
    public void popLast(){
        removeNode(tail);
    }
    
    public void put(int key, int value) {
        ListNode node = new ListNode(key,value);
        if(cache.get(key)!=null) removeNode(cache.get(key));
        else if(size==capacity){
            cache.remove(tail.key);
            popLast();
        }else size++;
        addNode(node);
        cache.put(key,node);
        //printList();
    }
    
    public void printList(){
        ListNode temp = head.next;
        while(temp!=null){
            System.out.println(temp.key);
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static void main(String args[]){
    LRUCache cache = new LRUCache( 3 /* capacity */ );
    cache.put(1, 1);
    cache.put(2, 2);
    cache.put(3, 3);
    cache.put(4, 4);
    cache.get(4);
    cache.get(3);
    cache.get(2);
    cache.get(1);
    cache.put(5, 5);    
    cache.get(1);       
    cache.get(2);      
    cache.get(3);
    cache.get(4);      
    cache.get(5);
  }
}

//  h 2 1
