package ds_algorithm.dfs;
// ============================================================
// Problem Name: NestedListReverseDepthSum (Reverse Weighted Nested List Sum)
// ============================================================

/*
 * Problem Description:
 * Given a nested list of integers (NestedInteger), compute the sum of all integers weighted by their "reverse depth".
 * Reverse depth of an integer = maxDepth - currentDepth + 1.
 * 
 * Input: List<NestedInteger> nestedList
 * Output: int sum
 * 
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Depths: 1 for outer list, 2 for integers inside sublists
 * maxDepth = 2
 * ReverseDepthSum = 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 * 
 * Example 2:
 * Input: [[[5]]]
 * Depths: 1→list, 2→list, 3→5
 * maxDepth = 3
 * ReverseDepthSum = 5 * 1 = 5
 * 
 * Constraints:
 * - nestedList length: 0 ≤ n ≤ 1000
 * - Integer values: -10^6 ≤ val ≤ 10^6
 * - Nesting depth: 0 ≤ maxDepth ≤ 100
 * - Edge cases:
 *   - Empty input → return 0
 *   - Single integer → weight = maxDepth
 *   - Deeply nested single path → weight correctly applied
 *   - Null nested lists → treated as empty
 *   - All negative integers → sum correctly weighted
 *   - Repeated elements → each counted individually
 */

// ============================================================
// Brute Force Solution (2-pass recursion: find maxDepth, then compute sum)
// ============================================================

import java.util.*;

import ds_algorithm.Pair;

class NestedListReverseDepthSum {


    public int reverseDepthSumBrute(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int maxDepth = findMaxDepth(nestedList, 1); // first pass: find maximum depth
        return computeReverseSum(nestedList, 1, maxDepth); // second pass: compute weighted sum
    }

    private int findMaxDepth(List<NestedInteger> nestedList, int depth) {
        int max = depth;
        for (NestedInteger ni : nestedList) {
            if (!ni.isInteger()) {
                max = Math.max(max, findMaxDepth(ni.getList(), depth + 1));
            }
        }
        return max;
    }

    private int computeReverseSum(List<NestedInteger> nestedList, int depth, int maxDepth) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * (maxDepth - depth + 1);
            } else {
                sum += computeReverseSum(ni.getList(), depth + 1, maxDepth);
            }
        }
        return sum;
    }








    public int reverseDepthSumPerLevel(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) return 0;
        List<Integer> levelSums = new ArrayList<>();
        int maxDepth = dfs(nestedList, 1, levelSums);
        int sum = 0;
        for (int depth = 1; depth <= maxDepth; depth++) {
            int levelSum = levelSums.get(depth - 1); // list is 0-indexed
            sum += levelSum * (maxDepth - depth + 1); // reverse depth weight
        }
        return sum;
    }

    private int dfs(List<NestedInteger> nestedList, int depth, List<Integer> levelSums) {
        if (nestedList == null) return depth - 1;

        // Ensure levelSums can accommodate current depth
        while (levelSums.size() < depth) levelSums.add(0);

        int maxDepth = depth;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                levelSums.set(depth - 1, levelSums.get(depth - 1) + ni.getInteger());
            } else {
                maxDepth = Math.max(maxDepth, dfs(ni.getList(), depth + 1, levelSums));
            }
        }
        return maxDepth;
    }







    public int reverseDepthSumBFS(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) return 0;

        List<Integer> levelSums = new ArrayList<>();
        Queue<List<NestedInteger>> queue = new LinkedList<>();
        queue.offer(nestedList);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            var nextLevel = new ArrayList<NestedInteger>();

            for (int i = 0; i < size; i++) {
                var currentList = queue.poll();
                for (NestedInteger ni : currentList) {
                    if (ni.isInteger()) {
                        levelSum += ni.getInteger();
                    } else {
                        nextLevel.addAll(ni.getList());
                    }
                }
            }

            levelSums.add(levelSum);
            if (!nextLevel.isEmpty()) queue.offer(nextLevel);
        }

        // Compute reverse depth sum
        int maxDepth = levelSums.size();
        int sum = 0;
        for (int depth = 1; depth <= maxDepth; depth++) {
            int lvlSum = levelSums.get(depth - 1); // list is 0-indexed
            sum += lvlSum * (maxDepth - depth + 1);
        }
        return sum;
    }




// ============================================================
// Alternate Paradigm Summary
// ============================================================

/*
 * - Brute force (2-pass recursion) is simple but requires two traversals.
 * - Optimal one-pass collects integer values with depth and computes reverse depth sum post-traversal.
 * - BFS could be applied, but tracking reverse depth requires first finding maxDepth or storing level values.
 * - DFS (post-order) naturally supports 1-pass collection of depths for reverse-weighted sum.
 */

// ============================================================
// Comparison Tables
// ============================================================

/*
 * Brute-to-Optimal Comparison:
 * | Approach        | Time Complexity | Space Complexity | Pros                       | Cons                       |
 * |-----------------|----------------|-----------------|----------------------------|----------------------------|
 * | 2-pass Recursion| O(T)           | O(H)             | Simple, easy to understand| Two passes, extra traversal|
 * | 1-pass Recursion| O(T)           | O(T+H)           | Single traversal           | Extra storage for depth values|
 *
 * Complexity Comparison:
 * | Approach        | Interview O() | Absolute       |
 * |-----------------|---------------|----------------|
 * | 2-pass Recursion| O(T)          | 2T + H         |
 * | 1-pass Recursion| O(T)          | T + H          |
 */

// ============================================================
// Interview Talking Points
// ============================================================

/*
 * - Optimal one-pass avoids redundant recursion by collecting depth information while traversing.
 * - Brute force may be acceptable for small input but scales poorly for deep nesting.
 * - Key trade-off: one-pass requires storing all integers with depths (O(T) space) versus minimal stack-only memory in 2-pass.
 * - Follow-ups: BFS implementation, very deep nesting, lazy evaluation with generators.
 */

// ============================================================
// Main Method with Tests
// ============================================================

    public static void main(String[] args) {
        NestedListReverseDepthSum solver = new NestedListReverseDepthSum();

        // Test 1: Normal nested list [[1,1],2,[1,1]]
        List<NestedInteger> test1 = List.of(
                new NestedIntegerImpl(List.of(new NestedIntegerImpl(1), new NestedIntegerImpl(1))),
                new NestedIntegerImpl(2),
                new NestedIntegerImpl(List.of(new NestedIntegerImpl(1), new NestedIntegerImpl(1)))
        );
        System.out.println(solver.reverseDepthSumBrute(test1)); // Expected: 8 ✅
        System.out.println(solver.reverseDepthSumPerLevel(test1)); // Expected: 8 ✅
        System.out.println(solver.reverseDepthSumBFS(test1)); // Expected: 8 ✅

        // Test 2: Deep nested single path [[[5]]]
        List<NestedInteger> test2 = List.of(
                new NestedIntegerImpl(List.of(
                        new NestedIntegerImpl(List.of(
                                new NestedIntegerImpl(5)
                        ))
                ))
        );
        System.out.println(solver.reverseDepthSumBrute(test2)); // Expected: 5 ✅
        System.out.println(solver.reverseDepthSumPerLevel(test2)); // Expected: 5 ✅
        System.out.println(solver.reverseDepthSumBFS(test2)); // Expected: 5 ✅

        // Test 3: Empty list
        System.out.println(solver.reverseDepthSumBrute(List.of())); // Expected: 0 ✅
        System.out.println(solver.reverseDepthSumPerLevel(List.of())); // Expected: 0 ✅
        System.out.println(solver.reverseDepthSumBFS(List.of())); // Expected: 0 ✅
    }
}

// ============================================================
// Supporting NestedInteger Implementation for testing
// ============================================================

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

class NestedIntegerImpl implements NestedInteger {
    private Integer value = null;
    private List<NestedInteger> list = null;

    NestedIntegerImpl(int val) { value = val; }
    NestedIntegerImpl(List<NestedInteger> lst) { list = lst; }

    public boolean isInteger() { return value != null; }
    public Integer getInteger() { return value; }
    public List<NestedInteger> getList() { return list; }
}

// ============================================================
// Notes:
// - Defensive null checks included
// - Brute force 2-pass is easier to reason about
// - Optimal one-pass reduces recursion passes but uses O(T) extra storage
// - Edge cases handled: empty list, deeply nested single integer, multiple sublists
// - Trade-offs: stack memory vs depth value storage
// ============================================================
