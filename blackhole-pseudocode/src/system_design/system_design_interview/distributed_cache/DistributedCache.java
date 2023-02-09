/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.distributed_cache;

/**
 *
 * @author venka
 */

import java.util.*;

// distributed cache like redis

public class DistributedCache {
    
    
    /*
    issues with just DB
        db executes for long time, costs resources, we can store some results in memeory
    
    while using cache, first we check in cache, only if data not presnt or stale we hit db
    data is too large to store in single machine, split data and store it across several machines
    
    Functional requirements
        put(key, value)
        get(key)
    
    Non Functonal Requirements
        Scalability(handles more data load , high put and get requests)
        highly Perfomant(low resonse time for main operations)
        High Availability(survives hardware and network partition) minimize number of cache misses
    
    local cache
        implement in memeory in single server with limited capacity
        
    data strcuctures and algo - HashMap + Queue(doubly linkedlist) (LRU) uses constant time for add, remove etc
        head - most recently used, tail - least renectly used
        get - check if item in cache, no return null, yes move item to head of list
        put - item in cache? yes update item, move to head,  
                    no -> cache full? no -> add item to hash, move to head. yes ->  remove tail from  lista dn hashtable, add item to hash, move to head
        IMPL below
    
    
    distributed cache
    sharding - partitioned into chunks
    
    dedicated cache cluster - sevice hosts + separate cache hosts or clusters
        isolation of resources, accessed by multiple services, hardware choice for cache hosts 
    colocated cache cluster - run cache as a process in service hosts 
        save hardware and operational costs, scale together
    
    choosing the right cache hosts for data
        1. mod function? - use mod on number of hosts, but adding hosts causes problems
        2. consistent hashing - mapping object to point in circle, compute hash based on host identifier like ip or name
                  assign hash ranges to each host in circle, owns range from host to nearest clockwise neighbour
                  for look up, calculate hash and move backwards to identify host, 
                  when adding hosts, we map new host to circle and new host becomes responsible for hash range, 
                  while counterclockwie neighbour becomes responsible for smaller range, rehashing happens for the two hosts
    
    cache client - identifies cache cluster with consistent hashing, lightweight library lives inside service host
               knows aboutall acche hosts, all cache clients hould have same list of cache hosts, 
                stores hosts in sorted order treeMap for binary search(logn), communicates in TCP/UDP
                if cache hosts unavailable, proceeds like a cache miss
                  
    maintaining list of cache servers
        1. config files + continuous deployment pipeline like chef / puppet to eploy file to every host
        2. file + object storage(s3) + serrvice hosts poll file periodically
        3. configuration service(zookeeper) - discover cache hostys and monitor health,  each cache hosts registers and send heartbeats
            if hearts beats stops cache host is unregistered, cache client gets lists of cache servers from config service, hardest + operational cost is higher,
            but least maintenance
    
    Data Repliation
        1. gossip, epidemic broadcast, bimodal multicast - eventual consistency
        2. 2 or 3 phase comiit, paxos, raft, chain replication - strong consistency
    
    leader follower(master slave)replication:
        master + read replicas(in diff data centers)
        everytime connnection to replica and master breaks replica tries to reconnect to master
        all put to master, get both master and read replicas, with more replicas easy to deal with hot shards, scale by adding more read replicas
    
    leader election:
        configuration service(zookeeper, redis sentinel) - monitors leaders and followers, if lead died, it promotes follower to leader, its is a distibuted system of its own
            has od number of nodes to achieve quorom writes easier
    
    
    outlook
        perfomant - yes, LRU operations constant time, cache client picks cache hosts in log n time, comm is TCP/UDP so fast
        scalability - yes, we can create more shards, but some shards may become hot, some shards process much more requests than their peers resulting in bottleneck,
                        so we need to split hot shards into smaller shards
        high availability - no, to favour perfomance we replicate async, so no guarantee on consistency of data, if leader fails before replication data is lost
    this is acceptable for cache
    
    what else is important
    consistency - async replication gaurantees no consistency, get call from master may differ from read replicas
    clients can have diff list of cache servers - so clients can write data(into consistent hash circle) that no other clients can read,
            we can fix this with sync replication at cost of perfomance
    Data Expiration - include time to live atributes to expire data, we can wither passively expire and when someone tries to access the itemis expired
            actively expire - through a maintenance thread thats runs at regular intervals and removes expired items
            since billions of items are there, we cants iterate all items, so probablistic algorithms are used, random items are tested
    local cache + remote cache - imlement local cahe in cache client, we can use LRU ro google guava cache and hide complexities inside cache client
    security - we favor perfomance over security, usually accessed by trusted clients inside trusted envs and not to expose cache to internet if not required
                put cache server inside firewall, and encrypt data before storage and decrypt at cost of perfomance
    monitoring and logging - many teams can use, if they get perfomance degradation, tehy will come to us as potential sources, we shld be abel to answer
                      number of faults, latency, number of hits and misses, CPU and memory of cache hosts, network IO,
                      for logging capture details of every request to the cache, info like who and when access the cache, key, return status etc
    cache client - maintain list of cache hosts, pick a shard, handle remote call and failures, emit metrics, 
                    we can introduce a proxy to pick shard(twemproxy by twitter) 
                    we can let cache servers pick the shard and client can hit a random cache server(used by redis cluster)
    consistent hasing - problems 
            1. domino effect - when 21 server dies, its load is transferreed to next server and it dies causing chain reaction
            2. cache servers do not split the circle evenly - adding each server to circle multiple times, jump hash algorithms(google), proportional hashing(yahoo video)
    */
    
    
    class Node{
        private final String key;
        private String value;
        private Node next, prev;
        
        public Node(String key, String value){
            this.key=key;
            this.value=value;
        }
    }
    
    class LRU{
        private final Map<String,Node> map;
        private final int capacity;
        private Node head=null, tail=null;
        public LRU(int capacity){
            this.map = new HashMap();
            this.capacity = capacity;
        }
        
        public String get(String key){
            if(!map.containsKey(key)) return null;
            Node n = map.get(key);
            deleteFromList(n);
            setListHead(n);
            return n.value;
        }
        
        public void put(String key, String value){
            if(map.containsKey(key)){
                Node n = map.get(key);
                n.setValue(value);
                deleteFromList(n);
                setListHead(n);
            }else{
                if(map.size()>=capacity){
                    map.remove(tail);
                    deleteFromList(tail);
                }
                Node n = new Node(key,value);
                map.put(key, n);
                setListHead(n);
            }
        }
    }
}
