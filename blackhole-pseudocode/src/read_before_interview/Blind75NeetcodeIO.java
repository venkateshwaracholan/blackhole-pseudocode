/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package read_before_interview;

/**
 *
 * @author venka
 */


// https://neetcode.io/practice

/*

Contains Duplicate	
    * Brute Force        : Compare each elem with every later elem(iterate i in (0, n-1), iterate j in (i+1, n-1)) to avoid checking self) to detect duplicates in O(n²).
    * HashSet Approach   : iterate i in (0, n-1), Use a HashSet to track seen elements(add after checking) and detect duplicates in O(n).
    * Sorting Approach   : Sort array and check adjacent elements for duplicates(iterate i in (1, n-1) to use i-1 withour error) in O(n log n).
    * Stream Approach    : Use Java Streams to compare distinct count with original length to detect duplicates in O(n) on average.

Valid Anagram
    * Brute Nested Match : For each char in s, linearly search t (as char[]) and mark match with '#' and break to avoid multiple match→ O(n²).
    * Sorting Approach   : Sort both strings and compare arrays → O(n log n).
    * HashMap Count      : Build frequency maps for s and t, compare counts → O(n).
    * Array Count (Opt)  : Use int[26], increment for s and decrement for t, all zeros ⇒ anagram → O(n).

Two Sum
    * Brute Force Approach : Check every pair of numbers(iterate i in (0, n-1), iterate j in (i+1, n-1) to avoid checking self) until one sums to target (O(n²) time, O(1) space).
    * HashMap Lookup       : Use a HashMap to store numbers and their positions, and check if complement (target - number) exists, add in map after checking.

Group Anagrams
    * Brute Force Pair Comparison: Compare every string with every other string and group anagrams manually using isAnagram() and a used[] array to avoid processing again→ O(n²·k) time, O(nk) space.
    * Sorting + HashMap: Sort each string and group by sorted string as key using a HashMap → O(n·k log k) time, O(nk) space.
    * Fixed-size Freq Array(int[26]) + HashMap: Count letter freq into a fixed array and use as a key(Ex: "bat" → "#1#1#0...#1") to group anagrams → O(n·k) time, O(nk) space. 

Top K Frequent Elements	
    * Brute Force: Count frequencies using a HashMap, sort entries by frequency with comparator, take top k → O(N log N), O(N) space.
    * Min Heap (Approach 1): Count frequencies using a HashMap, maintain size-(k+1) min-heap with comparator (a,b) → map.get(a)-map.get(b) → O(N log k), O(N + k) space.
    * Min Heap (Approach 1.2): Count frequencies using a HashMap, maintain size-k min-heap of Integer[] [num,freq], comparator on freq → a[1]-b[1] → O(N log k), O(N + k) space.
    * Bucket Sort: Count frequencies using a HashMap, invert map to buckets by frequency, traverse from high → low → O(N), O(N) space.
    * TreeSet/TreeMap: Count frequencies using a HashMap, maintain sorted order by frequency using comparator (freq, then value for uniqueness), use for top-k extraction → O(n log k) / O(n log n), O(n + k) space.
    * Comparator & Complexity Note:
    * - Heap: add/poll → O(log n), peek → O(1). Uses simple comparator for frequency, e.g., (a,b) -> freq.get(a)-freq.get(b).
    * - TreeMap/TreeSet: put/remove → O(log n), peek(first/last) → O(log n). Uses comparator to maintain sorted order of keys.
    * - Both self-balance; heaps preferred for top-k due to peek efficiency and simpler implementation, trees better for range queries or full ordering.

Product of Array Except Self
    * Brute Force: For each i(0 to n-1), iterate j(0 to n-1) multiply all nums[j] (j != i) and store in ans[i]→ O(n²) time, O(1) extra space.
    * Prefix & Suffix Product: Two-pass with left/right accumulators (ans[0]=l=r=1), ans[i] -> store all product before i, right pass inlcude ans[i] too in mul, no division → O(n) time, O(1) extra space.
    * Prefix Product In-Place (Optimized): Store left products in ans[], use one right accumulator (ans[0]=l,r=last) inlcude ans[i] too in mul→ O(n) time, O(1) extra space, fewer multiplications.
    * Division with Zero Handling: Compute p, pwz, zero count, handle zc>1, zc=1, zc=0 cases using pwz or p/n → O(n) time, O(1) extra space.  
    * Division with Zero Handling (skip total product): Track pwz, zero count, handle zc>1, zc=1, zc=0 separately → O(n) time, O(1) extra space.

Encode And Decode Strings

Longest Consecutive Sequence
    * Brute Force: For each number, while next consecutive exists via linear search, track max length → O(n³) time, O(1) space.  
    * Brute Force + HashSet: Use HashSet for O(1) contains, For each number, while next consecutive exists, track max length → O(n²) time, O(n) space.  
    * Optimized HashSet: Only start from sequence heads (n-1 absent), for each number, while next consecutive exists, track max length → O(n) time, O(n) space.  
    * Sort & Count: Sort array, skip duplicates, track consecutive length minima/maxima → O(n log n) time, O(1) extra space.  
   
Valid Palindrome
    * Brute Force: Filter and create lowercase alphanumerics, reverse, compare → O(n) time, O(n) space.
    * Two-Pointer: Skip non-alphanumerics with respective pointers, compare lowercase, move both inward if equal or exit false → O(n) time, O(1) space.
    * Two-Pointer with Map: Precompute alphanumeric map for fast lookup, skip non-alphanumerics, compare lowercase, , move both inward if equal or exit false → O(n) time, O(1) space.

3Sum
    * Brute Force: Sort array, three nested loops i(0 to n-1), j(i+1 to n-1), k(j+1 to n-1), skip first element duplicates(optional as set covers), store triplets in HashSet → O(n³) time, O(n) space.
    * Complement Lookup: Sort array, for i(0 to n-1), for j(i+1 to n-1) compute complement=0-i-j in inner loop(like two sum), check HashSet for existence, if present store triplets in HashSet  → O(n²) time, O(n) space.
    * Two-Pointer + HashSet: Sort array, for every i, inner loop j=i+1,k=len-1, move j->right on sum<0, k->left sum>0, add sum=0 to HashSet → O(n²) time, O(n) space.  
    * Two-Pointer Inline Dupes skip + Arraylist: Sort array, for every i, skip i duplicates(must coz no set), inner loop j=i+1,k=len-1, move j->right on sum<0, k->left sum>0, on sum=0 add to arraylist, j/k duplicates inline with while and bound j<k, → O(n²) time, O(1) extra space.

Container With Most Water
    * Brute Force: for each elem with every later elem(iterate i in (0, n-1), iterate j in (i+1, n-1)), height = min(height[i], height[j]), width = j-i, area = height*width, keep max → O(n²) time, O(1) space.
    * Two-Pointer Optimized(Move smaller h inward): Two pointers at both ends i = 0, j = n-1, height = min(height[i], height[j]), width = j-i, area = height*width, move pointer with smaller height inward, keep max → O(n) time, O(1) space.
    
Best Time to Buy And Sell Stock	
    * Brute Force: for each elem with every later elem(iterate i in (0, n-1), iterate j in (i+1, n-1)), profit = prices[j] - prices[i], keep max → O(n²), O(1).
    * One-Pass Optimized: iterate i in (0, n-1), track min price so far in low, profit = prices[i] - low, update max → O(n), O(1).
    * Sliding Window: iterate i = 0, j=i+1 to n-1, store min price index in i(slide i = j if prices[j] < prices[i]), profit = prices[j] - prices[i], update max → O(n), O(1).

Longest Substring Without Repeating Characters	

Longest Repeating Character Replacement	

Minimum Window Substring	

Valid Parentheses	

Find Minimum In Rotated Sorted Array	

Search In Rotated Sorted Array	



Reverse Linked List	
Merge Two Sorted Lists	
Reorder List	
Remove Nth Node From End of List	
Linked List Cycle	
Merge K Sorted Lists	
Invert Binary Tree	
Maximum Depth of Binary Tree	
Same Tree	
Subtree of Another Tree	
Lowest Common Ancestor of a Binary Search Tree	
Binary Tree Level Order Traversal	
Validate Binary Search Tree	
Kth Smallest Element In a Bst	
Construct Binary Tree From Preorder And Inorder Traversal	
Binary Tree Maximum Path Sum	
Serialize And Deserialize Binary Tree	
Implement Trie Prefix Tree	
Design Add And Search Words Data Structure	
Word Search II	
Find Median From Data Stream	
Combination Sum	
Word Search	
Number of Islands	
Clone Graph	
Pacific Atlantic Water Flow	
Course Schedule	
Number of Connected Components In An Undirected Graph   	
Graph Valid Tree   	
Alien Dictionary   	
Climbing Stairs	
House Robber	
House Robber II	
Longest Palindromic Substring	
Palindromic Substrings	
Decode Ways	
Coin Change	
Maximum Product Subarray	
Word Break	
Longest Increasing Subsequence	
Unique Paths	
Longest Common Subsequence	
Maximum Subarray	
Jump Game	
Insert Interval	
Merge Intervals	
Non Overlapping Intervals	
Meeting Rooms   	
Meeting Rooms II   	
Rotate Image	
Spiral Matrix	
Set Matrix Zeroes	
Number of 1 Bits	
Counting Bits	
Reverse Bits	
Missing Number	
Sum of Two Integers

*/

public class Blind75NeetcodeIO {
    
}
