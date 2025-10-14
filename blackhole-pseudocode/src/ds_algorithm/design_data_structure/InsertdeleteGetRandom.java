package ds_algorithm.design_data_structure;

import java.util.*;
// ============================================================
// Problem Name: RandomizedCollectionDuplicatesAllowed
// Alternate alias: InsertDeleteGetRandomO1WithDuplicates
// ============================================================

// ============================================================
// Problem Description:
// Design a data structure RandomizedCollection that supports insert, remove, and getRandom operations 
// in average O(1) time, allowing duplicate elements.
//
// Input:
//   - insert(int val): Inserts val into the collection. Returns true if val was not already present, false otherwise.
//   - remove(int val): Removes one occurrence of val if present. Returns true if removed, false otherwise.
//   - getRandom(): Returns a random element from the collection, probability weighted by occurrences.
//
// Output:
//   - Boolean for insert/remove operations, int for getRandom.
//
// Constraints:
//   - -2^31 <= val <= 2^31 - 1
//   - Up to 2*10^5 total calls
//   - getRandom only called if collection is non-empty
//
// Edge Cases:
//   - Empty input
//   - Multiple duplicates
//   - Single element
//   - Min/Max integers
//   - Consecutive inserts/removes
//   - Very large input sizes
// ============================================================

// ============================================================
// Brute Force Solution:
// ============================================================

// Idea: Use a simple ArrayList to store all elements. 
// insert(): append to list, check contains (O(n)) for duplicates. 
// remove(): linear scan to find element and remove (O(n)).
// getRandom(): Random index access O(1).
// Limitation: insert/remove are O(n), not O(1).


class RandomizedCollectionBrute {
    private List<Integer> list;
    public RandomizedCollectionBrute() {
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        boolean notPresent = !list.contains(val); // O(n)
        list.add(val);
        return notPresent;
    }

    public boolean remove(int val) {
        int idx = list.indexOf(val); // O(n)
        if(idx == -1) return false;
        list.remove(idx); // O(n)
        return true;
    }

    public int getRandom() {
        int idx = new Random().nextInt(list.size());// O(1)
        return list.get(idx);
    }
}

// ============================================================
// Optimal Solution (HashMap + ArrayList + Set indices)
// ============================================================

// Concept: Maintain:
// 1. ArrayList<Integer> list -> stores all elements for O(1) random access.
// 2. HashMap<Integer, Set<Integer>> valToIndices -> stores indices of each value in list.
//
// Operations:
// - insert(val):
//     - append val to list
//     - add index to valToIndices set
//     - return true if first insertion
//
// - remove(val):
//     - get any index of val from valToIndices
//     - swap last element in list to index to delete
//     - update valToIndices for moved element
//     - remove last element from list
//     - remove index from valToIndices[val]
//     - return true if element existed
//
// - getRandom(): pick random index from list
//
// All operations average O(1), space O(n).



class RandomizedCollection {
    Map<Integer,Set<Integer>> valToIndices;
    List<Integer> list;
    Random random;
    public RandomizedCollection() {
        valToIndices = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        boolean notPresent = false;
        if(!valToIndices.containsKey(val)){
            notPresent=true;
            valToIndices.put(val, new HashSet<>());
        }
        list.add(val);
        valToIndices.get(val).add(list.size()-1);
        return notPresent;
    }
    // 1:[0,1,3] 3:[2]
    //  [1] [1] [2] [1]
    //   0   1   2   3
    public boolean remove(int val) {
        if(!valToIndices.containsKey(val)){
            return false;
        }
        var removeIndices = valToIndices.get(val);
        int removeIdx = removeIndices.iterator().next();
        removeIndices.remove(removeIdx);

        int lastIdx = list.size()-1;
        int lastVal = list.get(lastIdx);
        list.set(removeIdx, lastVal);
        list.remove(lastIdx);
        
        var lastIndices = valToIndices.get(lastVal);
        lastIndices.add(removeIdx);
        lastIndices.remove(lastIdx);
        if(removeIndices.isEmpty()){
            valToIndices.remove(val);
        }
        return true;
    }
    
    public int getRandom() {
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }
}

// ============================================================
// Alternate Paradigm Summary:
// - Brute: ArrayList only, linear scan for remove/insert -> simple, slow for duplicates
// - Optimal: ArrayList + HashMap<Set<Integer>> -> O(1) average, handles duplicates efficiently
// ============================================================

// ============================================================
// Brute-to-Optimal Comparison Table:
// | Approach | Time Complexity | Space Complexity | Notes |
// | Brute    | insert/remove O(n), getRandom O(1) | O(n) | simple, fails large N |
// | Optimal  | insert/remove/getRandom O(1) avg | O(n) | HashMap+ArrayList, handles duplicates efficiently |
// ============================================================

// ============================================================
// Complexity Comparison:
// Time:
// - insert: O(1) avg (absolute: O(1))
// - remove: O(1) avg (absolute: O(1))
// - getRandom: O(1)
// Space:
// - O(n) for list + map storage
// ============================================================

// ============================================================
// Interview Talking Points:
// - Use ArrayList for random access, HashMap+Set for index tracking
// - Efficient handling of duplicates via Set of indices
// - Early clean-up for empty sets
// - Expected follow-ups: concurrency, scaling to huge datasets, randomization fairness
// ============================================================

// ============================================================
// Follow-up / Scaling / Discussion Points:
// - Large N: use LinkedHashSet or TreeSet if ordered access required
// - Memory vs speed trade-off: maintaining sets increases space but gives O(1) operations
// - Precomputation: not needed; all operations online
// - Edge handling: empty collection, min/max integer, duplicate removals
// ============================================================

// ============================================================
// Main Method with Tests
// ============================================================
class TestRandomizedCollection {
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();

        // Test 1: normal inserts
        System.out.println(rc.insert(1)); // true ✅ first insertion
        System.out.println(rc.insert(1)); // false ✅ duplicate
        System.out.println(rc.insert(2)); // true ✅ new element

        // Test 2: getRandom weighted
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i=0;i<10000;i++){
            int val = rc.getRandom();
            freq.put(val, freq.getOrDefault(val, 0)+1);
        }
        System.out.println(freq); // 1 should appear roughly 2x as often as 2

        // Test 3: remove
        System.out.println(rc.remove(1)); // true ✅ removed one occurrence
        System.out.println(rc.remove(1)); // true ✅ removed second occurrence
        System.out.println(rc.remove(1)); // false ✅ no more 1s
        System.out.println(rc.getRandom()); // should be 2

        // Edge case: remove non-existent
        System.out.println(rc.remove(3)); // false ✅ does not exist
    }
}

// ============================================================
// Comments / Recall Table:
// Approach | Key Idea | When to Use | Trade-offs
// Brute    | List only | Small N, duplicates rare | O(n) ops
// Optimal  | List+Map(Set) | Large N, frequent duplicates | O(1) avg ops, more memory
// ============================================================

// ============================================================
// Normalization Note:
// N/A for integers
// ============================================================

public class InsertdeleteGetRandom {
    
}
