package ds_algorithm.dfs_or_bfs;
import java.util.*;

// === Problem Name
// NestedListWeightSum (alias: WeightedSumOfNestedList)

// === Problem Description (LeetCode-style)
/*
You are given a nested list of integers represented by the interface NestedInteger. 
Each element is either an integer or a list of NestedIntegers.  

Implement a function to compute the sum of all integers in the list weighted by their depth.
The weight is defined as the depth level of the integer (top-level = 1).

Interface:
interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

Input format:
- nestedList: List<NestedInteger>, may contain integers or nested lists
Output format:
- integer representing weighted sum

Problem examples:

Example 1:
Input: [[1,1],2,[1,1]]
Output: 10
Explanation: 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10

Example 2:
Input: [1,[4,[6]]]
Output: 27
Explanation: 1*1 + 4*2 + 6*3 = 27

Constraints & realistic ranges:
- Depth can be up to 50
- List size up to 500
- Integers within [-100, 100]

Edge cases:
- Empty list → sum 0
- All integers at top level
- Deeply nested list
- Single integer
- Repeated nested patterns
- Null lists (defensive handling)

Recap constraints:
- Depth-weighted sum
- Nested lists possible
- Must handle large depth efficiently

    [[1,1],2,[1,1]]
    ├── [1,1]      (depth 1)
    │   ├── 1      (depth 2)
    │   └── 1      (depth 2)
    ├── 2          (depth 1)
    └── [1,1]      (depth 1)
        ├── 1      (depth 2)
        └── 1      (depth 2)
*/

// === DFS Solution
/*
ONE LINER: DFS recursion computing weighted sum by multiplying integer by current depth.

Idea:
- Recursively traverse nested lists
- Multiply integer by current depth and sum
- Increment depth when entering a nested list

Limitations:
- Stack depth limited by recursion
- Time proportional to total number of integers
- Space proportional to recursion depth

Time Complexity:
- Interview O(n), absolute O(T) where T = total number of integers + nested lists
Space Complexity:
- Interview O(d), absolute O(d) recursion stack where d = max depth
*/

class NestedListWeightSum {


    /*
    * depthSumDfs (Recursion + DFS)
    * ONE LINER: public int depthSumDfs(List<NestedInteger> nestedList): (wrapper method calling recursive DFS with depth 1); return depthSumDfs(nestedList, 1): (start DFS recursion at depth 1); public int depthSumDfs(List<NestedInteger> nestedList, int depth): (recursive DFS method declaration); if(nestedList==null) return 0: (defensive base case, handle null input gracefully); int sum = 0: (initialize cumulative sum for current depth); for(NestedInteger ni:  nestedList): (iterate over all elements in the current list); if(ni.isInteger()) sum+=depth*ni.getInteger(): (weighted sum for integer elements multiplied by current depth); else sum+=depthSumDfs(ni.getList(),depth+1): (recursive DFS call for sublist with incremented depth); return sum: (return cumulative weighted sum for this recursive call) → O(T), O(d) space (absolute: T + d, where T = total number of integers + nested lists, d = max depth)
    *
    * Code(Reason):
    * - public int depthSumDfs(List<NestedInteger> nestedList): wrapper method that initializes DFS recursion starting at depth 1.
    * - return depthSumDfs(nestedList, 1): call recursive helper with initial depth of 1.
    * - public int depthSumDfs(List<NestedInteger> nestedList, int depth): recursive DFS method declaration for weighted sum computation.
    *     - if(nestedList==null) return 0: defensive check for null input to prevent NullPointerException and handle empty or invalid lists.
    *     - int sum = 0: initialize sum accumulator for current recursive call.
    *     - for(NestedInteger ni:  nestedList): iterate through each element in the current list to process integers and sublists.
    *         - if(ni.isInteger()) sum+=depth*ni.getInteger(): add integer value weighted by current depth to sum.
    *         - else sum+=depthSumDfs(ni.getList(),depth+1): recursively traverse sublist, incrementing depth to account for nesting level.
    *     - return sum: return total weighted sum calculated for this level of recursion.
    *
    * Rationale: DFS recursion naturally tracks depth by passing the depth parameter. Each integer contributes to the total sum multiplied by its depth, and recursion ensures all nested lists are processed. Base case handles null lists safely, making the method robust for empty or malformed input.
    *
    * Time Complexity: O(T) — each integer and sublist is visited exactly once (absolute: T, where T = total number of integers + lists)
    * Space Complexity: O(d) — recursion stack up to maximum depth of nested lists (absolute: d)
    *
    * Examples:
    * Example 1:
    * Input: [ [1,1], 2, [1,1] ] (represented with NestedInteger structure)
    * Trace: depth=1 → sum=0; first element [1,1] → recursive depth=2 → sum += 1*2 + 1*2 = 4; second element 2 → depth=1 → sum += 2*1=2; third element [1,1] → recursive depth=2 → sum += 1*2 + 1*2 = 4; total sum = 4+2+4=10
    * Output: 10
    *
    * Example 2:
    * Input: [1,[4,[6]]] (NestedInteger structure)
    * Trace: depth=1 → sum=0; first element 1 → sum += 1*1=1; second element [4,[6]] → recursive depth=2 → sum += 4*2=8; nested [6] → recursive depth=3 → sum += 6*3=18; total sum = 1+8+18=27
    * Output: 27
    */

    public int depthSumDfs(List<NestedInteger> nestedList){
        return depthSumDfs(nestedList, 1);
    }

    public int depthSumDfs(List<NestedInteger> nestedList, int depth){
        if(nestedList==null){
            return 0;
        }
        int sum = 0;
        for(NestedInteger ni:  nestedList){
            if(ni.isInteger()){
                sum+=depth*ni.getInteger();
            }
            else{
                sum+=depthSumDfs(ni.getList(),depth+1);
            }
        }
        return sum;
    }



    /*
    * depthSumBfs (Queue + BFS + Iterative)
    * ONE LINER: public int depthSumBfs(List<NestedInteger> nestedList): (method declaration for BFS weighted sum); var queue = new LinkedList<List<NestedInteger>>(): (initialize queue of lists to process each level iteratively); int sum=0, depth=1: (initialize cumulative sum and starting depth); if(nestedList!=null) queue.add(nestedList): (add initial list to queue if non-null); while(!queue.isEmpty()): (process lists level by level); int size = queue.size(): (track number of lists at current depth level); while(size>0): (iterate through all lists at current depth level); for(NestedInteger ni: queue.poll()): (poll current list and iterate through its elements); if(ni.isInteger()) sum+=depth*ni.getInteger(): (add integer weighted by current depth to sum); else if(ni.getList()!=null) queue.add(ni.getList()): (add non-null nested sublist to queue for next depth level); size--: (decrement size counter for current level); depth++: (increment depth after finishing current level); return sum: (return total weighted sum) → O(T), O(W) space (absolute: T + W, where T = total number of integers + nested lists, W = max number of lists at any depth)
    *
    * Code(Reason):
    * - public int depthSumBfs(List<NestedInteger> nestedList): method declaration for BFS-based depth-weighted sum.
    * - var queue = new LinkedList<List<NestedInteger>>(): initialize a queue to hold lists at each depth level for BFS traversal.
    * - int sum=0, depth=1: initialize cumulative sum accumulator and starting depth of 1.
    * - if(nestedList!=null) queue.add(nestedList): enqueue initial list if it is non-null.
    * - while(!queue.isEmpty()): loop to process all lists level by level until queue is empty.
    *     - int size = queue.size(): record number of lists at current depth level to process correctly in inner loop.
    *     - while(size>0): iterate over all lists at current depth level.
    *         - for(NestedInteger ni: queue.poll()): poll one list from queue and iterate over its elements.
    *             - if(ni.isInteger()) sum+=depth*ni.getInteger(): add weighted integer value to sum for current depth.
    *             - else:
    *                 - if(ni.getList()!=null) queue.add(ni.getList()): enqueue non-null nested sublist for processing in next depth level.
    *         - size--: decrement counter of lists to process at current level.
    *     - depth++: increment depth after finishing all lists at current level.
    * - return sum: return the total weighted sum after BFS traversal is complete.
    *
    * Rationale: BFS iteratively processes all lists at each depth level. Each integer contributes to the sum weighted by its depth. Nested lists are enqueued for the next depth level, ensuring all elements are visited exactly once. This approach avoids recursion stack usage and explicitly tracks depth level.
    *
    * Time Complexity: O(T) — each integer and sublist is visited exactly once (absolute: T, where T = total number of integers + nested lists)
    * Space Complexity: O(W) — queue stores all lists at the maximum width (number of lists) at any depth level (absolute: W)
    *
    * Examples:
    * Example 1:
    * Input: [ [1,1], 2, [1,1] ] (NestedInteger structure)
    * Trace: depth=1 → sum=0; queue=[ [ [1,1], 2, [1,1] ] ]; size=1; poll first list → process elements: [1,1] → enqueue, 2 → sum+=2*1=2, [1,1] → enqueue; depth=2 → queue has two lists [1,1] and [1,1]; process each list: sum += 1*2 + 1*2 + 1*2 + 1*2 = 8; total sum = 2+8=10
    * Output: 10
    *
    * Example 2:
    * Input: [1,[4,[6]]] (NestedInteger structure)
    * Trace: depth=1 → sum=0; queue=[ [1,[4,[6]]] ]; size=1; poll list → 1 → sum+=1*1=1; [4,[6]] → enqueue; depth=2 → queue has one list [4,[6]]; process elements: 4 → sum+=4*2=8; [6] → enqueue; depth=3 → queue has one list [6]; process element 6 → sum+=6*3=18; total sum = 1+8+18=27
    * Output: 27
    */


    public int depthSumBfs(List<NestedInteger> nestedList){
        var queue = new LinkedList<List<NestedInteger>>();
        int sum=0, depth=1;
        if(nestedList!=null){
            queue.add(nestedList);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                for(NestedInteger ni: queue.poll()){
                    if(ni.isInteger()){
                        sum+=depth*ni.getInteger();
                    }
                    else{
                        if(ni.getList()!=null){
                            queue.add(ni.getList());
                        }
                    }
                }
                size--;
            }
            depth++;
        }
        return sum;
    }


    /*
    * depthSumBfsAlt (Queue + BFS + Iterative)
    * ONE LINER: public int depthSumBfsAlt(List<NestedInteger> nestedList): (method declaration for BFS weighted sum alternative); var queue = new LinkedList<NestedInteger>(): (initialize queue of NestedInteger elements to process iteratively); int sum=0,depth=1: (initialize cumulative sum and starting depth); queue.addAll(nestedList): (enqueue all top-level NestedInteger elements); while(!queue.isEmpty()): (process elements level by level); int size = queue.size(): (record number of elements at current depth level); for(int i=0;i<size;i++): (iterate over all elements at current depth level); NestedInteger ni = queue.poll(): (poll current element from queue); if(ni.isInteger()) sum+=depth*ni.getInteger(): (add weighted integer value to sum); else queue.addAll(ni.getList()): (enqueue all nested elements for next depth level); depth++: (increment depth after finishing current level); return sum: (return total weighted sum) → O(T), O(W) space (absolute: T + W, where T = total number of integers + nested lists, W = maximum number of elements at any depth)
    *
    * Code(Reason):
    * - public int depthSumBfsAlt(List<NestedInteger> nestedList): method declaration for BFS-based depth-weighted sum alternative.
    * - var queue = new LinkedList<NestedInteger>(): initialize a queue to hold all NestedInteger elements for BFS traversal.
    * - int sum=0,depth=1: initialize cumulative sum and starting depth level.
    * - queue.addAll(nestedList): enqueue all elements from the input nested list to start BFS.
    * - while(!queue.isEmpty()): loop to process all elements level by level until queue is empty.
    *     - int size = queue.size(): store the number of elements at the current depth to process exactly this many in the loop.
    *     - for(int i=0;i<size;i++): iterate over all elements at the current depth level.
    *         - NestedInteger ni = queue.poll(): remove the first element from the queue for processing.
    *         - if(ni.isInteger()) sum+=depth*ni.getInteger(): add integer weighted by current depth to cumulative sum.
    *         - else queue.addAll(ni.getList()): enqueue all nested elements for processing at the next depth level.
    *     - depth++: increment depth after finishing all elements at the current level.
    * - return sum: return the total weighted sum after processing all levels.
    *
    * Rationale: This BFS approach iterates over each NestedInteger element directly rather than lists. At each depth level, integers contribute to the sum multiplied by the current depth, and nested lists are expanded into the queue for the next level. It ensures all elements are visited exactly once and the depth weighting is correctly applied.
    *
    * Time Complexity: O(T) — each integer and nested element is visited exactly once (absolute: T, where T = total number of integers + nested lists)
    * Space Complexity: O(W) — queue stores all elements at the maximum width (number of elements) at any depth level (absolute: W)
    *
    * Examples:
    * Example 1:
    * Input: [ [1,1], 2, [1,1] ] (NestedInteger structure)
    * Trace: depth=1 → sum=0; queue=[ [1,1], 2, [1,1] ]; size=3; process elements: [1,1] → enqueue 1,1; 2 → sum+=2*1=2; [1,1] → enqueue 1,1; depth=2 → queue has four 1's; sum += 1*1 +1*1 +1*1 +1*1=4; total sum = 2+4=6 (assumed: integer elements counted individually as BFS processes all NestedInteger); Output: 6
    *
    * Example 2:
    * Input: [1,[4,[6]]] (NestedInteger structure)
    * Trace: depth=1 → sum=0; queue=[1,[4,[6]]]; size=2; process 1 → sum+=1*1=1; [4,[6]] → enqueue 4,[6]; depth=2 → queue=[4,[6]]; process 4 → sum+=4*2=8; [6] → enqueue 6; depth=3 → queue=[6]; process 6 → sum+=6*3=18; total sum = 1+8+18=27
    * Output: 27
    */

    public int depthSumBfsAlt(List<NestedInteger> nestedList){
        var queue = new LinkedList<NestedInteger>();
        int sum=0,depth=1;
        queue.addAll(nestedList);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                NestedInteger ni = queue.poll();
                if(ni.isInteger()){
                    sum+=depth*ni.getInteger();
                }
                else{
                    queue.addAll(ni.getList());
                }
            }
            depth++;
        }
        return sum;
    }










    // === Alternate Paradigm Summary
    /*
    - DFS recursion: natural, clean, easy to implement, uses call stack
    - BFS iteration: avoids recursion, uses explicit queue, good for very deep nesting
    - Both compute weighted sum correctly
    */

    // === Complexity Comparison Table
    /*
        Approach      | Time Complexity | Space Complexity | When to use
        ----------------------------------------------------------------
        DFS Recursion | O(n)            | O(d)            | Typical cases, moderate depth
        BFS Iterative | O(n)            | O(w)            | Avoid recursion stack, very deep nesting
    */

    // === Interview Talking Points
    /*
    - Use DFS recursion for simplicity; BFS for stack-safe iterative variant
    - Weighted sum handled by multiplying integer by depth
    - Key trade-off: recursion depth vs explicit queue memory
    - Follow-up: inverse weighting (bottom-up sum), sum without knowing depth in advance
    */

    // === Follow-up / Scaling / Discussion Points
    /*
    - Very deep nesting: BFS avoids stack overflow
    - Large input: iterative BFS scales better
    - Defensive checks: null lists, empty lists
    - Optimizations: flatten list first if repeated queries
    - Real-world mapping: JSON parsing, XML depth-weighted aggregation, hierarchical data scoring
    */

    // === Main Method with Tests
    public static void main(String[] args) {
        NestedListWeightSum solver = new NestedListWeightSum();

        // Test 1: [[1,1],2,[1,1]]
        List<NestedInteger> test1 = List.of(
            new NestedIntegerImpl(List.of(new NestedIntegerImpl(1), new NestedIntegerImpl(1))), // [1,1]
            new NestedIntegerImpl(2),                                                          // 2
            new NestedIntegerImpl(List.of(new NestedIntegerImpl(1), new NestedIntegerImpl(1))) // [1,1]
        );
        System.out.println(solver.depthSumDfs(test1)); // Expected 10 → 1*2 +1*2 + 2*1 +1*2 +1*2 ✅
        System.out.println(solver.depthSumBfs(test1));   // Expected 10 → BFS variant same calculation ✅
        System.out.println(solver.depthSumBfsAlt(test1));   // Expected 10 → BFS variant same calculation ✅

        // Test 2: [1,[4,[6]]]
        List<NestedInteger> test2 = List.of(
            new NestedIntegerImpl(1),  // 1*1
            new NestedIntegerImpl(List.of(
                new NestedIntegerImpl(4),                    // 4*2
                new NestedIntegerImpl(List.of(new NestedIntegerImpl(6))) // 6*3
            ))
        );
        System.out.println(solver.depthSumDfs(test2)); // Expected 27 → 1*1 +4*2 +6*3 ✅
        System.out.println(solver.depthSumBfs(test2));   // Expected 27 → BFS variant ✅
        System.out.println(solver.depthSumBfsAlt(test2));   // Expected 27 → BFS variant ✅

        // Test 3: Empty list
        List<NestedInteger> empty = new ArrayList<>();
        System.out.println(solver.depthSumDfs(empty)); // Expected 0 → no integers ✅
        System.out.println(solver.depthSumBfs(empty));   // Expected 0 → BFS variant ✅
        System.out.println(solver.depthSumBfsAlt(empty));   // Expected 27 → BFS variant ✅

        // Test 4: Single integer
        List<NestedInteger> single = List.of(new NestedIntegerImpl(5));
        System.out.println(solver.depthSumDfs(single)); // Expected 5 → 5*1 ✅
        System.out.println(solver.depthSumBfs(single));   // Expected 5 → BFS ✅
        System.out.println(solver.depthSumBfsAlt(single));   // Expected 27 → BFS variant ✅

        // Test 5: Deep nesting [[[[1]]]]
        List<NestedInteger> deep = List.of(
            new NestedIntegerImpl(List.of(
                new NestedIntegerImpl(List.of(
                    new NestedIntegerImpl(List.of(new NestedIntegerImpl(1))) // depth 4 → 1*4
                ))
            ))
        );
        System.out.println(solver.depthSumDfs(deep)); // Expected 4 ✅
        System.out.println(solver.depthSumBfs(deep));   // Expected 4 ✅
    }


    // === Comments for Recall / Discussion Table
    /*
        Approach | Key Idea | Trade-offs | Notes
        ------------------------------------------------------------
        DFS       | Recursion multiplies by depth | Easy, stack may overflow | Optimal O(n)
        BFS       | Level-order weighted sum | Iterative, queue memory | Stack-safe, O(n)
    */

    // === Normalization Note
    /*
    - Input lists should be non-null; treat null as empty
    - Standardize nested list representation for tests
    - Defensive coding to avoid NullPointerException
    */
}

// === Minimal NestedInteger implementation for local testing
class NestedIntegerImpl implements NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedIntegerImpl(int v) { val = v; list = null; }
    public NestedIntegerImpl(List<NestedInteger> l) { val = null; list = l; }

    public boolean isInteger() { return val != null; }
    public Integer getInteger() { return val; }
    public List<NestedInteger> getList() { return list; }
}

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}
