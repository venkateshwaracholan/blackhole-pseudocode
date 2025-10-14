/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.heap;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/find-median-from-data-stream/description/

public class FindMedianFromDataStream {

    /*
    * MedianFinderBruteForce => List<Integer> list: (store sorted stream), MedianFinder() { list = new ArrayList<Integer>(); }: (initialize empty list to store numbers), addNum(int num) { if(list.size()==0){ list.add(num); }: (base case — first element inserted directly), else{ int i=0; while(i<list.size() && list.get(i)<num){ i++; }: (linear scan to find correct insertion position), list.add(i, num); }: (insert element and shift others), findMedian() { int mid = list.size()/2;: (compute middle index), if(list.size()%2==0){ int mid1 = list.get(mid-1); int mid2 = list.get(mid); return (double)(mid1+mid2)/2; }: (even size — average two middle values), return (double)list.get(mid); }: (odd size — return middle value) → O(n) addNum (~2n absolute), O(1) findMedian, O(n) space.
    *
    * Code(Reason):
    * - List<Integer> list;: store all numbers in sorted order for direct median retrieval.
    * - public MedianFinder() { list = new ArrayList<Integer>(); }: initialize empty list to store numbers.
    * - public void addNum(int num):
    *     - if(list.size()==0){ list.add(num); }: base case, first element directly inserted.
    *     - else{ int i=0; while(i<list.size() && list.get(i)<num){ i++; } list.add(i, num); }: find correct sorted position by linear scan and insert, shifting elements.
    * - public double findMedian():
    *     - int mid = list.size()/2;: compute middle index.
    *     - if(list.size()%2==0){ int mid1 = list.get(mid-1); int mid2 = list.get(mid); return (double)(mid1+mid2)/2; }: even number of elements → average of middle two.
    *     - return (double)list.get(mid);: odd number of elements → middle element.
    *
    * Rationale: Maintaining sorted order in a list allows O(1) median retrieval, but requires O(n) insertion due to linear scan and element shifting. This is the simplest brute‑force approach for MedianFinder.
    *
    * Time Complexity:
    * - addNum: O(n) — linear scan to find position + O(n) shifting → absolute ≈ 2n per insertion.
    * - findMedian: O(1) — direct index access.
    * Space Complexity: O(n) — store all inserted numbers.
    */
    class MedianFinderBruteForce {
        List<Integer> list;
        public MedianFinderBruteForce() {
            list = new ArrayList<Integer>();
        }
        
        public void addNum(int num) {
            if(list.size()==0){
                list.add(num);
            }
            else{
                int i=0;
                while(i<list.size() && list.get(i)<num){
                    i++;
                }
                list.add(i, num);
            }
        }
        
        public double findMedian() {
            int mid = list.size()/2;

            if(list.size()%2==0){
                int mid1 = list.get(mid-1);
                int mid2 = list.get(mid);
                return (double) (mid1+mid2)/2;
            }
            return (double) list.get(mid);
        }
    }

    /*
    * MedianFinderBruteBinSearch => List<Integer> list: (store sorted stream), MedianFinderBruteBinSearch() { list = new ArrayList<Integer>(); }: (initialize empty list to store numbers), binarySearch(int target) { int l=0,r=list.size(); while(l<r){ int mid=l+(r-l)/2; if(target<list.get(mid)){ r=mid; }: (move right bound to mid to preserve [l,r) search range and allow insertion after duplicates; r=mid instead of mid-1 is essential because r is exclusive — setting r=mid keeps mid in range to ensure equal values insert correctly after existing ones and no potential insertion point is skipped, avoiding edge case errors like inserting duplicate mid values incorrectly), else{ l=mid+1; }: (move left bound past mid for equal or larger values), return l; }: (return correct insert position), addNum(int num) { int i=binarySearch(num); list.add(i,num); }: (find correct position by binary search and insert with shift), findMedian() { int mid=list.size()/2; if(list.size()%2==0){ int mid1=list.get(mid-1),mid2=list.get(mid); return (double)(mid1+mid2)/2; }: (even size → average two middle values), return (double)list.get(mid); }: (odd size → return middle element) → O(n) addNum (~log n + n = ~n absolute), O(1) findMedian, O(n) space.
    *
    * Code(Reason):
    * - List<Integer> list;: store all numbers in sorted order for direct median retrieval.
    * - public MedianFinderBruteBinSearch() { list = new ArrayList<Integer>(); }: initialize empty list to store numbers.
    * - public int binarySearch(int target):
    *     - int l=0,r=list.size();: set [l,r) search range so insertion at end is possible.
    *     - while(l<r){ int mid=l+(r-l)/2; if(target<list.get(mid)){ r=mid; }: move right bound to mid to preserve search range and allow insertion after duplicates; r=mid instead of mid-1 ensures mid remains in search range so no insertion points are skipped and equal values are inserted correctly, avoiding errors in edge cases. else{ l=mid+1; }: move left bound past mid for equal or larger values. }
    *     - return l;: final insertion index.
    * - public void addNum(int num): int i=binarySearch(num); list.add(i,num);: find correct position and insert shifting elements.
    * - public double findMedian():
    *     - int mid=list.size()/2;: find middle index.
    *     - if(list.size()%2==0){ int mid1=list.get(mid-1),mid2=list.get(mid); return (double)(mid1+mid2)/2; }: average two middle values for even size.
    *     - return (double)list.get(mid);: return middle value for odd size.
    *
    * Rationale: Binary search optimizes finding insert position to O(log n) but insertion still requires O(n) shifts; maintaining sorted order allows O(1) median retrieval. Choosing r=mid (not mid-1) is crucial in a [l,r) binary search for insertion to avoid skipping potential insertion positions, especially when duplicate values exist, ensuring correctness. This is a clear improvement over naive linear search brute force while keeping code readable.
    *
    * Time Complexity:
    * - binarySearch(): O(log n) — search range halved each step.
    * - addNum(): O(log n + n) = O(n) — binary search + shift insertion (~n absolute).
    * - findMedian(): O(1) — direct index access.
    *
    * Space Complexity: O(n) — store all inserted numbers.
    */

    class MedianFinderBruteBinSearch {
        List<Integer> list;
        public MedianFinderBruteBinSearch() {
            list = new ArrayList<Integer>();
        }

        public int binarySearch(int target){
            int l = 0, r = list.size();
            while(l<r){
                int mid = l + (r-l)/2;
                if(target<list.get(mid)){
                    r = mid;
                }else{
                    l = mid+1;
                }
            }
            return l;
        }
        
        public void addNum(int num) {
            int i=binarySearch(num);
            list.add(i, num);
        }
        
        public double findMedian() {
            int mid = list.size()/2;

            if(list.size()%2==0){
                int mid1 = list.get(mid-1);
                int mid2 = list.get(mid);
                return (double) (mid1+mid2)/2;
            }
            return (double) list.get(mid);
        }
    }


    
    
    //APPROACH 2 minheap + maxheap + even odd +prefer left -> for even r.add, l.add(r.poll) for odd l.add r.add(l.poll)  odd l,   even l+r/2
    
    // using size to check if its odd or even
    // for even, put an right and move to left
    // for odd put in left and move to right
    class MedianFinderTwoHeapsPreferLeft {
        Queue<Integer> left,right;
        public MedianFinderTwoHeapsPreferLeft() {
            left = new PriorityQueue<Integer>((a,b) -> b-a);
            right = new PriorityQueue<Integer>((a,b) -> a-b);
        }
        
        public void addNum(int num) {
            if(left.size()==right.size()){
                right.add(num);
                left.add(right.poll());
            }else{
                left.add(num);
                right.add(left.poll());
            }
        }
        
        public double findMedian() {
            if(left.size()==right.size()){
                int mid1 = left.peek();
                int mid2 = right.peek();
                return (double) (mid1+mid2)/2;
            }
            return (double) left.peek();
        }
    }
    
    

    
    
    //APPROACH 6 TreeSet<int[]> +  Comparator<int[]>, int[] l,r, put num, index in treemap to make it holds duplictes
    //             n={num,i++}  add in ts,   ts==1 l=r=n, l==r {if n<l l=lower(l) n>r r=higher(r)} l!=r n<l r=l, n>r l=r else l=r=n   med=l[0]+r[0]/2 l could be null ret 0
    
    
    // using treeset keys to sort
    // and maintain l and r pointers
    // 2 cases 
    // l==r (odd) if new val is placed left, move l to left, vice versa
    // l!=r (even) if val placed left, r=l, val plcaed right, l=r, else l=r=n
    class MedianFinderTreeSetValIdexComparator {
        TreeSet<int[]> treeSet;
        Comparator<int[]> comp = ((a,b)->a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        int idx = 0;
        int[] l=null, r=null;
        public MedianFinderTreeSetValIdexComparator() {
            treeSet = new TreeSet<int[]>(comp);
        }
        
        public void addNum(int num) {
            int n[] = new int[]{num, idx};
            treeSet.add(n);
            idx++;
            if(treeSet.size()==1){
                l=r=n;
            }
            else if(l==r){
                if(comp.compare(n,l)<0){
                    l=treeSet.lower(l);
                }
                else{
                    r = treeSet.higher(r);
                }
            }
            else{
                if(comp.compare(n,l)<0){
                    r=l;
                }
                else if(comp.compare(n,r)>0){
                    l=r;
                }
                else{
                    l=r=n;
                }
            }
        }
        
        public double findMedian() {
            return (double) (l[0]+r[0])/2;
        }
    }

    //FOLLOWUP 1 int[101] freq for(i=0,101) for(j=0,c[i]) at m-1 first=i and m for odd return i even first+i/2 idx++
    
    
    
    
    //follow ups
    // If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    class MedianFinder9 {
        int[] count = new int[101];
        int size=0;
        public void addNum(int num) {
            count[num]++;
            size++;
        }
        public double findMedian() {
            int m = size/2, idx = 0,first=0;
            for(int i=0;i<101;i++){
                for(int j=0;j<count[i];j++){
                    if(idx==m-1) first = i;
                    else if(idx==m){
                        if(size%2==1) return (double)i;
                        else return (first+i)/2.0;
                    }
                    idx++;
                }
            }
            return -1;
        }
    }
   
    
    //FOLLOWUP 2 int[101] freq +less than zero count for(i=lz,101) for(j=0,c[i]) at m-1 first=i and m for odd return i even first+i/2 idx++
    
    
    // If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    class MedianFinder10 {
        int[] count = new int[101];
        int size=0, lz;
        public void addNum(int num) {
            if(num<0) lz++;
            else if (num>=0 &&num<=100)count[num]++;
            size++;
        }
        public double findMedian() {
            int m = size/2, idx = lz,first=0;
            for(int i=0;i<101;i++){
                for(int j=0;j<count[i];j++){
                    if(idx==m-1) first = i;
                    else if(idx==m){
                        if(size%2==1) return (double)i;
                        else return (first+i)/2.0;
                    }
                    idx++;
                }
            }
            return -1;
        }
    }
    
    
    
    
    
    
    // looks at this later
    // https://leetcode.com/problems/find-median-from-data-stream/solutions/1506078/java-detailed-code-solutions-for-follow-ups/
    
}
