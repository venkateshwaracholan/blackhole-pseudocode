/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.rate_limiting;

/**
 *
 * @author venka
 */


// rate limiting or throttling (limiting number of request a client can submit in a given amount of time)
//    requests submitted over the limit are either rejected or processed later 
// popularity, load testing, ddos, noisy neighbour problem

public class RateLimiting {
    
    /*
    
    can scalability solve this, auto scaling?
        auto scaling is not immediate
    
    
    can LB solve this with max connections and max thread counts?
        it is possible, it can reject or send it to queue.  lb cant distinguish operations in API and allow fast operations atleast. no knwoledge, no control
    
    do we need to throttle in individual instances?
        lb cant distribute load evenly and instances can become slow or over heated, so distributed systems are required and instances need to share info on 
        how manty req each of them processed
    
    Functional requirements
        boolean allowRequest(request)
    
    Non Functonal Requirements
        Scalability(handles large number of requests)
        highly Perfomant(low latency to make decision)
        Accurate(as accurate as we can get) 
    
    Additional
    failures/delay - if rate limiter cannot make decision quickly, due to failures the decision is always not to throttle
    ease of integration - every service can integrate to common rate limiter
    
    
    Rate Limiter instance:
    
    a rule specifies number of requests allowed for particular client per seconds, rules are defined by servic owners and stored in dbs
    
    throttle rules retriever - background process that checks rules service for updates, store rules in memory(throttle rules cache)
    rules DB - DB to store rules
    Rules service -   CRUD operations 
    throttle rules cache
    client identifier builder - auth info, remote ip, or combi attributes that uniquely identify a client. passes the key to arte limiter
    Rate limiter - check key against rules cache to get rules for client, if found checks if number of req made by client for time duration is less than threshold
                    if threshold not exceede req is passed for further processing else rejected
    
    rejection possibilities
        1. 503(service unavailable), 429(too many requests)
        2. queue it for processing later
        3. simple drop the request
                    
    iterviewer may be interested in
        rate limiting algorithm
        object oriented design(define calsses and interfaces)
        distributed throttling solution
    
    
    
    Token bucket algorithm
        bucket with tokens => max capacity, current tokens, refill rate
        a token for requests, and reject if no more tokens, tokens are refilled constantly
        Implemented below
    
    
    Classes and interfaces
    JobScheduler
        RetrieveJobScheduler - run retrieve rules talk, inti, start, stop scheduler, use scheduledExecutorService interface
        RetrieveRulesTask - makes remote calls to rules service, craete token buckets based on rules and stores in token bucket cache
    RulesCache
        TokenBucketCache - stores token buckets, to store buckets we can use a maps, concurrentHashMaps, or google guava cache
    ClientIdentifier
        ClientIdentifierBuilder - builds key based on user info, or ip address etc
    RateLimiter
        TokenBucketRateLimiter - retrieves token bucket for client, calls allowrequest method on bucket
                                calls client id builder to get key and uses key to get token bucket from cache 
    
    
    
    Distributed token buckets
        lets have 3 hosts 4 tokens each, lb will not ditruibuute load evenly,
        once tokens in one host are uses, we must allow hosts to share this info with each other, and subtract tokens used by other buckets
        4 request may hit 3 hosts at same time or network partition happens, so 12 requests procesed?
        until all host agree on tokens they have, request will slip, so we need to scale our cluster accordingly
        modifying currentTokens to allow to hold negative numbers, and requests wont be processed until it becomes positive
        so on average we process 12 requests in 3 seconds, but all 12 were p[rocessed in 1st second
    
    Communication between hosts:
        1. tell everyone everything, mesh toplogy +  zookeeper for heartbeats and addinga ndremoving of nodes, or we can use config files with hosts and deploy
            its not scalable
        2. Gossip protocol - based on the way epidemics spread, random peer selection and shares data(yahoo uses this)
        3. distributed cache cluster - redis, can scale its separately and share between teams
        4. coordination service + leader election - , reduced number of msg broadcasts within cluster, leaders asks hosts to send all info,
                                    then leader calculates and sends back final result, each node talks to only on or set of leaders, where each leader is responsible
                                    for range of keys, consensus algo like paxos and raft used to implement coordination service, maintainabilty of coor serv is overhead
        5. random leader election - multiple leaders elected, unnecessary overhead, but each leader has its correct view of overall rate.
        
    
    TCP - gaurantees delivery and order, accuracy
    UDP - no guarantee but fast, perfomance
        
    Integration as a library?
        1. run as part of service process - just the library, faster, resilient to interporcess comm, 
        2. rus as separate process(daemon) popular pattern - 2 libs, daemon + client + interprocess communication between daemon process and client, 
            client and daemon can be in diff languages, rate limited has its own memory so isolation(from bugs), service teams are cautious
    
    service teams inquiries for library
        how much CPU and memeory does lib consume
        network partiton?
        results for load testing for library
    
    daemon process pattern is used for auto service discovery, when hosts in cluster identify each other
    
    populariry, millions of buckets craeted and stored in memeory
        we can cleanup buckets from memeory if no requests pops in for some time, and bucket will be created again if request for client comes again
    failures
        daemon failed and other hosts can lose visibility of failed daemon, so it leavs the group, throttles independently, onmly less requests will be throttled
        same for network partition, 
    rules management
        self service tool for CRUD
    synchronization
        token bucket- use atomic refrences
        tokcen bucket cache - while cleanup and recraetion sync is required, use concurrentHashMap
        it will only become bottleneck for services with insanely large requests  per seconds rate
    wat to do with throttled req
        queue for processing later
        retry throttled request with exp backoff and jitter(heps separate retires)
        
    final outlook
        non func req met? 
        depends on no. of hosts in cluster, no of rules, request rate
        for cluster size less than 100 nodes, and no of active buckets is less than 10000, gossip over UDP is really fast adn quite accurate
        for nodes > 10000 we cant rely on host comm as  it becomes costly, so we need distributed cache, but it increases latency but scales, its a tradeoff
        
    
    */
    
    class TokenBucket{
        private final long maxBucketSize;
        private final long refillRate;
        
        private double currentBucketSize;
        private long lastRefillTimestamp;
        
        public TokenBucket(long maxBucketSize, long refillRate){
            this.maxBucketSize = maxBucketSize;
            this.refillRate = refillRate;
            currentBucketSize = maxBucketSize;
            lastRefillTimestamp = System.nanoTime();
        }
        
        public synchronized boolean allowRequest(int tokens){
            refill();
            if(currentBucketSize>tokens){
                currentBucketSize-=tokens;
                return true;
            }
            return false;
        }
        
        public void refill(){
            long now = System.nanoTime();
            double tokensToAdd = (now - lastRefillTimestamp)*refillRate/10e9;
            currentBucketSize = Math.min(currentBucketSize+tokensToAdd, maxBucketSize);
            lastRefillTimestamp = now;
        }
    }
}
