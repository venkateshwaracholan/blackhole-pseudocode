/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.distributed_counter;

/**
 *
 * @author venka
 */

// distributed counter - count things at large scale

public class DistributedCounter {
    
    
    /*
    count views on youtube, likes on fb, or even a set of metrics, monitoring apps, no of req, no of errors,avg response time
    counting likes,shares, reposts, ad impressiona and clicks, same ideas can be used to design monitoring systems when we need to count metrics
    when designing fraud prevention system we need to countnumber of times each credit card was used recently
    when building recomendation service, we use counts as inputs to ML models
    when we design whats trending service, we count different reactions : views, retweets, comments, likes
    
    interviewer wants to see how we deal with ambiguity, so ask clarifying ques
    problems are opn ended, we have to figure out what functions to focus
    
    options: SQL, noSQL, distributed cache, stream processing(kafka +spark) , public cloud stream processing(amz kinesis), batch processsing(hadoop map reduce)
    each has its own pros and cons
    
    what to ask?
        users - all viewers or per hour staticstics for video owner, or count is used my ML to generate recomendation, or for marketing team to genearte monthly reports
                realtime or not realtime? what data is stored
        scale - clue on how much data is coming to system and retrieved, how many read queries per second? how much data is queried retrieved?
                how many video views are processed by system? shld we deal with sudden traffic spikes?
        perfomance - what is expected write to read delay? how fast data must be retrieved form system? for fast we must count while write than read(or do minimal counting)
        cost - minimize development cost? we have to use open source, for low maintenance we shld try public cloud services
    
    think abt data, what data, how it gets in and out of system, ask and clarify
    
    
    Functional requirements
        countViews(videoId)
        -> countEvent(videoId, eventType)
        ->processEvent(videoId, eventType, function) eventType-> likes views, share function-> count, sum, avg
        processEvents(list of events)
    
        getCountViews(videoId, startTime, EndTime)
        -> getCountEvent(videoId, startTime, EndTime, eventType)
        -> getStats(videoId, startTime, EndTime, eventType, function)
    
    Non Functonal Requirements
        Scalability(handles more data load: videos, posts, tweets etc)
        highly Perfomant(low resonse time for operations)
        High Availability(data available on hardware and network partition) 
        Consistency
        Cost
    
    we need to think what data we want to store and how, define a data model
    what can we store?
        individual events - like videoID, timestamp, user info like country, device type, operating system
            pros    fast writes, on ret. we can slice and dice, filter based o attributes
                    if there is a bug we can always recalculate
            cons    slow reads, count all event for tot count slow, cost lot to store all raw events
        aggregate per time interval - we lose details but store agg for time interval
            pros   -fast reads, dont have to calculate each event, we can send data in realtime to downstream recomendations/trending services.
            cons   -can query only based on how we aggregated, requires data aggregation pipeline, preaggregate sdata in memory before storing in db.
                    hard or impossible to fix errors for a bug in aggregation logic
    
    expected data delay
        time between when evennt happened and when it was processed, if its no more than several minutes, we have to agg data on the fly(stream data processing)
        if several hours are okay, then we can stroe and process in background(batch data processing)
        we can also combine both options
    
    we can store raw events for several days or weeks only and purge old data, also calc and store in realtime for immediate usage
    best of both worlds: fast reads, agg data, recacl stats in case of bug, the price is complexity and cost, tradeoffs
    
    where we store data
        both sq and nosql can scale well
        how to scale writes? 
        how to scale reads? 
        how to make read and writes faster? 
        how not to lose data in hardware failures and network partition?
        how to achieve string consistency? tradeoffs?
        how to recover data in case of an outage?
        how to ensure security?
        how to make it exstensible for future changes?
        where to run (cloud or onpremise)?
        evaludate cost?
        
    
    disributed SQL database
        sharing or horizontal partitioning into chunks and storing them on several machines
        now, services need to knwo how many machines exist and which one to pick to store and retrieve data
    
    cluster proxy
        a cluster proxy that knw abt all sharding logic and database hosts, need to handle add/remove nodes and host deaths, networ partitons
        
    configuration service(zookeeper)
        which maintans health checks to all database hosts
    
    shard proxy
        cache query results, monitors health and publish metrics, terminate slow queries.
    
    data replication
        master shard + several read replicas, writes go to master, read go to master + read replicas
        place in differen data centers for disaster(whole data center is down) cases
        data is sync or async replicated
    
    vitess(based on above architecture)
        youtube built a databse solution to scale and manage lareg clusters of mysql instances
    
    
    Distributed NoSQl databases(apache casssandra)
        we also split data into shards(nodes), but instead of leader follower, each shard is equal, so we no longer need configuration service to monitor.
        so shards talk to each other and exchange info, to reduce etowrk load, not each shard need to talk to every other shard
        shards echange info to no more than 3 shards and info propogates through out the cluster, gossip protocol
        so, each shard knows about all shards, we no longer neer cluster proxy, lients can call any node and any node will route it to right node
        initially we can use round robin to chose random node or be smart and choose node closest to client in network distance
        we can use consistent hashing algorithm to pick the right shard
        so the coordinator node calls the correct node and write/read data, coordinator node can contact multiple nodes if we need data deplication
        and we can use quorom writes to decide if its a sucess. minimum number of nodes that need to agree
        same way there are quorom reads,response from replica nodes may vary, so minimum number of nodes that have to agree on the response
        cassandra uses version number to determine data staleness
    
    Note: In the image node 4 contacts node 2(G-L) and 3(M-R) for storing B, which is wrong, it should contact node 1 from different clusters living in diff data centers
            for quorom readsm writes and data replication
    
    data replication
        place nodes in differen data centers for disaster(whole data center is down) cases
        data is sync or async replicated or based on quorom reads and writes
    
    we chose availability over consistency which means we can show stale data than no data at all
    Sync data replication is slow, so async, in leader follower, follower may fall behind with old data , but this inconsistency is temporary as 
    all writes will propogate to replicas, eventual consistency
    cassandra extends eventual consistency by offering tunable consistency
    
    
    how we store data or data modelling:
        SQl- think in terms of nouns, craete tables and foreign keys to reference related data(normalization)
            video info, views for time interval, channel info
            data is normalized(minimize data duplication), join tables and query
            storing data like video name in one place, changes in video name has to be updated only in one place
        NoSQL - think in terms of queries and denormalization is perfectly normal
            we store everything required for report together, 
            instead of adding rows, we add columns for time interval
    cassandra is a wide column dtabase that supports async masterless replication
            we can use cassandra as it is fault tolerent and scalable, both reeads and write throughputs increase as we linearly add machines
            it also suppoerts multi data center replication and works well with time series data
        types: key value, columnar, document, graph
            
        mongo db(document oriented) uses leader based replication
        hbase (column oriented) uses master based replication
    
    
    data processing:
        for users who open video, we have to show views count immediately, so calculate on the fly in real time
        for owner who open statistics, we have to show per hour counts
        process events and increment total and per hour counters
    
    for scalability - partition
    for availability - data replication over several data centers
    for perfomance - in memory and minimize disk reads
   
    data aggregation basics
        dont pre aggregate - we update counter for every video view event
        preaggregate - we accumulate data in memory for a time period and update agg value to database periodically
    
    processing service
        push - some service sends events synchronously to processing service
        pull - pulls events from temporary storage, pull has more advantages - easy to scale, fault tollerant, as processing serv may crash and we may lose data
    
        if processing machine crash before sending data to db, data is lost, so we choose pull model with a temp storage
        pulls events and udpates in memory counters, if machine crashes we can process them again
        temp storage is a distributed msg queue(kafka)
        checkpointing - fixed order allows us to use offsets, every time event is read from storage offset moves, we can use commits too
            once evens are processed successfully we can write checkpoints to some persistent storage, if machine dies, another machine can start from checkpoint
            checkpointing is very important for stream processing
        partitioning - events can be paritioned to multiple queues, partitioning allows us to parallelize processing, more events more partitions
        
        partition consumer - establishes and maintains TCP connection with partition to fetch data, polls data, 
                            deserializes from byte arry(we can use AVRO binary format too) to objects, consumer is usually single threaded
                            but can implement multithreaded access, but checkpointing becomes harder, also hard to preserve order
                            also helps to eliminate duplicate events via distributed cache that stores unique event identifier for last 10 minutes
    
        aggregator  -hastable that acc data for some time, periodically we stop writing to current hash table and create new ones, then old has table is sent to 
                        internal queue for further processing so that can wen process these by multiple threads
                        
        internal queue - by sending data to internal queue, we decouple consumption and processing, we can also put internal queue before aggregator
    
        database writer -  it can be a single threaded or multithreaded component, takes msg from internal queue and stores preaggregated view counts to db
                            single threaded version makes checkpointing easier, but multithreaded  version increases throughput
                            dead letter queue - we can put messages in dead letter queue if database is unavailable or slow
                            
        dead letter queue concept is widely sused to preserve data in case of downstream services degradations,we cal also store undelivered messages to local disk
        data enrichment - if we are using cassandra, events wont contain video name or channel name, but we need this info, so use a embedded database to avoid remote calls
        linkedin uses embedded databases for additional info on people who viewed your profile
    
        state management - we keep counters in memeory for some time, either in in memeory store or internal queue, since inmemeory, what to do when machine fails
                        we can use checkpoint and reprocess event one more time, this is great if we store data in memory for short time and state is small
                        other way is to store the state in durable storage periodically, so that new machine can reload state and start from there
    
    
    API gateway - single point of entry that routes clients requests to backend systems, counter service may be one of the backend systems
    partitioner service client(is the API gateway):
    
    blocking vs non blocking IO
      Blocking IO
        when client makes req, the server processes and sends response, the client initiates connection using sockets, the socket that handles connection
        is blocked, this happens in a single thread, so the thread that handles connectio is blocked, so when another request comes wer need to create 1 more thread
        blocking system create one thread per connection, modern multicore machines can handle hundreds of connections each
        but if server experiences slow down, number of active connections and threads increases, when this happends machines can go into death spiral and 
        the whole cluster of machines may die, this is exactly why we need rate limiting solutions to keep systems stable  during traffic peaks
        blocking systems are easy to debug, as we have a thread per request we can easily track progress of request, by looking into thread stack, exception pops
        up the stack and are easy to catch and handle, we can use thread local variables
      Non Blocking IO
        we can use a single thread to handle multiple concurrent connections, server queues the rrquest and action IO is then processed at some later point.
        piling up requests in queue are far less expensive than piling up threads, so non blocking systems are more efficient and has higher throughput
        peice of non blocking system is increased complexity of operations, hard to debug, catch exceptions, track requests and no thread local variables
        
    buffering and batching
        there are thousands of vid views happening on yt every sec, to process all requests API gateway cluster has to be gbig in size, thousands of machines
        when we pass each ind video event partitioner service cluster has to be large too, we have to combine events together in a single request, this is batching
        we put events in buffer and send periodically or hen buffer is full,
        pros - batching increases through put, helps to save on cost,request compression is more effective
        cons - can introduce complexity in both client and server side, for example, when partitioner serv processes a batch and several events fail, while others secceeded
               should we resend whole batch or only failed events
    
    Timeouts
        defines how much time a client can wait for response from server
        connection timeout - how much time client is willing to wait to establish a connection, this val is rel small, 
                            10s of ms becaus ewe only try to establish connnection and not process request
        request timeout - happens when request processing takes too much time and client is not willing to wait any longer, 
                        to choose request timeout we need to analyse latency percentiles, for ex we measure latency of 1% of slow requests and set this value as req timeout
                        means about  1% of requests will timeout, what to do with failed requests, retries, retry may hit a better machine increasing chances to success
    Retries + exp backoff and jitter
        we have to retry smart with exp backoff and jitter, otherwise all  clients retry at same time and creates retry storm event and overloads server
        exp backoff increases waiting time between retries up to maximum backoff time and jitter adds randomness to retry intervals to spread load
        if we dont add jitter, backoff algo will retry at same time
    
    circuit breaker
        even with exp backoff and jitter we may be in danger of too many reties when partitioner service is down or degraded
        circuit braker stops client from repeatedly trying to execute an operation that is likely to fail, we simply calc how many req failed recently 
        and if error threshold is exceeded, we stop calling downstream services, some time latr limited umber of requests are allowed to passthrough and if success
        prev assumed fault has been fixed, we allowe all req from this point and start counting failures again from scratch
        drawbacks - makes the ssytem more difficult to test, hard to set proper error thresholds and timers, tradeoffs
    
    
    
    LB - distribute events evenly(ideally) to partitioners :
    
    hardware - network devices we buy from known orgs, powerful machines with many cpu cores, memory and are optimized to handle very high throughput
                millions of requests per second
    software - software we can install on hardware of our choice, we dont need big fancy machines and software lb are opensource, also lb is available 
                as public cloud service(AWS ELB)

    network protocol
        TCP LB - simply forwards network packets without inspecting the content, establish a single end to end tcp connection between client and server
                super fast and handle millions of requests per sec
        HTTP LB -  Lb gets http request from client, terminates connections from client, establishes new connection to server,sends request to server
                   it can inspect content and make a LB decision based on the content, based on cookie info or header

    Load balancing algorithms
        round robin - distributes requests in order across list of servers
        least connections algorithms - sends requests to server with lowest number of active connections
        Least response time algorithm - sends request to fastest server with fastest response time  
        hash based algorithms distribute req based on a kew we define such as client id ip or request url etc
    
    how does paritioner srvice and Lb knw each other?
    how Lb guarantees high availability? is Lb single point of failure?
    
    DNS - domain name system,  phonebook of internet, maintains a directory of domain names and translate them into Ip addresses
            we register our partition service in DNS ex: partitionservice.domain.com and associate it with ip of LB, so when client hits domain name 
            requests are forwarded to LB, 
    LB ip mapping
        for Lb to knw abt partitoner service, we explicitly tell the Lb ips of each machine
        both software and hardware Lb provides API to register and unregister servers
        Lb also need to knw which servers from registered are healthy and unavailable, this way LB ensures traffic is routed only to healthy servers
        and if unhealthy server is identified Lb stops sending traffic to it, it will resume routing when it detects the server is healthy again
           
    Lb high availability
        lb uses a concept of primary and secondary nodes, primary accepts connections and serves request, while secondary monitors primary
        if for any reason primary is unavailable secondary takes over, primary adn secondary also live in diff data centers in case one data center goes down
    
    
    Partitioner service :
        gets req from clients, looks inside request to retrieve events(from batched events batched by part serv client)  and routes events to some partitions
        partitions are web service thats gets and stores messages on disk in form of  append-only log files, so we have ordered sequency of messages ordered by time
        ont a single large file, but ba set of log files with predefined size,
        partition service has to use a partition strategy that decides which partition gets what messages
       
    partition strategies:
        1. simple strategy is to calculate hash function based on some key,ex: video identifier ans choose a machine based on hash
        but ths strategy wont work for very large scale systems as it may lead to hot partitions
        exacmple: when we have popular video or set of vids, all view events go to that partition   
        2. consistent hasing ring
        
    Deal with hot partitions:
        1. one approach to deal with hot partitions is to include event time for example minutes into partition key, all video event with current minute are forwarded
        to some partition, next minute all events go to diff partition
        within 1 minute interval, single partitio gets a lot of data, but over several minutes data is spread among partitions
        2. another solution to hot partiton is to split hot partition into two new partitions, to get idea how this works rememebr consistent hashing
            and how adding new node ion consistent hashing ring, splits range of keys into 2 new ranges
            and to push this idea further we may explicitly allocate partitions for popular video channels, all events form these channels go to those partitions
            and evenst from other channel never go to that partition
    
    Service discovery:
    to send messages to every partiton, partitioner srv needs to knw abt all partitions, so we need service discovery
    in world of microservice, there are 2 types of service discoveries
        server side discovery - allows clients applications to find services through a router or a load balancer 
                    clients knw abt LB, Lb knw abt all part. server instances, but we dont need Lb between partitioner serv and partitons   
        client side dicscovery - the client is responsible for determining the network locations of available service instances and load balancing requests across them. 
                        The client queries a service registry, which is a database of available service instances
                        partitioner srv itself acts as a Lb for distributing events across partitions, this is example for client side discovery pattern
                        with client side discovery, every server isntance registers in some common place named service registry
                        service registry is another highly available web service that performs health checks to determine health of registered instances
                        clients can query service registry and obtain list of available servers, ex zookeeper(service registry)
                        in our case each partition registers in zookeeper, and eaach partitioner service queries zookeper to get list of partitions
    
        one more option to service discovery is similar to what cassandra does, we mentioned before nodes talk to each other, so everynode in cluster knws abt other nodes
        it means clients only read to contact one node to get info about whole cluster and and contacting while this node it becomes the coordinator node 
            
    data replication:
    we must not lose events when we store them in partition(high availability), when event is persisted we replicate it across diff data centers
        single leader replication - used to scale sql databases
        multi leader replication - multi leader repication is mostly used to replicate between  several data centers
        leaderless replication - cassandra replication
    
        lets choose single leader replication, we always write and read from leader only, while leader stays alive followers copeies events and if leader dies one
        of the followers take over ,leader keeps track of all followers whether they are alive or falling behind, if follower dies, gets stuck or falls behind, 
        leader will remove it from list of followers
    
        we can also use quorom writes   
        when partitoner service makes calls to partition, we may send response as soon as reader persisted or
        only when msg is replicated to specified number of replicas
        whn we write to leader only we may still lose data if leader goes down before replication, when we wait to replicate durability increases but latency will increase
        + if required numbe od nodes are unavailable quorom fails and availability suffers
        TRadeoffs , durability vs perfomance
    
    message formats
        texual - xml,json,csv, human readable
        binary - AVRO, thrift , protocolbuffers , fast but not human readable, compression, used in realtime processing systems
                msgs compact and faster to parse and reduce netwok load
               msg can contain several attributes, videoid, timestamp, channel info and when represented in json every message contans field names
               whcih increases total msg size, binary formats are smarter but requires a schema to serialize and deserialize
               apache thrift and protobuf uses field tags instead of field names, tags are nummbers that acts as aliases for field names
               tags occupy less space when encoded, schemas are crucial for binary formats, msg producer need to knw schema to serialize data
               and msg consumers need schema to deserialize binary data, so schemas are stored in some shared databases
               schemas will change over time, 
               apache AVRO is a great choice for our counting system
                   
    
    Data Retrieval path:
        when users open video on youtube, we need to show total views count, to build video web page, several web services are called
        a web servics to retrieve info abt video, comments, another for recommmendation, among them our query web service is also there which is resp for video statistics
        all these web services are typically hidden behind API gateway service, single entrypoint, then routes client requests to backend services
        so request land on query srvice and it can retrieve total count directly from database, total views count is a simple query, acess a single value from databse per video
        it beomes interesting when users retrieve time series data, sequence of data points ordered in time
        for example when channel owner wants statistics for the videos, as discusse, we agg data in database for some time interval, every hour for every video
        so this data grows over time, solution is known
        monitoring systems aggregate data for 1 minute and even 1 second, so data must be huge, so we cannot afford to store data at this granularity for long period
        solution is to roll up data as it gets old, for ex:we store perminute count for several days after one week, per minute data is aggregated into per hour data
        and we store per hour count for several months, then we roll up counts even further , data older than 3 months is stored with 1 day granularity
        trick here is we dont need to store old data in database, we can store data in db for several days and store old data in object storage(s3)
        Hot storage - represenst frequently used datathat must be accessed fast
        cold storage - doesnt require fast access, represents arhived and infreq accessed data
        data federation - query service does data federayion,it may need to call several storages to fulfil request, recent data from db and old data from object storage
                            then stitch data togeteher, we should store query results in a distributed cache, helps improve perfomance
        
    
    final OutLook:
        3 users opened vid A and API gateway got 3 req, 
        partitioner serv client(component lives in API gateway)  batches all 3 events into single request to partitioner serv domain
        this request hits LB first and it routes it to one of the partitioner serv. instances.
        partitioner serv gets all 3 events from request and sends them to some partition
        all three events end up in same partition as we partition based on videoID
        processing service
            parition consumer reads all 3 events from partition one by one and sends them to aggregator
            aggregator counts messages for 1 minute period inmeory and flushes calculated values to internal queue
            database writer picks counts from internal queue and sends it to the database
            in database we store per hour count and total count for each video, so we add 1 minute count to per hour count as well as total count
        during data retrieval, when user open vidoe A, API gateway sends request to the query service, query service checks the cache
        if data not present or expired, we call database, count is then stored in cache and retured to user
    
    Technology stack:
        when we design sysrtem we dont need to reinvent the wheel, we rely on well regarded technologies either open source or commercial, public cloud services
        
        client side(API gateway):
            netty - high perf non blocking io framework for developing network apps, both clients and servers
            Netflix Hysterix, poly  - simplifies implementation of many clientside concepts, timeouts, retries, circuit breaker pattern
    
        LB:
            citrix netscaler - famous hardware LB
            Nginx - software LB, 
            Aws ELB(elastic load balancer) if running in AWS cloud
    
        partitioner service and partitions
            Kafka
            Amazon kinsis - public cloud kafka
    
        Processing service:
            apache spark or flink - to process and aggregate evens in memeory, we can use these stream processing frameworks
            kinesis data analytics - public cloud stream processing
        
        storage:
            apache cassandra(both are wide column)
            apache Hbase - popular for time series data
    
            optimised to handle time series data:  influx db
    
            store raw events in temporarily to recalculate counts in case of error or if customer needs to run adhoc queries
            we can store events in apache hadoop or public cloud data warehouse such as AWS redshift
    
            and when we roll up data and need to archive AWS s3 is a natural choce
    
        Vitess: database solution for scaling and managing large clusters of mysql instances 
                vitess has been serving all youtube database traffic since 2011
    
        distributed cache: for msg deduplication and caching read data queries
            redis
            
        dead letter queue: temp queue undelivered messages,
            open source message broker rabbit MQ
            public cloud - amazon SQS
    
        data enrichment(store vidoe and channel info locally for cassandra and inject info in realtime) 
            rocksdb, high perfomance embedded database for key values
            
        for leader election for partitions and to manage service discovery
            use apache zookeeper, which is a distributed configuration service
    
        service discovery alternatives
            eureka web servce from netflix
    
        monitoring systems
            public cloud - aws cloud watch
            open source - elastic search, logstash, kibana (ELK for short)
    
        Binary msg formats
            Thrift, protobuf, AVRO
    
        partitioner serv to partiton, hashing algo
            murmur hash
    
    
    the interviewer wants to see if we know and understand tradeoffs
    there are always several options to choose from
    knwo your options and know the pros and cons(tradeoffs)
    
    
    how to identofy bottlenecks?
        we need to load test, test system under heavy load, perfomance testing
        Load testing - when we measure behaivour of system under specific expected load, we want to undestand if system is scalable and can handle the load we expect
                        for ex two or three times increase in traffic
        Stress testing - test beyond normal opreation capacity often to breaking point, identofy breaking point, which component will start to suffer first
                        and whatb resource it will be, memroy, CPU, network IO, disk IO
        soak testing - test systems in production load over extended period of time, we want to find leaks in resources like memory leaks
    so generating high load is the key to test
    tools like apache jmeter can be used to generate desire load
    
    How do u make sure systems are running healthy?
        all components of our system must be instrumented with monitoring of their health, metrics dashboards and alers should be firends all the time
        metric is a variable we measure like error count, processing time etc, dshboards provides summary view off services core metrics
        alert is a notification sent to service owners in reaction to some issue happening in service
        4 golden signals of monitoring
            latency, traffic, errors, saturation
    
    how to make sure system produces accuarate results?
        thsi becomes critical when we not just count views, no of times as was played in video for billing, charge ad onwers and pay money to video owners
    this is addressed by building an Audit system
    Audit systems: 
        weak -  continuously running end to end test, we generate several video view events in the system and call query service and validate return value equals expected value
                ths simple test gives us high confidencye that system counts correctly, easy to implement and maintain such tests,
                but this test are not 100% reliable, what if our system loses evenst in some arre scenarious and weak audit system may not identify thsi for long period of time 
        strong - strong audit system calc video views usinga  completely different path than our main system, we store raw events in hadoop, and use map reduce to count events
                and compare results of both systems, having 2 systems do same tng may seem like an overkill right? but this is a common practice, lambda architcture
    lambda architecture - key idea is to send the events to a bacth system and a stream processing system in parallel and 
    stitch together results from both at query time
    
    we should use a batch processing framework like mapreduce when latency si not an issue,
    and use stream processing system if latency is a concern, but do not do both smae time unless absolutely necessary
    
    lets say we have a hugely opular video. will it be become a bottleneck?
        hot partitions - spread events coming for popular videos across several partitions, otherwise single consumer from single hot parition may not be 
                        able to keeep up with load and may fall behind
    
    what if processing service cannot keepup with speed of new meessages that arrive?
        may be due to number of events is huge,  or processing single event is complicated or time consuming
        we batch evenst and store them in object storage(s3),everytime we persist a batch,we send msg to msg broker(sqs)
        then we can have a big cluster of ec2 machines that retrieve messages from sqs, read corresponding batch from s3 and process events
        this is bit slower than stream processing, but faster than batch processing
        
            
    Summary
        functional requirements:
            start with requirementsclarification, functional APIs, define apis, what exactly our system is supposed to do.
            write down verbsa dn think abt imput params
            we can make several iterations to brush up the APIs
        Non functional requirements
            figure out qualities are important
            open list of non functional requirements on wiki and read
            focus on scalability, availability, perfomance, 
            other popular choices are consistency, durability, maintainability, cost, complexity, security
            try to pick not more than 3 qualities
        High level design
            outline a high level architecture, draw key compoenents on the whiteboard
            think about how data gets in and out of system
            what data is stored inside the system
            draw these components on whitboard, its okay to be generic, details will follow
            drive the conversation, goal si to get understanding of what component to focus next and interviewer will help
        Detailed design:
            next stage is to dive deep into several of thsoe components, interviewer will help us understand what comonents to focus on
            while designing specific components start with data
            how its stored, processed and transfered
            knwledge is critical, use fundamental concepts of system design and knwo how to combine these concepts we can make small incremental improvements
            apply relevent technologies along the way
        bottlenecks and tradeoffs
            listen carefully to interviewer
            questions will hint us abt bottlenecks in system
            knwledge of different tradeoffs, pick and apply proper ones
            
    */  
        
}
