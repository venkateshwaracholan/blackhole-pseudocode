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
    
    //APPROACH 1 minheap + maxheap + prefer left -> if l empty or num<l put in l else putin r, r>l l.add(r.poll), l>r+1 r.add(l.poll),  odd m=l even l+r/2
    
    //    Time Complexity: O(nlogn)
    //    Space Complexity: O(n)
    class MedianFinder {
        Queue<Integer> right = new PriorityQueue<>((a,b)->a-b);
        Queue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        public void addNum(int num) {
            if(left.size()==0||num<left.peek()) left.add(num);
            else right.add(num);
            if(right.size()>left.size()) left.add(right.poll());
            else if(left.size()>right.size()+1) right.add(left.poll());
        }
        public double findMedian() {
            if((left.size()+right.size())%2==1) return (double)left.peek();
            else return (left.peek()+right.peek())/2.0;
        }
    }
    //APPROACH 1.2 minheap + maxheap +prefer both -> if l empty or num<l put in l else putin r, r-l==2 l.add(r.poll), l-r==2 r.add(l.poll),  odd l>r l else r,   even l+r/2
    
    // same as above, diff logics, 3 checks in findMedian is due to resizing logics like right.size()-left.size()==2 etc
    class MedianFinder2{
        Queue<Integer> right = new PriorityQueue<>((a,b)->a-b);
        Queue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        public void addNum(int num) {
            if(left.size()==0||num<left.peek()) left.add(num);
            else right.add(num);
            if(right.size()-left.size()==2) left.add(right.poll());
            else if(left.size()-right.size()==2) right.add(left.poll());
        }
        public double findMedian() {
            if(left.size()>right.size()) return (double)left.peek();
            else if(right.size()>left.size()) return (double)right.peek();
            else return (left.peek()+right.peek())/2.0;
        }
    }
    
    //APPROACH 2 minheap + maxheap + even odd +prefer left -> for even r.add, l.add(r.poll) for odd l.add r.add(l.poll)  odd l,   even l+r/2
    
    // using size to check if its odd or even
    // for even, put an right and move to left
    // for odd put in left and move to right
    class MedianFinder3 {
        Queue<Integer> right = new PriorityQueue<>((a,b)->a-b);
        Queue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        public void addNum(int num) {
            if(left.size()==right.size()) {
                right.add(num);
                left.add(right.poll());
            }else{
                left.add(num);
                right.add(left.poll());
            }
        }
        public double findMedian() {
            if(left.size()==right.size()) return (left.peek()+right.peek())/2.0;
            else return (double)left.peek();
        }
    }
    
    //APPROACH 3 minheap + maxheap + double memory ->  r.add, l.add(r.poll) l.add r.add(l.poll)  m=l+r/2
    
    // takes double memory
    // for 1,2,3 input
    // left  1,1,2   max heap so head is end 2
    // right 2,3,3   min heap so head is start 2
    class MedianFinder4 {
        Queue<Integer> left = new PriorityQueue<Integer>((a,b)->b-a);
        Queue<Integer> right = new PriorityQueue<Integer>((a,b)->a-b);
        public void addNum(int num) {
            left.add(num);
            right.add(left.poll()); 
            right.add(num);
            left.add(right.poll());
        }
        public double findMedian() {
            return (left.peek()+right.peek())/2.0;
        }
    }
    
    //APPROACH 4 minheap + maxheap + swap ->z=r  l.add, r.add(l.poll) swap with temp while u do it, m=l+z/2
    
    class MedianFinder5 {
        PriorityQueue<Integer> left = new PriorityQueue<Integer>((a,b)->b-a);
        PriorityQueue<Integer> right = new PriorityQueue<Integer>((a,b)->a-b);
        PriorityQueue<Integer> z = right, temp;
        public void addNum(int num) {
            (temp=left).add(num);
            (left=right).add((right=temp).poll());
        }
        public double findMedian() {
            return (left.peek()+z.peek())/2.0;
        }
    }
    
    //APPROACH 4.2 [maxheap,minheap] + swap 1,0 in i ->z=q[1],i=0  q[0].add, q[1].add(q[0].poll) swap with ^ while u do it, m=1[i]+q[1]/2
    
    class MedianFinder6 {
        //Queue[] q = {new PriorityQueue(), new PriorityQueue(Collections.reverseOrder())};
        Queue<Integer>[] q = new Queue[]{new PriorityQueue<Integer>((a,b)->b-a),new PriorityQueue<Integer>((a,b)->a-b)};
        int i=0;
        public void addNum(int num) {
            q[i].add(num);
            q[i^=1].add(q[i^1].poll());
        }
        public double findMedian(){
            return (q[i].peek()+q[1].peek())/2.0;
        }
    }
    
    //APPROACH 5 arraylist and binary search, find right index in AL and place,  m=len-1/2 odd m=mid, even m=mid+mid=1/2
    
    
    //binary search
    class MedianFinder7 {
        List<Integer> list = new ArrayList();
        public int binarySearch(List<Integer> list, int target){
            int l=0,r=list.size();
            while(l<r){
                int mid = l+(r-l)/2;
                if(target<list.get(mid)) r=mid;
                else l = mid+1;
            }
            return l;
        }
        public void addNum(int num) {
            if(list.size()==0)
                list.add(num);
            else{
                int x = binarySearch(list,num);
                list.add(x,num);
            }
        }
        public double findMedian() {
            int mid = (list.size()-1)/2;
            return list.size()%2==1 ? list.get(mid) :(list.get(mid)+list.get(mid+1)) / 2.0;
        }
    }
    
    //APPROACH 6 TreeSet<int[]> +  Comparator<int[]>, int[] l,r, put num, index in treemap to make it holds duplictes
    //             n={num,i++}  add in ts,   ts==1 l=r=n, l==r {if n<l l=lower(l) n>r r=higher(r)} l!=r n<l r=l, n>r l=r else l=r=n   med=l[0]+r[0]/2 l could be null ret 0
    
    
    // using treeset keys to sort
    // and maintain l and r pointers
    // 2 cases 
    // l==r (odd) if new val is placed left, move l to left, vice versa
    // l!=r (even) if val placed left, r=l, val plcaed right, l=r, else l=r=n
    class MedianFinder8 {
        Comparator<int[]> comp = ((a,b) -> a[0]==b[0] ? a[1]-b[1]:a[0]-b[0]);
        TreeSet<int[]> ts = new TreeSet(comp);
        int[] l=null, r=null;
        int i=0;
        public void addNum(int num) {
            int[] n = new int[]{num,i++};
            ts.add(n);
            if(ts.size()==1) l=r=n;
            else if(l==r){
                if(comp.compare(n,l)<0) l=ts.lower(l);
                else r=ts.higher(r);
            }else{
                if(comp.compare(n,l)<0) r=l;
                else if(comp.compare(n,r)>0) l=r;
                else l=r=n;
            }
        }
        public double findMedian() {
            if(l==null) return 0;
            return (l[0]+r[0])/2.0;
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
    
    
    //worst case
    class MedianFinder11 {

        ArrayList<Integer> list;

        public MedianFinder11() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            int i;
            if(list.size() > 0){
                for (i = 0; (i < list.size()  && list.get(i) < num); i++);
                list.add(i , num);
            }else{
                list.add(num);
            }
        }

        public double findMedian() {
            // System.out.println(list);
            int index = list.size()/2;
            if(list.size() % 2 == 0){
                return (double) (list.get(index) + list.get(index - 1))/2;
            }else{
                return list.get(index);
            }

        }
    }
}
