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

ThreeSum
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
    * Brute Force: for each substring check repeats (iterate i in (0, n-1), iterate j in (i, n-1)), check repetition in s[i..j] using set, update max if uniq and j-i+1>max → O(n³), O(n).
    * Sliding Window + HashSet: iterate i = 0, j = i to n-1, shrink window while duplicate char at j by moving i and removing s[i] from set, add s[j] to set, update max → keeps unique chars in window → O(2n), O(min(n, charset)).
    * Sliding Window + HashMap(val,index): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex map after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j] in map, update max if j-i+1>max→ O(n), O(min(n, charset)).
    * Sliding Window + Integer[256](asciiVal, pos): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex array after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j], update max if j-i+1>max→ faster for ASCII → O(n), O(256).  

Longest Repeating Character Replacement
    * Brute Force: for each substring s[i..j] (iterate i in (0, n-1), iterate j in (i, n-1)), iterate all chars to find max frequency, if j-i+1(window length) ≤ maxFreq+k(valid window = count+mistakes) update max → O(n³) for constant charset, O(n⁴) if counting all substring chars, O(1) space.
    * Brute Sliding Window by char: for each character at x (iterate x in (0, n-1)), iterate i = 0, j = 0 to n-1, track count of matches s[x]==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==s[x], update max if j-i+1>max → check all chars → O(n²), O(1).
    * Sliding Window by unique char: collect letters set, for each l in letters set, iterate i = 0, j = i to n-1, track count of matches letter==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==l, update max if j-i+1>max → avoids redundant checks → O(n×U), O(U).
    * Optimized Sliding Window with array and maxf: iterate i = 0, j = i to n-1, increment map[c] for s[j], track maxf = max frequency in window, shrink window if j-i+1(window length)>maxf+k (valid window = max frequency+mistakes) by moving i and decrementing map[s[i]], update max if j-i+1>max → track most frequent char dynamically → O(n), O(26).

Minimum Window Substring
    * Brute Force: build tCount[128] with count, for every substring (iterate i=0,j=i→n-1), inner loop expand j updating sCount[jchar]++, valid window = sCount has at least tCount chars (checked by iterating all 128 chars in contains method), update min if j-i+1<min → O(n²*128), O(128).
    * Sliding Window (HashMap): build tMap with count, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if j-i+1<min → O(n), O(|S|+|T|).
    * Filtered Sliding Window (HashMap): build tMap with count, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→filtered.size()-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(|S|+|T|).
    * Sliding Window (int[]): build tCount[128] with count, track uniqT, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if j-i+1<min → O(n), O(128).
    * Filtered Sliding Window (int[]): build tCount[128] with count, track uniqT, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→idxLen-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(128).
    

Valid Parentheses
    * Brute Force Replacement: set prev="", while (s not equals prev(meaning s didn’t change after replacing)) assign prev=s and repeatedly replace "()", "[]", "{}" in s ans assign replaced string to s, if result is empty → valid parentheses → O(n²), O(n).
    * Stack Push Open: iterate i=0→n-1, push open brackets to stack, else if stack empty or no corresponding brace present (c=')',t!='(' || c=']',t!='[' || c='}',t!='{') → false, valid = stack empty → O(n), O(n).
    * Stack Push Open Map: build map = {('('→')'), ('{'→'}'), ('['→']')}, iterate i=0→n-1, if map.containsKey(c) push c(open brace), else if(close brace) stack empty or map.get(st.pop())!=c (get corresp close brace from stack(has only open) with map) → false, valid = stack empty → O(n), O(n).
    * Stack Push Expected Close: iterate i=0→n-1, if c='(' push ')', c='{' push '}', c='[' push ']', else if stack empty or st.pop()!=c (expected close brace already pushed to stack)→ false, valid = stack empty → O(n), O(n).
    * Stack Map Push Expected Close: build map = {('('→')'), ('{'→'}'), ('['→']')}, iterate i=0→n-1, if map.containsKey(c) push map.get(c) (pushing expected close brace from map), else if stack empty or st.pop()!=c(expected close brace already pushed to stack) → false, valid = stack empty → O(n), O(n).

Find Minimum In Rotated Sorted Array
    * Binary Search r‑anchored: set l=0,r=n-1, if nums[l] ≤ nums[r] (no rotation) return nums[0](first elem), else binary search anchored on r — compare mid with nums[r]: if nums[mid] > nums[r] → l=mid+1 (min in right half), else r=mid (min in left half including mid), repeat until l=r (meaning we always keep min in [l,r]), return nums[l] (min) → O(log n), O(1).
    * Unsafe Binary Search: set l=0,r=n-1, if nums[l] ≤ nums[r] (no rotation) return nums[0](first elem), else use l ≤ r and mid+1 check — if nums[mid]>nums[mid+1] pivot found return nums[mid+1], else if nums[mid]>nums[r] → l=mid+1 else r=mid; repeat until found → O(log n), O(1); risk of index errors if not mid+1 check.
    * Unsafe l‑variant: set l=0,r=n-1, if nums[l] ≤ nums[r] (no rotation) return nums[0](first elem), else use l ≤ r with mid+1 check — if nums[mid]>nums[mid+1] return nums[mid+1], else if nums[l]<nums[mid] → l=mid+1 else r=mid; repeat until found, return nums[l] → O(log n), O(1).risk of index errors if not mid+1 check.

Search In Rotated Sorted Array
    * Single-pass Modified Binary Search: Finds the sorted half and skips it if the target isn’t there, set l=0,r=n-1, while l≤r, mid= l+(r-l)/2; if nums[mid]==target return mid; if nums[l]≤nums[mid] (left sorted) → if target lies in [nums[l],nums[mid]) then r=mid-1 else l=mid+1(skip left sorted); else (right sorted) → if target lies in (nums[mid],nums[r]] then l=mid+1 else r=mid-1(skip right sorted); repeat until found or l>r, return -1 → O(log n), O(1).
    * Two-step Binary Search find rotation pivot, then binary search in correct sorted half: set l=0,r=n-1, if nums[l]≤nums[r] return binarySearch(l,r); else find rotation index rot: while l<r mid=l+(r-l)/2 → if nums[mid]>nums[r] l=mid+1 else r=mid; return l; then binarySearch in correct half: if target lies in [nums[rot],nums[r]] search [rot,r] else search [l,rot-1]; binarySearch: standard O(log n) search → O(log n), O(1).
    



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
