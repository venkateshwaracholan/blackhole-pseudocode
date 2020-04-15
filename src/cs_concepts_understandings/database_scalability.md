vertical scaling: Increasing resources like ram, CPU and hard drives of a single node. There is an upper limit to these like technology and cost, so vertical scaling is not advisable for scaling web applications.

Horizontal scaling: instead of buying one super machine, we could instead buy n number of cost effective machines that are less powerful to scale as n can become a very large number without limitation.

Load Balancer: Load balancer distributes inbound HTTP requests to machines setup in horizontal scaling. Basic simplest load balancing method is to round robin the requests incrementally to private ips of the n servers and then wrap around. Load balancer is an internal DNS  that can also route to servers based on hosts. software load balancers, ELB, HAproxy, LVS.Load balancing can be done at Global DNS level based on availabiliyt, geography, etc

App server sessions: stateful session in app servers will fail when round robin method is used in load balancer in horizontal scaling as global DNS caching with TTL cannot guarantee that subsequent requests will be sent to the same machine unless the load balancer DNS implemented it. States should not be stored in individual app servers but instead on a shared resource like database, shared file systems etc.

Database replication: replication means to have a synchronous copy of the database server in another machine to prevent data loss in case of machine failure.


Database query cache: database returns the query results from its memory if the query results are not modified.

in memory databases: memcached, redis, these use LRU when they need memory.


master-slave:

master-master:

everything including, app servers, database servers, load balancers and communication link can fail.

tcp 80:

ssl 443:

ssh 22:
