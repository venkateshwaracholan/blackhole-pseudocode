/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;
import java.util.*;

/**
 *
 * @author venka
 */
public class MergeKSortedLinkedList {
    
    //APPROACH 1 Ite+arrayList, get min from arraylist and assign to cur, move set node to next in AL, if null remove node from AL
    // Time O(nk) space: O(k)
    // compare one by one
    // put heads in arraylist
    // create a useful dummy, ans
    // while siz>0
    // iterate and find min index from l
    // move min to dummy's next
    // move dummy to min
    // get next from min
    // if its null rmove from l,else set next to min pos in l
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(1);
        ListNode cur = dummy;
        List<ListNode> l = new ArrayList();
        for(int i=0;i<lists.length;i++)
            if(lists[i]!=null) 
                l.add(lists[i]);
        while(l.size()>0){
            int mi = 0;
            for(int i=0;i<l.size();i++)
                if(l.get(i).val<l.get(mi).val) mi = i;
            cur.next = l.get(mi);
            cur=cur.next;
            l.set(mi,l.get(mi).next);
            if(l.get(mi)==null) l.remove(mi);
        }
        return dummy.next;
    }
    //APPROACH 1.2 Ite+lists[], skip nulls in lists, use null count to exit, get min from lists and assign to cur, move set node to next in lists
    // Time O(nk) space: O(k)
    // same as above without using lists
    // using null counts for exit
    // so starting loop made infinite
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(true){
            int mi=0, nc=0;
            for(int i=0;i<lists.length;i++){
                if(lists[i]==null) {
                    nc++;continue;
                }
                if(lists[mi]==null||lists[i].val<lists[mi].val) mi=i;
            }
            if(nc==lists.length) return dummy.next;
            cur.next = lists[mi];
            cur = cur.next;
            lists[mi] = lists[mi].next;
        }
    }
    
    
    //APPROACH 2 Ite+minheap, put in minheap if not null, poll from ehap and put in cur, run until heap empty
    // Time O(nlogk) space: O(k)
    // adding into min heap
    // while minheap not empty
    // poll and move dummies
    // add in minheap if min's next is not null
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode dummy = new ListNode(1);
        ListNode cur = dummy;
        Queue<ListNode> minheap = new PriorityQueue<>((a,b)->a.val-b.val);
        for(int i=0;i<lists.length;i++)
            if(lists[i]!=null) minheap.add(lists[i]);
        while(minheap.size()>0){
            cur.next = minheap.poll();
            cur=cur.next;
            if(cur.next!=null) minheap.add(cur.next);
        }
        return dummy.next;
    }
    
    
    //APPROACH 3 merge+mergesort partition, partition and call merge 2 sorted list
    // Time O(nlogk) space: O(logk) call stack
    // using merge sort to sort 2 lists at a time
    // bounds 0 to k-1
    public ListNode mergeKLists4(ListNode[] lists) {
        return partition(lists,0,lists.length-1);
    }
    // if lo>hi return null
    // if lo==hi return lo val
    // find mid, call partitions, lo, mid and mid+1,hi
    // merge 2 nodes
    public ListNode partition(ListNode[] lists, int lo, int hi){
        if(lo>hi)return null;
        if(lo==hi) return lists[lo];
        int mid = lo+(hi-lo)/2;
        ListNode l1=partition(lists, lo, mid);
        ListNode l2=partition(lists, mid+1,hi);
        return merge(l1,l2);
    }
    
    //APPROACH 3.2 merge+mergesort Ite, in=1,  while(i<siz),for(x 0 to siz-i) i=i*2, z+=2*i, merge 2 sorted list(x,x+i) and assignt to left
    
    // Time O(nlogk) space: O(1) 
    // iterative merge sort, very intuitive
    // set interval as 1
    // while interval<siz
    // for again o to siz-interval, i+=twice interval
    // list[i] = merge two lists 
    // once merged all adjacents, make interval twice
    // 0,1,2
    public ListNode mergeKLists5(ListNode[] lists) {
        int interval = 1,siz = lists.length;
        while(interval<siz){
            for(int x=0;x<siz-interval;x+=2*interval){
                lists[x] = merge(lists[x], lists[x+interval]);
            }
            interval*=2;
        }
        return siz>0 ? lists[0] : null;
    }

    //merge logic
    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;
        if(l1.val<l2.val){
            l1.next = merge(l1.next,l2);
            return l1;
        }else{
            l2.next = merge(l1,l2.next);
            return l2;
        }
    }
    
    
    

}
