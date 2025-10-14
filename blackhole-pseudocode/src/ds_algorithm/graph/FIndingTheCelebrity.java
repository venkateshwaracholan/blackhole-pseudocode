/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

import ds_algorithm.Test;

/**
 *
 * @author vshanmugham
 */

// https://www.geeksforgeeks.org/the-celebrity-problem/
// https://leetcode.com/articles/find-the-celebrity/
// https://leetcode.com/problems/find-the-celebrity/

/*
ONE LINER:
Find the celebrity by eliminating candidates in O(n) passes (candidate selection + verification), falling back to O(n^2) brute force when necessary.

================================================================================
File: FindTheCelebrity.java
Purpose: FAANG-interview-quality, single-file Java solution & documentation
Problem: "Find the Celebrity" (LeetCode)
URL: https://leetcode.com/problems/find-the-celebrity/description/
================================================================================
*/
public class FIndingTheCelebrity {

    // === Problem Name
    // FindTheCelebrity (alias: CelebrityFinder)

    // === Problem Description (LeetCode-style)
    /*
     You are given n people labeled from 0 to n - 1. Among them, there may exist a
     celebrity. The definition of a celebrity is:
       - Everyone (except the celebrity themselves) knows the celebrity.
       - The celebrity knows nobody (no outgoing "knows" edges).

     You are given access to a helper API:
         boolean knows(int a, int b)
     which returns true if person a knows person b, and false otherwise.

     Return the label of the celebrity if one exists and is unique, otherwise return -1.

     Input format:
       - n: integer number of people (n >= 0)
       - Access to knows(a,b) API, or adjacency matrix in local tests.

     Output format:
       - integer index of the celebrity, or -1 if none exists.

     Constraints & realistic value ranges:
       - 0 <= n <= 1000 (common LeetCode constraint; scale to larger n in follow-ups)
       - knows calls are O(1) each.
       - Note: We must minimize knows calls for optimal solution.

     Edge cases to explicitly consider:
       - n == 0 (empty input) → return -1.
       - n == 1 → person 0 is trivially a celebrity (no others) → return 0.
       - Multiple potential celebrities (should not happen if definition strict) → return -1.
       - No celebrity → return -1.
       - knows API inconsistent or invalid (defensive checks in tests).
       - Repeated queries: caching possible if repeated.
       - Extremely large n: care about O(n) vs O(n^2) knows calls.

     Recap constraints (bullet form):
       - Minimize knows() calls.
       - Accepts n up to thousands in typical problems.
       - Return unique celebrity index or -1.
    */

    // === Example Walkthroughs
    /*
    Example 1:
    Input (n=3), matrix M:
        0  1  2
      0 [0, 1, 1]   // 0 knows 1 and 2
      1 [0, 0, 1]   // 1 knows 2
      2 [0, 0, 0]   // 2 knows no one → candidate
    Everyone (0,1) knows 2 → ✅ Celebrity = 2

    Example 2:
    Input (n=3), matrix M:
        0  1  2
      0 [0, 1, 0]   // 0 knows 1
      1 [0, 0, 1]   // 1 knows 2
      2 [1, 0, 0]   // 2 knows 0
    Cycle of knowing each other → ❌ No celebrity → -1

    Example 3:
    Input (n=4), matrix M:
        0  1  2  3
      0 [0, 1, 1, 1]   // 0 knows 1,2,3
      1 [0, 0, 1, 1]   // 1 knows 2,3
      2 [0, 0, 0, 0]   // 2 knows no one → candidate
      3 [0, 1, 1, 0]   // 3 knows 1,2
    Everyone knows 2 and 2 knows nobody → ✅ Celebrity = 2
    */


    // === Known helper / environment:
    // In LeetCode, knows(a,b) is provided. For local testing we use a matrix `M`.
    // We implement knows() to consult M when present.

    // -------------------------------
    // BRUTE FORCE SOLUTION (Naive)
    // -------------------------------
    /*
     Idea:
       Check every person i and verify two conditions:
         1. For every j != i, knows(i, j) must be false (i knows nobody).
         2. For every j != i, knows(j, i) must be true (everyone else knows i).
       If both conditions hold, i is the celebrity.
     Complexity:
       - Interview form time: O(n^2) (naive check over n candidates each requiring up to n checks).
       - Absolute form time: O(n * (n-1)) => O(n^2 - n) ~ O(n^2).
       - Interview form space: O(1)
       - Absolute form space: O(1)
     Limitations:
       - Uses up to n*(n-1) knows calls; too many when n large.
    */
    public static int findCelebrityBrute(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        for (var i = 0; i < n; i++) {
            boolean possible = true;
            // i should not know anyone else
            for (var j = 0; j < n; j++) {
                if (i == j) continue;
                if (knows(i, j)) { // i knows j -> cannot be celebrity
                    possible = false;
                    break;
                }
            }
            if (!possible) continue;
            // everyone else must know i
            for (var j = 0; j < n; j++) {
                if (i == j) continue;
                if (!knows(j, i)) { // someone doesn't know i -> not celebrity
                    possible = false;
                    break;
                }
            }
            if (possible) return i;
        }
        return -1;
    }

    // -------------------------------
    // OPTIMAL SOLUTION (Two-pass elimination)
    // -------------------------------
    /*
     Concept:
       1. Candidate Selection (one pass): Use two pointers (or linear scan) to eliminate non-celebrities.
          Start candidate = 0. For i from 1..n-1:
            if knows(candidate, i) => candidate cannot be celebrity => candidate = i
            else => i cannot be celebrity => keep candidate
          After this pass, candidate is the only possible celebrity.
       2. Verification (second pass): Verify candidate:
            - candidate knows nobody: for all j != candidate, knows(candidate, j) == false
            - everyone knows candidate: for all j != candidate, knows(j, candidate) == true
          If both hold => return candidate, else -1.
     Complexity:
       - Candidate selection: n-1 knows calls
       - Verification: up to 2*(n-1) knows calls
       - Total knows calls: up to 3n - 3
       - Interview time complexity: O(n)
       - Absolute time complexity: O(3n - 3) (we state as O(3n) for absolute-style)
       - Space: O(1)
     Why optimal:
       - Minimizes number of candidate checks to linear number of knows calls (constant factor 3).
       - Much better than naive O(n^2) knows calls for large n.
     */
    public static int findCelebrityOptimal(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;

        // Phase 1: candidate selection
        var candidate = 0;
        for (var i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                // candidate can't be celebrity
                candidate = i;
            }
        }

        // Phase 2: verification
        for (var j = 0; j < n; j++) {
            if (j == candidate) continue;
            // candidate should not know j, and j should know candidate
            if (knows(candidate, j) || !knows(j, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    // -------------------------------
    // ALTERNATE OPTIMAL VARIANTS
    // -------------------------------
    /*
     1) Stack-based elimination:
        - Push 0..n-1 onto stack.
        - While stack size > 1: pop a,b. If knows(a,b) => a can't be celebrity => push b else push a.
        - Final element candidate verify as above.
        - Complexity: same as two-pass (O(n) knows calls for elimination + 2n for verification).
        - Distinct insight: explicit pairwise elimination and clear proof by contradiction.

     2) In-degree / Out-degree counting (graph interpretation):
        - If we have adjacency (matrix) we can compute:
            indeg[i] = number of j where knows(j,i) == true
            outdeg[i] = number of j where knows(i,j) == true
          Celebrity => indeg[i] == n-1 && outdeg[i] == 0
        - Complexity: O(n^2) if we must read full matrix; but if we have streaming knows(a,b) API only, it's O(n^2) unless we optimize queries.
        - Distinct insight: counts convert relation into degrees for direct check. Useful if full adjacency matrix is available already.

     3) Randomized / Sampling heuristics:
        - Not recommended for exact answer; could be used to quickly find candidate with high probability but must be verified deterministically.
        - Distinct insight: trade speed for probabilistic guarantee (usually avoided in interviews).

     Each variant gives either same time bound (optimal elimination) or different trade-offs (if matrix available).
    */

    // Stack-based variant (explicit)
    public static int findCelebrityStack(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        for (var i = 0; i < n; i++) stack.push(i);
        while (stack.size() > 1) {
            var a = stack.pop();
            var b = stack.pop();
            if (knows(a, b)) {
                // a is not celebrity
                stack.push(b);
            } else {
                // b is not celebrity
                stack.push(a);
            }
        }
        var candidate = stack.pop();
        for (var j = 0; j < n; j++) {
            if (j == candidate) continue;
            if (knows(candidate, j) || !knows(j, candidate)) return -1;
        }
        return candidate;
    }

    // In-degree / Out-degree variant (requires full matrix or iterating all pairs)
    public static int findCelebrityDegrees(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        var indeg = new int[n];
        var outdeg = new int[n];
        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n; j++) {
                if (i == j) continue;
                if (knows(i, j)) {
                    outdeg[i]++;
                    indeg[j]++;
                }
            }
        }
        for (var i = 0; i < n; i++) {
            if (indeg[i] == n - 1 && outdeg[i] == 0) return i;
        }
        return -1;
    }

    // -------------------------------
    // ALTERNATE PARADIGM SUMMARY
    // -------------------------------
    /*
     - Two-pass elimination: Best for API-based problem; minimal knows() calls (linear).
     - Stack elimination: Equivalent to two-pass but conceptually uses pairwise elimination.
     - Degree counting: Useful if adjacency matrix already available; otherwise O(n^2).
     - Randomized sampling: Not deterministic; avoid in interviews unless explicitly asked.
    */

    // -------------------------------
    // BRUTE vs OPTIMAL & COMPLEXITY TABLE (Human-friendly bullets)
    // -------------------------------
    /*
     Brute Force:
       - Time (interview): O(n^2)
       - Time (absolute): O(n*(n-1)) -> O(n^2 - n)
       - Space: O(1)
       - When to use: tiny n, or when clarity over optimization matters

     Two-pass Optimal:
       - Time (interview): O(n)
       - Time (absolute): O(3n - 3) knows calls (candidate selection ~ n-1, verification ~ 2*(n-1))
       - Space: O(1)
       - When to use: typical LeetCode 'knows' API, large n

     Stack-based:
       - Time (interview): O(n)
       - Time (absolute): O(3n - 3)
       - Space: O(n) for stack (extra memory)
       - When to use: conceptual clarity for pairwise elimination or when asked to use explicit stack

     Degree-counting:
       - Time (interview): O(n^2) if must query knows for all pairs
       - Space: O(n)
       - When to use: if adjacency matrix precomputed or available
    */

    // -------------------------------
    // INTERVIEW TALKING POINTS
    // -------------------------------
    /*
     - Pitch (1-2 sentences):
       Use linear elimination to find a single candidate in one pass: whenever candidate knows i, candidate can't be celebrity, set candidate = i. Then verify candidate in O(n). This yields O(n) time and O(1) space.

     - Why optimal over brute:
       Brute checks all n candidates with n checks each (O(n^2)). Elimination proves many are impossible in pairwise fashion and reduces checks to linear number.

     - Key trade-offs:
       - Two-pass uses up to 3n knows calls (constant factor). Stack variant trades O(1) space for O(n) stack memory.
       - Degree-counting trades time for clarity if matrix exists.

     - Expected follow-ups:
       Q: "What if knows() is expensive?" => A: minimize calls; two-pass already optimal in order sense for API-based approach.
       Q: "Can there be more than one celebrity?" => A: No by definition: celebrity must have indeg = n-1; only one person can satisfy.
    */

    // -------------------------------
    // FOLLOW-UP / SCALING / DISCUSSION
    // -------------------------------
    /*
     - Scaling to large N/Q:
         * Two-pass scales linearly in n; for repeated queries, caching or precomputation (degree arrays) can help.
         * If many queries on same graph, precompute indeg/outdeg once (O(n^2) upfront), then answer queries O(1).

     - Precomputation vs query-time:
         * Precompute adjacency indeg/outdeg if repeated queries; otherwise two-pass per query best.

     - Memory vs speed:
         * In-degree method uses O(n) memory to get O(1) answer after precompute; two-pass uses O(1) memory per query.

     - Real-world data handling:
         * Normalization not relevant here.
         * Defensive checks: ensure knows returns consistent boolean; handle null input or inconsistent API by returning -1 or throwing.
         * Repeated queries: cache results of knows(a,b) if API expensive; implement an LRU or full matrix cache if enough memory.

     - Optimizations:
         * Memoization of knows results if repeated checks occur.
         * Short-circuit verification: if any check fails, return -1 immediately (we already do).
         * Parallel verification: if environment supports parallel knows checks, verification can be parallelized to O(n / p) time.

     - Constraints recap:
         1. Minimize knows calls.
         2. Expect n up to thousands.
         3. Prefer O(n) algorithm for single-shot queries.

     - Common interviewer probes:
         Q: "Prove correctness of candidate elimination?" -> A: Show invariant: candidate is always a person who could still be celebrity; elimination only removes impossible candidates.
         Q: "Why final verification required?" -> A: Candidate selection ensures only a possible candidate remains, but it doesn't guarantee the candidate satisfies both properties.

     - Real-world applicability:
         * Useful in systems that need to find a universal sink in directed graphs, social network 'celebrity' detection definitions (strict variant), or leader-election variants when trust is directional.
    */

    // -------------------------------
    // MAIN METHOD WITH TESTS
    // -------------------------------

    // We'll provide a simple controllable knows() via a static matrix for testing.

    // Static matrix representing "knows" relation for tests.
    // M[a][b] == true means a knows b.
    private static boolean[][] M;

    // The knows API used by the algorithms above. In production (LeetCode) this is provided externally.
    private static boolean knows(int a, int b) {
        if (M == null) throw new IllegalStateException("knows() matrix not initialized for local testing.");
        if (a < 0 || a >= M.length || b < 0 || b >= M.length)
            throw new IllegalArgumentException("Index out of range in knows()");
        return M[a][b];
    }

    // Helper to set the test matrix (defensive copy)
    private static void setMatrix(boolean[][] matrix) {
        if (matrix == null) {
            M = null;
            return;
        }
        var n = matrix.length;
        M = new boolean[n][n];
        for (var i = 0; i < n; i++) {
            if (matrix[i].length != n)
                throw new IllegalArgumentException("Matrix must be square (n x n). Row " + i + " length mismatch.");
            System.arraycopy(matrix[i], 0, M[i], 0, n);
        }
    }

    // Test harness
    public static void main(String[] args) {
        // Test 1: Simple celebrity at 2
        // People: 0,1,2
        // 0 knows 2 (true), 0 knows 1? false, 1 knows 2 true, 2 knows nobody.
        var test1 = new boolean[][]{
            {false, false, true},
            {false, false, true},
            {false, false, false}
        };
        setMatrix(test1);
        var n1 = test1.length;
        System.out.println("Test1 Brute: expected 2 -> got: " + findCelebrityBrute(n1)); // expected 2
        System.out.println("Test1 Optimal: expected 2 -> got: " + findCelebrityOptimal(n1)); // expected 2
        System.out.println("Test1 Stack: expected 2 -> got: " + findCelebrityStack(n1)); // expected 2
        System.out.println("Test1 Degrees: expected 2 -> got: " + findCelebrityDegrees(n1)); // expected 2
        System.out.println("----");

        // Test 2: No celebrity
        var test2 = new boolean[][]{
            {false, true, false},
            {false, false, true},
            {true, false, false}
        };
        setMatrix(test2);
        var n2 = test2.length;
        System.out.println("Test2 Brute: expected -1 -> got: " + findCelebrityBrute(n2)); // expected -1
        System.out.println("Test2 Optimal: expected -1 -> got: " + findCelebrityOptimal(n2)); // expected -1
        System.out.println("Test2 Stack: expected -1 -> got: " + findCelebrityStack(n2)); // expected -1
        System.out.println("Test2 Degrees: expected -1 -> got: " + findCelebrityDegrees(n2)); // expected -1
        System.out.println("----");

        // Test 3: Single person n=1
        var test3 = new boolean[][]{{false}};
        setMatrix(test3);
        var n3 = test3.length;
        System.out.println("Test3 Brute: expected 0 -> got: " + findCelebrityBrute(n3)); // expected 0
        System.out.println("Test3 Optimal: expected 0 -> got: " + findCelebrityOptimal(n3)); // expected 0
        System.out.println("Test3 Stack: expected 0 -> got: " + findCelebrityStack(n3)); // expected 0
        System.out.println("Test3 Degrees: expected 0 -> got: " + findCelebrityDegrees(n3)); // expected 0
        System.out.println("----");

        // Test 4: Empty n=0
        setMatrix(new boolean[0][0]);
        var n4 = 0;
        System.out.println("Test4 Brute: expected -1 -> got: " + findCelebrityBrute(n4)); // expected -1
        System.out.println("Test4 Optimal: expected -1 -> got: " + findCelebrityOptimal(n4)); // expected -1
        System.out.println("----");

        // Test 5: Large-ish sanity test (celebrity at last index)
        var n5 = 6;
        var test5 = new boolean[n5][n5];
        for (var i = 0; i < n5; i++) {
            for (var j = 0; j < n5; j++) {
                test5[i][j] = false;
            }
        }
        // make everyone know 5, and 5 knows no one
        for (var i = 0; i < n5 - 1; i++) test5[i][n5 - 1] = true;
        setMatrix(test5);
        System.out.println("Test5 Optimal: expected 5 -> got: " + findCelebrityOptimal(n5)); // expected 5
        System.out.println("----");

        // Test 6: Inconsistent or invalid knows: demonstrates defensive behavior (will throw)
        try {
            setMatrix(null);
            findCelebrityOptimal(3); // should throw when knows called
            System.out.println("Test6: ❌ failed (expected exception)");
        } catch (Exception ex) {
            System.out.println("Test6: ✅ passed (caught expected exception): " + ex.getMessage());
        }
    }

    // -------------------------------
    // COMMENTS FOR RECALL / DISCUSSION TABLE
    // -------------------------------
    /*
     Key ideas / 1-liners:
       - Two-pass elimination: "Eliminate impossible celebrities pairwise → verify the last candidate."
       - Stack: "Pairwise elimination using explicit stack; pop two and push the possible one."
       - Degree: "Compute indegree/outdegree when matrix exists; celebrity has indeg n-1 and outdeg 0."
       - Brute: "Test each person exhaustively."

     When to use:
       - Use two-pass for standard problem with knows API.
       - Use degree method if adjacency already available or many queries will be made.
       - Use brute only for tiny n or as clarity baseline.

     Trade-offs:
       - Two-pass: O(1) space, O(n) time (optimal for API).
       - Stack: O(n) extra space for same time.
       - Degree: O(n^2) time if compute from scratch; O(n) query after O(n^2) precomputation.

     Optimization ideas:
       - Cache knows(a,b) results to avoid repeated calls if knows is expensive.
       - Parallelize verification if environment supports concurrency.
       - If queries are repeated often, precompute degrees or adjacency.

    */

    // -------------------------------
    // NORMALIZATION NOTE
    // -------------------------------
    /*
     Not directly applicable—graph problem. If input data were strings or names:
       - Trim, lowercase, Unicode normalize, remove punctuation as needed.
    */

    // -------------------------------
    // ADVANCED EXPECTATIONS (FAANG completeness)
    // -------------------------------
    /*
     - Defensive checks added for local testing environment.
     - Provided several algorithmic variants and justification.
     - Complexity annotated in both interview and absolute forms.
     - Provided main() tests for normal, edge, corner cases with expected outputs in comments.
     - Discussed scaling, caching, and trade-offs.
    */
}
