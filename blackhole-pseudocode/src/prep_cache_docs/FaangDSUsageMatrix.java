package prep_cache_docs;


/**
 * ============================================================================ 
 * 📚 FAANG Data Structure Cheat Sheet — Java Collections + Problem Mapping
 * ============================================================================ 
 *
 * This table summarizes key Java data structures, their FAANG use cases,
 * time/space complexities, and examples of problems where they excel.
 *
 * ============================================================================ 
 *
 * Problem Type                        | Recommended Data Structure(s)                     | Time Complexity         | Space Complexity | Why & Example Problems
 * ---------------------------------------------------------------------------------------------------------------
 * Sliding Window (Two Pointers Only) | Two pointers (left, right)                        | O(n)                    | O(1)              | Best when only window boundaries matter. Example: Longest Substring Without Repeating Characters.
 * ---------------------------------------------------------------------------------------------------------------
 * Sliding Window (Monotonic / Min/Max)| Deque (ArrayDeque, LinkedList)                   | O(n)                    | O(k)              | Efficient min/max tracking inside a window. Example: Sliding Window Maximum.
 * ---------------------------------------------------------------------------------------------------------------
 * Min/Max Tracking                    | PriorityQueue (Heap), TreeMap, Bucket List       | O(log n) / O(1) (Bucket List) | O(n) | PriorityQueue: dynamic min/max queries (Kth Largest Element). TreeMap: ordered queries (Merge Intervals). Bucket List: AllOne problem.
 * ---------------------------------------------------------------------------------------------------------------
 * LRU Cache                           | LinkedHashMap, HashMap + DLL                      | O(1)                    | O(n)              | Maintains access order. Example: LRU Cache (LeetCode).
 * ---------------------------------------------------------------------------------------------------------------
 * Counting Frequencies                | HashMap<String, Integer> + Bucket List / DLL     | O(1) (Bucket List)     | O(n)              | AllOne problem, frequency analysis. Example: Top K Frequent Elements.
 * ---------------------------------------------------------------------------------------------------------------
 * Unique Elements                     | HashSet, LinkedHashSet                            | O(1)                    | O(n)              | Track uniqueness or insertion order. Example: First Unique Character.
 * ---------------------------------------------------------------------------------------------------------------
 * Sorted Order / Range Queries       | TreeMap, TreeSet                                  | O(log n)                | O(n)              | Range queries, sorted iteration. Example: MyCalendar (Booking Systems).
 * ---------------------------------------------------------------------------------------------------------------
 * Graph Adjacency Lists               | HashMap<Node, List<Node>>, List<List<Integer>>   | O(V + E)                | O(V + E)          | Graph representation for traversal. Example: Number of Islands.
 * ---------------------------------------------------------------------------------------------------------------
 * Union-Find / Disjoint Sets         | Custom array-based Union-Find with path compression | O(α(n)) amortized   | O(n)              | Connectivity problems. Example: Redundant Connection.
 * ---------------------------------------------------------------------------------------------------------------
 * Stack Behavior (LIFO)               | Deque (ArrayDeque)                                | O(1)                    | O(n)              | Standard stack behavior. Example: Valid Parentheses.
 * ---------------------------------------------------------------------------------------------------------------
 * Queue Behavior (FIFO)               | LinkedList, ArrayDeque                            | O(1)                    | O(n)              | Standard queue behavior. Example: Sliding Window Problems.
 * ---------------------------------------------------------------------------------------------------------------
 * Concurrent Access                   | ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue | O(1) / O(n) depending on operation | O(n) | Thread-safe data structures. Example: Producer-Consumer.
 * ---------------------------------------------------------------------------------------------------------------
 * Fixed-Size Buffer                   | ArrayDeque, CircularBuffer                        | O(1)                    | O(k)              | Rolling windows, stream processing. Example: Moving Average from Data Stream.
 * ---------------------------------------------------------------------------------------------------------------
 * High Frequency Insert/Delete        | LinkedList, HashMap + Doubly Linked List, TreeMap| O(1) / O(log n)        | O(n)              | Quick insert/delete at ends; arbitrary removals in O(1). Example: AllOne Data Structure.
 *
 * ============================================================================ 
 * 🔍 Diagram — Java Collections Framework
 * ============================================================================ 
 *
 * Java Collections Framework
 * ├── List
 * │   ├── ArrayList       // random access, fast append (O(1) amortized)
 * │   └── LinkedList      // fast insert/delete at ends, slow random access (O(n))
 * │
 * ├── Set
 * │   ├── HashSet         // fast, unordered, O(1) add/remove/contains
 * │   ├── LinkedHashSet   // preserves insertion order
 * │   └── TreeSet         // sorted order, O(log n) access
 * │
 * ├── Queue / Deque
 * │   ├── LinkedList      // deque, queue (O(1) add/remove at ends)
 * │   ├── ArrayDeque      // faster deque, O(1) add/remove at ends
 * │   └── PriorityQueue   // min/max heap (O(log n) add/remove)
 * │
 * ├── Map
 * │   ├── HashMap         // fast lookup (O(1) average)
 * │   ├── LinkedHashMap   // preserves order
 * │   ├── TreeMap         // sorted keys (O(log n))
 * │   └── ConcurrentHashMap // thread-safe
 * │
 * └── Others
 *     ├── Stack           // legacy — use Deque
 *     ├── Vector          // thread-safe ArrayList
 *     └── CopyOnWriteArrayList / Set // thread-safe
 *
 * ============================================================================ 
 * 📌 Quick FAANG Problem → Data Structure Mapping
 * ============================================================================ 
 * - Sliding Window (Two Pointers Only)       → Two pointers (left, right)
 * - Sliding Window (Monotonic / Min/Max)     → ArrayDeque, LinkedList
 * - Min/Max Queries                           → PriorityQueue, TreeMap, Bucket List
 * - LRU Cache                                 → LinkedHashMap, HashMap + DLL
 * - Counting Frequencies                      → HashMap + Bucket List
 * - Unique Element Tracking                    → HashSet, LinkedHashSet
 * - Sorted Order / Range Queries              → TreeMap, TreeSet
 * - Graph Problems                             → HashMap + List, Union-Find
 * - Stack Behavior                             → ArrayDeque, LinkedList
 * - Queue Behavior                             → ArrayDeque, LinkedList
 *
 * ============================================================================ 
 * 📝 Tip: Always choose data structure based on:
 * - Access patterns (random vs sequential)
 * - Required ordering
 * - Performance trade-offs (time/space)
 * - Concurrency requirements
 *
 * ============================================================================ 
 */


public class FaangDSUsageMatrix {
    
}
