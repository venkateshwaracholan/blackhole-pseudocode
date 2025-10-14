package prep_cache_docs;



/*
 * =============================================================================
 * Java Collections: Comprehensive Guide for FAANG / LeetCode
 * =============================================================================
 *
 * This document covers the most important Java Collections and data structures
 * relevant for competitive programming, FAANG interviews, and system design.
 *
 * For each collection type, it includes:
 * - Purpose / Typical Uses
 * - Time Complexity (Best / Average / Worst)
 * - Space Complexity
 * - Strengths
 * - Limitations
 * - Key Operations
 * - Example Usage
 * - Caveats & Common Pitfalls
 *
 * =============================================================================
 * 1. ArrayList<E>
 * =============================================================================
 *
 * Purpose:
 * - Resizable array implementation of List interface.
 * - Random access of elements is fast (O(1)).
 * - Good for lists with frequent reads and occasional inserts/deletes.
 *
 * Time Complexity:
 * - Access by index: O(1)
 * - Search (contains): O(n)
 * - Add (end): O(1) amortized
 * - Add (middle): O(n)
 * - Remove (by index): O(n)
 * - Remove (by value): O(n)
 *
 * Space Complexity:
 * - O(n), plus occasional O(n) resizing overhead.
 *
 * Strengths:
 * - Fast access by index.
 * - Resizable.
 *
 * Limitations:
 * - Insert/delete at arbitrary positions costly (O(n)).
 * - No fast removal from middle without shifting.
 *
 * Example:
 * List<Integer> list = new ArrayList<>();
 * list.add(10);
 * list.add(20);
 * int val = list.get(1); // O(1)
 *
 * =============================================================================
 * 2. LinkedList<E>
 * =============================================================================
 *
 * Purpose:
 * - Doubly-linked list implementation of List and Deque interfaces.
 * - Good for frequent insertions/deletions in middle or ends.
 *
 * Time Complexity:
 * - Access by index: O(n)
 * - Search (contains): O(n)
 * - Add (start or end): O(1)
 * - Add (middle): O(n) to find location
 * - Remove (start or end): O(1)
 * - Remove (middle): O(n)
 *
 * Space Complexity:
 * - O(n), plus extra memory for node pointers.
 *
 * Strengths:
 * - Fast insertion/removal at both ends.
 * - Implements Deque.
 *
 * Limitations:
 * - No O(1) random access.
 * - Higher memory usage due to node objects.
 *
 * Example:
 * LinkedList<String> list = new LinkedList<>();
 * list.addFirst("a"); // O(1)
 * list.addLast("b"); // O(1)
 * list.removeFirst(); // O(1)
 *
 * =============================================================================
 * 3. HashMap<K,V>
 * =============================================================================
 *
 * Purpose:
 * - Key-value store with O(1) average lookup.
 * - Good for fast access, insert, delete by key.
 *
 * Time Complexity:
 * - Put / Get / Remove: O(1) average, O(n) worst case (hash collisions)
 * - ContainsKey: O(1) average
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Very fast lookups.
 * - Flexible key and value types.
 *
 * Limitations:
 * - No order guarantee (except LinkedHashMap).
 * - Worst case O(n) when all keys collide (rare, mitigated by balanced trees internally).
 *
 * Example:
 * Map<String, Integer> map = new HashMap<>();
 * map.put("a", 1);
 * int val = map.get("a");
 *
 * Caveats:
 * - Key must have proper hashCode and equals implementations.
 * - Mutating key after insertion breaks lookup.
 *
 * =============================================================================
 * 4. LinkedHashMap<K,V>
 * =============================================================================
 *
 * Purpose:
 * - HashMap that maintains insertion order or access order.
 * - Useful for LRU cache implementations.
 *
 * Time Complexity:
 * - Put / Get / Remove: O(1) average
 * - Access order maintained automatically.
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Order preservation.
 * - Still fast O(1) lookup.
 *
 * Limitations:
 * - No direct access to predecessor or successor nodes.
 * - Order is maintained internally; not exposed for manipulation.
 *
 * Example:
 * LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true); // access order
 *
 * =============================================================================
 * 5. TreeMap<K,V>
 * =============================================================================
 *
 * Purpose:
 * - Key-value store with keys in sorted order (Red-Black tree).
 * - Useful for range queries, ordered maps.
 *
 * Time Complexity:
 * - Put / Get / Remove: O(log n)
 * - FirstKey / LastKey: O(log n)
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Ordered keys.
 * - Range queries efficient.
 *
 * Limitations:
 * - Slower than HashMap.
 * - No O(1) access.
 *
 * Example:
 * TreeMap<Integer, String> map = new TreeMap<>();
 * map.put(3, "a");
 * map.put(1, "b");
 * map.firstKey(); // 1
 *
 * =============================================================================
 * 6. HashSet<E>
 * =============================================================================
 *
 * Purpose:
 * - Stores unique elements.
 * - Backed by HashMap internally.
 *
 * Time Complexity:
 * - Add / Remove / Contains: O(1) average
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Fast lookup and uniqueness guarantee.
 *
 * Limitations:
 * - No order guarantee.
 *
 * Example:
 * Set<Integer> set = new HashSet<>();
 * set.add(5);
 * boolean exists = set.contains(5);
 *
 * =============================================================================
 * 7. LinkedHashSet<E>
 * =============================================================================
 *
 * Purpose:
 * - HashSet that preserves insertion order.
 *
 * Time Complexity:
 * - Add / Remove / Contains: O(1) average
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Maintains order while providing fast access.
 *
 * Limitations:
 * - Cannot access arbitrary neighbor elements in O(1).
 *
 * Example:
 * LinkedHashSet<String> set = new LinkedHashSet<>();
 * set.add("a");
 * set.add("b");
 * Iterator<String> it = set.iterator();
 * it.next(); // "a"
 *
 * =============================================================================
 * 8. PriorityQueue<E>
 * =============================================================================
 *
 * Purpose:
 * - Min-heap or max-heap (depending on comparator).
 * - Useful for order-based retrieval (min/max).
 *
 * Time Complexity:
 * - Add: O(log n)
 * - Poll: O(log n)
 * - Peek: O(1)
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Efficient min/max retrieval.
 *
 * Limitations:
 * - No arbitrary element access in O(1).
 * - Removing arbitrary element is O(n).
 *
 * Example:
 * PriorityQueue<Integer> pq = new PriorityQueue<>();
 * pq.add(5);
 * pq.poll(); // 5
 *
 * =============================================================================
 * 9. Deque<E> (ArrayDeque, LinkedList)
 * =============================================================================
 *
 * Purpose:
 * - Double-ended queue: add/remove at both ends in O(1).
 * - Used in sliding window problems, BFS.
 *
 * Time Complexity:
 * - AddFirst / AddLast / RemoveFirst / RemoveLast: O(1)
 * - Access by index: O(n)
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Fast end insert/remove.
 *
 * Limitations:
 * - No random access.
 *
 * Example:
 * Deque<Integer> dq = new ArrayDeque<>();
 * dq.addFirst(1);
 * dq.addLast(2);
 *
 * =============================================================================
 * 10. Stack<E>
 * =============================================================================
 *
 * Purpose:
 * - LIFO stack, usually implemented via Deque (preferred over Stack class).
 *
 * Time Complexity:
 * - Push / Pop / Peek: O(1)
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Simple LIFO.
 *
 * Limitations:
 * - Limited to stack access patterns.
 *
 * Example:
 * Deque<Integer> stack = new ArrayDeque<>();
 * stack.push(1);
 * stack.pop();
 *
 * =============================================================================
 * 11. Vector<E>
 * =============================================================================
 *
 * Purpose:
 * - Legacy resizable array (synchronized).
 * - Similar to ArrayList but thread-safe.
 *
 * Time Complexity:
 * - Same as ArrayList but synchronized (slower).
 *
 * Space Complexity:
 * - O(n)
 *
 * Strengths:
 * - Thread-safe.
 *
 * Limitations:
 * - Slower due to synchronization overhead.
 *
 * =============================================================================
 * General Caveats:
 * - Java collection implementations are not exposed for direct pointer manipulation.
 * - Random access and neighbor traversal limitations affect certain problem designs.
 * - Choosing the right collection is key for algorithmic efficiency.
 *
 * =============================================================================
 * Quick Reference Table:
 *
 * | Collection          | Access   | Insert   | Delete   | Order      | Space |
 * |---------------------|----------|----------|----------|------------|-------|
 * | ArrayList          | O(1)     | O(1) amort| O(n)    | Insertion  | O(n)  |
 * | LinkedList         | O(n)     | O(1)     | O(1)     | Insertion  | O(n)  |
 * | HashMap            | O(1)     | O(1)     | O(1)     | None       | O(n)  |
 * | LinkedHashMap      | O(1)     | O(1)     | O(1)     | Insertion  | O(n)  |
 * | TreeMap            | O(log n) | O(log n) | O(log n) | Sorted     | O(n)  |
 * | HashSet            | O(1)     | O(1)     | O(1)     | None       | O(n)  |
 * | LinkedHashSet      | O(1)     | O(1)     | O(1)     | Insertion  | O(n)  |
 * | PriorityQueue      | O(n)     | O(log n) | O(log n) | Heap Order | O(n)  |
 * | Deque (ArrayDeque) | O(n)     | O(1)     | O(1)     | Insertion  | O(n)  |
 * | Stack (Deque)      | O(1)     | O(1)     | O(1)     | LIFO       | O(n)  |
 *
 * =============================================================================
 */

 
public class JavaCollections {
    
}
