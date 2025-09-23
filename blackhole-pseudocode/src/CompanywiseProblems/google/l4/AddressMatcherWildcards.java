package CompanywiseProblems.google.l4;

import java.util.*;

/* 

https://leetcode.com/discuss/post/7139598/google-l4-coding-round-3-question-by-ano-9m5i/

Description:
You are given a list of addresses. Each address is a tuple/list of 4 strings:
(streetNumber, streetName, city, state).

You are also given a list of queries. Each query is also length 4 but may contain NULL (or null) in any position. A NULL in a query matches any value for that field (i.e., it is a wildcard). For each query, determine whether at least one address from the input list matches the query (exact match for non-NULL fields, wildcard for NULL fields). Return a list of booleans (or True/False) — one per query.

Example:

Input addresses:

0: ("1", "Main St", "Springfield", "IL")
1: ("2", "Main St", "Shelbyville", "IL")
2: ("1A", "Elm St", "Springfield", "IL")
3: ("3", "Main St", "Springfield", "IN")
4: ("1", "Main St", "Springfield", "IL")  // duplicate of 0


Queries and expected results:

Q0: ("1", "Main St", "Springfield", "IL")  -> True   // addresses 0 and 4 match
Q1: ("1", null, "Springfield", null)       -> True   // addresses 0 and 4 match
Q2: (null, "Main St", null, "IL")          -> True   // addresses 0 and 1 and 4 match
Q3: ("X", null, null, null)                -> False  // no streetNumber "X"
Q4: (null, null, null, null)               -> True   // matches any address (non-empty dataset)


Output: [True, True, True, False, True]

Constraints / Notes

N = number of addresses. Q = number of queries.

Each address has exactly 4 fields (strings). Fields may contain letters, digits, spaces, punctuation.

Query fields are either a string or NULL (wildcard).

Matching rule: exact equality (subject to normalization policy — e.g., case-insensitive or trimmed — described below).

Multiple nulls per query are allowed.

Addresses may contain duplicates.

Aim for solutions from naive to production-scale for real-world loads (millions of rows / heavy query load).

*/


public class AddressMatcherWildcards {

    /*     

    Brute Force Approach

    Idea:
    For each query → scan every address → check field-by-field.
    If all non-null fields match → success.

    Time: O(Q * N * 4) → basically O(Q * N)

    Space: O(1) extra

    Good for: small datasets, correctness check, warm-up

    */
    public List<Boolean> matchBruteForce(List<String[]> addresses,  List<String[]> queries){
        var res = new ArrayList<Boolean>();
        for(var query: queries){
            boolean found = false;
            for(var address: addresses){
                if(matches(address, query)){
                    found = true;
                    break;
                }
            }
            res.add(found);
        }
        return res;
    }

    public boolean matches(String[] address, String[] query){
        for(int i=0;i<4;i++){
            if(query[i]!=null && !query[i].equals(address[i]))
            return false;
        }
        return true;
    }


     // ----------------- INVERTED INDEX -----------------
    /**
     * Build 4 inverted indexes (one per field).
     *
     * Example:
     *   Addresses:
     *     id=0 ["123", "Main",     "NYC", "NY"]
     *     id=1 ["456", "Broadway", "NYC", "NY"]
     *     id=2 ["789", "Market",   "SF",  "CA"]
     *
     *   Index (field -> value -> set of IDs):
     *     street_number: "123":{0}, "456":{1}, "789":{2}
     *     street_name:   "Main":{0}, "Broadway":{1}, "Market":{2}
     *     city:          "NYC":{0,1}, "SF":{2}
     *     state:         "NY":{0,1}, "CA":{2}
     *
     *   Query ["123", null, "NYC", "NY"]:
     *     street_number=123 -> {0}
     *     city=NYC          -> {0,1}
     *     state=NY          -> {0,1}
     *     intersect({0}, {0,1}, {0,1}) = {0} → true
     *
     * Time:
     *   - Preprocess: O(N * 4)
     *   - Query: O(sum of candidate set sizes), early exit if empty
     * Space:
     *   - O(N * 4) for maps
     *
     * Key Points for Recall:
     * - 4 maps: field → value → set of IDs
     * - Query = intersect sets from non-null fields
     * - Empty set → immediate false
     * - All NULL → true
     * 
     * Discussion point for Google:
        Works well when most queries have at least one non-null field → sets to intersect are smaller than N.
        Handles multiple NULLs efficiently → skip those maps.
     */
    public List<Boolean> matchInvertedIndex(List<String[]> addresses, List<String[]> queries) {
        var index = buildIndex(addresses);
        var result = new ArrayList<Boolean>();
        for (var query : queries) {
            Set<Integer> candidate = null;

            for (int field = 0; field < 4; field++) {
                var fieldValue = query[field];
                if (fieldValue == null) continue;

                var matchingIds = index.get(field).get(fieldValue);
                if (matchingIds == null) { // no match
                    candidate = Collections.emptySet();
                    break;
                }

                if (candidate == null) {
                    candidate = new HashSet<>(matchingIds); // first non-null field
                } else {
                    candidate.retainAll(matchingIds); // set intersection
                    if (candidate.isEmpty()) break; // early exit
                }
            }

            result.add(candidate == null || !candidate.isEmpty());
        }

        return result;
    }

    private List<Map<String, Set<Integer>>> buildIndex(List<String[]> addresses) {
        var index = new ArrayList<Map<String, Set<Integer>>>();
        for (int i = 0; i < 4; i++) index.add(new HashMap<>());

        for (int id = 0; id < addresses.size(); id++) {
            for (int field = 0; field < 4; field++) {
                var value = addresses.get(id)[field];
                index.get(field).computeIfAbsent(value, k -> new HashSet<>()).add(id);
            }
        }

        return index;
    }

/* 
| Approach                          | Time                                                  | Space      | When best                   | Notes                          |
| --------------------------------- | ----------------------------------------------------- | ---------- | --------------------------- | ------------------------------ |
| Brute                             | O(Q*N*4)                                              | O(1) extra | Small N/Q                   | Easy, but scales poorly        |
| Inverted Index                    | O(N\*4) prep, O(sum of candidate set sizes per query) | O(N\*4)    | Large N, sparse queries     | Google expected solution       |
| Sorted intersection / small-first | Slightly faster query                                 | Same       | Sparse queries, large N     | Good discussion                |
| Precomputed 16-combinations       | O(N\*16) prep, O(1) query                             | O(N\*16)   | Many queries, very frequent | Can mention, rare in interview |

3. 16-Combinations Precomputation
 * ----------------------------------
 * - For each address, generate all 2^4 = 16 possible key combinations representing present/null fields
 *   e.g., address ["123","Main","NYC","NY"] => keys like "123-Main-NYC-NY", "123--NYC-NY", etc.
 * - Store all keys in a HashSet
 * - Query: convert query to key (use "-" or null for missing fields) → O(1) lookup
 * - Time:
 *      - Precompute: O(N * 16 * 4) → O(N)
 *      - Query: O(1)
 * - Space: O(N * 16) → higher memory
 * - Pros: Instant query, handles all null combinations naturally
 * - Cons: Memory overhead ~16x; not necessary unless queries are extremely frequent
 * - Google discussion points:
 *      - Mention trade-off: memory vs query speed
 *      - Only suitable for small-to-medium N in practical coding interview
 *
 *
 * 4. Bloom Filter for 16-Combinations
 * ------------------------------------
 * - Concept: store all 16N keys in a Bloom filter (bit array + k hash functions)
 * - Query: hash query key → check if all bits set
 * - Properties:
 *      - No false negatives → if filter says "not present," it definitely isn't
 *      - False positives possible → if filter says "present," may need actual check
 * - Time:
 *      - Insert: O(k) per key
 *      - Query: O(k) per query
 * - Space: much smaller than storing all strings in HashSet
 * - Pros: Memory-efficient, fast query
 * - Cons: Probabilistic, may return false positives → extra check required
 * - Google discussion points:
 *      - Mention as optimization for **extremely large N and very frequent queries**
 *      - Not expected to implement in a 60-min interview unless explicitly asked
 *      - Ideal discussion point: “memory vs accuracy trade-off”
 *
 *
 * 5. Additional Optimizations / Notes
 * -----------------------------------
 * - Handle all-NULL queries separately → matches any address instantly
 * - Consider caching repeated queries → memoization to avoid repeated intersections
 * - Intersect sets in order of increasing size → reduces intermediate candidate size
 * - Trade-offs summary:
 *      - Brute: simplest, slow
 *      - Inverted index: deterministic, memory reasonable, scalable
 *      - 16-combinations: O(1) query, higher memory
 *      - Bloom filter: memory-efficient, probabilistic, use in extreme-scale scenarios
 *
 * Note on Normalization:
 * -----------------------
 * In real-world scenarios, address fields may differ in case or contain extra spaces.
 * To avoid mismatches:
 *   - Convert all strings to lower case: value.toLowerCase()
 *   - Trim leading/trailing whitespace: value.trim()
 *   - Optional: remove punctuation or standardize abbreviations (St → Street)
 * Apply the same normalization to both addresses and queries before matching.
 * 
 * =======================================================================
 */

    public static void main(String[] args) {
        AddressMatcherWildcards matcher = new AddressMatcherWildcards();

        var addresses = List.of(
            new String[]{"123", "Main", "NYC", "NY"},     // id=0
            new String[]{"456", "Broadway", "NYC", "NY"}, // id=1
            new String[]{"789", "Market", "SF", "CA"},    // id=2
            new String[]{"101", "Main", "LA", "CA"}       // id=3
        );

        var queries = List.of(
            new String[]{"123", null, "NYC", "NY"},       // true, exact match on id=0
            new String[]{null, "Market", "SF", "CA"},     // true, matches id=2
            new String[]{null, null, "LA", "CA"},         // true, matches id=3
            new String[]{null, null, null, null},         // true, all NULL → any address matches
            new String[]{"000", null, null, null},        // false, no address with 000 street_number
            new String[]{null, "Broadway", "SF", null},   // false, city SF but street "Broadway" doesn't exist in SF
            new String[]{null, "Main", null, "CA"}        // true, id=3
        );

        System.out.println(matcher.matchBruteForce(addresses, queries));
        System.out.println(matcher.matchInvertedIndex(addresses, queries));
        // [true, true, true, true, false, false, true]
    }
}
