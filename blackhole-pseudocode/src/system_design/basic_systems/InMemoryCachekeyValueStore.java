/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.basic_systems;

/**
 *
 * @author user
 * 
 * 
 * PHASE 1 — Clarify Requirements (Start Here)
 * PHASE 2 — LLD: Single-Node Cache (Core Part)
 * PHASE 3 — Single-node Cache: Functional Flow
 * PHASE 4 — HLD: Distributed Cache
 * 1. Partitioning Keys Across Nodes

        Use consistent hashing.
    
  

* 2. Replication for Fault Tolerance

Say:

“Each key maps to primary + (N-1) replicas.
Typically 2 or 3 replicas for HA.”
* 
* 3. Eviction Policy in Distributed System

Say:

“Eviction stays local per node.
Cycle: Key → partition → node’s in-memory cache → node’s eviction.
Replicas must follow same deterministic ordering if eviction affects them.”

LRU/LFU are local.
Nodes do not coordinate eviction globally (too expensive).
This is the correct answer.
* 
* 4. Handling Hot Keys (VERY IMPORTANT)

LinkedIn ALWAYS asks this.

Say:

“Hot keys cause a single node to become overloaded.
Solutions:

Replicate popular keys more aggressively.

Client-side read replication (read from any replica).

Request coalescing (single-flight) inside node.

Shard hot key logically into sub-keys.

LRU shard awareness.”

This is gold.
* 
* 5. Consistency Models
* 
* 6. Write Policies
* 
* 7. Data Persistence (if asked)
* 
* PHASE 5 — Redis Internals (bonus points)

If asked about Redis:

You MUST mention at least:

✔ Uses a hashtable + skiplist for sorted sets
✔ Lazy eviction (expiry checked on access)
✔ Active eviction (randomized sampling)
✔ LRU approximation (not perfect LRU)
✔ Memory fragmentation issues
✔ Hash slots (16384) for clustering
✔ Replication via async propagation

This shows deep backend knowledge.
* 
* 
 */
public class InMemoryCachekeyValueStore {
    
}
