/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.notification_service;

/**
 *
 * @author venka
 */

// Distributed Notification service - KAFKA,  - msg is sent from publisher to subscribers subscribed to a topic


public class NotificationService {
    
    /*
    use case examples
        when credit card exceed limit, service monitoring system alerts, 
    
    limits of sync communication
        producer calls each sub in order and waits for response
        hard to scale when num of subs and num of msg grow
        hard to extend for diff types of subs
        
    
    Functional requirements
        create topic
        publish(topic, message)
        subscribe(topic, endpoint)
    
    Additional:
        delete topic API (delete is contoversial so most apps expose it via terminal)
        delete message API
        avoid duplicate submissions
        security requirements
        ordering guarantees
    
    Non Functonal Requirements
        Scalability(handles more load, more topics, pub, sub and messages)
        highly Perfomant(low response time for main operations)
        High Availability(survives hardware and network partition)
        Durable(once submitted, data is not lost and delivered to each sub atleast once)
    
    Additional
        security
        operational cost
    
    
    Load Balancer LB - routes requests across number of servers
        LB single point of failure? limit to throughput and number of bytes they can transfer?
        to solve, LB has primary and secondary nodes
        primary accepst connnection and serves requets, seondary monitors primary.
        if primary not able to accept connecton, secondary takes over.
        multiple VIPs, VIP partitioning can help, in dns we assign multiple Address records to same dns name
        requests are partitoned across multiple LBs
        many LBs in diff data centers can improve availability and perfomance
    
    Front end web service:
        light weight, stateless service deployed acroos several data centers
    actions:
        request validation - (basically checks like req params like queuename present and data falls in acceptable range, msg size limits)
        authentication/Authorizaation 
        TLS(SSL)termination
        server side encrypting - encrypts messages, stored in encrypte form and frontend service decrypts messages when sending to consumer
        caching  - cache ifo like queue metadata,auth user data, most actively used queues to avoid db call
        Rate limiting(throttling) - process of limiting number of requests that u can submit to APIs at given amount of time, avoids request overwhelings ddos
                                  leaky bucket algorithm
        request dispatching - call services though http requests, metadata service, backend service. 
                               islotate requests to diff services, one service failure should not affect other, bulk head, circuit breaker helps
        request deduplication - when response to successful request fails to reach client this happens, 
                                matters for atmost once, exactly once delivery strategies, not to orry for atleast once strategy
                                caching request ids can help to void duplication
        usage data collection - gather error and usage info for debugging and audits, like datadog
    
    whats inside frontened service host:
        reverse proxy - ssl termination, compression like gzip, return error codes lik 503 when frontend service is slow or unavailable.
        frontend service - stores and acess metadata service, it may use local cache like google guava or LRU cache.
        local disk - write logs, service health metrics etc
        agents - service logs agent, metrics agent, audit logs agent
        
        metrics agent - no of req, errors, latency etc for monitoring
        audit logs agent - process info on who and request was made to specific API
        these agents are resp for data aggregation an transfering to other intergration systems
        
    Metadata service:
        simplifies maintenance and extensible to make changes for future
        acts as a caching layer between db and frontend service
        many reads, few writes
        2 STRATEGIES:
        1. metadata service is partioned by data, stores chunk of data in each node, frontend interacts with zookeeper(configuration service) to know 
            which metadata service to call using consistent hashing rings, everytime new node is added, zookeper remaps hash key ranges
        2. metadata service is partioned by data but frontend doesnt know which metadat service to call, calls a random metadata node 
        and request are forwarded to right node. each metadata service nodes can communicate with gossip protocol, 
        each frontend nodes knw metadata service nodes to contact
    
    Temporary storage service:
        fast, highly available and scalable
        guarantee data persistence and ensure durability, msg has to survive unavailability of subscribers
        4 options
        database - we dont need acid, and we need to scale reads and writes, so nosql, column or keyvalue apache casssandra, amazon dynamo db
        in memory - if persistence is supported, redis
        distributed message queues - kafka, amazon sqs
        stream processing platforms - kafka, kinesis
    
    whats inside sender:
    message retriever - pool of threads tries to read data from temporary storage, keep track of idle threads and adjust number of msg retrievals dynamically
                        semaphore can help us limit senders bombard temp storage service, semaphores are like permit flags, thread holding this permit can process
                        return it after completion
    meta data service client - talks to metadata service and get subscriber info instead of passing this info from storage service, helps with large number of subs
    
    task creator - creates and schedules single msg delivery task, create pool of thread with threadpoolexecutor along with semaphore to keep track of available threads
                    if we dont have enough threads, we can postpone or stop msg processing and return back msg to temp storage, 
                    another host with enough threads may process it. this helps us handle slow sender host issues
    tasks executor -  responsible for msg delivery to single sub, tasks may deligate actual delivery to othe microservices like
                       http endpoints, email,sms, mobile push notifications
    
    
    will msg be sent to users as spam?
        we need to register sub, sub need to confirm and agree to get notification from service, 
        upon registration we send a confirmation msg to sub hhtpo endpoint or email, onwers need to confirm subscription request
    
    dulicate messages?
        can be handles in frontend service with request id caching at publisher level
        but retries at sender level caused by issues at subscriber level or networ issues can cause duplication, so subscribers also need to manage ths
    
    retries
        retry guarantees atleast once delivery, retires for several hours, days, until max retries are reached
        other option is to store failed msg in system monitored by subscribers, then subscribers decide what to do, retry option to subs
    
    message order FIFO?
        no order gurantees, delivery tasks can be executed in any order, slower sendeers may fall behind
        msg delivery may fail and thus arrive in wrong order
    
    security
        only auth pubs can publish and only registered subs can receive them, 
        encryption using SSL over HTTPS protect msg in transit
        encrypt msg while storing them
    
    monitoring
        setup monitoring for every microservice
        give customers ability to tract state of their topics like number of msg waiting for del;ivery, num of msg failed to deliver
        so integ with monitoring system is required
    
    final outlook
        scalability - yes, when load increases,we can add more  LB, frontend hosts,metadata serv cache shards, temp storage and senders.
                        every comp is horiziontally scalable. sender has potential for vertical scaling, more powerful hosts can execute more delivery tasks
        High availability - yes, as there is no single point of failure, eeach comp is deployed across several data centers, 
                            hosts may fail and network partition may happend but redundancy is present
        highly perfomant - depends on impl, hardware and network setup. each microservice needs to be fast and run in high perf data centers.
                            delegated frontend service tasks to async agents, metatdat sevice is distributed cache access from memory, 
                            sender splits into granular tasks
        durability - data replication at temporary storage ensuresredundancy and msg is not lost during transfer from producer to subs
    */
    
}
