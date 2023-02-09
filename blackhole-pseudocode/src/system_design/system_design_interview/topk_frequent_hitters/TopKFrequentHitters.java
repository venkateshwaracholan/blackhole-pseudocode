/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.topk_frequent_hitters;

/**
 *
 * @author venka
 */

import java.util.*;

// top k frequent  hitters

public class TopKFrequentHitters {
    
    /*
    
    storing directly in db is not a good option for high scale, 100k to 1Ms requests per second
    databases can handle millions of requests per second, how to get top N
    scan entries, compute agg, order by agg, but these are expensive and slow
    
    return list of heavy hitters as close to real time, eg, list of most viewved videos for several minutes
        
    Functional requirements
        topk(k, startTime, endTime)
        get(key)
    
    Non Functonal Requirements
        Scalability(handles more data load: videos, posts, tweets etc)
        highly Perfomant(low resonse time for main operations)
        High Availability(data available on hardware and network partition) 
        Accuracy(as accurate as possible)
    
    precomputation will avoid heavy calculations in topKAPI
    in data sampling, we count only a fraction of events to compute, at the cost of accuracy
    
    start from single host,
        videoId -> HashMap + MinHeap (nlogk)
        impl below
    
    Multiple hosts
    
    LoadBalancer splits events to multiple hosts and then the toverall events are aggregated into a single storrage host
    but we are using too much memory
    so partitioning or sharding can help data fit in memory of single host
  
    Data partitioner
        responsible for routing videoId to its own host, then sompute local topK in each hosts, then we need to combine the topK from many hosts
        procesor host only passes k entries to storage host
        merge K sorted List can help
    
    streaming data is unbounded or inf, usres click videos every sec, 
    so processor hosts can accumulate data only for a short period of time(ex: 1 minute) then flush to storage host
    rememeber we can only store data fro top k heavy hitters and info of others are lost, as we cannot afford to store data abt every video in memory
    
    but to get 1 hour or 1 day eavy hitter? can we build 1 hour list from 60 1 minute list, there is no way to solve this precisely
    we need whole data set for hour or day, but we cant accumulate data for whole day in memory
    
    lets store all data o disk and use batch processing to compute topk with Map reduce
    
    along with data partitioning data replication is also req for high availability, so need to think abt rebalancing if new node is added or removed
    also need to deal with hot partition
    
    count min sketch uses fixed size memory to solve topk at the cost of accuracy
        height - is small with a  number of hash functions
        width - usually in thousands
    
    put:
        compute videoid hash with all hash func in height and inc that corres cell
        there could be collision
    get:
        among all call values of A we take minimum, because of collision some cells may contains over estimated values
        by using more no of hash functions we decrease error rate
    
    there are formuals to calc width and height based on accuracy we want, and prob with which we can reach that accuracy
    countmin sketch + heap k, we replaced hashtable that an grow big, with fixed size count min sketch(does not grow even if data is inc)
    
    API gateway
        Authentication, SSL termiation, rate limiting. request routing, response caching
        single entrypoint, aggregates data on the fly or via background process taht proicess logs and flushes based on time ot size, serialize data in compact binary format AVRO
        log generation for monitoring, audit and billing
        api gateway si a widely spead paractice 
        we can use the logs to count how many times video was viewed via background process that reads data from logs, 
        perfoms initial aggregate and sends data for further processing
        so, we create a buffer of memory, read log entries, craete freq table, buffer is fixed size, flushed when full or time out
        other option is to agg data on the fly without even writing logs
        or completely skip data agg on API gateway and send all data
        serializing with compact binary format saves us network IO  for higher request rates and let CPU pay the price
        so it depends on resources available, memory, CPU, network, disk io
    
    Distributed message queue
        kafka, aws kinesis, internally kasfka splits messages across several partitions and each partition can be placed on separate machines
        we dont have a pref on how data is partitioned so random partiton is enough
    
    Fast Path:
        we calculate heavy hitters approximately and resultys are available within seconds
    fast processor
        creates count min sketches for some short time interval and aggregates data
        we dont have to parition further as memory is fixed for count min sketch
        since we are storing data in memory, host could die, so data replication may be required to make our system highly available
        as count min sketch produces aporxx results we may lose data in rare cases if host dies as long as slow path doenst lose data
        and results are avaibale in some time. absense of replication simplifies the system, a good tradeoff to discuss 
        everys sevral sec, fast processor flushes data to storage
        since count min sketch has fixed size, nothing stops us from aggregating over several minites, this will jusst delay final results, another tradeoff
    
    storage
        merging several count min sketch is simply summing  
        servce infront of DB, sql, nosql, stores topk list for some time interval, data replication si required
    
    our pipeline decreased request rate on the journey, millions of users clicking vidos in current minute, everything reach api gateway cluster
    this cluster may be big with thousands of hosts, the preaggreagte data on each hosts for several seconds, so it decreased numberof messages to kafka
    so, fast processor is much small in size than API gateway cluster, and fast porocessor aggregates data further for another several seconds
    at storage we only deal with a fraction of requests compared to initial on gateway
    ths is imortant when we deal with stream processing and data aggregation
    
    slow path:
        we calc heavy hitters accurately, but takes time
        2 Mapdreduce jobs + distributed file system HDFS or object storage
        freq count mapreduce job, top k map reduce job 
        lets add a data partitioner and run mapreuce jobs in parallel
    data partitioner
        parses batches(made by api gateway) of events into individual events
        paritions data into chunks and deals with hot partitions
    
    distributed msg queue
        kafka, kinesis(takes care of data replication)
    
    parition processor
        aggregates in memory over time of several minutes
        generates files in HDFS(lets say for time int 5 mins)
        can also send aggregate info to storage service
    
    freq count mapreduce job
        merges data from HDFS to generate overall freq count
        can merge several 5 minture intervals to generate top k for hour
        input is set of files split into independent chunks which are processed by tasks in parallel
        recored reader parses data and offer to mapper as key value pairs
        mapper takes the key value pairs and stores a intermediate pairs
        paritioner takesintermeidate pairs and assigns patitions to group all values of each key and make that all values go to same reducer
        partitone data is written to local disk for each mapper
        during shuffling phase mapreduce takes output files produced by partitioners and download them to reducer machine via HTTP to group keys together
        reducer then takes each keys and iterates over all values and sums up
        output of reducer task is written in file system
    input, split, map, partition, shuffle and sort
    
    top k map reduce job 
        calculates top k from the data sent from freq count mapreduce job
        takes data from prev job and splits into chunks and send to mapper to calc local topk
        all individual top lists are sent to a single reducer that calculates final top k
    
    if accuracy is not concern fast path is cheap and easy to build
    if accuracy is important and we want results soon, then we need to partition data and aggregate in memory
    if accuracy is important and time is not a n issue, hadoop mapreduce is the way to go 
    
    APi Gateway should expose topK operation, routes data retreival tasks to storage service whih gets data from underlying database
    our fast path creates approx 1 minute topk lists, our slow path creates precise top k for 1 hour interval
    if user asks top k for 5 mins, then we merge 5 1 minute list
    we can give back precisely for 1 hour list from slow path, but cat precisely give back for 2 hours by merging 2 1 hour lists(as  it wont be accurate)
    
    what if API gateway dont have cpu for data pre aggregation? 
        we can send logs to storage system and process from there
    
    alternates to count min sketch?
        lossy counting, space saving, sticky sampling
    
    how big is k?
        in fast path we calculates top k for small intervals,  on slow path we send all mappers data to single reducer, so k cannot be aribrarily large
        but several thousands are probably okay, but tens of thousands may cayse perfomance degradations
    
    drawbacks?
        the architecture we built is lambda architecture -> approach to building stream processing apps on top of map reduce and stream processing engines
        we send events to batch system and stream processing system in parallel and we stitch results from both system at query time
        how to beat cap theorem with lambda architecture,
        lanmbda architecture is so complex for accuracy(slow path), tradeoff 
    
    can we use apache kafka and stream processing to solve this?
        yes and no, we can solve with kafka + spark on a single pipeline. but it doess what we did basically : data partitioning  + in memeory aggregation
        spark internally partitions data and calc topk lists for each partition using heap, and all these lists are merged in reduce operation
    */
    public List<HeavyHitter> topK(String[] events, int k){
        Map<String,Integer> freqTable = new HashMap();
        for(String event:events){
            freqTable.put(event, freqTable.getOrDefault(event, 0) +1);
        }
        PriorityQueue<HeavyHitter> heap = new PriorityQueue<>(Comparator.comparing(e->e.getFreq()));
        for(Map.Entry<String,Integer> entry: freqTable.entrySet()){
            heap.offer(new HeavyHitter(entry.getKey(),entry.getValue()));
            if(heap.size()>k)heap.poll();
        }
        List<HeavyHitter> res = new ArrayList();
        // sorted from min to max freq
        while(heap.size()>0)
            res.add(heap.poll());
        return res;
    }
    
    class HeavyHitter{
        private final String id;
        private final int frequency;
        public HeavyHitter(String id, int freq){
            this.id=id;
            this.frequency = freq;
        }
        public int getFreq(){
            return this.frequency;
        }
        
    }
}
