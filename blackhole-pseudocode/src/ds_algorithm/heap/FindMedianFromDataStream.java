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
    
    //    Time Complexity: O(nlogn)
    //    Space Complexity: O(n)
    class MedianFinder {
        PriorityQueue<Integer> left,right;
        public MedianFinder() {
            left = new PriorityQueue<Integer>((a,b)->b-a);
            right = new PriorityQueue<Integer>((a,b)->a-b);
        }
        public void addNum(int num) {
            if(left.isEmpty()||num<left.peek())  
                left.add(num);
            else 
                right.add(num);
            if(right.size()>left.size())
                left.add(right.poll());
            else if(left.size()>right.size()+1)
                right.add(left.poll());
        }
        public double findMedian() {
            if(left.size()==right.size())
                return (left.peek()+right.peek())/2.0;
            else
                return (double)left.peek();
        }
    }
    // same as above, diff logics, 3 checks in findMedian is due to resizing logics like right.size()-left.size()==2 etc
    class MedianFinder2 {
        PriorityQueue<Integer> left,right;
        public MedianFinder2() {
            left = new PriorityQueue<Integer>((a,b)->b-a);
            right = new PriorityQueue<Integer>((a,b)->a-b);
        }
        public void addNum(int num) {
            if(left.size()>0&&num<left.peek())  
                left.add(num);
            else 
                right.add(num);
            if(right.size()-left.size()==2)
                left.add(right.poll());
            else if(left.size()-right.size()==2)
                right.add(left.poll());
        }
        public double findMedian() {
            if(left.size()==right.size())
                return (left.peek()+right.peek())/2.0;
            else if(left.size()>right.size())
                return (double)left.peek();
            else 
                return (double)right.peek();
        }
    }
    
    //
    class MedianFinder3 {
        PriorityQueue<Integer> left,right;
        public MedianFinder3() {
            left = new PriorityQueue<Integer>((a,b)->b-a);
            right = new PriorityQueue<Integer>((a,b)->a-b);
        }
        public void addNum(int num) {
            if((left.size()+right.size())%2==0){
                right.add(num);
                left.add(right.poll());
            }
            else{
                left.add(num);
                right.add(left.poll()); 
            }
        }
        public double findMedian() {
            if(left.size()==right.size())
                return (left.peek()+right.peek())/2.0;
            else
                return (double)left.peek();
        }
    }
    
    
    //
    class MedianFinder4 {
        PriorityQueue<Integer> left = new PriorityQueue<Integer>((a,b)->b-a);
        PriorityQueue<Integer> right = new PriorityQueue<Integer>((a,b)->a-b);
        public void addNum(int num) {
            left.add(num);
            right.add(left.poll()); 
            if(right.size()>left.size()){
                right.add(num);
                left.add(right.poll());
            }
        }
        public double findMedian() {
            if(left.size()==right.size())
                return (left.peek()+right.peek())/2.0;
            else
                return (double)left.peek();
        }
    }
    
    //
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
    
    //
    class MedianFinder6 {
        Queue[] q = {new PriorityQueue(), new PriorityQueue(Collections.reverseOrder())};
        int i = 0;
        public void addNum(int num) {
            q[i].add(num);
            q[i^=1].add(q[i^1].poll());
        }
        public double findMedian() {
            return ((int)(q[1].peek()) + (int)(q[i].peek())) / 2.0;
        }
    }
    
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
    
    //
    class MedianFinder8 {
        Comparator<int[]> comp = ((a,b) -> a[0]==b[0] ? a[1]-b[1]:a[0]-b[0]);
        TreeSet<int[]> treeset= new TreeSet(comp);
        int[] lower = null, upper=null;
        int i=0;
        public void addNum(int num) {
            int[] t = new int[]{num,i++}; 
            treeset.add(t);
            if(treeset.size()==1)
                lower = upper = t;
            else if(lower==upper){
                if(comp.compare(t,lower)<0){
                    upper=lower;
                    lower=treeset.lower(lower);
                }else{
                    lower=upper;
                    upper= treeset.higher(upper);
                }
            }else{
                if(comp.compare(t,lower)<0)
                    upper=lower;
                else if(comp.compare(t,upper)>0)
                    lower=upper;
                else{
                    lower=upper=t;
                }
            }
        }
        public double findMedian() {
            if(lower==null) return 0;
            return (lower[0]+upper[0])/2.0;
        }
    }

    
    
    
    //follow ups
    // If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    class MedianFinder {
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
    
    //same as above
    class MedianFinder {
        int[] count = new int[101];
        int size=0;
        public void addNum(int num) {
            count[num]++;
            size++;
        }
        public double findMedian() {
            int m = size%2==0 ? size/2 : (size+1)/2;
            int c=0, idx=0;
            for(;idx<101;idx++){
                c+=count[idx];
                if(m<=c) break;
            }
            System.out.println(idx);
            if(size%2==1) return idx;
            if(m<c) return idx;
            for(int i=idx+1;i<101;i++)
                if(count[i]!=0)
                    return (idx+i)/2.0;
            return -1;
        }
    }
    
    
    // If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    class MedianFinder {
        int[] count = new int[101];
        int size=0, lz;
        public void addNum(int num) {
            if(num<0) lz++;
            else if (num>=0 &&num<=100)count[num]++;
            size++;
        }
        public double findMedian() {
            int m = size/2, idx = 0,first=0;
            for(int i=lz;i<101;i++){
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
    
    //
    class MedianFinder {
        int[] count= count = new int[101];
        int size =0, lz=0;
        public void addNum(int num) {
            size++;
            if(num<0)
                lz++;
            else
                for (int i = num; i < 101; i++) count[i]++;
        }
        public double findMedian() {
            int m = size % 2 == 0 ? size / 2 : (size + 1) / 2;
            int idx = binarySearch(count, m);
            if(size % 2 == 1)return idx;
            if(m < count[idx]) return idx;
            for (int i = idx + 1; i < 101; i++) 
                if (count[i] > count[idx])
                    return (idx + i) / 2.0;
            return idx;
        }
        private int binarySearch(int[] nums, int target) {
            int l = lz,r = nums.length;
            while (l < r) {
                System.out.println(l+" "+r);
                int mid = l + (r - l) / 2;
                if (target<=nums[mid])  r = mid;
                else l = mid + 1;
            }
            return l;
        }
    }
    
    // looks at this later
    // https://leetcode.com/problems/find-median-from-data-stream/solutions/1506078/java-detailed-code-solutions-for-follow-ups/
    
    
    //worst case
    class MedianFinder10 {

        ArrayList<Integer> list;

        public MedianFinder10() {
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
