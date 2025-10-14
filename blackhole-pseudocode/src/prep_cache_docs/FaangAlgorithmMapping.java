package prep_cache_docs;

/**
 * ============================================================================ 
 * 📚 Ultimate 100% FAANG DSA & Java Collections Cheat Sheet
 * ============================================================================ 
 *
 * This document consolidates all key FAANG problem types, synonyms, recommended 
 * data structures, practical usage, performance trade-offs, and common patterns.
 * Covers every major DSA pattern, advanced structures, and Java Collections usage.
 *
 * ============================================================================ 
 *
 * Problem Type / Synonyms                         | Recommended DS / Approach                           | Why & Notes
 * ---------------------------------------------------------------------------------------------------------------
 * Sliding Window (Two Pointers)                   | Two pointers (left, right)                          | Best when only window boundaries matter (sum, count, substring). O(n) time, O(1) space.
 * Sliding Window / Fixed Window                   | ArrayDeque, LinkedList (Deque)                      | Supports O(1) insert/remove at both ends; ideal for monotonic queues for min/max queries.
 * Monotonic Queue / Sliding Window Max/Min       | ArrayDeque, LinkedList                               | Maintains monotonic order to query max/min in O(1) per operation.
 * Min/Max Tracking / Top-K / Leaderboard         | PriorityQueue (Heap), TreeMap, Bucket List         | Heap for dynamic min/max queries, TreeMap for sorted order + O(log n), Bucket List for O(1) min/max updates (AllOne problem).
 * Heap Variants (Indexed / Custom / Min/Max Heap)| PriorityQueue + HashMap or custom heap class      | Supports Top-K with deletion of arbitrary elements efficiently.
 * LRU Cache / MRU / Access-Order Cache           | LinkedHashMap, HashMap + DLL                        | Maintains access order, supports O(1) get/put, ideal for LRU problems.
 * Counting Frequencies / Frequency Map / AllOne | HashMap<String,Integer> + Bucket List / DLL        | Efficient frequency counting, max/min frequency queries.
 * Unique Elements / Deduplication                | HashSet, LinkedHashSet                               | O(1) lookup/insert; preserves insertion order if needed.
 * Sorted Order / Range Queries / Kth Order       | TreeMap, TreeSet, Segment Tree, Fenwick Tree       | Sorted order, O(log n) access, range queries, range sum/min/max queries.
 * Graph Problems / Adjacency List / Weighted Graph | HashMap<Node,List<Node>>, List<List<Integer>>   | Flexible sparse representation; include edge weights for shortest path or MST problems.
 * Graph Traversals / BFS / DFS / Topological / SCC | Queue, Stack, PriorityQueue, adjacency list, Union-Find | BFS for shortest path, DFS for connectivity/topological order, Union-Find for connected components.
 * Union-Find / DSU / Connectivity / Cycle Detection | Array-based Union-Find + Path Compression + Union by Rank | Optimal for connectivity checks, cycle detection. Amortized O(α(n)) per operation.
 * Dynamic Programming (DP) / Memoization         | Array, Matrix, HashMap                               | Store subproblem results, optimize recursion via memoization/tabulation.
 * Backtracking / DFS Recursion / Combinations    | Array, List, Stack (call stack), HashSet           | Solve N-Queens, Word Search, Combination Sum, Sudoku.
 * Trie / Prefix Tree / Word Dictionary           | Custom Trie Node class                               | Efficient prefix search, autocomplete, word break, Nokia keypad mapping.
 * Stack Behavior / LIFO                           | Deque (ArrayDeque)                                  | O(1) push/pop, safer and faster than legacy Stack class.
 * Queue Behavior / FIFO                           | LinkedList, ArrayDeque                               | O(1) add/remove from ends; ArrayDeque faster unless concurrency needed.
 * Fixed-Size Buffer / Circular Queue             | ArrayDeque, CircularBuffer                           | Sliding windows, streaming data, rolling buffer scenarios.
 * Bit Manipulation / Bitmask                      | int, long, BitSet                                   | Subset generation, XOR tricks, low-level operations.
 * Mathematical Problems / Modular Arithmetic    | int/long, BigInteger                                 | GCD/LCM, modular exponentiation, combinatorics.
 * High Frequency Insert/Delete                     | LinkedList, HashMap + DLL, TreeMap                  | LinkedList for ends, HashMap+DLL for arbitrary removals in O(1), TreeMap for order.
 * Streams / Iterator / Functional Processing     | Java Stream API, Iterator                            | Filter/map/reduce, aggregation, sequential processing.
 * Concurrency / Multi-threaded Access           | ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue | Thread-safe collections for shared state.
 * Segment Tree / Range Queries                   | Segment Tree, Lazy Segment Tree                      | Range sum/min/max queries, range updates, O(log n) per operation.
 * Fenwick Tree / Binary Indexed Tree (BIT)      | BIT array                                           | Efficient prefix sums / updates, O(log n) per operation.
 * Weighted Graph Algorithms (Dijkstra/Prim/Kruskal) | PriorityQueue, adjacency list, Union-Find          | Efficient shortest path / MST algorithms.
 * Hashing / Rolling Hash / Rabin-Karp           | HashMap, long/int rolling hash                       | String matching, substring uniqueness, pattern search.
 *
 * ============================================================================ 
 * 🔍 Diagram — Java Collections & Relationships
 * ============================================================================ 
 *
 * Java Collections Framework
 * ├── List
 * │   ├── ArrayList          // random access, fast append O(1), O(n) remove middle
 * │   └── LinkedList         // fast insert/delete at ends O(1), slow random access O(n)
 * │
 * ├── Set
 * │   ├── HashSet            // fast lookup/insert O(1) avg, unordered
 * │   ├── LinkedHashSet      // preserves insertion order, O(1) avg
 * │   └── TreeSet            // sorted order, O(log n) access
 * │
 * ├── Queue / Deque
 * │   ├── LinkedList         // deque, queue, O(1) ends, O(n) arbitrary
 * │   ├── ArrayDeque         // deque, faster than LinkedList, O(1) ends
 * │   └── PriorityQueue      // min/max heap, O(log n) insert/remove
 * │
 * ├── Map
 * │   ├── HashMap            // key-value O(1) avg, unordered
 * │   ├── LinkedHashMap      // preserves insertion order
 * │   ├── TreeMap            // sorted keys, O(log n) access
 * │   └── ConcurrentHashMap  // thread-safe key-value
 * │
 * └── Others
 *     ├── Stack              // legacy — prefer Deque
 *     ├── Vector             // thread-safe ArrayList
 *     └── CopyOnWriteArrayList / Set // thread-safe
 *
 * ============================================================================ 
 * 📌 Quick FAANG Problem → DS / Algorithm Mapping
 * ============================================================================ 
 * Sliding Window / Two Pointers                   | Two pointers (left/right)                          | Best when only window boundaries matter (sum, count, substring). O(n) time, O(1) space. Maintains a valid window efficiently without extra data structures.
 * Sliding Window / Min/Max                        | ArrayDeque, LinkedList, Monotonic Queue           | Useful for querying min/max in a moving window. Deque ensures O(1) per insertion/removal. Handles streaming or sliding window problems efficiently.
 * Min/Max Queries / Leaderboard                   | Heap (PriorityQueue), TreeMap, Bucket List        | Heap supports dynamic top-K queries, TreeMap maintains sorted order, Bucket List enables O(1) min/max frequency updates (like AllOne). Trade-offs: Heap O(log n), TreeMap O(log n), Bucket O(1) per op.
 * LRU Cache / MRU / Access-Order Cache            | LinkedHashMap, HashMap + DLL                      | Maintains insertion/access order for eviction policies. LinkedHashMap gives built-in order; HashMap+DLL supports O(1) arbitrary removal and insertion.
 * Counting Frequencies / AllOne                   | HashMap + Bucket List / DLL                        | Efficiently tracks frequency counts and allows querying max/min frequency quickly. Ideal for problems like LFU, AllOne Data Structure.
 * Unique Elements / Deduplication                 | HashSet, LinkedHashSet                             | Ensures elements are unique, O(1) average insert/lookup. LinkedHashSet preserves insertion order when required.
 * Sorted Order / Range Queries                     | TreeMap, TreeSet, Segment Tree, BIT               | Maintains sorted order or supports range queries efficiently. Segment Trees/BIT handle dynamic range sum/min/max updates. TreeMap/TreeSet for ordered keys with O(log n) operations.
 * Graph Problems (BFS/DFS/Topological/Shortest Path/SCC) | Queue/Stack + adjacency list + Union-Find    | BFS: shortest paths, DFS: connectivity/topological sort, Union-Find: connected components & cycle detection. Flexible for directed/undirected, weighted/unweighted graphs.
 * Weighted Graphs / Shortest Path / MST          | PriorityQueue + adjacency list + Union-Find       | Dijkstra/Prim/Kruskal algorithms for weighted graphs. PriorityQueue ensures optimal selection; adjacency list stores edges efficiently; Union-Find detects cycles for MST.
 * Union-Find / DSU / Connectivity                 | Array-based Union-Find + Path Compression + Union by Rank | Efficient connectivity and cycle checks. Amortized O(α(n)) per operation; supports merge/find operations on sets.
 * Dynamic Programming / Memoization               | Array, Matrix, HashMap                             | Store subproblem results to avoid recomputation. Arrays/matrices for indexed subproblems; HashMap for complex state representation.
 * Backtracking / Recursion / DFS                  | Array, List, Stack                                 | Solve combination, permutation, or constraint problems. Stack tracks recursion, Array/List stores state. Typical for N-Queens, Sudoku, Word Search.
 * Trie / Prefix Tree / Word Dictionary            | Custom Trie Node class                              | Supports prefix search, autocomplete, word break problems. Efficiently stores strings with common prefixes.
 * Bit Manipulation / Bitmask                       | int, long, BitSet                                   | Handles subset generation, XOR tricks, and low-level operations. O(1) per bit manipulation, memory efficient for small sets.
 * Math / Modular Arithmetic                        | int/long, BigInteger                                | Solves GCD/LCM, modular exponentiation, combinatorics. BigInteger handles overflow for large numbers.
 * Stack Behavior / LIFO                            | Deque (ArrayDeque)                                  | O(1) push/pop, safer and faster than legacy Stack class. Ideal for classic LIFO operations.
 * Queue Behavior / FIFO                            | LinkedList, ArrayDeque                               | O(1) add/remove from ends. ArrayDeque faster than LinkedList unless thread-safe features needed.
 * Fixed-Size / Sliding Window Buffer              | ArrayDeque, CircularBuffer                           | Maintains a rolling window or buffer efficiently. Suitable for streaming data, moving averages.
 * High Frequency Insert/Delete                     | LinkedList, HashMap + DLL, TreeMap                  | Supports fast inserts/deletes at ends or arbitrary positions. HashMap+DLL allows O(1) arbitrary deletion; TreeMap maintains order.
 * Streams / Iterator Processing                    | Stream API, Iterator                                 | Enables functional-style operations like filter, map, reduce. Efficient sequential processing and aggregation.
 * Concurrency / Multi-threaded Access             | ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue | Thread-safe collections for shared state in multi-threaded applications. Trade-offs: synchronization cost vs read-heavy optimization.
 *
 * ============================================================================ 
 * 📌 Notes:
 * - All structures highlight trade-offs, average/worst-case complexity, and patterns.
 * - Includes synonyms to quickly recognize problem types.
 * - Covers all major FAANG problem types including advanced structures (Segment Trees, BITs, Weighted Graphs, Indexed Heaps, Union by Rank).
 * - Can be used as a single reference for interviews and LeetCode prep.
 *
 * ============================================================================ 
 */


public class FaangAlgorithmMapping {
    
}
