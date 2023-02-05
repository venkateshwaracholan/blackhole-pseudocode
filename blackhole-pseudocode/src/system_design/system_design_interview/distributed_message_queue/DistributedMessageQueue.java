/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.distributed_message_queue;

/**
 *
 * @author venka
 */

// Distributed message queue - KAFKA, Amazon SQS, rabbitMQ - msg is sent to one consumer only(unlike  a topic in notification service)
public class DistributedMessageQueue {
    
    /*
    Functional requirements
        sendMessage(queue,message)
        receiveMessage(queue)
    
    Additional:
        create, delete queue API (delete is contoversial so most apps expose it via terminal)
        delete message API
        avoid duplicate submissions
        security requirements
        ordering guarantees
        
    
    Non Functonal Requirements
        Scalability(handles more load, more queues and messages)
        highly Perfomant(low resonse time for main operations)
        High Availability(survives hardware and network partition)
        Durable(once submitted, data is not lost)
    
    Additional SLAs
        minimum throughput
        minimize hardware and operational cost
    
    
    Queue Meta data
        name, creationdate and time,owner etc
    
    High level Architecture
        VIP - virtual IP, symbolic hostname, dns entries
        Load Balancer LB - routes requests across number of servers
        Front end web service - initial processing of requests like validation, authernication etc
        Metedata service - a facade, dedicated web service for hanling calls to database
        Backend  service - message persistence and processing 
        
    
    VIP and load balancer:
        requessts hits domain name -> req tranfer to VIPs registerd in DNS -> resolves to LB -> front end hosts
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
    
    Metadata service:
        simplifies maintenance and extensible to make changes for future
        acts as a caching layer between db and frontend service
        many reads, few writes
        strong consistency is preferred but not strictly required
        3 strategies:
        all metadata service holds same data
        metadata service is partioned by data, stores chunk of data in each node, frontend knows which metadata service to call using consistent hashing rings
        metadata service is partioned by databut frontend doesnt know which metadat service to call, calls a random metadata node 
        and request are forwaqrded to right node.
    
    backend service
    options:
        1 leader follower instances - leader is responsible for data storage and replication, and clean up after consumed, 
                    metadata service helps identify leader partitioned for queue, use zookeeper(in cluster manager) for leader election and management, 
                    it is responsible for maintaining mapping bet. queues, leaders and followers, manages queue assignment within cluster of lead. foll. instances
                    maintains hosts in the cluster, monitors heartbeat from hosts, deals with lead. foll. failures
                    splits queues into partitions and each partition gets a leader
        2 small clusters of independent instances (out cluster manager)- random instance in the cluster is responsible for data storage and  replication, and clean up after consumed,
                            metadata service helps identify cluster partitioned for queue. manages queue assignment across cluster,
                            maintains list of clusters, monitors cluster health, dals with overheated cluster meaning new queues wont to assigned to clusters with limits reached
                            splits queues across several clusters
                                            
        
    
    Message deletion
        1. In Kafka, not to delete messages right after it was consumed and let consumers deal with it, use offets and dleete mesages after several days with a job
        2. i AMazon SQS, msg not deleted immediately, but marked invisible and consumer needs to call dlete msg api.
    
    Message replication
        sync - waits until msg is replicated to other hostsa dn success response is returned to producer only after replication is fully complete. high durability, high latency for send msg operation
        async - success response is returned to producer as soon as msg is stored in single backend host. msg is later replicated to other nodes. highly perfomant but no guarantee that msg will survive.
    
    Message delivery semantics
        atleast once - messages are never lost, but may be redelivered
        at most once - msg may be lost but never redelivered
        exactly once - msg is delivered once and only once
    
    points of filures
        producer may fail to deliver or deliver mutiple times
        data replication may fail
        consumers may fail to retrieve or process the message
    so most support atleast once delivery as it provides a good balance between durability, availability and perfomance
    
    consumer
        pull - consumer constantly send retrieve msg req and if new msg available it is consumed
        push - consumer is notitfied as soon as new msg arrives
        
    in distributed system it is hard to maintain FIFO, so most either 
        1 doesnt gaurantee a strict order
        2 have limitation around throughput
    
    security
        encryption usig SSL over HTTPS helps to protect mesages in transit
        we also store message in encrypted form in backend hosts
    
    monitoring
        monitor health of componentls like queuemetadata, frontend and backend services
        give customer ability to track state of their queues
    each service has to emit metrics and write log data, use them to show dashboards and setup alerts for both us and customers
    for this Integeration to monitoring service is required
    
    
    final outlook
        scalability - yes, when load increases,we can add more  LB, frontend hosts,metadata serv cache shards, backend clusters and hosts.
        High availability - yes, as there is no single point of failure, eeach comp is deployed across several data centers, 
                            hosts may fail and network partition may happend but redundancy is present
        highly perfomant - depends on impl, hardware and network setup. each microservice needs to be fast and run in high perf data centers.
        durability - data replication ensures msg are not lost during transfer from producer to consumer
    */
    
        
}
