/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */
public class MergeKSortedLinkedList {
    
    
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
        ListNode ans = dummy;
        List<ListNode> l = new ArrayList();
        for(int i=0;i<lists.length;i++)
            if(lists[i]!=null) 
                l.add(lists[i]);
        while(l.size()>0){
            int mi = 0;
            for(int i=0;i<l.size();i++)
                if(l.get(i).val<l.get(mi).val) mi = i;
            ListNode ch = l.get(mi); 
            dummy.next = ch;
            dummy = ch;
            ch = ch.next;
            if(ch==null) l.remove(mi);
            else l.set(mi, ch);
        }
        return ans.next;
    }
    
    // Time O(nk) space: O(k)
    // same as above without using lists
    // using null counts for exit
    // so starting loop made infinite
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(1);
        ListNode ans = dummy;
        while(true){
            int mi = 0, nc = 0;
            for(int i=0;i<lists.length;i++){
                if(lists[i]==null) {
                    nc++; continue;
                }
                if(lists[mi]==null || lists[i].val<lists[mi].val) mi = i;
            }
            if(nc==lists.length) return ans.next;
            ListNode ch = lists[mi]; 
            dummy.next = ch;
            dummy = ch;
            ch = ch.next;
            lists[mi] = ch;
        }
    }
    
    
    // Time O(nlogk) space: O(k)
    // adding into min heap
    // while minheap not empty
    // poll and move dummies
    // add in minheap if min's next is not null
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(1);
        ListNode ans = dummy;
        Queue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val-b.val);
        for(ListNode n:lists) 
            if(n!=null) minHeap.add(n);
        while(minHeap.size()>0){
            ListNode min = minHeap.poll();
            dummy.next = min;
            dummy = min;
            if(min.next!=null) minHeap.add(min.next);
        }
        return ans.next;
    }
    
    
    // Time O(nlogk) space: O(logk) call stack
    // using merge sort to sort 2 lists at a time
    // bounds 0 to k-1
    public ListNode mergeKLists(ListNode[] lists) {
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
    
    
    // Time O(nlogk) space: O(1) 
    // iterative merge sort, very intuitive
    // set interval as 1
    // while interval<siz
    // for again o to siz-interval, i+=twice interval
    // list[i] = merge two lists 
    // once merged all adjacents, make interval twice
    // 0,1,2
    public ListNode mergeKLists(ListNode[] lists) {
        int interval = 1,siz = lists.length;
        while(interval<siz){
            for(int i=0;i<siz-interval;i+=2*interval){
                lists[i] = merge(lists[i], lists[i+interval]);
            }
            interval*=2;
        }
        return siz>0 ? lists[0] : null;
    }

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
