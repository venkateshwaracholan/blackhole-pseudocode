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
    
    /*
    * ONE LINERS — Quick Reference:\
    *
    * Merge K Sorted Lists (Brute Force Array Scan): create dummy node and cur pointer, while(true) — keep looping until all k lists exhausted, in each iteration find minIndex by scanning lists[] heads using if(lists[i]!=null && lists[i].val<min) to ensure only valid non-null heads are considered and min always holds the smallest value, if minIndex==-1 return dummy.next (all lists exhausted), else attach lists[minIndex] to cur.next, advance lists[minIndex] to next, advance cur → O(nk), O(1) extra space.
    * Merge K Sorted Lists (Min‑Heap): create dummy=0, cur=dummy(set result start), add all non-null heads to minHeap(PriorityQueue by a.val-b.val)(initialize heap with smallest heads as each list is sorted); while(minHeap.size()>0)(process until all nodes merged) — var minNode=minHeap.poll()(poll smallest node), cur.next=minNode(attach to cur.next), cur=cur.next(advance cur pointer); if(minNode.next!=null)add(minNode.next)(add next of polled node if present so next smallest of that list remains available) → O(n log k), O(k) extra space.
    * Merge K Sorted Lists (Divide & Conquer Partition): recursively partition lists range (to balance merges instead of sequential nk merges), Edge case: if lists.length==0 return null (empty input), directly call partition(lists,0,lists.length-1) (paritition full range rec), base case (if l==r return lists[l] → one list needs no merge → bottom of recursion), otherwise compute mid=l+(r-l)/2 (safe midpoint), recursively merge left=partition(l,mid), right=partition(mid+1,r), then return merge(left,right) (on unwind left and right single list merge two sorted lists by attaching smaller head first and recursing on the rest) → O(n log k), O(log k) recursion stack.
    * Merge K Sorted Lists (Iterative Pairwise Merge): if(size==0)return nullptr(edge case, no lists); set interval=1(start merging pairs of size 1), while(interval<size)(merge lists in increasing ranges until all merged) — for(x=0;x<size-interval;x+=2*interval)(bound x<size-interval coz interval x+intreval is used, jump by 2*interval to process non‑overlapping pairs) merge lists[x] and lists[x+interval](merge two sorted lists in place) and store at lists[x](overwrite with merged list); interval*=2(double merge range each pass) → O(n log k), O(1) extra space.
    */

    /*
    * ONE LINER => Merge K Sorted Lists (Brute Force Array Scan): create dummy node and cur pointer, while(true) — keep looping until all k lists exhausted, in each iteration find minIndex by scanning lists[] heads using if(lists[i]!=null && lists[i].val<min) to ensure only valid non-null heads are considered and min always holds the smallest value, if minIndex==-1 return dummy.next (all lists exhausted), else attach lists[minIndex] to cur.next, advance lists[minIndex] to next, advance cur → O(nk), O(1) extra space.
    *
    * Approach: Merge k sorted linked lists by scanning heads of all lists repeatedly
    * - Initialize dummy node and cur pointer to build result list.
    * - while(true):
    *     - Set min=Integer.MAX_VALUE, minIndex=-1 → ensures we correctly find smallest head node in current iteration.
    *     - For each list in lists[]:
    *         - if(lists[i]!=null && lists[i].val<min):
    *             - ensures we only consider non-null list heads and correctly update min to smallest available head value.
    *             - update min and minIndex accordingly.
    *     - If minIndex==-1 → all k lists are exhausted, break loop and return dummy.next.
    *     - Else:
    *         - cur.next = lists[minIndex] (attach smallest node to result list).
    *         - Move lists[minIndex] = lists[minIndex].next (advance that list).
    *         - cur = cur.next (advance result pointer).
    *
    * Reason for while(true): We do not know exactly how many total iterations (n) are needed; n = total number of nodes across all lists, so we loop until all heads are null, exiting explicitly when minIndex==-1.
    *
    * Time Complexity: O(nk) — for each of n nodes we scan all k list heads to find min → O(k) per node.
    * Space Complexity: O(1) extra space (output list excluded).
    *
    * Key Insight: min=Integer.MAX_VALUE ensures correct reset for each iteration; minIndex tracks which list head is smallest; if(lists[i]!=null && lists[i].val<min) is critical to skip null lists and correctly choose the next smallest node; brute force scanning is simplest but inefficient for large k.
    */

    public ListNode mergeKListsBruteMin(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(true){
            int min = Integer.MAX_VALUE, minIndex = -1;
            for(int i=0;i<lists.length;i++){
                if(lists[i]!=null && lists[i].val<min){
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if(minIndex==-1){
                return dummy.next;
            }
            cur.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
            cur = cur.next;
        }
    }
    
    
    /*
    * ONE LINER => Merge K Sorted Lists (Min‑Heap): create dummy=0, cur=dummy(set result start), add all non-null heads to minHeap(PriorityQueue by a.val-b.val)(initialize heap with smallest heads as each list is sorted); while(minHeap.size()>0)(process until all nodes merged) — var minNode=minHeap.poll()(poll smallest node), cur.next=minNode(attach to cur.next), cur=cur.next(advance cur pointer); if(minNode.next!=null)add(minNode.next)(add next of polled node if present so next smallest of that list remains available) → O(n log k), O(k) extra space.
    *
    * Approach: Use min‑heap to always attach the smallest head node across k lists.
    * - Create dummy node and cur pointer for result list start.
    * - Add all non-null list heads to minHeap to start (initialize heap with smallest heads as each list is sorted).
    * - While heap is not empty:
    *     - Poll minNode from heap (smallest among current heads).
    *     - Attach minNode to cur.next and advance cur pointer.
    *     - If minNode.next != null → add it to heap (add next of polled node if present so next smallest of that list remains available).
    * - Return dummy.next.
    *
    * Reasoning: MinHeap keeps ≤ k nodes at any moment; poll operation retrieves the smallest available head in O(log k); adding minNode.next ensures continuous processing of the list without scanning all heads again.
    *
    * Time Complexity: O(n log k) — n total nodes processed with O(log k) heap operations per node.
    * Space Complexity: O(k) — heap size never exceeds k.
    *
    * Key Insight: Using a min‑heap drastically reduces the time complexity compared to brute force scanning; adding minNode.next keeps heap updated with next candidate; comparator (a.val-b.val) ensures ascending order merging.
    */

    public ListNode mergeKListsMinHeap(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        var minHeap = new PriorityQueue<ListNode>((a,b)->a.val-b.val);
        for(var list: lists){
            if(list!=null){
                minHeap.add(list);
            }
        }
        while(minHeap.size()>0){
            var minNode = minHeap.poll();
            cur.next = minNode;
            cur = cur.next;
            if(minNode.next!=null){
                minHeap.add(minNode.next);
            }
            
        }
        return dummy.next;
    }
    
    
    /*
    * ONE LINER => Merge K Sorted Lists (Divide & Conquer Partition): recursively partition lists range (to balance merges instead of sequential nk merges), Edge case: if lists.length==0 return null (empty input), directly call partition(lists,0,lists.length-1) (paritition full range rec), base case (if l==r return lists[l] → one list needs no merge → bottom of recursion), otherwise compute mid=l+(r-l)/2 (safe midpoint), recursively merge left=partition(l,mid), right=partition(mid+1,r), then return merge(left,right) (on unwind left and right single list merge two sorted lists by attaching smaller head first and recursing on the rest) → O(n log k), O(log k) recursion stack.
    * 
    * Partition splits lists into two halfs and gets merged result from both half, does this recursively and recursion unwinds when only 1 list is present, we call 2 times to get both sides and merge them, so log2 merges in total
    * 
    * Approach: Use partitioning like merge sort to reduce redundant scans
    * - mergeKLists:
    *     - Edge case: if lists.length==0 return null (empty input).
    *     - Otherwise directly call partition(lists,0,lists.length-1).
    *
    * - partition(l,r):
    *     - if(l==r) return lists[l] (base case: bottom of recursion → exactly one list, no merging needed).
    *     - mid = l+(r-l)/2 (avoid overflow).
    *     - l1 = partition(l,mid) (recursively get merged left half).
    *     - l2 = partition(mid+1,r) (recursively get merged right half).
    *     - return merge(l1,l2) (combine both halves into one sorted list).
    *
    * - merge(l1,l2):
    *     - if(l1==null) return l2 (left empty → return right).
    *     - if(l2==null) return l1 (right empty → return left).
    *     - if(l1.val < l2.val):
    *         - l1.next = merge(l1.next,l2) (attach l1 to recursively merged result of rest of l1 with l2).
    *         - return l1 (head is l1 since smaller).
    *     - else:
    *         - l2.next = merge(l1,l2.next) (attach l2 to recursively merged result of l1 with rest of l2).
    *         - return l2 (head is l2 since smaller/equal).
    *
    * Example Partition (k=4, lists indices 0..3):
    *   partition(0,3)
    *       mid=1 → partition(0,1), partition(2,3)
    *           partition(0,1):
    *               mid=0 → partition(0,0)=lists[0], partition(1,1)=lists[1]
    *               merge(lists[0],lists[1]) → merged01
    *           partition(2,3):
    *               mid=2 → partition(2,2)=lists[2], partition(3,3)=lists[3]
    *               merge(lists[2],lists[3]) → merged23
    *       merge(merged01,merged23) → final merged list
    *
    * Time Complexity: O(n log k) — n total nodes across all lists, each node participates in log k merges.
    * Space Complexity: O(log k) recursion depth for partition + O(log n) recursion for merge calls.
    *
    * Key Insight: Partitioning reduces the problem size by half each recursion → balanced merges (like tournament bracket). 
    * Base case returns one list so merging starts efficiently. Merge attaches smaller head first and recurses on remaining nodes, ensuring sorted output without extra data structures.
    */

    public ListNode mergeKListsMergeSortPartition(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        return partition(lists, 0, lists.length-1);
    }
    public ListNode partition(ListNode[] lists, int l, int r){
        if(l==r){
            return lists[l];
        }
        int mid = l + (r-l)/2;
        var l1 = partition(lists,l,mid);
        var l2 = partition(lists,mid+1,r);
        return merge(l1,l2);
    }

    
    /*
    * ONE LINER => Merge K Sorted Lists (Iterative Pairwise Merge): if(size==0)return nullptr(edge case, no lists); set interval=1(start merging pairs of size 1), while(interval<size)(merge lists in increasing ranges until all merged) — for(x=0;x<size-interval;x+=2*interval)(bound x<size-interval coz interval x+intreval is used, jump by 2*interval to process non‑overlapping pairs) merge lists[x] and lists[x+interval](merge two sorted lists in place) and store at lists[x](overwrite with merged list); interval*=2(double merge range each pass) → O(n log k), O(1) extra space.
    *
    * Approach: Iteratively merge pairs of lists in bottom‑up manner to avoid recursion.
    * - If lists.size()==0 return nullptr (edge case).
    * - Set interval=1 to start merging adjacent pairs.
    * - While interval<size:
    *     - For x=0;x<size-interval;x+=2*interval:
    *         - Merge lists[x] and lists[x+interval] (pairwise merge in place).
    *         - Store merged list at lists[x].
    *     - interval*=2 (double merge size to merge larger ranges next pass).
    * - Return lists[0] (final merged list).
    *
    * Example Iterative Merge (k=4, lists indices 0..3):
    *   interval=1:
    *     merge(lists[0],lists[1]) → merged01 stored at lists[0]
    *     merge(lists[2],lists[3]) → merged23 stored at lists[2]
    *   interval=2:
    *     merge(lists[0],lists[2]) → final merged list stored at lists[0]
    *
    * Reasoning: 
    * - interval=1 means merge smallest adjacent pairs first.
    * - while(interval<size) ensures we merge until all lists are combined.
    * - for loop increments by 2*interval to avoid overlap and merge non‑overlapping pairs in current pass.
    * - doubling interval each pass merges progressively larger ranges → log k passes.
    * - storing merged list in place avoids extra space.
    *
    * Time Complexity: O(n log k) — n total nodes, log k passes of merging.
    * Space Complexity: O(1) extra space (in‑place).
    *
    * Key Insight: Interval doubling mimics bottom‑up merge sort, ensuring balanced merges with minimal extra space and avoiding recursion.
    */

    public ListNode mergeKListsMergeSortIte(ListNode[] lists) {
        int interval = 1, size = lists.length;
        if(size==0){
            return null;
        }
        while(interval<size){
            for(int x = 0;x<size-interval;x+=2*interval){
                lists[x] = merge(lists[x], lists[x+interval]);
            }
            interval = interval*2;
        }
        return lists[0];
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
