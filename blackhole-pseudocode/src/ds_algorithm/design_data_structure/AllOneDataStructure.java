
package ds_algorithm.design_data_structure;

// === Problem Name ===
// AllOneDataStructure (AllOne)

// === Problem Description ===
/*
 * Design a data structure to store string counts with the ability to return the string(s) with 
 * minimum and maximum counts, all in O(1) time complexity for each operation.
 *
 * Implement the AllOne class:
 * 
 * AllOne():
 * - Initializes the data structure.
 *
 * inc(String key):
 * - Increment the count of `key` by 1.
 * - If `key` does not exist, insert it with count = 1.
 *
 * dec(String key):
 * - Decrement the count of `key` by 1.
 * - If count becomes 0, remove `key` from the structure.
 * - It is guaranteed that key exists before decrement.
 *
 * getMaxKey():
 * - Return one of the keys with the maximal count.
 * - If no key exists, return "".
 *
 * getMinKey():
 * - Return one of the keys with the minimum count.
 * - If no key exists, return "".
 *
 * Input format:
 * - Operations as method calls (inc, dec, getMaxKey, getMinKey).
 *
 * Output format:
 * - Return values from getMaxKey() and getMinKey() as strings.
 *
 * Constraints:
 * - 1 <= key.length <= 10
 * - key consists of lowercase English letters.
 * - At most 5 * 10^4 calls will be made.
 *
 * Edge cases:
 * - Empty data structure
 * - Single element
 * - Multiple elements with same min or max count
 * - All keys having same count
 * - Removing keys to empty state
 * - Large sequence of operations (scale testing)
 *
 * Constraints recap:
 * - All operations must run in O(1) average time.
 * - Handle duplicates, empty state, and remove keys with zero count.
 * 
 * Example 1:
    Input:
    ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey", "dec", "getMaxKey", "getMinKey"]
    [[], ["hello"], ["hello"], [], [], ["leet"], [], [], ["hello"], [], []]

    Output:
    [null, null, null, "hello", "hello", null, "hello", "leet", null, "hello", "leet"]

    Explanation:
    AllOne allOne = new AllOne();
    allOne.inc("hello");         // "hello": count = 1
    allOne.inc("hello");         // "hello": count = 2
    allOne.getMaxKey();          // "hello" — only key with highest count (2)
    allOne.getMinKey();          // "hello" — only key with lowest count (2)
    allOne.inc("leet");          // "leet": count = 1
    allOne.getMaxKey();          // "hello" — count = 2
    allOne.getMinKey();          // "leet" — count = 1
    allOne.dec("hello");         // "hello": count = 1
    allOne.getMaxKey();          // "hello" — count = 1 (tie with "leet")
    allOne.getMinKey();          // "hello" or "leet" — either is valid
 */


import java.util.*;


/*
 * AllOneBrute (HashMap + BruteForce Max/Min) 
 * ONE LINER: Map<String, Integer> countMap;: (store key counts); public AllOneBrute() { countMap = new HashMap<String,Integer>(); }: (initialize empty count map); public void inc(String key) { countMap.compute(key, (k,v) -> v==null ? 1 : v+1); }: (increment key count or initialize to 1 if absent); public void dec(String key) { if(!countMap.containsKey(key)) { return; }: (do nothing if key absent); int count = countMap.get(key);: (get current count); if(count==1) { countMap.remove(key); }: (remove key if count becomes zero); else { countMap.put(key, count-1); }: (decrement count if greater than 1); }: (end dec method); public String getMaxKey() { if(countMap.isEmpty()) { return ""; }: (return empty string if no keys); return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey(); }: (return key with maximum count using brute-force scan); public String getMinKey() { if(countMap.isEmpty()) { return ""; }: (return empty string if no keys); return Collections.min(countMap.entrySet(), Map.Entry.comparingByValue()).getKey(); }: (return key with minimum count using brute-force scan) → O(n) for getMaxKey/getMinKey, O(1) for inc/dec, O(n) space (absolute: n for map storage).
 *
 * Code(Reason):
 * - Map<String, Integer> countMap;: store counts of each key in a hash map for constant-time insert/update.
 * - public AllOneBrute() { countMap = new HashMap<String,Integer>(); }: initialize the hash map to empty.
 * - public void inc(String key) {
 *     - countMap.compute(key, (k,v) -> v==null ? 1 : v+1);: increment key count or initialize to 1 if absent.
 * - public void dec(String key) {
 *     - if(!countMap.containsKey(key)) { return; }: do nothing if key does not exist.
 *     - int count = countMap.get(key);: retrieve current count of the key.
 *     - if(count==1) { countMap.remove(key); }: remove key if count becomes 0.
 *     - else { countMap.put(key, count-1); }: decrement count if greater than 1.
 * - public String getMaxKey() {
 *     - if(countMap.isEmpty()) { return ""; }: return empty string if no keys exist.
 *     - return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();: find key with max count using brute-force scan.
 * - public String getMinKey() {
 *     - if(countMap.isEmpty()) { return ""; }: return empty string if no keys exist.
 *     - return Collections.min(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();: find key with min count using brute-force scan.
 *
 * Rationale: This brute-force approach maintains key counts in a HashMap for O(1) increment and decrement. Max and min key retrievals scan all entries to find the key with the highest or lowest count, making them O(n). It is simple but inefficient for frequent max/min queries.
 *
 * Time Complexity: 
 * - inc/dec: O(1) — direct hash map updates.
 * - getMaxKey/getMinKey: O(n) — scan all n entries in map.
 * Space Complexity: O(n) — store counts for all n keys in the hash map (absolute: n).
 *
 * Examples:
 * Example 1:
 * Input: ["AllOneBrute","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey"]
 * Trace: AllOneBrute(): initialize empty map; inc("a"): map {"a":1}; inc("b"): map {"a":1, "b":1}; getMaxKey(): scan entries → max=1 → "a" or "b"; getMinKey(): scan entries → min=1 → "a" or "b"; inc("a"): map {"a":2, "b":1}; getMaxKey(): scan entries → max=2 → "a"; getMinKey(): scan entries → min=1 → "b"
 * Output: ["null","null","null","a","b","null","a","b"]
 *
 * Example 2:
 * Input: ["AllOneBrute","inc","dec","getMaxKey","getMinKey"]
 * Trace: AllOneBrute(): initialize; inc("a"): map {"a":1}; dec("a"): remove key → map {}; getMaxKey(): empty → ""; getMinKey(): empty → ""
 * Output: ["null","null","null","",""]
 */


class AllOneBrute {
    Map<String, Integer> countMap; 
    public AllOneBrute() {
        countMap = new HashMap<String,Integer>();
    }
    
    public void inc(String key) {
        countMap.compute(key, (k,v)-> v==null ? 1: v+1);
    }
    
    public void dec(String key) {
        if(!countMap.containsKey(key)){
            return;
        }
        int count = countMap.get(key);
        if(count==1){
            countMap.remove(key);
        }
        else{
            countMap.put(key, count-1);
        }
    }
    
    public String getMaxKey() {
        if(countMap.isEmpty()){
            return "";
        }
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    public String getMinKey() {
        if(countMap.isEmpty()){
            return "";
        }
        return Collections.min(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        
    }
}




// === Elevator Floors Analogy Example ===
/*
 * Imagine a building where each floor represents a point count, and people stand on the floor corresponding to their points.
 *
 * Example:
 * Floor 1: {"Alice", "Bob"}       // points = 1
 * Floor 2: {"Charlie"}            // points = 2
 * Floor 3: {"Dave", "Eve"}        // points = 3
 *
 * You also maintain a map: person → current floor.
 *
 * Example of map:
 * {
 *   "Alice" : 1,
 *   "Bob" : 1,
 *   "Charlie" : 2,
 *   "Dave" : 3,
 *   "Eve" : 3
 * }
 *
 * When a person gains a point (inc):
 * - Move the person up one floor (increment count in map).
 * - If the next floor doesn’t exist, create it.
 * - Remove the person from the current floor.
 *
 * Example: Charlie gains a point
 * Before:
 *   Floor 2: {"Charlie"}
 *   Floor 3: {"Dave", "Eve"}
 * After:
 *   Floor 2: {} → removed because empty
 *   Floor 3: {"Dave", "Eve", "Charlie"}
 * Map updated: "Charlie" → 3
 *
 * When a person loses a point (dec):
 * - Move the person down one floor (decrement count in map).
 * - Remove the floor if it becomes empty.
 * - Remove the person from the map if their points reach 0.
 *
 * Example: Dave loses a point
 * Before:
 *   Floor 3: {"Dave", "Eve", "Charlie"}
 *   Floor 2: {} → empty (assume it was removed)
 * After:
 *   Floor 2: {"Dave"} → created because previously removed
 *   Floor 3: {"Eve", "Charlie"}
 * Map updated: "Dave" → 2
 *
 * Max/Min queries:
 * - getMaxKey() → any person on the top floor (most points)
 * - getMinKey() → any person on the bottom floor (least points)
 *
 * All operations (inc, dec, getMaxKey, getMinKey) are O(1) time:
 * - Map gives direct access to each person’s floor.
 * - Doubly linked list of floors allows constant-time addition/removal of floors and movement between floors.
 */




/*
 * AllOneDataStructure (DoublyLinkedList + HashMap + O(1) Increment/Decrement)
 * ONE LINER: class Bucket { int count; Set<String> keys = new HashSet<String>(); Bucket prev, next; Bucket(int count) { this.count = count; } }: (define bucket with count, set of keys, prev/next pointers for DLL); Map<String, Bucket> keyBucketMap; Bucket head, tail; public AllOne() { keyBucketMap = new HashMap<String, Bucket>(); }: (initialize map and DLL pointers); public void addBucketBefore(Bucket newBucket, Bucket cur) { newBucket.next = cur; if(cur != null) { newBucket.prev = cur.prev; if(cur.prev != null) { cur.prev.next = newBucket; } cur.prev = newBucket; if(cur == head) { head = newBucket; } } else { head = tail = newBucket; } }: (insert bucket before given bucket, updating DLL pointers and head/tail); public void addBucketAfter(Bucket newBucket, Bucket cur) { newBucket.prev = cur; if(cur != null) { newBucket.next = cur.next; if(cur.next != null) { cur.next.prev = newBucket; } cur.next = newBucket; if(cur == tail) { tail = newBucket; } } else { head = tail = newBucket; } }: (insert bucket after given bucket, updating DLL pointers and head/tail); public void deleteBucket(Bucket cur) { if(cur.prev != null) { cur.prev.next = cur.next; } else { head = cur.next; } if(cur.next != null) { cur.next.prev = cur.prev; } else { tail = cur.prev; } }: (remove bucket from DLL, updating neighbors and head/tail); public void removeKeys(Bucket bucket, String key) { bucket.keys.remove(key); if(bucket.keys.isEmpty()) { deleteBucket(bucket); } }: (remove key from bucket and delete bucket if empty); public void addkeys(Bucket bucket, String key) { keyBucketMap.put(key, bucket); bucket.keys.add(key); }: (add key to bucket and map); public void inc(String key) { if(!keyBucketMap.containsKey(key)) { if(head == null || head.count != 1) { addBucketBefore(new Bucket(1), head); } addkeys(head, key); } else { Bucket cur = keyBucketMap.get(key); Bucket next = cur.next; if(next == null || next.count != cur.count+1) { addBucketAfter(new Bucket(cur.count+1), cur); } next = cur.next; addkeys(next, key); removeKeys(cur, key); } }: (increment key count, create new bucket if needed, update DLL and map); public void dec(String key) { if(!keyBucketMap.containsKey(key)) { return; } Bucket cur = keyBucketMap.get(key); if(cur.count == 1) { keyBucketMap.remove(key); removeKeys(cur, key); } else { Bucket prev = cur.prev; if(prev == null || prev.count != cur.count-1) { addBucketBefore(new Bucket(cur.count-1), cur); } prev = cur.prev; addkeys(prev, key); removeKeys(cur, key); } }: (decrement key count, create new bucket if needed, remove key if count becomes zero); public String getMaxKey() { if(tail == null) { return ""; } return tail.keys.iterator().next(); }: (return any key from bucket with max count or empty string if none); public String getMinKey() { if(head == null) { return ""; } return head.keys.iterator().next(); }: (return any key from bucket with min count or empty string if none) → O(1), O(n) space (absolute: n for map + at most n keys in buckets + DLL nodes).
 *
 * Code(Reason):
 * - class Bucket { int count; Set<String> keys = new HashSet<String>(); Bucket prev, next; Bucket(int count) { this.count = count; } }: define Bucket to store count, keys in this count, and DLL pointers for constant-time insertion/deletion.
 * - Map<String, Bucket> keyBucketMap; Bucket head, tail;: store mapping from key to bucket and head/tail of doubly linked list of buckets.
 * - public AllOne() { keyBucketMap = new HashMap<String, Bucket>(); }: initialize map and DLL pointers.
 * - public void addBucketBefore(Bucket newBucket, Bucket cur) { 
 *     - newBucket.next = cur;: link new bucket's next to current.
 *     - if(cur != null) { 
 *         - newBucket.prev = cur.prev;: link new bucket's prev to cur's prev.
 *         - if(cur.prev != null) { cur.prev.next = newBucket; }: update previous bucket's next to new bucket.
 *         - cur.prev = newBucket;: link current's prev to new bucket.
 *         - if(cur == head) { head = newBucket; }: update head if cur was head.
 *     - } else { head = tail = newBucket; }: if cur null, initialize list with new bucket.
 * - public void addBucketAfter(Bucket newBucket, Bucket cur) {
 *     - newBucket.prev = cur;: link new bucket's prev to current.
 *     - if(cur != null) { 
 *         - newBucket.next = cur.next;: link new bucket's next to cur's next.
 *         - if(cur.next != null) { cur.next.prev = newBucket; }: update next bucket's prev to new bucket.
 *         - cur.next = newBucket;: link current's next to new bucket.
 *         - if(cur == tail) { tail = newBucket; }: update tail if cur was tail.
 *     - } else { head = tail = newBucket; }: if cur null, initialize list with new bucket.
 * - public void deleteBucket(Bucket cur) {
 *     - if(cur.prev != null) { cur.prev.next = cur.next; } else { head = cur.next; }: unlink prev pointer or update head.
 *     - if(cur.next != null) { cur.next.prev = cur.prev; } else { tail = cur.prev; }: unlink next pointer or update tail.
 * - public void removeKeys(Bucket bucket, String key) {
 *     - bucket.keys.remove(key);: remove key from bucket.
 *     - if(bucket.keys.isEmpty()) { deleteBucket(bucket); }: delete bucket if empty.
 * - public void addkeys(Bucket bucket, String key) {
 *     - keyBucketMap.put(key, bucket);: map key to bucket.
 *     - bucket.keys.add(key);: add key to bucket's set.
 * - public void inc(String key) {
 *     - if(!keyBucketMap.containsKey(key)) {
 *         - if(head == null || head.count != 1) { addBucketBefore(new Bucket(1), head); }: create bucket for count 1 if missing.
 *         - addkeys(head, key);: add new key to head bucket.
 *     - } else {
 *         - Bucket cur = keyBucketMap.get(key);: current bucket of key.
 *         - Bucket next = cur.next;: next bucket.
 *         - if(next == null || next.count != cur.count+1) { addBucketAfter(new Bucket(cur.count+1), cur); }: create next count bucket if needed.
 *         - next = cur.next;: refresh next pointer.
 *         - addkeys(next, key);: add key to next bucket.
 *         - removeKeys(cur, key);: remove key from current bucket and delete if empty.
 * - public void dec(String key) {
 *     - if(!keyBucketMap.containsKey(key)) { return; }: key does not exist, nothing to do.
 *     - Bucket cur = keyBucketMap.get(key);: current bucket.
 *     - if(cur.count == 1) {
 *         - keyBucketMap.remove(key);: remove key from map.
 *         - removeKeys(cur, key);: remove key from bucket and delete bucket if empty.
 *     - } else {
 *         - Bucket prev = cur.prev;: previous bucket.
 *         - if(prev == null || prev.count != cur.count-1) { addBucketBefore(new Bucket(cur.count-1), cur); }: create prev count bucket if needed.
 *         - prev = cur.prev;: refresh prev pointer.
 *         - addkeys(prev, key);: add key to previous bucket.
 *         - removeKeys(cur, key);: remove key from current bucket and delete if empty.
 * - public String getMaxKey() {
 *     - if(tail == null) { return ""; }: empty structure.
 *     - return tail.keys.iterator().next();: return any key from max bucket.
 * - public String getMinKey() {
 *     - if(head == null) { return ""; }: empty structure.
 *     - return head.keys.iterator().next();: return any key from min bucket.
 *
 * Rationale: Maintain keys grouped by counts in a doubly linked list of buckets for O(1) increment and decrement. HashMap maps keys to their buckets. Insertion, deletion, and movement between buckets are constant time using DLL and set operations. Head points to min count, tail to max count for fast retrieval.
 *
 * Time Complexity: O(1) — all operations (inc, dec, getMaxKey, getMinKey) involve constant-time hash map lookups and DLL pointer updates.
 * Space Complexity: O(n) — store all n keys in map and linked list buckets (absolute: n for map + up to n keys in buckets + n DLL nodes).
 *
 * Examples:
 * Example 1:
 * Input: ["AllOne","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey"]
 * Trace: AllOne(): initialize empty map and head/tail; inc("a"): head null → add bucket count=1, add key 'a'; inc("b"): head count=1 → add key 'b'; getMaxKey(): tail keys → 'a' or 'b'; getMinKey(): head keys → 'a' or 'b'; inc("a"): move 'a' to bucket count=2 → new bucket after count=1; getMaxKey(): tail keys → 'a'; getMinKey(): head keys → 'b'
 * Output: ["null","null","null","a","b","null","a","b"]
 *
 * Example 2:
 * Input: ["AllOne","inc","dec","getMaxKey","getMinKey"]
 * Trace: AllOne(): initialize; inc("a"): add bucket count=1, key 'a'; dec("a"): remove 'a' from bucket count=1 and map, delete bucket as empty; getMaxKey(): tail null → ""; getMinKey(): head null → ""
 * Output: ["null","null","null","",""]
 */


class AllOneDLLMap {
    class Bucket{
        int count;
        Set<String> keys = new HashSet<String>();
        Bucket prev, next;
        Bucket(int count){
            this.count=count;
        }
    }
    Map<String, Bucket> KeyBucketMap = new HashMap<>();
    Bucket head, tail;
    public AllOneDLLMap() {
        
    }

    //    [N] h
    // [P]   [C]
    public void addBucketBefore(Bucket newBucket, Bucket cur){
        newBucket.next = cur;
        if(cur!=null){
            newBucket.prev = cur.prev;
            if(cur.prev!=null){
                cur.prev.next = newBucket;
            }
            cur.prev = newBucket;
            if(cur==head){
                head = newBucket;
            }
        }else{
            head = tail = newBucket;
        }
    }
    
    //  t [N] 
    // [C]   [Nx]
    public void addBucketAfter(Bucket newBucket, Bucket cur){
        newBucket.prev = cur;
        if(cur!=null){
            newBucket.next = cur.next;
            if(cur.next!=null){
                cur.next.prev = newBucket;
            }
            cur.next = newBucket;
            if(cur==tail){
                tail = newBucket;
            }
        }else{
            head = tail = newBucket;
        }
    }
    
    //      h,t
    // [P]  [C]  [Nx]
    public void deleteBucket(Bucket cur){
        if(cur.prev!=null){
            cur.prev.next = cur.next;
        }
        else{
            head = head.next;
        }
        if(cur.next!=null){
            cur.next.prev = cur.prev;
        }
        else{
            tail = tail.prev;
        }
    }

    public void addKey(String key, Bucket bucket){
        KeyBucketMap.put(key, bucket);
        bucket.keys.add(key);
    }

    public void removeKey(String key, Bucket bucket){
        KeyBucketMap.remove(key);
        bucket.keys.remove(key);
        if(bucket.keys.isEmpty()){
            deleteBucket(bucket);
        }
    }
    
    //         [N] 
    // [P]  [a]   [Nx]
    public void inc(String key) {
        if(!KeyBucketMap.containsKey(key)){
            if(head==null || head.count!=1){
                addBucketBefore(new Bucket(1), head);
            }
            addKey(key, head);
        }
        else{
            Bucket cur = KeyBucketMap.get(key);
            Bucket next = cur.next;
            if(next==null || next.count!=cur.count+1){
                addBucketAfter(new Bucket(cur.count+1), cur);
            }
            next = cur.next;
            removeKey(key,cur);
            addKey(key,next);
        }
    }
    
    //    [N]    
    // [P]   [C]  [Nx]
    public void dec(String key) {
        if(!KeyBucketMap.containsKey(key)){
            return;
        }
        Bucket cur = KeyBucketMap.get(key);
        if(cur.count==1){
            removeKey(key,cur);
        }
        else{
            Bucket prev = cur.prev;
            if(prev==null || prev.count!=cur.count-1){
                addBucketBefore(new Bucket(cur.count-1), cur);
            }
            prev = cur.prev;
            removeKey(key,cur);
            addKey(key,prev);
        }

    }
    
    public String getMaxKey() {
        if(tail==null){
            return "";
        }
        return tail.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head==null){
            return "";
        }
        return head.keys.iterator().next();
    }
}



/*
 * AllOneDLLMapDummyHeadTail (DoublyLinkedList + HashMap + Bucket + O(1) Operations)
 * ONE LINER: class Bucket{int count; Set<String> keys = new HashSet<String>(); Bucket prev, next; Bucket(int count){this.count = count;}}: (defines a bucket storing keys with same count and pointers for DLL); Map<String, Bucket> keyBucketMap: (maps keys to their current bucket for O(1) access); Bucket head = new Bucket(0): (dummy head for DLL); Bucket tail = new Bucket(0): (dummy tail for DLL); public AllOneDLLMapDummyHeadTail(): keyBucketMap = new HashMap<String, Bucket>(): (initialize key-bucket map), head.next = tail: (connect dummy head to tail), tail.prev = head: (connect dummy tail to head); public void addBucketAfter(Bucket newBucket, Bucket cur): newBucket.prev = cur: (link new bucket's prev), newBucket.next = cur.next: (link new bucket's next), cur.next.prev = newBucket: (link next bucket's prev), cur.next = newBucket: (link current bucket's next to new bucket); public void deleteBucket(Bucket cur): cur.prev.next = cur.next: (bypass current bucket), cur.next.prev = cur.prev: (bypass current bucket backwards); public void removeKeys(Bucket bucket, String key): bucket.keys.remove(key): (remove key from bucket), if(bucket.keys.isEmpty()){deleteBucket(bucket)}: (delete bucket if empty); public void addkeys(Bucket bucket, String key): keyBucketMap.put(key,bucket): (map key to bucket), bucket.keys.add(key): (add key to bucket set); public void inc(String key): if(!keyBucketMap.containsKey(key)): Bucket next = head.next: (first real bucket), if(next==tail || next.count!=1){addBucketAfter(new Bucket(1), head)}: (create new bucket if needed), next = head.next: (refresh next), addkeys(next, key): (add key to bucket), else: Bucket cur = keyBucketMap.get(key): (current bucket), Bucket next = cur.next: (next bucket), if(next==tail || next.count!=cur.count+1){addBucketAfter(new Bucket(cur.count+1), cur)}: (create next bucket if needed), next = cur.next: (refresh next), addkeys(next, key): (add key to next bucket), removeKeys(cur, key): (remove key from old bucket); public void dec(String key): if(!keyBucketMap.containsKey(key)) return: (ignore missing key), Bucket cur = keyBucketMap.get(key): (current bucket), if(cur.count==1){keyBucketMap.remove(key): (remove key from map), removeKeys(cur,key): (remove key from bucket)}, else: Bucket prev = cur.prev: (previous bucket), if(prev==head || prev.count!=cur.count-1){addBucketAfter(new Bucket(cur.count-1), prev)}: (create previous bucket if needed), prev = cur.prev: (refresh prev), addkeys(prev, key): (add key to previous bucket), removeKeys(cur, key): (remove key from current bucket); public String getMaxKey(): if(tail.prev==head) return "": (no keys exist), return tail.prev.keys.iterator().next(): (return any key from max count bucket); public String getMinKey(): if(head.next==tail) return "": (no keys exist), return head.next.keys.iterator().next(): (return any key from min count bucket) → O(1), O(1) space (absolute: key-bucket map + DLL of buckets)
 *
 * Code(Reason):
 * - class Bucket{int count; Set<String> keys = new HashSet<String>(); Bucket prev, next; Bucket(int count){this.count = count;}}: defines a bucket class holding keys of same count and pointers for doubly linked list navigation.
 * - Map<String, Bucket> keyBucketMap: stores mapping from each key to its current bucket for O(1) access.
 * - Bucket head = new Bucket(0): dummy head to simplify boundary operations in DLL.
 * - Bucket tail = new Bucket(0): dummy tail to simplify boundary operations in DLL.
 * - public AllOneDLLMapDummyHeadTail():
 *     - keyBucketMap = new HashMap<String, Bucket>(): initialize map.
 *     - head.next = tail: link dummy head to tail.
 *     - tail.prev = head: link dummy tail to head.
 * - public void addBucketAfter(Bucket newBucket, Bucket cur):
 *     - newBucket.prev = cur: set previous pointer.
 *     - newBucket.next = cur.next: set next pointer.
 *     - cur.next.prev = newBucket: update next bucket’s prev pointer.
 *     - cur.next = newBucket: insert new bucket after current.
 * - public void deleteBucket(Bucket cur):
 *     - cur.prev.next = cur.next: bypass current bucket in DLL.
 *     - cur.next.prev = cur.prev: bypass current bucket backwards.
 * - public void removeKeys(Bucket bucket, String key):
 *     - bucket.keys.remove(key): remove key from current bucket.
 *     - if(bucket.keys.isEmpty()){deleteBucket(bucket)}: remove bucket if empty.
 * - public void addkeys(Bucket bucket, String key):
 *     - keyBucketMap.put(key,bucket): map key to bucket.
 *     - bucket.keys.add(key): add key to bucket’s key set.
 * - public void inc(String key):
 *     - if(!keyBucketMap.containsKey(key)):
 *         - Bucket next = head.next: first real bucket after dummy head.
 *         - if(next==tail || next.count!=1){addBucketAfter(new Bucket(1), head)}: create bucket with count 1 if missing.
 *         - next = head.next: refresh next pointer.
 *         - addkeys(next, key): add key to new bucket.
 *     - else:
 *         - Bucket cur = keyBucketMap.get(key): current bucket.
 *         - Bucket next = cur.next: next bucket in DLL.
 *         - if(next==tail || next.count!=cur.count+1){addBucketAfter(new Bucket(cur.count+1), cur)}: create next bucket if missing.
 *         - next = cur.next: refresh next pointer.
 *         - addkeys(next, key): add key to next bucket.
 *         - removeKeys(cur, key): remove key from old bucket.
 * - public void dec(String key):
 *     - if(!keyBucketMap.containsKey(key)) return: do nothing if key missing.
 *     - Bucket cur = keyBucketMap.get(key): current bucket.
 *     - if(cur.count==1):
 *         - keyBucketMap.remove(key): remove key from map as count drops to 0.
 *         - removeKeys(cur,key): remove key from bucket.
 *     - else:
 *         - Bucket prev = cur.prev: previous bucket.
 *         - if(prev==head || prev.count!=cur.count-1){addBucketAfter(new Bucket(cur.count-1), prev)}: create previous bucket if missing.
 *         - prev = cur.prev: refresh prev pointer.
 *         - addkeys(prev, key): add key to previous bucket.
 *         - removeKeys(cur, key): remove key from current bucket.
 * - public String getMaxKey():
 *     - if(tail.prev==head) return "": no keys exist.
 *     - return tail.prev.keys.iterator().next(): return any key from max count bucket.
 * - public String getMinKey():
 *     - if(head.next==tail) return "": no keys exist.
 *     - return head.next.keys.iterator().next(): return any key from min count bucket.
 *
 * Rationale: This implementation maintains a doubly linked list of buckets, each containing keys with identical counts, along with a map from keys to their buckets. Incrementing or decrementing a key moves it to an adjacent bucket (creating it if needed) and allows constant-time retrieval of max/min keys from the tail/head, enabling O(1) updates and queries.
 *
 * Time Complexity: O(1) — all operations (inc, dec, getMaxKey, getMinKey) require at most pointer adjustments and map lookups.
 * Space Complexity: O(1) — excluding the space for key storage; absolute: keyBucketMap size + number of buckets linked in DLL.
 *
 * Examples:
 * Example 1:
 * Input: inc("a"), inc("b"), inc("a")
 * Trace: "a" goes to bucket 1 → "b" to bucket 1 → "a" moves from bucket 1 to 2; DLL buckets updated accordingly.
 * Output: getMaxKey() = "a", getMinKey() = "b"
 *
 * Example 2:
 * Input: inc("a"), inc("b"), inc("a"), dec("a")
 * Trace: "a" moves from bucket 1 → 2 → then dec moves back to bucket 1; "b" remains in bucket 1.
 * Output: getMaxKey() = "b" (or "a"), getMinKey() = "b" (or "a") — any key from top/bottom bucket.
 */


class AllOneDLLMapDummyHeadTail {
    class Bucket{
        int count;
        Set<String> keys = new HashSet<String>();
        Bucket prev, next;
        Bucket(int count){
            this.count=count;
        }
    }
    Map<String, Bucket> KeyBucketMap = new HashMap<>();
    Bucket head = new Bucket(0);
    Bucket tail = new Bucket(0);
    public AllOneDLLMapDummyHeadTail() {
        head.next = tail;
        tail.prev = head;
    }

    public void addBucketAfter(Bucket newBucket, Bucket cur){
        newBucket.prev = cur;
        newBucket.next = cur.next;
        cur.next.prev = newBucket;
        cur.next = newBucket;
    }
    
    
    //      h,t
    // [P]  [C]  [Nx]
    public void deleteBucket(Bucket cur){
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }

    public void removeKey(String key, Bucket bucket){
        KeyBucketMap.remove(key);
        bucket.keys.remove(key);
        if(bucket.keys.isEmpty()){
            deleteBucket(bucket);
        }
    }

    public void addKey(String key, Bucket bucket){
        KeyBucketMap.put(key, bucket);
        bucket.keys.add(key);
    }
    
    //         [N] 
    // [P]  [a]   [Nx]
    public void inc(String key) {
        if(!KeyBucketMap.containsKey(key)){
            Bucket next = head.next;
            if(next.count!=1){
                addBucketAfter(new Bucket(1), head);
            }
            next = head.next;
            addKey(key, next);
        }
        else{
            Bucket cur = KeyBucketMap.get(key);
            Bucket next = cur.next;
            if(next.count!=cur.count+1){
                addBucketAfter(new Bucket(cur.count+1), cur);
            }
            next = cur.next;
            removeKey(key,cur);
            addKey(key,next);
        }
    }
    
    //    [N]    
    // [P]   [C]  [Nx]
    public void dec(String key) {
        if(!KeyBucketMap.containsKey(key)){
            return;
        }
        Bucket cur = KeyBucketMap.get(key);
        if(cur.count==1){
            removeKey(key,cur);
        }
        else{
            Bucket prev = cur.prev;
            if(prev.count!=cur.count-1){
                addBucketAfter(new Bucket(cur.count-1), prev);
            }
            prev = cur.prev;
            removeKey(key,cur);
            addKey(key,prev);
        }

    }
    
    public String getMaxKey() {
        if(tail.prev==head){
            return "";
        }
        return tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head.next==tail){
            return "";
        }
        return head.next.keys.iterator().next();
    }
}





// === Alternate Paradigm Summary ===
/*
 * Brute Force HashMap scan:
 * - Very simple to implement.
 * - getMinKey/getMaxKey require scanning the entire map → O(n).
 *
 * Bucket List + HashMap:
 * - True O(1) for all operations.
 * - Complex structure involving custom bucket-linked list.
 * - Excellent for extremely high-performance requirements.
 */

// === Brute-to-Optimal Comparison Table ===
/*
 * | Approach               | inc   | dec   | getMinKey | getMaxKey | Space |
 * |------------------------|-------|-------|-----------|-----------|-------|
 * | Brute Force           | O(1)  | O(1)  | O(n)      | O(n)      | O(n)  |
 * | Bucket List + HashMap | O(1)  | O(1)  | O(1)      | O(1)      | O(n)  |
 */

// === Complexity Comparison Table ===
/*
 * | Approach               | Time Complexity        | Space Complexity |
 * |------------------------|------------------------|-----------------|
 * | Brute Force           | inc: O(1), others O(n)| O(n)            |
 * | Bucket List + HashMap | O(1)                   | O(n)            |
 */

// === Interview Talking Points ===
/*
 * - Pitch: We examined two approaches: brute force and bucket list with hashmap.
 * - Brute force: simplest, violates O(1) requirement for min/max.
 * - Bucket list: optimal O(1) but more complex to implement.
 * - Chose bucket list for strict O(1) guarantee, especially in high-performance scenarios.
 */

// === Follow-up / Scaling / Discussion Points ===
/*
 * - Scaling: Bucket list offers strict O(1) for all operations, scales very well for large data.
 * - Trade-offs: higher implementation complexity, but minimal time overhead.
 * - Real-world use: ideal for extremely high QPS systems (e.g., leaderboards, counters).
 * - Optimizations: lazy deletion, object pooling, or concurrency-safe modifications if needed.
 */

public class AllOneDataStructure{
    public static void main(String[] args){
        System.out.println("ALL ONE");
    }
}