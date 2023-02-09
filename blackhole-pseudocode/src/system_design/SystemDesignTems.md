

System Design Terms:


Always start with simple solution and evolve in interviews and mention that
Everything has a cost, tradeoff, always remember tradeoffs, more knowledge and exp can help

functional requirements - define system behaviour or ser of operations, APIs
Non functioanal requirements - define system qualities like scalability, perfomance, availability, consistency, durability, maintainability, accuracy, security, testability, hardware cost etc

Authentication - verifies the identity of a user or service.
Authorization  - determines their access rights. basically permissions they have.
VIP - virtual IP, symbolic hostname, dns entries
Load Balancer LB - routes requests across number of servers
Front end web service - initial processing of requests like validation, authernication etc
SSL(secure sockets layer) -
TLS(transfort layer security) - protocal that aims to provide privacy and data integrity, decrypts requests and pass unencrypted request to downstream and also encrypt responses while sending themback.
       TLS on LB is expensive. Termination usually handled by sep proxy that runs as a process on frontend hosts.
Server side encryption - encrypts messages, stored in encrypte form and frontend service decrypts messages when sending to consumer
caching  - cache ifo like queue metadata,auth user data, most actively used queues to avoid db call
Rate limiting(throttling) - process of limiting number of requests that u can submit to APIs at given amount of time, avoids request overwhelings ddos
                            leaky bucket algorithm
request dispatching - call services though http requests, metadata service, backend service. 
                        islotate requests to diff services, one service failure should not affect other, bulk head, circuit breaker helps
request deduplication - when response to successful request fails to reach client this happens, 
                        matters for atmost once, exactly once delivery strategies, not to orry for atleast once strategy
                        caching request ids can help to void duplication
usage data collection - gather error and usage info for debugging and audits, like datadog


Service discovery:
    https://avinetworks.com/glossary/service-discovery/#:~:text=Server%2Dside%20service%20discovery%20allows,all%20within%20the%20service%20registry.
Service Discovery has the ability to locate a network automatically making it so that there is no need for a long configuration set up process. 
Service discovery works by devices connecting through a common language on the network allowing devices or services to connect without any manual intervention. 
(i.e Kubernetes service discovery, AWS service discovery)
in world of microservice, there are 2 types of service discoveries
    server side discovery - allows clients applications to find services through a router or a load balancer 
    client side dicscovery - the client is responsible for determining the network locations of available service instances and load balancing requests across them. 
                    The client queries a service registry, which is a database of available service instances
                    The client then uses a load‑balancing algorithm to select one of the available service instances and makes a request.

Service Registy(zookeeper):
    with client side discovery, every server isntance registers in some common place named service registry
    service registry is another highly available web service that performs health checks to determine health of registered instancess


Nosql databases
    types: column, document, key-value, graph

data replication:
    we must not lose data when we store them (high availability), when data is persisted we replicate it across diff data centers
        single leader replication - used to scale sql databases, master + several read replicas, writes go to master, read go to master + read replicas
        place in differen data centers for disaster(whole data center is down) cases
        data is sync or async replicated
        while leader stays alive followers copeies events and if leader dies one
        of the followers take over ,leader keeps track of all followers whether they are alive or falling behind, if follower dies, gets stuck or falls behind, 
        leader will remove it from list of followers
        multi leader replication -  method of database replication which allows data to be stored by a group of computers, and updated by any member of the group. 
                            All members are responsive to client data queries. The multi-master replication system is responsible for propagating the data modifications 
                            made by each member to the rest of the group and resolving any conflicts that might arise between concurrent changes made by different members.
                            multi leader repication is mostly used to replicate between  several data centers
        leaderless replication - cassandra replication, place nodes in differen data centers for availability(whole data center is down) cases
        data is sync or async replicated or based on quorom reads and writes
        https://stackoverflow.com/questions/48434860/master-less-model-in-cassandra-vs-master-slave-model-in-mongodb

Quorom writes:
    a write is successful when predefined set of replcas ack the write. odd number of nodes help achieve quorom easier

checkpointing in multithreaded env
    https://en.wikipedia.org/wiki/Application_checkpointing#:~:text=There%20are%20two%20main%20approaches,coordinated%20checkpointing%20and%20uncoordinated%20checkpointing.
    https://en.wikipedia.org/wiki/Two-phase_commit_protocol
    https://engineering.linkedin.com/blog/2017/01/asynchronous-processing-and-multithreading-in-apache-samza--part
    https://thenewstack.io/apache-samza-linkedins-framework-for-stream-processing/
    https://samza.apache.org/learn/documentation/0.11/container/checkpointing.html



TCP connection establishment and termination
https://www.tutorialspoint.com/what-is-the-tcp-connection-establishment#:~:text=Connection%20Termination%20Protocol%20(Connection%20Release,should%20be%20shut%20down%20alone.