
package system_design.concepts;

public class Basics {
  
}


/*
Month seconds Numbers:
1 month = 2.5 million seconds


Clarifying Question to ask interms of scalability:


List all requirements with clarifying questions:
1. operations
2. additional features


Bandwidth estimation:



Database:
Data Size estimation in bytes:




API server/ App server:

load:
500M per month => 200 per socond
read to write ratio  - 10:1, 100:1
assuming 
no. of concurrent write requests per seconds - 200
no. of concurrent read requests per seconds - 2000

perfomance:
avg response time
throughput - no request that can be processed per sec

capacity:
java - 100 main threads, background threads 800 threads.
16 cores - 32 process
16 gb

Extras:
In client, in case of heavy load, we can use exp backoff to reduce further load to server.



database:
load:
no. of read queries per second
no. of write queries per second
read write ratio

performance:
avg read query execution time with index: 1-2ms
avg write query execution time with index: 5ms
data size

capacity:
56 to 64 cores - no of threads
128 gb ram - caching
64*2

sharding and clustering master slave


load balancer:
  master + slave + slave


disaster recovery replication:


cache server/redis memcached:







Zookeeper:




Load balancer:
health check
user request

list ips[app server ips]

















*/
