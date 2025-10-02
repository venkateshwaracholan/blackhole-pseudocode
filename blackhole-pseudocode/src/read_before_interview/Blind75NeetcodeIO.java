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
    * Iterative Reverse List: prev=null, cur=head, while cur!=null store next(cur.next before modifying cur.next), reverse link(cur.next=prev), move pointers(prev=cur, cur=next), return prev → O(n), O(1).
    * Top‑down Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, store next(cur.next), reverse link(cur.next=prev), recurse with(next,cur) → O(n), O(n) (stack).
    * Bottom‑up Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, recurse head=reverseList(cur.next,cur) without changing head until recursion unwinds (because reversal happens bottom‑up), after recursion unwinds reverse link via cur(cur.next=prev), return head level by level → O(n), O(n) (stack).
    * Bottom‑up Recursive Reverse List (Single Method): base case: head==null || head.next==null (handle empty list and return last node as new head), recurse last=reverseListBottomUpSingleMethod(head.next) without changing last until recursion unwinds (because reversal happens bottom‑up), then reverse link via head.next.next=head (modify head here), then mark head.next=null to terminate old link so that first element’s next becomes null, preserve last as new head to return → O(n), O(n) (stack).
    
Merge Two Sorted Lists
    * Recursive Merge: if list1 or list2 is null return the other (base case), else compare head values — if list1.val < list2.val (link list1 to merged remainder → list1.next = mergeTwoListsRec(list1.next,list2)) else (link list2 to merged remainder → list2.next = mergeTwoListsRec(list1,list2.next)), return the smaller head → O(n+m), O(n+m) (stack).
    * Iterative Merge (Dummy Node): dummy=0, cur=dummy, while both lists not null compare heads, attach smaller to cur.next, move chosen list pointer forward (list1=list1.next or list2=list2.next) and cur pointer forward (cur=cur.next), after loop attach remaining list (cur.next = list1 != null ? list1 : list2) → O(n+m), O(1).
    * Unsafe Merge Without Dummy: head=cur=null, loop while list1!=null || list2!=null (null handling with Integer.MAX_VALUE), pick smaller node as next, move chosen list pointer forward (list1=list1.next or list2=list2.next), if head==null assign head=cur=next else set cur.next=next and cur=next, return head → O(n+m), O(1).

Linked List Cycle
    * Cycle Detection Brute (HashSet): traverse list, store visited nodes in set, if a node repeats return true → O(n), O(n).
    * Cycle Detection (Floyd’s Tortoise and Hare): slow=head, fast=head, move slow by 1 and fast by 2 steps, then check if slow equals fast (only after both moves, coz they are initially equal) → O(n), O(1).

Reorder List
    * Reorder List (Stack): push all nodes into stack, set c=stack.size()/2, cur=head; while(c>0) save cur.next as next and stack.pop() as rev, then chain alternately (cur.next=rev, rev.next=next), move cur=next, decrement c; after loop set cur.next=null (avoid cycle) → O(n), O(n)
    * Reorder List (Mid Reversal + Merge): find mid (odd → exact mid, even → mid+1) with slow/fast pointers, reverse second half from slow to end, set cur=head; while(cur!=slow) traverse up to mid, save cur.next as next and rev.next as revNext to preserve chains, chain cur.next=rev and rev.next=next, advance cur=next, rev=revNext, after loop set cur.next=null (tail termination to avoid cycle) → O(n), O(1) extra space.
    * Reorder List (Recursive bottom-up with [head] for left(reference not value)): traverse to end (right moves bottom-up), carry left pointer as [head]; on unwind save next=left[0].next, chain left[0].next=right and right.next=next, advance left[0]=next; stop if (left[0].next == null) (short list) or if (left[0].next == right) (middle reached, odd/even handled) { left[0].next=null }; e.g., even: [l=2,l.next=3,r=4]→[l=3,l.next=3,r=3]→stop→1→4→2→3, odd: [l=2,l.next=3,r=5]→[l=3,l.next=4,r=4]→stop→1→5→2→4→3 → O(n), O(n) recursion stack.
    	
Remove Nth Node From End of List
    * Remove Nth Node From End (Two Pass): traverse list with cur=head while(cur!=null) increment len (count total nodes), if len==n return head.next (first node removed), else reset cur=head, for i=0;i<len-n-1;i++ cur=cur.next (land on node before target), set cur.next=cur.next.next (skip target) and return head → O(2n), O(1) (explicit bounds and pointer advances without extra memory).
    * Remove Nth Node From End (Recursive with Reference): call recursion(head, new int[]{n}) (passing as reference), if(cur==null) return cur (base case); call recursion(cur.next, n) (recursion call); on unwind set cur.next=next (preserve chain after possible skip from return value), decrement n[0] (compare with 0, not 1), if n[0]==0 return next (skip target), else return cur → O(n), O(n) recursion stack (single pass bottom-up without explicit length counting).
    * Remove Nth Node From End (Two Pointers): set prev=head, cur=head; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; if cur==null return head.next (target is first node); else while(cur.next!=null) (ensures prev lands on node before target) advance both prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node) → O(n), O(1).
    * Remove Nth Node From End (Two Pointers + Dummy): create dummy=0,dummy.next=head (dummy removes first element removal edge case), set prev=dummy,cur=dummy; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; while(cur.next!=null) (ensures prev lands on node before target) advance prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node); return dummy.next (coz we added dummy) → O(n), O(1).
	
Merge K Sorted Lists
    * Merge K Sorted Lists (Brute Force Array Scan): create dummy node and cur pointer, while(true) — keep looping until all k lists exhausted, in each iteration find minIndex by scanning lists[] heads using if(lists[i]!=null && lists[i].val<min) to ensure only valid non-null heads are considered and min always holds the smallest value, if minIndex==-1 return dummy.next (all lists exhausted), else attach lists[minIndex] to cur.next, advance lists[minIndex] to next, advance cur → O(nk), O(1) extra space.
    * Merge K Sorted Lists (Min‑Heap): create dummy=0, cur=dummy(set result start), add all non-null heads to minHeap(PriorityQueue by a.val-b.val)(initialize heap with smallest heads as each list is sorted); while(minHeap.size()>0)(process until all nodes merged) — var minNode=minHeap.poll()(poll smallest node), cur.next=minNode(attach to cur.next), cur=cur.next(advance cur pointer); if(minNode.next!=null)add(minNode.next)(add next of polled node if present so next smallest of that list remains available) → O(n log k), O(k) extra space.
    * Merge K Sorted Lists (Divide & Conquer Partition): recursively partition lists range (to balance merges instead of sequential nk merges), Edge case: if lists.length==0 return null (empty input), directly call partition(lists,0,lists.length-1) (paritition full range rec), base case (if l==r return lists[l] → one list needs no merge → bottom of recursion), otherwise compute mid=l+(r-l)/2 (safe midpoint), recursively merge left=partition(l,mid), right=partition(mid+1,r), then return merge(left,right) (on unwind left and right single list merge two sorted lists by attaching smaller head first and recursing on the rest) → O(n log k), O(log k) recursion stack.
    * Merge K Sorted Lists (Iterative Pairwise Merge): if(size==0)return nullptr(edge case, no lists); set interval=1(start merging pairs of size 1), while(interval<size)(merge lists in increasing ranges until all merged) — for(x=0;x<size-interval;x+=2*interval)(bound x<size-interval coz interval x+intreval is used, jump by 2*interval to process non‑overlapping pairs) merge lists[x] and lists[x+interval](merge two sorted lists in place) and store at lists[x](overwrite with merged list); interval*=2(double merge range each pass) → O(n log k), O(1) extra space.

Invert Binary Tree
    * Invert Binary Tree (Top-Down DFS): if (root == null) return root (base case), save TreeNode left = root.left, swap children root.left = root.right, root.right = left (swapping with saved var coz root.left is modified), call invertTreeTopDown(root.left), invertTreeTopDown(root.right) (process subtrees after swap), return root → O(n), O(h) recursion stack.
    * Invert Binary Tree (Bottom-Up DFS): if (root == null) return root (base case), save TreeNode left = root.left, assign root.left = invertTreeRecBottomUp(root.right), root.right = invertTreeRecBottomUp(left) (cross-assign achieves swap using saved var coz root.left is modified), return root (children processed by recursion) → O(n), O(h).
    * Invert Binary Tree (BFS Iterative): Initialize queue = new LinkedList<TreeNode>(), if (root != null) enqueue root, while (queue not empty) poll node (TreeNode node = queue.poll()), save TreeNode left = node.left, swap node.left = node.right, node.right = left (swapping with saved var coz node.left is modified), enqueue node.left,node.right if not null (process subtrees after swap), finally return root → O(n), O(n).

Maximum Depth of Binary Tree
    * Maximum Depth (DFS Bottom‑Up): if(root==null)return 0(base case: empty tree depth=0(coz recursion visits null leafs)), recursively compute lDepth=maxDepthRecBottomUp(root.left)+1 and rDepth=maxDepthRecBottomUp(root.right)+1(add 1 for current node, depth computation happens bottom‑up when recursion unwinds), return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    * Maximum Depth (DFS Top‑Down): call maxDepthTopDown(node,0) with depth=0(start at root, 0 coz recursion visits null leafs), if(node==null)return d(base case: leaf child depth=d coz top down depth compute), recursively call left and right with depth+1(depth computation happens top‑down) and return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    * Maximum Depth (BFS Level Order): initialize depth=0 (coz depth is incremented after each level), use queue for level order traversal, if(root!=null) Add root to queue, while queue not empty —> size=queue.size(), iterate size times to process current level, add non‑null children to queue, increment depth after processing level → O(n), O(n) space.
    * Maximum Depth (BFS with Node+Depth): use queue storing Entity(node,depth)(depth stored explicitly since recursion is not used, level order not done), create record record Entity(TreeNode node, int depth){}, queue = new LinkedList<Entity>() (Create queue storing Node+Depth pairs.), initialize maxDepth=0(track max), if(root!=null) queue.add(new Entity(root, 1)) (add non null entity to queue root depth=1 coz ite wont visit null leafs), while queue not empty — poll Entity, node, depth, add non‑null children with depth+1, track max depth encountered → O(n), O(n) space.
    
Same Tree
    * isSameTreeBottomUp: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), recursively compute left=isSameTreeBottomUp(p.left,q.left) and right=isSameTreeBottomUp(p.right,q.right)(left/right comparison happens bottom‑up), return p.val==q.val && left && right(value check happens after left/right checks when recursion unwinds same bottom up) → O(n), O(n) recursion space.
    * isSameTreeTopDown: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), if(p.val!=q.val)return false(values differ)(value check happens top‑down), recursively compute left=isSameTreeTopDown(p.left,q.left) and right=isSameTreeTopDown(p.right,q.right)(left/right comparison happens after recursion unwinds, bottom up) → O(n), O(n) recursion space.
    * isSameTreeBfsIteQueueDoubleAdd: Init queue queue = new LinkedList<TreeNode>(), initialize queue with both root nodes(p,q) for double poll, while queue not empty poll pNode,qNode(double poll), (if both null means equal, continue), if either null or pNode.val!=qNode.val return false(value check happens level by level), add left children and right children pairs(queue.add(pNode.left,qNode.left,pNode.right,qNode.right)(add both left and then only both right(polling twice for p and q), left/right comparison happens level‑by‑level)), return true(all node pairs match) → O(n), O(n) space.
    * isSameTreePreOrderString: call preOrder(p,new StringBuilder()) and preOrder(q,new StringBuilder()) to build preorder sequence of p and q (with null marker 'n' using preOrder(node,sb): if(node==null)sb.append("n"):encode null, sb.append(node.val):add value, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(pPreorder.equals(qPreorder)), return true if equal else false → O(2n), O(2n) space.

Subtree of Another Tree
    * isSubtreeDfsTopDown: if(root==null)return false(base case: empty tree can't contain subRoot), if(isSameTree(root,subRoot))return true(root matches subRoot, isSameTree check happens top‑down), boolean left=isSubtree(root.left,subRoot) and boolean right=isSubtree(root.right,subRoot), return left || right(subtree may exist in either branch, left/right comparison happens bottom‑up) → O(m*n), O(m+n) recursion space.
    * isSubtreeBottomUp(slower): if(root==null)return false(base case: empty tree can't contain subRoot), boolean left=isSubtreeBottomUp(root.left,subRoot) and boolean right=isSubtreeBottomUp(root.right,subRoot), return isSameTree(root,subRoot) || left || right(root check with isSameTree happens bottom‑up after left/right recursion unwinds) → O(m*n), O(m+n) recursion space.
    * isSubtreeBfsIte: Init queue=LinkedList<TreeNode>(), if(root!=null) add root to queue, while queue not empty poll node, if(isSameTree(node,subRoot)) return true(value and structure check happens level‑by‑level using isSameTree bottom‑up), add non‑null children to queue(left then right), return false(if no match found) → O(m*n), O(m) space.
    * isSubtreePreOrderString: call preOrder(root,new StringBuilder()) and preOrder(subRoot,new StringBuilder()) to build preorder sequences of root and subRoot (with null marker 'n' for null nodes and '#' as value separator(coz we wud call substring method) using preOrder(node,sb): if(node==null)sb.append("n"):encode null to preserve structure, sb.append('#').append(node.val):add value with '#' separator to avoid false matches in substring, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(rootPreOrder.indexOf(subRootPreOrder)!=-1)(substring search checks if subRoot structure+values exist in root), return true if found else false → O(m+n) serialization + O(m*n) substring search (naive) or O(m+n) with KMP, O(m+n) space.

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
